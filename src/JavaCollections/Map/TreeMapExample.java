package JavaCollections.Map;

/* TreeMap в Java

TreeMap — это структура данных, которая реализует интерфейс NavigableMap и основана на красно-черном дереве.
 Она хранит уникальные ключи в отсортированном порядке, при этом каждому ключу сопоставляется значение.

#### Основные особенности TreeMap:

1. Сортировка: В отличие от HashMap, TreeMap хранит элементы в отсортированном порядке, основываясь на порядке ключей.
 Ключи могут быть отсортированы по их естественному порядку (если они реализуют интерфейс Comparable)
  или с использованием заданного компаратора.

2. Логарифмические временные операции: Операции вставки, удаления и поиска в TreeMap выполняются за O(log n),
что медленнее по сравнению с HashMap (где операции выполняются за O(1) в среднем), но предоставляет сортированные данные.

3. Уникальные ключи: Как и в HashMap, TreeMap не позволяет иметь дублирующиеся ключи.
 При попытке вставить значение с уже существующим ключом, старое значение будет заменено.

4. Не допускает null ключи: TreeMap не позволяет использовать null в качестве ключа, в отличие от HashMap.*/

import java.util.TreeMap;

public class TreeMapExample {
    public static void main(String[] args) {
        // Создаем новый TreeMap для хранения пар ключ-значение
        TreeMap<String, Integer> map = new TreeMap<>();

        // Добавляем элементы в TreeMap
        map.put("Banana", 3);
        map.put("Apple", 1);
        map.put("Cherry", 2);
        map.put("Date", 5);

        // Попытка добавления элемента с существующим ключом
        int previousValue = map.put("Apple", 4); // Обновляет значение для "Apple"
        System.out.println("Предыдущее значение для 'Apple': " + previousValue);

        // Отображение всех элементов TreeMap
        System.out.println("Элементы в TreeMap: " + map);

        // Получение значения по ключу
        System.out.println("Количество бананов: " + map.get("Banana"));

        // Удаление элемента по ключу
        map.remove("Cherry");
        System.out.println("После удаления 'Cherry': " + map);

        // Итерация по ключам и значениям
        System.out.println("Итерация по элементам TreeMap:");
        for (String key : map.keySet()) {
            System.out.println(key + ": " + map.get(key));
        }

        // Получение первого и последнего ключа
        System.out.println("Первый ключ: " + map.firstKey());
        System.out.println("Последний ключ: " + map.lastKey());

        // Создание TreeMap с пользовательским компаратором для сортировки по длине ключа
        TreeMap<String, Integer> mapByLength = new TreeMap<>((s1, s2) -> Integer.compare(s1.length(), s2.length()));
        mapByLength.put("Peach", 2);
        mapByLength.put("Kiwi", 1);
        mapByLength.put("Watermelon", 3);

        // TreeMap будет отсортирован по длине строки ключа
        System.out.println("Элементы, отсортированные по длине ключа: " + mapByLength);
    }
}
