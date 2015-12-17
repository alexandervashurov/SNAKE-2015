import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;


class Window extends JFrame {
    public static List<List<DataOfSquare>> Grid;
    public static final int width = 20;
    public static final int height = 20;
    private final ThreadsController gameThread;


    public Window(int speed, boolean gameMode) {
        // массив клеток
        Grid = new ArrayList<>();
        List<DataOfSquare> data;

        // создание клеток

        for (int i = 0; i < width; i++) {
            data = new ArrayList<>();
            for (int j = 0; j < height; j++) {
                DataOfSquare c = new DataOfSquare(Colors.FREE_SPACE);
                data.add(c);
            }
            Grid.add(data);
        }

        if (gameMode) {
            for (int i=0 ; i<width; i++){
                Grid.get(0).set(i,new DataOfSquare(Colors.WALL));
                Grid.get(height-1).set(i, new DataOfSquare(Colors.WALL));
            }
            for (int i = 0; i < height; i++) {
                Grid.get(i).set(0, new DataOfSquare(Colors.WALL));
                Grid.get(i).set(width-1, new DataOfSquare(Colors.WALL));
            }
        }

        // размер поля
        getContentPane().setLayout(new GridLayout(20, 20, 0, 0));

        // обход массив
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                getContentPane().add(Grid.get(i).get(j).square);
            }
        }

        // инициализация позиции змеи
        Tuple position = new Tuple(10, 10);
        gameThread = new ThreadsController(this, position, speed,gameMode);
        //старт
        gameThread.start();

        // слежка за нажатиями
        this.addKeyListener(new KeyboardListener());


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                gameThread.interrupt();
            }
        });




    }

}


