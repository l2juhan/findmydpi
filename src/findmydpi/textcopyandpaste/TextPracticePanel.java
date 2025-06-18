package findmydpi.textcopyandpaste;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * Copy & Paste Practice 모드 패널
 */
public class TextPracticePanel extends TextTestPanel {
    /** Practice 시작 */
    public void startPractice() {
        // Practice 모드는 Test와 동일한 흐름을 사용하되, 시간 페널티는 없음
        super.startTest();
    }

    @Override
    protected void showResult() {
        // Practice 전용 메시지
        JOptionPane.showMessageDialog(this,
                "Practice complete!", "Practice Result",
                JOptionPane.INFORMATION_MESSAGE);
        // 메인 화면 복귀
        super.layout.show(super.cards, "MAIN");
    }
}