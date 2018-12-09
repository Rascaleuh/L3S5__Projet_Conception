package sample;

import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Controleur implements Sujet {
    private static Controleur singleton;


    public static Controleur getControleur() {
        if (singleton == null)
            singleton = new Controleur(new FacadeModele());
        return singleton;
    }

    FacadeModele facadeModele;
    ArrayList<Observateur> observateurs = new ArrayList<Observateur>();

    private Controleur(FacadeModele facadeModele) {
        this.facadeModele = facadeModele;
    }

    public void abonne(Observateur observateur) {
        observateurs.add(observateur);
    }

    @Override
    public void notifie() {
        for (Observateur observateur:observateurs)
            observateur.actualise();
    }

    public void undo(){
        facadeModele.undo();
        notifie();
    }

    public void redo(){
        facadeModele.redo();
        notifie();
    }

    public void redoAll(){
        facadeModele.redoAll(singleton);

    }

    public void move(KeyCode c) {
        //TODO : Faire une pause avant de charger le prochain niveau, maybe un écran de win ? Ou on charge pas le niveau
        boolean win = facadeModele.move(c);
        notifie();
        if(win) {

            niveauSuivant();
        }
    }

    public void reset() {
        facadeModele.reset();
        notifie();
    }

    public CommandeNiveau commandeGetNiveau() {
        return new CommandeNiveau() {
            @Override
            public Niveau exec() {
                return facadeModele.getNiveau();
            }
        };
    }

    public CommandeNbMove commandeNbMove(){
        return new CommandeNbMove(){
            @Override
            public int[] exec(){
                int[] tab = new int[2];
                tab[0] = facadeModele.nbMove();
                tab[1] = facadeModele.nbPoussée();
                return tab;
            }
        };

    }

    public void set_liste_niveaux(String chemin) {
        facadeModele.set_liste_niveaux(chemin);
        notifie();
    }

    public void niveauPrécédent() {
        facadeModele.niveauPrécédent();
        notifie();
    }

    public void niveauSuivant() {
        facadeModele.niveauSuivant();
        notifie();
    }
}
