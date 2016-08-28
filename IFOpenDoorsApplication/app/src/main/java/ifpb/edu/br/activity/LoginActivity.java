package ifpb.edu.br.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ifpb.edu.br.carcatalogbrowser_emanuel.LabLibrary;
import ifpb.edu.br.carcatalogbrowser_emanuel.R;
import ifpb.edu.br.entitys.Usuario;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText emailEdit, passwordEdit;
        emailEdit = (EditText) findViewById(R.id.emailEdit);
        passwordEdit = (EditText) findViewById(R.id.passwordEdit);
        Button login;

        login = (Button) findViewById(R.id.login);

        String email=emailEdit.getText().toString();
        String senha=passwordEdit.getText().toString();
        login.setOnClickListener(new View.OnClickListener(){


            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, LabLibrary.class));
                finish();
            }
        });


    }



}

