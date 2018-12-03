import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class ControllerDeplacement {

    IHMFX ihm;
    Niveau niveauActuel;

    ControllerDeplacement(IHMFX a_ihm) {
        ihm = a_ihm;
        a_ihm.stage.getScene().setOnKeyPressed(new ActionMove());
        niveauActuel = Modele.getModele().getNiveauActuel();

    }

    /* Event pour la selection du fichier */
    class ActionMove implements EventHandler<KeyEvent> {
        public void handle(KeyEvent event) {

            Deplacement.display(niveauActuel.get_level());

            if( event.getCode() == KeyCode.UP ) {
                System.out.println("up");
                Deplacement.move(1, niveauActuel.get_level(), niveauActuel.get_py(), niveauActuel.get_px());
            }
            else if( event.getCode() == KeyCode.DOWN ) {
                System.out.println("down");
                Deplacement.move(2, niveauActuel.get_level(), niveauActuel.get_py(), niveauActuel.get_px());
            }
            else if( event.getCode() == KeyCode.LEFT ) {
                System.out.println("left");
                Deplacement.move(3, niveauActuel.get_level(), niveauActuel.get_py(), niveauActuel.get_px());
            }
            else if( event.getCode() == KeyCode.RIGHT ) {
                System.out.println("right");
                Deplacement.move(4, niveauActuel.get_level(), niveauActuel.get_py(), niveauActuel.get_px());
            }

            ihm.actualise();
            niveauActuel.resetPos();

            //Verification victoire
            if(Deplacement.hasWin(niveauActuel.get_level()) )
                System.out.println("WIN !");

        }
    }
}


