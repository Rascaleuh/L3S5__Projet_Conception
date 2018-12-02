import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.io.FileNotFoundException;

public class ControllerVueListeNiveaux {

    VueListeNiveaux vueListeNiveaux;
    IHMFX ihm;

    ControllerVueListeNiveaux(IHMFX a_ihm, VueListeNiveaux a_vueListeNiveaux) {

        ihm = a_ihm;
        this.vueListeNiveaux = a_vueListeNiveaux;

        for( Button b : this.vueListeNiveaux.listeBoutonNiveau)
            b.setOnAction(new ActionSelectNiveau());
    }

    /* Event */
    class ActionSelectNiveau implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {

             String nomNiveau = ((Button)event.getSource()).getText();
             System.out.println("lancement de " + nomNiveau);

             /******   Afficher le niveau puis jouer ! ******/
                Modele modele = Modele.getModele();
                Niveau niveau = modele.getNiveau( nomNiveau );
                modele.setNiveauActuel( niveau );

            try {
                ihm.setVueNiveau();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
    }
}






