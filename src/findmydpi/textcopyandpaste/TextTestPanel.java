// File: src/findmydpi/textcopyandpaste/TextTestPanel.java
package findmydpi.textcopyandpaste;

import findmydpi.clickthebox.ClickPracticePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * Copy & Paste Test 모드 패널
 */
public class TextTestPanel extends JPanel {

    private static final String[] SENTENCES = {
            // TODO: 10개의 긴 문장을 여기에 채워 주세요.
            "언제부턴가 갈대는 속으로 조용히 울고 있었다.\n" +
                    "그런 어느 밤이었을 것이다. 그의 온몸이 흔들리고 있는 것을 알았다.\n" +
                    "바람도 달빛도 아닌 것. 갈대는 저를 흔드는 것이 제 조용한 울음인 것을 까맣게 몰랐다.\n" +
                    "산다는 것은 속으로 이렇게 조용히 울고 있는 것이란 것을 그는 몰랐다.",
            "죽는 날까지 하늘을 우러러 한 점 부끄럼이 없기를,\n" +
                    "잎새에 이는 바람에도 나는 괴로워했다.\n" +
                    "별을 노래하는 마음으로 모든 죽어가는 것을 사랑해야지.\n" +
                    "그리고 나한테 주어진 길을 걸어가야겠다.\n" +
                    "오늘 밤에도 별이 바람에 스치운다.",
            "열무 삼십 단을 이고 시장에 간 우리 엄마\n" +
                    "안 오시네, 해는 시든 지 오래\n" +
                    "나는 찬밥처럼 방에 담겨 아무리 천천히 숙제를 해도\n" +
                    "엄마 안 오시네, 배추잎 같은 발소리 타박타박\n" +
                    "안 들리네, 어둡고 무서워 금 간 창 틈으로 고요히 빗소리 빈 방에 혼자 엎드려 훌쩍거리던\n" +
                    "아주 먼 옛날 지금도 내 눈시울을 뜨겁게 하는 그 시절, 내 유년의 윗목.",
            "좋아요. 좋다고 하니까 나도 좋다.\n" +
                    "그래요. 새파란 물감을 머금은 하늘이\n" +
                    "선선히 불어오는 이 바람이\n" +
                    "오가는 사람들 미소 띈 모습이\n" +
                    "그리고, 이 글을 읽고 있는 그대가\n" +
                    "좋아요. 좋아요. 참 좋아요.",
            "낮은 곳에 있고 싶었다.\n" +
                    "낮은 곳이라면 지상의 그 어디라도 좋다.\n" +
                    "찰랑찰랑 물처럼 고여들어 네 사랑을 온몸으로 받아들일 수만 있다면\n" +
                    "한 방울도 헛되이 새어 나가지 않게 할 수만 있다면\n" +
                    "그래, 내가 낮은 곳에 있겠다는 건\n" +
                    "너를 위해 나를 온전히 배우겠다는 뜻이다.\n" +
                    "나의 존재마저 너에게 흠뻑 주고 싶다는 뜻이다.\n" +
                    "잠겨 죽어도 좋으니 너는 물처럼 내게 밀려오라.\n",
            "다시 한 번만 사랑하고 다시 한 번만 죄를 짓고\n" +
                    "다시 한 번만 용서를 받자 그래서 봄이다.",
            "사람들을 너무 많이 만나면 말에 취해서 멀미가 나고,\n" +
                    "꽃들을 너무 많이 대하면 향기에 취해서 멀미가 나지.\n" +
                    "살아있는 것은 아픈 것, 아름다운 것은 어지러운 것.\n" +
                    "너무 많아도 싫지 않은 꽃을 보면서 나는 더욱\n" +
                    "사람들을 사랑하기 시작하지.",
            "산모퉁이를 돌아 논가 외딴 우물을 홀로 찾아가선 가만히 들여다봅니다.\n" +
                        " 우물 속에는 달이 밝고 구름이 흐르고 하늘이 펼치고 파아란 바람이 불고 가을이 있읍니다.\n" +
                        " 그리고 한 사나이가 있읍니다. 어쩐지 그 사나이가 미워져 돌아갑니다.\n" +
                        " 어쩐지 그 사나이가 미워져 돌아갑니다. 돌아가다 생각하니 그 사나이가 가엾어집니다.\n" +
                        " 도로 가 들여다보니 사나이는 그대로 있읍니다. 다시 그 사나이가 미워져 돌아갑니다.\n" +
                        " 돌아가다 생각하니 그 사나이가 그리워집니다. 우물 속에는 달이 밝고 구름이 흐르고 하늘이 펼치고 파아란 바람이 불고 가을이 있고 추억처럼 사나이가 있읍니다.\n",
            "너 처음 만났을 때 사랑한다 이 말은 너무 작았다.\n" +
                        "같이 살자 이 말은 너무 흔했다.\n" +
                        "그래서 너를 두곤 목숨을 내걸었다.\n" +
                        "목숨의 처음과 끝 천국에서 지옥까지 가고 싶었다.\n" +
                        "맨발로 너와 함께 타오르고 싶었다. 죽고 싶었다.\n",
            "고달프지 않는 인생이 어디있고, 외롭지 않는 인생이 어디 있더냐.\n" +
                        "자기 인생은 자기 혼자서 갈 뿐이다\n" +
                        "남이 가르쳐 주는 건 그 사람이 겪은 과거일 뿐이고, 내가 해야 할 일은 혼자서 겪어 나아가야 하는 나의 미래다."

    };
    private int currentIndex;
    private String fragment;
    private String sentence;
    private long questionStartTime;
    private int score;
    private int remainingQuestions;

    private JToolBar toolBar;
    private JTextField inputField;
    private JButton submitButton;
    private JLabel promptLabel;
    private JTextArea fullTextArea;

    // 네비게이션 참조
    protected JPanel cards;
    protected CardLayout layout;

    public TextTestPanel() {
        setLayout(new BorderLayout());
        // 툴바 세팅
        toolBar = new JToolBar();
        inputField = new JTextField(20);
        submitButton = new JButton("Submit");
        toolBar.add(inputField);
        toolBar.add(submitButton);
        add(toolBar, BorderLayout.NORTH);

        // 중앙 영역: prompt + full text
        promptLabel = new JLabel("Paste this text: ");
        //프롬프트 글씨 크 키우기
        promptLabel.setFont(promptLabel.getFont().deriveFont(16f));
        promptLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        fullTextArea = new JTextArea(5, 40);
        //텍스트 글씨 크기 키우기
        fullTextArea.setFont(fullTextArea.getFont().deriveFont(16f));
        fullTextArea.setLineWrap(true);
        fullTextArea.setWrapStyleWord(true);
        fullTextArea.setEditable(false);
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(promptLabel, BorderLayout.NORTH);
        centerPanel.add(new JScrollPane(fullTextArea), BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        submitButton.addActionListener(e -> checkAnswer());
    }
    //네비게이션 설정
    public void setNavigation(JPanel cards, CardLayout layout) {
        this.cards = cards;
        this.layout = layout;
    }

    /** 테스트 시작 */
    public void startTest() {
        score = 0;
        remainingQuestions = SENTENCES.length;
        nextQuestion();
    }

    /** 다음 문제 */
    private void nextQuestion() {
        if (remainingQuestions == 0) {
            showResult();
            return;
        }
        // 랜덤 문장 선택 후 순서 변경으로 중복 방지
        currentIndex = new Random().nextInt(remainingQuestions);
        sentence = SENTENCES[currentIndex];
        String temp = SENTENCES[currentIndex];
        SENTENCES[currentIndex] = SENTENCES[remainingQuestions-1];
        SENTENCES[remainingQuestions-1] = temp;
        remainingQuestions--;

        // 랜덤 길이 8~12, 시작위치
        int len = 8 + new Random().nextInt(5);
        int start = new Random().nextInt(Math.max(1, sentence.length() - len));
        fragment = sentence.substring(start, start + len).trim();

        promptLabel.setText("Paste this text:" + fragment);
        fullTextArea.setText(sentence);
        inputField.setText("");
        questionStartTime = System.currentTimeMillis();
    }

    /** 사용자 입력 검사 */
    private void checkAnswer() {
        String user = inputField.getText().trim();
        long elapsed = (System.currentTimeMillis() - questionStartTime) / 1000;
        if (fragment.equals(user)) {
            if (elapsed <= 10) score += 10;
            else if (elapsed <= 15) score += 5;
            else score += 3;
        } else {
            score -= 5;
        }
        nextQuestion();
    }

    /** 결과 출력 */
    protected void showResult() {
        String feedback;
        if (score >= 90) feedback = "Perfect!!";
        else if (score >= 80) feedback = "Need to practice";
        else feedback = "Need to change your dpi,,,";
        JOptionPane.showMessageDialog(
                this,
                String.format("Score: %d\n%s", score, feedback),
                "Test Result",
                JOptionPane.INFORMATION_MESSAGE
        );
        layout.show(cards, "MAIN");
    }
}



