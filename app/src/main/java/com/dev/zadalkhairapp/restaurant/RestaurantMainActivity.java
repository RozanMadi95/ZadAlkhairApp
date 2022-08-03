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
    RestaurantHomeFragment  restaurantHomeFragment= null;
    RestaurantMealsFragment restaurantMealsFragment = null;
    RestaurantAddMealsFragment restaurantAddMealsFragment = null;
    RestaurantAssociationsFragment restaurantAssociationsFragment = null;
    RestaurantProfileFragment restaurantProfileFragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        bottomBar = findViewById(R.id.restaurant_bottom_navigation);

        restaurantHomeFragment = RestaurantHomeFragment.newInstance("","");
        restaurantMealsFragment = RestaurantMealsFragment.newInstance("","");
        restaurantAddMealsFragment = RestaurantAddMealsFragment.newInstance("","");
        restaurantAssociationsFragment = RestaurantAssociationsFragment.newInstance("","");
        restaurantProfileFragment = RestaurantProfileFragment.newInstance("","");



        bottomBar.selectTabById(R.id.restaurantHome,true);
        setFragmentToContainer(restaurantHomeFragment);
        bottomBar.setOnTabSelected(new Function1<AnimatedBottomBar.Tab, Unit>() {
            @Override
            public Unit invoke(AnimatedBottomBar.Tab tab) {
                if (tab.getId() == R.id.restaurantHome){
                    setFragmentToContainer(restaurantHomeFragment);
                }else if (tab.getId() == R.id.restaurantMeals){
                    setFragmentToContainer(restaurantMealsFragment);

                }else if (tab.getId() == R.id.restaurantAddMeals){
                    setFragmentToContainer(restaurantAddMealsFragment);

                }else if (tab.getId() == R.id.restaurantAssociation){
                    setFragmentToContainer(restaurantAssociationsFragment);
                }else if (tab.getId() == R.id.restaurantProfile){
                setFragmentToContainer(restaurantProfileFragment);
            }
                return null;
            }
        });
    }

    private void setFragmentToContainer(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.restaurant_containerView, fragment)
                .commit();
    }

}
