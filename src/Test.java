import java.util.HashMap;

public class Test {

    public static void main(String[] args) {
        String url = "https://fb.com"; 	//URL
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("name", "이순신");	//PARAM

        String resp = HttpConnectionUtil.postRequest(url, param);

        System.out.println(resp);
    }
}
