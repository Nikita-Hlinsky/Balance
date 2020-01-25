

public class Order implements Comparable <Order>{
    public int cost;
    private String name;

    public  Order(int c, String n){
        cost = c ;
        name = n;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String ToString() {
        return  name + " - " + cost + " руб";
    }

    @Override
    public int compareTo(Order ord) {
        if(this.getCost() > ord.getCost())
            return 1;
        else
            return -1;
    }

}

