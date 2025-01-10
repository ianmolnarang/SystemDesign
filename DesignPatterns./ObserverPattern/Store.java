public class Store{
    public static void main(String args[]){
        StocksObservable observable = new StocksObservable();
        NotificationAlertObserver observer1 = new EmailAlertObserverImpl("John", observable);
        NotificationAlertObserver observer2 = new MobileAlertObserverImpl("John", observable);
        IphoneObservalbleImpl.add(observer1);
        IphoneObservalbleImpl.add(observer2);
        observable.setStockCount(10);
        observable.setStockCount(20);
    }
}
