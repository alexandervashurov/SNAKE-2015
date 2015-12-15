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
        JButton difficult = new JButton("Difficult");

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
        add(difficult);
        add(exit);

        difficult.addActionListener(e -> {

            DifficultWindow dialog = new DifficultWindow(this);
            dialog.setVisible(true);
        });


    }

    private void changeDifficult(int speed) {
        this.speed = speed;
    }

    private class DifficultWindow extends JFrame {
        private final JButton easy;
        private final JButton medium;
        private final JButton hard;

        public DifficultWindow(MyWindowApp app) {
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
                app.changeDifficult(150);
                dispose();
            });
            medium.addActionListener(e -> {
                app.changeDifficult(90);
                dispose();
            });
            hard.addActionListener(e -> {
                app.changeDifficult(50);
                dispose();
            });
        }

    }
}



