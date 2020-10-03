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
	
	Graphic(){
		lal = new LightArrayLifeGame();
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
		t.schedule(new RenderTask(), 0, 300);
	}
	
	void render() {
    	Graphics2D g = (Graphics2D) this.strategy.getDrawGraphics();
    	g.setBackground(Color.gray);
    	lal.stepAll();
    	g.clearRect(0, 0, this.frame.getWidth(), this.frame.getHeight());
    	
    	for(int x = 1; x < lal.MAX_X;x++){
    		for(int y = 1; y < lal.MAX_Y;y++){
    			g.setColor(new Color(250,100,100));
    			if(lal.getCell(x, y))g.fill(new Rectangle2D.Double(100 + x*5, 100 + y*5, 5, 5));
    			g.setColor(Color.RED);
    			if(lal.getCell(x, y))g.draw(new Rectangle2D.Double(100 + x*5, 100 + y*5, 5, 5));
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
