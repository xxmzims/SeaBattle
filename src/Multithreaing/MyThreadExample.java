package Multithreaing;
// Многопоточность очень важная тема, позволяет распределить задачи между потоками. Каждый поток будет выполнять разные задания

public class MyThreadExample{
    public static void main(String[] args) {
        // Создание потоков
        MyThread myThread = new MyThread();
        MyThreadSecond secondThread = new MyThreadSecond();
        // метод start() запускает поток
        secondThread.start();
        myThread.start();
        System.out.println("hello from main Thread");

        // При использовании способа создания с реализацией интерфейса Runnable,
        // мы создаем объект класса Thread, в конструктор передаем наш класс-поток
        Thread thread = new Thread(new Runner());
        thread.start();

        // При запуске больше двух потоков, процессор будет очень быстро переключаться между потоками,
        // вывод в терминал будет хаотичным
    }

}
// класс унаследованный от Thread является потоком

class MyThread extends Thread{
    // в методе run выполняется код при запуске потока
    @Override
    public void run() {
        // Thread.sleep() заставляет поток заснуть принимает в качестве аргумента время в миллисекундах
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < 1000; i++) {
            System.out.println("Hello from my " + Thread.currentThread().getName());
        }
    }
}
class MyThreadSecond extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("Hello from my " + Thread.currentThread().getName());
        }
    }
}
// Другой способ создать поток, реализуя метод Runnable
class Runner implements Runnable{
    @Override
    public void run() {
        for (int i = 1000; i < 1500; i++) {
            System.out.println("Hello from my " + Thread.currentThread().getName());
        }
    }
}