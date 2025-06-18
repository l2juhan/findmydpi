package findmydpi.main;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;

/**
 * 배경 이미지를 그리기 위한 패널
 * MainPagePanel에 extends로 쓰임
 */
public class BackgroundPanel extends JPanel {
    private Image background;
    public BackgroundPanel(Image img) {
        this.background = img;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }
}