package idv.masqurin.opendata;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

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

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

}
