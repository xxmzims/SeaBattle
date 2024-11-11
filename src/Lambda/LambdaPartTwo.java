package Lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LambdaPartTwo {
    public static void main(String[] args) {
        int[] arr = new int[10];
        List<Integer> list = new ArrayList<>();


        // Заполняем массив и лист
        fillArr(arr);
        fillList(list);

        System.out.println(list);
        System.out.println(Arrays.toString(arr));
        // используем цикл для умножения каждого элемента на 2
        for (int i = 0; i < 10; i++) {
            arr[i] = arr[i] * 2;
            list.set(i, list.get(i) * 2);
        }
        System.out.println(list);
        System.out.println(Arrays.toString(arr));

        // используем стримы и лямбду для умножения каждого элемента на 2

        // используем map
        arr = Arrays.stream(arr).map(x -> x * 2).toArray();
        list = list.stream().map(x -> x * 2).toList();

        arr = Arrays.stream(arr).map(a -> 3).toArray();

        arr = Arrays.stream(arr).map(a -> a + 1).toArray();



        System.out.println(list);
        System.out.println(Arrays.toString(arr));


        int[] arr2 = new int[10];
        List<Integer> list2 = new ArrayList<>();
        fillArr(arr2);
        fillList(list2);

        // использование filter подразумевай собой, что лямбда должна возвращать bool
        arr2 = Arrays.stream(arr2).filter(a -> a % 2 == 0).toArray();
        list2 = list2.stream().filter(a -> a % 2 == 0).toList();

        Arrays.stream(arr2).forEach(a -> System.out.println(a));

        // можно сократить до такого значения если используется один метод, который принимает один метод
        list2.stream().forEach(System.out::println);
        System.out.println(Arrays.toString( arr2));
        System.out.println(list2);

        // метод reduce уменьшает кол-во данных

        int[] arr3 = new int[10];
        List<Integer> list3 = new ArrayList<>();
        fillArr(arr3);
        fillList(list3);

        // acc - переменная счетчик, которая постоянно обновляется
        // identity начальное значение аккумулятора, если не указывается, то первый элемент является аккумулятором
        int sum = Arrays.stream(arr3).reduce(1, (acc, b) -> acc * b);
        System.out.println(sum);
    }
    public static void fillList(List<Integer> list){
        for (int i = 0; i < 10; i++)
            list.add(i + 1);
    }
    public static void fillArr(int[] arr){
        for (int i = 0; i < arr.length; i++)
            arr[i] = i + 1;
    }
}
