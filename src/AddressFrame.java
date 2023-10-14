import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class AddressFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    Client c = null;

    /* Panel */
    JPanel panel = new JPanel();

    /* Label */
    JLabel dong = new JLabel("동명 번지");
    JLabel post = new JLabel("우편번호");

    /* TextField */
    JTextField dongField = new JTextField(); // xx동 검색하는 필드
    JTextField postField = new JTextField(); // 우편번호 검색 필드

    /* Button */
    JButton searchBtn1 = new JButton("검색");
    JButton searchBtn2 = new JButton("검색");
    JButton cancelBtn = new JButton("취소");
    JButton okBtn = new JButton("확인");

    /* List */
    DefaultListModel<String> model = new DefaultListModel<String>();
    JList<String> addressList = new JList<String>();

    /* ScrollPane */
    JScrollPane addressListCl = new JScrollPane();

    AddressFrame(Client _c) {
        c = _c;

        setTitle("주소 검색");

        /* Label 작업 */
        dong.setPreferredSize(new Dimension(80, 30));
        post.setPreferredSize(new Dimension(80, 30));

        /* TextField 작업 */
        dongField.setPreferredSize(new Dimension(170, 30));
        postField.setPreferredSize(new Dimension(170, 30));

        /* Button 작업 */
        searchBtn1.setPreferredSize(new Dimension(60, 30));
        searchBtn2.setPreferredSize(new Dimension(60, 30));

        /* list 작업 */
        addressList = new JList<String>(model);
        addressList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        /* ScrollPane 작업 */
        addressListCl = new JScrollPane(addressList);
        addressListCl.setPreferredSize(new Dimension(330, 300));

        /* Panel 추가 작업 */
        setContentPane(panel);

        panel.add(dong);
        panel.add(dongField);
        panel.add(searchBtn1);

        panel.add(post);
        panel.add(postField);
        panel.add(searchBtn2);

        panel.add(addressListCl);

        panel.add(cancelBtn);
        panel.add(okBtn);

        /* Panel 작업 */
        setSize(360, 460);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(false);

        /* 버튼작업 */
        searchBtn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findAddress("dong", dongField.getText());
            }
        });

        searchBtn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findAddress("post", postField.getText());
            }
        });

        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.jf.addressField.setText(addressList.getSelectedValue());
                dispose();
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        KeyBoardListener kl = new KeyBoardListener();
        dongField.addKeyListener(kl);
        postField.addKeyListener(kl);
    }

    class KeyBoardListener implements KeyListener {
        // KeyListener
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if (keyCode == KeyEvent.VK_ENTER) {
                if (!dongField.getText().equals("")) {
                    findAddress("dong", dongField.getText());
                } else if (!postField.getText().equals(""))
                    findAddress("post", postField.getText());
            }
        }
    }

    public void findAddress(String _s, String _w) {
        try {
            model.removeAllElements();
            String searchSe = _s;
            String srchwrd = _w;

            StringBuilder urlBuilder = new StringBuilder(
                    "http://openapi.epost.go.kr/postal/retrieveNewAdressAreaCdService/retrieveNewAdressAreaCdService/getNewAddressListAreaCd");
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
                    + "=eKZl6rkQywvMBSq%2BReH0s0sqLAXCqJ4iQDnaHwT9bHCQumCmQuYy8I%2B42juw9xXDCoH3NMB7P8DPaxT2IP%2B8Ag%3D%3D");
            urlBuilder.append("&" + URLEncoder.encode("searchSe", "UTF-8") + "="
                    + URLEncoder.encode(searchSe, "UTF-8")); /* dong : 동(읍/면)명road :도로명[default]post : 우편번호 */
            urlBuilder.append(
                    "&" + URLEncoder.encode("srchwrd", "UTF-8") + "=" + URLEncoder.encode(srchwrd, "UTF-8")); /* 검색어 */
            urlBuilder.append("&" + URLEncoder.encode("countPerPage", "UTF-8") + "="
                    + URLEncoder.encode("50", "UTF-8")); /* 페이지당 출력될 개수를 지정 */
            urlBuilder.append("&" + URLEncoder.encode("currentPage", "UTF-8") + "="
                    + URLEncoder.encode("1", "UTF-8")); /* 출력될 페이지 번호 */
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println("Response code: " + conn.getResponseCode());
            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();

            // XML 문자열 또는 파일에서 Document를 생성
            String xmlString = sb.toString(); // 여기에 실제 XML 문자열을 넣으세요.
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xmlString)));

            // 노드 목록 얻기
            NodeList lnmAdresNodes = document.getElementsByTagName("lnmAdres");

            for (int i = 0; i < lnmAdresNodes.getLength(); i++) {
                Element element = (Element) lnmAdresNodes.item(i);
                String lnmAdressValue = element.getTextContent();
                System.out.println(lnmAdressValue);
                model.addElement(lnmAdressValue);
            }

            dongField.setText("");
            postField.setText("");

            System.out.println(sb.toString());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

}
// [2023-09-01 #1] 버튼 크기 조정
// [2023-09-01 #11] 예외 처리 강화
// [2023-09-03 #2] 좌표 계산 정밀도 개선
// [2023-09-04 #6] 버퍼 크기 최적화
// [2023-09-04 #8] 게임 로직 검증
// [2023-09-05 #3] 테두리 스타일 변경
// [2023-09-07 #8] 보드 렌더링 최적화
// [2023-09-07 #13] 채팅 필터링 기반 코드
// [2023-09-09 #9] 로그 레벨 분류
// [2023-09-11 #14] 로직 최적화
// [2023-09-12 #10] EDT 스레드 안전성
// [2023-09-12 #11] 인덱스 활용 개선
// [2023-09-13 #14] 다이얼로그 메시지 수정
// [2023-09-14 #12] 채팅 필터링 기반 코드
// [2023-09-14 #13] 예외 처리 강화
// [2023-09-15 #8] 컬렉션 초기 용량 설정
// [2023-09-17 #8] 동기화 처리 개선
// [2023-09-18 #1] 로그 레벨 분류
// [2023-09-19 #10] 키보드 단축키 추가
// [2023-09-20 #4] 프로토콜 메시지 형식 정리
// [2023-09-21 #10] 채팅 필터링 기반 코드
// [2023-09-25 #2] 로깅 추가
// [2023-09-25 #9] 메서드 분리
// [2023-09-28 #5] 소켓 타임아웃 설정
// [2023-09-29 #10] GC 힌트 추가
// [2023-09-30 #4] 로그 레벨 분류
// [2023-09-30 #9] 경계값 체크
// [2023-10-02 #6] 주석 보완
// [2023-10-06 #6] 메서드 분리
// [2023-10-08 #12] GC 힌트 추가
// [2023-10-09 #7] 돌 배치 애니메이션 기반
// [2023-10-11 #12] 보드 렌더링 최적화
// [2023-10-12 #1] 폰트 크기 조정
// [2023-10-12 #3] 소켓 타임아웃 설정
// [2023-10-13 #10] 폰트 크기 조정
// [2023-10-14 #5] 툴팁 추가
