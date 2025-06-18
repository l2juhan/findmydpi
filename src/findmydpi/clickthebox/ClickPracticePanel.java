// File: src/findmydpi/clickthebox/ClickPracticePanel.java
package findmydpi.clickthebox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * Click the Box Practice 모드 패널
 */
public class ClickPracticePanel extends JPanel implements MouseListener {
    // 배경 이미지 로드
    private static final Image BG_IMAGE = new ImageIcon(
            ClickPracticePanel.class.getResource("/images/click_bg.png")
    ).getImage();

    // 레이블 및 감도 설정 (현재 감도)
    private static final String LABEL = "current dpi";
    private static final int DURATION = 30;        // 30초
    private static final int BOX_LIFETIME = 1100;  // 1.1초

    private int score;
    private Box box;
    private int secondsLeft;
    private Timer countdownTimer;
    private Timer spawnTimer;
    private final Random random = new Random();

    // 네비게이션용 레퍼런스
    private JPanel cards;
    private CardLayout layout;

    public ClickPracticePanel() {
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
     * Practice 시작
     */
    public void startPractice() {
        score = 0;
        nextRound();
    }

    /**
     * 한 라운드 실행 (30초)
     */
    private void nextRound() {
        secondsLeft = DURATION;
        spawnBox();

        // 박스 재생성 타이머 (유지시간)
        if (spawnTimer != null) spawnTimer.stop();
        spawnTimer = new Timer(BOX_LIFETIME, e -> spawnBox());
        spawnTimer.start();

        // 카운트다운 타이머
        if (countdownTimer != null) countdownTimer.stop();
        countdownTimer = new Timer(1000, e -> {
            secondsLeft--;
            if (secondsLeft <= 0) {
                countdownTimer.stop();
                spawnTimer.stop();
                showPracticeResult();
            }
            repaint();
        });
        countdownTimer.start();
    }

    /**
     * 박스 화면에 생성
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
        // 배경 이미지 그리기
        if (BG_IMAGE != null) {
            g.drawImage(BG_IMAGE, 0, 0, getWidth(), getHeight(), this);
        }
        // 레이블 및 남은 시간 표시
        g.setColor(Color.BLACK);
        g.drawString(String.format("%s  Time: %d sec", LABEL, secondsLeft), 10, 20);
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
            score += 5;
            spawnTimer.restart();  // 유지시간 초기화
            spawnBox();
        } else {
            score -= 1;
        }
        repaint();
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    /**
     * Practice 결과창 표시 후 메인 화면으로 복귀
     */
    private void showPracticeResult() {
        String feedback;
        if (score < 65) feedback = "need to practice more....";
        else if (score < 75) feedback = "not bad...!";
        else feedback = "Perfect!!";

        JOptionPane.showMessageDialog(
                this,
                String.format("Score: %d\n%s", score, feedback),
                "Practice Result",
                JOptionPane.INFORMATION_MESSAGE
        );
        layout.show(cards, "MAIN");
    }

    /**
     * 박스 좌표 모델
     */
    static class Box {
        int x, y, w, h;
        Rectangle rect;
        Box(int x, int y, int w, int h) {
            this.x = x; this.y = y; this.w = w; this.h = h;
            this.rect = new Rectangle(x, y, w, h);
        }
    }
}
