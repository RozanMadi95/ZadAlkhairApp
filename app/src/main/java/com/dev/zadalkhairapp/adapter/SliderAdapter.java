package com.dev.zadalkhairapp.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.dev.zadalkhairapp.R;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    int imagesSlider[]={
            R.drawable.sf,
            R.drawable.st,
            R.drawable.ss
    };
    int descriptionsSlider[]={
            R.string.first_slide_description,
            R.string.third_slide_description,
            R.string.second_slide_description
    };


    @Override
    public int getCount() {
        return descriptionsSlider.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view == (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position)  {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slides_layout,container,false);

        ImageView imageView = view.findViewById(R.id.slider_image);
        TextView textViewDescription = view.findViewById(R.id.slider_descriptions);


        imageView.setImageResource(imagesSlider[position]);
        textViewDescription.setText(descriptionsSlider[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }
}