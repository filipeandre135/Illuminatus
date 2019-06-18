/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author FilipeAndre
 */
public class Board {
    private int sizec;
    private int sizel;
    private Casa [][] board;
    
    public Board()
    { sizec =1;
        sizel=1;
        board = new Casa [sizec][sizel];
        addCasa(0,0,'t',0);
    }
    public Board(int sizeC,int sizeL){
    sizec = sizeC;
    sizel = sizeL;
    board = new Casa [sizeC][sizeL];
}
    public int getsizeC(){ return this.sizec; }
    public int getsizeL() { return this.sizel; }
    public Casa getCasa(int i,int j){ return board[i][j]; }

    public void addCasa(int i,int j,char estado,int num)
    { Casa c = new Casa(num,estado);
      this.board[i][j] = c;
}
}