
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JApplet;

public class OthelloGUI extends JApplet implements MouseListener {

    Othello othello = new Othello();
    int hamle = othello.kareSay, sira = 2;
    BufferedImage s, b, g, t;

    public void init() {
        try {
            // TODO start asynchronous download of heavy resources
            s = ImageIO.read(this.getClass().getResource("s.png"));
            b = ImageIO.read(this.getClass().getResource("b.png"));
            g = ImageIO.read(this.getClass().getResource("golge.png"));
            t = ImageIO.read(this.getClass().getResource("tahta.png"));
        } catch (IOException ex) {
            System.out.println("Resim okunamadı..");
        }
        setSize(600, 480);
        setBackground(Color.green);
        this.addMouseListener(this);
    }

    public void hamle(int p, int x, int y) {
        if (othello.check(p, x, y)) {
            othello.put(p, x, y);
            repaint();
            hamle++;
            sira = (sira == 1) ? 2 : 1;
            if (sira == 1 && !othello.isAvaliable(1)) {
                sira = 2;
            }
            repaint();
            if (sira == 1) {
                compHamle();
            }

        }
        repaint();
    }

    public void compHamle() {

        AI_put yz = othello.yz.getBest(1);
        if (yz != null) {
            System.out.println("Bilgisayar Hamlesi!(" + yz.x + "," + yz.y + ")");
            if (othello.check(1, yz.x, yz.y)) {
                othello.put(1, yz.x, yz.y);
                hamle++;
                sira = 2;
            }
        } else {
            System.out.println("en iyi sonuç alınamadı");
            sira = 1;
        }

        if (!othello.isAvaliable(2)) {
            compHamle();
        }

        repaint();
    }

    public void paint(Graphics g) {

        drawBoard(g);
        writePoints(g);
        drawDots(g);
        drawShadows(g);

    }

    public void writePoints(Graphics g) {
        g.drawImage(b, 500, 20, this);
        g.drawString(othello.p2 + "", 530, 50);
        g.setColor(Color.white);
        g.drawImage(s, 500, 80, this);
        g.drawString(othello.p1 + "", 530, 110);
    }

    public void drawBoard(Graphics g) {
        g.drawImage(t, 0, 0, this);
//        g.setColor(Color.green);
//        g.fillRect(0, 0, 500, 500);
//        g.setColor(Color.black);
//        for (int i = 0; i < 9; i++) {
//            g.drawLine(i * 31, 0, i * 31, 248);
//            g.drawLine(0, i * 31, 248, i * 31);
//        }

    }

    public void drawDots(Graphics g) {
        BufferedImage bim;
        for (int i = 0; i < othello.board.length; i++) {
            for (int j = 0; j < othello.board[0].length; j++) {
                if (!othello.board[i][j].isFresh) {
                    bim = (othello.board[i][j].type == 1) ? s : b;
                    g.drawImage(bim, i * 60, j * 60, this);
                }
            }
        }
    }

    public void drawShadows(Graphics g) {
        BufferedImage bim = this.g;
        for (int i = 0; i < othello.board.length; i++) {
            for (int j = 0; j < othello.board[0].length; j++) {
                if (othello.board[i][j].isFresh && othello.check(sira, i, j)) {
                    g.drawImage(bim, i * 60, j * 60, this);
                }
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
        int posX = e.getX() / 60, posY = e.getY() / 60;
        if (othello.isFinish()) {
            othello.refreshBoard();
            sira = 2;
        } else {
            hamle(2, posX, posY);
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
    // TODO overwrite start(), stop() and destroy() methods

    public void run() {
        while (true) {
            try {
                //Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
