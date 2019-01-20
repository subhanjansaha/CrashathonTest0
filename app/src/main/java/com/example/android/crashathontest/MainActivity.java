package com.example.android.crashathontest;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    ImageView explicitImage;
    Button showExplicitButton;
    Calendar mCalendar = Calendar.getInstance();
    boolean isImageBlurred = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        explicitImage = (ImageView) findViewById(R.id.explicit_image);
        showExplicitButton = (Button) findViewById(R.id.show_explicit_button);

        showExplicitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showExplicitButton.setVisibility(View.GONE);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        mCalendar.set(Calendar.YEAR, year);
                        mCalendar.set(Calendar.MONTH, monthOfYear);
                        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        if (year > 2001) {
                            Toast.makeText(MainActivity.this, "Crash!", Toast.LENGTH_SHORT).show();
                        } else {
                            isImageBlurred = false;
                            explicitImage.setImageResource(R.drawable.rohit);
                        }
                    }
                }, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        explicitImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isImageBlurred) {
                    return;
                } else {
                    Intent intent = new Intent(view.getContext(), FullScreenImage.class);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ActivityOptionsCompat options = ActivityOptionsCompat.
                                makeSceneTransitionAnimation(MainActivity.this, (View) explicitImage, getString(R.string.product_transition));
                        startActivity(intent, options.toBundle());
                    } else {
                        startActivity(intent);
                    }
                }
            }
        });

        explicitImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(MainActivity.this, "Crash!", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
