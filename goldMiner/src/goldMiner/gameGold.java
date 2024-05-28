package goldMiner;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class gameGold {
// cordinates
int x;
int y;
int width;
int height;
int type;
boolean flag;
//img
int mass;
Image img;
int count;
void paintSelf(Graphics g) {
	g.drawImage(img,x,y,null);
}
public int getWidth() {
	return width;
}
public Rectangle getRec () {
	return new Rectangle(x,y,width,height); //avoid object that are together
}
}
