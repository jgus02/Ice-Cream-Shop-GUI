package gui;
public enum Screen{
    ICE_CREAM_FLAVORS("Ice Cream Flavors"),
    MIX_IN_FLAVORS("Mix-In Flavors"),
    CONTAINERS("Containers"),
    ORDERS("Orders");

   private String label;
   Screen(String label) 
   {
      this.label=label;
   }

   public String getLabel() 
   {
      return label;
   }
}