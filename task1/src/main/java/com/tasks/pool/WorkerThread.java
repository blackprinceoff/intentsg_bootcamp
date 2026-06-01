package com.tasks.pool;

import java.util.concurrent.BlockingQueue;

public class WorkerThread extends Thread {

    private final BlockingQueue<Runnable> taskQueue;
    private final CustomThreadPool pool;
    private volatile boolean isStopped;

    public WorkerThread(BlockingQueue<Runnable> taskQueue, CustomThreadPool pool) {
        this.taskQueue = taskQueue;
        this.pool = pool;
        this.isStopped = false;
    }

    @Override
    public void run() {
        while (!isStopped) {
            try {
                if (pool.isShutdown() && taskQueue.isEmpty()) {
                    break;
                }

                Runnable task = taskQueue.take();
                task.run();

            } catch (InterruptedException e) {
                if (isStopped) {
                    break;
                }
                Thread.currentThread().interrupt();
            } catch (Exception e) {
                System.err.println("Exception in " + Thread.currentThread().getName() + ": " + e.getMessage());
            }
        }
    }

    public void stopWorker() {
        isStopped = true;
        this.interrupt();
    }
}
