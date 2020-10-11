
public class Test {
	
	//@invariant array.length == 2;
	//@invariant (\forall int x; 0 <= x && x < 2; array[x] != null && array[x].length == 2);
	boolean array[][];
	
	
	Test(){
		array = new boolean[2][2];
	}
	
	//@ assignable array[0][1];
	void setArray00(){
		array[0][0] = true;
	}
	
	public static void main(String[] args) {
		System.out.println((-1)%200);
		
	}
	

}
