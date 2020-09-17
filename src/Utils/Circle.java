package Utils;

public class Circle {

    private float radius;
    private float x, y;

    public Circle(float radius, float x, float y) {
        this.radius = radius;
        this.x = x;
        this.y = y;
    }

    public boolean intersects (Circle circle){
        return this.radius + circle.radius < Math.sqrt(Math.pow(this.x - circle.x, 2) + Math.pow(this.y - circle.y, 2));
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
