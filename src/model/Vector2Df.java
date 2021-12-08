package model;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @since 06-30-2021
 * Easy solution of handling 2D Vector.
 * Act like Processing PVector.
 */
public class Vector2Df {
    
    // X-Coordiante
    public float x;
    // Y-Coordiante
    public float y;

    /**
     * Constructor with starting values
     * @param x coordinate
     * @param y coordinate
     */
    public Vector2Df(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constrctor without parameter.
     * Initial with 0.0
     */
    public Vector2Df() {
        this.x = 0.0f;
        this.y = 0.0f;
    }

    public void negate() {
        this.x = -this.x;
        this.y = -this.y;
    }

    /**
     * Set the current Vector Object to the given parameters
     * @param x coordinate of new Vector
     * @param y coordinate of new Vector
     */
    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Set the current Vector Object to the given parameter Vector
     * @param vector another Vector
     */
    public void set(Vector2Df vector) {
        this.x = vector.x;
        this.y = vector.y;
    }

    /**
     * Adds the parameter values
     * @param x coordinates which need to be added to x
     * @param y coordinates which need to be added to y
     */
    public void add(float x, float y) {
        this.x += x;
        this.y += y;
    }

    /**
     * Adds the parameter Vector to the current Vector Object
     * @param vector another Vector
     */
    public void add(Vector2Df vector) {
        this.x += vector.x;
        this.y += vector.y;
    }

    /**
     * Static function that allow you to add the second Vector(v) to the first one (target)
     * @param target the base Vector
     * @param v another Vector that add
     * @return a new Vector
     */
    public static Vector2Df add(Vector2Df target, Vector2Df v) {
        return new Vector2Df(target.x + v.x, target.y + v.y);
    }

    /**
     * Subtract the parameter values
     * @param x coordinates which need to be subtract to x
     * @param y coordinates which need to be subtract to y
     */
    public void sub(float x, float y) {
        this.x -= x;
        this.y -= y;
    }

    /**
     * Subtract the parameter Vector from the current Vector Object
     * @param vector another Vector
     */
    public void sub(Vector2Df vector) {
        this.x -= vector.x;
        this.y -= vector.y;
    }

    /**
     * Static function that allow you to subtract the second Vector(v) from the first one (target)
     * @param target the base Vector
     * @param v another Vector that subtract
     * @return a new Vector
     */
    public static Vector2Df sub(Vector2Df target, Vector2Df v) {
        return new Vector2Df(target.x - v.x, target.y - v.y);
    }

    /**
     * Divides the Vector Object through n
     * @param n the value which divide the Vector
     */
    public void div(float n) {
        this.x /= n;
        this.y /= n;
    }

    /**
     * Divide the Vector Object through the parameter Vector.
     * This will divide the X and Y values
     * @param vector another Vector
     */
    public void div(Vector2Df vector) {
        this.x /= vector.x;
        this.y /= vector.y;
    }

    /**
     * Static function that allow you to divide the first Vector(target) through the second one (v)
     * @param target the base Vector
     * @param v another Vector that divide
     * @return new Vector
     */
    public static Vector2Df div(Vector2Df target, Vector2Df v) {
        return new Vector2Df(target.x / v.x, target.y / v.y);
    }

    /**
     * Multiply the Vector Object with n
     * @param n the values which multiply the Vector
     */
    public void mult(float n) {
        this.x *= n;
        this.y *= n;
    }

    /**
     * Multiply the Vector Object with the parameter Vector.
     * This will multiply the X and Y values
     * @param vector another Vector
     */
    public void mult(Vector2Df vector) {
        this.x *= vector.x;
        this.y *= vector.y;
    }

    /**
     * Static function that allow you to multiply the first Vector(target) with the second one (v)
     * @param target the base Vector
     * @param v another Vector that multiply
     * @return new Vector
     */
    public static Vector2Df mult(Vector2Df target, Vector2Df v) {
        return new Vector2Df(target.x * v.x, target.y * v.y);
    }

    /**
     * Calculate the Magnitude of the Vector Object
     * @return the Magnitude as float
     */
    public float mag() {
        return (float) Math.sqrt(x*x + y*y);
    }

    /**
     * Set the Magnitude of the Vector Object
     * @param len define the length of the Magnitude
     */
    public void setMag(float len) {
        Vector2Df vector = normalize(this);
        this.x = vector.x;
        this.y = vector.y;
        mult(len);
    }

    /**
     * Set the Magnitude for the parameter Vector(target)
     * @param target the base Vector
     * @param len define the length of the Magnitude
     * @return new Vector with given Magnitude(len)
     */
    public Vector2Df setMag(Vector2Df target, float len) {
        target = normalize(target);
        target.mult(len);
        return target;
    }

    /**
     * Normalize(0.0-1.0) the Vector Object
     */
    public void normalize() {
        float m = mag();
        if (m != 0 && m != 1) {
            div(m);
        }
    }

    /**
     * Normalize(0.0-1.0) the parameter Vector(target)
     * @param target the base Vector
     * @return new Vector which got normalized
     */
    public Vector2Df normalize(Vector2Df target) {
        if (target == null) {
            target = new Vector2Df();
        }
        float m = mag();
        if (m > 0) {
            target.set(x/m, y/m);
        } else {
            target.set(x, y);
        }
        return target;
    }

    /**
     * Limit the Magnitude of the Vector Object
     * @param max the maximum length for the Vector Object
     */
    public void limit(float max) {
        if (mag() > max*max) {
            normalize();
            mult(max);
        }
    }

    /**
     * Calculate the distance between the Vector Object and the parameter Vector
     * @param v another Vector
     * @return distance as float
     */
    public float dist(Vector2Df v) {
        float dx = x - v.x;
        float dy = y - v.y;
        return (float) Math.sqrt(dx*dx + dy*dy);
    }

    /**
     * Calculate the distance between the Vector Object and the parameter values
     * @param x coordinate
     * @param y coordiante
     * @return distance as float
     */
    public float dist(float x, float y) {
        float dx = x - x;
        float dy = y - y;
        return (float) Math.sqrt(dx*dx + dy*dy);
    }

    /**
     * Static function that allow you to calculate the distance between the two parameter Vectors
     * @param target an Vector
     * @param v another Vector
     * @return distance as float
     */
    public static float dist(Vector2Df target, Vector2Df v) {
        float dx = target.x - v.x;
        float dy = target.y - v.y;
        return (float) Math.sqrt(dx*dx + dy*dy);
    }

    /**
     * Calculate the angle between the Vector Object and the parameter Vector
     * @param v another Vector
     * @return angle as float
     */
    public float angleBetween(Vector2Df v) {
        float dot = x * v.x + y * v.y;
        float v1mag = (float) Math.sqrt(x * x + y * y);
        float v2mag = (float) Math.sqrt(v.x * v.x + v.y * v.y);
        return (float) Math.acos(dot / (v1mag * v2mag));
    }

    /**
     * Static function that allow you to calculate the angle between the two parameter Vectors
     * @param v1 an Vector
     * @param v2 another Vector
     * @return angle as float
     */
    public static float angleBetween(Vector2Df v1, Vector2Df v2) {
        float dot = v1.x * v2.x + v1.y * v2.y;
        float v1mag = (float) Math.sqrt(v1.x * v1.x + v1.y * v1.y);
        float v2mag = (float) Math.sqrt(v2.x * v2.x + v2.y * v2.y);
        return (float) Math.acos(dot / (v1mag * v2mag));
    }

    /**
     * Static function that allow you to generate an Vector with an angle
     * @param angle from which the Vector will be generated
     * @return new Vector with angle
     */
    public static Vector2Df vectorFromAngle(float angle) {
        return new Vector2Df((float) Math.cos(angle), (float) Math.sin(angle));
    }

    /**
     * Static function that allow you to generate an random 2D Vector
     * @return new Vector
     */
    public static Vector2Df random2D () {
        return vectorFromAngle(ThreadLocalRandom.current().nextInt(361));
    }

    /**
     * Static function that allow you to generate an random 2D Vector within an defined angle.
     * @param origin starting angle.
     * @param bound ending angle.
     * @return new Vector within the giving angle
     */
    public static Vector2Df random2D (int origin, int bound) {
        return vectorFromAngle(ThreadLocalRandom.current().nextInt(origin, bound));
    }

    /**
     * Generate an immutable view of the Vector Object
     * @return new ImmutableView
     */
    public ImmutableVector2Df ImmutableView() {
        return new ImmutableVector2Df(x,y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector2Df vector2Df = (Vector2Df) o;

        if (Float.compare(vector2Df.x, x) != 0) return false;
        return Float.compare(vector2Df.y, y) == 0;
    }

    @Override
    public int hashCode() {
        int result = (x != +0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Vector2Df{" +
                "type=float"+
                ", x=" + x +
                ", y=" + y +
                ", mag=" + mag() +
                '}';
    }

    private static class ImmutableVector2Df{

        private final float x;
        private final float y;

        public ImmutableVector2Df(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }

        @Override
        public String toString() {
            return "ImmutableVector{" +
                    "type=float"+
                    ", x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
