package myProject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
public class PlayerMovement {

    public static void start(Player firstPlayer, Player secondPlayer) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        firstPlayer.initView();
        secondPlayer.initView();

        while (true) {
            executeTurn(firstPlayer, secondPlayer, reader);
            executeTurn(secondPlayer, firstPlayer, reader);
            if (isGameOver(firstPlayer)) {
                System.out.println(secondPlayer.getName() + " победил!");
                break;
            }
            if (isGameOver(secondPlayer)) {
                System.out.println(firstPlayer.getName() + " победил!");
                break;
            }
        }
    }

    private static void executeTurn(Player currentPlayer, Player opponent, BufferedReader reader) throws Exception {
        System.out.println("Ваше поле");
        currentPlayer.updateView();
        Utils.printBoard(currentPlayer.board);
        System.out.println("Поле врага");
        Utils.printBoard(currentPlayer.enemyBoard);
        while (true){
            if (isGameOver(opponent)) {
                break;
            }
            Coordinate attackCoordinate = currentPlayer.doMove();
        if(processAttack(attackCoordinate, currentPlayer, opponent)) {
            System.out.println("Вы попали, ходите еще раз:\n ");
            currentPlayer.updateView();
            Utils.printBoard(currentPlayer.enemyBoard);
        }
        else break;
        }
        System.out.println("К сожалению вы промазали");
        currentPlayer.updateView();
        Utils.printBoard(currentPlayer.enemyBoard);
        System.out.println("Нажмите Enter, чтобы передать управление другому игроку.");
        reader.readLine();
    }

    public  static boolean processAttack(Coordinate coordinate, Player currentPlayer, Player opponentPlayer) {
        boolean hit = false;
        Ship hitShip = null;

        for (Ship enemyShip : opponentPlayer.shipPlacement.ships) {
            for (Coordinate enemyCoordinate : enemyShip.coordinatesOfShip) {
                if (coordinate.x == enemyCoordinate.x && coordinate.y == enemyCoordinate.y) {
                    currentPlayer.enemyBoard[coordinate.x][coordinate.y].setState(CellState.HIT);
                    opponentPlayer.board[coordinate.x][coordinate.y].setState(CellState.HIT);
                    enemyShip.hitCount++;
                    hit = true;
                    hitShip = enemyShip; // Сохраняем ссылку на потопленный корабль
                    break;
                }
            }
            if (hit) {
                if (hitShip.getSizeOfShip() == hitShip.hitCount) {
                    for (Coordinate enemyCoordinate : hitShip.coordinatesOfShip) {
                        currentPlayer.enemyBoard[enemyCoordinate.x][enemyCoordinate.y].setState(CellState.DESTROYED);
                    }
                    break;
                }
            }
        }

        if (hit) {
            if (hitShip.getSizeOfShip() == hitShip.hitCount) {
                for(Coordinate coordinate1: hitShip.coordinatesOfShip){
                    updateSurroundingCells(coordinate1, currentPlayer, opponentPlayer);
                }
            }
        } else {
            opponentPlayer.board[coordinate.x][coordinate.y].setState(CellState.MISS);
            currentPlayer.enemyBoard[coordinate.x][coordinate.y].setState(CellState.MISS);
        }
        return hit;
    }

    private static void updateSurroundingCells(Coordinate hitCoordinate, Player currentPlayer, Player opponentPlayer) {
        int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1};
        int[] dy = {0, 1, 0, -1, -1, 1, -1, 1};

        for (int i = 0; i < 8; i++) {
            int newX = hitCoordinate.x + dx[i];
            int newY = hitCoordinate.y + dy[i];

            if (newX >= 0 && newX < 10 && newY >= 0 && newY < 10) {
                if (opponentPlayer.board[newX][newY].getRepresentation().equals(CellState.WATER.getRepresentation())) {
                    opponentPlayer.board[newX][newY].setState(CellState.MISS);
                    currentPlayer.enemyBoard[newX][newY].setState(CellState.MISS);
                    currentPlayer.moves.add(new Coordinate(newX, newY));
                }
            }
        }
    }
    private static boolean isGameOver(Player opponentPlayer) {
        // Логика проверки, остались ли корабли у противника
        return opponentPlayer.shipPlacement.ships.stream().allMatch(Ship::isSunk);
    }
}