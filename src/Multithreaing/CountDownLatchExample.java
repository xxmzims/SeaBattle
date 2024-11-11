package Multithreaing;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        // Защелка обратного отсчета, в конструктор передается количество итераций до открытия защелки
        CountDownLatch countDownLatch = new CountDownLatch(3);

        // Создание пула потоков с фиксированным количеством потоков
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // Запускаем 3 задачи, каждая из которых работает с одним и тем же объектом CountDownLatch
        for (int i = 0; i < 3; i++) {
            executorService.submit(new Processor(countDownLatch)); // Передаем счетчик в задачу
        }

        executorService.shutdown(); // Завершает работу пула потоков
        countDownLatch.await(); // Ждет, пока счетчик не станет равен нулю
        System.out.println("Latch has been opened, main thread is processing"); // Вывод в консоль
    }
}

class Processor implements Runnable {
    private CountDownLatch countDownLatch; // Поле для хранения ссылки на CountDownLatch

    public Processor(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch; // Инициализация счетчика
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000); // Симуляция продолжительной работы, за которую выполнится задача
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        countDownLatch.countDown(); // Уменьшаем счетчик на 1 после завершения работы задачи
    }
}