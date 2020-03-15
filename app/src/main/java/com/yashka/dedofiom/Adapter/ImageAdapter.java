package com.yashka.dedofiom.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.yashka.dedofiom.R;
import com.yashka.dedofiom.Utill.Utility;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {
    private Context context;
    /* access modifiers changed from: private */
    public List<String> imageNames;
    /* access modifiers changed from: private */
    public String mImage;
    /* access modifiers changed from: private */
    public int rowIndex;

    public class MyViewHolder extends ViewHolder implements OnClickListener, OnLongClickListener {
        public ImageView editImage;
        public LinearLayout imagewraper;

        public void onClick(View view) {
        }

        public boolean onLongClick(View view) {
            return false;
        }

        public MyViewHolder(@NonNull View view) {
            super(view);
            this.editImage = (ImageView) view.findViewById(R.id.edit_image);
            this.imagewraper = (LinearLayout) view.findViewById(R.id.image_wrapper);
            this.editImage.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ImageAdapter.this.rowIndex = MyViewHolder.this.getAdapterPosition();
                    ImageAdapter.this.mImage = (String) ImageAdapter.this.imageNames.get(ImageAdapter.this.rowIndex);
                    ImageAdapter.this.notifyDataSetChanged();
                }
            });
            this.editImage.setOnLongClickListener(new OnLongClickListener() {
                public boolean onLongClick(View view) {
                    return false;
                }
            });
        }
    }

    public String getmImage() {
        return this.mImage;
    }

    public ImageAdapter(Context context2, List<String> list, int i) {
        this.context = context2;
        this.imageNames = list;
        this.rowIndex = i;
        this.mImage = (String) list.get(i);
    }

    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_layout, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.editImage.setImageResource(Utility.getImageId((String) this.imageNames.get(i), this.context).intValue());
        if (this.rowIndex == i) {
            myViewHolder.imagewraper.setBackgroundColor(Color.parseColor("#efe9f4"));
        } else {
            myViewHolder.imagewraper.setBackgroundColor(Color.parseColor("#ffffff"));
        }
    }

    public int getItemCount() {
        return this.imageNames.size();
    }
}
