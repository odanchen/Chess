import board_package.Board;
import pieces.PieceColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display {

    public static void MainMenu() {

        JLabel Ltitle = new JLabel();
        Ltitle.setText("Chess Game");
        Ltitle.setFont(new Font("Serif", Font.PLAIN, 20));

        JPanel title = new JPanel();
        title.setBounds(220, 150, 150, 50);
        //title.setBackground(Color.green);
        title.add(Ltitle);

        Dimension BtnSize = new Dimension(130, 50);

        JButton start = new JButton("   Start   ");

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You started the game");
               GameControl gameRuner = new GameControl();
               gameRuner.runTheGame(new Board()/*i have no clue what to put here but when i find out i will put it here :)*/, PieceColor.WHITE);
            }
        });

        start.setPreferredSize(BtnSize);
        start.setBorderPainted(false);
        start.setFocusPainted(false);
        start.setContentAreaFilled(false);

        JButton settings = new JButton("Settings");
        settings.setPreferredSize(BtnSize);
        settings.setBorderPainted(false);
        settings.setFocusPainted(false);
        settings.setContentAreaFilled(false);

        JButton quit = new JButton("    Quit    ");
        quit.setPreferredSize(BtnSize);
        quit.setBorderPainted(false);
        quit.setFocusPainted(false);
        quit.setContentAreaFilled(false);

        JPanel buttons = new JPanel();
        buttons.setBounds(230, 250, 130, 170);
        //buttons.setBackground(Color.cyan);

        buttons.add(start);
        buttons.add(settings);
        buttons.add(quit);

        JFrame f = new JFrame("Chess");
        f.setSize(600, 600);
        f.setDefaultCloseOperation(3);
        f.setLayout(null);
        f.setVisible(true);
        f.setResizable(false);
        f.add(buttons);
        f.add(title);


    }

    public static void drawBoard(String textFamily ) throws IOException {
        Image imgs[] = new Image[12];
        String[] pieceText = {"bb.png", "bk.png", "bn.png", "bp.png", "bq.png", "br.png", "wb.png", "wk.png", "wn.png", "wp.png", "wq.png", "wr.png"};
        for(int i = 0; i<12; i++){
            BufferedImage image = ImageIO.read(new File("src/assets/pieces_textures/" + textFamily + "/" + pieceText[i]));
            imgs[i] = image.getSubimage(45, 45, 45,45);
        }


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
                // draw piece img here
            }
        };
        frame.add(pn);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        frame.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

    }

    public static void main(String[] args) {
        MainMenu();
    }
}

    public static void main(String[] args) {
        MainMenu();
    }
}
