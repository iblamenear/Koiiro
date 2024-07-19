import javax.swing.*;
import java.awt.*;

public class Koiiro extends JPanel {

    private String[] lyrics = {
            "Suki Yo",
            "Ima Anata Ni Omoi Nosete",
            "Hora",
            "Sunao Ni Naru No Watashi",
            "Kono Saki Motto",
            "Soba Ni Ite Mo ii Ka Na",
            "Koi To Koi Ga Kasanatte",
            "Suki Yo",
    };

    private int[] delays = {
            500, 200, 400, 160, 500, 300, 400, 500
    };

    private int currentIndex = 0;
    private String currentLine = "";
    private int currentCharIndex = 0;
    private ImageIcon backgroundGif;

    public Koiiro() {
        setPreferredSize(new Dimension(800, 600));
        backgroundGif = new ImageIcon("Sky.gif");
        new Thread(() -> {
            try {
                while (currentIndex < lyrics.length) {
                    if (currentCharIndex < lyrics[currentIndex].length()) {
                        currentLine += lyrics[currentIndex].charAt(currentCharIndex);
                        currentCharIndex++;
                        repaint();
                        Thread.sleep(130);
                    } else {                        Thread.sleep(delays[currentIndex]);
                        currentIndex++;
                        currentLine = "";
                        currentCharIndex = 0;
                        repaint();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundGif != null) {
            g.drawImage(backgroundGif.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
        g.setFont(new Font("Borsok", Font.PLAIN, 32));
        g.setColor(Color.white);

        int y = (getHeight() - lyrics.length * 30) / 2;

        int stringWidth = g.getFontMetrics().stringWidth(currentLine);
        int x = (getWidth() - stringWidth) / 2;
        g.drawString(currentLine, x, y + currentIndex * 30);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Lyrics");
        Koiiro panel = new Koiiro();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
