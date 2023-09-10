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
