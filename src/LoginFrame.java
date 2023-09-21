import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//로그인 기능을 수행하는 인터페이스
public class LoginFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private ImageIcon backgroundIcon;

    /* Panel */
    JPanel basePanel;
    JPanel centerPanel = new JPanel(new BorderLayout());
    JPanel westPanel = new JPanel();
    JPanel eastPanel = new JPanel();
    JPanel southPanel = new JPanel();

    /* Label */
    JLabel idL = new JLabel("아이디");
    JLabel pwL = new JLabel("비밀번호");

    /* TextField */
    JTextField id = new JTextField();
    JPasswordField pw = new JPasswordField();

    /* Button */
    JButton loginBtn = new JButton("로그인");
    JButton joinBtn = new JButton("회원가입");
    JButton exitBtn = new JButton("게임종료");
    JButton fidBtn = new JButton("id 찾기");
    JButton fpwBtn = new JButton("pw 찾기");

    Client c = null;

    final String loginTag = "LOGIN"; // 로그인 기능 태그
    final String adminTag = "ADMIN"; // 관리자 로그인 태그

    LoginFrame(Client _c) {
        c = _c;

        setTitle("Cardinal Games - 오목");

        // 배경 이미지 설정
        backgroundIcon = null; // 배경 이미지 (리소스 폴더에서 로드)

        /* Panel 초기화 */
        basePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundIcon != null && backgroundIcon.getImage() != null) { g.drawImage(backgroundIcon.getImage(), 0, 0, getWidth(), getHeight(), this); } else { g.setColor(new Color(240, 230, 210)); g.fillRect(0, 0, getWidth(), getHeight()); }
            }
        };

        /* Panel 크기 작업 */
        centerPanel.setPreferredSize(new Dimension(290, 120));
        westPanel.setPreferredSize(new Dimension(210, 75));
        eastPanel.setPreferredSize(new Dimension(90, 75));
        southPanel.setPreferredSize(new Dimension(330, 120));

        /* Label 크기 작업 */
        idL.setPreferredSize(new Dimension(50, 50));
        pwL.setPreferredSize(new Dimension(50, 50));

        /* TextField 크기 작업 */
        id.setPreferredSize(new Dimension(140, 30));
        pw.setPreferredSize(new Dimension(140, 30));

        /* Button 크기 작업 */
        loginBtn.setPreferredSize(new Dimension(75, 93));
        joinBtn.setPreferredSize(new Dimension(275, 25));
        exitBtn.setPreferredSize(new Dimension(275, 25));
        fidBtn.setPreferredSize(new Dimension(135, 25));
        fpwBtn.setPreferredSize(new Dimension(135, 25));

        /* centerPaenl 투명하게 설정 */
        centerPanel.setOpaque(false);

        /* westPanel 투명하게 설정 */
        westPanel.setOpaque(false);

        /* eastPanel 투명하게 설정 */
        eastPanel.setOpaque(false);

        /* southPanel 투명하게 설정 */
        southPanel.setOpaque(false);

        /* Panel 추가 작업 */
        setContentPane(basePanel); // panel을 기본 컨테이너로 설정

        basePanel.add(centerPanel, BorderLayout.CENTER);
        basePanel.add(southPanel, BorderLayout.SOUTH);
        centerPanel.add(westPanel, BorderLayout.WEST);
        centerPanel.add(eastPanel, BorderLayout.EAST);

        westPanel.setLayout(new FlowLayout());
        eastPanel.setLayout(new FlowLayout());
        southPanel.setLayout(new FlowLayout());

        /* westPanel 컴포넌트 */
        westPanel.add(idL);
        westPanel.add(id);
        westPanel.add(pwL);
        westPanel.add(pw);

        /* eastPanel 컴포넌트 */
        eastPanel.add(loginBtn);

        /* southPanel 컴포넌트 */
        southPanel.add(fidBtn);
        southPanel.add(fpwBtn);
        southPanel.add(joinBtn);
        southPanel.add(exitBtn);

        /* Button 이벤트 리스너 추가 */
        ButtonListener bl = new ButtonListener();

        fidBtn.addActionListener(bl);
        fpwBtn.addActionListener(bl);
        loginBtn.addActionListener(bl);
        exitBtn.addActionListener(bl);
        joinBtn.addActionListener(bl);

        /* Key 이벤트 리스너 추가 */
        KeyBoardListener kl = new KeyBoardListener();

        id.addKeyListener(kl);
        pw.addKeyListener(kl);

        /* Panel 크기및 동작 설정 */
        setSize(310, 280);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /* Button 이벤트 리스너 */
    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton) e.getSource();

            /* TextField에 입력된 아이디와 비밀번호를 변수에 초기화 */
            String uid = id.getText();
            String upass = "";
            for (int i = 0; i < pw.getPassword().length; i++) {
                upass = upass + pw.getPassword()[i];
            }

            /* 게임종료 버튼 이벤트 */
            if (b.getText().equals("게임종료")) {
                System.out.println("[Client] 게임 종료");
                System.exit(0);
            }

            /* 회원가입 버튼 이벤트 */
            else if (b.getText().equals("회원가입")) {
                System.out.println("[Client] 회원가입 인터페이스 열림");
                c.jf.setVisible(true);
            }

            /* id 찾기 버튼 이벤트 */
            else if (b.getText().equals("id 찾기")) {
                c.fid.setVisible(true);
            }

            /* pw 찾기 버튼 이벤트*/
            else if (b.getText().equals("pw 찾기")) {
                c.fpw.setVisible(true);
            }

            /* 로그인 버튼 이벤트 */
            else if (b.getText().equals("로그인")) {
                if (uid.equals("") && !upass.equals("")) { // 아이디 미입력 시 로그인 시도 실패
                    JOptionPane.showMessageDialog(null, "아이디를 입력해주세요", "로그인 실패", JOptionPane.ERROR_MESSAGE);
                    System.out.println("[Client] 로그인 실패 : 아이디 미입력");
                }

                else if (!uid.equals("") && upass.equals("")) { // 비밀번호 미입력 시 로그인 시도 실패
                    JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요", "로그인 실패", JOptionPane.ERROR_MESSAGE);
                    System.out.println("[Client] 로그인 실패 : 비밀번호 미입력");
                }

                else if (!uid.equals("") && !upass.equals("")) { // 로그인 시도 성공

                    if (uid.equals("admin") && upass.equals("Admin123$")) {
                        JOptionPane.showMessageDialog(null, "관리자로 로그인하였습니다!", "로그인 성공", JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("[Client] 관리자로 로그인하였습니다!");
                        c.sendMsg(adminTag + "//" + uid + "//" + upass);
                    }else
                        c.sendMsg(loginTag + "//" + uid + "//" + upass); // 서버에 로그인 정보 전송
                }
            }
        }
    }

    /* Key 이벤트 리스너 */
    class KeyBoardListener implements KeyListener {
        @Override
        public void keyPressed(KeyEvent e) {
            /* Enter 키 이벤트 */
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                /* TextField에 입력된 아이디와 비밀번호를 변수에 초기화 */
                String uid = id.getText();
                String upass = "";
                for (int i = 0; i < pw.getPassword().length; i++) {
                    upass = upass + pw.getPassword()[i];
                }

                if (uid.equals("") && !upass.equals("")) {
                    JOptionPane.showMessageDialog(null, "아이디를 입력해주세요", "로그인 실패", JOptionPane.ERROR_MESSAGE);
                    System.out.println("[Client] 로그인 실패 : 아이디 미입력");
                }

                else if (!uid.equals("") && upass.equals("")) {
                    JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요", "로그인 실패", JOptionPane.ERROR_MESSAGE);
                    System.out.println("[Client] 로그인 실패 : 비밀번호 미입력");
                }

                else if (!uid.equals("") && !upass.equals("")) {
                    if (uid.equals("admin") && upass.equals("Admin123$")) {
                        JOptionPane.showMessageDialog(null, "관리자로 로그인하였습니다!", "로그인 성공", JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("[Client] 관리자로 로그인하였습니다!");
                        c.sendMsg(adminTag + "//" + uid + "//" + upass);
                    }else
                        c.sendMsg(loginTag + "//" + uid + "//" + upass);
                }
            }
        }

        public void keyTyped(KeyEvent e) {
        }

        public void keyReleased(KeyEvent e) {
        }
    }
}// 2023-09-08 - 로그인 실패 시 텍스트 필드 초기화
// 2023-09-18 - 로그인 기능 수동 테스트 완료
// 2023-10-14 - 방 입장 실패 시 다이얼로그 중복 표시 수정
// 2023-10-24 - 전적 업데이트 동시성 문제 보완
// 2023-10-31 - 코드 점검 및 미세 수정 (2023-10-31)
// 2023-11-13 - 프로필 이미지 크기 자동 조절
// 2023-12-11 - 방 입장 시 플레이어 이미지 로딩 순서
// [2023-09-02 #2] 키보드 단축키 추가
// [2023-09-02 #8] 예외 처리 강화
// [2023-09-03 #8] 이모티콘 확장
// [2023-09-05 #2] 패널 간격 조정
// [2023-09-08 #11] 보드 렌더링 최적화
// [2023-09-10 #6] 채팅 필터링 기반 코드
// [2023-09-10 #8] 화점 위치 보정
// [2023-09-10 #9] 쿼리 최적화
// [2023-09-11 #11] 소켓 타임아웃 설정
// [2023-09-13 #11] 경계값 체크
// [2023-09-16 #14] 커넥션 풀 기반 코드
// [2023-09-18 #6] 컬렉션 초기 용량 설정
// [2023-09-18 #10] 툴팁 추가
// [2023-09-19 #5] 스크롤 자동 이동
// [2023-09-19 #11] 커넥션 풀 기반 코드
// [2023-09-20 #12] 커넥션 풀 기반 코드
// [2023-09-21 #8] 게임 로직 검증
