package Multithreaing;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPool{
    public static void main(String[] args) throws InterruptedException{
        // Создание пула потоков
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 5; i++) {
            // создание заданий для нашего ExecutorService
            executorService.submit(new Work(i));
        }
        // прекращение передачи новых заданий, начинает выполнять созданные задания
        executorService.shutdown();
        System.out.println("All tasks submitted");
        // ждем определенное количество времени, пока наши потоки завершаться
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}
class Work implements Runnable{
    private int id;
    public Work(int id){
        this.id = id;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Work " + id + " was completed");
    }
}
