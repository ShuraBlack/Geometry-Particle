package model;

import util.Constant;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.List;

public class Particle {

    private final Vector2Df position;
    private Vector2Df velocity;
    private final float reduction;

    public Particle(float reduction) {
        this.reduction = reduction;
        int x = ThreadLocalRandom.current().nextInt(0, Constant.WIDTH);
        int y = ThreadLocalRandom.current().nextInt(0, Constant.HEIGHT);
        this.position = new Vector2Df(x,y);

        generateVelocity();
        while (this.velocity.x == 0 || this.velocity.y == 0) {
            generateVelocity();
        }
    }

    public void update() {

        if (distance(this.position, new Vector2Df(Constant.MOUSE_X, Constant.MOUSE_Y)) < 100) {
            Vector2Df mouse = new Vector2Df(Constant.MOUSE_X, Constant.MOUSE_Y);

            mouse.sub(this.position);
            this.velocity = mouse.setMag(mouse,this.velocity.mag());
            this.velocity.negate();
        }

        if (distance(this.position, new Vector2Df(Constant.WIDTH/2, Constant.HEIGHT/2)) < 200) {
            Vector2Df center = new Vector2Df(Constant.WIDTH/2, Constant.HEIGHT/2);

            center.sub(this.position);
            this.velocity = center.setMag(center,this.velocity.mag());
            this.velocity.negate();
        }

        if (this.velocity.x > 0.7 || this.velocity.x < -0.7 || this.velocity.y > 0.7 || this.velocity.y < -0.7) {
            this.velocity.setMag(0.4f);
        }

        this.position.add(this.velocity);
        if (this.position.x > Constant.WIDTH) {
            this.position.x = 0;
        }
        if (this.position.x < 0) {
            this.position.x = Constant.WIDTH;
        }

        if (this.position.y > Constant.HEIGHT) {
            this.position.y = 0;
        }
        if (this.position.y < 0) {
            this.position.y = Constant.HEIGHT;
        }
    }

    public void draw(Graphics g, List<Particle> particles) {
        g.setColor(new Color(1f,1f,1f,1f-this.reduction));
        g.fillOval((int)position.x, (int)position.y,3,3);

        for (Particle particle : particles) {
            double distance = distance(this.position, particle.getPosition());
            if (distance < 250) {
                float opacity = (1f - (float)(distance * 4) / 1000) - this.reduction;
                if (opacity < 0f) {
                    opacity = 0f;
                }
                ((Graphics2D) g).setStroke(new BasicStroke(1f + opacity));
                g.setColor(new Color(1f,1f,1f,opacity));
                g.drawLine((int)this.position.x, (int)this.position.y, (int)particle.position.x, (int)particle.position.y);
            }
        }
    }

    public Vector2Df getPosition() {
        return position;
    }

    private void generateVelocity() {
        double velX = ThreadLocalRandom.current().nextDouble(-0.7,0.7);
        double velY = ThreadLocalRandom.current().nextDouble(-0.7,0.7);
        this.velocity = new Vector2Df((float)velX,(float)velY);
    }

    public double distance(Vector2Df a, Vector2Df b) {
        return Math.sqrt((b.y - a.y) * (b.y - a.y) + (b.x - a.x) * (b.x - a.x));
    }
}
