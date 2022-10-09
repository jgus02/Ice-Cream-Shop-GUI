package product;

public abstract class Item{
    protected String name;
    protected String description;
    protected int cost;
    protected int price;

    public Item(String name, String description, int cost, int price){
        dataValidation(name,description,cost,price);

        this.name           = name; //does this make me code better
        this.description    = description;
        this.cost           = cost;
        this.price          = price;
    }

    private void dataValidation(String name, String description, int cost, int price){
        if(name.isEmpty()){
            throw new IllegalIceCreamException("Field \"name\" cannot be empty.\n");
        }
        if(description.isEmpty()){
            throw new IllegalIceCreamException("Field \"description\" cannot be empty.\n");
        }
        if(cost < 0){
            throw new IllegalIceCreamException("Cost cannot be negative.\n");
        }
        if((price < cost)){
            throw new IllegalIceCreamException("Price cannot be less than cost.\n");
        }
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

    public class IllegalIceCreamException extends IllegalArgumentException{
        public IllegalIceCreamException(String message){
            super(message);
        }
    }

}
