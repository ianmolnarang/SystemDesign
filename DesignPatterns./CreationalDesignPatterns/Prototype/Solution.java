// the clone logic should be a responsibility of the class itself
// the clone method should be implemented in the class itself

public class Main{
    public static void main(String[] args) {
        Student obj = new Student(20, 1, "John");
        Student cloneOBJ = obj.clone();
        // -> left side ->. we are intializing the cloneOBJ object with the obj object
        // -> right side -> we are calling the clone method of the obj object, which is already implemented in the Student class
    }
}

public class Student implements Prototype {
    public int age ;
    private int rollNum;
    public String name;

    Student(int age, int rollNum, String name){
        this.age = age;
        this.rollNum = rollNum;
        this.name = name;
    }

    public Student clone() {
        return new Student(this.age, this.rollNum, this.name);
    }
}
