package com.example.sylviali.toloveapsycho;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BeginnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginner);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Before you play...");
        builder.setMessage("I coded this game in approximately five hours. Therefore, the game is not very fancy or well-developed. \n" +
                "Please forgive little glitches, such as the game may start over automatically if a part of storyline is not created yet. Thank you!");
        builder.setPositiveButton("OK", null);
        builder.setNegativeButton("", null);
        builder.show();

        TextView tx = (TextView)findViewById(R.id.TitleP);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Engagement-Regular.ttf");
        tx.setTypeface(custom_font);
        TextView hint = (TextView)findViewById(R.id.hinter1);
        hint.setText(R.string.hint1);
        hint.setVisibility(View.VISIBLE);
        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),GameActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
