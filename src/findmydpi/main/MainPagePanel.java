// File: src/findmydpi/main/MainPagePanel.java
package findmydpi.main;

import findmydpi.clickthebox.ClickPracticePanel;
import findmydpi.clickthebox.ClickTestPanel;
import findmydpi.textcopyandpaste.TextTestPanel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 메인 페이지: 배경 + 버튼 3개 (감도 설정, Click Practice/Test, Copy&Paste Practice/Test)
 */
public class MainPagePanel extends BackgroundPanel {
    private static final Image BACKGROUND_IMG =
            new ImageIcon(MainPagePanel.class.getResource("/images/main_bg.png")).getImage();

    private CardLayout cardLayout;
    private JPanel cards;

    // 테스트 및 연습 패널 참조
    private ClickTestPanel clickTestPanel;
    private ClickPracticePanel clickPracticePanel;
    private TextTestPanel textTestPanel;

    private static final Map<String,String> desc = new HashMap<>();
    static {
        desc.put("macos_mouse","시스템 환경설정→마우스→이동속도");
        desc.put("macos_trackpad","시스템 환경설정→트랙패드→이동속도");
        desc.put("windows_mouse","설정→장치→마우스→커서 속도");
        desc.put("windows_trackpad","설정→장치→터치 패드→커서 속도");
    }

    public MainPagePanel(JPanel cards, CardLayout layout) {
        super(BACKGROUND_IMG);
        this.cards = cards;
        this.cardLayout = layout;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("find my dpi",SwingConstants.CENTER);
        title.setFont(new Font("Arial",Font.BOLD,32));
        gbc.gridy=0; add(title,gbc);

        // 감도 설정 버튼
        JButton b1 = new JButton("마우스/트랙패드 감도 설정법");
        b1.addActionListener(e->onSensitivity());
        gbc.gridy=1; add(b1,gbc);

        // Click Practice/Test 버튼
        JButton b2 = new JButton("Click the box");
        b2.addActionListener(e -> {
            String m = (String) JOptionPane.showInputDialog(
                    this, "Mode 선택", "Click the box",
                    JOptionPane.QUESTION_MESSAGE, null,
                    new String[]{"Practice","Test"},"Practice");
            if (m != null) {
                if (m.equals("Test")) {
                    clickTestPanel.startTest();
                    cardLayout.show(cards, "CLICK_TEST");
                } else {
                    clickPracticePanel.startPractice();
                    cardLayout.show(cards, "CLICK_PRACTICE");
                }
            }
        });
        gbc.gridy=2; add(b2,gbc);

        // Copy & Paste Practice/Test 버튼
        JButton b3 = new JButton("Copy and Paste");
        b3.addActionListener(e -> {
            String m = (String) JOptionPane.showInputDialog(
                    this, "Mode 선택", "Copy & Paste",
                    JOptionPane.QUESTION_MESSAGE, null,
                    new String[]{"Practice","Test"},"Practice");
            if (m != null) {
                if (m.equals("Test")) {
                    textTestPanel.startTest();
                    cardLayout.show(cards, "TEXT_TEST");
                } else {
                    textTestPanel.startPractice();
                    cardLayout.show(cards, "TEXT_PRACTICE");
                }
            }
        });
        gbc.gridy=3; add(b3,gbc);
    }

    // 패널 참조 설정
    public void setClickTestPanel(ClickTestPanel cp) {
        this.clickTestPanel = cp;
    }
    public void setClickPracticePanel(ClickPracticePanel pp) {
        this.clickPracticePanel = pp;
    }
    public void setTextTestPanel(TextTestPanel tp) {
        this.textTestPanel = tp;
    }

    // 감도 설정 안내
    private void onSensitivity() {
        String os = (String) JOptionPane.showInputDialog(
                this, "OS 선택", "", JOptionPane.QUESTION_MESSAGE,
                null, new String[]{"macOS","Windows"},"macOS");
        if (os == null) return;
        String dev = (String) JOptionPane.showInputDialog(
                this, "장치 선택", "", JOptionPane.QUESTION_MESSAGE,
                null, new String[]{"Mouse","Trackpad"},"Mouse");
        if (dev == null) return;
        showDialog(os.toLowerCase() + "_" + dev.toLowerCase());
    }

    private void showDialog(String key) {
        String fn = key + ".png";
        JLabel pic = new JLabel(new ImageIcon(
                getClass().getResource("/images/" + fn)));
        JTextArea ta = new JTextArea(desc.get(key));
        ta.setLineWrap(true); ta.setWrapStyleWord(true);
        JDialog d = new JDialog((Frame)SwingUtilities.getWindowAncestor(this),"감도 설정법",true);
        d.setLayout(new BorderLayout());
        d.add(new JScrollPane(pic),BorderLayout.CENTER);
        d.add(new JScrollPane(ta),BorderLayout.SOUTH);
        d.setSize(800,800);
        d.setLocationRelativeTo(this);
        d.setVisible(true);
    }
}
