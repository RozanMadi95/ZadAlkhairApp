package com.dev.zadalkhairapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewpager.widget.ViewPager;


import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;


import com.dev.zadalkhairapp.R;
import com.dev.zadalkhairapp.adapter.SliderAdapter;

public class OnBoardingScreen extends AppCompatActivity {
    ViewPager viewPagerSlider;
    Button buttonGetStart;
    LinearLayoutCompat dotsLayout;
    TextView[] dots;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_screen);
        findViewById();

        viewPagerSlider.setAdapter( new SliderAdapter(this));

        addDots(0);
        viewPagerSlider.addOnPageChangeListener(changeListener);

        buttonGetStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OnBoardingScreen.this,
                        TypeUserScreen.class));
                finish();
            }
        });

    }


    public void findViewById() {
        viewPagerSlider = findViewById(R.id.slider);
        buttonGetStart = findViewById(R.id.get_start_btn);
        dotsLayout = findViewById(R.id.dots);
    }

    private void addDots(int position) {

        dots = new TextView[3];
        dotsLayout.removeAllViews();


        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(40);

            dotsLayout.addView(dots[i]);
        }
        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.orange));
        }



    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener()
    {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);

            if (position == 0) {
                buttonGetStart.setVisibility(View.INVISIBLE);
            } else if (position == 1) {
                buttonGetStart.setVisibility(View.INVISIBLE);
            }else {
                animation = AnimationUtils.loadAnimation(OnBoardingScreen.this, R.anim.bottom_anim);
                buttonGetStart.setAnimation(animation);
                buttonGetStart.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };



}