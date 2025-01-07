/*
if class B is subtype of classs A, then we should we able to replace A with B without affecting the functionality of the program.
Subclass should extend the functionality of the parent class without changing the behavior of the parent class.
*/

//correct
interface Bike{
    void turnonEngine();
    void accelerate();
}

class MotorBike implements Bike{
    boolean isEngineOn;
    int speed;
    public void turnonEngine(){
        System.out.println("MotorBike engine is on");
    }
    public void accelerate(){
        System.out.println("MotorBike is accelerating");
    }
}

//wrong way
interface Bike{
    void turnonEngine();
    void accelerate();
}

class Cycle implements Bike{
    boolean isEngineOn;
    int speed;

    public void turnonEngine(){
       //cycle does not have engine
    }
    public void accelerate(){
        //cycle does not have engine
    }
}
