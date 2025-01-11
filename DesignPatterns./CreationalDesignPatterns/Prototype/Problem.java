public class Student {
    public int age ;
    private int rollNum;
    public String name;

     Student(int age, int rollNum, String name){
        this.age = age;
        this.rollNum = rollNum;
        this.name = name;
};

public class Main {
    Student obj = new Student(20, 1, "John");
    //clone
    Student cloneOBJ = new Student() ;
    //now instead of calling the Student class and use it's methods, we can use the cloneOBJ to get the same result
    cloneOBJ.name = obj.name;
    cloneOBJ.age = obj.age;
    cloneOBJ.rollNum = obj.rollNum;

    //this is the problem with the prototype pattern, we have to manually copy the values of the object to the new object
    //this is not a good practice and is not efficient
    //this is where the prototype pattern comes in
    //we can create a clone method in the Student class and use it to clone the object
    //this way we can avoid the manual copying of the values
    //also the private members are not accessible
    //also for making the clone object, we should know the objects of the class
}