import org.omg.PortableServer.THREAD_POLICY_ID;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

 class KeyboardListener extends KeyAdapter{
 	
	 public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		 switch (keyCode) {
			 case KeyEvent.VK_LEFT:
				 ThreadsController.directionSnake = Way.VK_RIGHT;
				 break;
			 case KeyEvent.VK_UP:
				 ThreadsController.directionSnake = Way.VK_DOWN;
				 break;
			 case KeyEvent.VK_RIGHT:
				 ThreadsController.directionSnake = Way.VK_LEFT;
				 break;
			 case KeyEvent.VK_DOWN:
				 ThreadsController.directionSnake = Way.VK_UP;
				 break;
		 }

	}}
