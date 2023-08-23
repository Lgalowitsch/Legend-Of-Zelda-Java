//Logan Galowitsch, 3/31/23, We used polymorhism with our sprites and added a boomerang and pots that we can throw, break, and interact with.
import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class Tile extends Sprite {
 static BufferedImage image;
 

	Tile(int x, int y)
	{
        this.x = x;
        this.y = y;
		this.width = 50;
 		this.height = 50;

		 if(image == null)
		 {
		 image = View.loadImage("tile.jpg");
		  }
	}


    
	Json marshal ()
	{
		Json ob = Json.newObject();
		ob.add("tileX", x);
		ob.add("tileY",y);
		return ob;
	}

	public Tile(Json ob)
	{
		x = (int) ob.getLong("tileX");
		y = (int) ob.getLong("tileY");
		this.width = 50;
 		this.height = 50;
		if(image == null)
    	{
        image = View.loadImage("tile.jpg");
 		}

	}
	
	void draw(Graphics g, int roomPosX, int roomPosY)
	{
		g.drawImage(image, x - roomPosX, y - roomPosY, width, height, null);
	}

    public boolean ClickedExistingTile(int clickedX, int clickedY)
	{
		if(clickedX >= x && clickedX <= x + width && clickedY >= y && clickedY <= y + height)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}

	

	@Override
	public String toString()
	{
		return "Tile (x,y) = (" + x + ", " + y + "), w = " + width + ", h = " + height;
	}
	
	@Override
	public boolean isTile(){
		return true;
	}

	public boolean update(){
		return true;
	}
	

	

}
