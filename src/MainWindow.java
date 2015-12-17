import javax.swing.*;
import java.awt.*;

/**
 * Created by AlexVashurov on 10.12.15.
 */
class MainWindow extends JFrame {
    private boolean gameMode = false;
    private Window gameWindow;
    private int speed = 100;


    public MainWindow() {
        super("Super Snake");
        JButton newGame = new JButton("New Game!");
        JButton exit = new JButton("Exit!");
        JButton difficulty = new JButton("Difficulty");
        JButton gameMod = new JButton("Game Mode");

        newGame.addActionListener(e -> {
            gameWindow = new Window(speed, gameMode);
            gameWindow.setLocation(400, 200);
            gameWindow.setSize(450, 500);
            gameWindow.setVisible(true);
            gameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        });

        exit.addActionListener(e -> this.dispose());

        setLayout(new GridLayout(4, 4));
        add(newGame);
        add(gameMod);
        add(difficulty);
        add(exit);
        gameMod.addActionListener(e1 -> {
            ChangeGameWindow dialog = new ChangeGameWindow(this);
            dialog.setVisible(true);
        });
        difficulty.addActionListener(e -> {

            DifficultyWindow dialog = new DifficultyWindow(this);
            dialog.setVisible(true);
        });


    }

    private void changeDifficulty(int speed) {
        this.speed = speed;
    }

    private class DifficultyWindow extends JFrame {
        private final JButton easy;
        private final JButton medium;
        private final JButton hard;

        public DifficultyWindow(MainWindow app) {
            super("Difficult");
            easy = new JButton("Easy");
            medium = new JButton("Medium");
            hard = new JButton("Hard");
            setSize(350, 150);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new GridLayout(1, 3));
            add(easy);
            add(medium);
            add(hard);
            easy.addActionListener(e -> {
                app.changeDifficulty(150);
                dispose();
            });
            medium.addActionListener(e -> {
                app.changeDifficulty(90);
                dispose();
            });
            hard.addActionListener(e -> {
                app.changeDifficulty(50);
                dispose();
            });
        }

    }

    private void changeGame(boolean gameMode) {
        this.gameMode=gameMode;
    }

    private class ChangeGameWindow extends JFrame {
        private final JButton withoutWalls;
        private final JButton withWalls;

        public ChangeGameWindow(MainWindow app) {
            super("Chose");
            withoutWalls = new JButton("No Walls");
            withWalls = new JButton("With Walls");
            setSize(350, 150);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new GridLayout(1, 2));
            add(withoutWalls);
            add(withWalls);
            withoutWalls.addActionListener(e -> {
                app.changeGame(false);
                dispose();
            });
            withWalls.addActionListener(e -> {
                app.changeGame(true);
                dispose();
            });
        }
    }
}





