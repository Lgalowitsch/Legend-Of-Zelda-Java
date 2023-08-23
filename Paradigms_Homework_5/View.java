//Logan Galowitsch, 3/31/23, We used polymorhism with our sprites and added a boomerang and pots that we can throw, break, and interact with.
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;
import java.io.IOException;

class View extends JPanel
{
	
	BufferedImage tile_image;
	Model model;
	int roomPosX;
	int roomPosY;
	

	View(Controller c, Model m)
	{
		model = m;
		c.setView(this);
		
	}

	

	public void paintComponent(Graphics g)
	{
		g.setColor(new Color(157, 98, 240));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		//model.link.createImage(g, roomPosX, roomPosY);
		for(int i = 0; i < model.sprites.size(); i++)
		{
			Sprite t = model.sprites.get(i);
			t.draw(g, roomPosX, roomPosY);
			//g.drawImage(tile_image, t.x - roomPosX, t.y - roomPosY, null);
		}
		
	}

	public static BufferedImage loadImage(String filename)
	{
		BufferedImage image = null;
		try{
			image = ImageIO.read(new File(filename));
		} catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
		return image;
	}

	void update()
	{	   }
}
