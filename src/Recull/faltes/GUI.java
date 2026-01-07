package Recull.faltes;

import static Recull.faltes.Mides.*;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import Recull.faltes.Button;
import Recull.faltes.TextList1D;
import processing.core.PApplet;


public class GUI {

    Colors colors;

    PImage logo, logo2 ;

    public PagedTable tableHistorial;

    //Button
    public Button b1, b2, b3, btable1, btable2;

    //Enumerat de les pantalles de l'app
    public enum PANTALLA {LOGIN, FORMULARI, HISTORIAL, ESTADISTIQUES, COMANDA};

    //Pantalla actual
    public PANTALLA pantallaActual;

    //Text Field
    public static TextField text1, text2, text3, text4;


    // Dimensions dels botons
    float buttonW = 60, buttonH = 60;

    // Taula Paginada
    PagedTable t, t1, t2, t3, t4, t5, t6, t7, tc1, tc2, tc3;

    public int taulaActiva = -1;

    // Dimensions de la taula
    float tableW = 800, tableH = 300;

    // Número de files (capçalera inclosa) i columnes de la taula
    int files = 8, columnes = 4;

    int files1 = 4, columnes1 = 2;

    // Títols de les columnes
    String[] headers = {"Data", " Medicament ", "Causa de falta","Resolució"};
    String[] headers1 = {"Data", " Medicament "};

    // Amplades de les columnes
    float[] colWidths = {20, 30, 30, 20};
    float[] colWidths1 = {20, 80};

    // Dades de la taula
    String[][] info = {
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
    };

    String[][] info1 = {
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
    };

    String[][] info2 = {
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
    };

    String[][] info3 = {
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
    };

    String[][] info4 = {
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
    };

    String[][] info5 = {
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
    };

    String[][] info6 = {
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
    };

    String[][] info7 = {
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
            {"__/__/__", "Nom Medicament", "Causa de falta", "Resolució"},
    };

    String[][] infoc1 = {
            {"Nom producte", "Nombre faltes"},
            {"Nom producte", "Nombre faltes"},
            {"Nom producte", "Nombre faltes"},
            {"Nom producte", "Nombre faltes"},
            {"Nom producte", "Nombre faltes"},
            {"Nom producte", "Nombre faltes"},
            {"Nom producte", "Nombre faltes"},
            {"Nom producte", "Nombre faltes"},
            {"Nom producte", "Nombre faltes"},
            {"Nom producte", "Nombre faltes"},
    };

    String[][] infoc2 = {
            {"Nom producte", "Nombre faltes"},
            {"Nom producte", "Nombre faltes"},
            {"Nom producte", "Nombre faltes"},
            {"Nom producte", "Nombre faltes"},
            {"Nom producte", "Nombre faltes"},
            {"Nom producte", "Nombre faltes"},
            {"Nom producte", "Nombre faltes"},
            {"Nom producte", "Nombre faltes"},
            {"Nom producte", "Nombre faltes"},
            {"Nom producte", "Nombre faltes"},
    };

    String[][] infoc3 = {
            {"Nom producte", "Nombre faltes"},
            {"Nom producte", "Nombre faltes"},
            {"Nom producte", "Nombre faltes"},
            {"Nom producte", "Nombre faltes"},
            {"Nom producte", "Nombre faltes"},
            {"Nom producte", "Nombre faltes"},
            {"Nom producte", "Nombre faltes"},
            {"Nom producte", "Nombre faltes"},
            {"Nom producte", "Nombre faltes"},
            {"Nom producte", "Nombre faltes"},
    };






    //Select
    public String[]SelectValues1 = {"Formulari", "Historial", "Estadístiques", "Comanda"};
    public static Select s1;

    // Variables radio buttons
    RadioButton rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8,rb9, rb10, rb11, rb12, rb13, rb14, rb15;

    // Variable radio button group
    RadioButtonGroup rbg1, rbg2, rbg3;

    // Variables del color (RGB)
    float r, g, b;

    // Elements de la Interfície Gràfica (TextList)
    TextList1D tList;   // Llista de textos
    Button btl;         // Botons

    String[] listValues = {"Alemania", "Angola", "Canada", "Brasil"};
    String selectedText;

    // Dimensions del TextList i Botons
    float tListW = 600, tListH = 60;
    float buttonW1 = 120, buttonH1 = 60;

    // public RadioButton rb1, rb2;           // Radio buttons
   // public RadioButtonGroup rbGroup;

    public GUI(PApplet p5){
        pantallaActual = PANTALLA.LOGIN;

        colors = new Colors(p5);

        logo = p5.loadImage("data/logo.png");
        logo2 = p5.loadImage("data/logo2.png");
        //casa = p5.loadImage("data/casa.png");
        b1 = new Button(p5,"Login", p5.width/2-200,p5.height/2+200,400,50);
        b2 = new Button(p5,"envia", p5.width/2-200,p5.height/2+300,400,50);
        //b3 = new Button(p5,casa, p5.width/2, p5.height/2,50,50);


        text1 = new PassField(p5, p5.width/2-200, p5.height/2+145, 400,50);
        text2 = new TextField(p5, p5.width/2-200, p5.height/2+70, 400,50);
        text3 = new TextField(p5, p5.width/2-550, p5.height/2-300, 200,40);
        text4 = new TextField(p5, p5.width/2-550, p5.height/2-200, 200,40);
        s1 = new Select(SelectValues1, p5.width/2+425, p5.height/2-390,200,50);

        rb1 = new RadioButton(p5, p5.width/2,355,15);
        rb2 = new RadioButton(p5, 100,355,15);
        rb3 = new RadioButton(p5, 100,425,15);
        rb4 = new RadioButton(p5, p5.width/2,425,15);
        rb5 = new RadioButton(p5, 100,550,15);
        rb6 = new RadioButton(p5, p5.width/2,550,15);
        rb7 = new RadioButton(p5, 100,620,15);
        rb8 = new RadioButton(p5, 100,155,10);
        rb9 = new RadioButton(p5, 400,155,10);
        rb10 = new RadioButton(p5, 700,155,10);
        rb11 = new RadioButton(p5, 1000,155,10);
        rb12 = new RadioButton(p5, 100,255,10);
        rb13 = new RadioButton(p5, 400,255,10);
        rb14 = new RadioButton(p5, 700,255,10);
        rb15 = new RadioButton(p5, 1000,255,10);


        //Construcció del radio button group
        rbg1 = new RadioButtonGroup(4);
        rbg1.setRadioButtons(rb1, rb2, rb3, rb4);   // Format pels 3 radio buttons
        rbg1.setSelected(2);

        rbg2 = new RadioButtonGroup(3);
        rbg2.setRadioButtons(rb5, rb6, rb7);   // Format pels 3 radio buttons
        rbg2.setSelected(2);

        rbg3 = new RadioButtonGroup(8);
        rbg3.setRadioButtons(rb8, rb9, rb10, rb11, rb12, rb13, rb14, rb15);   // Format pels 3 radio buttons
        rbg3.setSelected(2);

        t = new PagedTable(files, columnes);
        t.setHeaders(headers);
        t.setData(info);
        t.setColumnWidths(colWidths);

        t1 = new PagedTable(files, columnes);
        t1.setHeaders(headers);
        t1.setData(info1);
        t1.setColumnWidths(colWidths);

        t2 = new PagedTable(files, columnes);
        t2.setHeaders(headers);
        t2.setData(info2);
        t2.setColumnWidths(colWidths);

        t3 = new PagedTable(files, columnes);
        t3.setHeaders(headers);
        t3.setData(info3);
        t3.setColumnWidths(colWidths);

        t4 = new PagedTable(files, columnes);
        t4.setHeaders(headers);
        t4.setData(info4);
        t4.setColumnWidths(colWidths);

        t5 = new PagedTable(files, columnes);
        t5.setHeaders(headers);
        t5.setData(info5);
        t5.setColumnWidths(colWidths);

        t6 = new PagedTable(files, columnes);
        t6.setHeaders(headers);
        t6.setData(info6);
        t6.setColumnWidths(colWidths);

        t7 = new PagedTable(files, columnes);
        t7.setHeaders(headers);
        t7.setData(info7);
        t7.setColumnWidths(colWidths);

        tc1 = new PagedTable(files1, columnes1);
        tc1.setHeaders(headers1);
        tc1.setData(infoc1);
        tc1.setColumnWidths(colWidths1);

        tc2 = new PagedTable(files1, columnes1);
        tc2.setHeaders(headers1);
        tc2.setData(infoc2);
        tc2.setColumnWidths(colWidths1);

        tc3 = new PagedTable(files1, columnes1);
        tc3.setHeaders(headers1);
        tc3.setData(infoc3);
        tc3.setColumnWidths(colWidths1);

        // Creació dels botons
        btable1 = new Button(p5, "NEXT", 25 + tableW/2 + buttonW/1.5f, 720, buttonW, buttonH);
        btable2 = new Button(p5, "PREV", 25 + tableW/2 - buttonW/1.5f, 720, buttonW, buttonH);



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

    public void dibujoPantallaFormulari(PApplet p5){
        p5.background (colors.getColorAt(0));
        logoPantallaHistorial(p5, logo2);
       // casaPantallaHistorial(p5, casa);
        b2.display(p5);
       // b3.display(p5);
        p5.textSize(15);
        p5.text("Nom medicament", p5.width/2-550, p5.height/2-310);
        p5.textSize(15);
        p5.text("Codi", p5.width/2-550, p5.height/2-210);
        text3.display(p5);
        text4.display(p5);
        s1.display(p5);
        rb1.display(p5);
        rb2.display(p5);
        rb3.display(p5);
        rb4.display(p5);
        rb5.display(p5);
        rb6.display(p5);
        rb7.display(p5);
        p5.textSize(20);
        p5.text("Problema subministrament", p5.width/2+25,361);
        p5.textSize(20);
        p5.text("Article nou", p5.width/2-515, 361);
        p5.textSize(20);
        p5.text("Article comanda externa", p5.width/2-515, 431);
        p5.textSize(20);
        p5.text("Trencament estoc", p5.width/2+25, 431);
        p5.textSize(20);
        p5.text("Substitució", p5.width/2+25,556);
        p5.textSize(20);
        p5.text("Demanat encàrrec", p5.width/2-515, 556);
        p5.textSize(20);
        p5.text("Res", p5.width/2-515, 626);


    }

    public void dibujoPantallaHistorial(PApplet p5) {
        p5.background(colors.getColorAt(0));
        logoPantallaHistorial(p5, logo2);
        s1.display(p5);

        // Dibuixem tots els radio buttons
        rb8.display(p5);
        rb9.display(p5);
        rb10.display(p5);
        rb11.display(p5);
        rb12.display(p5);
        rb13.display(p5);
        rb14.display(p5);
        rb15.display(p5);

        p5.textSize(20);
        p5.text("Farmacèutic", 120, 162);
        p5.text("Farmacèutic", 420, 162);
        p5.text("Farmacèutic", 720, 162);
        p5.text("Tècnic", 1020, 162);
        p5.text("Tècnic", 120, 262);
        p5.text("Tècnic", 420, 262);
        p5.text("Tècnic", 720, 262);
        p5.text("Tècnic", 1020, 262);

        // Només dibuixem la taula i els botons si hi ha taula activa
        if (taulaActiva != -1) {

            switch (taulaActiva) {
                case 0: t.display(p5, 100, 350, tableW, tableH); break;
                case 1: t1.display(p5, 100, 350, tableW, tableH); break;
                case 2: t2.display(p5, 100, 350, tableW, tableH); break;
                case 3: t3.display(p5, 100, 350, tableW, tableH); break;
                case 4: t4.display(p5, 100, 350, tableW, tableH); break;
                case 5: t5.display(p5, 100, 350, tableW, tableH); break;
                case 6: t6.display(p5, 100, 350, tableW, tableH); break;
                case 7: t7.display(p5, 100, 350, tableW, tableH); break;
            }

            // Mostrem els botons NEXT / PREV només si hi ha taula activa
            btable1.display(p5);
            btable2.display(p5);
        }

        s1.display(p5);
    }

    public void dibujoPantallaComanda(PApplet p5){
        p5.background(colors.getColorAt(0));
        logoPantallaHistorial(p5, logo2); //logo
        s1.display(p5); //Select



        btable1.display(p5);
        btable2.display(p5);

        tList.display(p5);
        btl.display(p5);

    }


    public void dibujoPantallaEstadistica(PApplet p5){
        p5.background (colors.getColorAt(0));
        logoPantallaHistorial(p5, logo2);
        s1.display(p5);

    }



    //Zones de la GUI

    public static void logoLogIn(PApplet p5, PImage logo){
        p5.imageMode(PConstants.CENTER);
        p5.image(logo, p5.width/2, p5.height/2-100);
    }

    public static void logoPantallaHistorial(PApplet p5, PImage logo2){
        p5.imageMode(PConstants.CENTER);
        p5.image(logo2, p5.width/2-550, p5.height/2-370, logo2.width*0.25f, logo2.height*0.25f);



    }
    //public static void casaPantallaHistorial(PApplet p5, PImage casa){
        //p5.imageMode(PConstants.CENTER);
       // p5.image(casa, p5.width/2+590, p5.height/2-370, casa.width*0.2f, casa.height*0.2f);

   // }

   // public static void casaPantallaFormulari(PApplet p5, PImage casa){
        //p5.imageMode(PConstants.CENTER);
        //p5.image(casa, p5.width/2+590, p5.height/2-370, casa.width*0.2f, casa.height*0.2f);

    //}

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