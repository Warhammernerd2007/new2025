import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {
    private static final int CELL_SIZE = 20;
    private int width = 20;
    private int height = 20;
    private int delay = 150;
    private LinkedList<Point> snake;
    private Point food;
    private char direction = 'R';
    private Timer timer;
    private Random rand;
    private boolean inGame = false;

    public SnakeGame() {
        setFocusable(true);
        addKeyListener(this);
        rand = new Random();
        showStartScreen();
    }

    private void showStartScreen() {
        String[] sizes = {"Small", "Large"};
        String[] speeds = {"Easy", "Medium", "Hard"};

        String sizeChoice = (String) JOptionPane.showInputDialog(null, "Choose Playfield Size:", "Game Options", JOptionPane.QUESTION_MESSAGE, null, sizes, sizes[0]);
        String speedChoice = (String) JOptionPane.showInputDialog(null, "Choose Difficulty:", "Game Options", JOptionPane.QUESTION_MESSAGE, null, speeds, speeds[0]);

        if (sizeChoice.equals("Small")) {
            width = 15;
            height = 15;
        } else {
            width = 25;
            height = 25;
        }

        switch (speedChoice) {
            case "Easy": delay = 200; break;
            case "Medium": delay = 150; break;
            case "Hard": delay = 100; break;
        }

        startGame();
    }

    private void startGame() {
        setPreferredSize(new Dimension(width * CELL_SIZE, height * CELL_SIZE));
        snake = new LinkedList<>();
        snake.add(new Point(width / 2, height / 2));
        placeFood();
        timer = new Timer(delay, this);
        timer.start();
        inGame = true;
    }

    private void placeFood() {
        int x, y;
        do {
            x = rand.nextInt(width);
            y = rand.nextInt(height);
        } while (snake.contains(new Point(x, y)));
        food = new Point(x, y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame) {
            g.setColor(Color.RED);
            g.fillRect(food.x * CELL_SIZE, food.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            g.setColor(Color.GREEN);
            for (Point p : snake) {
                g.fillRect(p.x * CELL_SIZE, p.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    private void move() {
        Point head = snake.getFirst();
        Point newHead = switch (direction) {
            case 'U' -> new Point(head.x, head.y - 1);
            case 'D' -> new Point(head.x, head.y + 1);
            case 'L' -> new Point(head.x - 1, head.y);
            case 'R' -> new Point(head.x + 1, head.y);
            default -> head;
        };

        if (newHead.x < 0 || newHead.x >= width || newHead.y < 0 || newHead.y >= height || snake.contains(newHead)) {
            gameOver();
            return;
        }

        if (newHead.equals(food)) {
            placeFood();
        } else {
            snake.removeLast();
        }
        snake.addFirst(newHead);
    }

    private void gameOver() {
        timer.stop();
        JOptionPane.showMessageDialog(this, "Game Over! Score: " + (snake.size() - 1));
        showStartScreen();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if ((key == KeyEvent.VK_UP || key == KeyEvent.VK_W) && direction != 'D') direction = 'U';
        else if ((key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) && direction != 'U') direction = 'D';
        else if ((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) && direction != 'R') direction = 'L';
        else if ((key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) && direction != 'L') direction = 'R';
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        SnakeGame game = new SnakeGame();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
