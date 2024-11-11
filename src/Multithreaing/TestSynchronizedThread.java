package Multithreaing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestSynchronizedThread {
    public static void main(String[] args) throws InterruptedException {
        // Создаем экземпляр Worker и запускаем его метод main
        new Worker().main();
    }
}

class Worker {
    // Объекты для синхронизации
    Object lock1 = new Object();
    Object lock2 = new Object();

    Random random = new Random(); // Генератор случайных чисел
    private List<Integer> list1 = new ArrayList<>(); // Первый список
    private List<Integer> list2 = new ArrayList<>(); // Второй список

    // Метод для добавления случайного числа в list1
    public void addToList1() {
        synchronized (lock1) { // Синхронизация на lock1
            try {
                Thread.sleep(0); // Симуляция работы
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list1.add(random.nextInt(100)); // Добавляем случайное число в список
        }
    }

    // Метод для добавления случайного числа в list2
    public synchronized void addToList2() {
        synchronized (lock2) { // Синхронизация на lock2
            try {
                Thread.sleep(0); // Симуляция работы
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list2.add(random.nextInt(100)); // Добавляем случайное число в список
        }
    }

    // Метод для выполнения работы
    public void work() {
        // В цикле добавляем случайные числа в оба списка
        for (int i = 0; i < 1000; i++) {
            addToList1(); // Добавляем в первый список
            addToList2(); // Добавляем во второй список
        }
    }

    // Главный метод работы с потоками
    public void main() throws InterruptedException {
        long before = System.currentTimeMillis(); // Запоминаем время начала

        // Создаем первый поток
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                work(); // Запускаем метод work
            }
        });

        // Создаем второй поток
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                work(); // Запускаем метод work
            }
        });

        // Запускаем потоки
        thread1.start();
        thread2.start();

        // Ожидаем завершения обоих потоков
        thread1.join();
        thread2.join();

        long after = System.currentTimeMillis(); // Запоминаем время завершения
        System.out.println("Program took " + (after - before) + " ms to run"); // Вывод времени выполнения
        System.out.println(list1.size()); // Вывод размера первого списка
        System.out.println(list2.size()); // Вывод размера второго списка
    }
}