/*
Interface should be such, that client should not be forced to implement the methods that it does not use.
*/
interface ReastuarauntEmployee {
    void washDishes();
    void serveFood();
    void cleanFloor();
    void cookFood();
}
class waiter implements ReastuarauntEmployee {
    @Override
    public void washDishes() {
        // not a waiter job
    }
    @Override
    public void serveFood() {
        // right job
    }
    @Override
    public void cleanFloor() {
        // not a waiter job
    }
    @Override
    public void cookFood() {
        // not a waiter job
    }
}