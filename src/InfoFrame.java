import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;

//조회한 회원정보를 출력하는 인터페이스
public class InfoFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	/* Panel */
	JPanel panel = new JPanel();

	/* Label */
	JLabel imgL = new JLabel(); // 이미지를 넣을 레이블
	JLabel imgnL = new JLabel("사진");
	JLabel nameL = new JLabel("이름");
	JLabel nicknameL = new JLabel("닉네임");
	JLabel emailL = new JLabel("이메일");
	JLabel addressL = new JLabel("주소");

	/* Border 설정 */
	Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

	/* TextField */
	JTextField name = new JTextField();
	JTextField nickname = new JTextField();
	JTextField email = new JTextField();
	JTextField address = new JTextField();

	/* Button */
	JButton viewBtn = new JButton("조회하기");
	JButton exitBtn = new JButton("나가기");

	Client c = null;

	final String viewTag = "VIEW"; // 회원 정보 조회 기능 태그
	final String viewImgTag = "VIEWIMG"; // 이미지 조회 태그

	InfoFrame(Client _c) {
		c = _c;

		setTitle("내 정보");

		/* Label 크기 작업 */
		imgL.setPreferredSize(new Dimension(200, 160));
		imgL.setBorder(border);
		imgnL.setPreferredSize(new Dimension(40, 30));
		nameL.setPreferredSize(new Dimension(40, 30));
		nicknameL.setPreferredSize(new Dimension(40, 30));
		emailL.setPreferredSize(new Dimension(40, 30));
		addressL.setPreferredSize(new Dimension(40, 30));

		/* TextField 크기 작업 */
		name.setPreferredSize(new Dimension(200, 30));
		nickname.setPreferredSize(new Dimension(200, 30));
		email.setPreferredSize(new Dimension(200, 30));
		address.setPreferredSize(new Dimension(200, 30));

		name.setEditable(false);
		nickname.setEditable(false);
		email.setEditable(false);
		address.setEditable(false);

		/* Button 크기 작업 */
		viewBtn.setPreferredSize(new Dimension(250, 30));
		exitBtn.setPreferredSize(new Dimension(250, 30));

		/* panel 추가 작업 */
		setContentPane(panel); // panel을 기본 컨테이너로 설정

		panel.add(imgnL);
		panel.add(imgL);
		panel.add(nameL);
		panel.add(name);

		panel.add(nicknameL);
		panel.add(nickname);

		panel.add(emailL);
		panel.add(email);

		panel.add(addressL);
		panel.add(address);

		panel.add(viewBtn);
		panel.add(exitBtn);

		/* Button 이벤트 작업 */
		viewBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("[Client] 회원 정보 조회 시도");
				c.sendMsg(viewImgTag + "//" + c.getId() + "//"); // 서버에 회원 정보 조회 전송
			}
		});

		exitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("[Client] 회원 정보 조회 인터페이스 종료");
				dispose();
			}
		});

		setSize(280, 430);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
}// 2023-09-28 - 코드 점검 및 미세 수정 (2023-09-28)
// 2023-10-22 - 프로필 이미지 null 체크 추가
// 2023-10-27 - 코드 점검 및 미세 수정 (2023-10-27)
// [2023-09-01 #2] 재연결 로직 기반
// [2023-09-05 #7] 미사용 import 제거
// [2023-09-06 #8] 경계값 체크
// [2023-09-08 #6] 버퍼 크기 최적화
// [2023-09-10 #5] 컬렉션 초기 용량 설정
// [2023-09-10 #11] 커넥션 풀 기반 코드
// [2023-09-11 #8] 재연결 로직 기반
// [2023-09-12 #7] 포커스 처리
// [2023-09-13 #12] SwingWorker 활용
// [2023-09-14 #7] 주석 보완
// [2023-09-14 #15] 게임 로직 검증
// [2023-09-15 #11] 테두리 스타일 변경
// [2023-09-18 #7] 버튼 크기 조정
// [2023-09-19 #2] 채팅 필터링 기반 코드
// [2023-09-24 #9] 인코딩 처리
// [2023-09-24 #14] 스레드 안정성 개선
// [2023-09-27 #5] 상수 정리
// [2023-09-28 #2] 커넥션 풀 기반 코드
// [2023-09-30 #1] EDT 스레드 안전성
// [2023-10-06 #11] 설정 파일 로딩 캐시
// [2023-10-10 #3] 메서드 분리
// [2023-10-10 #7] 상수 정리
// [2023-10-15 #6] 쿼리 최적화
// [2023-10-15 #10] 포커스 처리
// [2023-10-18 #11] 버퍼 크기 최적화
// [2023-10-20 #1] EDT 스레드 안전성
// [2023-10-22 #6] 문자열 연결 StringBuilder 변환
// [2023-10-24 #10] 테두리 스타일 변경
// [2023-10-25 #4] 화점 위치 보정
// [2023-10-25 #6] 인덱스 활용 개선
// [2023-10-26 #7] 메서드 분리
// [2023-10-29 #5] 돌 배치 애니메이션 기반
// [2023-10-29 #12] 포커스 처리
// [2023-10-30 #4] 이모티콘 확장
// [2023-10-31 #3] 예외 처리 강화
// [2023-10-31 #8] 툴팁 추가
// [2023-10-31 #10] 승리 판정 엣지케이스 처리
// [2023-11-01 #7] 버튼 크기 조정
// [2023-11-01 #8] 로깅 추가
// [2023-11-03 #6] 설정 파일 로딩 캐시
// [2023-11-04 #5] 색상 코드 정리
// [2023-11-05 #7] 패널 간격 조정
// [2023-11-06 #1] 경계값 체크
// [2023-11-07 #2] SwingWorker 활용
