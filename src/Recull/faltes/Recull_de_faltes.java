package Recull.faltes;
import processing.core.PApplet;

public class Recull_de_faltes extends PApplet {

    Fonts appFonts;
    GUI appGUI;


    public static void main(String[] args) {
        PApplet.main("Recull.faltes.Recull_de_faltes");
    }

    public void settings(){
        fullScreen();
    }
    public void setup(){
        appFonts = new Fonts(this);
        appGUI = new GUI(this);

        rb1 = new RadioButton(this, 180,75,15);
        rb2 = new RadioButton(this, 180,175,15);
        rb3 = new RadioButton(this, 180,275,15);

        //Construcció del radio button group
        rbg = new RadioButtonGroup(3);
        rbg.setRadioButtons(rb1, rb2, rb3);   // Format pels 3 radio buttons
        rbg.setSelected(2);

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
        }

        // Mostra la paleta de colors
        //appPaleta.displayPaleta(this, 100,100,width-200);
        appFonts.displayTipografia(this, 100,400,500);

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
        if(appGUI.pantallaActual==GUI.PANTALLA.LOGIN) {
            appGUI.text1.keyPressed(key, keyCode);
            appGUI.text2.keyPressed(key, keyCode);
        }
        if(appGUI.pantallaActual== GUI.PANTALLA.FORMULARI) {
            appGUI.text3.keyPressed(key, keyCode);
            appGUI.text4.keyPressed(key, keyCode);
        }

    }

    public void mousePressed(){
        if(appGUI.b1.mouseOverButton(this)){
            println("B1 has been pressed!!");
            appGUI.pantallaActual = GUI.PANTALLA.FORMULARI;
        }
        if(appGUI.b2.mouseOverButton(this)){
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
            }
        }
        else {
            appGUI.s1.setCollapsed(true);
        }


    }


}