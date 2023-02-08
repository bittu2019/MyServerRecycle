package com.example.myserverrecycle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycle;
    String url="https://jsonplaceholder.typicode.com/posts";
    private ArrayList<History>arrayList=new ArrayList<>();
    Myadapter myadapter;
    private EditText etSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycle = (RecyclerView) findViewById(R.id.recycle);
        etSearch = (EditText) findViewById(R.id.et_search);
        getapidata();
        myadapter=new Myadapter(arrayList,this);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recycle.setLayoutManager(layoutManager);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }

    private void filter(String text) {
        ArrayList<History>filteredList=new ArrayList<>();
        for(History item : arrayList)
        {
            if(item.getId().toString().trim().toLowerCase().contains(text.toLowerCase()))
            {
                filteredList.add(item);
            }
        }
        myadapter.filterlist(filteredList);
    }

    private void getapidata() {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for(int i =0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        int user_id=jsonObject.getInt("userId");
                        int id=jsonObject.getInt("id");
                        String title=jsonObject.getString("title");
                        String body=jsonObject.getString("body");
                        History history=new History(user_id,id,title,body);
                        arrayList.add(history);
                        recycle.setAdapter(myadapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }
}