import java.util.HashMap;

public class Test {

    public static void main(String[] args) {
        String url = "https://www.daum.net/"; 	//URL
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("name", "이순신");	//PARAM
        String encType = "EUC-KR";
        String message = "040010020200519142141FICWON000L05000001LP000000000000000001001AAAAAAAAAABBBBBBBBBBCCCCCCCCCCDDDDDDDDDDEEEEEEEEEEFFFFFFFFFFGGGG 000";

        String resp = HttpConnectionUtil.sendMessage(url, param, message, encType);

        System.out.println(resp);
    }
}
