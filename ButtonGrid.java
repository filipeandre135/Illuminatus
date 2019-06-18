/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author FilipeAndre
 */
import java.awt.GridLayout; //imports GridLayout library
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.util.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.KeyEvent;

public  class ButtonGrid {
        
        final Board brd;
        final JFrame frame;
        public JButton[][] grid; //names the grid of buttons
        public static Stack<Node> stack = new Stack();
        
        public ButtonGrid(Board brd){ //constructor
                this.brd = brd;
                JPanel panel = new JPanel();
                panel.setPreferredSize(new Dimension(500,500));
                frame=new JFrame(); //creates frame
                GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
                frame.setExtendedState(frame.getExtendedState() | frame.MAXIMIZED_BOTH);
                   panel.setLayout(new GridBagLayout()); //set layout
                   GridBagConstraints c = new GridBagConstraints();
                panel.setPreferredSize(new Dimension(1050,800));
                grid=new JButton[brd.getsizeL()][brd.getsizeC()]; //allocate the size of grid
                for(int y=0; y<brd.getsizeL(); y++){
                        for(int x=0; x<brd.getsizeC(); x++){
                                JButton jb = new JButton(brd.getCasa(x,y).getEstado()+""); //creates new button
                                jb.setSize(5,5);
                                if(jb.getText().charAt(0)=='t') jb.setText("Carregue um tabuleiro");
                                if(jb.getText().charAt(0) == '@')
                                   { jb.setBackground(Color.RED);
                                     jb.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                                JButton src = (JButton)e.getSource();
                                                if(src.getText().charAt(0) == '@')
                                                {src.setText(".");
                                                src.setBackground(Color.YELLOW);}
                                                else if(src.getText().charAt(0) == '.')
                                                        {src.setText(" ");
                                                            src.setBackground(Color.WHITE);}
                                                      else if(src.getText().charAt(0) == ' ')
                                                            {src.setText("@");
                                                            src.setBackground(Color.RED);}
                                            }});}
                                if(jb.getText().charAt(0) == 'x' || Character.isDigit(jb.getText().charAt(0)))
                                    {jb.setBackground(Color.BLACK);
                                    jb.setForeground(Color.WHITE);}
                                if(jb.getText().charAt(0) == '.')
                                    { jb.setBackground(Color.YELLOW);
                                       jb.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                                JButton src = (JButton)e.getSource();
                                                if(src.getText().charAt(0) == '@')
                                                {src.setText(".");
                                                src.setBackground(Color.YELLOW);}
                                                else if(src.getText().charAt(0) == '.')
                                                        {src.setText(" ");
                                                            src.setBackground(Color.WHITE);}
                                                      else if(src.getText().charAt(0) == ' ')
                                                            {src.setText("@");
                                                            src.setBackground(Color.RED);}}});}
                                if(jb.getText().charAt(0) == ' ')
                                     { jb.setBackground(Color.WHITE);
                                       jb.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                                JButton src = (JButton)e.getSource();
                                                if(src.getText().charAt(0) == '@')
                                                {src.setText(".");
                                                src.setBackground(Color.YELLOW);}
                                                else if(src.getText().charAt(0) == '.')
                                                        {src.setText(" ");
                                                            src.setBackground(Color.WHITE);}
                                                      else if(src.getText().charAt(0) == ' ')
                                                            {src.setText("@");
                                                            src.setBackground(Color.RED);}}});}
                                c.gridx = x;
                                c.gridy = y;
                                c.ipadx = 1;
                                c.gridwidth = 1;
                                  
                                panel.add(jb,c);
                        }
                }
                
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack(); //sets appropriate size for frame
                frame.setVisible(true); //makes frame visible
                panel.setVisible(true);
                frame.add(panel,BorderLayout.WEST);
       
       JPanel bpanel = new JPanel(); 
       bpanel.setPreferredSize(new Dimension(250,500));
       final JTextField loadfield = new JTextField();
       TextPrompt ldprmpt = new TextPrompt("Nome ficheiro",loadfield);
       JButton load = new JButton("Carregar");
       load.setToolTipText("Carrega um tabuleiro (introduza o nome do ficheiro ao lado)");
       load.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                String getTxt = loadfield.getText();
                boolean flag =Illuminatus.LoadBoard(getTxt);
                if(flag == false) {JPanel panel = new JPanel();JOptionPane.showMessageDialog(panel, "O ficheiro "+getTxt+".ill não existe", "Erro",
        JOptionPane.ERROR_MESSAGE);}
                else {Illuminatus.grid = new ButtonGrid(Illuminatus.brd);
                frame.setVisible(false);
                frame.dispose();}
            }});
            
       JButton save = new JButton("Gravar");
       final JTextField savefield = new JTextField();
       TextPrompt svprmpt = new TextPrompt("Nome ficheiro",savefield);
       save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                String getTxt = savefield.getText();
                boolean flag =Save(getTxt);
                if(flag == false) {JPanel panel = new JPanel();JOptionPane.showMessageDialog(panel, "O ficheiro "+getTxt+".ill não existe", "Erro",
        JOptionPane.ERROR_MESSAGE);}
                else {JPanel panel = new JPanel();JOptionPane.showMessageDialog(panel, "O tabuleiro foi gravado no ficheiro "+getTxt+".ill", "Sucesso",
        JOptionPane.ERROR_MESSAGE);}
            }});
       save.setToolTipText("Grava o tabuleiro actual (introduza o nome do ficheiro ao lado)");
       /** CODIGO SAVE*/
       load.setPreferredSize(new Dimension(100,30));
       save.setPreferredSize(new Dimension(100,30));
       
       bpanel.setVisible(true);
       savefield.setPreferredSize(new Dimension(100,30));
       loadfield.setPreferredSize(new Dimension(100,30));
       bpanel.add(save);
       bpanel.add(savefield);
       bpanel.add(load);
       bpanel.add(loadfield);
       JButton undo = new JButton("Anular");
       undo.setToolTipText("Anula uma estratégia");
       ToolTipManager.sharedInstance().setInitialDelay(0);
       undo.setPreferredSize(new Dimension(100,30));
       undo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                undo();
            }});
       bpanel.add(undo);
       /** BOTOES DAS ESTRATEGIAS*/
       JButton est1 = new JButton("Estrat1");
       est1.setToolTipText("Se uma casa de número N tiver N casas livres coloca lampâdas nessas casas");
       est1.setPreferredSize(new Dimension(100,30));
       est1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Estrategia1();
            }});
       est1.registerKeyboardAction(est1.getActionForKeyStroke(
                                      KeyStroke.getKeyStroke(KeyEvent.VK_1, 0, true)),
                                      KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                                      JComponent.WHEN_FOCUSED);     
       JButton est2 = new JButton("Estrat2");
       est2.setToolTipText("Marca as colunas e linhas ortogonais a uma casa com lâmpada");
       est2.setPreferredSize(new Dimension(100,30));
       est2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Estrategia2();
            }});
       JButton est3 = new JButton("Estrat3");
       est3.setToolTipText("Marca casas em que não é possivel ter lâmpada");
       est3.setPreferredSize(new Dimension(100,30));
       est3.setPreferredSize(new Dimension(100,30));
       est3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Estrategia3();
            }});
       JButton est4 = new JButton("Estrat4");
       est4.setToolTipText("Estrategia 4");
       est4.setPreferredSize(new Dimension(100,30));
       est4.setPreferredSize(new Dimension(100,30));
       est4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Estrategia4();
            }});     
       bpanel.add(est1);
       bpanel.add(est2);
       bpanel.add(est3);
       bpanel.add(est4);
       frame.add(bpanel,BorderLayout.CENTER);
       frame.pack();
        }
        
        class Node{
            
            char estado;
            int i;
            int j;
            Color cor;
            
           public Node(int i,int j,char estado,Color color){
               this.i=i;
               this.j=j;
               this.estado=estado;
               this.cor = color;
            }
        public int getI() { return this.i; }
        public int getJ() { return this.j;}
        public char getEstado() { return this.estado; }
        public Color getColor() { return this.cor; }
        }
            
            
  public void Estrategia2()
  {     this.stack.push(new Node(-1,-1,'o',Color.GRAY));
       for(int i=0; i<brd.getsizeC(); i++){
                        for(int j=0; j<brd.getsizeL(); j++){
           if(grid[i][j].getText().charAt(0) == '@'){
                ilumina_casas(i,j);
}
    }}
    
   if(this.stack.peek().estado == 'o')
   this.stack.pop();
}
  public void ilumina_casas(int i,int j)
  { boolean flag=false;
      int x=i,y=j;
      for(i=i+1;i<brd.getsizeC() && !flag;i++){
    if(Character.isDigit(grid[i][j].getText().charAt(0)) || grid[i][j].getText().charAt(0) == 'x'){
    flag=true;}
    else {altera(i,j,'.',Color.YELLOW);}}
    flag=false;
    i=x;j=y;
for(i=i-1;i>=0 && !flag;i--){
    if(Character.isDigit(grid[i][j].getText().charAt(0)) || grid[i][j].getText().charAt(0) == 'x'){
    flag=true;} 
    else{ altera(i,j,'.',Color.YELLOW);}}
    flag=false;
    i=x;j=y;
for(j=j+1;j<brd.getsizeL() && !flag;j++){
    if(Character.isDigit(grid[i][j].getText().charAt(0)) || grid[i][j].getText().charAt(0) == 'x'){
    flag=true;} 
    else {altera(i,j,'.',Color.YELLOW);}}
    flag=false;
    i=x;j=y;
for(j=j-1;j>=0 && !flag;j--){
    if(Character.isDigit(grid[i][j].getText().charAt(0)) || grid[i][j].getText().charAt(0) == 'x'){
    flag=true;}
    else {altera(i,j,'.',Color.YELLOW);}}
}

    public void Estrategia1()
    {   int i= 0;
        int j= 0; 
        int n = 0;

        this.stack.push(new Node(-1,-1,'o',Color.GRAY));
    for(i=0;i<(brd.getsizeC());i++)
    {for(j=0;j<(brd.getsizeL());j++){
        if (Character.isDigit(grid[i][j].getText().charAt(0))) 
            { if(coordsvalid(i-1,j,brd.getsizeC(),brd.getsizeL())){
                if (casalivre(i-1,j) || grid[i-1][j].getText().charAt(0) =='@') 
                n++;}
              if(coordsvalid(i+1,j,brd.getsizeC(),brd.getsizeL())){  
              if (casalivre(i+1,j) || grid[i+1][j].getText().charAt(0) =='@')
                n++;}
              if(coordsvalid(i,j-1,brd.getsizeC(),brd.getsizeL())){  
              if (casalivre(i,j-1) || grid[i][j-1].getText().charAt(0) =='@')
                n++;}
              if(coordsvalid(i,j+1,brd.getsizeC(),brd.getsizeL())){  
              if (casalivre(i,j+1) || grid[i][j+1].getText().charAt(0) =='@')
                n++;}
                
            }
        if(n!=0){                        
        if((Integer.parseInt(grid[i][j].getText()) == n))
            {
            
            if(coordsvalid(i-1,j,brd.getsizeC(),brd.getsizeL()) && ((grid[i-1][j].getText().charAt(0) =='@') || (grid[i-1][j].getText().charAt(0) ==' '))){ 
            altera(i-1,j,'@',Color.RED);     
            }
             if(coordsvalid(i+1,j,brd.getsizeC(),brd.getsizeL()) && ((grid[i+1][j].getText().charAt(0) =='@') || (grid[i+1][j].getText().charAt(0) ==' '))){
              altera(i+1,j,'@',Color.RED);            }
             if(coordsvalid(i,j-1,brd.getsizeC(),brd.getsizeL()) && ((grid[i][j-1].getText().charAt(0) =='@') || (grid[i][j-1].getText().charAt(0) ==' '))){ 
              altera(i,j-1,'@',Color.RED);
            }
             if(coordsvalid(i,j+1,brd.getsizeC(),brd.getsizeL()) && ((grid[i][j+1].getText().charAt(0) =='@') || (grid[i][j+1].getText().charAt(0) ==' '))){
              altera(i,j+1,'@',Color.RED);}
}}





n=0;

}}
if(this.stack.peek().estado == 'o')
this.stack.pop();
}


public boolean casalivre(int i,int j){
if(coordsvalid(i,j,brd.getsizeC(),brd.getsizeL())){        
if(grid[i][j].getText().charAt(0) == ' ')
        return true;
        else return false;}
else return false;
    
}
    
public boolean coordsvalid(int i,int j,int sizec,int sizel)
{   
    if (i<0 || i>sizec -1 || j<0 || j>sizel-1)
        return false;
    else return true;    
}


public void altera(int i,int j,char estado,Color color)
{   if(coordsvalid(i,j,brd.getsizeC(),brd.getsizeL())){
    if(grid[i][j].getText().charAt(0) != estado && grid[i][j].getText().charAt(0) != 'x' && !(Character.isDigit(grid[i][j].getText().charAt(0)))){
        this.stack.push(new Node(i,j,grid[i][j].getText().charAt(0),grid[i][j].getBackground()));
        grid[i][j].setText(estado+"");
        grid[i][j].setBackground(color);
    }}
       
    
 }
 
 public void undo(){
     if(this.stack.empty())
     {JPanel panel = new JPanel();JOptionPane.showMessageDialog(panel, "Não há comandos para anular", "Erro",
        JOptionPane.ERROR_MESSAGE);}
    else{ while(this.stack.peek().estado!='o')
     {
        grid[this.stack.peek().getI()][this.stack.peek().getJ()].setText(this.stack.peek().getEstado()+"");
        grid[this.stack.peek().getI()][this.stack.peek().getJ()].setBackground(this.stack.peek().getColor());
        this.stack.pop();
        
    }
    this.stack.pop();
}

}

    
 public void casasortog()
 {  int i,j,count=0;
     for(i=0;i<brd.getsizeC();i++){
         for(j=0;j<brd.getsizeL();j++){
             if(grid[i][j].getText().charAt(0) =='0'){          /*Quando encontrar um 0 na matriz */
            if(coordsvalid(i-1,j,brd.getsizeC(),brd.getsizeL())){
            if (grid[i-1][j].getText().charAt(0)==' ') altera(i-1,j,'.',Color.YELLOW);}
            if(coordsvalid(i+1,j,brd.getsizeC(),brd.getsizeL())){
            if (grid[i+1][j].getText().charAt(0)==' ') altera(i+1,j,'.',Color.YELLOW);}
            if(coordsvalid(i,j+1,brd.getsizeC(),brd.getsizeL())){
            if (grid[i][j+1].getText().charAt(0)==' ') altera(i,j+1,'.',Color.YELLOW);}
            if(coordsvalid(i,j-1,brd.getsizeC(),brd.getsizeL())){
            if (grid[i][j-1].getText().charAt(0)==' ') altera(i,j-1,'.',Color.YELLOW);}
                
                }
            
            if(Character.isDigit(grid[i][j].getText().charAt(0))){
                   if(coordsvalid(i-1,j,brd.getsizeC(),brd.getsizeL())){  
                   if(grid[i-1][j].getText().charAt(0)=='@') count++;}
                   if(coordsvalid(i+1,j,brd.getsizeC(),brd.getsizeL())){
                   if(grid[i+1][j].getText().charAt(0)=='@') count++;}
                   if(coordsvalid(i,j-1,brd.getsizeC(),brd.getsizeL())){
                   if(grid[i][j-1].getText().charAt(0)=='@') count++;}
                   if(coordsvalid(i,j+1,brd.getsizeC(),brd.getsizeL())){
                   if(grid[i][j+1].getText().charAt(0)=='@') count++;}  /*Contas as lâmpadas à volta de uma casa */
                if(Integer.parseInt(grid[i][j].getText()) == count){        /* Se o numero de lampadas for igual ao numero da casa*/
                    if(coordsvalid(i-1,j,brd.getsizeC(),brd.getsizeL())){
                    if (grid[i-1][j].getText().charAt(0)==' ') altera(i-1,j,'.',Color.YELLOW);}
                    if(coordsvalid(i+1,j,brd.getsizeC(),brd.getsizeL())){
                    if (grid[i+1][j].getText().charAt(0)==' ') altera(i+1,j,'.',Color.YELLOW);}
                    if(coordsvalid(i,j+1,brd.getsizeC(),brd.getsizeL())){
                    if (grid[i][j+1].getText().charAt(0)==' ') altera(i,j+1,'.',Color.YELLOW);}
                    if(coordsvalid(i,j-1,brd.getsizeC(),brd.getsizeL())){
                    if (grid[i][j-1].getText().charAt(0)==' ') altera(i,j-1,'.',Color.YELLOW);}}}   /* Marca as casas ortogonais à casa com numero*/
            count=0;    /* "Reseta" a variavel count, para ser utlizada na proxima ocorrencia */
        
        }}
        
}


public void casasdiag()
{
int i,j,count=0;

for(i=0;i<brd.getsizeC();i++)
    {for(j=0;j<brd.getsizeL();j++){
        if(Character.isDigit(grid[i][j].getText().charAt(0))){                                                 
            if(Integer.parseInt(grid[i][j].getText()) > 2) {
                    if(coordsvalid(i-1,j-1,brd.getsizeC(),brd.getsizeL())){
                    if (grid[i-1][j-1].getText().charAt(0)==' ') altera(i-1,j-1,'.',Color.YELLOW);}
                    if(coordsvalid(i+1,j+1,brd.getsizeC(),brd.getsizeL())){
                    if (grid[i+1][j+1].getText().charAt(0)==' ') altera(i+1,j+1,'.',Color.YELLOW);}
                    if(coordsvalid(i-1,j+1,brd.getsizeC(),brd.getsizeL())){
                    if (grid[i-1][j+1].getText().charAt(0)==' ') altera(i-1,j+1,'.',Color.YELLOW);}
                    if(coordsvalid(i+1,j-1,brd.getsizeC(),brd.getsizeL())){
                    if (grid[i+1][j-1].getText().charAt(0)==' ') altera(i+1,j-1,'.',Color.YELLOW);} }
            else {if(Integer.parseInt(grid[i][j].getText()) == 1 || Integer.parseInt(grid[i][j].getText()) == 2)
                { count = conta_casas_bloq(i,j);
                  count=count - Integer.parseInt(grid[i][j].getText());
                    if(count<2) {
                        if(grid[i][j].getText().charAt(0) == '1'){
                        if(TESTA_CASA_BLOQ(i-1,j) && TESTA_CASA_BLOQ(i,j-1))
                            altera(i+1,j+1,'.',Color.YELLOW);
                        if(TESTA_CASA_BLOQ(i+1,j) && TESTA_CASA_BLOQ(i,j-1))
                            altera(i-1,j+1,'.',Color.YELLOW);
                        if(TESTA_CASA_BLOQ(i+1,j) && TESTA_CASA_BLOQ(i,j+1))
                            altera(i-1,j-1,'.',Color.YELLOW);
                        if(TESTA_CASA_BLOQ(i-1,j) && TESTA_CASA_BLOQ(i,j+1))
                            altera(i+1,j-1,'.',Color.YELLOW);}
                    else { 
                        if(TESTA_CASA_BLOQ(i,j-1)){
                            altera(i+1,j+1,'.',Color.YELLOW);
                            altera(i-1,j+1,'.',Color.YELLOW);}
                        if(TESTA_CASA_BLOQ(i-1 ,j)){
                            altera(i+1,j+1,'.',Color.YELLOW);
                            altera(i+1,j-1,'.',Color.YELLOW);}
                        if(TESTA_CASA_BLOQ(i+1,j)){
                            altera(i-1,j-1,'.',Color.YELLOW);   
                            altera(i-1,j+1,'.',Color.YELLOW);}
                        if(TESTA_CASA_BLOQ(i,j+1)){
                            altera(i-1,j-1,'.',Color.YELLOW);
                            altera(i+1,j-1,'.',Color.YELLOW);}}}
    }}}}}
}
    
    public int conta_casas_bloq(int i,int j)
    { int count=0;
        if(TESTA_CASA_BLOQ(i+1,j)) count++;
        if (TESTA_CASA_BLOQ(i-1,j)) count++;
        if (TESTA_CASA_BLOQ(i,j+1)) count++;
        if (TESTA_CASA_BLOQ(i,j-1)) count++;
        
        return count;
}

    public boolean TESTA_CASA_BLOQ(int i,int j)
    {   if (!(coordsvalid(i,j,brd.getsizeC(),brd.getsizeL())) || grid[i][j].getText().charAt(0)=='x' || Character.isDigit(grid[i][j].getText().charAt(0)) )
            return true;
        else return false;
    }
public void Estrategia3()

{   this.stack.push(new Node(-1,-1,'o',Color.GRAY));   
casasortog();
    casasdiag();
    
    if(this.stack.peek().estado == 'o')
    this.stack.pop();
 }
    
    
    
    
    
    public boolean Save(String filename)
    {
        int i,j;
        boolean flag= true;
        try {PrintWriter pw = new PrintWriter(new FileWriter(filename+".ill",false));
            
            pw.println(brd.getsizeC()+" "+brd.getsizeL());
            for(i=0;i<brd.getsizeC();i++)
                {   StringBuffer sb = new StringBuffer();
                    for(j=0;j<brd.getsizeL();j++){
                    if(j==brd.getsizeL()-1){
                        if(grid[i][j].getText().charAt(0)==' ')
                            sb.append("-");
                        else sb.append(grid[i][j].getText().charAt(0)+"");}
                    else {
                            if(grid[i][j].getText().charAt(0)==' ')
                            sb.append("- ");
                        else sb.append(grid[i][j].getText().charAt(0)+" ");}
                        
                    }
                    pw.println(sb.toString());}
                        
            pw.flush();
            pw.close();
        }   catch (IOException e) {flag = false;}
        
        
        return flag;
    }
    
    
    
    public void Estrategia4(){
        int i,j;
        int count=0;
        
        for(i=0;i<brd.getsizeC();i++){
                for(j=0;j<brd.getsizeL();j++){
                    if (Character.isDigit(grid[i][j].getText().charAt(0))){
                        if(Integer.parseInt(grid[i][j].getText()) == 4)
                        { if(coordsvalid(i+1,j,brd.getsizeC(),brd.getsizeL()))
                            altera(i+1,j,'@',Color.RED);
                          if(coordsvalid(i-1,j,brd.getsizeC(),brd.getsizeL()))  
                          altera(i-1,j,'@',Color.RED);
                          if(coordsvalid(i,j+1,brd.getsizeC(),brd.getsizeL()))
                          altera(i,j+1,'@',Color.RED);
                          if(coordsvalid(i,j-1,brd.getsizeC(),brd.getsizeL()))
                          altera(i,j-1,'@',Color.RED);}    
                    if(coordsvalid(i-1,j+1,brd.getsizeC(),brd.getsizeL()))
                          {if(Character.isDigit(grid[i-1][j+1].getText().charAt(0))){
                              count = teste_estrategia4(i,j,i-1,j+1); 
                if(count == 1){
                    if(coordsvalid(i,j-1,brd.getsizeC(),brd.getsizeL()))
                    altera(i,j-1,'@',Color.RED);
                    if(coordsvalid(i+1,j,brd.getsizeC(),brd.getsizeL()))
                    altera(i+1,j,'@',Color.RED);}
                if(count == -1){
                    if(coordsvalid(i-1,j+1,brd.getsizeC(),brd.getsizeL()))
                    altera(i-2,j+1,'@',Color.RED);
                    if(coordsvalid(i-1,j+2,brd.getsizeC(),brd.getsizeL()))
                    altera(i-1,j+2,'@',Color.RED);}
                }}
             if(coordsvalid(i+1,j+1,brd.getsizeC(),brd.getsizeL())){   
            if(Character.isDigit(grid[i+1][j+1].getText().charAt(0))){
                count = teste_estrategia4(i,j,i+1,j+1);
                if(count == 1){ 
                    if(coordsvalid(i,j-1,brd.getsizeC(),brd.getsizeL()))
                    altera(i,j-1,'@',Color.RED);
                    if(coordsvalid(i-1,j,brd.getsizeC(),brd.getsizeL()))
                    altera(i-1,j,'@',Color.RED);}
                if(count == -1){
                    if(coordsvalid(i+1,j+2,brd.getsizeC(),brd.getsizeL()))
                    altera(i+1,j+2,'@',Color.RED);
                    if(coordsvalid(i+2,j+1,brd.getsizeC(),brd.getsizeL()))
                    altera(i+2,j+1,'@',Color.RED);}
                }}
                    }}}
    
                }
                
                
          public int teste_estrategia4(int i,int j,int x,int y)
          {int l1=0,l2=0;
              
                   if(coordsvalid(i-1,j,brd.getsizeC(),brd.getsizeL())){  
                   if(grid[i-1][j].getText().charAt(0)=='@') l1++;}
                   if(coordsvalid(i+1,j,brd.getsizeC(),brd.getsizeL())){
                   if(grid[i+1][j].getText().charAt(0)=='@') l1++;}
                   if(coordsvalid(i,j-1,brd.getsizeC(),brd.getsizeL())){
                   if(grid[i][j-1].getText().charAt(0)=='@') l1++;}
                   if(coordsvalid(i,j+1,brd.getsizeC(),brd.getsizeL())){
                   if(grid[i][j+1].getText().charAt(0)=='@') l1++;}
    
                   if(coordsvalid(x-1,y,brd.getsizeC(),brd.getsizeL())){  
                   if(grid[x-1][y].getText().charAt(0)=='@') l2++;}
                   if(coordsvalid(x+1,y,brd.getsizeC(),brd.getsizeL())){
                   if(grid[x+1][y].getText().charAt(0)=='@') l2++;}
                   if(coordsvalid(x,y-1,brd.getsizeC(),brd.getsizeL())){
                   if(grid[x][y-1].getText().charAt(0)=='@') l2++;}
                   if(coordsvalid(x,y+1,brd.getsizeC(),brd.getsizeL())){
                   if(grid[x][y+1].getText().charAt(0)=='@') l2++;}
        
    l1 = Integer.parseInt(grid[i][j].getText()) - l1;
    l2 = Integer.parseInt(grid[x][y].getText()) - l2;
if((l1-l2)==2) return 1;
if( (l1-l2) == -2) return -1;	

return 0;
}


public void Resolve()
{}





}