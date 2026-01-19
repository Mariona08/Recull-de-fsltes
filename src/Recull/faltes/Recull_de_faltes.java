package Recull.faltes;
import processing.core.PApplet;

public class  Recull_de_faltes extends PApplet {

    Fonts appFonts;
    GUI appGUI;
    PagedTable appPagedTable;
    LinesDiagram grafica;



    public static void main(String[] args) {
        PApplet.main("Recull.faltes.Recull_de_faltes");
    }

    public void settings(){
        fullScreen();
    }
    public void setup(){
        appFonts = new Fonts(this);
        appGUI = new GUI(this);
        // Creació de la Llista de Textos
        appGUI.tList = new TextList1D(this, appGUI.listValues, 50, 100, 300, 40);
        // Creació del Botó
        appGUI.btl = new Button(this, "TRIA", 950, 110, 275, 40);

        grafica = new LinesDiagram(200, 300, 900, 300);

        grafica.setTexts(new String[]{
                "Gen", "Feb", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago"
        });

        grafica.setValues(new float[]{
                12, 18, 10, 25, 30, 22, 15, 28
        });

        grafica.setColors(color(0, 100, 200));


        // Construcció dels checkboxes
        appGUI.cb1 = new CheckBox(this, 160,100,20);
        appGUI.cb2 = new CheckBox(this, 160,165,20);
        appGUI.cb3 = new CheckBox(this, 380,100,20);
        appGUI.cb4 = new CheckBox(this, 380,165,20);
        appGUI.cb5 = new CheckBox(this, 600,100,20);
        appGUI.cb6 = new CheckBox(this, 600,165,20);
        appGUI.cb7 = new CheckBox(this, 820,100,20);
        appGUI.cb8 = new CheckBox(this, 820,165,20);
        appGUI.cb9 = new CheckBox(this, 1040,100,20);

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

    public void keyPressed(){
        if(key=='0'){
            appGUI.pantallaActual = GUI.PANTALLA.LOGIN;
        }
        else if(key=='1'){
            appGUI.pantallaActual = GUI.PANTALLA.FORMULARI;
        }
        else if(key=='2'){
            appGUI.pantallaActual = GUI.PANTALLA.HISTORIAL;
        }
        else if(key=='3'){
            appGUI.pantallaActual = GUI.PANTALLA.COMANDA;
        }

        if(appGUI.pantallaActual==GUI.PANTALLA.LOGIN) {
            appGUI.text1.keyPressed(key, keyCode);
            appGUI.text2.keyPressed(key, keyCode);
        }
        if(appGUI.pantallaActual== GUI.PANTALLA.FORMULARI) {
            appGUI.text3.keyPressed(key, keyCode);
            appGUI.text4.keyPressed(key, keyCode);
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
        if(appGUI.b1.mouseOverButton(this)) {
            println("B1 has been pressed!!");
            appGUI.pantallaActual = GUI.PANTALLA.FORMULARI;
        }

        if(appGUI.b2.mouseOverButton(this)) {
            println("B2 has been pressed!!");
        }

        GUI.text1.isPressed(this);
        GUI.text2.isPressed(this);
        GUI.text3.isPressed(this);
        GUI.text4.isPressed(this);

        if (GUI.s1.mouseOverSelect(this)) {
            if (GUI.s1.isCollapsed()) {
                GUI.s1.toggle();
            } else {
                GUI.s1.update(this);
                GUI.s1.setCollapsed(true);
                println("Seleccionado: " + GUI.s1.getSelectedValue());
            }

            if (appGUI.s1.getSelectedValue().equals("Formulari")) {
                appGUI.pantallaActual = GUI.PANTALLA.FORMULARI;
            } else if (appGUI.s1.getSelectedValue().equals("Historial")) {
                appGUI.pantallaActual = GUI.PANTALLA.HISTORIAL;
            } else if (appGUI.s1.getSelectedValue().equals("Estadístiques")) {
                appGUI.pantallaActual = GUI.PANTALLA.ESTADISTIQUES;
            } else if (appGUI.s1.getSelectedValue().equals("Comanda")) {
                appGUI.pantallaActual = GUI.PANTALLA.COMANDA;
            }

        } else {
            appGUI.s1.setCollapsed(true);
        }

        if (appGUI.sComanda.mouseOverSelect(this)) {
            if (appGUI.sComanda.isCollapsed()) {
                appGUI.sComanda.toggle();
            } else {
                appGUI.sComanda.update(this);
                appGUI.sComanda.setCollapsed(true);
                println("Seleccionado: " + appGUI.sComanda.getSelectedValue());
            }
        } else {
            appGUI.sComanda.setCollapsed(true);
        }

        // Si pitjam sobre el radiobuttongroup
        appGUI.rbg1.updateOnClick(this);

        // Miram el seu valor, per actualitzar r,g i b
        appGUI.r = appGUI.rb1.isChecked() ? 255 : 0;
        appGUI.g = appGUI.rb2.isChecked() ? 255 : 0;
        appGUI.b = appGUI.rb3.isChecked() ? 255 : 0;
        appGUI.b = appGUI.rb4.isChecked() ? 255 : 0;

        appGUI.rbg2.updateOnClick(this);
        appGUI.rbg3.updateOnClick(this);

        // Miram el seu valor, per actualitzar r,g i b
        appGUI.r = appGUI.rb5.isChecked() ? 255 : 0;
        appGUI.g = appGUI.rb6.isChecked() ? 255 : 0;
        appGUI.b = appGUI.rb7.isChecked() ? 255 : 0;

        if (appGUI.rb8.isChecked()) {
            appGUI.taulaActiva = 0;
        } else if (appGUI.rb9.isChecked()) {
            appGUI.taulaActiva = 1;
        } else if (appGUI.rb10.isChecked()) {
            appGUI.taulaActiva = 2;
        } else if (appGUI.rb11.isChecked()) {
            appGUI.taulaActiva = 3;
        } else if (appGUI.rb12.isChecked()) {
            appGUI.taulaActiva = 4;
        } else if (appGUI.rb13.isChecked()) {
            appGUI.taulaActiva = 5;
        } else if (appGUI.rb14.isChecked()) {
            appGUI.taulaActiva = 6;
        } else if (appGUI.rb15.isChecked()) {
            appGUI.taulaActiva = 7;
        }

        if (appGUI.btable1.mouseOverButton(this) && appGUI.btable1.isEnabled()) {
            if (appGUI.taulaActiva == 0) {
                appGUI.t.nextPage();
            } else if (appGUI.taulaActiva == 1) {
                appGUI.t1.nextPage();
            } else if (appGUI.taulaActiva == 2) {
                appGUI.t2.nextPage();
            } else if (appGUI.taulaActiva == 3) {
                appGUI.t3.nextPage();
            } else if (appGUI.taulaActiva == 4) {
                appGUI.t4.nextPage();
            } else if (appGUI.taulaActiva == 5) {
                appGUI.t5.nextPage();
            } else if (appGUI.taulaActiva == 6) {
                appGUI.t6.nextPage();
            } else if (appGUI.taulaActiva == 7) {
                appGUI.t7.nextPage();
            }
        } else if (appGUI.btable2.mouseOverButton(this) && appGUI.btable2.isEnabled()) {
            if (appGUI.taulaActiva == 0) {
                appGUI.t.prevPage();
            } else if (appGUI.taulaActiva == 1) {
                appGUI.t1.prevPage();
            } else if (appGUI.taulaActiva == 2) {
                appGUI.t2.prevPage();
            } else if (appGUI.taulaActiva == 3) {
                appGUI.t3.prevPage();
            } else if (appGUI.taulaActiva == 4) {
                appGUI.t4.prevPage();
            } else if (appGUI.taulaActiva == 5) {
                appGUI.t5.prevPage();
            } else if (appGUI.taulaActiva == 6) {
                appGUI.t6.prevPage();
            } else if (appGUI.taulaActiva == 7) {
                appGUI.t7.prevPage();
            }
        }

        if(appGUI.pantallaActual == GUI.PANTALLA.ESTADISTIQUES){

            // Si pitjam sobre el checboxes
            if(appGUI.cb1.onMouseOver(this)){
                appGUI.cb1.toggle();
            }

            else if(appGUI.cb2.onMouseOver(this)){
                appGUI.cb2.toggle();
            }
            else if(appGUI.cb3.onMouseOver(this)){
                appGUI.cb3.toggle();
            }

            if(appGUI.cb4.onMouseOver(this)){
                appGUI.cb4.toggle();
            }

            else if(appGUI.cb5.onMouseOver(this)){
                appGUI.cb5.toggle();
            }
            else if(appGUI.cb6.onMouseOver(this)){
                appGUI.cb6.toggle();
            }
            if(appGUI.cb7.onMouseOver(this)){
                appGUI.cb7.toggle();
            }

            else if(appGUI.cb8.onMouseOver(this)){
                appGUI.cb8.toggle();
            }
            if (appGUI.cb9.onMouseOver(this)) {

                // comprovam l'estat actual de "Tots"
                if (appGUI.cb9.isChecked()) {
                    // si està activat, ho posem tot a false
                    appGUI.cb9.setChecked(false);
                    appGUI.cb1.setChecked(false);
                    appGUI.cb2.setChecked(false);
                    appGUI.cb3.setChecked(false);
                    appGUI.cb4.setChecked(false);
                    appGUI.cb5.setChecked(false);
                    appGUI.cb6.setChecked(false);
                    appGUI.cb7.setChecked(false);
                    appGUI.cb8.setChecked(false);
                } else {
                    // si està desactivat, ho posem tot a true
                    appGUI.cb9.setChecked(true);
                    appGUI.cb1.setChecked(true);
                    appGUI.cb2.setChecked(true);
                    appGUI.cb3.setChecked(true);
                    appGUI.cb4.setChecked(true);
                    appGUI.cb5.setChecked(true);
                    appGUI.cb6.setChecked(true);
                    appGUI.cb7.setChecked(true);
                    appGUI.cb8.setChecked(true);
                }
            }

            // Miram el seu valor, per actualitzar r,g i b
            appGUI.l = appGUI.cb1.isChecked() ? 255 : 0;
            appGUI.m = appGUI.cb2.isChecked() ? 255 : 0;
            appGUI.p = appGUI.cb3.isChecked() ? 255 : 0;
            appGUI.o = appGUI.cb4.isChecked() ? 255 : 0;
            appGUI.q = appGUI.cb5.isChecked() ? 255 : 0;
            appGUI.u = appGUI.cb6.isChecked() ? 255 : 0;
            appGUI.v = appGUI.cb7.isChecked() ? 255 : 0;
            appGUI.w = appGUI.cb8.isChecked() ? 255 : 0;
            appGUI.x = appGUI.cb8.isChecked() ? 255 : 0;
        }

        if(appGUI.pantallaActual == GUI.PANTALLA.COMANDA){

            // clicar dins el camp de text
            appGUI.tList.getTextField().isPressed(this);

            // clicar una opció de la llista
            appGUI.tList.buttonPressed(this);

            // botó TRIA
            if(appGUI.btl.mouseOverButton(this) && appGUI.btl.isEnabled()){
                println("Botó TRIA premut!");
                // Esborrem el text del TextList
                appGUI.tList.getTextField().text = "";
                appGUI.sComanda.clearSelection(); //select en blanc
            }

            // clicar dins el camp de text
            appGUI.tList.getTextField().isPressed(this);

            // clicar una opció de la llista
            appGUI.tList.buttonPressed(this);

            // botó TRIA
            if(appGUI.btl.mouseOverButton(this) && appGUI.btl.isEnabled()){
                println("Botó TRIA premut!");
                // Esborrem el text del TextList
                appGUI.tList.getTextField().text = "";
            }

            // NEXT / PREV de la taula comanda
            if(appGUI.btable1.mouseOverButton(this) && appGUI.btable1.isEnabled()){
                appGUI.tcomanda.nextPage();
            } else if(appGUI.btable2.mouseOverButton(this) && appGUI.btable2.isEnabled()){
                appGUI.tcomanda.prevPage();
            }

        }


    }

}
