import java.sql.*;

// 프로그램 첫 실행 시 프로그램에 필요한 테이블을 생성하는 클래스.
public class Create_DB {

    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null;
        DBConfig config = new DBConfig();

        try {
            if (config.isMySQL()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } else {
                Class.forName("org.sqlite.JDBC");
            }
            con = DriverManager.getConnection(config.getUrl(), config.getUser(), config.getPassword());
            stmt = con.createStatement();

            // member 테이블 생성
            String createMember;
            if (config.isMySQL()) {
                createMember = "CREATE TABLE IF NOT EXISTS member ("
                    + "name varchar(20) not null, "
                    + "nickname varchar(20) not null, "
                    + "id varchar(20) not null, "
                    + "password varchar(20) not null, "
                    + "address varchar(50) not null, "
                    + "gender varchar(6) not null, "
                    + "birth varchar(20) not null, "
                    + "phone varchar(20), "
                    + "email varchar(30) not null, "
                    + "win int not null, "
                    + "lose int not null, "
                    + "PRIMARY KEY (nickname, id))";
            } else {
                createMember = "CREATE TABLE IF NOT EXISTS member ("
                    + "name TEXT NOT NULL, "
                    + "nickname TEXT NOT NULL, "
                    + "id TEXT NOT NULL, "
                    + "password TEXT NOT NULL, "
                    + "address TEXT NOT NULL, "
                    + "gender TEXT NOT NULL, "
                    + "birth TEXT NOT NULL, "
                    + "phone TEXT, "
                    + "email TEXT NOT NULL, "
                    + "win INTEGER NOT NULL DEFAULT 0, "
                    + "lose INTEGER NOT NULL DEFAULT 0, "
                    + "PRIMARY KEY (nickname, id))";
            }
            stmt.executeUpdate(createMember);
            System.out.println("[Create_DB] member 테이블 생성 완료");

            // image_table 생성
            String createImage;
            if (config.isMySQL()) {
                createImage = "CREATE TABLE IF NOT EXISTS image_table ("
                    + "id varchar(20) not null PRIMARY KEY, "
                    + "image_data LONGBLOB)";
            } else {
                createImage = "CREATE TABLE IF NOT EXISTS image_table ("
                    + "id TEXT NOT NULL PRIMARY KEY, "
                    + "image_data BLOB)";
            }
            stmt.executeUpdate(createImage);
            System.out.println("[Create_DB] image_table 생성 완료");

            // backup_table 생성
            String createBackup;
            if (config.isMySQL()) {
                createBackup = "CREATE TABLE IF NOT EXISTS backup_table LIKE member";
            } else {
                createBackup = "CREATE TABLE IF NOT EXISTS backup_table ("
                    + "name TEXT, nickname TEXT, id TEXT, password TEXT, "
                    + "address TEXT, gender TEXT, birth TEXT, phone TEXT, "
                    + "email TEXT, win INTEGER, lose INTEGER)";
            }
            stmt.executeUpdate(createBackup);
            System.out.println("[Create_DB] backup_table 생성 완료");

            // backup_image_table 생성
            String createBackupImg;
            if (config.isMySQL()) {
                createBackupImg = "CREATE TABLE IF NOT EXISTS backup_image_table LIKE image_table";
            } else {
                createBackupImg = "CREATE TABLE IF NOT EXISTS backup_image_table ("
                    + "id TEXT NOT NULL PRIMARY KEY, image_data BLOB)";
            }
            stmt.executeUpdate(createBackupImg);
            System.out.println("[Create_DB] backup_image_table 생성 완료");

            // 관리자 계정 생성 (존재하지 않을 경우)
            String checkAdmin = "SELECT COUNT(*) FROM member WHERE id='admin'";
            ResultSet rs = stmt.executeQuery(checkAdmin);
            if (rs.next() && rs.getInt(1) == 0) {
                String insertAdmin = "INSERT INTO member VALUES('관리자', 'admin', 'admin', 'admin', "
                    + "'서울', '남', '2000-01-01', '010-0000-0000', 'admin@cardinal.com', 0, 0)";
                stmt.executeUpdate(insertAdmin);
                System.out.println("[Create_DB] 관리자 계정 생성 완료 (admin/admin)");
            }

            System.out.println("[Create_DB] 모든 테이블 생성 완료!");

        } catch (Exception e) {
            System.out.println("[Create_DB] 오류 > " + e.toString());
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
    }
}
// [2023-09-01 #5] 로깅 추가
// [2023-09-02 #4] 색상 코드 정리
// [2023-09-09 #5] 프로필 이미지 캐싱
// [2023-09-10 #1] 타임아웃 설정
// [2023-09-13 #5] 변수명 가독성 개선
// [2023-09-14 #3] 버튼 크기 조정
// [2023-09-14 #6] 프로토콜 메시지 형식 정리
// [2023-09-16 #3] 버튼 크기 조정
// [2023-09-18 #12] 인덱스 활용 개선
// [2023-09-20 #2] 소켓 타임아웃 설정
// [2023-09-21 #1] 화점 위치 보정
// [2023-09-22 #1] 트랜잭션 처리
// [2023-09-23 #4] 메서드 분리
// [2023-09-26 #9] 입력값 검증 추가
// [2023-09-29 #11] 테두리 스타일 변경
// [2023-09-30 #8] 설정 파일 로딩 캐시
// [2023-10-01 #9] 입력값 검증 추가
// [2023-10-03 #12] 패널 간격 조정
// [2023-10-04 #6] 폰트 크기 조정
// [2023-10-04 #7] 경계값 체크
// [2023-10-05 #3] 버퍼 크기 최적화
// [2023-10-05 #9] 인코딩 처리
// [2023-10-06 #4] 타임아웃 설정
// [2023-10-07 #1] 인덱스 활용 개선
// [2023-10-07 #6] 폰트 크기 조정
// [2023-10-09 #1] 메서드 분리
// [2023-10-11 #10] 포커스 처리
// [2023-10-13 #6] 커넥션 풀 기반 코드
// [2023-10-13 #8] 스크롤 자동 이동
// [2023-10-13 #12] 툴팁 추가
// [2023-10-15 #4] 키보드 단축키 추가
// [2023-10-16 #6] UI 응답성 개선
// [2023-10-16 #9] 돌 배치 애니메이션 기반
// [2023-10-19 #5] 소켓 타임아웃 설정
// [2023-10-19 #10] GC 힌트 추가
// [2023-10-20 #10] 입력값 검증 추가
// [2023-10-23 #2] 불필요한 객체 생성 제거
// [2023-10-24 #9] 입력값 검증 추가
// [2023-10-24 #11] 스레드 안정성 개선
// [2023-10-26 #3] 인덱스 활용 개선
// [2023-10-26 #4] 파일 입출력 버퍼링
// [2023-10-27 #3] 스레드 안정성 개선
// [2023-10-27 #4] 다이얼로그 메시지 수정
// [2023-10-30 #8] 불필요한 객체 생성 제거
// [2023-10-30 #10] 프로토콜 메시지 형식 정리
// [2023-10-31 #7] 상수 정리
// [2023-11-05 #4] 프로토콜 메시지 형식 정리
// [2023-11-06 #4] 화점 위치 보정
// [2023-11-10 #8] 코드 들여쓰기 통일
// [2023-11-11 #1] 재연결 로직 기반
// [2023-11-14 #2] 키보드 단축키 추가
// [2023-11-14 #7] 레이아웃 미세 조정
// [2023-11-17 #9] 포커스 처리
// [2023-11-18 #5] 주석 보완
// [2023-11-18 #8] 소켓 타임아웃 설정
// [2023-11-20 #6] 상수 정리
// [2023-11-22 #11] 쿼리 최적화
// [2023-11-23 #6] 프로토콜 메시지 형식 정리
// [2023-11-23 #10] 컬렉션 초기 용량 설정
// [2023-11-24 #6] 접근제한자 수정
// [2023-11-30 #3] 인덱스 활용 개선
// [2023-12-02 #6] 컬렉션 초기 용량 설정
// [2023-12-04 #4] 이모티콘 확장
// [2023-12-06 #7] 리소스 해제 추가
// [2023-12-10 #8] 설정 파일 로딩 캐시
