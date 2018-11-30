package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.util.ArrayList;

public class MonteurScene {
    ArrayList<Region> bas = new ArrayList<Region>();
    Region centre;
    int largeur = 800;
    int hauteur = 400;

    public MonteurScene setLargeur(int l){
        largeur=l;
        return this;
    }

    public MonteurScene setHauteur(int l) {
        hauteur=l;
        return this;
    }

    public MonteurScene setCentre(Region node) {
        centre = node;
        return this;
    }

    Scene retourneScene() {
        assert (centre !=null);
        GridPane gridPane = new GridPane();

        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(centre,0,0);
        gridPane.setMinSize(largeur/4, hauteur/8);
        centre.setMinSize(largeur/4, hauteur/8);

        //Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));


        return new Scene(gridPane,largeur,hauteur);

    }
}

