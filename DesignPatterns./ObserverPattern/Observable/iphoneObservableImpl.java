// this is the implementation of the observable interface, concrete class

public class IphoneObservalbleImpl implements StocksObservable{
    private List<StockObserver> observers;
    private int stockCount;

    public IphoneObservalbleImpl() {
        observers = new ArrayList<>();
    }
    @Override
    public void add(NotificationAlertObserver observer) {
        observers.add(observer);
    }
    @Override
    public void remove(NotificationAlertObserver observer) {
        observers.remove(observer);
    }
    @Override
    public void notifyObservers() {
        //iterate over all the observers and notify them
        for(StockObserver observer : observers) {
            observer.update();
        }
    }
    @Override
    public int getStockCount() {
        return stockCount;
    }
    @Override
    public void setStockCount(int stockCount) {
        if(this.stockCount != stockCount) {
            this.stockCount = stockCount;
            notifyObservers();
        }
        stockCount = stockCount + newStockAdded;
    }
}

