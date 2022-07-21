package java8.calc;

public class MainCalc {

	public static void main(String[] args) {
		operation op=new operation() {
			
			@Override
			public int calculate(int a, int b) {
				// TODO Auto-generated method stub
				return 0;
			}
		};
		operation addition=(int a,int b)->a+b;
		System.out.println(MainCalc.open(addition,50,30));
		operation subtraction=(int a,int b)->a-b;
		System.out.println(MainCalc.open(subtraction,50,30));
		operation multiply=(int a,int b)->a*b;
		System.out.println(MainCalc.open(multiply,50,30));
		operation division=(int a,int b)->a/b;
		System.out.println(MainCalc.open(division,50,30));
		
		
	}
	private static int open(operation o,int a,int b) {
		return o.calculate(a, b);
	}

}
