package Recull.faltes;
import processing.core.PApplet;

public class  Recull_de_faltes extends PApplet {

    Fonts appFonts;
    GUI appGUI;
    PagedTable appPagedTable;
    LinesDiagram grafica;
    boolean loginWrong= false;
    DataBase db;

    public static void main(String[] args) {
        PApplet.main("Recull.faltes.Recull_de_faltes");
    }

    public void settings(){
        fullScreen();
    }
    public void setup(){


        db = new DataBase("admin", "12345", "medicaments");
        db.connect();

        appFonts = new Fonts(this);
        appGUI = new GUI(this, db);

        // Creació de la Llista de Textos
        appGUI.tList = new TextList1D(this, appGUI.listValues, 50, 100, 300, 40);
        // Creació del Botó
        appGUI.btl = new Button(this, "TRIA", 950, 110, 275, 40);

        grafica = new LinesDiagram(75, 250, 1100, 500);

        grafica.setTexts(new String[]{
                "Gen", "Feb", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Sept", "Oct", "Nov", "Dec"
        });

        /*grafica.setValues(new float[]{
                12, 18, 10, 25, 30, 22, 15, 28
        });*/

        grafica.setColors(color(0, 100, 200));
        grafica.setColors(appGUI.colors.getColorAt(1));


        grafica.values = new float[]{10, 20, 5, 15, 30, 25, 10, 5, 20, 15, 10, 5};

        // Construcció dels checkboxes
        appGUI.cb1 = new CheckBox(this, 240,110,20);
        appGUI.cb2 = new CheckBox(this, 240,175,20);
        appGUI.cb3 = new CheckBox(this, 460,110,20);
        appGUI.cb4 = new CheckBox(this, 460,175,20);
        appGUI.cb5 = new CheckBox(this, 680,110,20);
        appGUI.cb6 = new CheckBox(this, 680,175,20);
        appGUI.cb7 = new CheckBox(this, 900,110,20);
        appGUI.cb8 = new CheckBox(this, 900,175,20);
        appGUI.cb9 = new CheckBox(this, 1120,110,20);

    }

    public void draw(){
        // Dibuixa el fons (blanc)

        background(0);

        textFont(appFonts.getFirstTipografia());
        text("Titulo de la App", 50, 200);

        fill(50);
        textFont(appFonts.getSecondTipografia());
        text("Subtitulo de la App", 50, 250);

        fill(55,0,0);
        textFont(appFonts.getThirdTipografia());
        text("Paragrafo de la App", 50,300);

        // Dibuixa la pantalla corresponent
        switch(appGUI.pantallaActual) {
            case LOGIN:
                appGUI.dibujoPantallaLogIn(this);
                break;

            case HISTORIAL:
                appGUI.dibujoPantallaHistorial(this);
                break;

            case FORMULARI:
                appGUI.dibujoPantallaFormulari(this);
                break;


            case COMANDA:
                appGUI.dibujoPantallaComanda(this);

                break;

            case ESTADISTIQUES:
                appGUI.dibujoPantallaEstadistica((PApplet)this, grafica);
                break;

        }

        // Mostra la paleta de colors
        //appPaleta.displayPaleta(this, 100,100,width-200);
        appFonts.displayTipografia(this, 100,400,500);


        // Actualitza el cursor
        updateCursor(this);

        // Mostra la informació seleccionada
        if(appGUI.selectedText!=null){
            pushStyle();
            textAlign(CENTER); fill(0);
            text(appGUI.selectedText, width/2, height/2);
            popStyle();
        }



    }

    void updateCursor(PApplet p5){

    }
    //KEYBOARD interaction
    public void keyTyped(){
        GUI.text1.keyTyped(key);
        GUI.text2.keyTyped(key);
    }

    void resetFormulari() {
        // Camps de text
        GUI.text3.setText("");
        GUI.text4.setText("");

        // Radio buttons (tipus)
        appGUI.rb1.setChecked(false);
        appGUI.rb2.setChecked(false);
        appGUI.rb3.setChecked(false);
        appGUI.rb4.setChecked(false);

        // Radio buttons (resolució)
        appGUI.rb5.setChecked(false);
        appGUI.rb6.setChecked(false);
        appGUI.rb7.setChecked(false);

        // Select (tipus medicament)
        appGUI.sComandaFormulari.clearSelection();
    }

    public void keyPressed(){


        if(appGUI.pantallaActual==GUI.PANTALLA.LOGIN) {
            GUI.text1.keyPressed(keyCode);
            GUI.text2.keyPressed(keyCode);
        }
        if(appGUI.pantallaActual== GUI.PANTALLA.FORMULARI) {
            GUI.text3.keyPressed(key, keyCode);
            GUI.text4.keyPressed(key, keyCode);
        }

        if(keyCode==LEFT){
            appPagedTable.prevPage();
        }
        else if(keyCode==RIGHT){
            appPagedTable.nextPage();
        }

        if(appGUI.tList.getTextField().mouseOverTextField(this)){
            appGUI.tList.getTextField().keyPressed(key, (int)keyCode);
            appGUI.tList.update(this);
        }


    }

    public void mousePressed() {

        // ---------- BOTONS LOGIN ----------
        if (appGUI.pantallaActual == GUI.PANTALLA.LOGIN) {
            if(appGUI.b1.mouseOverButton(this)) {
                String nom = GUI.text2.getText();
                String contraseña = GUI.text1.getText();
                if(db.loginCorrecte(nom, contraseña)){
                    println("LOGIN Ok");
                    GUI.usuariActual = nom;
                    appGUI.pantallaActual = GUI.PANTALLA.FORMULARI;
                } else {
                    println("LOGIN WRONG");
                    loginWrong = true;
                }
            }
        }


        // ---------- BOTÓ GUARDAR ----------
        if(appGUI.b2.mouseOverButton(this)) {
            try {
                String nom = GUI.text3.getText();
                String codi = GUI.text4.getText();

                String tipus = "";
                if (appGUI.rb1.isChecked()) tipus = "Problema subministrament";
                else if (appGUI.rb2.isChecked()) tipus = "Article nou";
                else if (appGUI.rb3.isChecked()) tipus = "Article comanda externa";
                else if (appGUI.rb4.isChecked()) tipus = "Trencament estoc";

                String resolucio = "";
                if (appGUI.rb5.isChecked()) resolucio = "Substitució";
                else if (appGUI.rb6.isChecked()) resolucio = "Demanat encàrrec";
                else if (appGUI.rb7.isChecked()) resolucio = "Res";

                String tipusMedic = appGUI.sComandaFormulari.getSelectedValue();

                String sql = "INSERT INTO incidencies(nom_medicament, codi, tipus_falta, resolucio, usuari, tipus_medicament, data_registre) VALUES (?, ?, ?, ?, ?, ?, NOW())";
                java.sql.PreparedStatement ps = db.getConnection().prepareStatement(sql);

                ps.setString(1, nom);
                ps.setString(2, codi);
                ps.setString(3, tipus);
                ps.setString(4, resolucio);
                ps.setString(5, GUI.usuariActual);
                ps.setString(6, tipusMedic);

                ps.executeUpdate();
                println("GUARDAT A MYSQL");
                resetFormulari();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // ---------- TEXTFIELDS ----------
        GUI.text1.isPressed(this);
        GUI.text2.isPressed(this);
        GUI.text3.isPressed(this);
        GUI.text4.isPressed(this);

        // ---------- RADIOBUTTONS ----------
        appGUI.rbg1.updateOnClick(this);
        appGUI.rbg2.updateOnClick(this);
        appGUI.rbg3.updateOnClick(this);

        // ---------- TAULES ----------
        if (appGUI.rb8.isChecked()) appGUI.taulaActiva = 0;
        else if (appGUI.rb9.isChecked()) appGUI.taulaActiva = 1;
        else if (appGUI.rb10.isChecked()) appGUI.taulaActiva = 2;
        else if (appGUI.rb11.isChecked()) appGUI.taulaActiva = 3;
        else if (appGUI.rb12.isChecked()) appGUI.taulaActiva = 4;
        else if (appGUI.rb13.isChecked()) appGUI.taulaActiva = 5;
        else if (appGUI.rb14.isChecked()) appGUI.taulaActiva = 6;
        else if (appGUI.rb15.isChecked()) appGUI.taulaActiva = 7;

        if (appGUI.btable1.mouseOverButton(this) && appGUI.btable1.isEnabled()) {
            switch (appGUI.taulaActiva) {
                case 0: appGUI.t.nextPage(); break;
                case 1: appGUI.t1.nextPage(); break;
                case 2: appGUI.t2.nextPage(); break;
                case 3: appGUI.t3.nextPage(); break;
                case 4: appGUI.t4.nextPage(); break;
                case 5: appGUI.t5.nextPage(); break;
                case 6: appGUI.t6.nextPage(); break;
                case 7: appGUI.t7.nextPage(); break;
            }
        } else if (appGUI.btable2.mouseOverButton(this) && appGUI.btable2.isEnabled()) {
            switch (appGUI.taulaActiva) {
                case 0: appGUI.t.prevPage(); break;
                case 1: appGUI.t1.prevPage(); break;
                case 2: appGUI.t2.prevPage(); break;
                case 3: appGUI.t3.prevPage(); break;
                case 4: appGUI.t4.prevPage(); break;
                case 5: appGUI.t5.prevPage(); break;
                case 6: appGUI.t6.prevPage(); break;
                case 7: appGUI.t7.prevPage(); break;
            }
        }

        // ---------- SELECTS (SOLAPAMENT CONTROLAT) ----------
        boolean clickGestionat = false;

        // SELECT PRINCIPAL
        if(!clickGestionat && GUI.s1.mouseOverSelect(this)) {
            clickGestionat = true;

            if (GUI.s1.isCollapsed()) {
                GUI.s1.toggle();
            } else {
                GUI.s1.update(this);
                GUI.s1.setCollapsed(true);

                String val = GUI.s1.getSelectedValue();
                println("Seleccionado: " + val);

                if (val.equals("Formulari")) appGUI.pantallaActual = GUI.PANTALLA.FORMULARI;
                else if (val.equals("Historial")) appGUI.pantallaActual = GUI.PANTALLA.HISTORIAL;
                else if (val.equals("Estadístiques")) appGUI.pantallaActual = GUI.PANTALLA.ESTADISTIQUES;
                else if (val.equals("Comanda")) appGUI.pantallaActual = GUI.PANTALLA.COMANDA;
            }
        } else if (!clickGestionat) {
            appGUI.s1.setCollapsed(true);
        }

        // SELECT FORMULARI
        if(!clickGestionat
                && appGUI.pantallaActual == GUI.PANTALLA.FORMULARI
                && appGUI.sComandaFormulari.mouseOverSelect(this)) {
            clickGestionat = true;

            if (appGUI.sComandaFormulari.isCollapsed()) {
                appGUI.sComandaFormulari.toggle();
            } else {
                appGUI.sComandaFormulari.update(this);
                appGUI.sComandaFormulari.setCollapsed(true);
            }
        } else if (!clickGestionat) {
            appGUI.sComandaFormulari.setCollapsed(true);
        }

        // SELECT ESTADÍSTIQUES (NOMÉS SI ESTÀ ACTIU)
        if(!clickGestionat
                && appGUI.pantallaActual == GUI.PANTALLA.ESTADISTIQUES
                && appGUI.modeEstadistiques == 2
                && appGUI.sComandaEstadistiques.mouseOverSelect(this)) {

            clickGestionat = true;

            if (appGUI.sComandaEstadistiques.isCollapsed()) {
                appGUI.sComandaEstadistiques.toggle();
            } else {
                appGUI.sComandaEstadistiques.update(this);
                appGUI.sComandaEstadistiques.setCollapsed(true);

                actualitzarDadesGrafica(); // <--- AFEGEIX AIXÒ AQUÍ
            }
        }

        // ---------- ESTADÍSTIQUES ----------
        if(appGUI.pantallaActual == GUI.PANTALLA.ESTADISTIQUES){

            if(appGUI.bPersonal.mouseOverButton(this)){
                appGUI.modeEstadistiques = 1;
                grafica.values = new float[12]; // Netegem per seguretat
                actualitzarDadesGrafica();
            }

            if(appGUI.bMedicaments.mouseOverButton(this)){
                appGUI.modeEstadistiques = 2;
                grafica.values = new float[12]; // Netegem per seguretat
                actualitzarDadesGrafica();
            }

            // CHECKBOXES (Cada vegada que fem toggle, actualitzem dades)
            if(appGUI.cb1.onMouseOver(this)) { appGUI.cb1.toggle(); actualitzarDadesGrafica(); }
            else if(appGUI.cb2.onMouseOver(this)) { appGUI.cb2.toggle(); actualitzarDadesGrafica(); }
            else if(appGUI.cb3.onMouseOver(this)) { appGUI.cb3.toggle(); actualitzarDadesGrafica(); }
            else if(appGUI.cb4.onMouseOver(this)) { appGUI.cb4.toggle(); actualitzarDadesGrafica(); }
            else if(appGUI.cb5.onMouseOver(this)) { appGUI.cb5.toggle(); actualitzarDadesGrafica(); }
            else if(appGUI.cb6.onMouseOver(this)) { appGUI.cb6.toggle(); actualitzarDadesGrafica(); }
            else if(appGUI.cb7.onMouseOver(this)) { appGUI.cb7.toggle(); actualitzarDadesGrafica(); }
            else if(appGUI.cb8.onMouseOver(this)) { appGUI.cb8.toggle(); actualitzarDadesGrafica(); }
            else if(appGUI.cb9.onMouseOver(this)) {
                // Lògica de "Tots"
                boolean estatNou = !appGUI.cb9.isChecked();
                appGUI.cb9.setChecked(estatNou);
                appGUI.cb1.setChecked(estatNou);
                appGUI.cb2.setChecked(estatNou);
                appGUI.cb3.setChecked(estatNou);
                appGUI.cb4.setChecked(estatNou);
                appGUI.cb5.setChecked(estatNou);
                appGUI.cb6.setChecked(estatNou);
                appGUI.cb7.setChecked(estatNou);
                appGUI.cb8.setChecked(estatNou);

                actualitzarDadesGrafica(); // Actualitzem un sol cop després de canviar-los tots
            }
        }

        // ---------- COMANDA ----------
        if(appGUI.pantallaActual == GUI.PANTALLA.COMANDA){

            appGUI.tList.getTextField().isPressed(this);
            appGUI.tList.buttonPressed(this);

            if(appGUI.btl.mouseOverButton(this) && appGUI.btl.isEnabled()){
                String seleccionat = appGUI.tList.getTextField().text;

                if(seleccionat != null && !seleccionat.equals("")){
                    db.eliminarComanda(seleccionat);

                    appGUI.infoComanda = db.getMedicamentsComanda();
                    appGUI.tcomanda.setData(appGUI.infoComanda);

                    appGUI.listValues = db.getNomsMedicamentsComanda();
                    appGUI.tList.setValues(appGUI.listValues);

                    appGUI.tList.getTextField().text = "";
                }
            }

            if(appGUI.btable1.mouseOverButton(this) && appGUI.btable1.isEnabled()){
                appGUI.tcomanda.nextPage();
            } else if(appGUI.btable2.mouseOverButton(this) && appGUI.btable2.isEnabled()){
                appGUI.tcomanda.prevPage();
            }
        }
    }

    void actualitzarDadesGrafica() {
        if (appGUI.modeEstadistiques == 1) {
            // Mode Personal
            String[] seleccionats = appGUI.getUsuarisSeleccionats();
            if (seleccionats.length > 0) {
                grafica.values = db.getIncidenciesPerMes(seleccionats);
            } else {
                grafica.values = new float[12]; // Gràfica buida si no hi ha ningú
            }
        } else if (appGUI.modeEstadistiques == 2) {
            // Mode Medicament
            String tipus = appGUI.sComandaEstadistiques.getSelectedValue();
            if (tipus != null && !tipus.equals("")) {
                grafica.values = db.getIncidenciesPerMesItipus(tipus);
            }
        }
    }

}