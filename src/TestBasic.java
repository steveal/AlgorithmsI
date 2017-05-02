import java.util.ArrayList;
import java.util.Arrays;


public class TestBasic {
	

	 public static void main(String[] args) {
		 Point p1 = new Point(1,2);
		 Point p2 = new Point(3,4);
		 Point[] parr1 = new Point[]{p2,p1};
//		 Point[] parr2 = new Point[parr1.length];
//		 for(int i = 0 ; i<parr1.length ; i++) {
//		     parr2[i]=parr1[i];
//		 }
		 Point[] parr2 = parr1.clone();
		 Arrays.sort(parr2);
		 System.out.println(Arrays.toString(parr1));
		 System.out.println(Arrays.toString(parr2));
		 
		 
	 }
	 
	 
	 
	 
}
