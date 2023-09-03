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
