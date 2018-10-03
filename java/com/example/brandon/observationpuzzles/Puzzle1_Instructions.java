package com.example.brandon.observationpuzzles;

import android.content.Intent;
import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;

import static java.lang.Thread.sleep;

public class Puzzle1_Instructions extends AppCompatActivity {

    private int screenWidth;
    private int screenHeight;

    // Images
    private ImageView redSquare;
    private ImageView yellowCircle;
    private ImageView orangeHeart;
    private ImageView greenTriangle;
    private ImageView greyPlus;
    private ImageView lightBlueCloud;
    private ImageView blackNo;
    private ImageView blueArrow;
    private ImageView darkBlueStar;

    // Position variables
    private float squareX;
    private float squareY;
    private float circleX;
    private float circleY;
    private float heartX;
    private float heartY;
    private float triangleX;
    private float triangleY;
    private float plusX;
    private float plusY;
    private float cloudX;
    private float cloudY;
    private float noX;
    private float noY;
    private float arrowX;
    private float arrowY;
    private float starX;
    private float starY;
    private boolean initialSetup;
    private boolean shuffle;

    // Initialize Class
    private Handler handler = new Handler();
    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle1__instructions);

        final TextView instructions = (TextView) findViewById(R.id.pz1_instTV);
        final TextView question1 = (TextView) findViewById(R.id.pz1_q1TV);

        Button pz1_backBtn = (Button) findViewById(R.id.pz1_backBtn);
        Button pz1_goBtn = (Button) findViewById(R.id.pz1_goBtn);
        redSquare = (ImageView) findViewById(R.id.redSquare);
        yellowCircle = (ImageView) findViewById(R.id.yellowCircle);
        orangeHeart = (ImageView) findViewById(R.id.orangeHeart);
        greenTriangle = (ImageView) findViewById(R.id.greenTriangle);
        greyPlus = (ImageView) findViewById(R.id.greyPlus);
        lightBlueCloud = (ImageView) findViewById(R.id.lightBlueCloud);
        blackNo = (ImageView) findViewById(R.id.blackNo);
        blueArrow = (ImageView) findViewById(R.id.blueArrow);
        darkBlueStar = (ImageView) findViewById(R.id.darkBlueStar);
        //box = (ImageView) findViewById(R.id.box);

        // Get Screen Size
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        // Set Starting Positions
        initialSetup = false;
        shuffle = false;
        redSquare.getViewTreeObserver().addOnGlobalLayoutListener(new MyGlobalListenerClass());


        pz1_backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBack = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goBack);
            }
        });

        pz1_goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                instructions.setVisibility(View.GONE);

                question1.setVisibility(View.VISIBLE);
                for(int x=10; x>0; x--){
                    ShuffleImages(2);
                }
                //SetPosition(box, 1);
                //box.setVisibility(View.VISIBLE);
            }
        });
    }

    private ImageView ChooseImage(double rand){
        ImageView shape;
        if(rand < 1){
            shape = redSquare;
        }
        else if(rand < 2){
            shape = yellowCircle;
        }
        else if(rand < 3){
            shape = orangeHeart;
        }
        else if(rand < 4){
            shape = greenTriangle;
        }
        else if(rand < 5){
            shape = lightBlueCloud;
        }
        else if(rand < 6){
            shape = blackNo;
        }
        else if(rand < 7){
            shape = blueArrow;
        }
        else if(rand < 8){
            shape = darkBlueStar;
        }
        else{
            shape = greyPlus;
        }
        return shape;
    }

    private void ShuffleImages(int number_of_swaps){
        double rand = Math.random()*9;
        int iterations = number_of_swaps;
        ImageView shape1;
        ImageView shape2 = ChooseImage(rand);

        while(iterations > 0) {
            shape1 = ChooseImage(rand);
            while(shape2 == shape1) {
                rand = Math.random() * 9;
                shape2 = ChooseImage(rand);
            }
            SwapShapes(shape1, shape2);
            iterations--;
        }
    }

    private void SwapShapes(ImageView shape1, ImageView shape2){
        int storePos;
        storePos = GetPosition(shape1);
        SetPosition(shape1, GetPosition(shape2));
        SetPosition(shape2, storePos);
    }

    private int GetPosition(ImageView shape){
        float posX = shape.getX();
        float posY = shape.getY();
        int row;
        int column;

        // Find row
        if(posY < screenHeight/3 - shape.getHeight()/2 + 5){
            row = 0;
        }
        else if(posY < screenHeight/2 - shape.getHeight()/2+5){
            row = 3;
        }
        else{
            row = 6;
        }

        // Find column
        if(posX < screenWidth / 4 - shape.getWidth() / 2+5){
            column = 1;
        }
        else if(posX < screenWidth/2 - shape.getWidth()/2+5){
            column = 2;
        }
        else{
            column = 3;
        }

        return row + column;
    }

    private void SetPosition(ImageView shape, int pos){
        float posX;
        float posY;

        switch(pos){
            case 1:
                posX = screenWidth / 4 - shape.getWidth() / 2;
                posY = screenHeight / 3 - shape.getHeight() / 2;
                shape.setX(posX);
                shape.setY(posY);
                break;
            case 2:
                posX = screenWidth / 2 - shape.getWidth() / 2;
                posY = screenHeight / 3 - shape.getHeight() / 2;
                shape.setX(posX);
                shape.setY(posY);
                break;
            case 3:
                posX = screenWidth *3/ 4 - shape.getWidth() / 2;
                posY = screenHeight / 3 - shape.getHeight() / 2;
                shape.setX(posX);
                shape.setY(posY);
                break;
            case 4:
                posX = screenWidth / 4 - shape.getWidth() / 2;
                posY = screenHeight / 2 - shape.getHeight() / 2;
                shape.setX(posX);
                shape.setY(posY);
                break;
            case 5:
                posX = screenWidth /2 - shape.getWidth() / 2;
                posY = screenHeight / 2 - shape.getHeight() / 2;
                shape.setX(posX);
                shape.setY(posY);
                break;
            case 6:
                posX = screenWidth *3/ 4 - shape.getWidth() / 2;
                posY = screenHeight / 2 - shape.getHeight() / 2;
                shape.setX(posX);
                shape.setY(posY);
                break;
            case 7:
                posX = screenWidth / 4 - shape.getWidth() / 2;
                posY = screenHeight *2/ 3 - shape.getHeight() / 2;
                shape.setX(posX);
                shape.setY(posY);
                break;
            case 8:
                posX = screenWidth /2 - shape.getWidth() / 2;
                posY = screenHeight *2/ 3 - shape.getHeight() / 2;
                shape.setX(posX);
                shape.setY(posY);
                break;
            case 9:
                posX = screenWidth *3/ 4 - shape.getWidth() / 2;
                posY = screenHeight *2/ 3 - shape.getHeight() / 2;
                shape.setX(posX);
                shape.setY(posY);
                break;
            default:
                break;
        }
    }

    class MyGlobalListenerClass implements ViewTreeObserver.OnGlobalLayoutListener{
        @Override
        public void onGlobalLayout(){

            if(!initialSetup) {
                SetPosition(redSquare, 1);
                SetPosition(yellowCircle, 2);
                SetPosition(orangeHeart, 3);
                SetPosition(greenTriangle, 4);
                SetPosition(lightBlueCloud, 5);
                SetPosition(blackNo, 6);
                SetPosition(blueArrow, 7);
                SetPosition(darkBlueStar, 8);
                SetPosition(greyPlus, 9);
                initialSetup = true;
            }
            else if(shuffle){

                ShuffleImages(2);
                shuffle = false;
            }
        }
    }
}
