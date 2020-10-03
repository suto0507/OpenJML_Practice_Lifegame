import java.applet.Applet;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;

import javax.swing.JApplet;
import javax.swing.JFrame;

public class LightArrayLifeGame {
	
	
	
	boolean cells[][];
	
	
	boolean newcells[][];
	
	
	final public int MAX_X = 202;
	final public int MAX_Y = 202;
	
	
	LightArrayLifeGame(){
		cells = new boolean[MAX_X][MAX_Y];
		newcells = new boolean[MAX_X][MAX_Y];
		for(int x = 1; x < MAX_X-1; x++){
			for(int y = 1; y < MAX_Y-1; y++){
				cells[x][y] = (Math.random()>0.6);
			}
		}
	}
	
	
	public void stepAll(){
		
		for(int x = 1; x < MAX_X-1; x++){
			
			for(int y = 1; y < MAX_Y-1; y++){
				stepCell(x, y);
			}
		}
		
		for(int x = 1; x < MAX_X-1; x++){
			newcells[x][MAX_Y-1] = newcells[x][1];
			newcells[x][0] = newcells[x][MAX_Y-2];
		}
		
		for(int y = 1; y < MAX_Y-1; y++){
			newcells[MAX_X-1][y] = newcells[1][y];
			newcells[0][y] = newcells[MAX_X-2][y];
		}
		newcells[0][0] = newcells[MAX_X-2][MAX_Y-2];
		newcells[MAX_X-1][0] = newcells[1][MAX_Y-2];
		newcells[0][MAX_Y-1] = newcells[MAX_X-2][1];
		newcells[1][1] = newcells[MAX_X-1][MAX_Y-1];
		
		
		for(int x = 0; x <= MAX_X-1; x++){
			
			for(int y = 0; y <= MAX_Y-1; y++){
				cells[x][y] = newcells[x][y];
			}
		}
	}
	
	
	public void stepCell(int x,int y){
		if(cells[x][y]&&livingCellsNum(x,y)<=1){
			newcells[x][y] = false;
		//}else if(cells[x][y]&&livingCellsNum(x,y)<=4){
		}else if(cells[x][y]&&livingCellsNum(x,y)>=4){
			newcells[x][y] = false;
		}else if(!cells[x][y]&&livingCellsNum(x,y)==3){
			newcells[x][y] = true;
		}else{
			newcells[x][y] = cells[x][y];
		}
	}
	
	public int livingCellsNum(int x,int y){
		int sum = 0;
		
		for(int i=-1;i<2;i++){
			
			for(int j=-1;j<2;j++){
				if(i!=0 || j!=0){

					if(cells[x+i][y+j])sum++;
					
				}
				
			}
		}
		return sum;
	}
	
	public boolean getCell(int x,int y){
		return this.cells[x][y];
	}
	

	
	
	
}
