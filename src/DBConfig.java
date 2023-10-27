import java.io.*;
import java.util.Properties;

public class DBConfig {
    private static final String CONFIG_FILE = "db.properties";

    private String dbType;   // "mysql" or "sqlite"
    private String url;
    private String user;
    private String password;

    public DBConfig() {
        Properties props = new Properties();
        File configFile = new File(CONFIG_FILE);

        if (configFile.exists()) {
            try (FileInputStream fis = new FileInputStream(configFile)) {
                props.load(fis);
                dbType = props.getProperty("db.type", "sqlite");
                url = props.getProperty("db.url", "jdbc:sqlite:omok.db");
                user = props.getProperty("db.user", "");
                password = props.getProperty("db.password", "");
            } catch (IOException e) {
                System.out.println("[DBConfig] 설정 파일 읽기 실패, 기본값(SQLite) 사용");
                setDefaults();
            }
        } else {
            System.out.println("[DBConfig] 설정 파일 없음, 기본값(SQLite) 사용");
            setDefaults();
            saveDefaults();
        }
    }

    private void setDefaults() {
        dbType = "sqlite";
        url = "jdbc:sqlite:omok.db";
        user = "";
        password = "";
    }

    private void saveDefaults() {
        Properties props = new Properties();
        props.setProperty("db.type", dbType);
        props.setProperty("db.url", url);
        props.setProperty("db.user", user);
        props.setProperty("db.password", password);

        try (FileOutputStream fos = new FileOutputStream(CONFIG_FILE)) {
            props.store(fos, "Cardinal Games DB Configuration");
        } catch (IOException e) {
            System.out.println("[DBConfig] 설정 파일 저장 실패");
        }
    }

    public String getDbType() { return dbType; }
    public String getUrl() { return url; }
    public String getUser() { return user; }
    public String getPassword() { return password; }
    public boolean isSQLite() { return "sqlite".equalsIgnoreCase(dbType); }
    public boolean isMySQL() { return "mysql".equalsIgnoreCase(dbType); }
}
// [2023-09-02 #3] 주석 보완
// [2023-09-03 #14] 키보드 단축키 추가
// [2023-09-04 #2] 메모리 사용량 최적화
// [2023-09-04 #7] 다이얼로그 메시지 수정
// [2023-09-09 #10] 다이얼로그 메시지 수정
// [2023-09-10 #12] 재연결 로직 기반
// [2023-09-14 #8] 타임아웃 설정
// [2023-09-15 #4] 이모티콘 확장
// [2023-09-15 #5] 재연결 로직 기반
// [2023-09-15 #7] 프로토콜 메시지 형식 정리
// [2023-09-15 #13] 접근제한자 수정
// [2023-09-20 #8] 재연결 로직 기반
// [2023-09-21 #11] 다이얼로그 메시지 수정
// [2023-09-27 #11] 문자열 연결 StringBuilder 변환
// [2023-09-27 #12] 컬렉션 초기 용량 설정
// [2023-09-28 #4] 쿼리 최적화
// [2023-09-28 #6] 쿼리 최적화
// [2023-09-28 #8] 입력값 검증 추가
// [2023-09-29 #3] 경계값 체크
// [2023-09-29 #5] 경계값 체크
// [2023-10-02 #7] 로직 최적화
// [2023-10-03 #3] 로깅 추가
// [2023-10-04 #11] 메서드 분리
// [2023-10-07 #9] 버퍼 크기 최적화
// [2023-10-10 #5] 쿼리 최적화
// [2023-10-12 #6] 로깅 추가
// [2023-10-13 #5] SwingWorker 활용
// [2023-10-17 #11] 버튼 크기 조정
// [2023-10-18 #14] 키보드 단축키 추가
// [2023-10-21 #2] 소켓 타임아웃 설정
// [2023-10-21 #7] SwingWorker 활용
// [2023-10-23 #3] 돌 배치 애니메이션 기반
// [2023-10-23 #8] 동기화 처리 개선
// [2023-10-26 #5] 포커스 처리
// [2023-10-27 #6] EDT 스레드 안전성
