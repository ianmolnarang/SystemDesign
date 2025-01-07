//open for extension but closed for modification
//problem - > if the class is tested and working fine and on production, so why there is a need to change it? instead extends this!


//bad approach
class Invoice{
    private Marker maker ;
    private int quantitiy ;

    public Invoice(Marker marker, int quantity){
        this.maker= marker;
        this.quantitiy=quantitiy;
    }

    public int Calculate(){
        int price = ((marker.price) * this.quantitiy);
        return price;
    }
    public void printInvoice(){}
    public void saveToDB(){}
}

//good approach
interface Invoice{
    public void printInvoice();
    public void saveToDB();
}

class InvoiceDao implements Invoice{
    Invoice invoice ;
    public InvoiceDao(Invoice invoice){
        this.invoice=invoice;
    }
    public void saveToDb(){
    }
}