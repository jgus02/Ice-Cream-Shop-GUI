package gui;

import product.MixInFlavor;

public class CreateMixInDialog extends CreateFlavorDialog<MixInFlavor>{

    CreateMixInDialog(MainWin parent){
        super(parent, "Mix-In Flavor");
    }

    public MixInFlavor getChoice(){
        return new MixInFlavor(
                        super.names.getText(), 
                        super.descriptions.getText(), 
                        super.spinnerToInt(super.costs), 
                        super.spinnerToInt(super.prices)
        );
    }
}