package RegularExpressions;

import java.util.Arrays;

public class RegularExampleSecond {
    public static void main(String[] args) {
        String a = "Hello there hey why count privet wdwdew dwdw.ghghgh/wwewe,efdf";
        System.out.println(Arrays.toString(a.split("[\\.\\s,/]")));

        // replace принимает строку
        // replaceAll принимает регулярное выражение

        System.out.println("fjdjfnw iwejdqwokdn qwkdqsoidnq dqoidnqd dnqowwdn".replaceAll(" ", "."));

    }
}
