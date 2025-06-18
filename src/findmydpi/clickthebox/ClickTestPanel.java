// File: src/findmydpi/clickpractice/ClickTestPanel.java
package findmydpi.clickthebox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * Click the Box Test 모드 패널 (ClickTestPanel)
 */
public class ClickTestPanel extends JPanel implements MouseListener {
    // 배경 이미지 로드
    private static final Image BG_IMAGE = new ImageIcon(
            ClickPracticePanel.class.getResource("/images/click_bg.png")
    ).getImage();
    // 감도 이름 레이블
    private static final String[] LABELS = {"low dpi", "current dpi", "high dpi"};
    private static final double[] SENS = {0.5, 1.0, 1.5};
    private final int[] scores = new int[SENS.length];
    private int idx;

    private Box box;
    private int secondsLeft;
    private javax.swing.Timer countdownTimer;
    private javax.swing.Timer spawnTimer;
    private final Random random = new Random();

    private JPanel cards;
    private CardLayout layout;

    public ClickTestPanel() {
        setBackground(Color.WHITE);
        addMouseListener(this);
    }

    /**
     * MainFrame에서 네비게이션 세팅
     */
    public void setNavigation(JPanel cards, CardLayout layout) {
        this.cards = cards;
        this.layout = layout;
    }

    /**
     * 테스트 시작
     */
    public void startTest() {
        idx = 0;
        for (int i = 0; i < scores.length; i++) scores[i] = 0;
        nextRound();
    }

    /**
     * 민감도별 라운드 실행 (30초)
     */
    private void nextRound() {
        if (idx >= SENS.length) {
            showResult();
            return;
        }
        secondsLeft = 30;
        spawnBox();

        // 박스 유지시간 설정(밀리초)
        int lifetime = (idx == 0) ? 850 : (idx == 1) ? 1100 : 1350;
        if (spawnTimer != null) spawnTimer.stop();
        spawnTimer = new javax.swing.Timer(lifetime, e -> spawnBox());
        spawnTimer.start();

        // 카운트다운 타이머
        if (countdownTimer != null) countdownTimer.stop();
        countdownTimer = new javax.swing.Timer(1000, e -> {
            secondsLeft--;
            if (secondsLeft <= 0) {
                countdownTimer.stop();
                spawnTimer.stop();
                idx++;
                nextRound();
            }
            repaint();
        });
        countdownTimer.start();
    }

    /**
     * 화면에 박스 하나 생성
     */
    private void spawnBox() {
        int size = 40;
        int w = getWidth();
        int h = getHeight() - 30;
        int x = random.nextInt(Math.max(1, w - size));
        int y = random.nextInt(Math.max(1, h - size)) + 30;
        box = new Box(x, y, size, size);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (BG_IMAGE != null) {
            g.drawImage(BG_IMAGE, 0, 0, getWidth(), getHeight(), this);
        }
        // 레이블 및 남은 시간 표시
        g.setColor(Color.BLACK);
        g.drawString(String.format("%s  Time: %d sec", LABELS[idx], secondsLeft), 10, 20);
        // 박스 그리기
        if (box != null) {
            g.setColor(Color.GREEN);
            g.fillRect(box.x, box.y, box.w, box.h);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = e.getPoint();
        if (box != null && box.rect.contains(p)) {
            scores[idx] += 5;
            spawnTimer.restart();  // 유지시간 리셋
            spawnBox();
        } else {
            scores[idx] -= 1;
        }
        repaint();
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    /**
     * 결과창 및 피드백, 메인 페이지로 복귀
     */
    private void showResult() {
        StringBuilder sb = new StringBuilder("Result Score\n");
        for (int i = 0; i < LABELS.length; i++) {
            sb.append(String.format("%s : %d\n", LABELS[i], scores[i]));
        }
        int max = Math.max(scores[0], Math.max(scores[1], scores[2]));
        boolean eq01 = scores[1] == max && (scores[0] == max || scores[2] == max);
        boolean eq02 = scores[0] == max && scores[2] == max;
        String feedback;
        if (eq02) feedback = "test one more time...";
        else if (scores[1] == max) feedback = "you find your dpi!";
        else if (scores[0] == max) feedback = "decrease your dpi";
        else feedback = "increase your dpi";

        sb.append("\nFeedback: ").append(feedback);
        JOptionPane.showMessageDialog(this, sb.toString(), "Result", JOptionPane.INFORMATION_MESSAGE);
        // MAIN 패널로 복귀
        layout.show(cards, "MAIN");
    }

    /**
     * 박스 위치 모델
     */
    static class Box {
        int x, y, w, h;
        Rectangle rect;
        Box(int x, int y, int w, int h) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
            this.rect = new Rectangle(x, y, w, h);
        }
    }
}
