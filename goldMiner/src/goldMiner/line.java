package goldMiner;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class line {
int x=380; // start
int y=180;
int endX=500;
int endY=500;
//line long
double length=100;
double min=100;
double max=750;
double angle=0;
// direction is to make sure that line is swing left and right
int dir=1;
//status of line
int state=0; // then swing 1 catch 2 return
Image hook = Toolkit.getDefaultToolkit().getImage("images/hook.png");
gameWin frame;
line(gameWin frame){
	this.frame=frame;
}


void logic() {
	
	for(gameGold gold : this.frame.goldL) {
		if(endX>gold.x && endX<gold.x +gold.width && endY > gold.y && endY < gold.y+gold.height){
			
			state=3;
			gold.flag=true;
		}
	}
	
}


void paintSelf(Graphics g)  {
	
	logic();
	switch(state) {
	case 0:
		if(angle<0.1) {dir=1;}
		else if(angle>0.9) {dir=-1;}
		angle+=0.005*dir;
		
		endX=(int)(x+length*Math.cos(angle * Math.PI));
		endY=(int)(y+length*Math.sin(angle * Math.PI));
		g.setColor(Color.red);
		g.drawLine(x-1,y,endX-1,endY);
		g.drawLine(x,y,endX,endY);
		g.drawImage(hook, endX-36, endY-2,null);
		
		break;
		
	case 1:
		if(length < max) {
		length +=5;
		
		endX=(int)( x+length*Math.cos(angle * Math.PI));
		endY=(int)(y+length*Math.sin(angle * Math.PI));
		g.setColor(Color.red);
		g.drawLine(x-1,y,endX-1,endY);
		g.drawLine(x,y,endX,endY);
		g.drawImage(hook, endX-36, endY-2,null);
		}
		
		else {state=2;}
		break;
	
	case 2:
		if(length>min) {
		length -= 5;
		
		endX=(int)( x+length*Math.cos(angle * Math.PI));
		endY=(int)(y+length*Math.sin(angle * Math.PI));
		g.setColor(Color.red);
		g.drawLine(x-1,y,endX-1,endY);
		g.drawLine(x,y,endX,endY);
		g.drawImage(hook, endX-36, endY-2,null);
		}
		
		else {state=0;}
		break;
	case 3:
		int m=1;
		if(length > min) {
			length -=5;
			
			endX=(int)( x+length*Math.cos(angle * Math.PI));
			endY=(int)(y+length*Math.sin(angle * Math.PI));
			g.setColor(Color.red);
			g.drawLine(x-1,y,endX-1,endY);
			g.drawLine(x,y,endX,endY);
			g.drawImage(hook, endX-36, endY-2,null);
			
			for(gameGold gold : this.frame.goldL) {
				if(gold.flag) {
					m=gold.mass;
					gold.x=endX-gold.getWidth()/2;
					gold.y =endY;
					
					if(length<=min) {
						gold.x=-150;
						gold.y=-150;
						gold.flag=false;
						gameWin.waterFlag=false;
						gameWin.count+=gold.count;
						state=0;
					}
					if(gameWin.waterFlag) {
						if(gold.type==1) {
							m=1;
						}
						if(gold.type==2) {
							gold.x=-150;
							gold.y=-150;
							gold.flag=false;
							gameWin.waterFlag=false;
							state =2;
						}
					}
				}
				
			}
			
			
			
		}
		
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
		
		
		default:
		
	}	
	
}
void regame() {
	angle=0;
	length=100;
}

}
