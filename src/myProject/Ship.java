package myProject;

import java.util.SortedSet;

public class Ship {
    public SortedSet<Coordinate> coordinatesOfShip;
    private final int sizeOfShip;
    public int hitCount = 0;

    public Ship(SortedSet<Coordinate> coordinatesOfShip, int sizeOfShip) {
        this.coordinatesOfShip = coordinatesOfShip;
        this.sizeOfShip = sizeOfShip;
    }

    public int getSizeOfShip() {
        return sizeOfShip;
    }


    public boolean isSunk() {
        return hitCount >= sizeOfShip;
    }
}