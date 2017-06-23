package com.example.iiitmk.volleytrial;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnUpload;
    private ProgressDialog progressDialog;
    private String URL = "http://gis.iiitmk.ac.in/dev/gamma/services/insert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnUpload = (Button) findViewById(R.id.btn_Upload);

        btnUpload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Upload:

                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Uploading, please wait...");
                progressDialog.show();

                StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>(){
                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();
                        if(s.equals("true")){
                            Toast.makeText(MainActivity.this, "Uploaded Successful", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "adichu poi!", Toast.LENGTH_LONG).show();
                        }
                    }
                },new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(MainActivity.this, "Some error occurred -> "+volleyError, Toast.LENGTH_LONG).show();
                    }
                }) {
                    //adding parameters to send
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        parameters.put("m_id","1");
                        parameters.put("l1_id","1");
                        parameters.put("l2_id","1");
                        parameters.put("l3_id","1");
                        parameters.put("u_id","1");
                        parameters.put("p_id","1");
                        parameters.put("d_date","2020-03-27 10:38:09.367");
                        parameters.put("d_lat","100.100");
                        parameters.put("d_lng","20.23434");
                        parameters.put("d_filename","volley_trial");
                        parameters.put("d_others","volleyothers");
                        return parameters;
                    }
                };

                RequestQueue rQueue = Volley.newRequestQueue(MainActivity.this);
                rQueue.add(request);



                break;
            //hello git test...
        }
    }
}
