import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//대기실 기능을 수행하는 인터페이스
public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    /* Panel */
    JPanel basePanel = new JPanel(new BorderLayout());
    JPanel centerPanel = new JPanel();
    JPanel eastPanel = new JPanel();

    /* Label */
    JLabel roomListL = new JLabel("================ 방 목록 ================");
    JLabel cuListL = new JLabel("======= 접속 인원 =======");

    /* ScrollPane */
    JScrollPane rL_sp;
    JScrollPane cL_sp;

    /* List */
    JList<String> rList = new JList<String>();
    JList<String> cuList = new JList<String>();

    /* Menu */
    JMenuBar mb = new JMenuBar();
    JMenu infoMenu = new JMenu("내정보");
    JMenuItem viewInfo = new JMenuItem("내 정보 보기");
    JMenuItem changeInfo = new JMenuItem("내 정보 바꾸기");

    /* Button */
    JButton chatRoom = new JButton("익명 채팅방 입장");
    JButton viewRanking = new JButton("전적 보기");
    JButton createRoom = new JButton("방 생성하기");
    JButton enterRoom = new JButton("방 입장하기");
    JButton exitGame = new JButton("게임 종료하기");

    String selRoom; // 선택된 방 제목
    String roomName; // 생성할 방 제목

    Client c = null;

    final String getP1ImgTag = "GETP1IMG"; // 플레이어1 이미지 조회 태그
    final String getP2ImgTag = "GETP2IMG"; // 플레이어2 이미지 조회 태그
    final String setP1ImgTag = "SETP1IMG"; // 플레이어1 이미지 세팅 태그
    final String setP2ImgTag = "SETP2IMG"; // 플레이어2 이미지 세팅 태그
    final String chatRoomTag = "CHATROOM"; // 채팅 방 기능 태그
    final String croomTag = "CROOM"; // 방 생성 기능 태그
    final String eroomTag = "EROOM"; // 방 입장 기능 태그
    final String rankTag = "RANK"; // 전적 조회 기능 태그
    final String pexitTag = "PEXIT"; // 프로그램 종료 기능 태그
    final String spectatorXYTag = "SPECTATORXY"; // 관전자 돌 위치 태그

    MainFrame(Client _c) {
        c = _c;

        setTitle("대기실");

        /* Menu */
        infoMenu.add(viewInfo);
        infoMenu.addSeparator();
        infoMenu.add(changeInfo);
        mb.add(infoMenu);
        setJMenuBar(mb);

        /* Panel 크기 작업 */
        centerPanel.setPreferredSize(new Dimension(310, basePanel.getHeight()));
        eastPanel.setPreferredSize(new Dimension(180, basePanel.getHeight()));

        /* Label 크기 작업 */
        roomListL.setPreferredSize(new Dimension(290, 20));
        cuListL.setPreferredSize(new Dimension(160, 20));

        /* ScrollPane 크기 작업 */
        rL_sp = new JScrollPane(rList);
        cL_sp = new JScrollPane(cuList);
        rL_sp.setPreferredSize(new Dimension(300, 350));
        cL_sp.setPreferredSize(new Dimension(160, 188));

        /* Button 크기 작업 */
        viewRanking.setPreferredSize(new Dimension(160, 35));
        createRoom.setPreferredSize(new Dimension(160, 35));
        enterRoom.setPreferredSize(new Dimension(160, 35));
        exitGame.setPreferredSize(new Dimension(160, 35));
        chatRoom.setPreferredSize(new Dimension(160, 35));

        /* Panel 추가 작업 */
        setContentPane(basePanel); // panel을 기본 컨테이너로 설정

        basePanel.add(centerPanel, BorderLayout.CENTER);
        basePanel.add(eastPanel, BorderLayout.EAST);

        centerPanel.setLayout(new FlowLayout());
        eastPanel.setLayout(new FlowLayout());

        centerPanel.add(roomListL);
        centerPanel.add(rL_sp);

        /* eastPanel 컴포넌트 */

        eastPanel.add(cuListL);
        eastPanel.add(cL_sp);
        eastPanel.add(viewRanking);
        eastPanel.add(createRoom);
        eastPanel.add(enterRoom);
        eastPanel.add(chatRoom);
        eastPanel.add(exitGame);

        /* MenuItem 이벤트 리스너 추가 */
        MenuItemListener mil = new MenuItemListener();

        viewInfo.addActionListener(mil);
        changeInfo.addActionListener(mil);

        /* Button 이벤트 리스너 추가 */
        ButtonListener bl = new ButtonListener();

        chatRoom.addActionListener(bl);
        viewRanking.addActionListener(bl);
        createRoom.addActionListener(bl);
        enterRoom.addActionListener(bl);
        exitGame.addActionListener(bl);

        /* Mouse 이벤트 추가 */
        rList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!rList.isSelectionEmpty()) {
                    String[] m = rList.getSelectedValue().split(" : ");
                    selRoom = m[0];
                }
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        });

        setSize(510, 490);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "정말 종료하시겠습니까?", "종료 확인", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    c.sendMsg("PEXIT//");
                    System.exit(0);
                }
            }
        });
    }

    /* Button 이벤트 리스너 */
    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton) e.getSource();

            /* 전적 보기 버튼 이벤트 */
            if (b.getText().equals("전적 보기")) {
                c.rf.setVisible(true);
                System.out.println("[Client] 전적 조회 인터페이스 열림");
                c.sendMsg(rankTag + "//"); // 서버에 전적 조회 태그 전송
            }

            /* 방 생성하기 버튼 이벤트 */
            else if (b.getText().equals("방 생성하기")) {
                // 생성할 방 제목을 입력받음
                roomName = JOptionPane.showInputDialog(null, "생성할 방 제목을 입력하시오", "방 생성", JOptionPane.QUESTION_MESSAGE);
                if (roomName == null) {
                    System.out.println("방 생성이 취소되었습니다.");
                } else if (roomName.equals("")) { // roomName이 null이 아니면 서버에 "태그//방이름" 형태의 메시지를 전송
                    roomName = "덤벼라!";
                    c.setMyStat("PLAYER");
                    c.sendMsg(getP1ImgTag + "//" + c.getId());
                    c.sendMsg(croomTag + "//" + roomName);
                    c.sendMsg(setP1ImgTag);
                } else { // roomName이 비어 있지 않다면 방 이름으로 방 생성
                	c.setMyStat("PLAYER");
                    c.sendMsg(getP1ImgTag + "//" + c.getId());
                    c.sendMsg(croomTag + "//" + roomName);
                    c.sendMsg(setP1ImgTag);
                }
            }

            /* 방 입장하기 버튼 이벤트 */
            else if (b.getText().equals("방 입장하기")) {
                if (selRoom != null) { // selRoom이 null이 아니면 서버에 "태그//방이름" 형태의 메시지를 전송
                    c.setMyStat("PLAYER");
                    c.sendMsg(getP2ImgTag + "//" + c.getId());
                    c.sendMsg(eroomTag + "//" + selRoom);
                    c.sendMsg(setP2ImgTag);
                    c.sendMsg(spectatorXYTag + "//");
                } else { // selRoom이 null이면 입장 시도 실패
                    JOptionPane.showMessageDialog(null, "입장할 방을 선택해주세요", "입장 실패", JOptionPane.ERROR_MESSAGE);
                    System.out.println("[Client] 방 입장 오류 : 선택 값이 존재하지 않음");
                }
            }

            /* 게임 종료하기 버튼 이벤트 */
            else if (b.getText().equals("게임 종료하기")) {
                System.out.println("[Client] 게임 종료");
                c.sendMsg(pexitTag + "//"); // 서버에 프로그램 종료 태그 전송
                System.exit(0); // 프로그램 강제 종료
            }

            /* 채팅방 입장 버튼 이벤트 */
            else if (b.getText().equals("익명 채팅방 입장")) {
                String msg = chatRoomTag + "//" + c.cf.getNickname() + "님이 입장 했습니다.";
                c.cf.setVisible(true);
                System.out.println("[Client] 채팅 인터페이스 열림");
                c.sendMsg(msg); // 서버에 채팅 인터페이스를 열었다고 알림
            }
        }
    }

    /* MenuItem 이벤트 리스너 */
    class MenuItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JMenuItem mi = (JMenuItem) e.getSource();

            /* 내 정보 보기 메뉴 이벤트 */
            if (mi.getText().equals("내 정보 보기")) {
                c.inf.setVisible(true);
                System.out.println("[Client] 회원 정보 조회 인터페이스 열림");
            }

            /* 내 정보 바꾸기 메뉴 이벤트 */
            else if (mi.getText().equals("내 정보 바꾸기")) {
                c.cinf.setVisible(true);
                System.out.println("[Client] 회원 정보 변경 인터페이스 열림");
            }
        }
    }
}// 2023-09-14 - 방 생성 시 빈 제목 방지
// 2023-10-12 - 관전자 퇴장 시 인원 수 동기화
// 2023-11-07 - 이모티콘 다이얼로그 코드 정리
// 2023-11-18 - 랭킹 정렬 기준 승률로 변경
// 2023-11-29 - 코드 점검 및 미세 수정 (2023-11-29)
// 2023-12-02 - 보드 화점 위치 미세 조정
// 2023-12-16 - 불필요한 System.out.println 정리
// [2023-09-01 #7] 설정 파일 로딩 캐시
// [2023-09-02 #9] 로직 최적화
// [2023-09-04 #4] 프로필 이미지 캐싱
// [2023-09-05 #11] 패널 간격 조정
// [2023-09-06 #5] GC 힌트 추가
// [2023-09-07 #12] UI 응답성 개선
// [2023-09-08 #1] 리소스 해제 추가
// [2023-09-09 #4] 재연결 로직 기반
// [2023-09-10 #2] GC 힌트 추가
// [2023-09-11 #4] 에러 메시지 한글화
// [2023-09-12 #5] 인코딩 처리
// [2023-09-14 #9] 버튼 크기 조정
// [2023-09-16 #4] 돌 배치 애니메이션 기반
// [2023-09-17 #6] 이벤트 리스너 분리
// [2023-09-17 #7] 로그 레벨 분류
// [2023-09-17 #10] 스레드 안정성 개선
// [2023-09-19 #4] 상수 정리
// [2023-09-19 #9] 소켓 타임아웃 설정
// [2023-09-22 #8] 인코딩 처리
// [2023-09-26 #11] 타임아웃 설정
// [2023-09-29 #6] 인덱스 활용 개선
// [2023-10-01 #10] 재연결 로직 기반
