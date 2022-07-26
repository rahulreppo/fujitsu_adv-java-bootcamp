package java8.calc;

import java.util.function.BiFunction;

public class Lambdawithinterface {

	public static void main(String[] args) {
		System.out.println(Lambdawithinterface.calculate((a,b)->a+b, 50, 30));

	}
	public static int calculate(BiFunction<Integer, Integer, Integer>op,int a,int b) {
		return op.apply(a, b);
	}

}
