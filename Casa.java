/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author FilipeAndre
 */
public class Casa {
    private int num;
    private char estado;
    public Casa(int n,char e)
    { this.num = n;
        this.estado = e;
    } 
    public int getNum() { return this.num; }
    public char getEstado() { return this.estado; }
    public void setEstado(char e) { this.estado = e; }
    public void setNum(int n) { this.num = n; }
}