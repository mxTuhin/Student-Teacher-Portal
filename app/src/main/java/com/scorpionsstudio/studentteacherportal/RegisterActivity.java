package com.scorpionsstudio.studentteacherportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public Button registerButton;

    public EditText student_name, email, cell_no, password;

    private int selectedItem;
    private String userIdentifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        instantiateViews();
        onClickListenrs();


    }

    private void instantiateViews(){
        registerButton = (Button) findViewById(R.id.cirRegisterButton);
        student_name = findViewById(R.id.editTextName);
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        cell_no = findViewById(R.id.editTextMobile);
        inflitrateSpinner();
    }

    private void inflitrateSpinner(){
        Spinner spinner = findViewById(R.id.choice_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.broadcastOptions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
    }


    private void onLoginClick(View View){
        startActivity(new Intent(this,MainActivity.class));
//        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
    }

    public void onRegisterClick(){
        System.out.println(student_name.getText()+" "+email.getText()+" "+cell_no.getText()+" "+password.getText());
        if(!TextUtils.isEmpty(email.getText().toString().trim()) && !TextUtils.isEmpty(password.getText().toString().trim())){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.registerURL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try{
                        JSONObject obj = new JSONObject(response);
                        if(obj.getString("status").equalsIgnoreCase("success")){
                            StaticVars.name = student_name.getText().toString().trim();
                            StaticVars.email = email.getText().toString().trim();
                            Intent intent = new Intent(RegisterActivity.this, DashboardActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(RegisterActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(RegisterActivity.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("email", email.getText().toString().trim());
                    data.put("password", password.getText().toString().trim());
                    data.put("name", student_name.getText().toString().trim());
                    data.put("cell_no", cell_no.getText().toString().trim());
                    data.put("user", userIdentifier);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }else{
            Toast.makeText(this, "Field Empty", Toast.LENGTH_SHORT).show();
        }
    }

    private void onClickListenrs(){
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRegisterClick();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedItem = i;
        if(i==0){
            userIdentifier = "student";
        }else if(i==1){
            userIdentifier = "teacher";
        }else{
            userIdentifier = "admin";
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}