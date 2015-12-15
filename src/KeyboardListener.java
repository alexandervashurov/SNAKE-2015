import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

 class KeyboardListener extends KeyAdapter{
 	
 		public void keyPressed(KeyEvent e){
 		    switch(e.getKeyCode()){
		    	case 39:	// вправо, если не противоположная сторона

		    				if(ThreadsController.directionSnake!=Way.LEFT)
		    					ThreadsController.directionSnake=Way.RIGHT;
		    			  	break;
		    	case 38:	// наверх
							if(ThreadsController.directionSnake!=Way.UP)
								ThreadsController.directionSnake=Way.DOWN;
		    				break;
		    				
		    	case 37: 	// влево
							if(ThreadsController.directionSnake!=Way.RIGHT)
								ThreadsController.directionSnake=Way.LEFT;
		    				break;
		    				
		    	case 40:	// вниз
							if(ThreadsController.directionSnake!=Way.DOWN)
								ThreadsController.directionSnake=Way.UP;
		    				break;
		    	
		    	default: 	break;
 		    }
 		}
 	
 }
