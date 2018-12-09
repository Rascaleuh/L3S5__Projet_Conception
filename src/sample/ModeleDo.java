package sample;

import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ModeleDo implements Modele {
    Modele modeleNbMove;
    ArrayList<KeyCode> soko;
    int index = -1;  // Permet de savoir où est-ce qu'on est dans l'Arraylist

    public ModeleDo(ModeleNbMove modele){
        this.modeleNbMove = modele;
        soko = new ArrayList<KeyCode>();
    }

    @Override
    public Niveau getNiveau() {
        return modeleNbMove.getNiveau();
    }

    @Override
    public int move(KeyCode c) {
        int rt = modeleNbMove.move(c);
        if (rt == 1 || rt == 2){
            soko.add(c);
            index = soko.size()-1;
            System.out.println(soko.toString());
            System.out.println(" i = " + index + " | size = " + soko.size());
        }
        return rt;
    }

    @Override
    public void reset() {
        modeleNbMove.reset();
        soko = new ArrayList<KeyCode>();
    }

    @Override
    public void setActuel(int n) {
        modeleNbMove.setActuel(n);
        soko = new ArrayList<KeyCode>();
    }

    @Override
    public void set_liste_niveaux(String chemin) {
        modeleNbMove.set_liste_niveaux(chemin);
    }

    @Override
    public boolean win() {
        return modeleNbMove.win();
    }

    public void undo(){
        if( index >= 0 ){
            KeyCode undo = soko.get(index);
            int x = 0;
            int y = 0;

            switch(undo){
                case UP:
                    undo = KeyCode.DOWN;
                    x = 1;
                    break;
                case DOWN:
                    undo = KeyCode.UP;
                    x = -1;
                    break;
                case LEFT:
                    undo = KeyCode.RIGHT;
                    y = 1;
                    break;
                case RIGHT:
                    undo = KeyCode.LEFT;
                    y = -1;
                    break;
            }

            soko.add(undo);
            index --;
            getNiveau().moveCaisse(x, y);
        }
    }

    public void redo(){
        if(index+1 != soko.size()){
            index++;
            KeyCode redo = soko.get(index);
            soko.add(redo);
            modeleNbMove.move(redo);
        }
    }

    public void redoAll(){
        for(KeyCode c : soko){
            modeleNbMove.move(c);
        }
    }
}
