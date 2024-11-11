package Lambda;

interface Executable {
    int execute(int x, int b);
}

class Runner {
    public void run(Executable e){
        int a = e.execute(10, 5);
        System.out.println(a);
    }
}

public class LambdaExample {
    public static void main(String[] args) {
        Runner runner = new Runner();

        // a не должно менять свое значение, чтобы ее можно было использовать в лямбда выражении
        int a = 1;

        // если строка одна то не нужно писать фигурные скобки
        // лямбда не имеет своей области видимости
        runner.run((x, y) -> x + y + a);
    }
}
