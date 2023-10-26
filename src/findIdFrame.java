import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class findIdFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    /* Panel */
    JPanel panel = new JPanel(new FlowLayout());

    /* Label */
    JLabel nameL = new JLabel("이름");
    JLabel nicknameL = new JLabel("닉네임");

    /* TextField */
    JTextField name = new JTextField();
    JTextField nickname = new JTextField();

    Client c = null;

    JButton findIdBtn = new JButton("id 찾기");
    JButton previousBtn = new JButton("이전 화면");

    final String fidTag = "FID"; // 아이디 찾기 태그

    public findIdFrame(Client _c) {
        c = _c;

        setTitle("ID 찾기");

        /* Panel 크기 작업 */
        panel.setPreferredSize(new Dimension(200, 100));

        /* Label 크기 작업 */
        nameL.setPreferredSize(new Dimension(50, 30));
        nicknameL.setPreferredSize(new Dimension(50, 30));

        /* TextField 크기 작업 */
        name.setPreferredSize(new Dimension(220, 30));
        nickname.setPreferredSize(new Dimension(220, 30));

        /* Button 크기 작업 */
        findIdBtn.setPreferredSize(new Dimension(135, 25));
        previousBtn.setPreferredSize(new Dimension(135, 25));

        /* Panel 추가 작업 */
        setContentPane(panel);

        panel.add(nameL);
        panel.add(name);
        panel.add(nicknameL);
        panel.add(nickname);
        panel.add(findIdBtn);
        panel.add(previousBtn);

        ButtonListener bl = new ButtonListener();
        findIdBtn.addActionListener(bl);
        previousBtn.addActionListener(bl);

        KeyBoardListener kl = new KeyBoardListener();
        nickname.addKeyListener(kl);

        /* Panel 크기및 동작 설정 */
        setSize(310, 150);
        setLocationRelativeTo(null);
        setVisible(false);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton) e.getSource();

            String uname = name.getText();
            String unickname = nickname.getText();

            if (b.getText().equals("id 찾기")) {
                if (uname.equals("") || unickname.equals("")) {
                    JOptionPane.showMessageDialog(null, "이름 혹은 닉네임을 입력해주세요", "검색 실패",
                            JOptionPane.ERROR_MESSAGE);
                }else {
                    c.sendMsg(fidTag + "//" + uname + "//" + unickname);
                }
            } else if (b.getText().equals("이전 화면")) {
                dispose();
            }
        }

    }

    class KeyBoardListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                String uname = name.getText();
                String unickname = nickname.getText();

                if (uname.equals("") || unickname.equals("")) {
                    JOptionPane.showMessageDialog(null, "이름, 닉네임을 확인해주세요", "검색 실패",
                            JOptionPane.ERROR_MESSAGE);
                }else {
                    c.sendMsg(fidTag + "//" + uname + "//" + unickname);
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

    }
}// 2023-09-04 - 소스 파일 주석 보완
// 2023-09-06 - Server 소켓 예외 처리 강화
// 2023-09-11 - 닉네임 중복 확인 로직 수정
// 2023-09-22 - 회원가입 시 이메일 형식 검증 보완
// 2023-09-24 - 방 목록 새로고침 타이밍 수정
// 2023-11-24 - DB 스키마 설명 주석 추가
// [2023-09-02 #1] 예외 처리 강화
// [2023-09-07 #1] 문자열 연결 StringBuilder 변환
// [2023-09-07 #6] 테두리 스타일 변경
// [2023-09-09 #7] 문자열 연결 StringBuilder 변환
// [2023-09-11 #6] 인코딩 처리
// [2023-09-15 #2] 인덱스 활용 개선
// [2023-09-16 #2] GC 힌트 추가
// [2023-09-17 #4] 스레드 안정성 개선
// [2023-09-18 #2] 메서드 분리
// [2023-09-18 #13] 화점 위치 보정
// [2023-09-19 #3] 로깅 추가
// [2023-09-20 #9] 설정 파일 로딩 캐시
// [2023-09-21 #4] 예외 처리 강화
// [2023-09-26 #7] 이모티콘 확장
// [2023-09-27 #7] 입력값 검증 추가
// [2023-09-29 #4] 변수명 가독성 개선
// [2023-09-29 #8] 입력값 검증 추가
// [2023-10-01 #6] 프로토콜 메시지 형식 정리
// [2023-10-03 #6] 스크롤 자동 이동
// [2023-10-03 #14] 에러 메시지 한글화
// [2023-10-04 #4] 포커스 처리
// [2023-10-06 #3] 문자열 연결 StringBuilder 변환
// [2023-10-08 #9] 승리 판정 엣지케이스 처리
// [2023-10-09 #5] 변수명 가독성 개선
// [2023-10-13 #11] EDT 스레드 안전성
// [2023-10-14 #6] 포커스 처리
// [2023-10-16 #4] EDT 스레드 안전성
// [2023-10-18 #1] SwingWorker 활용
// [2023-10-21 #5] 접근제한자 수정
// [2023-10-25 #9] SwingWorker 활용
// [2023-10-26 #13] 인코딩 처리
