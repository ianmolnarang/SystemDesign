public class Vehicle {
    DriveStrategy driveStrategy;

    //constructor injection
    Vehicle(DriveStrategy driveStrategy) {
        this.driveStrategy = driveStrategy;
    }
    // this.driveStrategy saves the strategy object passed to the constructor further call the drive method of the strategy object.
    // helps to eliminate the duplication
    public void drive() {
        driveStrategy.drive();
    }
}