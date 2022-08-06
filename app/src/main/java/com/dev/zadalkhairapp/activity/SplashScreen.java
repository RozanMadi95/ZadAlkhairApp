package com.dev.zadalkhairapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.zadalkhairapp.R;
import com.dev.zadalkhairapp.consumer.MainActivity;
import com.dev.zadalkhairapp.consumer.ProfileFragment;
import com.dev.zadalkhairapp.restaurant.RestaurantActivity;
import com.dev.zadalkhairapp.restaurant.RestaurantEditMealsActivity;
import com.dev.zadalkhairapp.restaurant.RestaurantEditProfileActivity;
import com.dev.zadalkhairapp.restaurant.RestaurantMainActivity;

public class SplashScreen extends AppCompatActivity {
    //تعريف المتغيرات
    ImageView ivLogo;
    TextView tvAppName;
    CharSequence charSequence;
    int index;
    long delay = 200;
    Handler handler = new Handler();
    SharedPreferences sharedPreferencesOnBoarding;
    private SharedPreferences.Editor sharedPreferencesEditor;
    public static final String SP_NAME= "OnBoardingScreen";
    public static final String SP_KEY_FIRST_TIME= "FirstTime";
    private static final int SPLASH_DURATION = 3000; // الوقت المحدد للانتظار في السبلاش = 3000 =3ث

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
            findViewById(); //انفلات للعناصر

            //تهيئة اوبجكت انميشن
            ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
                    ivLogo,
                    PropertyValuesHolder.ofFloat("scaleX",1.2f),
                    PropertyValuesHolder.ofFloat("scaleY",1.2f)
            );

            //تعين المدة الزمنية للانميشن
            objectAnimator.setDuration(500);

            //تعين عدد تكرار
            objectAnimator.setRepeatCount(ValueAnimator.INFINITE);

            //تعين مود التكرار
            objectAnimator.setRepeatMode(ValueAnimator.REVERSE);

            //بداية الانميشن
            objectAnimator.start();

            //حط نص الانميشن من خلال استدعاء دالة انميشن النص
            animationText(getResources().getString(R.string.logo));

            new Handler().postDelayed(() -> {
                //قراءة مؤشر
                sharedPreferencesOnBoarding = getSharedPreferences(SP_NAME, MODE_PRIVATE);
                //قراءة القيمة للبحث عن مفتاح
                boolean isFirstTime = sharedPreferencesOnBoarding.getBoolean(SP_KEY_FIRST_TIME, true);

            /*
            ذا اول مرة  بفوت التطبيق
            انقله من شاشة السبلاش لشاشة الوصف لتطبيق
             */
                if(isFirstTime) {
                    sharedPreferencesEditor = sharedPreferencesOnBoarding.edit(); // افتح محرر
                    sharedPreferencesEditor.putBoolean(SP_KEY_FIRST_TIME,false); // اكتب فيه قيمة منطقية من اسم الكي
                    sharedPreferencesEditor.commit();

                    startActivity(new Intent(SplashScreen.this,
                            OnBoardingScreen.class)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }else {
//                    startActivity(new Intent(SplashScreen.this,
//                            TypeUserScreen.class));
                    startActivity(new Intent(SplashScreen.this,
                            RestaurantActivity.class));
                }
                finish();
            },SPLASH_DURATION);
        }

        public void findViewById() {
            ivLogo = findViewById(R.id.iv_logo);
            tvAppName = findViewById(R.id.tv_name_app);
        }

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //عند ما رانندبل ييداء
                // خد النص
                tvAppName.setText(charSequence.subSequence(0,index++));
                //افحص الشرط
                if(index <= charSequence.length()){
                    //اذا المؤشر يساوي طول النص  شغل الهندلر
                    handler.postDelayed(runnable,delay);
                }

            }
        };


        // انشاء دالة الانميشن للنص
        public  void  animationText(CharSequence sequence){

            charSequence = sequence;  //خد النص
            index = 0; //عين قيمة صفر للمتغير مرشر
            tvAppName.setText(""); // احدف النص
            handler.removeCallbacks(runnable); // احدف الاستدعاء الخلفي
            handler.postDelayed(runnable, delay); // شغل الهندلر
        }

    }