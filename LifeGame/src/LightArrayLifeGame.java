import java.applet.Applet;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;

import javax.swing.JApplet;
import javax.swing.JFrame;

public class LightArrayLifeGame {
	
	
	//@public invariant cells.length == MAX_X;
	//@public invariant (\forall int x; 0 <= x && x < MAX_X; cells[x] != null && cells[x].length == MAX_Y);
	/*@spec_public*/ boolean cells[][];
	
	//@public invariant newcells.length == MAX_X;
	//@public invariant (\forall int x; 0 <= x && x < MAX_X; newcells[x] != null && newcells[x].length == MAX_Y);
	/*@spec_public*/ boolean newcells[][];
	
	final public int MAX_X = 202;
	final public int MAX_Y = 202;
	
	
	
	//@requires array.length == 202;
	//@requires (\forall int x; 0 <= x && x < 202; array[x] != null && array[x].length == 202);
	LightArrayLifeGame(boolean[][] array){
		cells = new boolean[MAX_X][MAX_Y];
		newcells = new boolean[MAX_X][MAX_Y];
		//@maintaining 1<=x && x<=MAX_X-1;
		//@decreases MAX_X-1 - x;
		for(int x = 1; x < MAX_X-1; x++){
			//@maintaining 1<=x && x<=MAX_X-1;
			//@maintaining 1<=y && y<=MAX_Y-1;
			//@decreases MAX_Y-1 - y;
			for(int y = 1; y < MAX_Y-1; y++){
				//cells[x][y] = (Math.random()>0.6);
				cells[x][y] = array[x][y];
			}
		}
	}
	
	
	public void stepAll(){
		//@maintaining 1<=x && x<=MAX_X-1;
		//@decreases MAX_X-1 - x;
		for(int x = 1; x < MAX_X-1; x++){
			//@maintaining 1<=x && x<=MAX_X-1;
			//@maintaining 1<=y && y<=MAX_Y-1;
			//@decreases MAX_Y-1 - y;
			for(int y = 1; y < MAX_Y-1; y++){
				stepCell(x, y);
			}
		}
		//@maintaining 1<=x && x<=MAX_X-1;
		//@decreases MAX_X-1 - x;
		for(int x = 1; x < MAX_X-1; x++){
			newcells[x][MAX_Y-1] = newcells[x][1];
			newcells[x][0] = newcells[x][MAX_Y-2];
		}
		//@maintaining 1<=y && y<=MAX_Y-1;
		//@decreases MAX_Y-1 - y;
		for(int y = 1; y < MAX_Y-1; y++){
			newcells[MAX_X-1][y] = newcells[1][y];
			newcells[0][y] = newcells[MAX_X-2][y];
		}
		newcells[0][0] = newcells[MAX_X-2][MAX_Y-2];
		newcells[MAX_X-1][0] = newcells[1][MAX_Y-2];
		newcells[0][MAX_Y-1] = newcells[MAX_X-2][1];
		newcells[1][1] = newcells[MAX_X-1][MAX_Y-1];
		
		//@maintaining 0<=x && x<=MAX_X;
		//@decreases MAX_X - x;
		for(int x = 0; x <= MAX_X-1; x++){
			//@maintaining 0<=x && x<=MAX_X;
			//@maintaining 0<=y && y<=MAX_Y;
			//@decreases MAX_Y - y;
			for(int y = 0; y <= MAX_Y-1; y++){
				cells[x][y] = newcells[x][y];
			}
		}
	}
	
	//@requires 0<x && x<MAX_X-1 && 0<y && y<MAX_Y-1;
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
	
	//@requires 0<x && x<MAX_X-1 && 0<y && y<MAX_Y-1;
	public int livingCellsNum(int x,int y){
		int sum = 0;
		//@ maintaining -1<=i && i<=2;
		//@ decreases 2-i;
		//@ maintaining 0<=sum && sum<=((i+1)*3);
		for(int i=-1;i<2;i++){
			//@ maintaining -1<=i && i<=2;
			//@ maintaining -1<=j && j<=2;
			//@ maintaining 0<=sum && sum<=((i+1)*3+(j+1));
			//@ decreases 2-j;
			for(int j=-1;j<2;j++){
				if(i!=0 || j!=0){

					if(cells[x+i][y+j])sum++; //sumのループインバリアントを書かないとオーバーフローするかもって言われた
					
				}
				
			}
		}
		return sum;
	}
	
	//@requires 0<=x && x<MAX_X && 0<=y && y<MAX_Y;
	public boolean getCell(int x,int y){
		return this.cells[x][y];
	}
	

	
	
	
}
