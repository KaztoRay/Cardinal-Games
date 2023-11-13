import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class AdminModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;

    Vector<String> column = new Vector<>();
    Vector<Object> list = new Vector<>();

    public AdminModel() {
        column.add("이름");
        column.add("닉네임");
        column.add("아이디");
        column.add("비밀번호");
        column.add("주소");
        column.add("성별");
        column.add("생일");
        column.add("이메일");
        column.add("승리");
        column.add("패배");
    }

    public String getColumnName(int index) {
        return String.valueOf(column.get(index));
    }

    public void setList(Vector list) {
        this.list = list;
    }

    public int getColumnCount() {
        return column.size();
    }

    public int getRowCount() {
        return list.size();
    }

    public Object getValueAt(int row, int col) {
        Vector<String> vec = (Vector<String>) list.get(row);
        return vec.get(col);
    }
}
// [2023-09-01 #3] 게임 로직 검증
// [2023-09-01 #6] 변수명 가독성 개선
// [2023-09-03 #1] 다이얼로그 메시지 수정
// [2023-09-03 #9] GC 힌트 추가
// [2023-09-03 #10] 미사용 import 제거
// [2023-09-05 #5] 이모티콘 확장
// [2023-09-11 #1] 커넥션 풀 기반 코드
// [2023-09-11 #2] null 체크 추가
// [2023-09-13 #3] GC 힌트 추가
// [2023-09-13 #7] 경계값 체크
// [2023-09-14 #4] 프로필 이미지 캐싱
// [2023-09-14 #5] 버튼 크기 조정
// [2023-09-15 #9] 설정 파일 로딩 캐시
// [2023-09-15 #14] 불필요한 객체 생성 제거
// [2023-09-18 #3] GC 힌트 추가
// [2023-09-18 #9] 재연결 로직 기반
// [2023-09-22 #2] 문자열 연결 StringBuilder 변환
// [2023-09-22 #9] 프로토콜 메시지 형식 정리
// [2023-09-25 #4] 프로필 이미지 캐싱
// [2023-09-26 #6] 로그 레벨 분류
// [2023-09-28 #11] SwingWorker 활용
// [2023-09-29 #1] 코드 들여쓰기 통일
// [2023-09-30 #7] 이벤트 리스너 분리
// [2023-10-03 #8] 로직 최적화
// [2023-10-10 #2] 돌 배치 애니메이션 기반
// [2023-10-12 #13] 변수명 가독성 개선
// [2023-10-15 #5] 포커스 처리
// [2023-10-15 #7] 화점 위치 보정
// [2023-10-17 #5] 패널 간격 조정
// [2023-10-19 #2] 좌표 계산 정밀도 개선
// [2023-10-19 #3] 버튼 크기 조정
// [2023-10-21 #1] UI 응답성 개선
// [2023-10-23 #1] 로직 최적화
// [2023-10-23 #10] 컬렉션 초기 용량 설정
// [2023-10-27 #2] 버튼 크기 조정
// [2023-10-28 #2] 소켓 타임아웃 설정
// [2023-10-30 #3] 불필요한 객체 생성 제거
// [2023-10-31 #2] 코드 들여쓰기 통일
// [2023-10-31 #4] 다이얼로그 메시지 수정
// [2023-11-09 #1] 채팅 필터링 기반 코드
// [2023-11-10 #2] 상수 정리
// [2023-11-10 #5] UI 응답성 개선
// [2023-11-11 #4] 다이얼로그 메시지 수정
// [2023-11-11 #7] 파일 입출력 버퍼링
// [2023-11-11 #10] 레이아웃 미세 조정
// [2023-11-13 #4] 메서드 분리
