package com.algo;

import java.util.List;

public class Path {
    private final List<Coordinates> coordinates;

    public Path(List<Coordinates> coordinates) {
        this.coordinates = coordinates;
    }

    public void insertCoordinates(Coordinates coordinates) {
        this.coordinates.add(coordinates);
    }

    public void considerLastCoordinate() {
        this.coordinates.get(coordinates.size() - 1).setConsidered();
    }
    public void removeLastCoordinate() {
        this.coordinates.remove(coordinates.size() - 1);
    }

    public void retractLastCoordinate() {
        this.coordinates.get(coordinates.size() - 1).setRetracted();
    }

    public Coordinates getIthCoordinate(int i) {
        if (i < coordinates.size()) {
            return coordinates.get(i);
        }
        else {
            return null;
        }
    }
}
