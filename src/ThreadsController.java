import java.util.ArrayList;
import java.util.List;


//змейка
class ThreadsController extends Thread {
    private List<List<DataOfSquare>> Squares = new ArrayList<>();
    private final Window gameWindow;
    private final Tuple headSnakePos;
    private int sizeSnake = 3;
    private final long speed;
    private int score = 0;
    public static Way directionSnake;
    private boolean play = true;

    private final ArrayList<Tuple> positions = new ArrayList<>();
    private Tuple foodPosition;

    //конструктор потока
    ThreadsController(Window win, Tuple positionDepart, int speed) {
        gameWindow = win;
        Squares = Window.Grid;
        this.speed = speed;
        headSnakePos = new Tuple(positionDepart.getX(), positionDepart.getY());
        directionSnake = Way.RIGHT;


        Tuple headPos = new Tuple(headSnakePos.getX(), headSnakePos.getY());
        positions.add(headPos);

        foodPosition = new Tuple(Window.height - 1, Window.width - 1);
        spawnFood(foodPosition);

    }

    public void run() {
        try {
            while (play) {
                move(directionSnake);
                checkCollision();
                deleteTail();
                refresh();
                sleep(speed);
            }
        } catch (InterruptedException e) {
            System.err.println("Thread interrupted");
            Thread.currentThread().interrupt();
        }
    }


    //проверка аварии
    private void checkCollision() {
        Tuple posCritique = headSnakePos;
        for (int i = 0; i <= positions.size() - 2; i++) {
            boolean biteItself = posCritique.getX() == positions.get(i).getX() && posCritique.getY() == positions.get(i).getY();
            if (biteItself) {
                stopTheGame();


            }
        }

        boolean eatingFood = posCritique.getX() == foodPosition.getY() && posCritique.getY() == foodPosition.getX();
        if (eatingFood) {
            System.out.println("ПОЕЛ");
            score += 1000 / speed;
            sizeSnake = sizeSnake + 1;
            foodPosition = getValAleaNotInSnake();

            spawnFood(foodPosition);
        }
    }

    //конец игры
    private void stopTheGame() {
        System.out.println("НАЖРАЛСЯ \n");
        play = false;
        gameWindow.showScore(score);


    }

    //Расположение писчи
    private void spawnFood(Tuple foodPositionIn) {
        Squares.get(foodPositionIn.getX()).get(foodPositionIn.getY()).lightMeUp(Colors.EAT);
    }

    //где змейки нет
    private Tuple getValAleaNotInSnake() {
        Tuple p;
        int ranX = (int) (Math.random() * 19);
        int ranY = (int) (Math.random() * 19);
        p = new Tuple(ranX, ranY);
        for (int i = 0; i <= positions.size() - 1; i++) {
            if (p.getY() == positions.get(i).getX() && p.getX() == positions.get(i).getY()) {
                ranX = (int) (Math.random() * 19);
                ranY = (int) (Math.random() * 19);
                p = new Tuple(ranX, ranY);
                i = 0;
            }
        }
        return p;
    }

    //изменяет голову змеи и обновляет массив
    private void move(Way dir) {
        switch (dir) {
            case UP:
                headSnakePos.ChangeData(headSnakePos.getX(), (headSnakePos.getY() + 1) % 20);
                positions.add(new Tuple(headSnakePos.getX(), headSnakePos.getY()));
                break;
            case DOWN:
                if (headSnakePos.getY() - 1 < 0) {
                    headSnakePos.ChangeData(headSnakePos.getX(), 19);
                } else {
                    headSnakePos.ChangeData(headSnakePos.getX(), Math.abs(headSnakePos.getY() - 1) % 20);
                }
                positions.add(new Tuple(headSnakePos.getX(), headSnakePos.getY()));
                break;
            case LEFT:
                if (headSnakePos.getX() - 1 < 0) {
                    headSnakePos.ChangeData(19, headSnakePos.getY());
                } else {
                    headSnakePos.ChangeData(Math.abs(headSnakePos.getX() - 1) % 20, headSnakePos.getY());
                }
                positions.add(new Tuple(headSnakePos.getX(), headSnakePos.getY()));

                break;
            case RIGHT:
                headSnakePos.ChangeData(Math.abs(headSnakePos.getX() + 1) % 20, headSnakePos.getY());
                positions.add(new Tuple(headSnakePos.getX(), headSnakePos.getY()));
                break;
        }
    }

    //изменение цвета и обновление клеточки
    private void refresh() {
        for (Tuple t : positions) {
            int y = t.getX();
            int x = t.getY();
            Squares.get(x).get(y).lightMeUp(Colors.SNAKE);

        }
    }

    //изменение позиции хвоста и цвета клетки
    private void deleteTail() {
        int size = sizeSnake;
        for (int i = positions.size() - 1; i >= 0; i--) {
            if (size == 0) {

                Tuple t = positions.get(i);
                Squares.get(t.getY()).get(t.getX()).lightMeUp(Colors.FREE_SPACE);
                positions.remove(i);
            } else {
                size--;
            }
        }
    }
}
