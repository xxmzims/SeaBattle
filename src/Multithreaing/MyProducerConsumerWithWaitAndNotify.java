package Multithreaing;

import java.util.LinkedList;
import java.util.Queue;

public class MyProducerConsumerWithWaitAndNotify {
    private static Queue<Integer> queue = new LinkedList<>(); // Очередь для хранения элементов
    private final static int LIMIT = 10; // Максимальный размер очереди
    private final static Object lock = new Object(); // Объект для синхронизации

    public static void main(String[] args) throws InterruptedException {
        // Создание потоков для производства и потребления данных
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    produce(); // Запуск метода produce
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consume(); // Запуск метода consume
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread1.start(); // Запуск потока производителя
        thread2.start(); // Запуск потока потребителя

        thread1.join(); // Ожидание завершения потока производителя
        thread2.join(); // Ожидание завершения потока потребителя
    }

    // Метод для производства данных
    private static void produce() throws InterruptedException {
        int value = 0; // Переменная для хранения производимого значения
        while (true) {
            synchronized (lock) { // Синхронизация на объекте lock
                while (queue.size() == LIMIT) { // Если очередь заполнена, ждем освобождения места
                    lock.wait(); // Освобождение мониторного блока
                }
                queue.offer(value++); // Добавляем новое значение в очередь и увеличиваем значение
                lock.notify(); // Оповещение потребителя о новом элементе
            }
        }
    }

    // Метод для потребления данных
    private static void consume() throws InterruptedException {
        while (true) {
            synchronized (lock) { // Синхронизация на объекте lock
                while (queue.isEmpty()) { // Если очередь пуста, ждем появления элемента
                    lock.wait(); // Освобождение мониторного блока
                }
                int value = queue.poll(); // Извлечение элемента из очереди
                System.out.println(value); // Выводим извлеченное значение
                System.out.println(queue.size()); // Вывод текущего размера очереди
                lock.notify(); // Оповещение производителя о наличии свободного места
            }
            Thread.sleep(1000); // Симуляция времени обработки данных: спим 1 секунду между извлечениями
        }
    }
}