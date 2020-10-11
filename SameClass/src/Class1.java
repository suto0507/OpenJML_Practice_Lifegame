
public class Class1 {
	//@ghost Class2 class2;
	//@invariant class2.num2 == num1;
	//@invariant \invariant_for(class2);
	
	int num1;
	Class1(){
		num1 = 1;
	}
	
	
	void setNum(int num){
		num1 = num;
	}
	
	//
	int getNum(){
		return num1;
	}
}
