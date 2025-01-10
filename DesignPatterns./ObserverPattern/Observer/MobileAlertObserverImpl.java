public class MobileAlertObserverImpl implements NotificationAlertObserver {

    String userName;
    StocksObservable observable;
    //constructor ingesting the userName and the observable object
    
    public MobileAlertObserverImpl(String userName, StocksObservable observable) {
        this.userName = userName;
        this.observable = observable;
    }
    @Override
    public void update() {
        System.out.println("Hello " + userName + ", Mobile Alert: Stock count is " + observable.getStockCount());
    }
}

// i.e the observer pattern is used to notify the observers when there is a change in the observable object