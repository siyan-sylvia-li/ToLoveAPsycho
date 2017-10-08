package com.example.sylviali.toloveapsycho;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class GameActivity extends AppCompatActivity {
    public static ArrayList<Page> pages = new ArrayList<>();
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
    private boolean isFirstTime = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        if (isFirstTime) {
            pages.clear();
            try {
                readPages();
            } catch (IOException e) {
                e.printStackTrace();
            }
            isFirstTime = false;
        }
        if (gameOver) {
            pageIndex = 0;
            gameOver = false;
        }
        // If game over start game over activity
//        if (gameOver) {
//            Intent i = new Intent(getApplicationContext(), GameOverActivity.class);
//            startActivity(i);
//        }

        final Intent starterIntent = getIntent();
        Log.d("CURRENT_INDEX", "" + pageIndex);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Courgette-Regular.ttf");
        TextView description = (TextView) findViewById(R.id.description);
        description.setTypeface(custom_font);
        MaxScrollView sbox = (MaxScrollView)findViewById(R.id.scroll_box);
        sbox.setMaxHeight(600);
        ChoicesLayout ch1 = (ChoicesLayout) findViewById(R.id.choice1);
        ChoicesLayout ch2 = (ChoicesLayout) findViewById(R.id.choice2);
        ChoicesLayout ch3 = (ChoicesLayout) findViewById(R.id.choice3);
        ChoicesLayout[] chArr = {ch1, ch2, ch3};
        // Initialize choices
        ch1.setIndex("A");
        ch2.setIndex("B");
        ch3.setIndex("C");

        description.setText(pages.get(pageIndex).getMotherNode().getDescription());
        Node[] optionNodes = pages.get(pageIndex).getOptionNodes();
        final ChoicesLayout[] chArray = {ch1, ch2, ch3};
        for (int i = 0; i < 3; i++) {
            chArray[i].setDescription(optionNodes[i].getDescription());
            chArray[i].setPage(Integer.parseInt(optionNodes[i].getIndex()));
            final ChoicesLayout cl = chArray[i];
            cl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pageIndex = cl.getPage() - 1;
                    finish();
                    startActivity(starterIntent);
                }
            });
        }
        gameOver = true;
        for (int i = 0; i < 3; i++) {
            ChoicesLayout cl = chArr[i];
            if (!cl.choiceDes().equals("na")) {
                cl.setTypeFace(custom_font);
                cl.setVisibility(View.VISIBLE);
                // set OnClickListeners
            }
            gameOver = gameOver && (cl.choiceDes().equals("na"));
        }

        if (gameOver) {
            ImageView survived_meme = (ImageView) findViewById(R.id.survived);
            survived_meme.setVisibility(View.VISIBLE);
            // Different pics
            survived_meme.setImageURI(Uri.parse(pages.get(pageIndex).getMotherNode().getIndex() + ".jpg"));
            sbox.setMaxHeight(1000);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(getApplicationContext(), GameOverActivity.class);
                    startActivity(i);
                    finish();
                }
            }, pages.get(pageIndex).getMotherNode().getDescription().length() * 100);
        }
    }


    public void readPages() throws IOException {
        // String path = Environment.getExternalStorageDirectory().getAbsolutePath()+ "/Android/data/" + packageName + "/files/";
        InputStream inputStream = getResources().openRawResource(R.raw.to_love);
        Scanner scanner = new Scanner(inputStream);
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNext()) {
            String statement = scanner.nextLine();
            sb.append(statement + "\n");
        }
        Log.d("CONTENT", sb.toString());
        MainReader mr = new MainReader();
        mr.extractor(sb.toString());
        pages = mr.bookPages;
        pages.sort(new PageComparator());
    }
}
