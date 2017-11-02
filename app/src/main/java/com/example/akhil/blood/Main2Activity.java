package com.example.akhil.blood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        String list = intent.getStringExtra("list");
        //Log.d("keep",list);

        // Get ListView object from xml
        ListView listView = (ListView) findViewById(R.id.bbank_list);

        // Defined Array values to show in ListView
        String[] values = new String[] { "Android List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter);


        try{
            JSONObject obj = new JSONObject(list);
            JSONArray results = obj.getJSONArray("records");
                for (int i = 0; i < results.length(); i++) {
                    JSONObject rec = results.getJSONObject(i);
                    String bbname = rec.getString("_blood_bank_name");
                    String laty = rec.getString("_latitude");
                    String longy = rec.getString("_longitude");
                    String state = rec.getString("_state");
                    String dist = rec.getString("_district");
                    String city = rec.getString("_city");
                    String add = rec.getString("_address");
                    String cont = rec.getString("_contact_no");
                    String email = rec.getString("_email");
                    String cat = rec.getString("_category");
                    String site = rec.getString("_website");
                    String aph = rec.getString("_apheresis");
                    String bca = rec.getString("_blood_component_available");
                }
            }
        catch (JSONException e) {
            e.printStackTrace();
            Log.d("catch","failed");
        }
    }
}


//finding blood is difficult even if we have blood banks. due to improper communication gaps between individual and blood banks having blood at required time is highly difficult.
// To overcome this we need to bridge the gap between them. making an Application which can directly allow the contact with the donors and blood bank offiicals will make the problem
//to reduce for a extent


/*
Finding blood in case of emergency is very difficult and expensive. Improper communication gaps between the blood donars and blood bank hospitals will endanger a life. If we could somehow able
to contact a blood bank we may not be sure that the blood which was said available in the blood bank is not given to anyone or else if you can get the contact of a donor you may not be sure
 about his previous donation details and his health condition. To overcome this we need a mobile applcatiom which will ensure that blood is reached to needed at the right time without fail. The
 user (need in blood) will be shown all the nearest blood banks and its availability. If blood is available user can hold the blood so that no one can take it. If blood is not availble in blood
  banks blood donars who can actively donate are shown near to us.we can contact and request them to donate blood at any nearest blood bank to them and the donar can hold the blood for the
  user at the blood bank. Likewise if you are a donor you are notified whenever the blood of your type is requested and you can act upon your availability. The donor can track his blood
  usage once his blood is donated to ensure that his blood is used purposefully.
*/