public class MobileAlertObserverImpl implements NotificationAlertObserver {

    String userName;
    StocksObservable observable;

    public MobileAlertObserverImpl(String userName, StocksObservable observable) {
        this.userName = userName;
        this.observable = observable;
    }
    @Override
    public void update() {
        System.out.println("Hello " + userName + ", Mobile Alert: Stock count is " + observable.getStockCount());
    }
}

