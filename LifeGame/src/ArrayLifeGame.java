import javax.swing.JFrame;

public class ArrayLifeGame {
	
	//@ public invariant \type(boolean[]) == \elemtype(\typeof(cells));
	//@public invariant cells.length == MAX_X;
	////@public invariant (\forall int i; 0 <= i && i < MAX_X; \invariant_for(cells[i]));
	//@public invariant (\forall int x; 0 <= x && x < MAX_X; cells[x] != null && cells[x].length == MAX_Y);
	
	//@public invariant (\forall int x; 0 < x && x < MAX_X-1; cells[x][1] == cells[x][MAX_Y-1] && cells[x][0] == cells[x][MAX_Y-2]);
	//@public invariant (\forall int y; 0 < y && y < MAX_Y-1; cells[1][y] == cells[MAX_X-1][y] && cells[0][y] == cells[MAX_X-2][y]);
	//@public invariant (cells[0][0] == cells[MAX_X-2][MAX_Y-2])&& (cells[MAX_X-1][0] == cells[1][MAX_Y-2]) && (cells[0][MAX_Y-1] == cells[MAX_X-2][1]) && (cells[MAX_X-1][MAX_Y-1] == cells[1][1]);
	
	//@public invariant (\forall int i; 0 <= i && i < MAX_X;(\forall int j; 0 <= j && j < MAX_X; i != j ==> cells[i] != cells[j]));
	/*@spec_public*/ boolean cells[][];
	
	//@public invariant newcells.length == MAX_X;
	
	//@ public invariant \type(boolean[]) == \elemtype(\typeof(newcells));
	////@public invariant (\forall int i; 0 <= i && i < MAX_X; \invariant_for(newcells[i]));
	//@public invariant (\forall int x; 0 <= x && x < MAX_X; newcells[x] != null && newcells[x].length == MAX_Y);
	
	//@public invariant (\forall int i; 0 <= i && i < MAX_X;(\forall int j; 0 <= j && j < MAX_X; cells[i]!=newcells[j]));
	//@public invariant (\forall int i; 0 <= i && i < MAX_X;(\forall int j; 0 <= j && j < MAX_X; i != j ==> newcells[i] != newcells[j]));
	/*@spec_public*/ boolean newcells[][];
	
	
	final public int MAX_X = 12;
	final public int MAX_Y = 12;
	
	
	ArrayLifeGame(){
		cells = new boolean[MAX_X][MAX_Y];
		newcells = new boolean[MAX_X][MAX_Y];
	}
	
	////@ensures (\forall int x; 0 <= x && x <= MAX_X-1;(\forall int y; 0 <= y && y <= MAX_Y-1; cells[x][y] == newcells[x][y]));
	
	//中にcopyToNew書いたので外す
	////@ensures (\forall int x; 0 < x && x < MAX_X-1; newcells[x][1] == newcells[x][MAX_Y-1] && newcells[x][0] == newcells[x][MAX_Y-2]);
	////@ensures (\forall int y; 0 < y && y < MAX_Y-1; newcells[1][y] == newcells[MAX_X-1][y] && newcells[0][y] == newcells[MAX_X-2][y]);
	////@ensures (newcells[0][0] == newcells[MAX_X-2][MAX_Y-2])&& (newcells[MAX_X-1][0] == newcells[1][MAX_Y-2]) && (newcells[0][MAX_Y-1] == newcells[MAX_X-2][1]) && (newcells[MAX_X-1][MAX_Y-1] == newcells[1][1]);

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
		//@maintaining (\forall int i; 1<=i && i<x;newcells[i][MAX_Y-1] == newcells[i][1] && newcells[i][0] == newcells[i][MAX_Y-2]);
		//@decreases MAX_X-1 - x;
		for(int x = 1; x < MAX_X-1; x++){
			newcells[x][MAX_Y-1] = newcells[x][1];
			newcells[x][0] = newcells[x][MAX_Y-2];
		}
		//@maintaining 1<=y && y<=MAX_Y-1;
		//@maintaining (\forall int i; 1<=i && i<MAX_X-1;newcells[i][MAX_Y-1] == newcells[i][1] && newcells[i][0] == newcells[i][MAX_Y-2]);
		//@maintaining (\forall int i; 1<=i && i<y;newcells[MAX_X-1][i] == newcells[1][i] && newcells[0][i] == newcells[MAX_X-2][i]);
		//@decreases MAX_Y-1 - y;
		for(int y = 1; y < MAX_Y-1; y++){
			newcells[MAX_X-1][y] = newcells[1][y];
			newcells[0][y] = newcells[MAX_X-2][y];
		}
		newcells[0][0] = newcells[MAX_X-2][MAX_Y-2];
		newcells[MAX_X-1][0] = newcells[1][MAX_Y-2];
		newcells[0][MAX_Y-1] = newcells[MAX_X-2][1];
		//逆にしてた
		//newcells[1][1] = newcells[MAX_X-1][MAX_Y-1];
		newcells[MAX_X-1][MAX_Y-1] = newcells[1][1];
		
		copyToNew();
	}
	
	
	//@requires (\forall int x; 0 < x && x < MAX_X-1; newcells[x][1] == newcells[x][MAX_Y-1] && newcells[x][0] == newcells[x][MAX_Y-2]);
	//@requires (\forall int y; 0 < y && y < MAX_Y-1; newcells[1][y] == newcells[MAX_X-1][y] && newcells[0][y] == newcells[MAX_X-2][y]);
	//@requires (newcells[0][0] == newcells[MAX_X-2][MAX_Y-2])&& (newcells[MAX_X-1][0] == newcells[1][MAX_Y-2]) && (newcells[0][MAX_Y-1] == newcells[MAX_X-2][1]) && (newcells[MAX_X-1][MAX_Y-1] == newcells[1][1]);

	public void copyToNew(){
		//@maintaining 0<=x && x<=MAX_X;
		
		//@maintaining (\forall int x1; 0 < x1 && x1 < MAX_X-1; newcells[x1][1] == newcells[x1][MAX_Y-1] && newcells[x1][0] == newcells[x1][MAX_Y-2]);
		//@maintaining (\forall int y1; 0 < y1 && y1 < MAX_Y-1; newcells[1][y1] == newcells[MAX_X-1][y1] && newcells[0][y1] == newcells[MAX_X-2][y1]);
		//@maintaining (newcells[0][0] == newcells[MAX_X-2][MAX_Y-2]) && (newcells[MAX_X-1][0] == newcells[1][MAX_Y-2]) && (newcells[0][MAX_Y-1] == newcells[MAX_X-2][1]) && (newcells[MAX_X-1][MAX_Y-1] == newcells[1][1]);
		
		//@maintaining (\forall int x1; 0 <= x1 && x1 < x;(\forall int y1; 0 <= y1 && y1 < MAX_Y;cells[x1][y1] == newcells[x1][y1]));
		
		//@decreases MAX_X - x;
		for(int x = 0; x <= MAX_X-1; x++){
			//@maintaining 0<=x && x<=MAX_X;
			//@maintaining 0<=y && y<=MAX_Y;
			
			//@maintaining (\forall int x1; 0 < x1 && x1 < MAX_X-1; newcells[x1][1] == newcells[x1][MAX_Y-1] && newcells[x1][0] == newcells[x1][MAX_Y-2]);
			//@maintaining (\forall int y1; 0 < y1 && y1 < MAX_Y-1; newcells[1][y1] == newcells[MAX_X-1][y1] && newcells[0][y1] == newcells[MAX_X-2][y1]);
			//@maintaining (newcells[0][0] == newcells[MAX_X-2][MAX_Y-2]) && (newcells[MAX_X-1][0] == newcells[1][MAX_Y-2]) && (newcells[0][MAX_Y-1] == newcells[MAX_X-2][1]) && (newcells[MAX_X-1][MAX_Y-1] == newcells[1][1]);
			
			//@maintaining (\forall int x1; 0 <= x1 && x1 < x;(\forall int y1; 0 <= y1 && y1 < MAX_Y;cells[x1][y1] == newcells[x1][y1]));
			//@maintaining (\forall int y1; 0 <= y1 && y1 < y; cells[x][y1] == newcells[x][y1]);
			
			//@decreases MAX_Y - y;
			for(int y = 0; y <= MAX_Y-1; y++){
				cells[x][y] = newcells[x][y];
			}
		}
	}
	
	//↓LivingcellNumの仕様をもっと書かないと無理
	// @ ensures newcells[x][y] ==> (cells[x][y]&&(livingCellsNum(x,y)==2||livingCellsNum(x,y)==3) || !cells[x][y] && livingCellsNum(x,y)==3);
	// @ ensures (!newcells[x][y]) ==> (cells[x][y]&&livingCellsNum(x,y)<=1 || cells[x][y]&&livingCellsNum(x,y)>=4 || !cells[x][y]&&livingCellsNum(x,y)!=3);
	 
	
	  // @also
	  // @ requires !(0<x&&x<MAX_X-1&&0<y&&y<MAX_Y-1) && -2147483648<x&&x<2147483647&&-2147483648<y&&y<2147483647;
	  // @ assignable newcells[x];
	  // @ signals_only java.lang.ArrayIndexOutOfBoundsException;
	
	
	//assignable newcells[x][y];を書いてなかったらStepAllの検証がものすごく遅かった
	//@requires (0<x&&x<MAX_X-1&&0<y&&y<MAX_Y-1);
	//@ensures (\forall int x1; 0<=x1 && x1<MAX_X;(\forall int y1; 0<=y1 && y1<MAX_Y;!(x==x1 && y==y1) ==> newcells[x1][y1]==\old(newcells[x1][y1])));
	//@ensures (\forall int x2; 0<=x2 && x2<MAX_X;(\forall int y2; 0<=y2 && y2<MAX_Y;cells[x2][y2]==\old(cells[x2][y2])));
	public void stepCell(int x,int y){
		if(cells[x][y]&&livingCellsNum(x,y)<=1){
			newcells[x][y] = false;
		//}else if(cells[x][y]&&livingCellsNum(x,y)<=4){
		}else if(cells[x][y]&&livingCellsNum(x,y)>=4){
			newcells[x][y] = false;
		}else if(!cells[x][y]&&livingCellsNum(x,y)==3){
			newcells[x][y] = true;
		}else{
			//@assert \old(x) == x && \old(y) == y && \old(newcells[x]) == newcells[x];
			newcells[x][y] = cells[x][y];
		}
	}
	
	//-1を返さないとStepCellで崩壊しそう これもだめ、siganals?
	/*@ normal_behavior
	  @ requires (0<x&&x<MAX_X-1&&0<y&&y<MAX_Y-1);
	  @ ensures \result>=0 && \result<=9;
	  @also
	  @ requires !(0<x&&x<MAX_X-1&&0<y&&y<MAX_Y-1) && -2147483648<x&&x<2147483647&&-2147483648<y&&y<2147483647;
	  @ signals_only java.lang.ArrayIndexOutOfBoundsException;
	 */
	//@pure
	public int livingCellsNum(int x,int y){
		int sum = 0;
		//@ maintaining -1<=i && i<=2;
		//@ decreases 2-i;
		//@ maintaining 0<=sum && sum<=((i+1)*3);
		for(int i=-1;i<2;i++){
			//for(int j=-1;j<2;i++){ //OpenJMLのおかげで見つけたやつ 　オーバーフロー起こるせいでここが黄色くなるの良い
			//@ maintaining -1<=i && i<=2;
			//@ maintaining -1<=j && j<=2;//j<2じゃない罠に引っかかった
			//@ maintaining 0<=sum && sum<=((i+1)*3+(j+1));
			//@ decreases 2-j;
			for(int j=-1;j<2;j++){
				if(i!=0 || j!=0){
					
					
					// if(cells[i][j])sum++; //OpenJMLのおかげで見つけたやつ

					if(cells[x+i][y+j])sum++;
					
				}
				
			}
		}
		return sum;
	}
	
	public static void main(String[] args) {
		JFrame frame=new JFrame("test");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(400,400);
	    frame.setVisible(true);
	}
}
