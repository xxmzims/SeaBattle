package Multithreaing; // Определение пакета для класса

public class InterruptedExample {
    public static void main(String[] args) throws InterruptedException {

        // Создание нового потока с использованием лямбда-выражения
        Thread thread = new Thread(() -> {
            // Цикл, который будет выполняться 100 миллионов раз
            for (int i = 0; i < 100_000_000; i++) {
                System.out.println(i);
                if(Thread.currentThread().isInterrupted()){
                    break;
                }
                // Поток засыпает на 100 миллисекунд
            }
        });

        // Запуск потока
        thread.start();

        // Главный поток засыпает на 3000 миллисекунд (3 секунды)
        Thread.sleep(3000);

        // Прерываем работающий поток
        thread.interrupt();

        // Ожидаем завершения потока
        thread.join();
    }
}
