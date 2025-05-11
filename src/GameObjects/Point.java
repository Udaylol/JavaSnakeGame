package GameObjects;

public class Point {
    // PUBLIC DATA MEMBERS
    public int x, y;

    // CONSTRUCTOR
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // PUBLIC METHODS
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        return this.x == ((Point) obj).x && this.y == ((Point) obj).y;
    }
}
