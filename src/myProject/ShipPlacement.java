package myProject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

public class ShipPlacement {

    private static final int FOUR_DECKS = 4;
    private static final int THREE_DECKS = 3;
    private static final int TWO_DECKS = 2;
    private static final int ONE_DECK = 1;
    private final Player player;
    public List<Ship> ships = new ArrayList<>();
    private final BufferedReader input;
    private final Random random = new Random();

    public ShipPlacement(Player player) {
        input = new BufferedReader(new InputStreamReader(System.in));
        this.player = player;
    }

    public void placeShips() {
        System.out.println("Выберите способ расстановки кораблей:");
        System.out.println("1 - Расставить корабли вручную");
        System.out.println("2 - Автоматическая расстановка кораблей");
        while (true) {
            try {
                int choice = Integer.parseInt(input.readLine());
                if (choice == 1) {
                    manualPlacement();
                    break;
                } else if (choice == 2) {
                    automaticPlacement();
                    break;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Неккоректный ввод, попробуйте еще раз...");
            }
        }
    }

    private void manualPlacement() {
        placeShip(FOUR_DECKS, "четырех палубного корабля");
        for (int i = 0; i < 2; i++) {
            placeShip(THREE_DECKS, (i + 1) + "-го трех палубного корабля");
        }
        for (int i = 0; i < 3; i++) {
            placeShip(TWO_DECKS, (i + 1) + "-го двух палубного корабля");
        }
        for (int i = 0; i < 4; i++) {
            placeShip(ONE_DECK, (i + 1) + "-го одно палубного корабля");
        }
    }

    private void automaticPlacement() {
        placeRandomShip(FOUR_DECKS);
        for (int i = 0; i < 2; i++) {
            placeRandomShip(THREE_DECKS);
        }
        for (int i = 0; i < 3; i++) {
            placeRandomShip(TWO_DECKS);
        }
        for (int i = 0; i < 4; i++) {
            placeRandomShip(ONE_DECK);
        }

    }

    private void placeShip(int shipSize, String shipDescription) {
        while (true) {
            try {
                Ship ship = requestShipCoordinates(shipSize, shipDescription);
                ships.add(ship);
                player.initView();
                Utils.printBoard(player.board);
                break;
            } catch (Exception e) {
                System.out.println("Введено некорректное значение. Повторите попытку.");
            }
        }
    }

    private void placeRandomShip(int shipSize) {
        SortedSet<Coordinate> coordinates;
        boolean placed = false;

        while (!placed) {
            boolean horizontal = random.nextBoolean();
            int row = random.nextInt(10);
            int col = random.nextInt(10);
            coordinates = new TreeSet<>();

            // Проверяем возможность размещения корабля
            if (horizontal && col + shipSize <= 10) {
                for (int i = 0; i < shipSize; i++) {
                    coordinates.add(new Coordinate(row, col + i));
                }
            } else if (!horizontal && row + shipSize <= 10) {
                for (int i = 0; i < shipSize; i++) {
                    coordinates.add(new Coordinate(row + i, col));
                }
            } else {
                continue;
            }

            if (Utils.correctShip(new Ship(coordinates, shipSize), ships)) {
                ships.add(new Ship(coordinates, shipSize));
                placed = true;
            }
        }
    }

    private Ship requestShipCoordinates(int shipSize, String shipDescription) throws Exception {
        while (true) {
            System.out.printf("Введите через запятую координаты %s.\n", shipDescription);
            String stringOfCoordinates = input.readLine();
            SortedSet<Coordinate> coordinates = Utils.parseInputString(stringOfCoordinates);
            Ship ship = new Ship(coordinates, shipSize);
            if (Utils.correctShip(ship, ships)) {
                return ship;
            } else {
                System.out.println("Введено некорректное значение. Повторите попытку.");
            }
        }
    }
}