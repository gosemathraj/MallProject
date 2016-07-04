package com.example.mallassignmentproject.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mallassignmentproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by iamsparsh on 4/7/16.
 */
public class Fragment_Login extends Fragment {

    private EditText username;
    private EditText password;
    private Button login;
    private TextView error;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login,container,false);

        username = (EditText) view.findViewById(R.id.username_edittext);
        password = (EditText) view.findViewById(R.id.password_edittext);
        login = (Button) view.findViewById(R.id.login_button);
        error = (TextView) view.findViewById(R.id.error_login_textview);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){

                    login();
                }else{

                    error.setText("please enter valid username or password");
                }
            }
        });


        return view;
    }

    private boolean validate() {

        if(username.getText().toString().equals("") || password.getText().toString().equals("")){

            return false;
        }
        return true;
    }

    private void login() {

        String url = "http://stage.phonethics.in/inorbitapp/api-1/user_api/validate_credentials";

        HashMap<String,String> post_params = new HashMap<>();
        post_params.put("mobile",username.getText().toString());
        post_params.put("password",password.getText().toString());

        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.POST,url,new JSONObject(post_params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            String validate = response.getString("success");
                            if(validate.equals("true")){

                                addMallDetailsFragment();
                            }else{

                                error.setText("Invalid username or password");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        String s = error.toString();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("X-API-KEY", "d41d8cd98f00b204e9800998ecf8427e");
                return params;
            };
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonObjectRequest);

    }

    private void addMallDetailsFragment() {

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.parent_linear_layout,new Fragment_Home()).addToBackStack(null).commit();
    }
}
