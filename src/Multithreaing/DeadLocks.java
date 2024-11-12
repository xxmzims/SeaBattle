package Multithreaing;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLocks {
    // Создаем один объект замка для синхронизации доступа к учетным записям
    public static ReentrantLock lock = new ReentrantLock();
    // Создаем два объекта учетной записи для моделирования переводов
    static Account account1 = new Account();
    static Account account2 = new Account();

    public static void main(String[] args) throws InterruptedException {
        // Создаем потоки для выполнения переводов между аккаунтами
        Thread firstTransThread = new Thread(new TransferThread(account1, account2));
        Thread secondTransThread = new Thread(new TransferThread(account2, account1));

        // Запускаем потоки
        firstTransThread.start();
        secondTransThread.start();

        // Ожидаем завершения потоков
        firstTransThread.join();
        secondTransThread.join();

        // Вызываем метод для отображения итогового баланса аккаунтов
        showBalance();
    }

    // Метод для отображения балансов обоих счетов
    public static void showBalance() {
        System.out.println(account1.showBalance()); // Печатаем баланс первого аккаунта
        System.out.println(account2.showBalance()); // Печатаем баланс второго аккаунта
        // Печатаем общий баланс
        System.out.println("Total Balance - " + (account1.showBalance() + account2.showBalance()));
    }
}

// Класс для потоков, которые выполняют переводы между аккаунтами
class TransferThread implements Runnable {
    Random random = new Random(); // Генератор случайных чисел для определения суммы перевода
    Account acc1; // Первый аккаунт для перевода
    Account acc2; // Второй аккаунт для перевода

    // Конструктор класса, инициализирующий аккаунты
    public TransferThread(Account acc1, Account acc2) {
        this.acc1 = acc1; // Присваиваем первый аккаунт
        this.acc2 = acc2; // Присваиваем второй аккаунт
    }

    @Override
    public void run() {
        // Цикл, выполняющий 10,000 переводов
        for (int i = 0; i < 10000; i++) {
            // Захватываем замок для доступа к ресурсам
            DeadLocks.lock.lock();
            try {
                // Переводим случайную сумму от одного аккаунта к другому
                Account.transfer(acc1, acc2, random.nextInt(100));
            } finally {
                // Освобождаем замок, чтобы другие потоки могли получить доступ
                DeadLocks.lock.unlock();
            }
        }
    }
}

// Класс, представляющий банковский аккаунт
class Account {
    private int balance = 10000; // Баланс аккаунта

    // Статический метод для перевода средств между двумя аккаунтами
    public static void transfer(Account acc1, Account acc2, int amount) {
        acc1.withdraw(amount); // Снимаем средства с первого аккаунта
        acc2.deposit(amount); // Вносим средства на второй аккаунт
    }

    // Метод для внесения средств на аккаунт
    public void deposit(int amount) {
        balance += amount; // Увеличиваем баланс на указанную сумму
    }

    // Метод для получения текущего баланса
    public int showBalance() {
        return balance; // Возвращаем текущий баланс
    }

    // Метод для снятия средств с аккаунта
    public void withdraw(int amount) {
        balance -= amount; // Уменьшаем баланс на указанную сумму
    }
}