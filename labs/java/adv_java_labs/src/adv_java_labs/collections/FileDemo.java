package adv_java_labs.collections;

import java.io.File;

public class FileDemo {

	
public static void main(String args[])
{
	File file =new File("D:\\Java training");
	
	System.out.println(file.isDirectory());
	System.out.println(file.isFile());
	System.out.println(file.isAbsolute());
	System.out.println(file.canRead());
	System.out.println(file.canExecute());
}

}
