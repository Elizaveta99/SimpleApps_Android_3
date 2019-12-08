package com.android.guessnumber7;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();

    private TextView tvInfo;
    private EditText etInput;
    private Button bControl;
    private Button newGameButton;
    private int number;
    private ProgressBar progressBar;
    private ImageView image;
    private int progressStatus = 0;
    private boolean isOver;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = findViewById(R.id.textView);
        etInput = findViewById(R.id.editText);
        bControl = findViewById(R.id.button);
        newGameButton = findViewById(R.id.button2);
        progressBar = findViewById(R.id.progressBar);
        image = findViewById(R.id.hit_image);
        builder = new AlertDialog.Builder(MainActivity.this);

        newGame();
    }

    private void newGame()
    {
        Log.e(LOG_TAG, String.format("new game"));
        progressStatus = 0;
        progressBar.setProgress(progressStatus);
        Random r = new Random();
        int low = 1;
        int high = 10; // 200, 10 to test
        number = r.nextInt(high - low) + low;
        newGameButton.setEnabled(false);
        bControl.setEnabled(true);
        tvInfo.setText(R.string.try_to_guess);
        isOver = false;

        Animation animation;
        animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.first_animation);
        image.startAnimation(animation);
    }

    public void enterButton(View view) {
        Log.e(LOG_TAG, String.format("Enter button"));
        progressStatus++;
        if (progressStatus > 10)
        {
            builder.setTitle(getResources().getString(R.string.oops))
                    .setMessage(getResources().getString(R.string.tries_over))
                    .setCancelable(false)
                    .setNegativeButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    isOver = true;
                                    newGameButton.setEnabled(true);
                                    dialog.cancel();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();
        }
        else {

            progressBar.setProgress(progressStatus);

            if (etInput.getText().toString().equals("")) {
                Log.e(LOG_TAG, String.format("Empty input"));

                builder.setTitle(R.string.error)
                        .setMessage(R.string.empty)
                        .setCancelable(false)
                        .setNegativeButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
            } else {

                try {
                    int temp = Integer.parseInt(etInput.getText().toString());

                    if (temp < 1 || temp > 10) {
                        builder.setTitle(R.string.error)
                                .setMessage(R.string.value_borders)
                                .setCancelable(false)
                                .setNegativeButton("OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });
                        AlertDialog alert = builder.create();
                        alert.show();

                        etInput.setText("");
                    } else {
                        if (temp > number) {
                            tvInfo.setText(getResources().getString(R.string.ahead));
                        } else if (temp < number) {
                            tvInfo.setText(getResources().getString(R.string.behind));
                        } else if (temp == number) {
                            tvInfo.setText(getResources().getString(R.string.hit));

                            builder.setTitle(getResources().getString(R.string.hit))
                                    .setMessage(getResources().getString(R.string.hit) + " in " + progressStatus + " tries")
                                    .setCancelable(false)
                                    .setNegativeButton("OK",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    isOver = true;
                                                    newGameButton.setEnabled(true);
                                                    bControl.setEnabled(false);
                                                    //newGame();
                                                    //etInput.setText("");

                                                    dialog.cancel();
                                                }
                                            });
                            AlertDialog alert = builder.create();
                            alert.show();


                        }
                    }
                } catch (NumberFormatException e) {
                    builder.setTitle(R.string.error)
                            .setMessage(R.string.value_not_int)
                            .setCancelable(false)
                            .setNegativeButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();

                    etInput.setText("");
                }
            }
        }
    }

    public void newGame(View view) {
        newGame();
        etInput.setText("");

        //image animation
    }
}
