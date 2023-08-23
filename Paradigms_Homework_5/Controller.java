//Logan Galowitsch, 3/31/23, We used polymorhism with our sprites and added a boomerang and pots that we can throw, break, and interact with.
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener
{
	View view;
	Model model;
    boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean editor = false;
	boolean potMode = false;

	Controller(Model m)
	{
    	this.model = m;
		Json load = Json.load("map.json"); 
		model.unmarshal(load); 
		System.out.println("Map Loaded");
	}

	public void actionPerformed(ActionEvent e)
	{
		
	}

	void setView(View v)
	{
		view = v;
	}

    public void mousePressed(MouseEvent e)
	{
		if(editor == true && potMode == false){
		model.addTile(e.getX() + view.roomPosX, e.getY() + view.roomPosY);
		}
		else if(editor == true && potMode == true)
		{
			model.addPot(e.getX() + view.roomPosX, e.getY() + view.roomPosY);
		}


	}

	public void mouseReleased(MouseEvent e) {    }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) {    }

    public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = true; break;
			case KeyEvent.VK_LEFT: keyLeft = true; break;
			case KeyEvent.VK_UP: keyUp = true; break;
			case KeyEvent.VK_DOWN: keyDown = true; break;
			case KeyEvent.VK_A: 
				if (editor == true)
				{
					view.roomPosX = 0; 
					 // scroll left
				}
				break;
			case KeyEvent.VK_W:
				if (editor == true)
				{ 
					view.roomPosY = 0; 
					// scroll up
				}
				break;

			case KeyEvent.VK_D:
				if (editor == true)
				{ 
					view.roomPosX = 700; 
					// scroll right
				}
				break;

			case KeyEvent.VK_X:
				if (editor == true)
				{
					view.roomPosY = 500;
					// scroll down
				}
				break;
			
		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: 
				keyRight = false; break;
			case KeyEvent.VK_LEFT: 
				keyLeft = false; break;
			case KeyEvent.VK_UP: 
				keyUp = false; break;
			case KeyEvent.VK_DOWN: 
				keyDown = false; break;
			case KeyEvent.VK_Q: 
				System.exit(0); break; //will quit your program's execution.
			case KeyEvent.VK_ESCAPE: 
				System.exit(0); break; //will quit your program's execution.
			case KeyEvent.VK_P:
				if(editor == true)
					{
						potMode = !potMode;
					}
					break;
			case KeyEvent.VK_E:
			{
				editor = !editor;
				if(editor == true){//switch to edit mode
					System.out.println("Edit Mode On"); 
				}else if (editor ==false){
					System.out.println("Edit Mode Off");
				}
				break;
			}
			case KeyEvent.VK_S:
				if (editor == true || potMode == true)
				{ 
					Json save = model.marshal(); 
					save.save("map.json"); 
					// save file
				}
				break;

			case KeyEvent.VK_L: 
				Json load = Json.load("map.json"); 
				model.unmarshal(load); 
				break;// load file
			
			case KeyEvent.VK_CONTROL:
				model.throwBoomerang();
				break;
		}
	}

	public void keyTyped(KeyEvent e)
	{
	}

	void update()
	{
		model.link.setPrevCoordinates();
		if(keyDown == true)
		{
			model.link.y += model.link.speed;
			model.link.updateImage(0);
		}
		else if(keyLeft == true)
		{
			model.link.x -= model.link.speed;
			model.link.updateImage(1);
		}
		else if(keyRight == true)
		{
			model.link.x += model.link.speed;
			model.link.updateImage(2);
		}
		else if(keyUp == true)
		{
			model.link.y -= model.link.speed;
			model.link.updateImage(3);
		}
		
	
		view.roomPosX = 700 * ((model.link.x + model.link.width / 2) / 700);
		view.roomPosY = 500 * ((model.link.y + model.link.height / 2) / 500);
	}

}