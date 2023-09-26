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
