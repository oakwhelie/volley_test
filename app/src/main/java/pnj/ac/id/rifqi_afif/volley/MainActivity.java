package pnj.ac.id.rifqi_afif.volley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
{

    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = Volley.newRequestQueue(getApplicationContext());  // this = context

    }

    public void send(View v)
    {
        final TextView nama = (TextView)findViewById(R.id.edit_nama);
        final TextView posisi = (TextView)findViewById(R.id.edit_posisi);
        final TextView gaji = (TextView)findViewById(R.id.edit_gaji);
        final TextView requeststatus = (TextView)findViewById(R.id.display_request_status);

        String url = String.format("http://192.168.43.172/android/receive_data.php?nama=%s&posisi=%s&gaji=%s",
                nama.getText().toString().replaceAll(" ", "+"),
                posisi.getText().toString().replaceAll(" ", "+"),
                gaji.getText().toString().replaceAll(" ", "+"));
        // prepare the Request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        requeststatus.setText("Success");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requeststatus.setText("Fail");

            }
        });
// add it to the RequestQueue
        queue.add(stringRequest);
    }
}
