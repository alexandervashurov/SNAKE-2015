import javax.swing.*;
import java.awt.*;

class SquarePanel extends JPanel {


    public SquarePanel(Color d) {
        this.setBackground(d);
    }

    public void changeColor(Color d) {
        this.setBackground(d);
        this.repaint();
    }

}

