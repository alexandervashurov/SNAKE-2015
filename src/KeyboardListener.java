import org.omg.PortableServer.THREAD_POLICY_ID;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

 class KeyboardListener extends KeyAdapter{
 	
	 public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_LEFT)
			ThreadsController.directionSnake=Way.VK_RIGHT;
		else if (keyCode == KeyEvent.VK_UP)
			ThreadsController.directionSnake=Way.VK_DOWN;
		else if (keyCode == KeyEvent.VK_RIGHT)
			ThreadsController.directionSnake=Way.VK_LEFT;
		else if (keyCode == KeyEvent.VK_DOWN)
			ThreadsController.directionSnake=Way.VK_UP;

	}}
