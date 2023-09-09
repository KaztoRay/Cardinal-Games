import javax.swing.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatRoomFrame extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private JTextArea chatTextArea;
    private JTextField chatInputField;
    private JButton sendButton;
    private String nickname;

    Client c = null;

    final String lobbyChatTag = "LOBBYCHAT"; // 채팅태그

    ChatRoomFrame(Client _c) {
        c = _c;

        setTitle("익명 채팅방");
        setSize(400, 300);
        setLayout(new BorderLayout());

        chatTextArea = new JTextArea();
        chatTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatTextArea);
        add(scrollPane, BorderLayout.CENTER);

        chatInputField = new JTextField();
        sendButton = new JButton("보내기");

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(chatInputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        chatInputField.addActionListener(this);
        sendButton.addActionListener(this);

    }

    // 이벤트 발생시 sendMsg(msg)를 사용해서 클라이언트로 msg를 보냄
    public void actionPerformed(ActionEvent e) {
        String msg = lobbyChatTag + "//" + nickname + " : " + chatInputField.getText() + "\n";
        c.sendMsg(msg);
        chatInputField.setText("");
    }

    // 채팅을 받아서 메세지로 붙여보내는 메소드
    public void appendMsg(String msg) {
        chatTextArea.append(msg);
    }

    // 닉네임 가져오기
    public String getNickname() {
        return nickname;
    }

    // 닉네임 설정하기
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}// 2023-09-23 - Database 클래스 쿼리 주석 추가
// 2023-10-03 - GameFrame 이벤트 리스너 분리
// 2023-10-13 - Server run() 메서드 가독성 개선
// 2023-10-18 - AdminFrame 검색 기능 정리
// 2023-10-28 - 코드 점검 및 미세 수정 (2023-10-28)
// 2023-12-04 - 게임 로그 파일 저장 기능 기반
// 2023-12-12 - 서버 시작 시 DB 연결 상태 표시
// 2023-12-14 - README 실행 환경 요구사항 추가
// 2023-12-17 - 게임 종료 후 재대국 버튼 활성화 타이밍
// [2023-09-05 #1] 키보드 단축키 추가
// [2023-09-05 #8] 파일 입출력 버퍼링
// [2023-09-07 #9] 커넥션 풀 기반 코드
// [2023-09-08 #4] 이벤트 리스너 분리
// [2023-09-08 #9] 동기화 처리 개선
// [2023-09-09 #2] 동기화 처리 개선
