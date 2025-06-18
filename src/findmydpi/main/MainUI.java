package findmydpi.main;

import javax.swing.SwingUtilities;

/**
 * 메인 클래스: EDT에서 UI를 시작
 */
public class MainUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}