package com.scorpionsstudio.studentteacherportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {

    public TextView name, email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        instantiateViews();
        onClickListeners();
    }


    public void instantiateViews(){
        name = findViewById(R.id.nameText);
        name.setText(StaticVars.name);
        email = findViewById(R.id.emailText);
        email.setText(StaticVars.email);

    }

    public void onClickListeners(){

    }

    public void onCoursesClick(View view){

    }

    public void onEnrollClick(View view){

    }

    public void onAnnounceClick(View view){

    }

    public void onAssignmentClick(View view){

    }

    public void onProfileClick(View view){

    }

    public void onLogoutClick(View view){
        startActivity(new Intent(this,MainActivity.class));

    }




}