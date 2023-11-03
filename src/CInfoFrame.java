import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//회원정보 변경 기능을 수행하는 인터페이스
public class CInfoFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    /* Panel */
    JPanel panel = new JPanel();

    /* Button */
    JButton nameBtn = new JButton("이름 변경하기");
    JButton pwBtn = new JButton("비밀번호 변경하기");

    JButton phoneBtn = new JButton("전화번호 변경하기");

    JButton emailBtn = new JButton("이메일 변경하기");

    JButton exitBtn = new JButton("나가기");

    String name; // 변경할 이름
    String pw; // 변경할 비밀번호
    String email; // 변경할 이메일

    String phone; // 변경할 전화번호

    Client c = null;

    final String changeTag = "CHANGE"; // 회원정보 변경 기능 태그

    CInfoFrame(Client _c) {
        c = _c;

        setTitle("회원정보 수정");

        /* Button 크기 작업 */
        nameBtn.setPreferredSize(new Dimension(250, 30));
        pwBtn.setPreferredSize(new Dimension(250, 30));
        phoneBtn.setPreferredSize(new Dimension(250, 30));
        emailBtn.setPreferredSize(new Dimension(250, 30));
        exitBtn.setPreferredSize(new Dimension(250, 30));

        /* panel 추가 작업 */
        setContentPane(panel); // panel을 기본 컨테이너로 설정

        panel.add(nameBtn);
        panel.add(pwBtn);
        panel.add(phoneBtn);
        panel.add(emailBtn);
        panel.add(exitBtn);

        /* 버튼 이벤트 리스너 추가 */
        ButtonListener bl = new ButtonListener();

        nameBtn.addActionListener(bl);
        pwBtn.addActionListener(bl);
        phoneBtn.addActionListener(bl);
        emailBtn.addActionListener(bl);
        exitBtn.addActionListener(bl);

        setSize(280, 205);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    /* Button 이벤트 리스너 */
    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton) e.getSource();

            /* 이름 변경하기 버튼 이벤트 */
            if (b.getText().equals("이름 변경하기")) {
                System.out.println("[Client] 이름 변경 시도");
                // 변경할 이름을 받음
                name = JOptionPane.showInputDialog(null, "변경할 이름을 입력하시오", "이름변경", JOptionPane.QUESTION_MESSAGE);

                if (name != null) { // name이 null이 아니면 서버에 "태그//이름//변경할이름" 형태의 메시지를 전송
                    c.sendMsg(changeTag + "//name//" + name);
                }
            }

            /* 비밀번호 변경하기 버튼 이벤트 */
            else if (b.getText().equals("비밀번호 변경하기")) {
                System.out.println("[Client] 비밀번호 변경 시도");
                // 변경할 비밀번호를 받음
                pw = JOptionPane.showInputDialog(null, "변경할 비밀번호를 입력하시오", "비밀번호변경", JOptionPane.QUESTION_MESSAGE);

                if (pw != null) { // pw가 null이 아니면 서버에 "태그//비밀번호//변경할비밀번호" 형태의 메시지를 전송
                    c.sendMsg(changeTag + "//password//" + pw);
                }
            }

            /* 이메일 변경하기 버튼 이벤트 */
            else if (b.getText().equals("이메일 변경하기")) {
                System.out.println("[Client] 이메일 변경 시도");
                // 변경할 이메일을 받음
                email = JOptionPane.showInputDialog(null, "변경할 이메일을 입력하시오", "이메일변경", JOptionPane.QUESTION_MESSAGE);

                if (email != null) { // email이 null이 아니면 서버에 "태그//이메일//변경할이메일" 형태의 메시지를 전송
                    c.sendMsg(changeTag + "//email//" + email);
                }
            }

            /* 전화번호 변경하기 버튼 이벤트 */
            else if (b.getText().equals("전화번호 변경하기")) {
                System.out.println("[Client] 전화번호 변경 시도");
                // 변경할 전화번호를 받음
                phone = JOptionPane.showInputDialog(null, "변경할 전화번호을 입력하시오", "전화번호변경", JOptionPane.QUESTION_MESSAGE);

                if (phone != null) { // phone이 null이 아니면 서버에 "태그//전화번호//변경할전화번" 형태의 메시지를 전송
                    c.sendMsg(changeTag + "//phone//" + phone);
                }
            }

            /* 나가기 버튼 이벤트 */
            else if (b.getText().equals("나가기")) {
                System.out.println("[Client] 회원 정보 변경 인터페이스 종료");
                dispose(); // 인터페이스 닫음
            }
        }
    }
}// 2023-09-12 - 메시지 태그 상수 정리
// 2023-09-27 - 코드 점검 및 미세 수정 (2023-09-27)
// 2023-10-09 - 전적 조회 시 닉네임 공백 처리
// 2023-10-23 - Database 리소스 해제 패턴 통일
// 2023-11-27 - 코드 점검 및 미세 수정 (2023-11-27)
// 2023-12-21 - 최종 코드 리뷰 반영
// 2023-12-29 - 코드 점검 및 미세 수정 (2023-12-29)
// [2023-09-03 #12] 채팅 필터링 기반 코드
// [2023-09-07 #7] 재연결 로직 기반
// [2023-09-11 #10] 파일 입출력 버퍼링
// [2023-09-13 #8] 채팅 필터링 기반 코드
// [2023-09-21 #9] 로깅 추가
// [2023-09-22 #5] 인덱스 활용 개선
// [2023-09-25 #1] 경계값 체크
// [2023-09-25 #7] 이벤트 리스너 분리
// [2023-09-27 #8] 로직 최적화
// [2023-09-28 #7] 버튼 크기 조정
// [2023-10-04 #10] 스레드 안정성 개선
// [2023-10-10 #10] 설정 파일 로딩 캐시
// [2023-10-11 #7] 다이얼로그 메시지 수정
// [2023-10-11 #8] 트랜잭션 처리
// [2023-10-22 #13] 좌표 계산 정밀도 개선
// [2023-10-25 #10] 메모리 사용량 최적화
// [2023-10-25 #11] 화점 위치 보정
// [2023-10-26 #11] 이모티콘 확장
// [2023-10-27 #10] 이벤트 리스너 분리
// [2023-10-28 #9] 코드 들여쓰기 통일
// [2023-10-29 #7] 불필요한 객체 생성 제거
// [2023-11-01 #6] GC 힌트 추가
// [2023-11-02 #5] 스크롤 자동 이동
// [2023-11-03 #10] 인코딩 처리
