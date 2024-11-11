package JavaCollections.Set;

/* TreeSet в Java

TreeSet — это коллекция, которая реализует интерфейс NavigableSet.
 Она основана на красно-черном дереве и хранит уникальные элементы в отсортированном порядке.
 Основные преимущества использования TreeSet включают автоматическую сортировку элементов и возможность выполнения операций
 поиска и извлечения по логарифмическому времени.

#### Основные отличия от HashSet:
1. Сортировка: В отличие от HashSet, который не гарантирует порядок элементов,
TreeSet хранит элементы в отсортированном порядке (по возрастанию).
2. Скорость операций: В то время как операции в HashSet выполняются за O(1) в среднем, операции в TreeSet
 выполняются за O(log n) из-за необходимости поддержания порядка.
3. Хранение дубликатов: Оба типа коллекций не допускают дублирования.
 Если вы попытаетесь добавить элемент, который уже существует в TreeSet, он не будет добавлен.
4. Типы хранимых объектов: TreeSet требует, чтобы хранимые элементы реализовывали интерфейс Comparable
или предоставляли компаратор через конструктор для обеспечения их упорядочивания. */

import java.util.TreeSet;

public class TreeSetExample {
    public static void main(String[] args) {
        // Создаем новый TreeSet для хранения строк
        TreeSet<String> set = new TreeSet<>();

        // Добавляем элементы в TreeSet
        set.add("Banana");
        set.add("Apple");
        set.add("Cherry");
        set.add("Date");

        // Попытка добавить дубликат. Он не будет добавлен.
        boolean isAdded = set.add("Apple"); // Возвращает false
        System.out.println("Дубль \"Apple\" добавлен: " + isAdded);

        // Отображение элементов в отсортированном порядке
        System.out.println("Элементы в TreeSet: " + set);

        // Большинство методов коллекции возвращает элементы в отсортированном порядке.
        System.out.println("Первый элемент: " + set.first());
        System.out.println("Последний элемент: " + set.last());

        // Удаляем элемент
        set.remove("Banana");
        System.out.println("После удаления \"Banana\": " + set);

        // Итерация по элементам
        System.out.println("Итерация по элементам TreeSet:");
        for (String fruit : set) {
            System.out.println(fruit);
        }

        // Создание TreeSet с пользовательским компаратором для сортировки по длине строки
        TreeSet<String> setByLength = new TreeSet<>((s1, s2) -> Integer.compare(s1.length(), s2.length()));
        setByLength.add("Peach");
        setByLength.add("Kiwi");
        setByLength.add("Watermelon");
        // TreeSet будет отсортирован по длине строк
        System.out.println("Элементы, отсортированные по длине: " + setByLength);
    }
}
