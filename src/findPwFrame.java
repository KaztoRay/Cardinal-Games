import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class findPwFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    /* Panel */
    JPanel panel = new JPanel(new FlowLayout());

    /* Label */
    JLabel nameL = new JLabel("이름");
    JLabel nicknameL = new JLabel("닉네임");
    JLabel idL = new JLabel("id");

    /* TextField */
    JTextField name = new JTextField();
    JTextField nickname = new JTextField();
    JTextField id = new JTextField();

    Client c = null;

    JButton findPwBtn = new JButton("pw 찾기");
    JButton previousBtn = new JButton("이전 화면");

    final String fpwTag = "FPW"; // 아이디 찾기 태그

    public findPwFrame(Client _c) {
        c = _c;

        setTitle("ID 찾기");

        /* Panel 크기 작업 */
        panel.setPreferredSize(new Dimension(200, 100));

        /* Label 크기 작업 */
        nameL.setPreferredSize(new Dimension(50, 30));
        nicknameL.setPreferredSize(new Dimension(50, 30));
        idL.setPreferredSize(new Dimension(50, 30));

        /* TextField 크기 작업 */
        name.setPreferredSize(new Dimension(220, 30));
        nickname.setPreferredSize(new Dimension(220, 30));
        id.setPreferredSize(new Dimension(220, 30));

        /* Button 크기 작업 */
        findPwBtn.setPreferredSize(new Dimension(135, 25));
        previousBtn.setPreferredSize(new Dimension(135, 25));

        /* Panel 추가 작업 */
        setContentPane(panel);

        panel.add(nameL);
        panel.add(name);
        panel.add(nicknameL);
        panel.add(nickname);
        panel.add(idL);
        panel.add(id);
        panel.add(findPwBtn);
        panel.add(previousBtn);

        ButtonListener bl = new ButtonListener();
        findPwBtn.addActionListener(bl);
        previousBtn.addActionListener(bl);

        KeyBoardListener kl = new KeyBoardListener();
        id.addKeyListener(kl);

        /* Panel 크기및 동작 설정 */
        setSize(310, 180);
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
            String uid = id.getText();

            if (b.getText().equals("pw 찾기")) {
                if (uname.equals("") || unickname.equals("") || uid.equals("")) {
                    JOptionPane.showMessageDialog(null, "이름,닉네임,id를 확인해주세요", "검색 실패",
                            JOptionPane.ERROR_MESSAGE);
                }else {
                    c.sendMsg(fpwTag + "//" + uname + "//" + unickname + "//" + uid);
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
                String uid = id.getText();

                if (uname.equals("") || unickname.equals("") || uid.equals("")) {
                    JOptionPane.showMessageDialog(null, "이름 혹은 닉네임을 입력해주세요", "검색 실패",
                            JOptionPane.ERROR_MESSAGE);
                }else {
                    c.sendMsg(fpwTag + "//" + uname + "//" + unickname + "//" + uid);
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

    }
}// 2023-10-02 - 오목 돌 좌표 계산 오프셋 수정
// 2023-10-19 - 회원정보 변경 후 화면 갱신
// 2023-11-17 - CInfoFrame 버튼 배치 수정
// 2023-11-23 - 회원가입 생년월일 유효성 검사
// 2023-11-28 - 코드 점검 및 미세 수정 (2023-11-28)
// 2023-12-24 - 빌드 스크립트 추가
// [2023-09-03 #3] 인덱스 활용 개선
// [2023-09-03 #7] 보드 렌더링 최적화
// [2023-09-04 #1] 게임 로직 검증
// [2023-09-04 #9] 버튼 크기 조정
// [2023-09-05 #10] 폰트 크기 조정
// [2023-09-06 #1] 리소스 해제 추가
// [2023-09-09 #6] 이모티콘 확장
// [2023-09-13 #4] 프로필 이미지 캐싱
// [2023-09-16 #7] EDT 스레드 안전성
// [2023-09-16 #12] 미사용 import 제거
// [2023-09-16 #13] 리소스 해제 추가
// [2023-09-18 #8] 동기화 처리 개선
// [2023-09-19 #7] 돌 배치 애니메이션 기반
// [2023-09-19 #8] 스크롤 자동 이동
// [2023-09-20 #1] 버퍼 크기 최적화
// [2023-09-22 #10] 소켓 타임아웃 설정
// [2023-09-23 #2] EDT 스레드 안전성
// [2023-09-24 #7] 화점 위치 보정
// [2023-09-25 #10] 변수명 가독성 개선
// [2023-09-30 #5] UI 응답성 개선
// [2023-10-02 #9] 포커스 처리
// [2023-10-03 #11] 불필요한 객체 생성 제거
// [2023-10-05 #6] 트랜잭션 처리
// [2023-10-06 #7] 색상 코드 정리
// [2023-10-08 #10] 채팅 필터링 기반 코드
// [2023-10-12 #8] 버튼 크기 조정
// [2023-10-13 #4] 게임 로직 검증
// [2023-10-13 #9] 로직 최적화
// [2023-10-14 #1] 설정 파일 로딩 캐시
// [2023-10-14 #8] 포커스 처리
// [2023-10-15 #9] 키보드 단축키 추가
// [2023-10-15 #12] 버퍼 크기 최적화
// [2023-10-17 #4] 패널 간격 조정
// [2023-10-18 #7] 코드 들여쓰기 통일
// [2023-10-18 #8] 폰트 크기 조정
// [2023-10-19 #9] 인코딩 처리
// [2023-10-19 #11] 에러 메시지 한글화
// [2023-10-26 #2] EDT 스레드 안전성
// [2023-10-26 #9] 좌표 계산 정밀도 개선
// [2023-10-28 #8] 키보드 단축키 추가
// [2023-11-03 #2] 다이얼로그 메시지 수정
// [2023-11-05 #9] 컬렉션 초기 용량 설정
// [2023-11-09 #6] 보드 렌더링 최적화
// [2023-11-09 #11] 패널 간격 조정
// [2023-11-10 #3] 폰트 크기 조정
// [2023-11-12 #9] 이모티콘 확장
// [2023-11-13 #5] 재연결 로직 기반
// [2023-11-13 #7] 에러 메시지 한글화
// [2023-11-15 #6] 컬렉션 초기 용량 설정
// [2023-11-15 #7] 레이아웃 미세 조정
// [2023-11-17 #12] 쿼리 최적화
// [2023-11-18 #9] 게임 로직 검증
// [2023-11-21 #2] 화점 위치 보정
// [2023-11-21 #5] GC 힌트 추가
// [2023-11-24 #5] 코드 들여쓰기 통일
// [2023-11-24 #11] 동기화 처리 개선
// [2023-11-27 #9] 설정 파일 로딩 캐시
