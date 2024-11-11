package Multithreaing;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    // Создаем семафор с разрешением на 3 одновременно работающих потоков
    private static final Semaphore semaphore = new Semaphore(3);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // Запускаем 10 потоков, которые будут пытаться получить доступ к ресурсу
        for (int i = 0; i < 10; i++) {
            int threadNumber = i;
            executorService.submit(() -> {
                try {
                    // Пытаемся получить семафор
                    semaphore.acquire();
                    System.out.println("Thread " + threadNumber + " is accessing the resource.");
                    // Имитация работы с ресурсом
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    // Освобождаем семафор
                    semaphore.release();
                    System.out.println("Thread " + threadNumber + " has released the resource.");
                }
            });
        }

        executorService.shutdown();
    }
}