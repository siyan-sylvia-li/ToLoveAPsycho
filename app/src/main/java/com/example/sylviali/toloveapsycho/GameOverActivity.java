package com.example.sylviali.toloveapsycho;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        TextView yes_button = (TextView)findViewById(R.id.yes);
        TextView no_button = (TextView)findViewById(R.id.no);
        yes_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),GameActivity.class);
                startActivity(i);
                finish();
            }
        });
        no_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast t = Toast.makeText(getApplicationContext(),"Thank you for playing!",Toast.LENGTH_SHORT);
                t.show();
                finish();
            }
        });
    }
}
