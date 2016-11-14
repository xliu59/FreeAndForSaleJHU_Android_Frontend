package com.team16.oose_project.MyAccount;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.team16.oose_project.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MyAccountUpdate extends Activity implements View.OnClickListener {

    EditText a_username;
    EditText a_email;
    EditText a_phone;
    EditText a_address;
    EditText a_city;
    EditText a_state;
    EditText a_zipcode;
    Button updatePost;

    UserProfile userProfile;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account_update);

        // Fetch views
        a_username = (EditText) findViewById(R.id.username);
        a_email = (EditText) findViewById(R.id.email);
        a_phone = (EditText) findViewById(R.id.phone);
        a_address = (EditText) findViewById(R.id.address);
        a_city = (EditText) findViewById(R.id.city);
        a_state = (EditText) findViewById(R.id.state);
        a_zipcode = (EditText) findViewById(R.id.zipcode);
        updatePost = (Button) findViewById(R.id.updateButton);

        // add click listener to Button "POST"
        updatePost.setOnClickListener(this);
    }

    public static String POST(String url, UserProfile userProfile){
        InputStream inputStream = null;
        String result = "";
        try {
            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);

            String json = "";

            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();

            jsonObject.accumulate("username", userProfile.getUsername());
            jsonObject.accumulate("email", userProfile.getEmail());
            jsonObject.accumulate("phoneNum", userProfile.getPhone());
            jsonObject.accumulate("address", userProfile.getAddress());
            jsonObject.accumulate("city", userProfile.getCity());
            jsonObject.accumulate("state", userProfile.getState());
            jsonObject.accumulate("zipCode", userProfile.getZipcode());


            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content   
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // 10. convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        // 11. return result
        return result;
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.updateButton:
                if(!validate())
                    Toast.makeText(getBaseContext(), "Enter some data!", Toast.LENGTH_LONG).show();
                // call AsynTask to perform network operation on separate thread

                userProfile = new UserProfile();
                userProfile.setUsername(a_username.getText().toString());
                userProfile.setEmail(a_email.getText().toString());
                userProfile.setPhone(a_phone.getText().toString());
                userProfile.setAddress(a_address.getText().toString());
                userProfile.setCity(a_city.getText().toString());
                userProfile.setState(a_state.getText().toString());
                userProfile.setZipcode(a_zipcode.getText().toString());

                new HttpAsyncTask(userProfile).execute("http://10.0.2.2:4567/login_test/MyAccount/AccountModify/1");
                break;
        }

        final Context context = this;
        Intent intent = new Intent(context, MyAccountDisplay.class);
        startActivity(intent);
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {

        private UserProfile userProfile;

        public HttpAsyncTask(UserProfile userProfile){
            this.userProfile = userProfile;
        }

        @Override
        protected String doInBackground(String... urls) {

            return POST(urls[0],userProfile);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Data Sent!", Toast.LENGTH_LONG).show();
        }
    }

    private boolean validate(){
        if(a_username.getText().toString().trim().equals(""))
            return false;
        else if(a_email.getText().toString().trim().equals(""))
            return false;
        else if(a_phone.getText().toString().trim().equals(""))
            return false;
        else
            return true;
    }


    // Helper method to convert inputstream to String
    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

}





