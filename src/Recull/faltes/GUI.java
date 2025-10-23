package Recull.faltes;

import static Recull.faltes.Mides.*;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import Recull.faltes.Text_Field;

public class GUI {

    Colors colors;

    PImage logo, logo2;

    //Botons
    public static Botons b1, b2;

    //Enumerat de les pantalles de l'app
    public enum PANTALLA {LOGIN, INICIAL, HISTORIAL, ESTADISTIQUES, RECOMANATS};

    //Pantalla actual
    public static PANTALLA pantallaActual;

    //Text Field
    public static Text_Field text1, text2;

    public GUI(PApplet p5){
        pantallaActual = PANTALLA.LOGIN;

        colors = new Colors(p5);

        logo = p5.loadImage("data/logo.png");
        logo2 = p5.loadImage("data/logo2.png");
        b1 = new Botons(p5,"Login", p5.width/2-200,p5.height/2+200,400,50);
        b2 = new Botons(p5,"HISTORIAL", p5.width/2+200,p5.height/2-300,400,50);
        text1 = new Text_Field(p5, p5.width/2-200, p5.height/2+145, 400,50);
        text2 = new Text_Field(p5, p5.width/2-200, p5.height/2+70, 400,50);
    }
    //Pantalles GUI

    public void dibujoPantallaLogIn(PApplet p5){
        p5.background (colors.getColorAt(0));
        logoLogIn(p5, logo);
        b1.display(p5);
        text1.display(p5);
        text2.display(p5);
        p5.textSize(15);
        p5.text("contrasenya", p5.width/2-198, p5.height/2+140);
        p5.textSize(15);
        p5.text("usuari", p5.width/2-198, p5.height/2+65);

    }

    public void dibujoPantallaInicial(PApplet p5){
        p5.background (colors.getColorAt(0));
        logoPantallInicial(p5, logo2);
        b2.display(p5);
    }

    //Zones de la GUI

    public static void logoLogIn(PApplet p5, PImage logo){
        p5.imageMode(PConstants.CENTER);
        p5.image(logo, p5.width/2, p5.height/2-100);
    }

    public static void logoPantallInicial(PApplet p5, PImage logo2){
        p5.imageMode(PConstants.CENTER);
        p5.image(logo2, p5.width/2-500, p5.height/2-330, logo2.width*0.5f, logo2.height*0.5f);
    }

    public static void zonaLogo(PApplet p5){
        p5.fill(200,50,100);
        p5.rect(marginH, marginV, logoWidth, logoHeight);
        p5.fill(0);
        p5.text("LOGO", marginH + logoWidth/2, marginV + logoHeight/2);
    }

    public static void sideBar(PApplet p5){
        // Zona Sidebar ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        p5.fill(50,200,100);
        p5.rect(marginH, 2*marginV + logoHeight, sidebarWidth, sidebarHeight);
        p5.fill(0);
        p5.text("SIDEBAR", marginH + sidebarWidth/2, marginV + logoHeight + sidebarHeight/2);
    }

}