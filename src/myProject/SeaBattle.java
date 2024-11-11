package myProject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SeaBattle {
    public static void Hello() {
        System.out.println("""
                 =======================================================
                                    \uD83C\uDF0A SeaBattle \uD83D\uDEA2                     \s
                 =======================================================
                \s""");
    }

    public static void rules() {
        System.out.println();
        System.out.println("""
                Принцип «Морского боя» очень прост — вы расставляете свои корабли на поле 10х10,
                оппонент расставляет свои. Далее вы по очереди делаете «выстрелы», называя те или иные координаты поля —\s
                и оппонент отвечает на каждый «выстрел» словами «попал», «промахнулся» или «потопил».
                Пример ввода для трехпалубного корабля - а1, а2, а3, а4""");
        System.out.println();

    }

    public static void main(String[] args) throws Exception {
        Hello();
        rules();
        System.out.println("Введите имя первого игрока");
        BufferedReader input = new BufferedReader(new BufferedReader(new InputStreamReader(System.in)));
        Player player1 = new Player(input.readLine());
        System.out.println("Введите имя второго игрока");
        Player player2 = new Player(input.readLine());
        PlayerMovement.start(player1, player2);
    }
}