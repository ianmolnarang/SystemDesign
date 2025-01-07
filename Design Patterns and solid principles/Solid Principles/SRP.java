// Single Responsibility Principal
/*
 This principle states that “A class should have only one reason to change”
 which means every class should have a single responsibility or single job or single purpose.
 In other words, a class should have only one job or purpose within the software system.
 i.e. a baker should of responsibility of baking, a driver should have the responsibility of driving.
 */


class Marker{
    String name;
    String color ;
    int year ;
    int price ;


    public Marker(String name, String color, int year, int price){
        this.name = name ;
        this.color = color ;
        this.year = year ;
        this.price = price ;
    }
}

//this is wrong approach, since one class handling multiple responsiblity
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

//correct way
class Invoice{
    Invoice invoice ;
    public InvoiceDao(Invoice invoice){
        this.invoice=invoice;
    }
    public void saveToDb(){
    }
}

class Invoice{
    private Marker maker ;
    private int quantitiy ;

    public Invoice(Marker maker, int quantitiy){
        this.maker= marker;
        this.quantitiy=quantitiy;
    }

    public int Calculate(){
       int price = ((marker.price) * this.quantitiy);
        return price;
    }
}
