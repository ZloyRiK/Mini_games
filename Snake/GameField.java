

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JPanel;

public class GameField extends JPanel implements ActionListener {
    private final int size = 320;
    private final int dotSize = 16;
    private final int maxDots = 400;
    private Image dot;
    private Image apple;
    private int appleX;
    private int appleY;
    private int[] x = new int[maxDots];
    private int[] y = new int[maxDots];
    private int startSize;
    private Timer timer;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;

    public GameField() {
        setBackground(Color.black);
        loadImages();
        initGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);
    }

    public void initGame() {
        startSize = 3;
        for (int i = 0; i < startSize; i++) {
            x[i] = 48 - i * dotSize;
            y[i] = 48;
        }
        timer = new Timer(250, this);
        timer.start();
        createApple();
    }

    public void createApple() {
        appleX = new Random().nextInt(20) * dotSize;
        appleY = new Random().nextInt(20) * dotSize;
    }

    public void loadImages() {
        ImageIcon ia = new ImageIcon("Snake/apple.png");
        apple = ia.getImage();
        ImageIcon id = new ImageIcon("Snake/dot.png");
        dot = id.getImage();
    }

    public void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            startSize++;
            createApple();
        }
    }

    public void checkCollision() {
        for (int i = startSize; i > 0; i--) {
            if (i > 4 && x[0] == x[i] && y[0] == y[i]) {
                inGame = false;
            }
        }
        if (x[0] > size) {
            inGame = false;
        }
        if (y[0] > size) {
            inGame = false;
        }
        if (x[0] < 0) {
            inGame = false;
        }
        if (y[0] < 0) {
            inGame = false;
        }
    }

    public void move() {
        for (int i = startSize; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        if (left) {
            x[0] -= dotSize;
        }
        if (right) {
            x[0] += dotSize;
        }
        if (up) {
            y[0] -= dotSize;
        }
        if (down) {
            y[0] += dotSize;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame) {
            g.drawImage(apple, appleX, appleY, this);
            for (int i = 0; i < startSize; i++) {
                g.drawImage(dot, x[i], y[i], this);
            }
        }else {
            String strr = "Game over";
            g.setColor(Color.white);
            g.drawString(strr, 138, size/2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkApple();
            checkCollision();
            move();
        }
        repaint();

        // throw new UnsupportedOperationException("Unimplemented method
        // 'actionPerformed'");
    }
    class FieldKeyListener extends KeyAdapter{
    
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT && !right) {
                left = true;
                up = false;
                down = false;
            }
            if (key == KeyEvent.VK_RIGHT && !left) {
                right = true;
                up = false;
                down = false;
            }
            if (key == KeyEvent.VK_UP && !down) {
                up = true;
                left = false;
                right = false;
            }
            if (key == KeyEvent.VK_DOWN && !up) {
                down = true;
                left = false;
                right = false;
            }
        }
    }

}
