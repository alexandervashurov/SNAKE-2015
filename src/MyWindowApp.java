import javax.swing.*;
import java.awt.*;

/**
 * Created by AlexVashurov on 10.12.15.
 */
class MyWindowApp extends JFrame {
    private Window gameWindow;
    private int speed = 100;

    public MyWindowApp() {
        super("Super Snake");
        JButton newGame = new JButton("New Game!");
        JButton exit = new JButton("Exit!");
        JButton difficulty = new JButton("Difficulty");

        newGame.addActionListener(e -> {
            gameWindow = new Window(speed);
            gameWindow.setLocation(400, 200);
            gameWindow.setSize(450, 500);
            gameWindow.setVisible(true);
            gameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        });

        exit.addActionListener(e -> this.dispose());

        //JPanel ButtonsPanel = new JPanel(new GridLayout());
        setLayout(new GridLayout(3, 3));
        add(newGame);
        add(difficulty);
        add(exit);

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

        public DifficultyWindow(MyWindowApp app) {
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
}



