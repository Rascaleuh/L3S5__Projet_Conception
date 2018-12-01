package sample;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;


public class VueListeNiveaux {

    VBox vBox;
    ArrayList<Button> listeBoutonNiveau;


    public VueListeNiveaux(ArrayList<Niveau> listeNiveaux) {

        listeBoutonNiveau = new ArrayList<Button>();
        vBox = new VBox();

        vBox.setSpacing(8);

        Text title = new Text("NIVEAUX");
        vBox.getChildren().add(title);

        for( Niveau n : listeNiveaux) {
            System.out.println("iteration");
            //Creer un bouton avec le nom du niveau correspondant
            Button b = new Button(n.get_nom());

            listeBoutonNiveau.add(b); //Ajout à la liste de boutons
            vBox.getChildren().add(b); //Ajout du bouton à la VBox
        }
    }

    public void dessine() {

    }
}