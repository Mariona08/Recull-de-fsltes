package Recull.faltes;

import static Recull.faltes.Mides.*;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class GUI {

    Colors colors;

    PImage logo;

    //Botons
    public static Botons b1, b2;

    //Enumerat de les pantalles de l'app
    public enum PANTALLA {LOGIN, INICIAL, SETTINGS, CREACIONCLUB, JUGADAS, PLANNING};

    //Pantalla actual
    public static PANTALLA pantallaActual;

    //Text Field
    public static Text_Field text1, text2;

    public GUI(PApplet p5){
        pantallaActual = PANTALLA.LOGIN;

        colors = new Colors(p5);

        logo = p5.loadImage("data/logo.png");
        b1 = new Botons(p5,"Login", p5.width/2-200,p5.height/2+200,400,50);
        text1 = new Text_Field(p5, p5.width/2-200, p5.height/2+125, 400,50);
        text2 = new Text_Field(p5, p5.width/2-200, p5.height/2+70, 400,50);
    }
    //Pantalles GUI

    public void dibujoPantallaLogIn(PApplet p5){
        p5.background (colors.getColorAt(0));
        logoLogIn(p5, logo);
        b1.display(p5);
        text1.display(p5);
        text2.display(p5);
        p5.textSize(20);
        p5.text("contrasenya", p5.width/2-190, p5.height/2+157);
        p5.textSize(20);
        p5.text("usuari", p5.width/2-190, p5.height/2+105);

    }

    public void dibujoPantallaInicial(PApplet p5){
        p5.background (colors.getColorAt(0));
    }

    //Zones de la GUI

    public static void logoLogIn(PApplet p5, PImage logo){
        p5.imageMode(PConstants.CENTER);
        p5.image(logo, p5.width/2, p5.height/2-100);
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