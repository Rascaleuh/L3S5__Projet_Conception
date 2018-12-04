package sample;

import java.util.ArrayList;
import java.util.Arrays;

public class Niveau {
    public String nom;
    public ArrayList<ArrayList<Integer>> level;
    public int posx;
    public int posy;

    public Niveau(String _nom){
        nom = _nom;
        level = new ArrayList<ArrayList<Integer>>();
        reset();


    }

    public Niveau(String _nom, ArrayList<ArrayList<Integer>> clone, int _posx, int _posy) {
        nom = _nom;
        level = clone;
        posx = _posy;
        posy = _posx;
    }

    public Niveau(Niveau niveau) {
        nom = niveau.nom;
        level = new ArrayList<ArrayList<Integer>>();
        // Copie du level
        for(int i=0; i < niveau.get_level().size(); i++ ){
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            for(int j=0; j < niveau.get_level().get(i).size(); j++){
                tmp.add(niveau.get_level().get(i).get(j));
            }
            level.add(tmp);
        }
        posx = niveau.posx;
        posy = niveau.posy;
    }

    public void reset(){

    }

    public void afficher(){
        System.out.println("Nom : " + nom);
        System.out.println("Pos départ : ("+posx+","+posy+")");
        for(int i=0; i < level.size(); i++){
            for(int j=0; j < level.get(i).size(); j++){
                System.out.print(level.get(i).get(j));
            }
            System.out.println();
        }
    }

    public ArrayList<ArrayList<Integer>> get_level(){
        return level;
    }

    public int move(int x, int y){
        //TODO CHECK LES CAISSES ET POUSSER 36 42
        //TODO NE PAS EFFACER LES GOALS
        //TODO ET LE RESTE AUSSI
        System.out.println("Movement ("+posx+","+posy+") vers ("+(posx+x)+","+(posy+y)+")");
        int tailleX = level.size();;
        int tailleY = level.get(posx).size();

        //Check si on sort pas de la taille des arraylist
        if (posx+x >= tailleX || posy+y >= tailleY){
            return 0;
        }

        //Check si le prochain move est un mur
        if (level.get(posx+x).get(posy+y) == 35){
            System.out.println("On a un mur en ("+(posx+x)+","+(posy+y)+")");
            return 0;
        }

        level.get(posx).set(posy, 32);
        posx += x;
        posy += y;
        level.get(posx).set(posy, 64);
        return 1;
    }



}
