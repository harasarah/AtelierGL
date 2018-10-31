package com.example.sarah.ateliergl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText login;
    EditText password;
    CardView sign_in;
    TextView alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    password =  findViewById(R.id.edittext_password);
    sign_in =  findViewById(R.id.cardview2);
    login =  findViewById(R.id.edittext_email);
    TextWatcher test = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String loginInput = login.getText().toString().trim();
            String passwordInput = password.getText().toString().trim();
            sign_in.setEnabled(!loginInput.isEmpty() && !passwordInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };
        login.addTextChangedListener(test);
        password.addTextChangedListener(test);
        sign_in.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick (View view){
            validate(login.getText().toString().trim(),password.getText().toString().trim());
        }
    });
}

    private void validate ( String log , String pass ){
        if ((log.equals("admin")) && (pass.equals("a"))){
            Intent intent= new Intent(LoginActivity.this, ServiceActivity.class);
            startActivity(intent);
        } else {
            Toast toast;
            Context context = getApplicationContext();
            String error_message = "Mot de passe incorrecte, réessayez";
            toast = Toast.makeText(context,error_message,Toast.LENGTH_SHORT);
            toast.show();

        }
    }

}
