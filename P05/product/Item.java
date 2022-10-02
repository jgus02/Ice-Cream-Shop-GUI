package product;

public abstract class Item{
    private String name;
    private String description;
    private int cost;
    private int price;

    public Item(String name, String description, int cost, int price){
        if(name.isEmpty()){
            throw new IllegalArgumentException("Field \"Name\" cannot be empty.\n");
        }
        if(cost < 0){
            throw new IllegalArgumentException("Cost cannot be less than zero.\n");
        }
        if(price < 0){
            throw new IllegalArgumentException("Price cannot be less than zero.\n");
        }

        this.name = name;
        this.description = description;
        this.cost = cost;
        this.price = price;
    }

    public String name(){
        return name; 
    }

    public String description(){
        return description;
    }

    public int price(){
        return price;
    }

    public int cost(){
        return cost;
    }

    @Override
    public String toString(){
        return name;
    }


}