package myProject;

import java.util.*;

public class Utils {
    public static final HashMap<Character, Integer> letters = new HashMap<>();

    static {
        letters.put('а', 0);
        letters.put('б', 1);
        letters.put('в', 2);
        letters.put('г', 3);
        letters.put('д', 4);
        letters.put('е', 5);
        letters.put('ж', 6);
        letters.put('з', 7);
        letters.put('и', 8);
        letters.put('к', 9);
    }


    public static void printBoard(Cell[][] board) {
        System.out.println("   а  б  в  г  д  е  ж  з   и  к");
        for (int i = 0; i < board.length; i++) {
            System.out.print((i + 1));
            if (i != 9)
                System.out.print(" ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j].getRepresentation() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static Boolean collisionOfShips(Ship ship, List<Ship> existShips) {
        for (Coordinate coordinate : ship.coordinatesOfShip) {

            for (Ship existShip : existShips) {
                for (Coordinate existCoordinate : existShip.coordinatesOfShip) {

                    int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1, 0};
                    int[] dy = {0, 1, 0, -1, -1, 1, -1, 1, 0};

                    for (int i = 0; i < dx.length; i++) {
                        if (coordinate.y == (existCoordinate.y + dx[i]) && coordinate.x == (existCoordinate.x + dy[i])) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static boolean correctShip(Ship ship, List<Ship> existShips) {
        int horizontal = 0;
        int vertical = 0;

        if (ship.coordinatesOfShip.size() != ship.getSizeOfShip()) {
            System.out.println("Неккоректное количество координат");
            return false;
        }

        Iterator<Coordinate> iterator = ship.coordinatesOfShip.iterator();

        if (iterator.hasNext()) {
            Coordinate previous;
            Coordinate current = iterator.next();


            if (!(current.x >= 0 && current.x <= 9 && current.y >= 0 && current.y <= 9)) {
                System.out.println("Одна из координат вышла за границу поля");
                return false;
            }


            while (iterator.hasNext()) {

                previous = current;
                current = iterator.next();


                if (!(current.x >= 0 && current.x <= 9 && current.y >= 0 && current.y <= 9)) {
                    System.out.println("Одна из координат вышла за границу поля");
                    return false;
                }

                if (current.y == previous.y + 1) {
                    horizontal++;
                } else if (current.x == previous.x + 1) {
                    vertical++;
                }
            }
        }


        return (vertical == ship.getSizeOfShip() - 1 || horizontal == ship.getSizeOfShip() - 1) && collisionOfShips(ship, existShips);
    }

    public static SortedSet<Coordinate> parseInputString(String string) throws NullPointerException, NumberFormatException {
        String[] coordinatesSplit = string.toLowerCase().split(", *");
        SortedSet<Coordinate> coordinates = new TreeSet<>();
        for (String coordinateString : coordinatesSplit) {

            if (coordinateString.substring(1).equals("10")) {
                int x = Integer.parseInt(String.valueOf(coordinateString.charAt(1)) + coordinateString.charAt(2)) - 1;
                int y = letters.get(coordinateString.charAt(0));
                Coordinate coordinate = new Coordinate(x, y);
                coordinates.add(coordinate);
            } else {
                int x = Integer.parseInt(String.valueOf(coordinateString.charAt(1))) - 1;
                int y = letters.get(coordinateString.charAt(0));
                Coordinate coordinate = new Coordinate(x, y);
                coordinates.add(coordinate);
            }
        }
        return coordinates;
    }
}
