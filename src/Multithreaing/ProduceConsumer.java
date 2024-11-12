package Multithreaing;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


/* Производитель (Producer): Этот компонент создает данные и помещает их в общую структуру (например, в очередь)
 для последующего использования. Производитель может работать в отдельном потоке и периодически добавлять данные.

2. Потребитель (Consumer): Этот компонент извлекает данные из общей структуры для их обработки.
 Потребитель также может работать в своем собственном потоке, ожидая появления данных в очереди.

3. Общая структура (Shared Resource): Это место, где производители помещают созданные данные, и откуда потребители извлекают данные.
 Оно должно быть синхронизировано, чтобы избежать гонки данных и обеспечить согласованный доступ.*/

public class ProduceConsumer {
    private static BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {
        // создание фиксированной очереди
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    produce();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consumer();
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

    private static void produce() throws InterruptedException {
        Random random = new Random();

        while (true) {
            // добавление случайного числа в очередь
            // put ждет пока место в очереди не освободиться

            blockingQueue.put(random.nextInt(100));
        }
    }

    private static void consumer() throws InterruptedException {
        Random random = new Random();
        while (true) {
            Thread.sleep(100);
            // достаем из очереди элемент
            System.out.println(blockingQueue.take());
            System.out.println("Queue size is " + blockingQueue.size());
        }
    }
}
