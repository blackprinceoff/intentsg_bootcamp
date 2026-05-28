package com.tasks.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CustomThreadPool {

    private final BlockingQueue<Runnable> taskQueue;
    private final List<WorkerThread> workers;
    private volatile boolean isShutdown;

    public CustomThreadPool(int poolSize) {
        this.taskQueue = new LinkedBlockingQueue<>();
        this.workers = new ArrayList<>(poolSize);
        this.isShutdown = false;

        for (int i = 0; i < poolSize; i++) {
            WorkerThread worker = new WorkerThread(taskQueue, this);
            workers.add(worker);
            worker.setName("WorkerThread-" + i);
            worker.start();
        }
    }

    public void execute(Runnable task) {
        if (isShutdown) {
            throw new IllegalStateException("ThreadPool is shut down, cannot accept new tasks.");
        }
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void shutdown() {
        this.isShutdown = true;
        for (WorkerThread worker : workers) {
            worker.stopWorker();
        }
    }

    public boolean isShutdown() {
        return isShutdown;
    }
}
