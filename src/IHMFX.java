import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class IHMFX extends Application {

    VueIHMFX vue;

    public void actualise(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                vue.dessine();
            }
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        vue = new VueIHMFX();
        Controller controleur = new Controller(vue);
        /* montage de la scene */
        MonteurScene monteurScene = new MonteurScene();

        Scene scene = monteurScene.
                setCentre(vue.myButton).
                setLargeur(800).
                setHauteur(800).
                retourneScene();

        primaryStage.setScene(scene);

        primaryStage.setTitle("Sokoban");
        primaryStage.show();
    }

    public void lance() {
        launch(new String[]{});
    }
}


