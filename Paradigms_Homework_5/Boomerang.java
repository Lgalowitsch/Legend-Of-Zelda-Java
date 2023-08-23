//Logan Galowitsch, 3/31/23, We used polymorhism with our sprites and added a boomerang and pots that we can throw, break, and interact with.
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Boomerang extends Sprite {
    static BufferedImage boomerang;
    static BufferedImage[] image;
    final int NUM_IMAGES = 4;
    final int MAX_IMAGES = 1;
    int speed;
    int dir = -1;
    boolean isActive = true;

    public Boomerang(int x, int y, int direction){
        this.x = x;
        this.y = y;
        this.width = 10;
        this.height = 15;
        this.dir = direction;
        this.speed = 30;
        if(image == null)
        {
            boomerang = View.loadImage("boomerang_images/boomerang1.png");
        }
        // if(image != null) return;
        image = new BufferedImage[NUM_IMAGES];
        for(int i = 0; i < NUM_IMAGES; i++)
        {
            image[i] = View.loadImage("boomerang_images/boomerang" + (i+1) + ".png");
        }
    }


    public Json marshal(){
        Json obj = Json.newObject();
        return obj;
    }

   
    void draw(Graphics g, int roomPosX, int roomPosY)
    {
        g.drawImage(this.image[dir], x - roomPosX, y - roomPosY, width, height, null);

    }

    boolean isActive()
    {
        return isActive = false;
    }

    @Override
	public String toString()
	{
		return "Boomberang (x,y) = (" + x + ", " + y + "), w = " + width + ", h = " + height;
	}

    @Override
    public boolean isBoomberg(){
        return true;
    }

    boolean update(){
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
        
}
