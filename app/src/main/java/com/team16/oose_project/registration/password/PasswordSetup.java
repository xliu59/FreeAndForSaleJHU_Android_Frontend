package com.team16.oose_project.registration.password;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.team16.oose_project.R;
import com.team16.oose_project.registration.constant.RegistrationConstant;

import java.util.Arrays;

public class PasswordSetup extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_setup);
    }

    public void verifyPassword(View v) {
        EditText passwordInput =  (EditText)findViewById(R.id.psPasswordInput);
        EditText passwordConfirmationInput =  (EditText)findViewById(R.id.psPasswordConfirmationInput);
        String password = passwordInput.getText().toString();
        String passwordConfirmation = passwordConfirmationInput.getText().toString();
        int passwordSize = password.length();

        if (matchPassword(password, passwordConfirmation)) {
            if(validPasswordSize(passwordSize)) {
                finishPasswordSetup(password, passwordSize);
            }
        }
    }

    private boolean matchPassword(String password, String passwordConfirmation) {
        if (password.equals(passwordConfirmation)) {
            return true;
        } else {
            Toast.makeText(this, PasswordConstant.PASSWORD_MISMATCH,
                    Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean validPasswordSize(int passwordSize){
        if (passwordSize < PasswordConstant.MINIMUM_PASSWORD_LENGTH) {
            Toast.makeText(this, PasswordConstant.PASSWORD_LENGTH_MSG + PasswordConstant.MINIMUM_PASSWORD_LENGTH,
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }

    private String hidePassword(int passwordSize) {
        char[] repeat = new char[passwordSize];
        char asterisk = PasswordConstant.ASTERISK;
        Arrays.fill(repeat, asterisk);
        String hiddenPassword = "";
        hiddenPassword += new String(repeat);
        return hiddenPassword;
    }

    private void finishPasswordSetup(String password, int passwordSize) {
        Intent phoneVerification = getIntent();
        String verifiedEmail = phoneVerification.getStringExtra(RegistrationConstant.EMAIL_INPUT);
        String verifiedPhone = phoneVerification.getStringExtra(RegistrationConstant.PHONE_INPUT);
        Intent successfulPasswordSetup = new Intent(this, SuccessfulPasswordSetup.class);
        successfulPasswordSetup.putExtra(RegistrationConstant.EMAIL_INPUT, verifiedEmail);
        successfulPasswordSetup.putExtra(RegistrationConstant.PHONE_INPUT, verifiedPhone);
        successfulPasswordSetup.putExtra(PasswordConstant.PASSWORD, password);
        successfulPasswordSetup.putExtra(PasswordConstant.HIDDEN_PASSWORD, hidePassword(passwordSize));
        startActivity(successfulPasswordSetup);
    }
}
