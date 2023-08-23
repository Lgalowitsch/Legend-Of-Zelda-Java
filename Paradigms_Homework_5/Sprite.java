//Logan Galowitsch, 3/31/23, We used polymorhism with our sprites and added a boomerang and pots that we can throw, break, and interact with.
import java.awt.Graphics;
//import java.awt.image.BufferedImage

abstract class Sprite {
    int x, y, width, height;
    //BufferedImage image;

abstract void draw(Graphics g, int x, int y);
abstract boolean update();
abstract Json marshal();

boolean isLink(){
    return false;
}

boolean isTile(){
    return false;
}

boolean isBoomberg(){
    return false;
}

boolean isPots(){
    return false;
}






}