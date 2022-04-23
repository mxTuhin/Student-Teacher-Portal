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
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
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