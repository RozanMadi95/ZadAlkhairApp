package com.dev.zadalkhairapp.consumer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.dev.zadalkhairapp.R;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import nl.joery.animatedbottombar.AnimatedBottomBar;

public class MainActivity extends AppCompatActivity {
    AnimatedBottomBar bottomBar;
    HomeFragment homeFragment = null;
    FavoriteFragment favoriteFragment = null;
    CartFragment cartFragment = null;
    ProfileFragment profileFragment = null;
    EmptyFavouriteFragment emptyFavouriteFragment = null;
    EmptyCartFragment emptyCartFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        bottomBar = findViewById(R.id.consumer_bottom_navigation);

        homeFragment = HomeFragment.newInstance("","");
        favoriteFragment = FavoriteFragment.newInstance("","");
        cartFragment = CartFragment.newInstance("","");
        profileFragment = ProfileFragment.newInstance("","");
        emptyCartFragment = EmptyCartFragment.newInstance("","");
        emptyFavouriteFragment = EmptyFavouriteFragment.newInstance("","");



        bottomBar.selectTabById(R.id.consumerHome,true);
        setFragmentToContainer(homeFragment);
        bottomBar.setOnTabSelected(new Function1<AnimatedBottomBar.Tab, Unit>() {
            @Override
            public Unit invoke(AnimatedBottomBar.Tab tab) {
                if (tab.getId() == R.id.consumerHome){
                    setFragmentToContainer(homeFragment);
                }else if (tab.getId() == R.id.consumerFavorite){
                    setFragmentToContainer(emptyFavouriteFragment);

                }else if (tab.getId() == R.id.consumerCart){
                    setFragmentToContainer(emptyCartFragment);

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