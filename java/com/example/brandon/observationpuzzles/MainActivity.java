package com.example.brandon.observationpuzzles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button puz1Btn = (Button) findViewById(R.id.puz1Btn);
        Button puz2Btn = (Button) findViewById(R.id.puz2Btn);
        Button puz3Btn = (Button) findViewById(R.id.puz3Btn);

        puz1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_to_puzzle1 = new Intent(getApplicationContext(), Puzzle1_Instructions.class);
                startActivity(go_to_puzzle1);
            }
        });
    }
}
