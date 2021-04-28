package com.algo;

import java.util.Objects;

public class Coordinates {
    private final int x;
    private final int y;
    private boolean isConsidered;
    private boolean isRetracted;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
        this.isConsidered = false;
        this.isRetracted = false;
    }

    public boolean isRetracted() {
        return isRetracted;
    }

    public boolean isConsidered() {
        return isConsidered;
    }

    public void setConsidered() {
        this.isConsidered = true;
    }
    public void setRetracted() {
        this.isRetracted = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates)) return false;
        Coordinates that = (Coordinates) o;
        return getX() == that.getX() && getY() == that.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}
