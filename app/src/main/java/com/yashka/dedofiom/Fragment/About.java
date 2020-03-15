package com.yashka.dedofiom.Fragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


import com.yashka.dedofiom.BuildConfig;
import com.yashka.dedofiom.R;

public class About extends Fragment implements IOnFocusListenable {
    AnimationDrawable animationDrawable;
    ImageView imageView;
    TextView version;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.about, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        this.version = (TextView) view.findViewById(R.id.version);
        this.imageView = (ImageView) view.findViewById(R.id.anim);
        TextView textView = this.version;
        StringBuilder sb = new StringBuilder();
        sb.append("Version : ");
        sb.append(BuildConfig.VERSION_NAME);
        textView.setText(sb.toString());
//        this.imageView.setBackgroundResource(R.drawable.splash_animation);
        this.animationDrawable = (AnimationDrawable) this.imageView.getBackground();
    }

    public void onWindowFocusChanged(boolean z) {
        this.animationDrawable.start();
        System.out.println("ddddqqqq");
    }
}
