package sample;

import javafx.scene.input.KeyCode;

import java.util.ArrayList;

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

    public void move(KeyCode c) {
        facadeModele.move(c);
        notifie();
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
