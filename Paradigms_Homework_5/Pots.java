//Logan Galowitsch, 3/31/23, We used polymorhism with our sprites and added a boomerang and pots that we can throw, break, and interact with.
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Pots extends Sprite{
    
    static BufferedImage goodPot;
    static BufferedImage badPot;
    int dir = -1;
    int speed = 10;
    boolean isBroken = false;
    int timer = 20;
    

    Pots(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.width = 50;
        this.height = 50;
        this.speed = 10;
        if(goodPot == null)
        {
            goodPot = View.loadImage("pot.png");
        }
        if(badPot == null)
        {
            badPot =  View.loadImage("pot_broken.png");
        }
    }

    void draw(Graphics g, int roomPosX, int roomPosY)
	{
        if(isBroken == false)
        {
		g.drawImage(goodPot, x - roomPosX, y - roomPosY, width, height, null);
        }
        else
        {
            g.drawImage(badPot, x - roomPosX, y - roomPosY, width, height, null);
        }
	}




    public Json marshal(){
        Json obj = Json.newObject();
        obj.add("PotX", x);
		obj.add("PotY",y);
        return obj;
    }

    public Pots(Json obj)
    {
        x = (int) obj.getLong("PotX");
		y = (int) obj.getLong("PotY");
		this.width = 50;
 		this.height = 50;
         if(goodPot == null)
         {
             goodPot = View.loadImage("pot.png");
         }
         if(badPot == null)
         {
             badPot =  View.loadImage("pot_broken.png");
         }
    }
    

    boolean update()
    {
        if(isBroken == true)
        {
            timer -= 1;
        }
        if(timer == 0)
        {
            return false;
        }
        if(dir == 0) //down
        {
            y += speed;
        }
        if(dir == 1) //left
        {
            x -= speed;
        }
        if(dir == 2) //right
        {
            x += speed;
        }
        if(dir == 3) //up
        {
            y -= speed;
        }
        return true;
    }

    @Override
	public String toString()
	{
		return "Pots (x,y) = (" + x + ", " + y + "), w = " + width + ", h = " + height;
	}

    @Override
    public boolean isPots(){
        return true;
    }    

}
