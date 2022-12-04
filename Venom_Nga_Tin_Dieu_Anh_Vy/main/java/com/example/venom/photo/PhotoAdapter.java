package com.example.venom.photo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.venom.HomeFragment;
import com.example.venom.R;

import java.util.List;

public class PhotoAdapter extends PagerAdapter {

    private HomeFragment context;
    private List<Photo> listPhoto;

    public PhotoAdapter(HomeFragment context, List<Photo> listPhoto) {
        this.context = context;
        this.listPhoto = listPhoto;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_photo, container, false);
        ImageView imgPhoto = view.findViewById(R.id.img_photo);

        Photo photo = listPhoto.get(position);
        if (photo != null){
            Glide.with(context).load(photo.getResourceId()).into(imgPhoto);

        }
        //add view to Vg
        container.addView(view);

        return view;
    }
    @Override
    public int getCount() {
        if (listPhoto != null){
            return listPhoto.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //Remove
        container.removeView((View) object);
    }

}
