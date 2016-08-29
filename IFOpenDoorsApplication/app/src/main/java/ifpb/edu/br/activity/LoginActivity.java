package ifpb.edu.br.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        final EditText emailEdit, passwordEdit;
        emailEdit = (EditText) findViewById(R.id.emailEdit);
        passwordEdit = (EditText) findViewById(R.id.passwordEdit);
        Button loginn;

        loginn = (Button)findViewById(R.id.ButtonLogin);

        final String email=emailEdit.getText().toString();
        final String senha=passwordEdit.getText().toString();
        final Usuario usr=new Usuario();
        usr.setEmail(email);
        usr.setSenha(senha);
        loginn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String email=emailEdit.getText().toString();
                 String senha=passwordEdit.getText().toString();

                if (email.equals("rhavy@gmail.com") && senha.equals("rhavy123")) {
                    startActivity(new Intent(LoginActivity.this, LabLibrary.class));
                    finish();
                }else{
                    if ((email.equals("rhavy@gmail.com"))) {
                            Toast.makeText(getApplicationContext(), "Senha Incorreta",Toast.LENGTH_SHORT).show();
                            passwordEdit.setText("");
                    }else {
                        if (!(email.equals("rhavy@gmail.com"))) {
                            Toast.makeText(getApplicationContext(), "Usuario nao cadastrado", Toast.LENGTH_SHORT).show();
                            passwordEdit.setText("");
                            emailEdit.setText("");
                        }
                    }

                }
            }
        });


    }



}

