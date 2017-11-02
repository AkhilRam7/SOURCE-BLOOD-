package com.example.akhil.blood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class detail extends AppCompatActivity {

    String JSON_URL;
    String api_key="579b464db66ec23bdd00000185565bcb28fb4e0853b95ebb66825cb0";
    TextView name,avail,categoryt,apher,bcat,addr,cityt,dist,statet,web;


    String bbname,lat,lng,state,district,city,address,contact,email,category,website,apheresis,bloodComponentsAvail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Button call = (Button) findViewById(R.id.calb);
        Button mail = (Button) findViewById(R.id.mailb);
        Button map = (Button) findViewById(R.id.mapb);
        name = (TextView) findViewById(R.id.name);
        avail = (TextView) findViewById(R.id.avail);
        categoryt = (TextView) findViewById(R.id.category);
        apher = (TextView) findViewById(R.id.apher);
        bcat = (TextView) findViewById(R.id.bcat);
        addr = (TextView) findViewById(R.id.addr);
        cityt = (TextView) findViewById(R.id.city);
        dist = (TextView) findViewById(R.id.dist);
        statet = (TextView) findViewById(R.id.state);
        web = (TextView) findViewById(R.id.web);

        Intent intent = getIntent();
        String det = intent.getStringExtra("serial");
        Log.d("details",det);
        makeurl(det);
        Log.d("making",JSON_URL);
        downurl();




        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(detail.this, "this will make a call to"+contact, Toast.LENGTH_SHORT).show();
            }
        });
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(detail.this, "this will mail to"+email, Toast.LENGTH_SHORT).show();
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(detail.this, "this will open G maps at"+lat+","+lng, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void makeurl(String det){
       JSON_URL = "https://api.data.gov.in/resource/fced6df9-a360-4e08-8ca0-f283fc74ce15?format=json&api-key="+api_key+"&filters%5Bsr_no%5D="+det;

    }

    private void downurl() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONObject obj = new JSONObject(response);

                            JSONArray records = obj.getJSONArray("records");
                            JSONObject bbObject = records.getJSONObject(0);
                            bbname = bbObject.getString("_blood_bank_name");
                            lat = bbObject.getString("_latitude");
                            lng = bbObject.getString("_longitude");
                            state = bbObject.getString("_state");
                            district = bbObject.getString("_district");
                            city = bbObject.getString("_city");
                            address = bbObject.getString("_address");
                            contact = bbObject.getString("_contact_no");
                            email = bbObject.getString("_email");
                            category = bbObject.getString("_category");
                            website = bbObject.getString("_website");
                            apheresis = bbObject.getString("_apheresis");
                            bloodComponentsAvail = bbObject.getString("_blood_component_available");
                            name.setText(bbname);
                            avail.setText("O+, O-, AB+, A+, B+");
                            categoryt.setText(category);
                            apher.setText(apheresis);
                            bcat.setText(bloodComponentsAvail);
                            addr.setText(address);
                            cityt.setText(city);
                            dist.setText(district);
                            statet.setText(state);
                            web.setText(website);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("catch","deterror");

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }

}
