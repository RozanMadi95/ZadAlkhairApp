package com.dev.zadalkhairapp.restaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.dev.zadalkhairapp.R;
import com.dev.zadalkhairapp.consumer.CartFragment;
import com.dev.zadalkhairapp.consumer.FavoriteFragment;
import com.dev.zadalkhairapp.consumer.HomeFragment;
import com.dev.zadalkhairapp.consumer.ProfileFragment;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import nl.joery.animatedbottombar.AnimatedBottomBar;

public class RestaurantMainActivity extends AppCompatActivity {
    AnimatedBottomBar bottomBar;
    HomeFragment homeFragment = null;
    FavoriteFragment favoriteFragment = null;
    CartFragment cartFragment = null;
    ProfileFragment profileFragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        bottomBar = findViewById(R.id.consumer_bottom_navigation);

        homeFragment = HomeFragment.newInstance("","");
        favoriteFragment = FavoriteFragment.newInstance("","");
        cartFragment = CartFragment.newInstance("","");
        profileFragment = ProfileFragment.newInstance("","");



        bottomBar.selectTabById(R.id.consumerHome,true);
        setFragmentToContainer(homeFragment);
        bottomBar.setOnTabSelected(new Function1<AnimatedBottomBar.Tab, Unit>() {
            @Override
            public Unit invoke(AnimatedBottomBar.Tab tab) {
                if (tab.getId() == R.id.consumerHome){
                    setFragmentToContainer(homeFragment);
                }else if (tab.getId() == R.id.consumerFavorite){
                    setFragmentToContainer(favoriteFragment);

                }else if (tab.getId() == R.id.consumerCart){
                    setFragmentToContainer(cartFragment);

                }else if (tab.getId() == R.id.consumerProfile){
                    setFragmentToContainer(profileFragment);
                }
                return null;
            }
        });
    }

    private void setFragmentToContainer(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, fragment)
                .commit();
    }

}
