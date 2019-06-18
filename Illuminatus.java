/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author FilipeAndre
 */
import java.io.*;
import java.util.StringTokenizer;
import javax.swing.*; 


/**
 *
 * @author FilipeAndre
 */




public class Illuminatus {
    
    
    public static Board brd;
    public static ButtonGrid grid; 
    
    public static boolean LoadBoard(String filename)
    {   StringBuffer sb = new StringBuffer();
        sb.append(filename);
        sb.append(".ill");
        try { BufferedReader ficheiro = new BufferedReader(new FileReader(sb.toString()));
    int ciclo=0;
    String linha = "";
          int sizec,sizel,num,i,j;
          char estado;
          String aux = "";
          StringTokenizer str;
      while(ficheiro.ready()){
          if(ciclo==0){
          
          linha = ficheiro.readLine();
          
          str = new StringTokenizer(linha," ");
          aux = str.nextToken();
            sizec = Integer.parseInt(aux);
            aux = str.nextToken();
            sizel = Integer.parseInt(aux);
            brd = new Board(sizec,sizel);
            
          }
          else {     
          for(j=0;j<brd.getsizeL();j++) 
          { linha = ficheiro.readLine();
            str = new StringTokenizer(linha," ");
              for(i=0;i<brd.getsizeC();i++){
              aux = str.nextToken();
            estado = aux.charAt(0);
            if(estado == '-')
            brd.addCasa(i,j,' ',-1);
            else brd.addCasa(i,j,estado,-1);
            }}
            
          }
              
          
         ciclo++; 
        }
        ficheiro.close();}catch (IOException e) {return false;}
    
    
    return true;
}
    
    public static void main(String[] args) {
        brd = new Board();
        grid =new ButtonGrid(brd);
       
       
    }
    
    
    
  
}