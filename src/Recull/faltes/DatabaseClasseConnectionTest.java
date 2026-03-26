
package Recull.faltes;

import processing.core.PApplet;

public class DatabaseClasseConnectionTest extends PApplet {

    public static DataBase db;

    public static void main(String[] args) {
        db = new DataBase("admin", "12345", "Prova base de dades");
        db.connect();

        String s = db.getInfo("client", "Nom", "DNI", "41690391L");
        System.out.println(s);

        int n  = db.getNumFilesTaula("client");
        System.out.printf("Hi ha  %d clients. \n", n);


        String[] noms= db.getInfoArray("client", "Nom");
        for(int i=0; i< noms.length; i++){
            System.out.println(noms[i]);
        }

        String[] Nom = db.getNomTotsClients();
        db.printArray1D(Nom);

        String[][] infoClients = db.getInfoTotsClients();
        db.printArray2D(infoClients);

    }


}
