package com.satyam.vollygetrequest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView tv, parsetv;
    Button parse,parse2,parse3;
    Context mcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        mcontext=getApplicationContext();
        parse = findViewById(R.id.parse);
        parse2=findViewById(R.id.parse2);
        parse3=findViewById(R.id.parse3);
        parsetv = findViewById(R.id.parsetv);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://jsonplaceholder.typicode.com/todos/2", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    tv.setText(response.getString("title"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tv.setText("Something Went wrong");

            }
        });
        requestQueue.add(jsonObjectRequest);

        parse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonparse();
            }
        });
        parse2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonparse2();
            }
        });
        parse3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonparse3();
            }
        });
    }


    public void jsonparse() {
        parsetv.setText(" ");
        String url2 = "https://jsonplaceholder.typicode.com/posts";
        RequestQueue requestQueue1 = Volley.newRequestQueue(mcontext);
        JsonArrayRequest jsonArrayRequest1 = new JsonArrayRequest(Request.Method.GET, url2, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < 10; i++) {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String userId = jsonObject.getString("userId");
                        String id = jsonObject.getString("id");
                        String title = jsonObject.getString("title");
                        parsetv.append(userId+" "+id+"\ntitle: "+title);
                        parsetv.append("\n\n");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                parsetv.setText("Something went wrong");
            }
        });
        requestQueue1.add(jsonArrayRequest1);

    }
    public void jsonparse2(){
        parsetv.setText(" ");
        String url="https://reqres.in/api/users?data=";
        RequestQueue requestQueue2=Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("data");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String id=jsonObject.getString("id");
                        String email=jsonObject.getString("email");
                        String first_name=jsonObject.getString("first_name");
                        String last_name=jsonObject.getString("last_name");
                        parsetv.append("id: "+id+"\nemail:"+email+"\nfirst_name:"+first_name+"\nlast_name: "+last_name);
                        parsetv.append("\n\n");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                parsetv.setText("Something Went Wrong");
            }
        });
        requestQueue2.add(jsonObjectRequest);


    }
    public void jsonparse3(){
        parsetv.setText(" ");
        String url="https://thebudgetpantry.com/api/v2/products?page=1/";
        RequestQueue requestQueue3=Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("data");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String id=jsonObject.getString("id");
                        String name=jsonObject.getString("name");
                        String base_price=jsonObject.getString("base_price");
                        String current_stock=jsonObject.getString("current_stock");
                        String unit=jsonObject.getString("unit");
                        parsetv.append("id: "+id+"\nName: "+name+"\nBase Price: "+base_price+"\nCurrent stock: "+current_stock+"\nUnit: "+unit);
                        parsetv.append("\n\n");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                parsetv.setText("Something Went Wrong");

            }
        });
        requestQueue3.add(objectRequest);

//        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                for(int i=0;i<response.length();i++){
//                    try {
//                        JSONObject jsonObject=response.getJSONObject(i);
//                        String text=jsonObject.getString("text");
//                        parsetv.append("text: "+text);
//                        parsetv.append("\n\n");
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                parsetv.setText("Something went wrong");
//            }
//        });

    }

}