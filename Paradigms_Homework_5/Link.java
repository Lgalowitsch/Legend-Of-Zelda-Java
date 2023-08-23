//Logan Galowitsch, 3/31/23, We used polymorhism with our sprites and added a boomerang and pots that we can throw, break, and interact with.
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;




class Link extends Sprite{
    static BufferedImage image;
    static BufferedImage[] images;
    final int NUM_IMAGES = 50;
    final int MAX_IMAGES = 13;
    int currentImage;
    int direction;
    double speed = 10;
    int prevX;
    int prevY;
    // int dir;
    

public Link(int x, int y)
{
    this.x = x;
    this.y = y;
    this.width = 45;
    this.height = 45;
    if(image == null)
    {
        image = View.loadImage("link_images/link1.png");
    }
    if(images != null) return;
    images = new BufferedImage[NUM_IMAGES];
    for(int i = 0; i < NUM_IMAGES; i++)
    {
        images[i] = View.loadImage("link_images/link" + (i+1) + ".png");
    }
    

    currentImage = 0;
}


public void updateImage(int dir)
{
    direction = dir;
    currentImage++;
    if(currentImage >= MAX_IMAGES)
    {
        currentImage = 0;    
    }
    if(currentImage + direction * MAX_IMAGES >= 50)
    {
        currentImage = 0;
    }

}

void draw(Graphics g, int roomPosX, int roomPosY)
{

    g.drawImage(this.images[currentImage + direction * MAX_IMAGES], x - roomPosX, y - roomPosY, width, height, null);
}

void setPrevCoordinates()
{
    prevX = x;
    prevY = y;
}

void leaveTile(Sprite t)
	{
		if((x + width >= t.x) && (prevX + width <= t.x) && prevY == y) //Left Collision
		{
			x = t.x - width - 1;
		}
		else if((x <= t.x + width) && (prevX >= t.x + t.width) && prevY == y) //Right Collision
		{
			x = t.x + t.width + 1;
		}
		else if((y + height >= t.y) && (prevY + height<= t.y)  && prevX == x)//From top collision
		{
			y = t.y - height - 1;
		}else{
            y = t.y + t.height + 1; 
        }


        /*
		else if((y<= t.x + t.height) && (prevY >= t.y + t.height)  && prevX == x) //bottom Collision
		{
			y = t.y + t.height + 1;
		}

        */
    }

public boolean update(){
    return true;

}

@Override
boolean isLink(){
    return true;
}

@Override
	public String toString()
	{
		return "Link (x,y) = (" + x + ", " + y + "), w = " + width + ", h = " + height;
	}


public Json marshal(){
    Json obj = Json.newObject();
    return obj;
}
}



