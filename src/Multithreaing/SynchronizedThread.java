package Multithreaing;
/*
 В Java монитор — это механизм синхронизации, который используется для управления доступом к общим ресурсам в многопоточном окружении.
Каждый объект в Java имеет связанный с ним монитор.
Когда поток пытается войти в «синхронизированный» блок кода или метод, он захватывает монитор объекта.
* Если другой поток уже захватил этот монитор, то текущий поток будет ждать, пока монитор не будет освобожден.
Использование мониторов помогает избежать ошибок, связанных с параллельным доступом к данным.
Основные положения:
- synchronized используется для создания синхронизированных методов и блоков.
- Мониторы обеспечивают взаимное исключение (mutex), предотвращая гонки данных.
*/

public class SynchronizedThread {
    public static int counter;

    public static void main(String[] args) throws InterruptedException {
        MyThreadSync myThreadSync = new MyThreadSync();
        myThreadSync.start();
        MyThreadSync myThreadSync1 = new MyThreadSync();
        myThreadSync1.start();
        // метод join дает возможность подождать пока не выполниться поток.
        myThreadSync.join();
        myThreadSync1.join();

        System.out.println(counter);
    }

    // пример синхронизации метода.
    // Пока один поток использует метод, в сигнатуре которого указано ключевое слово synchronized,
    // другие потоки не смогут получить доступ, что обеспечивает синхронизацию.
    public static synchronized void increment() {
        SynchronizedThread.counter++;
    }

    public void decrement() {
        // пример синхронизации блока, использует монитор, указанного объекта в скобках.
        synchronized (this) {
            SynchronizedThread.counter--;
        }
    }
}

class MyThreadSync extends Thread {
    // Ключевое слово volatile используется для того, чтобы гарантировать когерентность кэшей.
    // То есть, переменная помеченная этим ключевым словом не будет кэшироваться для ядра, в котором работает этот поток.
    // Переменная будет всегда доступна для чтения другим потокам и она всегда будет одинаковая для всех потоков.
    private volatile boolean running = true;

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            SynchronizedThread.increment();
        }
    }

    public void shutdown() {
        this.running = false;
    }
}
