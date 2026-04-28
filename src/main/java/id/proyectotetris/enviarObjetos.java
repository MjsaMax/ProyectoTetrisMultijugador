/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.proyectotetris;
import java.io.Serializable;
import java.util.List;
/**
 *
 * @author mjsam
 */

public class enviarObjetos implements Serializable {
    private static final long serialVersionUID = 2L;
    
    public String[][] tablero;
    public List<Object> fichaActual;
    public int[][] bloquesFicha;
    public int[] puntajes;
    public int nrcli;
    public int turno;
    public GameTetris g;
    
    public enviarObjetos(GameTetris g,int[] puntajes, int  nrcli) {
        this.g=g;
        this.puntajes = puntajes;
        this.nrcli=nrcli;
    }
    public GameTetris getGame(){
        return g;
    }
//    public String[][] getTablero() {
//        return tablero;
//    }
    public int getNrcli() {
        return nrcli;
    }
//    public int getTurno() {
//        return turno;
//    }
//
//    public List<Object> getFichaActual() {
//        return fichaActual;
//    }
//
//    public int[][] getBloquesFicha() {
//        return bloquesFicha;
//    }
//
    public int[] getPuntajes() {
        return puntajes;
    }
}
