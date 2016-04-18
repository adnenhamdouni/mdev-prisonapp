package tn.mdevtunisia.sample.prisonapp.helper;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by adnen on 15/01/16.
 */
public class MyHelper {


    public final static String TAG = "HELPER";


    public static final String BASE_SERVER_URL = "http://192.168.1.100/mooc/";
    public static final String BASE_GET_ALL_USERS_URL = "getPrisoners.php";


    public static String PrisonersData(String baseUrl, String state) {

        String result = null;
        HttpURLConnection con;
        InputStream is;

        String wsUrl = baseUrl;
        try {

            wsUrl = BASE_SERVER_URL + BASE_GET_ALL_USERS_URL ;
            Log.v(TAG, wsUrl);

            con = (HttpURLConnection) (new URL(wsUrl)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = br.readLine()) != null) {
                buffer.append(line + "\r\n");
            }

            is.close();
            con.disconnect();
            result = buffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.v(TAG,
                "MyHelper: user response result = " + result);
        return result;

    }

}
