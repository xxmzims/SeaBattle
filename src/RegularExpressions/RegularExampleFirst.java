package RegularExpressions;

public class RegularExampleFirst {
    public static void main(String[] args) {
        // \\d - одна цифра
        // + - 1 и более
        // * - 0 или более
        // ? - 0 или 1 символов до
        // \\w - одна буква
        // (x | y | z) - одна строка из множества строк

        //[a-zA-z] - все английские буквы алфавита
        // [abc] = (a|b|c)
        // [0-9] - \\d

        // . - любой символ

        //[^abc] - мы хотим все символы кроме [abc]

        //{2} 2 символа до
        //{2,} 2 и более символов
        //{2, 4} от 2 до 4 символов

        String a = "21321";
        String b = "-21321";
        String c = "+12223";

        System.out.println(c.matches("(\\+|-)?\\d+"));
        String d = "gewrw1ew3erwer9852375423";

        System.out.println(d.matches("[a-zA-Z31]*\\d+")); // true

        String e = "hello";
        String f = "aaafdfdf";

        System.out.println(e.matches("[^abc]*")); // true

        System.out.println(f.matches("[^abc]*")); // false

        String url = "http://www.google.com";
        String url2 = "http://www.yandex.ru";
        String url3 = "http://www.freemen.for";
        System.out.println(url.matches("http://www\\..+\\.(com|ru)")); // true
        System.out.println(url2.matches("http://www\\..+\\.(com|ru)")); // true
        System.out.println(url3.matches("http://www\\..+\\.(com|ru)")); // false

    }
}
