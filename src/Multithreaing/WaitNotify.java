package Multithreaing;

import java.util.Scanner;

public class WaitNotify {
    public static void main(String[] args) throws InterruptedException {
        WaitAndNotify wn = new WaitAndNotify();
        Object object = new Object();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    wn.produce();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    wn.consumer();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}
class WaitAndNotify{
    public void produce() throws InterruptedException {
        synchronized (this){
            System.out.println("Produce Thread started...");
            wait(); // отдаем intrinsic lock(монитор) объекта this.
            // lock.wait() отдаем объект lock
            // Другие потоки могут забрать объект себе.
            // Ждем пока будет вызвано notify


            // ждет одну секунду пока где-то будет вызван notify
            wait(1000);
            System.out.println("Produce Thread resumed...");
        }
    }
    public void consumer() throws InterruptedException {
        Thread.sleep(2000);
        Scanner scanner = new Scanner(System.in);
        synchronized (this){
            System.out.println("Waiting for return ley pressed");
            scanner.nextLine();
            notify(); // все потоки которые ждут будут восстановлены

            // Оповестит потоки только после того когда блок synchronized будет полностью выполнен
            // то есть все инструкции, которые идут после notify() должны выполниться;
            Thread.sleep(5000);

        }
    }
}
