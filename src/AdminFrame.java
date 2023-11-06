import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class AdminFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    Client c = null;

    /* JLabel */
    JLabel adminL = new JLabel("관리자 모드");

    /* model */
    AdminModel model = new AdminModel();

    /* Panel */
    JPanel adminLabelPanel = new JPanel();
    JPanel categoryPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    JPanel btnPanel = new JPanel();

    /* JTable */
    JTable memberTable = new JTable();

    /* JScrollPane */
    JScrollPane memberSp = new JScrollPane();

    /* JComboBox */
    JComboBox<String> chCategory = new JComboBox<>();

    /* JTextField */
    JTextField keyword = new JTextField();

    /* JButton */
    JButton selectBtn = new JButton();
    JButton getAllBtn = new JButton();
    JButton registerBtn = new JButton();
    JButton modifyBtn = new JButton();
    JButton deleteBtn = new JButton();
    JButton exitBtn = new JButton();

    String category = "";

    /* Tag */
    final String viewTag = "VIEW"; // 회원정보조회
    final String viewDBTag = "VIEWDB"; // DB조회태그
    final String pexitTag = "PEXIT"; // 프로그램종료
    final String chSIdTag = "CHSID"; // 서버에서 처리할 아이디 변경
    final String unRegisterIdTag = "UNREGI"; // 회원 탈퇴 태그

    AdminFrame(Client _c) {
        c = _c;

        setTitle("--관리자 프레임--");

        /* JLabel */
        adminL.setPreferredSize(new Dimension(100, 35));
        adminL.setBackground(Color.WHITE);
        adminL.setFont(new Font("HY견고딕", Font.PLAIN, 17));

        /* JTextField */
        keyword.setPreferredSize(new Dimension(80, 26));

        /* JTable */
        memberTable = new JTable(model);

        /* ScrollPane */
        memberSp = new JScrollPane(memberTable);
        memberSp.setPreferredSize(new Dimension(700, 400));

        /* JComboBox */
        chCategory = new JComboBox<String>();
        chCategory.addItem("카테고리");
        chCategory.addItem("name");
        chCategory.addItem("nickname");
        chCategory.addItem("id");
        chCategory.addItem("password");
        chCategory.addItem("address");
        chCategory.addItem("gender");
        chCategory.addItem("birth");
        chCategory.addItem("email");

        /* Button */
        selectBtn = new JButton("조회");
        getAllBtn = new JButton("전체 조회");
        registerBtn = new JButton("등록");
        modifyBtn = new JButton("수정");
        deleteBtn = new JButton("삭제");
        exitBtn = new JButton("종료");

        /* Panel */
        adminLabelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        adminLabelPanel.setPreferredSize(new Dimension(700, 80));
        adminLabelPanel.setBackground(Color.BLACK);
        adminLabelPanel.add(adminL);
        adminLabelPanel.add(categoryPanel);

        categoryPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        categoryPanel.setPreferredSize(new Dimension(700, 40));
        categoryPanel.setBackground(Color.BLUE);
        categoryPanel.add(chCategory);
        categoryPanel.add(keyword);
        categoryPanel.add(selectBtn);
        categoryPanel.add(getAllBtn);

        btnPanel.add(registerBtn);
        btnPanel.add(modifyBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(exitBtn);

        centerPanel.add(adminLabelPanel, BorderLayout.NORTH);

        centerPanel.add(memberSp);
        centerPanel.add(btnPanel, BorderLayout.SOUTH);

        /* ButtonListener 장착 */
        ButtonListener bl = new ButtonListener();
        registerBtn.addActionListener(bl);
        modifyBtn.addActionListener(bl);
        deleteBtn.addActionListener(bl);
        selectBtn.addActionListener(bl);
        getAllBtn.addActionListener(bl);
        exitBtn.addActionListener(bl);

        /* Panel 크기및 동작 설정 */
        setSize(800, 580);
        setResizable(false);
        setVisible(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.add(centerPanel);

        chCategory.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                category = (String) ie.getItem();
            }
        });
    }

    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton) e.getSource();

            if (b.getText().equals("조회")) { // 입력한 카테고리와 값으로 회원 검색
                String selectedCategory = (String) chCategory.getSelectedItem();
                String keywordInput = keyword.getText();
                if (selectedCategory.equals("카테고리")) {
                    JOptionPane.showMessageDialog(null, "카테고리를 선택해주세요.", "카테고리 미선택", JOptionPane.ERROR_MESSAGE);
                } else if (keywordInput.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "검색어를 입력해주세요.", "검색어 미입력", JOptionPane.ERROR_MESSAGE);
                } else {
                    c.sendMsg(viewTag + "//" + category + "//" + keyword.getText());
                }
            } else if (b.getText().equals("등록")) { // 회원가입 프레임 띄움
                c.jf.setVisible(true);
            } else if (b.getText().equals("수정")) { // 회원 수정 -> 이름 비밀번호 이메일만 가능
                String inputId = JOptionPane.showInputDialog("수정할 계정의 id를 입력");
                keyword.setText(inputId);
                c.sendMsg(chSIdTag + "//" + inputId);
                if (inputId == null) {
                    JOptionPane.showMessageDialog(null, "수정할 계정을 확인해주세요.", "계정 id 확인", JOptionPane.ERROR_MESSAGE);
                } else {
                    c.cinf.setVisible(true);
                }
            } else if (b.getText().equals("삭제")) { // 회원 삭제 -> 백업테이블로 이동
                String inputId = JOptionPane.showInputDialog("삭제할 계정의 id를 입력");
                keyword.setText(inputId);
                if (inputId == null) {
                    JOptionPane.showMessageDialog(null, "삭제할 계정을 확인해주세요.", "계정 id 확인", JOptionPane.ERROR_MESSAGE);
                } else {
                    c.sendMsg(unRegisterIdTag + "//" + keyword.getText());
                }
            } else if (b.getText().equals("전체 조회")) { // 회원 전체 조회
                c.sendMsg(viewDBTag);
            } else if (b.getText().equals("종료")) { // 서버에 존재하는 접속유저벡터에서 해당 클라이언트를 지움
                c.sendMsg(pexitTag);
                System.exit(0);
            }
        }
    }
}
// 2023-09-30 - 코드 점검 및 미세 수정 (2023-09-30)
// 2023-11-22 - SRankFrame 폰트 크기 조정
// 2023-12-09 - 이미지 인코딩/디코딩 유틸 분리
// 2023-12-22 - 프로젝트 구조 다이어그램 추가
// 2023-12-23 - 연결 끊김 시 자동 재연결 기반 코드
// 2023-12-27 - 코드 점검 및 미세 수정 (2023-12-27)
// [2023-09-01 #4] 경계값 체크
// [2023-09-02 #10] 툴팁 추가
// [2023-09-06 #6] 화점 위치 보정
// [2023-09-06 #9] 프로필 이미지 캐싱
// [2023-09-07 #4] 상수 정리
// [2023-09-09 #3] 쿼리 최적화
// [2023-09-12 #1] 주석 보완
// [2023-09-12 #2] 변수명 가독성 개선
// [2023-09-12 #3] 주석 보완
// [2023-09-12 #13] 버퍼 크기 최적화
// [2023-09-13 #2] 불필요한 객체 생성 제거
// [2023-09-14 #10] null 체크 추가
// [2023-09-16 #10] 이벤트 리스너 분리
// [2023-09-16 #11] 설정 파일 로딩 캐시
// [2023-09-21 #3] 동기화 처리 개선
// [2023-09-21 #6] 스크롤 자동 이동
// [2023-09-22 #7] GC 힌트 추가
// [2023-09-24 #5] 재연결 로직 기반
// [2023-09-26 #1] 파일 입출력 버퍼링
// [2023-09-27 #13] 패널 간격 조정
// [2023-09-30 #2] 패널 간격 조정
// [2023-10-01 #3] 입력값 검증 추가
// [2023-10-03 #1] 재연결 로직 기반
// [2023-10-03 #2] 스크롤 자동 이동
// [2023-10-08 #8] 스크롤 자동 이동
// [2023-10-11 #6] 로깅 추가
// [2023-10-14 #2] 로그 레벨 분류
// [2023-10-16 #10] 상수 정리
// [2023-10-20 #6] 레이아웃 미세 조정
// [2023-10-25 #3] 인코딩 처리
// [2023-10-26 #10] 입력값 검증 추가
// [2023-10-28 #3] 로그 레벨 분류
// [2023-10-28 #4] 프로토콜 메시지 형식 정리
// [2023-11-01 #1] 화점 위치 보정
// [2023-11-04 #8] 쿼리 최적화
// [2023-11-06 #7] 미사용 import 제거
