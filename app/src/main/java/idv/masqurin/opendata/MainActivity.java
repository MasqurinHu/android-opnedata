package idv.masqurin.opendata;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private  static  String MY_URL = "http://data.tycg.gov.tw/api/v1/rest/datastore/a1b4714b-3b75-4ff8-a8f2-cc377e4eaa0f?format=json";
    private Button btnConnect;
    private TextView tvResul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
    }

    private void findViews() {
        tvResul = (TextView) findViewById(R.id.tvResult);
        btnConnect = (Button) findViewById(R.id.btnConnect);
        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetOpenDataTask().execute(MY_URL);
            }
        });
    }

    private class GetOpenDataTask extends AsyncTask<String, Void, String> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
//            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Downloading...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setDoInput(true);
                con.setRequestMethod("GET");

                InputStream is = con.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                StringBuilder sb = new StringBuilder();

                int status = con.getResponseCode();
                if (status == HttpURLConnection.HTTP_OK){
                    String str;
                    while ((str = br.readLine()) != null){
                        sb.append(str);
                    }
                }

                br.close();
                isr.close();
                is.close();

                return sb.toString();


            }catch (IOException ie){
                Log.e("Main",ie.toString());
            }
            return "Failed to get data...";
        }

        @Override
        protected void onPostExecute(String s) {
            progressDialog.cancel();
            tvResul.setText(s);
        }
    }

}
