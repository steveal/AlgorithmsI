package mypackage;

public class Test {
    public static void main(String[] args) {
        if(null == null) {
            System.out.println("xx");
        }
        
        Board2 b1 = null;
        Board2 b2 = null;
        if(b1 == b2) {
            System.out.println("yy");
        }
        
        People pp = new People("steve","ll","dog",2);
        
    }
}
