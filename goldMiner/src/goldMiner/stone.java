package goldMiner;

import java.awt.Toolkit;

public class stone extends gold{
	stone(){
		this.type=2;
		this.count=0;
		this.x=(int)(Math.random()*700);
		this.y=(int)(Math.random()*550+300);
		this.width=71;
		this.mass=50;
		this.height=71;
		this.flag=false;
		this.img=Toolkit.getDefaultToolkit().getImage("images/rock1.png");
	}

}
