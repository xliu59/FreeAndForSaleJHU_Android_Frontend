package com.team16.oose_project.MyAccount;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.team16.oose_project.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;



public class MyAccountDisplay extends Activity {
    TextView a_username;
    TextView a_email;
    TextView a_phone;
    TextView a_address;
    TextView a_city;
    TextView a_state;
    TextView a_zipcode;
    Button updatePost;

    UserProfile userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account_display);

        // get reference to the views
        a_username = (TextView) findViewById(R.id.username);
        a_email = (TextView) findViewById(R.id.email);
        a_phone = (TextView) findViewById(R.id.phone);
        a_address = (TextView) findViewById(R.id.address);
        a_city = (TextView) findViewById(R.id.city);
        a_state = (TextView) findViewById(R.id.state);
        a_zipcode = (TextView) findViewById(R.id.zipcode);
        updatePost = (Button) findViewById(R.id.updateButton);

        // call AsynTask to perform network operation on separate thread
        new HttpAsyncTask().execute("http://10.0.2.2:4567/login_test/MyAccount/1");
    }

    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream

            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null) {
                result = convertInputStreamToString(inputStream);
            }
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        System.out.println("result: " + result);
        return result;
    }

    // helper method to convert input stream to string
    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();

            // show response on the TextView
            try {
                JSONObject json_result = new JSONObject(result);
                a_username.setText(json_result.getString("username"));
                a_email.setText(json_result.getString("email"));
                a_phone.setText(json_result.getString("phone"));
                a_address.setText(json_result.getString("address"));
                a_zipcode.setText(json_result.getString("zipCode"));

                System.out.println("username: " + a_username.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

