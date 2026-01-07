package Recull.faltes;
import processing.core.PApplet;

public class  Recull_de_faltes extends PApplet {

    Fonts appFonts;
    GUI appGUI;
    PagedTable appPagedTable;



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
        appGUI.btl = new Button(this, "TRIA", 950, 100, 300, 40);

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

            case ESTADISTIQUES:
            appGUI.dibujoPantallaEstadistica(this);
            break;

            case COMANDA:
            appGUI.dibujoPantallaComanda(this);
            appGUI.tList.update(this);

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
        if( appGUI.btl.mouseOverButton(p5) || appGUI.tList.mouseOverButtons(p5)){
            cursor(HAND);
        }
        else {
            cursor(ARROW);
        }
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

        if(appGUI.pantallaActual == GUI.PANTALLA.COMANDA){

            // clicar dins el camp de text
            appGUI.tList.getTextField().isPressed(this);

            // clicar una opció de la llista
            appGUI.tList.buttonPressed(this);

            // botó TRIA
            if(appGUI.btl.mouseOverButton(this) && appGUI.btl.isEnabled()){
                appGUI.selectedText = appGUI.tList.getSelectedValue();
            }
        }
    }

}
