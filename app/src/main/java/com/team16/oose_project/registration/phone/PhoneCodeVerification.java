package com.team16.oose_project.registration.phone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.team16.oose_project.R;
import com.team16.oose_project.registration.constant.RegistrationConstant;
import com.team16.oose_project.registration.validation.CodeValidation;

public class PhoneCodeVerification extends AppCompatActivity implements CodeValidation{
    private String registrationCode;
    private String emailInput;
    private String phoneInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_code_verification);

        Intent intent = getIntent();
        registrationCode  = intent.getStringExtra(PhoneRegistrationConstant.PHONE_REGISTRATION_CODE);
        emailInput = intent.getStringExtra(RegistrationConstant.EMAIL_INPUT);
        phoneInput = intent.getStringExtra(RegistrationConstant.PHONE_INPUT);
    }

    public void verifyPhoneCode(View v) {
        EditText codeInput = (EditText) findViewById(R.id.pcvCodeInput);
        String codeToBeVerified = codeInput.getText().toString();
        if (validateCode(codeToBeVerified)) {
            Intent successfulPhoneRegistration = new Intent(this, SuccessfulPhoneRegistration.class);
            successfulPhoneRegistration.putExtra(RegistrationConstant.EMAIL_INPUT, emailInput);
            successfulPhoneRegistration.putExtra(RegistrationConstant.PHONE_INPUT, phoneInput);
            startActivity(successfulPhoneRegistration);
        }
    }

    @Override
    public boolean validateCode(String code) {
        if (code.equals(registrationCode)) {
            return true;
        } else {
            Toast.makeText(this, RegistrationConstant.INCORRECT_CODE,
                    Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
