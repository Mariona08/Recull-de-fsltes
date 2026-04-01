package Recull.faltes;

import java.sql.*;

public class DataBase {

    // Variable de connexió a la BBDD
    Connection c;

    // Variable de consulta
    Statement query;

    // Dades de connexió (user, password, nom de la base de dades)
    String user, password, databaseName;

    // Estat de la connexió
    boolean connectat = false;

    public DataBase(String user, String password, String databaseName) {
        this.user = user;
        this.password = password;
        this.databaseName = databaseName;
    }

    public void connect() {
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:8889/" + databaseName, user, password);
            query = c.createStatement();
            System.out.println("Connectat a la BBDD! :) ");
            connectat = true;
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getInfo(String nomTaula, String nomColumna, String nomClau, String identificador) {
        try {
            String q = "SELECT " + nomColumna +
                    " FROM " + nomTaula +
                    " WHERE " + nomClau + " = '" + identificador + "' ";
            System.out.println(q);
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getString(nomColumna);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }

    public int getNumFilesTaula(String nomTaula) {
        String q = "SELECT COUNT(*) AS num FROM " + nomTaula;
        try {
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getInt("num");
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public String[] getInfoArray(String nomTaula, String nomColumna) {
        int n = getNumFilesTaula(nomTaula);
        String[] info = new String[n];
        String q = "SELECT " + nomColumna +
                " FROM " + nomTaula +
                " ORDER BY " + nomColumna + " ASC";
        System.out.println(q);
        try {
            ResultSet rs = query.executeQuery(q);
            int f = 0;
            while (rs.next()) {
                info[f] = rs.getString(1);
                f++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return info;
    }

    public void printArray1D(String[] info) {
        for (int i = 0; i < info.length; i++) {
            System.out.printf("%d: %s.\n", i, info[i]);
        }
    }

    public void printArray2D(String[][] info) {
        for (int i = 0; i < info.length; i++) {
            System.out.printf("%d", i);
            for (int j = 0; j < info.length; j++) {
                System.out.printf("%s \t", info[i][j]);
            }
        }
    }

    public Connection getConnection() {
        return c;
    }


    //FUnció q retorna el nom del client amb un cert DNI
    public String getNomClientAmbDNI(String dni) {
        String q = "SELECT Nom FROM client WHERE DNI ='" + dni + "'";
        System.out.println(q);
        try {
            ResultSet rs = query.executeQuery(q);
            rs.next();
            String nom = rs.getString("Nom");
            return nom;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public String[] getNomTotsClients() {
        String q = "SELECT Nom FROM client ORDER BY nom ASC";
        System.out.println(q);
        try {
            int numFiles = getNumFilesTaula("client");
            String[] info = new String[numFiles];
            ResultSet rs = query.executeQuery(q);
            int f = 0;
            while (rs.next()) {
                info[f] = rs.getString("Nom");
                f++;
            }
            return info;
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public String[][] getInfoTotsClients() {
        String q = "SELECT DNI, Nom FROM client ORDER BY Nom ASC";
        System.out.println(q);
        try {
            int numFiles = getNumFilesTaula("client");
            String[][] info = new String[numFiles][2];
            ResultSet rs = query.executeQuery(q);
            int f = 0;
            while (rs.next()) {
                info[f][0] = rs.getString("DNI");
                info[f][1] = rs.getString("Nom");
                f++;
            }
            return info;
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public String[][] getInfoTipusMedicaments() {
        String qf = "SELECT m.Nom AS NomMedicament, t.nom AS Tipus " +
                    "FROM Medicament m, Tipus t, Data d " +
                    "WHERE m.Tipus_Id = t.id " +
                    "WHERE m.Tipus_Id = t.id " +
                    "ORDER BY d.data ASC " ;
        System.out.println(qf);
        int nf = getNumFilesQuery(qf);
        String[][] info = new String[nf][3];

        String q = "SELECT m.Nom AS NomMedicament, t.nom AS Tipus " +
                   "FROM Medicament m, Tipus t, Data d " +
                   "WHERE m.Tipus_Id = t.id " +
                   "WHERE m.Tipus_Id = t.id " +
                   "ORDER BY d.data ASC " ;
        System.out.println(q);

        try{
            ResultSet rs = query.executeQuery(q);
            int f=0;
            while (rs.next()){
                info[f][0]= rs.getString("NomMedicament");
                info[f][1]= rs.getString("Tipus");
                info[f][2]= rs.getString("data");
                f++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return info;
    }

    public int getNumFilesQuery(String q){
        try{
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getInt('q');
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public String[][] ordenarMedicaments() {
        String q = "SELECT * FROM Medicament " +
                   "ORDER BY nom;";
        System.out.println(q);
        try {
            int numFiles = getNumFilesTaula("Medicament");
            String[][] info = new String[numFiles][2];
            ResultSet rs = query.executeQuery(q);
            int f = 0;
            while (rs.next()) {
                info[f][0] = rs.getString("numero");
                info[f][1] = rs.getString("id");
                f++;
            }
            return info;
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean loginCorrecte(String Nom, String contraseña){
        String q = "SELECT COUNT(*) AS N "+
                "FROM Usuari " +
                "WHERE Nom = '" + Nom + "'AND Contraseña ='" + contraseña + "'";

        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            rs.next();
            int n = rs.getInt("N");
            return(n==1);
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }

}


