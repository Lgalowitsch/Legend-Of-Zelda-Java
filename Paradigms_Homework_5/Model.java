//Logan Galowitsch, 3/31/23, We used polymorhism with our sprites and added a boomerang and pots that we can throw, break, and interact with.
import java.util.ArrayList; // import the ArrayList class
import java.util.Iterator;
class Model
{
	ArrayList<Sprite> sprites;
	Link link;

	Model()
	{
		sprites = new ArrayList<Sprite>();
		link = new Link(300,100);
		sprites.add(link);
	}
	Json marshal()
	     {
	         Json ob = Json.newObject();
	         Json tmpList = Json.newList();
			 Json tmpListPots = Json.newList();
	         ob.add("tiles", tmpList);
			 ob.add("pots", tmpListPots);
	         for(int i = 0; i < sprites.size(); i++)
			 {
				if(sprites.get(i).isTile() == true)
				{
	             tmpList.add(sprites.get(i).marshal());
				}
				else if(sprites.get(i).isPots() == true)
				{
					tmpListPots.add(sprites.get(i).marshal());
				}
			 }
	         return ob;
	     }

	public void unmarshal(Json ob)
    {
		sprites.clear();
		sprites.add(link);

        Json tmpList = ob.get("tiles");
		Json tmpListPots = ob.get("pots");
        for(int i = 0; i < tmpList.size(); i++)
		{
            sprites.add(new Tile(tmpList.get(i)));
		}
		for(int i = 0; i < tmpListPots.size(); i++)
		{
			sprites.add(new Pots(tmpListPots.get(i)));
		}

		//sprites.add(new Tile(100,100));
    }

	public void update()
	{
		Iterator<Sprite> it = sprites.iterator();
		while(it.hasNext())
		{
			Sprite sprite1 = it.next();
			if(sprite1.update() == false) {
				it.remove();
				continue;
			}

			for (Sprite sprite2 : sprites) {
				if(isColliding(sprite1, sprite2))
				{
					// ((Pots)sprite2).speed = 10;
					/*if(sprite1.isLink() && sprite2.isTile())
					{
						link.leaveTile((sprite2));
					}*/
					if (sprite1.isTile() && sprite2.isLink())
					{
						link.leaveTile((sprite1));
					}
					else if(sprite1.isBoomberg() && sprite2.isTile())
					{
						it.remove();
						
					}
					else if(sprite1.isPots() && sprite2.isLink())
					{
						((Pots)sprite1).dir = ((Link)sprite2).direction;
					}
					else if(sprite1.isPots() && sprite2.isTile())
					{
						((Pots)sprite1).isBroken = true;
						((Pots)sprite1).dir = -1;
					}
					else if(sprite1.isBoomberg() && sprite2.isPots())
					{
						((Pots)sprite2).isBroken = true;
					}

					break;
					
				}
				
			}
			
			
		}
		}
		
	

	public void addTile(int mouseX, int mouseY)
	{
		boolean existingTile = false;
		int x = mouseX - mouseX % 50;
		int y = mouseY - mouseY % 50;
		for(int i = 0; i < sprites.size(); i++)
		{
			if(sprites.get(i).isTile())
			{
				if(((Tile)sprites.get(i)).ClickedExistingTile(mouseX, mouseY))
				{
					existingTile = true;
					sprites.remove(i);
					break;
				}
			}
		}
	
		if(!existingTile)
		{
			Sprite t = new Tile(x, y);
			sprites.add(t);
		}
	}

	public boolean isColliding(Sprite x, Sprite y)
	{
		if(x.x + x.width < y.x)
			return false;
		if(x.x > y.x + y.width)
			return false;
		if(x.y + x.height < y.y) // assumes bigger is downward
			return false;
		if(x.y > y.y + y.height) // assumes bigger is downward
			return false;

		return true;
	}

	public void throwBoomerang()
	{
		Sprite boomerang = new Boomerang(link.x, link.y, link.direction);
		sprites.add(boomerang);
	}

	public void addPot(int mouseX, int mouseY)
	{
		Sprite s = new Pots(mouseX, mouseY);
		sprites.add(s);
	}
}