package Multithreaing; // Определение пакета для класса

import java.io.*; // Импорт необходимых классов для работы с вводом/выводом
import java.net.*; // Импорт классов для работы с сетевыми сокетами
import java.util.concurrent.*; // Импорт классов для работы с многопоточностью

public class SimpleWebServer {
    private final ExecutorService executorService; // Используется для управления пулом потоков

    // Конструктор принимающий номер порта и размер пула потоков
    public SimpleWebServer(int port, int poolSize) throws IOException {
        // Создание серверного сокета для прослушивания поступающих запросов на указанном порту
        ServerSocket serverSocket = new ServerSocket(port);
        // Создание пула потоков с фиксированным размером
        executorService = Executors.newFixedThreadPool(poolSize);

        // Бесконечный цикл для обработки входящих соединений
        while (true) {
            // Принятие входящего соединения от клиента
            Socket clientSocket = serverSocket.accept();
            // Подача задачи на выполнение в пул потоков
            executorService.submit(new RequestHandler(clientSocket));
        }
    }

    // Вложенный класс для обработки HTTP-запросов
    private static class RequestHandler implements Runnable {
        private final Socket clientSocket; // Сокет для взаимодействия с клиентом

        // Конструктор принимающий сокет клиента
        public RequestHandler(Socket socket) {
            this.clientSocket = socket; // Инициализация сокета клиента
        }

        @Override
        public void run() {
            // Метод, который будет исполняться в отдельном потоке для каждого клиента
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
                // Чтение первой строки запроса от клиента
                String requestLine = in.readLine();
                // Обработка запроса и вывод его на консоль
                System.out.println("Received: " + requestLine);
                // Формирование HTTP-ответа
                out.println("HTTP/1.1 200 OK"); // Статус ответа
                out.println("Content-Type: text/html"); // Тип содержимого
                out.println(); // Пустая строка для разделения заголовков и тела
                out.println("<h1>Hello, World!</h1>"); // Тело ответа (ответная HTML-страница)
            } catch (IOException e) {
                e.printStackTrace(); // Вывод ошибки, если возникает ошибка ввода/вывода
            } finally {
                // Закрытие сокета после обработки запроса
                try {
                    clientSocket.close(); // Закрываем сокет клиента
                } catch (IOException e) {
                    e.printStackTrace(); // Вывод ошибки при закрытии сокета
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            // Создание и запуск веб-сервера на порту 8080 с пулом из 10 потоков
            new SimpleWebServer(8080, 10);
        } catch (IOException e) {
            e.printStackTrace(); // Обработка исключений при создании сервера
        }
    }
}