import javax.swing.*;
import java.awt.*;

//검색한 전적을 출력하는 인터페이스
public class SRankFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    /* Panel */
    JPanel panel = new JPanel();

    /* Label 및 Font */
    JLabel l = new JLabel();
    Font f = new Font("Dialog", Font.PLAIN, 15);

    Client c = null;

    SRankFrame(Client _c) {
        c = _c;
        setTitle("전적검색");

        // 폰트 작업
        l.setFont(f);
        l.setHorizontalAlignment(JLabel.CENTER);

        setContentPane(panel); // panel을 기본 컨테이너로 설정
        panel.add(l);

        setSize(250, 70);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
// [2023-09-01 #8] 게임 로직 검증
// [2023-09-03 #11] 접근제한자 수정
// [2023-09-04 #3] 접근제한자 수정
// [2023-09-08 #12] 버퍼 크기 최적화
// [2023-09-11 #7] 미사용 import 제거
// [2023-09-13 #9] UI 응답성 개선
// [2023-09-17 #1] 변수명 가독성 개선
// [2023-09-22 #11] 컬렉션 초기 용량 설정
// [2023-09-23 #8] 툴팁 추가
// [2023-09-28 #10] 경계값 체크
// [2023-10-03 #5] 트랜잭션 처리
// [2023-10-05 #8] 패널 간격 조정
// [2023-10-10 #4] 패널 간격 조정
