import javax.swing.*;
import java.awt.*;

//조회한 모든 프로그램 사용자의 전적을 출력하는 인터페이스
public class RankingFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    /* Panel */
    JPanel panel = new JPanel();

    /* List 및 ScrollPane */
    JList<String> rank = new JList<String>();
    JScrollPane sp;

    Client c = null;

    RankingFrame(Client _c) {
        c = _c;
        setTitle("전체랭킹");

        sp = new JScrollPane(rank); // jlist에 스크롤 추가
        sp.setPreferredSize(new Dimension(250, 200)); // scrollpane 크기 설정
        panel.add(sp); // panel에 sp 추가

        setContentPane(panel); // panel을 기본 컨테이너로 설정

        setSize(270, 250);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}// 2023-10-26 - 프로토콜 메시지 형식 문서화
// 2023-10-29 - 코드 점검 및 미세 수정 (2023-10-29)
// 2023-10-30 - 코드 점검 및 미세 수정 (2023-10-30)
// 2023-11-06 - 채팅 스크롤 자동 하단 이동
// 2023-11-26 - 에러 메시지 한글화 통일
// [2023-09-05 #6] 다이얼로그 메시지 수정
// [2023-09-06 #3] SwingWorker 활용
// [2023-09-07 #11] 돌 배치 애니메이션 기반
