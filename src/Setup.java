import model.Clock;
import model.Particle;
import util.Constant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static util.Constant.*;
/**
 * @since 27.05.2021
 * Application procedure with drawing function
 */
public class Setup extends JPanel implements ActionListener {

    private final List<Particle> particles;
    private final List<Particle> particlesBack;
    private final Clock clock;

    /**
     * Contructor for setup and basic configuration
     */
    public Setup() {

        renderSettings(
                true,
                true,
                true,
                true
        );

        this.particles = new ArrayList<>();
        this.particlesBack = new ArrayList<>();
        generate();

        this.clock = new Clock();

        this.setPreferredSize(new Dimension(Constant.WIDTH,Constant.HEIGHT));
        this.setBackground(new Color(20,20,20));
        this.setFocusable(true);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                    clock.close();
                } else if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    particles.clear();
                    particlesBack.clear();
                    generate();
                }
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                MOUSE_X = e.getX();
                MOUSE_Y = e.getY();
            }
        });

        Timer timer = new Timer(DELAY, this);
        timer.start();
    }

    /**
     * Function to get the graphic that allow you to draw objects
     * @param g Graphics of the super class
     */
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        draw(g);
        update();
    }

    /**
     * Actual draw function which will be used to draw the objects
     * @param g Graphics of the super class
     */
    public void draw (Graphics g) {
        ((Graphics2D) g).setRenderingHints(RENDERINGSETTINGS);
        g.setFont(new Font("Ebrima",Font.BOLD,150));
        int textWidth = g.getFontMetrics().stringWidth(this.clock.getTime());
        g.drawString(this.clock.getTime(), (Constant.WIDTH/2)-(textWidth/2), Constant.HEIGHT/2);
        for (Particle particle : this.particles) {
            particle.draw(g, this.particles);
        }
        for (Particle particle : this.particlesBack) {
            particle.draw(g, this.particlesBack);
        }
    }

    /**
     * Updates all Objects which are visible on the screen
     */
    public void update() {
        for (Particle particle : this.particles) {
            particle.update();
        }
        for (Particle particle : this.particlesBack) {
            particle.update();
        }
    }

    /**
     * Function that will update the Application window in DELAY time (16ms = ~60FPS)
     * @param e ignored ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    /**
     * Generates a new Set of Particle
     */
    private void generate() {
        int amount = ThreadLocalRandom.current().nextInt(MINPARTICLE,MAXPARTICLE);

        for (int i = 0 ; i < amount ; i++) {
            this.particles.add(new Particle(0.0f));
            this.particlesBack.add(new Particle(0.65f));
        }
    }
}
