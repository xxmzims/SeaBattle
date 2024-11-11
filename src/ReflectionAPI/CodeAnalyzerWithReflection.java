package ReflectionAPI;

import java.util.Arrays;

public class CodeAnalyzerWithReflection {
    public static void analyzeClass(Object o){

        //Вывести название класса, к которому принадлежит объект о
        //Вывести названия всех полей этого класса
        //Вывести названия всех методов этого класса

        // Создаем экземпляр объекта Class
        Class clazz = o.getClass();

        // Выводит название класса объекта о
        System.out.println("Имя класса: " + clazz);
        // поля класса
        System.out.println("Поля класса: " + Arrays.toString(clazz.getDeclaredFields()));
        // Родительский класс
        System.out.println("Родительский класс: " + clazz.getSuperclass());
        // методы класса
        System.out.println("Методы класса: " + Arrays.toString(clazz.getDeclaredMethods()));
        // Конструкторы класса
        System.out.println("Конструкторы класса: " + Arrays.toString(clazz.getConstructors()));
    }

    public static void main(String[] args) {
        analyzeClass(new Person());
    }
}
