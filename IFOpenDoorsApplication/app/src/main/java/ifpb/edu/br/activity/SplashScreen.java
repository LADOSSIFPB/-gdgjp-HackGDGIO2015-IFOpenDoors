package ifpb.edu.br.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import ifpb.edu.br.carcatalogbrowser_emanuel.LabLibrary;
import ifpb.edu.br.carcatalogbrowser_emanuel.R;


public class SplashScreen extends Activity implements Runnable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Log.i("TESTE","PEGOU");
        Handler handler = new Handler();
        handler.postDelayed(this, 5000);
    }

    @Override
    public void run() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

}