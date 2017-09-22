package com.example.sylviali.toloveapsycho;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class GameActivity extends AppCompatActivity {
    public static ArrayList<Pages> pages = new ArrayList<>();
    public static int pageIndex = 0;
    public static boolean gameOver = false;
    // Update survive and dead every time a new ending is created
    public static int[] survive = new int[]{
            5, 12
    };
    public static int[] dead = new int[]{
            13
    };
    private Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        if (gameOver) {
            pageIndex = 0;
            gameOver = false;
        }
        // If game over start game over activity
//        if (gameOver) {
//            Intent i = new Intent(getApplicationContext(), GameOverActivity.class);
//            startActivity(i);
//        }
        pages.clear();
        final Intent starterIntent = getIntent();
        Log.d("CURRENT_INDEX", "" + pageIndex);
        try {
            readPages();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Courgette-Regular.ttf");
        TextView description = (TextView) findViewById(R.id.description);
        description.setTypeface(custom_font);
        Log.d("DESCRIPTION", pages.get(pageIndex).description);
        description.setText(pages.get(pageIndex).description);
        ChoicesLayout ch1 = (ChoicesLayout) findViewById(R.id.choice1);
        ChoicesLayout ch2 = (ChoicesLayout) findViewById(R.id.choice2);
        ChoicesLayout ch3 = (ChoicesLayout) findViewById(R.id.choice3);
        ChoicesLayout[] chArr = {ch1, ch2, ch3};
        // Initialize choices
        ch1.setIndex("A");
        ch2.setIndex("B");
        ch3.setIndex("C");

        description.setText(pages.get(pageIndex).description);
        ch1.setDescription(pages.get(pageIndex).choiceA);
        ch2.setDescription(pages.get(pageIndex).choiceB);
        ch3.setDescription(pages.get(pageIndex).choiceC);

        for (int i = 0; i < 3; i++) {
            ChoicesLayout cl = chArr[i];
            if (!cl.choiceDes().equals("na")) {
                cl.setTypeFace(custom_font);
                cl.setVisibility(View.VISIBLE);
                // set OnClickListeners
                setLink(cl, i + 1, starterIntent);
            }
        }

        if (isSurvive(pageIndex + 1)) {
            gameOver = true;
            ImageView survived_meme = (ImageView) findViewById(R.id.survived);
            survived_meme.setVisibility(View.VISIBLE);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(getApplicationContext(), GameOverActivity.class);
                    startActivity(i);
                    finish();
                }
            }, 5000);
        } else if (isDead(pageIndex + 1)) {
            gameOver = true;
            ImageView dead_meme = (ImageView) findViewById(R.id.died);
            dead_meme.setVisibility(View.VISIBLE);
            Log.d("STATUS","dead");
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //finish();
                    Intent i = new Intent(getApplicationContext(), GameOverActivity.class);
                    startActivity(i);
                    finish();
                }
            }, 5000);
        } else if (pages.get(pageIndex).aNum.equals("0")) {
            gameOver = true;
            Toast toast = Toast.makeText(getApplicationContext(), "Sorry, this part has not been created yet... starting over",
                    Toast.LENGTH_LONG);
            toast.show();

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(getApplicationContext(),GameActivity.class);
                    startActivity(i);
                    finish();
                }
            }, 10000);
        }

    }


    public boolean isSurvive(int index) {
        for (int i : survive) {
            if (i == index) {
                return true;
            }
        }
        return false;
    }

    public boolean isDead(int index) {
        for (int i : dead) {
            if (i == index) {
                return true;
            }
        }
        return false;
    }

    public int stringToInt(String n) {
        return (n.charAt(0) - '0');
    }

    public void setLink(final ChoicesLayout cl, int choice, final Intent starterIntent) {
        switch (choice) {
            case 1:
                cl.setPage(stringToInt(pages.get(pageIndex).aNum));
                break;
            case 2:
                cl.setPage(stringToInt(pages.get(pageIndex).bNum));
                break;
            case 3:
                cl.setPage(stringToInt(pages.get(pageIndex).cNum));
                break;
            default:
                break;
        }
        cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pageIndex = cl.getPage() - 1;
                finish();
                startActivity(starterIntent);
            }
        });
    }

    public void readPages() throws IOException {
        // String path = Environment.getExternalStorageDirectory().getAbsolutePath()+ "/Android/data/" + packageName + "/files/";
        InputStream inputStream = getResources().openRawResource(R.raw.info);
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNext()) {
            String statement = scanner.nextLine();
            Log.d("STATEMENT", statement);
            statement = statement.replaceAll("\n", "");
            if (statement.length() > 0) {
                String[] info = statement.split("&");
                Pages page = new Pages(info[0], info[1], info[2], info[3], info[4], info[5], info[6],
                        info[7]);
                pages.add(page);
                Log.d("SIZE OF PAGES", "" + pages.size());
            }
        }
    }
}
