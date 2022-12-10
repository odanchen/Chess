import pieces.ChessPiece;

import java.util.List;

public class Board {
    ColorPair boardColor = BoardColors.OPTION1;
    List<List<ChessPiece>> configuration;
    
     public static void main(String[] args) {//old code (not using ColorPair class) 
        JFrame frame = new JFrame();
        frame.setBounds(10,10,528,550);
        JPanel pn = new JPanel(){
            @Override
            public void paint(Graphics g){
                boolean white = true;
                for(int y = 0; y<8; y++){
                    for(int x = 0; x<8; x++){
                        if(white){
                            g.setColor(Color.lightGray);
                        }else {
                            g.setColor(Color.darkGray);
                        }
                        g.fillRect(x*64, y*64, 64,64);
                        white=!white;
                    }
                    white=!white;
                }
            }
        };
        frame.add(pn);
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }
}
