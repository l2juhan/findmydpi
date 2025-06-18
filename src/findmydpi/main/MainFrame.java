// File: src/findmydpi/main/MainFrame.java
package findmydpi.main;

import findmydpi.clickthebox.ClickTestPanel;
import findmydpi.clickthebox.ClickPracticePanel;
import findmydpi.textcopyandpaste.TextPracticePanel;
import findmydpi.textcopyandpaste.TextTestPanel;

import javax.swing.*;
import java.awt.CardLayout;

/**
 * 메인 프레임: CardLayout으로 패널 전환
 */
public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cards;

    public MainFrame() {
        super("find my dpi");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // 1) CardLayout + cards 패널 생성
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        // 2) 각 패널 생성
        MainPagePanel mainPanel              = new MainPagePanel(cards, cardLayout);
        ClickTestPanel clickTestPanel       = new ClickTestPanel();
        ClickPracticePanel clickPracticePanel = new ClickPracticePanel();
        TextTestPanel textTestPanel         = new TextTestPanel();
        TextPracticePanel textPracticePanel    = new TextPracticePanel();

        // 3) Test/Practice 패널에 navigation 정보 세팅
        clickTestPanel.setNavigation(cards, cardLayout);
        clickPracticePanel.setNavigation(cards, cardLayout);
        textTestPanel.setNavigation(cards, cardLayout);
        textPracticePanel.setNavigation(cards, cardLayout);

        // 4) cards에 패널 추가 (키: MAIN, CLICK_TEST, CLICK_PRACTICE, TEXT_TEST, TEXT_PRACTICE)
        cards.add(mainPanel,         "MAIN");
        cards.add(clickTestPanel,    "CLICK_TEST");
        cards.add(clickPracticePanel,"CLICK_PRACTICE");
        cards.add(textTestPanel,     "TEXT_TEST");
        cards.add(textPracticePanel,     "TEXT_PRACTICE");

        // 5) JFrame에 cards 부착 후 초기 화면 설정
        add(cards);
        cardLayout.show(cards, "MAIN");

        // 6) MainPagePanel에 자식 패널 참조 전달
        mainPanel.setClickTestPanel(clickTestPanel);
        mainPanel.setClickPracticePanel(clickPracticePanel);
        mainPanel.setTextTestPanel(textTestPanel);
        mainPanel.setTextPracticePanel(textPracticePanel);
    }
}
