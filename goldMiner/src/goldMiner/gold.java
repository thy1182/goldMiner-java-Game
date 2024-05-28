package goldMiner;

import java.awt.Toolkit;

public class gold extends gameGold {
	
	boolean flag=false;
	
	gold(){
		this.type=1;
		this.count=4;
		this.mass=30;
		this.x=(int)(Math.random()*700);
		this.y=(int)(Math.random()*550+300);
		this.width=52;
		this.height=52;
		this.flag=false;
		this.img=Toolkit.getDefaultToolkit().getImage("images/gold1.gif");
	}

}
class goldMini extends gold{
	goldMini(){
		this.count=2;
		this.width=36;
		this.height=36;
		this.mass=15;
		this.img=Toolkit.getDefaultToolkit().getImage("images/gold0.gif");
	}
}
class goldPlus extends gold{
	goldPlus(){
		this.count=8;
		this.x=(int)(Math.random()*700);
		this.width=105;
		this.height=105;
		this.mass=60;
		this.img=Toolkit.getDefaultToolkit().getImage("images/gold2.gif");
	}
}
