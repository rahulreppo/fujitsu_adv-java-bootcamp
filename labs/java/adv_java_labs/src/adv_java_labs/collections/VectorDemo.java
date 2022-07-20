package adv_java_labs.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class VectorDemo {
	public static void main(String[] args) {
		List list=new Vector();
			list.add("python");
			list.add("Rahul");
			list.add("Java");
			list.add(".net");
			
			System.out.println(list);
			System.out.println(list.get(2));
			
			list.set(3, "c++");
			System.out.println(list);
			
			list.remove(2);
			System.out.println(list);
			
			

	}

}
