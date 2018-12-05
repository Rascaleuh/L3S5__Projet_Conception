package sample;

import javafx.scene.control.Label;

public class VueNbMoveIHMFX {
    Controleur controleur;
    Label labelMove = new Label("0");
    Label labelPoussée = new Label("0");
    CommandeNbMove commande;

    public VueNbMoveIHMFX(Controleur controleur){
        this.controleur = controleur;
        commande= controleur.commandeNbMove();
    }

    public void dessine(){
        int[] tab = commande.exec();
        labelMove.setText(tab[0]+"");
        labelPoussée.setText(tab[1]+"");
    }
}
