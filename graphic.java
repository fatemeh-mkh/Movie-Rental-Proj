import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class graphic {
    public void graphicFunc(String str,Color color){
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Graphic Output Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 20));
                    g.setColor(color);
                    g.drawString(str, 50, 50);
                }
            };
            frame.add(panel);
            frame.setSize(2000, 800);
            frame.setVisible(true);
        });
    }

}
