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
        switch(GUI.pantallaActual) {
            case LOGIN:
                appGUI.dibujoPantallaLogIn(this);
                break;

            case HISTORIAL:
                appGUI.dibujoPantallaHistorial(this);
                break;

            case FORMULARI:
            appGUI.dibujoPantallaFormulari(this);
            break;
        }

        // Mostra la paleta de colors
        //appPaleta.displayPaleta(this, 100,100,width-200);
        appFonts.displayTipografia(this, 100,400,500);

    }

    //KEYBOARD interaction

    public void keyPressed(){
        if(key=='0'){
            GUI.pantallaActual = GUI.PANTALLA.LOGIN;
        }
        else if(key=='1'){
            GUI.pantallaActual = GUI.PANTALLA.FORMULARI;
        }
        else if(key=='2'){
            GUI.pantallaActual = GUI.PANTALLA.HISTORIAL;
        }
        if(GUI.pantallaActual==GUI.PANTALLA.LOGIN) {
            GUI.text1.keyPressed(key, keyCode);
            GUI.text2.keyPressed(key, keyCode);
        }
        if(GUI.pantallaActual==GUI.PANTALLA.FORMULARI) {
            GUI.text3.keyPressed(key, keyCode);
        }

    }

    public void mousePressed(){
        if(GUI.b1.mouseOverButton(this)){
            println("B1 has been pressed!!");
            GUI.pantallaActual = GUI.PANTALLA.FORMULARI;
        }
        if(GUI.b2.mouseOverButton(this)){
            println("B2 has been pressed!!");
        }
        GUI.text1.isPressed(this);
        GUI.text2.isPressed(this);
        GUI.text3.isPressed(this);

        if (GUI.s1.mouseOverSelect(this)) {
            if (GUI.s1.isCollapsed()) {
                GUI.s1.toggle();
            } else {
                GUI.s1.update(this);
                GUI.s1.setCollapsed(true);
                println("Seleccionado: " + GUI.s1.getSelectedValue());
            }
            if (GUI.s1.getSelectedValue().equals("Formulari")) {
                GUI.pantallaActual = GUI.PANTALLA.FORMULARI;
            } else if (GUI.s1.getSelectedValue().equals("Historial")) {
                GUI.pantallaActual = GUI.PANTALLA.HISTORIAL;
            } else if (GUI.s1.getSelectedValue().equals("Estadístiques")) {
                GUI.pantallaActual = GUI.PANTALLA.ESTADISTIQUES;
            }
        }
        } else {
            GUI.s1.setCollapsed(true);
        }


    }


}