package myProject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Player {
    public ShipPlacement shipPlacement = new ShipPlacement(this);
    private final String name;
    Set<Coordinate> moves = new HashSet<>();
    public Cell[][] board = new Cell[10][10];
    public Cell[][] enemyBoard = new Cell[10][10];

    public String getName() {
        return name;
    }

    public Player(String name) throws Exception {
        this.name = name;
        initBoard();
        shipPlacement.placeShips();
    }

    private void initBoard() {
        for (Cell[] cells : board) {
            for (int j = 0; j < cells.length; j++) {
                cells[j] = new Cell(CellState.WATER);
            }
        }
        for (Cell[] cells : enemyBoard) {
            for (int j = 0; j < cells.length; j++) {
                cells[j] = new Cell(CellState.WATER);
            }
        }
    }

    public void initView() {
        for (Ship ship : shipPlacement.ships) {
            for (Coordinate coordinate : ship.coordinatesOfShip) {
                board[coordinate.x][coordinate.y].setState(CellState.SHIP);
            }
        }
    }

    public void updateView() {
        for (Ship ship : shipPlacement.ships) {
            if (ship.getSizeOfShip() == ship.hitCount) {
                for (Coordinate coordinate : ship.coordinatesOfShip) {
                    board[coordinate.x][coordinate.y].setState(CellState.DESTROYED);
                }
            }
        }
    }

    public Coordinate doMove() throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Coordinate coordinate = null;

        while (coordinate == null) {
            System.out.printf("Ход игрока %s\nВведите координаты для хода, пример а1\n", name);
            String input = bufferedReader.readLine();

            try {
                coordinate = Utils.parseInputString(input).first();
                if (moves.contains(coordinate)) {
                    System.out.println("Эти координаты уже были использованы. Попробуйте снова.");
                    coordinate = null; // Сбросить координаты, так как они не валидны
                } else {
                    moves.add(coordinate);
                }
            } catch (IndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
                System.out.println("Некорректный ввод. Убедитесь, что вы вводите координаты в правильном формате.");
            }
        }
        return coordinate;
    }
}