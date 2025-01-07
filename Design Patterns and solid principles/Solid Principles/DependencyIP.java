/*
Class should depend on the interfaces rather than concrete classes
i.e if we have a class that depends on another class, it should depend on the interface of that class
*/

// Mouse<<Interface>>--Mouse
// -> Wired Mouse
// -> Bluetooth Mouse

// Keyboard<<Interface>>--Keyboard
// -> Wired Keyboard
// -> Bluetooth Keyboard

//wrong way
class Macbook{
    private final WiredMouse wiredMouse;
    private final WiredKeyboard wiredKeyboard;

    public Macbook(){
        Keyboard keyboard = new WiredKeyboard();
        Mouse mouse = new WiredMouse();
    }
    //due to which i am bound to use the method, this is very generic and not specific
    //i can't use bluetooth mouse or keyboard

}

//correct way
class Macbook{
    private final WiredMouse wiredMouse;
    private final WiredKeyboard wiredKeyboard;

    public Macbook(Keyboard keyboard, Mouse mouse){
        this.keyboard = Keyboard;
        this.mouse = wiredKeyboard;
    }
    //now i can use any type of keyboard and mouse
    
}