
public class DifferentLifeGame {
	 Cell livingcells[];
	 int livingcellslength;
	 Cell steplist[];
	 int steplistlength;
	 Cell newlivingcells[];
	 int newlivingcellslength;
	 
	//1~200
	final public int MAX_X = 202;
	final public int MAX_Y = 202;
	 
	 
	DifferentLifeGame(boolean[][] array){
		livingcells = new Cell[(MAX_X-2)*(MAX_Y-2)];
		livingcellslength = 0;
		steplist = new Cell[(MAX_X-2)*(MAX_Y-2)];
		steplistlength = 0;
		newlivingcells = new Cell[(MAX_X-2)*(MAX_Y-2)];
		newlivingcellslength = 0;
		Cell tmpcells[][];
		tmpcells = new Cell[200][200];
		for(int x = 1; x < MAX_X-1; x++){
			for(int y = 1; y < MAX_Y-1; y++){
				tmpcells[x%200][y%200] = new Cell(x,y);
				if(array[x][y]){
					tmpcells[x%200][y%200].setLiving(true);
					livingcells[livingcellslength] = tmpcells[x%200][y%200];
					livingcellslength++;
				}
			}
		}
		for(int x = 1; x < MAX_X-1; x++){
			for(int y = 1; y < MAX_Y-1; y++){
				Cell tmpneis[];
				tmpneis = new Cell[8];
				int tmpnum = 0;
				for(int i=-1;i<2;i++){
					for(int j=-1;j<2;j++){
						if(i!=0 || j!=0){
							tmpneis[tmpnum] = tmpcells[(x+i)%200][(y+j)%200];
							tmpnum++;
						}
					}
				}
				tmpcells[x%200][y%200].setNeighbors(tmpneis);
			}
		}
	}
	 
	public void stepAll(){
		steplistlength = 0;
		for(int i = 0; i < livingcellslength; i++){
			if(!livingcells[i].steplisted){
				steplist[steplistlength] = livingcells[i];
				steplistlength ++;
				livingcells[i].setStepListed(true);
			}
			for(int j = 0; j < 8; j++){
				if(!livingcells[i].neighbors[j].steplisted){
					steplist[steplistlength] = livingcells[i].neighbors[j];
					steplistlength ++;
					livingcells[i].neighbors[j].setStepListed(true);
				}
			} 
		}
		
		newlivingcellslength = 0;
		for(int i = 0; i < steplistlength; i++){
			stepCell(steplist[i]);
			steplist[i].setStepListed(false);
		}
		
		copyToNew();
		
	}
	
	public void copyToNew(){
		for(int i = 0; i < livingcellslength; i++)livingcells[i].setLiving(false);
		for(int i = 0; i < newlivingcellslength; i++)livingcells[i] = newlivingcells[i];
		livingcellslength = newlivingcellslength;
		for(int i = 0; i < livingcellslength; i++)livingcells[i].setLiving(true);
	}
	
	public void stepCell(Cell cell){
		if(cell.living&&livingCellsNum(cell)<=1){
		}else if(cell.living&&livingCellsNum(cell)>=4){
		}else if(!cell.living&&livingCellsNum(cell)==3){
			newlivingcells[newlivingcellslength] = cell;
			newlivingcellslength++;
		}else{
			if(cell.living){
				newlivingcells[newlivingcellslength] = cell;
				newlivingcellslength++;
			}
		}
	}
	 
	 
	public int livingCellsNum(Cell cell){
		int sum = 0;
		for(int i=0;i<8;i++){
			if(cell.neighbors[i].living)sum++;
		}
		return sum;
	}
	 
	public boolean getCell(int x,int y){
		for(int i = 0; i < livingcellslength; i++){
			if(livingcells[i].x == x && livingcells[i].y == y){
				return true;
			}
		}
		return false;
	}
	
	public int getCellx(int i){
		return livingcells[i].x;
	}
	public int getCelly(int i){
		return livingcells[i].y;
	}
	public int getCellslen(){
		return livingcellslength;
	}
	
	 
	 
	public class Cell{
		 int x,y;
		 boolean steplisted;
		 boolean living;
		 Cell neighbors[];
		 Cell(int x,int y){
			 this.x=x;
			 this.y=y;
			 neighbors = new Cell[8];
		 }
		 
		 
		 void setNeighbors(Cell[] cells){
			 neighbors = cells;
		 }
		 
		 void setStepListed(boolean b){
			 steplisted = b;
		 }
		 
		 void setLiving(boolean b){
			 living = b;
		 }
	 }
}
