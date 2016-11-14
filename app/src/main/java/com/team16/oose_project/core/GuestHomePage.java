package com.team16.oose_project.core;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.team16.oose_project.MyAccount.MyAccountDisplay;
import com.team16.oose_project.R;
import com.team16.oose_project.login.Login;
import com.team16.oose_project.registration.email.EmailRegistration;

public class GuestHomePage extends AppCompatActivity {

    Button meButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guesthomepage);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(myToolbar);

        meButton = (Button) findViewById(R.id.meButton);

        meButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent myProfile = new Intent(view.getContext(), MyAccountDisplay.class);
                startActivity(myProfile);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_buttons, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_signup:
                Intent emailRegistration = new Intent(this, EmailRegistration.class);
                startActivity(emailRegistration);
                return true;

            case R.id.action_login:
                Intent login = new Intent(this, Login.class);
                startActivity(login);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
