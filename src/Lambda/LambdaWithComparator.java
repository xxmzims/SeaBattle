package Lambda;

import java.util.ArrayList;
import java.util.List;

public class LambdaWithComparator {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("dima");
        list.add("maksim");
        list.add("kira");
        list.add("k");

        // вместо компаратора можем использовать лямбда-выражение
        list.sort((x, y) -> x.length() - y.length());
        System.out.println(list);
    }
}
