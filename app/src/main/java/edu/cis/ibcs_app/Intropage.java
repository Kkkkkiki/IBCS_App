package edu.cis.ibcs_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import edu.cis.ibcs_app.Controllers.MainActivity;

public class Intropage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intropage);
    }

    public void goToStudent(View v) {
        Intent toNewScreen = new Intent(getBaseContext(), CISUserActivity.class);
        startActivity(toNewScreen);
    }

    public void goToAdmin(View v) {
        Intent toAdminScreen = new Intent(getBaseContext(), MainActivity.class);
        startActivity(toAdminScreen);
    }
}