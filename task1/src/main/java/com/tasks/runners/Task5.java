package com.tasks.runners;

import com.tasks.pool.CustomThreadPool;

public class Task5 {

    public static void run() {
        int poolSize = 3;
        int numberOfTasks = 10;

        CustomThreadPool pool = new CustomThreadPool(poolSize);

        for (int i = 1; i <= numberOfTasks; i++) {
            final int taskId = i;
            pool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " is executing Task " + taskId);
                try {
                    Thread.sleep((long) (Math.random() * 500) + 100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println(Thread.currentThread().getName() + " has completed Task " + taskId);
            });
        }

        pool.shutdown();
    }
}
