/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.proyectotetris;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;


/**
 *
 * @author Max--
 */


public class GameTetris implements Serializable{
    private static final long serialVersionUID = 1L;//
    public String[][] tablero;
    public int ancho;
    public int alto;
    public boolean jugando;
    public boolean hayFicha;
    private boolean puedeMover;
    public boolean juegoTerminado;
    private int puntajeActual;
    public int puntajeTotal;
    public int turno;
    
    private int numFichas;
    private final String VACIO = ".";
    private final String LIMITE = "I";
    private final String FINAL = "=";

    public List<Object> fichaD = new ArrayList<>(); //ficha 1x1
    public List<Object> fichaI = new ArrayList<>(); //ficha 2x1
    public List<Object> fichaC = new ArrayList<>(); // ficha 2x2
    public List<Object> fichal = new ArrayList<>(); // ficha 3x1
    public List<Object> fichaT = new ArrayList<>(); // ficha 1x3 + 1x1
    public List<Object> fichaV = new ArrayList<>(); // ficha 2x2 - 1x1
    
    
    public int eligeFicha;
    public List<Object> fichaActual = new ArrayList<>();
    public int[][] bloquesFicha = {{0, 0}, {0, 0}, {0, 0}, {0, 0}};
    public transient Scanner sc = new Scanner(System.in);//
    Random rand = new Random();

    public void imprimir(String[][] tablero){
        for (int i = 0; i < this.alto; i++) {
            for (int j = 0; j < this.ancho; j++) {
                System.out.print("[");
                System.out.print(tablero[i][j]);
                System.out.print("]");
            } System.out.println("");
        }
    }
    public static void main(String[] args) {
        System.out.println("Hello World!");
        GameTetris g1 = new GameTetris();
        g1.iniciarJuego(8, 7);
    }
    public void iniciarJuego(int ancho, int alto){
        this.ancho = ancho;
        this.alto = alto;
        this.tablero = new String[this.alto][this.ancho];
        this.jugando = true;
        this.puntajeTotal= 0;
        this.turno=-1;
        for (int i = 0; i < this.alto-1; i++) {
            tablero[i][0] = LIMITE;
            for (int j = 1; j < this.ancho-1; j++) {
                tablero[i][j] = VACIO;
            } tablero[i][this.ancho-1] = LIMITE;
        }
        for (int j = 0; j < this.ancho; j++) {
            tablero[this.alto-1][j] = FINAL;
        }
        definirFichas();
        hayFicha=false;
//        while(true){
//            if (!hayFicha){
//                System.out.println("");
//                hayFicha=true;
//                borrarLineaHorizontal();
//                escogerSigFicha(fichaActual);
//                crearFicha(tablero,fichaActual,bloquesFicha);
//                imprimir(tablero);
//            }
//            if (juegoTerminado){
//                anunciarGanador();
//                break;
//            }
//            System.out.print("Puntaje:"+puntajeTotal+"Ingrese (a), (s), (d), (g), (u) o (e) para salir:");
//            String entrada = sc.next();
//            if(entrada.equals("a") || entrada.equals("s") || entrada.equals("d") || entrada.equals("g") || entrada.equals("u")  ){
//                switch (entrada) {
//                    case "a" -> moverIzquierda();
//                    case "s" -> moverAbajo();
//                    case "d" -> moverDerecha();
//                    case "g" -> girarHorario();
//                    case "u" -> girarAntihorario();
//                    default -> {
//                    }
//                }
//                imprimir(tablero);
//            }else if(entrada.equals("e")){
//                break;
//            }
//            
//        }
        
    }
    
    
    public void definirFichas(){
        fichaD.add("d");
        fichaD.add("s1");
        fichaD.add("h");
        fichaI.add("i");
        fichaI.add("s1");
        fichaI.add("h");
        fichaC.add("c");
        fichaC.add("s1");
        fichaC.add("h");
        fichal.add("l");
        fichal.add("s1");
        fichal.add("h");
        fichaT.add("t");
        fichaT.add("s1");
        fichaT.add("h");
        fichaV.add("v");
        fichaV.add("s1");
        fichaV.add("h");
        
    }
    //int f1x,int f1y,int f2x,int f2y,int f3x,int f3y,int f4x,int f4y

    public void crearFicha(String[][] tablero, List<Object> fichaActual, int[][] bloquesFicha){
        //coordenadas futuras 
//        tablero[bloquesFicha[0]][bloquesFicha[1]];

        for(int i=0; i< numFichas;i++){
            if(tablero[bloquesFicha[i][1]][bloquesFicha[i][0]].equals(VACIO)){
                puedeMover=true;
            }else{puedeMover=false;juegoTerminado=true;break;}
        }
        //comprueba coordenadas futuras en tablero si existe interseccion entonces puedeMover=false
        if(puedeMover){
            for(int i=0; i< numFichas;i++){
                tablero[bloquesFicha[i][1]][bloquesFicha[i][0]]=(String)fichaActual.get(0);
            }
        }
        if(!puedeMover){puedeMover=true;hayFicha=false;};
        
    }
    public void moverAbajo(){
        //coordenadas futuras 
//        tablero[bloquesFicha[0]][bloquesFicha[1]];
        for(int i=0; i< numFichas;i++){
            tablero[bloquesFicha[i][1]][bloquesFicha[i][0]]=VACIO;
        }
        for(int i=0; i< numFichas;i++){
            if(tablero[bloquesFicha[i][1]+1][bloquesFicha[i][0]].equals(VACIO)){
                puedeMover=true;
            }else{puedeMover=false;break;}
        }
        //comprueba coordenadas futuras en tablero si existe interseccion entonces puedeMover=false
        if(puedeMover){
            for(int i=0; i< numFichas;i++){
                bloquesFicha[i][1]+=1;
            }
        }else{
            puedeMover=true;hayFicha=false;
        }
        for(int i=0; i< numFichas;i++){
            tablero[bloquesFicha[i][1]][bloquesFicha[i][0]]=(String)fichaActual.get(0);
        }
        
    }
    public void moverDerecha(){
        for(int i=0; i< numFichas;i++){
            tablero[bloquesFicha[i][1]][bloquesFicha[i][0]]=VACIO;
        }
        for(int i=0; i< numFichas;i++){
            if(tablero[bloquesFicha[i][1]][bloquesFicha[i][0]+1].equals(VACIO)){
                puedeMover=true;
            }else{puedeMover=false;break;}
        }
        //comprueba coordenadas futuras en tablero si existe interseccion entonces puedeMover=false
        if(puedeMover){
            for(int i=0; i< numFichas;i++){
                bloquesFicha[i][0]+=1;
            }
        }
        for(int i=0; i< numFichas;i++){
            tablero[bloquesFicha[i][1]][bloquesFicha[i][0]]=(String)fichaActual.get(0);
        }
    }
    
    public void moverIzquierda(){
        for(int i=0; i< numFichas;i++){
            tablero[bloquesFicha[i][1]][bloquesFicha[i][0]]=VACIO;
        }
        for(int i=0; i< numFichas;i++){
            if(tablero[bloquesFicha[i][1]][bloquesFicha[i][0]-1].equals(VACIO)){
                puedeMover=true;
            }else{puedeMover=false;break;}
        }
        //comprueba coordenadas futuras en tablero si existe interseccion entonces puedeMover=false
        if(puedeMover){
            for(int i=0; i< numFichas;i++){
                bloquesFicha[i][0]-=1;
            }
        }
        for(int i=0; i< numFichas;i++){
            tablero[bloquesFicha[i][1]][bloquesFicha[i][0]]=(String)fichaActual.get(0);
        }
    }
    
    public void girarHorario(){
        for(int i=0; i< numFichas;i++){
            tablero[bloquesFicha[i][1]][bloquesFicha[i][0]]=VACIO;
        }
        switch ((String)fichaActual.get(0)) {
            case "i" -> {
                switch ((String)fichaActual.get(1)) {
                    case "s1" -> {
                        if(tablero[bloquesFicha[0][1]+1][bloquesFicha[0][0]+1].equals(VACIO)){
                            bloquesFicha[0][0]+=1;
                            bloquesFicha[0][1]+=1;
                            fichaActual.set(1, "s2");
                            fichaActual.set(2, "h");
                        }
                    }
                    case "s2" -> {
                        switch((String)fichaActual.get(2)){
                            case "h" ->{
                                if(tablero[bloquesFicha[0][1]-1][bloquesFicha[0][0]-1].equals(VACIO)){
                                    bloquesFicha[0][0]-=1;
                                    bloquesFicha[0][1]-=1;
                                    fichaActual.set(1, "s1");
                                    fichaActual.set(2, "h");
                                }
                            }case "ah" ->{
                                if(tablero[bloquesFicha[0][1]-1][bloquesFicha[0][0]+1].equals(VACIO)){
                                    bloquesFicha[0][0]+=1;
                                    bloquesFicha[0][1]-=1;
                                    fichaActual.set(1, "s1");
                                    fichaActual.set(2, "h");
                                }
                            }
                        }
                    }

                }
            }
            case "l" -> {
                switch ((String)fichaActual.get(1)) {
                    case "s1" -> {
                        if(tablero[bloquesFicha[0][1]+1][bloquesFicha[0][0]+1].equals(VACIO) && 
                           tablero[bloquesFicha[2][1]-1][bloquesFicha[2][0]-1].equals(VACIO)){
                            bloquesFicha[0][0]+=1; bloquesFicha[0][1]+=1;
                            bloquesFicha[2][0]-=1; bloquesFicha[2][1]-=1;
                            fichaActual.set(1, "s2");
                            fichaActual.set(2, "h");
                        }
                    }
                    case "s2" -> {
                        switch((String)fichaActual.get(2)){
                            case "h" -> {
                                if(tablero[bloquesFicha[0][1]-1][bloquesFicha[0][0]-1].equals(VACIO) && 
                                   tablero[bloquesFicha[2][1]+1][bloquesFicha[2][0]+1].equals(VACIO)){
                                    bloquesFicha[0][0]-=1; bloquesFicha[0][1]-=1;
                                    bloquesFicha[2][0]+=1; bloquesFicha[2][1]+=1;
                                    fichaActual.set(1, "s1");
                                    fichaActual.set(2, "h");
                                }
                            }
                            case "ah" -> {
                                if(tablero[bloquesFicha[0][1]-1][bloquesFicha[0][0]+1].equals(VACIO) && 
                                   tablero[bloquesFicha[2][1]+1][bloquesFicha[2][0]-1].equals(VACIO)){
                                    bloquesFicha[0][0]+=1; bloquesFicha[0][1]-=1;
                                    bloquesFicha[2][0]-=1; bloquesFicha[2][1]+=1;
                                    fichaActual.set(1, "s1");
                                    fichaActual.set(2, "h");
                                }
                            }
                        }
                    }
                }
            }
            case "t" -> {
                switch ((String)fichaActual.get(1)) {
                    case "s1" -> { 
                        if(tablero[bloquesFicha[0][1]-1][bloquesFicha[0][0]+1].equals(VACIO) &&
                           tablero[bloquesFicha[2][1]+1][bloquesFicha[2][0]-1].equals(VACIO) &&
                           tablero[bloquesFicha[3][1]-1][bloquesFicha[3][0]-1].equals(VACIO)) {
                            bloquesFicha[0][0]+=1; bloquesFicha[0][1]-=1;
                            bloquesFicha[2][0]-=1; bloquesFicha[2][1]+=1;
                            bloquesFicha[3][0]-=1; bloquesFicha[3][1]-=1;
                            fichaActual.set(1, "s2");
                        }
                    }
                    case "s2" -> { 
                        if(tablero[bloquesFicha[0][1]+1][bloquesFicha[0][0]+1].equals(VACIO) &&
                           tablero[bloquesFicha[2][1]-1][bloquesFicha[2][0]-1].equals(VACIO) &&
                           tablero[bloquesFicha[3][1]-1][bloquesFicha[3][0]+1].equals(VACIO)) {
                            bloquesFicha[0][0]+=1; bloquesFicha[0][1]+=1;
                            bloquesFicha[2][0]-=1; bloquesFicha[2][1]-=1;
                            bloquesFicha[3][0]+=1; bloquesFicha[3][1]-=1;
                            fichaActual.set(1, "s3");
                        }
                    }
                    case "s3" -> { 
                        if(tablero[bloquesFicha[0][1]+1][bloquesFicha[0][0]-1].equals(VACIO) &&
                           tablero[bloquesFicha[2][1]-1][bloquesFicha[2][0]+1].equals(VACIO) &&
                           tablero[bloquesFicha[3][1]+1][bloquesFicha[3][0]+1].equals(VACIO)) {
                            bloquesFicha[0][0]-=1; bloquesFicha[0][1]+=1;
                            bloquesFicha[2][0]+=1; bloquesFicha[2][1]-=1;
                            bloquesFicha[3][0]+=1; bloquesFicha[3][1]+=1;
                            fichaActual.set(1, "s4");
                        }
                    }
                    case "s4" -> { 
                        if(tablero[bloquesFicha[0][1]-1][bloquesFicha[0][0]-1].equals(VACIO) &&
                           tablero[bloquesFicha[2][1]+1][bloquesFicha[2][0]+1].equals(VACIO) &&
                           tablero[bloquesFicha[3][1]+1][bloquesFicha[3][0]-1].equals(VACIO)) {
                            bloquesFicha[0][0]-=1; bloquesFicha[0][1]-=1;
                            bloquesFicha[2][0]+=1; bloquesFicha[2][1]+=1;
                            bloquesFicha[3][0]-=1; bloquesFicha[3][1]+=1;
                            fichaActual.set(1, "s1");
                        }
                    }
                }
            }
            case "v" -> {
                switch ((String)fichaActual.get(1)) {
                    case "s1" -> {
                        if(tablero[bloquesFicha[0][1]+1][bloquesFicha[0][0]+1].equals(VACIO) &&
                           tablero[bloquesFicha[2][1]+1][bloquesFicha[2][0]-1].equals(VACIO)) {
                            bloquesFicha[0][0]+=1; bloquesFicha[0][1]+=1;
                            bloquesFicha[2][0]-=1; bloquesFicha[2][1]+=1;
                            fichaActual.set(1, "s2");
                        }
                    }
                    case "s2" -> {
                        if(tablero[bloquesFicha[0][1]+1][bloquesFicha[0][0]-1].equals(VACIO) &&
                           tablero[bloquesFicha[2][1]-1][bloquesFicha[2][0]-1].equals(VACIO)) {
                            bloquesFicha[0][0]-=1; bloquesFicha[0][1]+=1;
                            bloquesFicha[2][0]-=1; bloquesFicha[2][1]-=1;
                            fichaActual.set(1, "s3");
                        }
                    }
                    case "s3" -> {
                        if(tablero[bloquesFicha[0][1]-1][bloquesFicha[0][0]-1].equals(VACIO) &&
                           tablero[bloquesFicha[2][1]-1][bloquesFicha[2][0]+1].equals(VACIO)) {
                            bloquesFicha[0][0]-=1; bloquesFicha[0][1]-=1;
                            bloquesFicha[2][0]+=1; bloquesFicha[2][1]-=1;
                            fichaActual.set(1, "s4");
                        }
                    }
                    case "s4" -> {
                        if(tablero[bloquesFicha[0][1]-1][bloquesFicha[0][0]+1].equals(VACIO) &&
                           tablero[bloquesFicha[2][1]+1][bloquesFicha[2][0]+1].equals(VACIO)) {
                            bloquesFicha[0][0]+=1; bloquesFicha[0][1]-=1;
                            bloquesFicha[2][0]+=1; bloquesFicha[2][1]+=1;
                            fichaActual.set(1, "s1");
                        }
                    }
                }
            }
        }
        for(int i=0; i< numFichas;i++){
            tablero[bloquesFicha[i][1]][bloquesFicha[i][0]]=(String)fichaActual.get(0);
        }
    }
    public void girarAntihorario(){
        for(int i=0; i< numFichas;i++){
            tablero[bloquesFicha[i][1]][bloquesFicha[i][0]]=VACIO;
        }
        switch ((String)fichaActual.get(0)) {
            case "i" -> {
                switch ((String)fichaActual.get(1)) {
                    case "s1" -> {
                        if(tablero[bloquesFicha[0][1]+1][bloquesFicha[0][0]-1].equals(VACIO)){
                            bloquesFicha[0][0]-=1;
                            bloquesFicha[0][1]+=1;
                            fichaActual.set(1, "s2");
                            fichaActual.set(2, "ah");
                        }
                    }
                    case "s2" -> {
                        switch((String)fichaActual.get(2)){
                            case "h" ->{
                                if(tablero[bloquesFicha[0][1]-1][bloquesFicha[0][0]-1].equals(VACIO)){
                                    bloquesFicha[0][0]-=1;
                                    bloquesFicha[0][1]-=1;
                                    fichaActual.set(1, "s1");
                                    fichaActual.set(2, "ah");
                                }
                            }case "ah" ->{
                                if(tablero[bloquesFicha[0][1]-1][bloquesFicha[0][0]+1].equals(VACIO)){
                                    bloquesFicha[0][0]+=1;
                                    bloquesFicha[0][1]-=1;
                                    fichaActual.set(1, "s1");
                                    fichaActual.set(2, "ah");
                                }
                            }
                        }
                    }

                }
            }
           case "l" -> {
                
                switch ((String)fichaActual.get(1)) {
                    case "s1" -> {
                        if(tablero[bloquesFicha[0][1]+1][bloquesFicha[0][0]-1].equals(VACIO) && 
                           tablero[bloquesFicha[2][1]-1][bloquesFicha[2][0]+1].equals(VACIO)) {
                            bloquesFicha[0][0]-=1; bloquesFicha[0][1]+=1;
                            bloquesFicha[2][0]+=1; bloquesFicha[2][1]-=1;
                            fichaActual.set(1, "s2");
                            fichaActual.set(2, "ah");
                        }
                    }
                    case "s2" -> {
                        switch((String)fichaActual.get(2)){
                            case "h" -> {
                                if(tablero[bloquesFicha[0][1]-1][bloquesFicha[0][0]-1].equals(VACIO) && 
                                   tablero[bloquesFicha[2][1]+1][bloquesFicha[2][0]+1].equals(VACIO)) {
                                    bloquesFicha[0][0]-=1; bloquesFicha[0][1]-=1;
                                    bloquesFicha[2][0]+=1; bloquesFicha[2][1]+=1;
                                    fichaActual.set(1, "s1");
                                    fichaActual.set(2, "ah");
                                }
                            }
                            case "ah" -> {
                                if(tablero[bloquesFicha[0][1]-1][bloquesFicha[0][0]+1].equals(VACIO) && 
                                   tablero[bloquesFicha[2][1]+1][bloquesFicha[2][0]-1].equals(VACIO)) {
                                    bloquesFicha[0][0]+=1; bloquesFicha[0][1]-=1;
                                    bloquesFicha[2][0]-=1; bloquesFicha[2][1]+=1;
                                    fichaActual.set(1, "s1");
                                    fichaActual.set(2, "ah");
                                }
                            }
                        }
                    }
                }
            }
            case "t" -> {
                switch ((String)fichaActual.get(1)) {
                    case "s1" -> { 
                        if(tablero[bloquesFicha[0][1]+1][bloquesFicha[0][0]+1].equals(VACIO) &&
                           tablero[bloquesFicha[2][1]-1][bloquesFicha[2][0]-1].equals(VACIO) &&
                           tablero[bloquesFicha[3][1]-1][bloquesFicha[3][0]+1].equals(VACIO)) {
                            bloquesFicha[0][0]+=1; bloquesFicha[0][1]+=1;
                            bloquesFicha[2][0]-=1; bloquesFicha[2][1]-=1;
                            bloquesFicha[3][0]+=1; bloquesFicha[3][1]-=1;
                            fichaActual.set(1, "s4");
                        }
                    }
                    case "s4" -> { 
                        if(tablero[bloquesFicha[0][1]-1][bloquesFicha[0][0]+1].equals(VACIO) &&
                           tablero[bloquesFicha[2][1]+1][bloquesFicha[2][0]-1].equals(VACIO) &&
                           tablero[bloquesFicha[3][1]-1][bloquesFicha[3][0]-1].equals(VACIO)) {
                            bloquesFicha[0][0]+=1; bloquesFicha[0][1]-=1;
                            bloquesFicha[2][0]-=1; bloquesFicha[2][1]+=1;
                            bloquesFicha[3][0]-=1; bloquesFicha[3][1]-=1;
                            fichaActual.set(1, "s3");
                        }
                    }
                    case "s3" -> { 
                        if(tablero[bloquesFicha[0][1]-1][bloquesFicha[0][0]-1].equals(VACIO) &&
                           tablero[bloquesFicha[2][1]+1][bloquesFicha[2][0]+1].equals(VACIO) &&
                           tablero[bloquesFicha[3][1]+1][bloquesFicha[3][0]-1].equals(VACIO)) {
                            bloquesFicha[0][0]-=1; bloquesFicha[0][1]-=1;
                            bloquesFicha[2][0]+=1; bloquesFicha[2][1]+=1;
                            bloquesFicha[3][0]-=1; bloquesFicha[3][1]+=1;
                            fichaActual.set(1, "s2");
                        }
                    }
                    case "s2" -> { 
                        if(tablero[bloquesFicha[0][1]+1][bloquesFicha[0][0]-1].equals(VACIO) &&
                           tablero[bloquesFicha[2][1]-1][bloquesFicha[2][0]+1].equals(VACIO) &&
                           tablero[bloquesFicha[3][1]+1][bloquesFicha[3][0]+1].equals(VACIO)) {
                            bloquesFicha[0][0]-=1; bloquesFicha[0][1]+=1;
                            bloquesFicha[2][0]+=1; bloquesFicha[2][1]-=1;
                            bloquesFicha[3][0]+=1; bloquesFicha[3][1]+=1;
                            fichaActual.set(1, "s1");
                        }
                    }
                }
            }
            case "v" -> {
                switch ((String)fichaActual.get(1)) {
                    case "s1" -> {
                        if(tablero[bloquesFicha[0][1]+1][bloquesFicha[0][0]-1].equals(VACIO) &&
                           tablero[bloquesFicha[2][1]-1][bloquesFicha[2][0]-1].equals(VACIO)) {
                            bloquesFicha[0][0]-=1; bloquesFicha[0][1]+=1;
                            bloquesFicha[2][0]-=1; bloquesFicha[2][1]-=1;
                            fichaActual.set(1, "s4");
                        }
                    }
                    case "s4" -> {
                        if(tablero[bloquesFicha[0][1]+1][bloquesFicha[0][0]+1].equals(VACIO) &&
                           tablero[bloquesFicha[2][1]+1][bloquesFicha[2][0]-1].equals(VACIO)) {
                            bloquesFicha[0][0]+=1; bloquesFicha[0][1]+=1;
                            bloquesFicha[2][0]-=1; bloquesFicha[2][1]+=1;
                            fichaActual.set(1, "s3");
                        }
                    }
                    case "s3" -> { 
                        if(tablero[bloquesFicha[0][1]-1][bloquesFicha[0][0]+1].equals(VACIO) &&
                           tablero[bloquesFicha[2][1]+1][bloquesFicha[2][0]+1].equals(VACIO)) {
                            bloquesFicha[0][0]+=1; bloquesFicha[0][1]-=1;
                            bloquesFicha[2][0]+=1; bloquesFicha[2][1]+=1;
                            fichaActual.set(1, "s2");
                        }
                    }
                    case "s2" -> { 
                        if(tablero[bloquesFicha[0][1]-1][bloquesFicha[0][0]-1].equals(VACIO) &&
                           tablero[bloquesFicha[2][1]-1][bloquesFicha[2][0]+1].equals(VACIO)) {
                            bloquesFicha[0][0]-=1; bloquesFicha[0][1]-=1;
                            bloquesFicha[2][0]+=1; bloquesFicha[2][1]-=1;
                            fichaActual.set(1, "s1");
                        }
                    }
                }
            }
            
        }

        for(int i=0; i< numFichas;i++){
            tablero[bloquesFicha[i][1]][bloquesFicha[i][0]]=(String)fichaActual.get(0);
        }
    }
        
   
     
    public void escogerSigFicha(List<Object> fichaActual){
        eligeFicha = rand.nextInt(6);
        switch(eligeFicha){
            case 0 -> {
                fichaActual.clear();
                fichaActual.addAll(fichaD);
                bloquesFicha[0][0]=ancho/2;
                bloquesFicha[0][1]=0;
                numFichas=1;
            }
            case 1 -> {
                fichaActual.clear();
                fichaActual.addAll(fichaI);
                bloquesFicha[0][0]=ancho/2;
                bloquesFicha[0][1]=0;
                bloquesFicha[1][0]=ancho/2;
                bloquesFicha[1][1]=1;
                numFichas=2;
            }
            case 2 -> {
                fichaActual.clear();
                fichaActual.addAll(fichaC);
                bloquesFicha[0][0] = ancho/2;
                bloquesFicha[0][1] = 0;
                bloquesFicha[1][0] = ancho/2 + 1;
                bloquesFicha[1][1] = 0;
                bloquesFicha[2][0] = ancho/2;
                bloquesFicha[2][1] = 1;
                bloquesFicha[3][0] = ancho/2 + 1;
                bloquesFicha[3][1] = 1;
                numFichas = 4;
            }
            case 3 -> {
                fichaActual.clear();
                fichaActual.addAll(fichal);
                bloquesFicha[0][0] = ancho/2;
                bloquesFicha[0][1] = 0;
                bloquesFicha[1][0] = ancho/2;
                bloquesFicha[1][1] = 1;
                bloquesFicha[2][0] = ancho/2;
                bloquesFicha[2][1] = 2;
                numFichas = 3;
            }
            case 4 -> {
                fichaActual.clear();
                fichaActual.addAll(fichaT);
                bloquesFicha[0][0] = ancho/2 - 1;
                bloquesFicha[0][1] = 0;
                bloquesFicha[1][0] = ancho/2;
                bloquesFicha[1][1] = 0;
                bloquesFicha[2][0] = ancho/2 + 1;
                bloquesFicha[2][1] = 0;
                bloquesFicha[3][0] = ancho/2;
                bloquesFicha[3][1] = 1;
                numFichas = 4;
            }
            case 5 -> {
                fichaActual.clear();
                fichaActual.addAll(fichaV);
                bloquesFicha[0][0] = ancho/2;
                bloquesFicha[0][1] = 0;
                bloquesFicha[1][0] = ancho/2;
                bloquesFicha[1][1] = 1;
                bloquesFicha[2][0] = ancho/2 + 1;
                bloquesFicha[2][1] = 1;
                numFichas = 3;
            }
        }
    }   
    
    
    public int borrarLineaHorizontal() {
        puntajeTotal=0;
        for (int i = this.alto - 2; i > 0; i--) {
            boolean lineaLlena = true;
            
            for (int j = 1; j < this.ancho - 1; j++) {
                if (tablero[i][j].equals(VACIO)) {
                    lineaLlena = false; 
                    break;
                }
            }

            if (lineaLlena) {
                puntajeActual = (this.ancho - 2) * 10;
                puntajeTotal += puntajeActual;
                for (int k = i; k > 0; k--) {
                    for (int j = 1; j < this.ancho - 1; j++) {
                        tablero[k][j] = tablero[k - 1][j];
                    }
                }
                for (int j = 1; j < this.ancho - 1; j++) {
                    tablero[0][j] = VACIO;
                }
                i++;
            }
        }
        return puntajeTotal;//puntaje obtenido en esta ronda
    }
    public void anunciarGanador(){
        System.out.println("Se acabo el juego.");
        System.out.println("Ganador:"+puntajeTotal);
        
    }
    public void anunciarOrden(){
        
    }
    
}
