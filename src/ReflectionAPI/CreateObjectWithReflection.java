package ReflectionAPI;

import java.lang.reflect.InvocationTargetException;

public class CreateObjectWithReflection {

    public static Person createPerson(){
        Class clazz = null;
        Person person = null;

        try{
            // Считываем название класса
            clazz = Class.forName("ReflectionAPI.Person");

            // Создаем структуру параметров для нашего конструктора явно указывая тип данных String и int
            Class[] personClassParams = {String.class, int.class};

            // Создаем объекта класса Person во время выполнения программы.
            person = (Person) clazz.getConstructor(personClassParams).newInstance("maks", 5);
            /*

                        ВАЖНО!!!!!!!!!!!!!!
            Обязательно следует обработать все исключения,
            чтобы избежать ошибочного ввода пользователем

            */
            // Если класса не существует
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            // Если объект класса не может быть создан
        }catch (InstantiationException e){
            e.printStackTrace();
            // Нет доступа к методу, классу, полю
        }catch (IllegalAccessException e){
            e.printStackTrace();

            // Не существует метода в классе.
        }catch (NoSuchMethodException e){
            e.printStackTrace();

            // Если во время вызова метода, возникло исключение
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }
        return person;
    }
    // Рефлексия может ломать все принципы инкапсуляции, с помощью нее можно достучаться до
    // полей, конструкторов, методов и всего остального с ЛЮБЫМ модификатором доступа
    // Кроме того мы можем вызывать методы в классах
    // с помощью метода setAccessible(bool) мы можем сделать поле ДОСТУПНЫМ
    // и уже с помощью метода set можем менять значение этого поля!!!!!
    // Рефлексия очень мощное оружие, дающее власть над всеми объектами в Java.

    public static void main(String[] args) {
        System.out.println(createPerson());
    }
}
