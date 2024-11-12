package Multithreaing;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    static Task task = new Task(); // Создаем общий объект Task для двух потоков

    public static void main(String[] args) throws InterruptedException {
        // Создание первого потока
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                task.firstThread(); // Запуск метода первого потока
            }
        });

        // Создание второго потока
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                task.secondThread(); // Запуск метода второго потока
            }
        });

        thread1.start(); // Запуск первого потока
        thread2.start(); // Запуск второго потока

        thread1.join(); // Ожидание завершения первого потока
        thread2.join(); // Ожидание завершения второго потока
        System.out.println(task.getCounter()); // Вывод значения счетчика после завершения обоих потоков
    }
}

class Task {
    private int counter; // Переменная для хранения значения счетчика
    private Lock lock = new ReentrantLock(); // Создание объекта ReentrantLock

    private void increment() { // Метод для увеличения счетчика на 10000
        for (int i = 0; i < 10000; i++) {
            counter++; // Увеличиваем счетчик на 1
        }
    }

    public void firstThread() { // Метод, выполняемый первым потоком
        lock.lock(); // Блокируем доступ другим потокам
        increment(); // Увеличиваем счетчик
        lock.unlock(); // Освобождаем блокировку
    }

    public void secondThread() { // Метод, выполняемый вторым потоком
        lock.lock(); // Блокируем доступ другим потокам
        increment(); // Увеличиваем счетчик
        lock.unlock(); // Освобождаем блокировку
    }

    public int getCounter() { // Метод для получения значения счетчика
        return counter; // Возврат значения счетчика
    }
}