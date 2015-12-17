import java.util.ArrayList;
import java.util.List;
import java.util.Random;


//змейка
class ThreadsController extends Thread {
    public static Way directionSnake;
    private final Window gameWindow;
    private final Tuple headSnakePos;
    private final long speed;
    private final boolean gameMode;
    private boolean walls;
    private final Random randomizer = new Random();
    private final ArrayList<Tuple> positions = new ArrayList<>();
    public Tuple bonusFoodPosition;
    private List<List<DataOfSquare>> squares = new ArrayList<>();
    private int sizeSnake = 3;
    public int score = 0;
    private boolean play = true;
    private int bonusChance = 1;
    private Tuple foodPosition;


    ThreadsController(Window win, Tuple positionDepart, int speed, boolean gameMode) {
        gameWindow = win;
        squares = Window.Grid;
        this.speed = speed;
        this.gameMode = gameMode;
        headSnakePos = new Tuple(positionDepart.getX(), positionDepart.getY());
        directionSnake = Way.VK_LEFT;


        Tuple headPos = new Tuple(headSnakePos.getX(), headSnakePos.getY());
        positions.add(headPos);

        foodPosition = new Tuple(Window.height - 4, Window.width - 3);
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



    private void checkCollision() {

        Tuple posCritique = headSnakePos;

        if (gameMode) {
            if (headSnakePos.getX() == 0
                    || headSnakePos.getX() == squares.size() - 1
                    || headSnakePos.getY() == 0
                    || headSnakePos.getY() == squares.get(0).size()
                    ) {
                stopTheGame();
            }


        }


        for (int i = 0; i <= positions.size() - 2; i++) {
            boolean biteItself = posCritique.getX() == positions.get(i).getX() && posCritique.getY() == positions.get(i).getY();
            if (biteItself) {
                stopTheGame();


            }
        }
        bonusFoodPosition = new Tuple(Window.height - 1, Window.width - 1);
        boolean eatingFood = posCritique.getX() == foodPosition.getY() && posCritique.getY() == foodPosition.getX();
        boolean eatingBonus = posCritique.getX() == bonusFoodPosition.getY() && posCritique.getY() == bonusFoodPosition.getX();

        if (eatingFood) {
            System.out.println("ПОЕЛ");
            score += 1000 / speed;
            sizeSnake = sizeSnake + 1;
            bonusChance = bonusChance + 1;

            foodPosition = getValAreaNotInSnake();
            spawnFood(foodPosition);

            if (bonusChance % 4 == 0) {

                bonusFoodPosition = getValAreaNotInSnake();
                spawnBonusFood(bonusFoodPosition);

            }

        }
        if (eatingBonus) {
            System.out.print("БОНУС ");
            score += 1000 / speed;
            bonusChance = 1;
        }
    }


    private void stopTheGame() {
        System.out.println("ОБЪЕЛСЯ \n");
        play = false;
    }


    private void spawnFood(Tuple foodPositionIn) {
        squares.get(foodPositionIn.getX()).get(foodPositionIn.getY()).lightMeUp(Colors.EAT);
    }

    private void spawnBonusFood(Tuple bonusFoodPositionIn) {
        squares.get(bonusFoodPositionIn.getX()).get(bonusFoodPositionIn.getY()).lightMeUp(Colors.BONUS);
    }


    private Tuple getValAreaNotInSnake() {

        int i = randomizer.nextInt(squares.size());
        int j = randomizer.nextInt(squares.get(i).size());
        if (gameMode) {
            i = (i == 0) ? i + 1 : i;
            i = (i == squares.size() - 1) ? i - 1 : i;
            j = (j == 0) ? j + 1 : j;
            j = (j == squares.get(0).size() - 1) ? j - 1 : j;
        }
        while (!squares.get(i).get(j).getState().equals(Colors.FREE_SPACE)) {
            i = randomizer.nextInt(squares.size());
            j = randomizer.nextInt(squares.get(i).size());
            if (gameMode) {
                i = (i == 0) ? i + 1 : i;
                i = (i == squares.size() - 1) ? i - 1 : i;
                j = (j == 0) ? j + 1 : j;
                j = (j == squares.get(0).size() - 1) ? j - 1 : j;
            }
        }

        return new Tuple(i, j);
    }

    //изменяет голову змеи и обновляет массив
    private void move(Way dir) {
        switch (dir) {
            case VK_UP:
                headSnakePos.ChangeData(headSnakePos.getX(), (headSnakePos.getY() + 1) % 20);
                positions.add(new Tuple(headSnakePos.getX(), headSnakePos.getY()));
                break;
            case VK_DOWN:
                if (headSnakePos.getY() - 1 < 0) {
                    headSnakePos.ChangeData(headSnakePos.getX(), 19);
                } else {
                    headSnakePos.ChangeData(headSnakePos.getX(), Math.abs(headSnakePos.getY() - 1) % 20);
                }
                positions.add(new Tuple(headSnakePos.getX(), headSnakePos.getY()));
                break;
            case VK_RIGHT:
                if (headSnakePos.getX() - 1 < 0) {
                    headSnakePos.ChangeData(19, headSnakePos.getY());
                } else {
                    headSnakePos.ChangeData(Math.abs(headSnakePos.getX() - 1) % 20, headSnakePos.getY());
                }
                positions.add(new Tuple(headSnakePos.getX(), headSnakePos.getY()));

                break;

            case VK_LEFT:
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
            squares.get(x).get(y).lightMeUp(Colors.SNAKE);

        }
    }

    //изменение позиции хвоста и цвета клетки
    private void deleteTail() {
        int size = sizeSnake;
        for (int i = positions.size() - 1; i >= 0; i--) {
            if (size == 0) {

                Tuple t = positions.get(i);
                squares.get(t.getY()).get(t.getX()).lightMeUp(Colors.FREE_SPACE);
                positions.remove(i);
            } else {
                size--;
            }
        }
    }
}
