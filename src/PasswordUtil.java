import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * 비밀번호 해싱 유틸리티 클래스
 * SHA-256 해싱을 사용하여 비밀번호를 안전하게 저장
 */
public class PasswordUtil {

    /**
     * 비밀번호를 SHA-256으로 해싱
     * @param password 원본 비밀번호
     * @return 해싱된 비밀번호 (Base64 인코딩)
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("[PasswordUtil] 해싱 실패: " + e.getMessage());
            return password; // 해싱 실패 시 원본 반환 (fallback)
        }
    }

    /**
     * 비밀번호 검증
     * @param inputPassword 입력된 비밀번호
     * @param storedHash 저장된 해시
     * @return 일치 여부
     */
    public static boolean verifyPassword(String inputPassword, String storedHash) {
        String inputHash = hashPassword(inputPassword);
        return inputHash.equals(storedHash);
    }
}
// [2023-09-02 #5] 로깅 추가
// [2023-09-03 #13] 스레드 안정성 개선
// [2023-09-04 #12] 동기화 처리 개선
// [2023-09-04 #13] 파일 입출력 버퍼링
// [2023-09-07 #3] 스레드 안정성 개선
// [2023-09-08 #5] 변수명 가독성 개선
// [2023-09-08 #8] 색상 코드 정리
// [2023-09-09 #1] 불필요한 객체 생성 제거
// [2023-09-12 #12] 에러 메시지 한글화
// [2023-09-15 #1] 버튼 크기 조정
// [2023-09-17 #9] 에러 메시지 한글화
// [2023-09-19 #12] 경계값 체크
// [2023-09-22 #3] UI 응답성 개선
// [2023-09-23 #5] 인덱스 활용 개선
// [2023-09-26 #4] 툴팁 추가
// [2023-09-26 #5] GC 힌트 추가
// [2023-09-28 #1] 로깅 추가
// [2023-10-01 #12] 타임아웃 설정
// [2023-10-02 #4] 코드 들여쓰기 통일
// [2023-10-02 #8] 커넥션 풀 기반 코드
// [2023-10-03 #10] 키보드 단축키 추가
// [2023-10-04 #2] 툴팁 추가
// [2023-10-05 #5] 툴팁 추가
// [2023-10-06 #10] 프로토콜 메시지 형식 정리
// [2023-10-07 #8] 트랜잭션 처리
// [2023-10-08 #14] 컬렉션 초기 용량 설정
// [2023-10-09 #10] null 체크 추가
// [2023-10-11 #2] 색상 코드 정리
// [2023-10-11 #9] 타임아웃 설정
// [2023-10-22 #14] 메서드 분리
// [2023-10-24 #2] 미사용 import 제거
// [2023-10-26 #6] 메모리 사용량 최적화
