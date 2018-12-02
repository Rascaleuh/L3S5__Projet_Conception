package sample;


import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Vue
{
    Niveau niveau;
    Map<Integer, Image> images;
    GridPane gridPane = new GridPane();
    ArrayList<ArrayList<Pane>> listeCases;

    //** à décommenter
    //public Vue(Controller controller) throws FileNotFoundException
    public Vue() throws FileNotFoundException
    {
        //** à décommenter
        // niveau = controller.getNiveau();
        //** à suppr
        niveau = Modele.get_liste_niveaux("niveaux.xsb").get(0);
        //**

        images = new HashMap<>();
        images.put(35, new Image(new FileInputStream("img/mur.png"),80,80,false,false));
        images.put(36, new Image(new FileInputStream("img/caisse.png"),80,80,false,false));
        images.put(42, new Image(new FileInputStream("img/caisseV.png"),80,80,false,false));
        images.put(43, new Image(new FileInputStream("img/personnage_on_goal.png"),80,80,false,false));
        images.put(64, new Image(new FileInputStream("img/personnage.png"),80,80,false,false));
        images.put(32, new Image(new FileInputStream("img/vide.png"),80,80,false,false));
        images.put(46, new Image(new FileInputStream("img/goal.png"),80,80,false,false));

        listeCases = new ArrayList<>();
        for (int i = 0; i < niveau.get_level().size(); i++)
        {
            ArrayList<Pane> listeTmp = new ArrayList<>();
            for (int j = 0; j < niveau.get_level().get(i).size(); j++) //Pour chaque élément du tableau
            {
                Pane pane = new Pane();
                listeTmp.add(pane);
                gridPane.add(pane, j, i);
            }
            listeCases.add(listeTmp);
        }
        draw();
    }

    public void draw()
    {
        for (int i = 0; i < niveau.get_level().size(); i++)
        {
            for (int j = 0; j < niveau.get_level().get(i).size(); j++) //Pour chaque élément du tableau
            {
                listeCases.get(i).get(j).getChildren().setAll(new ImageView(images.get(niveau.get_level().get(i).get(j))));
            }
        }
    }
}
