package com.scorpionsstudio.studentteacherportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public Button loginButton;

    public EditText email;
    public EditText password;

    private int selectedItem;
    private String userIdentifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instantiateViews();
        onClickListeners();

    }



    private void instantiateViews(){
        loginButton = (Button) findViewById(R.id.cirLoginButton);
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        inflitrateSpinner();

    }

    private void inflitrateSpinner(){
        Spinner spinner = findViewById(R.id.choice_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.broadcastOptions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
    }




    private void onClickListeners(){
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginClick();
                overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
            }
        });
    }

    private void onLoginClick(){
        System.out.println(email.getText()+" "+password.getText());
//        Intent intent = new Intent(this, RegisterActivity.class);
//        startActivity(intent);
    }

    public void onRegisterClick(View View){
        startActivity(new Intent(this,RegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedItem = i;
        if(i==1){
            userIdentifier = "Student";
        }else if(i==2){
            userIdentifier = "Teacher";
        }else{
            userIdentifier = "Admin";
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}