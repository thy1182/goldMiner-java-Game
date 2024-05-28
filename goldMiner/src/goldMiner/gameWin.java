package goldMiner;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;


public class gameWin extends JFrame {
	
	private static final long serialVersionUID = 1L;
	static int count=0;
	static int waterNum=3;
	static boolean waterFlag=false;
	static int level=1;
	int goal = level * 5;
	long startTime;
	long endTime;
	int price=(int)(Math.random()*10);
	boolean shop=false;
	Image offScreen;
	Image bg=Toolkit.getDefaultToolkit().getImage("images/bg.jpg");
	Image bg1=Toolkit.getDefaultToolkit().getImage("images/bg1.jpg");
	Image bg2=Toolkit.getDefaultToolkit().getImage("images/peo.png");
	Image item=Toolkit.getDefaultToolkit().getImage("images/water.png");
	
	//List<gold>goldList=new ArrayList<>();
	static int status;
	List<gameGold>goldL=new ArrayList<>();
	line line=new line(this);
	//boolean isPlace=true; //determine 
	
	gold gold=new gold();
	{
	boolean isPlace=true; //determine 
	for(int i=0; i<11; i++) {
		double rand=Math.random();
		//goldList.add(new gold());
		gold gol; //its a place to save the gold that just generated
		
		if(rand<0.3) {gol=new goldMini();}
		else if(rand < 0.7) {
			gol=new gold();
		}
				
		else {
			gol=new goldPlus();
		}
		
		// -----------------------------------------
		for(gameGold gold: goldL) {
			if(gol.getRec().intersects(gold.getRec())) {
			    // can not be placed together regenerate
			    isPlace=false;
			    
			   }
			  }
			 
			  if(isPlace) {
			   goldL.add(gol);
			   
			  }
			  else {
			  isPlace=true;
			  i--;
			  }
		}
	
	{
		for(int i=0; i<5; i++) {
			stone stone=new stone();

			for(gameGold gold: goldL) {
				if(stone.getRec().intersects(gold.getRec())) {
				    // can not be placed together regenerate
				    isPlace=false;
				    
				   }
				  }
				 
				  if(isPlace) {
				   goldL.add(stone);
				   
				  }
				  else {
				  isPlace=true;
				  i--;
				  }
			
		}
		
	}
	}
	
	
	
	
	
	
	void launch() {
		this.setVisible(true);// window information
		this.setSize(768,1000); // how big window is 
		this.setLocationRelativeTo(null);//make the window in middle of your screen
		this.setTitle("GoldMiner");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		addMouseListener(new MouseAdapter(){
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			switch(status) {
			case 0:
				if(e.getButton()==3) {
				status=1;
				startTime=System.currentTimeMillis();
			} 
				break;
			case 1:
				if(e.getButton()==1 && line.state==0) {
					line.state=1;
				}
				if(e.getButton()==3 && line.state==3 && waterNum>0){
					waterFlag=true;
					waterNum--;
				}
				break;
			case 2:
				if(e.getButton()==1) {
					shop=true;
				}
				if(e.getButton()==3) {
					status=1;
					startTime=System.currentTimeMillis();
				}
				break;
			case 3: 
			case 4: 
				if(e.getButton()==1) {
					status=0;
					regame();
					line.regame();
					
				}
				break;
			default:
			}
			
			
		}
		});
		
		
		
		while(true) {
			repaint();
			nextLevel();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		
		public void nextLevel() {
			if ( gameW() && status == 1 )  {
				if (count > goal ) {
					if (level == 5) {
						status = 4;
						
					} else {
						status=2;
						level++;
						
						System.out.println(level);
					}
					
					 
				} else {
					status = 3;
				}
				
				dispose(); // Dispose of the current frame
				gameWin gameWin1 = new gameWin(); // Create a new frame for the next level
				gameWin1.launch();
			  }
				
		   
		   }
	
	
	
	
	
	@Override
	public void paint(Graphics g) {
		
		
		offScreen=this.createImage(768,1000);
		Graphics gImage=offScreen.getGraphics();
		
		gImage.drawImage(bg, 0, 0,null);
		gImage.drawImage(bg1,0,0,null);
		switch(status){
		case 0: 
			
			   gImage.drawString("READY TO START : ",400,80);
			   
			  
			  
			break;
		case 1:
				   line.paintSelf(gImage);
				   gold.paintSelf(gImage);
				   gImage.drawImage(item,450,40,null);
				   gImage.drawImage(bg2,310,50,null);
				   
				   
					gImage.drawString("POINT :"+ count, 30,150);
					
					gImage.drawString("* :"+ waterNum, 510,70);
				   
					gImage.drawString("NO. :"+ level, 30,60);
	               
	                //目标积分
					gImage.drawString("TARGRT POINT :"+ goal, 30,110);
	                
				  
				   
				   endTime=System.currentTimeMillis();
				   long gameTime=20-(endTime-startTime)/1000;
				  if(gameTime>0) {
				  gImage.drawString(" TIME: "+gameTime,520,150);
				   
				 }
				 if(gameTime<0) {
				  gImage.drawString(" TIME: "+0,520,150);
				 }
				   
				  
				   
				   for(gameGold gold: goldL) {
				    gold.paintSelf(gImage);
				   }
				   

					break;
				  
			
		case 2:	
			gImage.drawImage(item,300,400,null);
			gImage.drawString("PRICE :"+ waterNum, 300,500);
			gImage.drawString("BUY IT OR NOT :"+ waterNum, 300,550);
			if(shop) {
				count-=price;
				waterNum++;
				shop=false;
				status=1;
				startTime=System.currentTimeMillis();
			}
			break;
		case 3: 
			gImage.drawString("LOST :", 250,350);
			gImage.drawString("POINT :"+ count, 200,450);
            break;
		case 4:
			
			gImage.drawString("SUCCESS :", 250,350);
			gImage.drawString("POINT :"+ count, 200,450);
             break;
		

			
			
		}
		
			  
		g.drawImage(offScreen, 0, 0,null);
	}
	void regame() {
		level=1;
		goal=level*5;
		count=0;
		waterNum=3;
		waterFlag=false;
		
	}
	boolean gameW() {
		  long time=( endTime- startTime ) /1000;
		  if(time >20) {
		   return true;
		  }
		  
		   return false;
		  
		 }
	
	
	public static void main(String[] args) {
		gameWin game=new gameWin();
		game.launch();
	}

 }

