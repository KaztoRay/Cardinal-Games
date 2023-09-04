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
