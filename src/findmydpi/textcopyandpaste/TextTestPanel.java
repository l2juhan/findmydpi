package findmydpi.textcopyandpaste;

import javax.swing.*;
import java.awt.*;

/**
 * 복사 붙여넣기 연습 패널
 */
public class TextTestPanel extends JPanel {
    private JTextArea src;
    private JTextField input;
    private JButton submit;
    private long startTime;

    public TextTestPanel() {
        setLayout(new BorderLayout());
        src = new JTextArea("...여기에 연습할 긴 텍스트...");
        src.setLineWrap(true);
        src.setWrapStyleWord(true);
        src.setEditable(false);
        add(new JScrollPane(src), BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout());
        input = new JTextField();
        submit = new JButton("Submit");
        submit.addActionListener(e -> onSubmit());
        bottom.add(input, BorderLayout.CENTER);
        bottom.add(submit, BorderLayout.EAST);
        add(bottom, BorderLayout.SOUTH);
    }

    /** MainPagePanel에서 호출되는 메서드 */
    public void startTest() {
        // 테스트 시작 시각 기록
        startTime = System.currentTimeMillis();
        input.setText("");
    }

    private void onSubmit() {
        long elapsed = System.currentTimeMillis() - startTime;
        String user = input.getText();
        String target = src.getText();
        // (여기서 정확도 계산 로직을 넣으세요)
        /*
        int accuracy = ;

        JOptionPane.showMessageDialog(
                this,
                String.format("Accuracy: %d%%\nTime: %.2f sec", accuracy, elapsed/1000.0),
                "Result",
                JOptionPane.INFORMATION_MESSAGE
        );
        SwingUtilities.getWindowAncestor(this).dispose();
        */
    }

    public void startPractice() {
    }

    public void setNavigation(JPanel cards, CardLayout cardLayout) {
    }
}
