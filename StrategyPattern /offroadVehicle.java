public class PassengerVehicle extends Vehicle {
    public PassengerVehicle() {
        //this will call the constructor of the parent class
        super(new NormalDriveStrategy());
    }
}