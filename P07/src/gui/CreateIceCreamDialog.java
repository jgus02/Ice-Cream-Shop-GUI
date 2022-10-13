package gui;

import product.IceCreamFlavor;

public class CreateIceCreamDialog extends CreateFlavorDialog<IceCreamFlavor>{

    public IceCreamFlavor getChoice(){
        return new IceCreamFlavor(
                        super.names.getText(), 
                        super.descriptions.getText(), 
                        super.spinnerToInt(super.costs), 
                        super.spinnerToInt(super.prices)
        );
    }

    CreateIceCreamDialog(MainWin parent){
        super(parent, "Ice Cream Flavor");
    }
}