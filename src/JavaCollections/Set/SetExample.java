package JavaCollections.Set;

import java.util.HashSet; // Импортируем HashSet
import java.util.Set;     // Импортируем интерфейс Set

public class SetExample {
    public static void main(String[] args) {
        // Создаем экземпляр HashSet
        Set<String> set = new HashSet<>();

        // Добавление элементов в набор
        set.add("Apple");  // Добавляем "Apple"
        set.add("Banana"); // Добавляем "Banana"
        set.add("Orange"); // Добавляем "Orange"

        // Попытка добавить дубликат
        boolean isAdded = set.add("Apple"); // Вернет false, т.к. "Apple" уже есть в наборе
        System.out.println("Was 'Apple' added again? " + isAdded);

        // Проверка на наличие элемента
        boolean hasBanana = set.contains("Banana"); // Вернет true
        System.out.println("Does the set contain 'Banana'? " + hasBanana);

        // Удаление элемента
        set.remove("Orange"); // Удаляем "Orange"
        System.out.println("Set after removing 'Orange': " + set);

        // Перебор элементов в наборе
        System.out.println("Elements in the set:");
        for (String fruit : set) {
            System.out.println(fruit); // Выводим каждый элемент
        }

        // Получение размера набора
        int size = set.size(); // Получаем количество элементов
        System.out.println("Size of the set: " + size);

        // Очистка набора
        set.clear(); // Удаляем все элементы
        System.out.println("Set after clearing: " + set);
    }
}
