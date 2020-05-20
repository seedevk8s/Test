import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class HttpConnectionUtil {

    /**
     * @param pURL : 요청 URL
     * @param pList : 파라미터 객체 (HashMap<String,String>)
     *
     */
    public static String sendMessage(String pURL, HashMap < String, String > pList, String message, String encType) {

        String myResult = "";

        try {
            //   URL 설정하고 접속하기
            URL url = new URL(pURL); // URL 설정

            HttpURLConnection mConn = (HttpURLConnection) url.openConnection(); // 접속
            //--------------------------
            //   전송 모드 설정 - 기본적인 설정
            //--------------------------
            mConn.setRequestProperty("Content-type", "text/plain;charset=".concat(encType));
            mConn.setRequestMethod("POST");
            mConn.setConnectTimeout(10000);
            mConn.setDoOutput(true);
            //mConn.setFixedLengthStreamingMode(message.getBytes().length);
            mConn.setDefaultUseCaches(false);
            mConn.setDoInput(true); // 서버에서 읽기 모드 지정
            mConn.setDoOutput(true); // 서버로 쓰기 모드 지정
            mConn.setRequestMethod("POST"); // 전송 방식은 POST



            //--------------------------
            // 헤더 세팅
            //--------------------------
            // 서버에게 웹에서 <Form>으로 값이 넘어온 것과 같은 방식으로 처리하라는 걸 알려준다
            mConn.setRequestProperty("content-type", "application/x-www-form-urlencoded");


            //--------------------------
            //  클라이언트가 서버로 값을 전송
            //--------------------------
/*            StringBuffer buffer = new StringBuffer();

            //HashMap으로 전달받은 파라미터가 null이 아닌경우 버퍼에 넣어준다
            if (pList != null) {

                Set key = pList.keySet();

                for (Iterator iterator = key.iterator(); iterator.hasNext();) {
                    String keyName = (String) iterator.next();
                    String valueName = pList.get(keyName);
                    buffer.append(keyName).append("=").append(valueName);
                }
            }*/



            //OutputStreamWriter outStream = new OutputStreamWriter(mConn.getOutputStream(), "EUC-KR");
            OutputStream out = mConn.getOutputStream();

            //PrintWriter writer = new PrintWriter(outStream);
            //writer.write(buffer.toString());
            out.write(message.getBytes());
            System.out.println(out);
            //writer.flush();
            out.flush();
            out.close();


            //--------------------------
            //   Response Code
            //--------------------------
            //http.getResponseCode();


            //--------------------------
            //   서버에서 클라이언트로 값을 전송받기
            //--------------------------
            InputStreamReader tmp = new InputStreamReader(mConn.getInputStream(), "UTF-8");
            BufferedReader reader = new BufferedReader(tmp);
            StringBuilder builder = new StringBuilder();
            String str;
            while ((str = reader.readLine()) != null) {
                builder.append(str + "\n");
            }
            myResult = builder.toString();
            System.out.println(message);
            return myResult;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myResult;
    }
}
