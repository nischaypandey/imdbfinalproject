package com.example.user.imdm_final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash);


        Thread thread = new Thread()
        {
            //run method
            @Override
            public void run() {
                try {
                    //to stop the screen for 3 seconds calling sleep method
                    sleep(3000);

                    //calling MainActivity
                    Intent intent = new Intent(SplashScreenActivity.this,BaseDashBoardActivity.class);

                    //starting MainActivity
                    startActivity(intent);

                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        //starting thread
        thread.start();

    }//end of onCreate method


}
