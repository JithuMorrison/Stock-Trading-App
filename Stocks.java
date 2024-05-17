import java.util.*;
class Stock{
  String name;
  double price;
  int volume;
  double percent;
  Stock(String name, double price){
    this.name = name;
    this.price = price;
  }
}
class Stocks{
  LinkedList<Stock> stocks =  new LinkedList<Stock>();
  public void addstock(String nam,double price){
    Stock st = new Stock(nam,price);
    stocks.add(st);
  }
  public void removestock(String nam){
    ListIterator<Stock> itr = stocks.listIterator();
    while(itr.hasNext()){
      Stock st = itr.next();
      if(st.name.equals(nam)){
        itr.remove();
        break;
      }
    }
  }
}