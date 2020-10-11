import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

public class Graphic {

	JFrame frame;
	BufferStrategy strategy;
	LightArrayLifeGame lal;
	ArrayLifeGame al;
	DifferentLifeGame dl;
	
	Graphic(){
		boolean array[][];
		array = new boolean[202][202];
		for(int x = 1; x < 202-1; x++){
			for(int y = 1; y < 202-1; y++){
				array[x][y] = (Math.random()>0.6);
			}
		}
		lal = new LightArrayLifeGame(array);
		al = new ArrayLifeGame(array);
		dl = new DifferentLifeGame(array);
		this.frame = new JFrame("test");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize(1200,1200);
		this.frame.setVisible(true);
	    
		this.frame.setIgnoreRepaint(true);
		this.frame.createBufferStrategy(2);
		this.strategy = this.frame.getBufferStrategy();
	}
	
	public static void main(String[] args) {
		Graphic gra = new Graphic();
		gra.start();
	           
	}
	
	void start(){
		Timer t = new Timer();
		t.schedule(new RenderTask(), 0, 500);
	}
	
	void render() {
    	Graphics2D g = (Graphics2D) this.strategy.getDrawGraphics();
    	g.setBackground(new Color(40,40,40));
    	al.stepAll();
    	dl.stepAll();
    	g.clearRect(0, 0, this.frame.getWidth(), this.frame.getHeight());
    	Color blue1 = new Color(30,40,100);
    	Color blue2 = new Color(60,75,150);
    	Color blue3 = new Color(200,100,150);
    	
    	for(int i = 0; i < dl.getCellslen();i++){
    		int x = dl.getCellx(i);
    		int y = dl.getCelly(i);
			g.setColor(blue3);
			g.fill(new Rectangle2D.Double(100 + x*5, 100 + y*5, 5, 5));
    	}
    	
    	for(int x = 1; x < al.MAX_X-1;x++){
    		for(int y = 1; y < al.MAX_Y-1;y++){
    			g.setColor(blue2);
    			if(al.getCell(x, y))g.draw(new Rectangle2D.Double(100 + x*5 + 1, 100 + y*5 + 1, 3, 3));
    			g.setColor(blue1);
    			if(al.getCell(x, y))g.draw(new Rectangle2D.Double(100 + x*5, 100 + y*5, 5, 5));
        	}
    	}
    	
    	
    	//g.setColor(Color.RED);
    	//g.draw(new Rectangle2D.Double(100, 100, 10, 10));
    	g.dispose();
    	this.strategy.show();
    	
    }
	
	class RenderTask extends TimerTask{
		
		@Override
		public void run() {
			Graphic.this.render();
		}
	}
}
