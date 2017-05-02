package mypackage;
import java.util.Comparator;  
import java.util.TreeSet;  
  
//import org.junit.Test;  
  
//非线程安全  
public class TreeSetTest {  
    /** 
     * TreeSet:它可以给Set集合中的元素进行指定方式的排序。 保证元素唯一性的方式：通过比较的结果是否为0. 底层数据结构是：二叉树。 
     *  
     * 排序的第一种方式： 让元素自身具备比较性。只要让元素实现Comparable接口，覆盖compareTo方法即可。 
     *  
     * 但是，如果元素自身不具备比较性，或者元素自身具备的比较性，不是所需要的。 
     * 比如，学生的自然排序是按年龄排序，现在想要按照学生姓名排序。还可以不改动原有代码。 这时怎么办呢？ 
     *  
     * 排序的第二种方式：自定比较器的方式。这时可以让集合自身具备比较性。 
     *  
     * 可以定义一个类实现Comparator接口，覆盖compare方法。 
     * 将该Comparator接口子类对象作为实际参数传递给TreeSet集合构造函数。 
     */  
    public static void main(String[] args) {  
        TreeSet<Student> ts = new TreeSet<Student>();  
        // Student如果不实现Comparable，add时会出错，Student cannot be cast to java.lang.Comparable  
        //添加第一个对象时，TreeSet里没有任何元素，所以没有问题；  
        //当添加第二个Err对象时，TreeSet就会调用该对象的compareTo(Object obj)方法与集合中其他元素进行比较——  
        //如果对应的类没有实现Comparable接口，则会引发ClassCastException异常  
        // add方法内部会对插入的数据进行排序，此时元素自身具备比较性，因为其实现了Comparable接口  
        ts.add(new Student("lisi0", 30));  
        ts.add(new Student("lisixx", 29));  
        ts.add(new Student("lisi9", 29));  
        ts.add(new Student("lisi8", 38));  
        // 重复插入无效，但是不报错（根据年龄和名字进行比较，都相同，视为同一个学生）  
        ts.add(new Student("lisixx", 29));  
        ts.add(new Student("lisixx", 28));  
        ts.add(new Student("lisi4", 14));  
        ts.add(new Student("lisi7", 27));  
        System.out.println(ts);  
    }  
      
    //此时根据自定义的比较器进行排序  
//    @Test  
    public void testComparator() {  
        // 可以利用匿名类，按名字进行排序  
        TreeSet<Student> ts = new TreeSet<Student>(new Comparator<Student>() {  
  
            @Override  
            public int compare(Student s1, Student s2) {  
                int num = s1.getName().compareTo(s2.getName());  
                return num;  
            }  
        });  
  
        ts.add(new Student("lisi0", 30));  
        ts.add(new Student("lisixx", 29));  
        ts.add(new Student("lisi9", 29));  
        ts.add(new Student("lisi8", 38));  
        //此时根据名字进行排序，不管年龄是否相同，下面两个都不会插入  
        //名字相同，则视为同一个人  
        ts.add(new Student("lisixx", 29));  
        ts.add(new Student("lisixx", 28));  
        ts.add(new Student("lisi4", 14));  
        ts.add(new Student("lisi7", 27));  
  
        System.out.println(ts);  
    }  
    //奇数升序，偶数降序  
//    @Test  
    public void mySort() {  
        TreeSet set = new TreeSet(new Comparator() {  
            int r = 0;  
            @Override  
            public int compare(Object a, Object b) {  
                int n1 = Integer.parseInt(a.toString());  
                int n2 = Integer.parseInt(b.toString());  
                if (n1 % 2 != n2 % 2) {  
                    r = (n2 % 2 - n1 % 2);  
                } else if (n1 % 2 == 1) {  
                    if (n1 > n2)  
                        r = 1;  
                    else if (n1 < n2)  
                        r = -1;  
                } else if (n1 % 2 == 0) {  
                    if (n1 > n2)  
                        r = -1;  
                    else if (n1 < n2)  
                        r = 1;  
                }  
                return r;  
            }  
        });  
        set.add("1");  
        set.add("2");  
        set.add("3");  
        set.add("4");  
        set.add("5");  
        set.add("6");  
        set.add("7");  
        set.add("8");  
        set.add("9");  
        set.add("10");  
        System.out.println(set);  
    }  
  
    // //同姓名同年龄的学生视为同一个学生  
    public static class Student implements Comparable<Student> {  
  
        private int age;  
        private String name;  
  
        public Student(String name, int age) {  
            this.age = age;  
            this.name = name;  
        }  
  
        @Override  
        public int compareTo(Student stu) {  
            int num = new Integer(this.age).compareTo(new Integer(stu.age));  
            return num == 0 ? this.name.compareTo(stu.name) : num;  
        }  
  
        public String toString() {  
            return name + "::" + age;  
        }  
  
        public int getAge() {  
            return age;  
        }  
  
        public void setAge(int age) {  
            this.age = age;  
        }  
  
        public String getName() {  
            return name;  
        }  
  
        public void setName(String name) {  
            this.name = name;  
        }  
    }  
}  