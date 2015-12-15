import java.awt.*;

public enum Colors {
    EAT(Color.BLUE),
    SNAKE(Color.DARK_GRAY),
    FREE_SPACE(Color.WHITE);

    private final Color color;

    Colors(Color clr) {
        color = clr;
    }

    public Color getColor() {
        return color;
    }

}