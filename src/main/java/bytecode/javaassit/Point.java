package javaassit;

public class Point {
    int x, y;

    public Point() {
    }

    int move(int dx, int dy)
    { x += dx; y += dy;
      return x + y;
    }
}
