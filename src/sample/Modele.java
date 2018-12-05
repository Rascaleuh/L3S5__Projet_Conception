package sample;

import javafx.scene.input.KeyCode;

public interface Modele {
    public Niveau getNiveau();
    public void move(KeyCode c);
    public void reset();
}
