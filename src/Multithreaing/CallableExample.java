package Multithreaing; // Определение пакета для данного класса

import java.util.Random; // Импорт библиотеки для генерации случайных чисел
import java.util.concurrent.*; // Импорт классов для работы с многопоточностью

public class CallableExample {
    public static void main(String[] args) {
        // Создание пула потоков с фиксированным количеством потоков (в данном случае 1)
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        // Отправка задачи на выполнение в пул потоков, возвращающей Future
        Future<Integer> future = executorService.submit(() -> {
            Random random = new Random(); // Создание генератора случайных чисел
            int randomValue = random.nextInt(10); // Генерация случайного числа от 0 до 9
            // Если случайное число меньше 8, выбрасывается исключение
            if (randomValue < 8) {
                throw new Exception("Something bad happened"); // Генерация исключения
            }
            return randomValue; // Возвращение сгенерированного значения
        });

        // Завершение работы пула потоков
        executorService.shutdown();

        try {
            // Получение результата выполнения задачи. Если задача завершилась с исключением, то оно будет выброшено здесь
            System.out.println(future.get());
        } catch (InterruptedException e) {
            // Обработка прерывания текущего потока
            throw new RuntimeException(e); // Генерация исключения при прерывании
        } catch (ExecutionException e) {
            // Обработка случая, когда задача завершилась с исключением
            Throwable ex = e.getCause(); // Получение причины исключения
            System.out.println(ex.getMessage()); // Вывод сообщения об ошибке
        }
    }
}