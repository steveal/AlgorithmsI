package mypackage;

public class People {
    private String name;
    private Pet pet;
    
    public People(String _name,String petname, String pettype, int petage) {
        this.name = _name;
        pet = new Pet(petname, pettype, petage);
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
