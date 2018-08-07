package com.proteam.bai_13_3_ex_volley;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.proteam.bai_13_3_ex_volley.model.People;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class ParseJsonGsonVolleyActivity extends AppCompatActivity {

    // json object response url
    private String urlJsonObj = "https://api.androidhive.info/volley/person_object.json";

    // json array response url
    private String urlJsonArry = "https://api.androidhive.info/volley/person_array.json";

    private static String TAG = MainActivity.class.getSimpleName();

    private Button btnMakeObjectRequest, btnMakeArrayRequest;

    // Progress dialog
    private ProgressDialog pDialog;

    private TextView txtResponse;

    // temporary string to show the parsed response
    private String jsonResponse;

    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        btnMakeObjectRequest = (Button) findViewById(R.id.btnObjRequest);
        btnMakeArrayRequest = (Button) findViewById(R.id.btnArrayRequest);
        txtResponse = (TextView) findViewById(R.id.txtResponse);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        btnMakeObjectRequest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // making json object request
                makeJsonObjectRequest();
            }
        });

        btnMakeArrayRequest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // making json array request
                makeJsonArrayRequest();
            }
        });

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();

    }

    /**
     * Method to make json object request where json response starts with {
     */
    private void makeJsonObjectRequest() {

        showDialog();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlJsonObj, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                People people = gson.fromJson(response.toString(), People.class);

                txtResponse.setText("-Name:" + people.getName() + "\n-Email:" + people.getEmail() + "\n-Mobile: " +
                        people.getPhone().getMobile() + "\n-Home:" + people.getPhone().getHome()
                );
                hideDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                hideDialog();
            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

    /**
     * Method to make json array request where response starts with [
     */
    private void makeJsonArrayRequest() {

        showDialog();

        JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String jsonOutput = response.toString();
                        Type listType = new TypeToken<List<People>>() {
                        }.getType();

                        List<People> posts = gson.fromJson(jsonOutput, listType);

                        String p ="";
                        for (People people: posts) {
                            p += "\n-Name:" + people.getName() + "\n-Email:" + people.getEmail() + "\n-Mobile: " +
                            people.getPhone().getMobile() + "\n-Home:" + people.getPhone().getHome();
                        }
                        txtResponse.setText(p);

                        hideDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                hideDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
    }

    // Show loading request
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    // Hide loading request
    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
