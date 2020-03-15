package com.yashka.dedofiom.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yashka.dedofiom.R;

import java.util.List;

public class StringAdapter extends RecyclerView.Adapter<StringAdapter.MyViewHolder> {
    private Context context;
    /* access modifiers changed from: private */
    public int rowIndex;
    /* access modifiers changed from: private */
    public List<String> strings;
    /* access modifiers changed from: private */
    public Typeface textFont;
    /* access modifiers changed from: private */
    public String unit;

    public class MyViewHolder extends RecyclerView.ViewHolder implements OnClickListener, OnLongClickListener {
        /* access modifiers changed from: private */
        public TextView txtUnit;

        public void onClick(View view) {
        }

        public boolean onLongClick(View view) {
            return false;
        }

        public MyViewHolder(@NonNull View view) {
            super(view);
            this.txtUnit = (TextView) view.findViewById(R.id.txt_id);
            this.txtUnit.setTypeface(StringAdapter.this.textFont);
            this.txtUnit.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    StringAdapter.this.rowIndex = MyViewHolder.this.getAdapterPosition();
                    StringAdapter.this.notifyDataSetChanged();
                    StringAdapter.this.unit = (String) StringAdapter.this.strings.get(StringAdapter.this.rowIndex);
                }
            });
        }
    }

    public StringAdapter(Context context2, List<String> list, int i) {
        this.context = context2;
        this.strings = list;
        this.rowIndex = i;
        this.unit = (String) list.get(i);
        this.textFont = Typeface.createFromAsset(context2.getAssets(), "fonts/BhashitaComplexBoldEnglish.ttf");
    }

    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.common_string_layout, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.txtUnit.setText((CharSequence) this.strings.get(i));
        if (this.rowIndex == i) {
            myViewHolder.txtUnit.setBackgroundColor(Color.parseColor("#efe9f4"));
        } else {
            myViewHolder.txtUnit.setBackgroundColor(Color.parseColor("#ffffff"));
        }
    }

    public int getItemCount() {
        return this.strings.size();
    }

    public String getUnit() {
        return this.unit;
    }
}
