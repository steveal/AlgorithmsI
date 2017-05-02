package mypackage;

public class Pet {
    private String type;
    private String name;
    private int age;
    
    public Pet(String petname, String pettype, int petage) {
        this.name = petname;
        this.type = pettype;
        this.age = petage;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
