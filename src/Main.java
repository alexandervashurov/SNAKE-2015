import javax.swing.JFrame;

class Main {

	public static void main(String[] args) {

       	MainWindow f1= new MainWindow();
        f1.setLocationRelativeTo(null);
		f1.setSize(150 ,350);
		f1.setVisible(true);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
