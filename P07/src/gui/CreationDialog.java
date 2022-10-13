package gui;

public interface CreationDialog<T>{ 
    abstract void creationDialog();

    abstract void confirmChoice();

    public abstract T getChoice();
}