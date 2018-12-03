import java.util.ArrayList;

public class Deplacement {

    public static Integer mur = 35;
    public static Integer caisse = 36;
    public static Integer vide = 32;
    public static Integer caisseSurGoal = 42;
    public static Integer goal = 46;
    public static Integer personnage = 64;
    public static Integer personnageSurGoal = 43;


    public Deplacement() {

    }

    public static boolean hasWin(ArrayList<ArrayList<Integer>> niveau) {

        for (int i = 0; i < niveau.size(); i++)
            for (int j = 0; j < niveau.get(i).size(); j++)
                if( niveau.get(i).get(j).equals(goal) || niveau.get(i).get(j).equals(personnageSurGoal))
                    return false;


        return true;
    }


    public static void display(ArrayList<ArrayList<Integer>> t) {
        for (int i = 0; i < t.size(); i++) {
            for (int j = 0; j < t.get(i).size(); j++) {
                System.out.print(t.get(i).get(j));
            }
            System.out.println();
        }
    }

    public static boolean isValidMove(int dir, ArrayList<ArrayList<Integer>> niveau, int i, int j) {
        int nextPos = 0;
        int nextNextPos = 0;

        if (dir == 1) {
            nextPos = niveau.get(i - 1).get(j);
            System.out.println("i bounce = "+ i);
            if( i-2 >=0 )
                nextNextPos = niveau.get(i - 2).get(j);
        }
        else if (dir == 2) {
            nextPos = niveau.get(i + 1).get(j);
            if( i+2 < niveau.size())
                nextNextPos = niveau.get(i + 2).get(j);
        }
        else if( dir == 3 ) {
            nextPos = niveau.get(i).get(j - 1);
            if( j-2 >= 0 )
                nextNextPos = niveau.get(i).get(j - 2);
        }
        else if( dir == 4 ) {
            nextPos = niveau.get(i).get(j + 1);
            if( j-2 < niveau.get(i).size() )
                nextNextPos = niveau.get(i).get(j + 2);
        }

        if( nextPos == vide )
            return true;
        else if( nextPos == mur )
            return false;

        else if( nextPos == caisse || nextPos == caisseSurGoal ) {
            if (nextNextPos == caisse || nextNextPos == caisseSurGoal || nextNextPos == mur)
                return false;
        }


        return true;
    }

    //Dir : 1 = haut, 2 = bas, 3 = gauche, 4 = droite
    public static void move(int dir, ArrayList<ArrayList<Integer>> niveau, int i, int j) {

        System.out.println("i= "+ i + " j = " + j);
        if(!Deplacement.isValidMove(dir, niveau, i, j))
            return;

        int nextPos = 0;
        int nextNextPos = 0;

        //Le perso peut etre sur une case goal, il faut le prendre en compte dans les conditions de remplacement
        int casePrecedente = vide;
        if( niveau.get(i).get(j) == personnageSurGoal )
            casePrecedente = goal;

        if (dir == 1) {
            nextPos = niveau.get(i - 1).get(j);
            nextNextPos = niveau.get(i - 2).get(j);
        }
        else if (dir == 2) {
            nextPos = niveau.get(i + 1).get(j);
            nextNextPos = niveau.get(i + 2).get(j);
        }
        else if( dir == 3 ) {
            nextPos = niveau.get(i).get(j - 1);
            nextNextPos = niveau.get(i).get(j - 2);
        }
        else if( dir == 4 ) {
            nextPos = niveau.get(i).get(j + 1);
            nextNextPos = niveau.get(i).get(j + 2);
        }

        //Vide sur nextPos
        if( nextPos == vide ) {
            if (dir == 1)
                niveau.get(i - 1).set(j, personnage);
            else if (dir == 2)
                niveau.get(i + 1).set(j, personnage);
            else if( dir == 3 )
                niveau.get(i).set(j - 1, personnage);
            else if( dir == 4 )
                niveau.get(i).set(j + 1, personnage);
        }

        //Goal sur nextPos
        if( nextPos == goal ) {
            if (dir == 1)
                niveau.get(i - 1).set(j, personnageSurGoal);
            else if (dir == 2)
                niveau.get(i + 1).set(j, personnageSurGoal);
            else if( dir == 3 )
                niveau.get(i).set(j - 1, personnageSurGoal);
            else if( dir == 4 )
                niveau.get(i).set(j + 1, personnageSurGoal);
        }

        //Caisse sur nextPos et nextNextPos est vide
        if( nextPos == caisse && nextNextPos == vide ) {
            if (dir == 1) {
                niveau.get(i - 1).set(j, personnage);
                niveau.get(i - 2).set(j, caisse);
            }
            else if (dir == 2) {
                niveau.get(i + 1).set(j, personnage);
                niveau.get(i + 2).set(j, caisse);
            }
            else if( dir == 3 ) {
                niveau.get(i).set(j - 1, personnage);
                niveau.get(i).set(j - 2, caisse);
            }
            else if( dir == 4 ) {
                niveau.get(i).set(j + 1, personnage);
                niveau.get(i).set(j + 2, caisse);
            }
        }

        //Caisse sur nextPos et nextNextPos est un goal
        if( nextPos == caisse && nextNextPos == goal ) {
            if (dir == 1) {
                niveau.get(i - 1).set(j, personnage);
                niveau.get(i - 2).set(j, caisseSurGoal);
            }
            else if (dir == 2) {
                niveau.get(i + 1).set(j, personnage);
                niveau.get(i + 2).set(j, caisseSurGoal);
            }
            else if( dir == 3 ) {
                niveau.get(i).set(j - 1, personnage);
                niveau.get(i).set(j - 2, caisseSurGoal);
            }
            else if( dir == 4 ) {
                niveau.get(i).set(j + 1, personnage);
                niveau.get(i).set(j + 2, caisseSurGoal);
            }
        }
        //CaisseOnGoal sur nextPos et nextNextPos est un goal
        if( nextPos == caisseSurGoal && nextNextPos == goal ) {
            if (dir == 1) {
                niveau.get(i - 1).set(j, personnageSurGoal);
                niveau.get(i - 2).set(j, caisseSurGoal);
            }
            else if (dir == 2) {
                niveau.get(i + 1).set(j, personnageSurGoal);
                niveau.get(i + 2).set(j, caisseSurGoal);
            }
            else if( dir == 3 ) {
                niveau.get(i).set(j - 1, personnageSurGoal);
                niveau.get(i).set(j - 2, caisseSurGoal);
            }
            else if( dir == 4 ) {
                niveau.get(i).set(j + 1, personnageSurGoal);
                niveau.get(i).set(j + 2, caisseSurGoal);
            }
        }
        //CaisseOnGoal sur nextPos et nextNextPos est vide
        if( nextPos == caisseSurGoal && nextNextPos == vide ) {
            if (dir == 1) {
                niveau.get(i - 1).set(j, personnageSurGoal);
                niveau.get(i - 2).set(j, caisse);
            }
            else if (dir == 2) {
                niveau.get(i + 1).set(j, personnageSurGoal);
                niveau.get(i + 2).set(j, caisse);
            }
            else if( dir == 3 ) {
                niveau.get(i).set(j - 1, personnageSurGoal);
                niveau.get(i).set(j - 2, caisse);
            }
            else if( dir == 4 ) {
                niveau.get(i).set(j + 1, personnageSurGoal);
                niveau.get(i).set(j + 2, caisse);
            }
        }

        //Puis on remplace la case precedente par un goal ou par vide
        niveau.get(i).set(j, casePrecedente);
        System.out.println("i = "+ i + "j = " + j);

    }

}
