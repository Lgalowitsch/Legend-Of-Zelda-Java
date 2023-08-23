//Logan Galowitsch, 3/31/23, We used polymorhism with our sprites and added a boomerang and pots that we can throw, break, and interact with.
import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame
{
	Model model;
	Controller controller;
	View view;
	

	public Game()
	{
		model = new Model();
    	controller = new Controller(model);
		view = new View(controller, model);
		this.setTitle("A5 - Using Link");
		this.setSize(700, 500);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		view.addMouseListener(controller);
        this.addKeyListener(controller);
		load();
		
	}

	void load(){
		Json j = Json.load("map.json");
		model.unmarshal(j);
		
	}
	public static void main(String[] args)
	{
		Game g = new Game();
		g.run();
	}

	public void run()
	{
		while(true)
		{
			controller.update();
			model.update();
			view.repaint(); // This will indirectly call View.paintComponent
			Toolkit.getDefaultToolkit().sync(); // Updates screen
            
			// Go to sleep for 50 milliseconds
			try
			{
				Thread.sleep(40);
			} catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
			//System.out.println("hi"); // remove this line
		}
	}

	

}
