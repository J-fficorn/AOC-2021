public class Coord {
    public int x, y, z;
    
    public Coord(int newX, int newY, int newZ) {
        x = newX;
        y = newY;
        z = newZ;
    }
    
    public Coord change(Coord c) {
        return new Coord(this.x + c.x, this.y + c.y, this.z + c.z);
    }
    
    public Coord rotate(String axis) {
        Coord temp = null;
        switch (axis) {
            case "x": temp = new Coord(x, z, -y); break;
            case "y": temp = new Coord(-z, y, x); break;
            case "z": temp = new Coord(y, -x, z); break;
        }
        return temp;
    }
    
    public int dist(Coord c) {
        return (Math.abs(this.x-c.x)
              + Math.abs(this.y-c.y)
              + Math.abs(this.z-c.z));
    }
    
    public String toString() {
        return (this.x + ", " + this.y + ", " + this.z);
    }
    
    public Boolean equals(Coord c) {
        return (this.x == c.x && this.y == c.y && this.z == c.z);
    }
    
    public int manDist(Coord c) {
        return (Math.abs(this.x - c.x) + Math.abs(this.y - c.y) + Math.abs(this.z - c.z));
    }
}
