package com.yashka.dedofiom.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.yashka.dedofiom.Model.BlockObject;

import com.yashka.dedofiom.Utill.DataBase;
import com.yashka.dedofiom.Utill.Utility;
import com.yashka.dedofiom.R;

import java.io.PrintStream;
import java.util.List;

public abstract class BlockListAdapter extends RecyclerView.Adapter<BlockListAdapter.MyViewHolder> {
    private TextView allDevice;
    private TextView allDeviceRunning;

    private Context context;
    private DataBase dataBase;
    private int idClicked;
    private LinearLayout noDataLogo;
    private RelativeLayout notifyLayout;
    public int orientation;

    /* renamed from: rv */
    private RecyclerView rv;
    /* access modifiers changed from: private */
    public Typeface textFont;
    private List<BlockObject> blockObjects;

    public class MyViewHolder extends ViewHolder implements OnClickListener, OnLongClickListener, OnTouchListener {
        private CardView blockCard;
        /* access modifiers changed from: private */
        public ImageView image;
        /* access modifiers changed from: private */
        public TextView name;

        public void onClick(View view) {
        }

        public boolean onLongClick(View view) {
            return false;
        }

        private MyViewHolder(@NonNull View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.name);
            this.image = (ImageView) view.findViewById(R.id.image);
            this.blockCard = (CardView) view.findViewById(R.id.block_card);
           this.name.setTypeface(BlockListAdapter.this.textFont);
            this.image.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    int adapterPosition = MyViewHolder.this.getAdapterPosition();
                    PrintStream printStream = System.out;
                    StringBuilder sb = new StringBuilder();
                    sb.append("koakoako itemPosition ");
                    sb.append(adapterPosition);
                    printStream.println(sb.toString());
                    BlockListAdapter.this.blockClick(adapterPosition);
                }
            });
            this.image.setOnLongClickListener(new OnLongClickListener() {
                public boolean onLongClick(View view) {
                    BlockListAdapter.this.blockLongClick(MyViewHolder.this.getAdapterPosition());
                    return false;
                }
            });
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() != 0) {
            }
            return false;
        }
    }

    public abstract void blockClick(int i);

    public abstract void blockLongClick(int i);

    protected BlockListAdapter(List<BlockObject> list, Context context2, RecyclerView recyclerView, LinearLayout linearLayout, int i) {
        this.blockObjects = list;
        this.context = context2;
        this.rv = recyclerView;
        this.noDataLogo = linearLayout;
        this.idClicked = i;
       this.textFont = Typeface.createFromAsset(context2.getAssets(), "fonts/BhashitaComplexBoldEnglish.ttf");
        this.dataBase = new DataBase(context2);
    }

    public void setBlockObjects(List<BlockObject> list) {
        this.blockObjects = list;
        PrintStream printStream = System.out;
        StringBuilder sb = new StringBuilder();
        sb.append("koakoako list ");
        sb.append(list.size());
        printStream.println(sb.toString());
    }

    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bolck_card, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        BlockObject blockObject = (BlockObject) this.blockObjects.get(i);
        String str = blockObject.name;
        String str2 = blockObject.image;
        PrintStream printStream = System.out;
        StringBuilder sb = new StringBuilder();
        sb.append("dhddjd ");
        sb.append(str2);
        printStream.println(sb.toString());
        myViewHolder.name.setText(str);
        myViewHolder.image.setImageResource(Utility.getImageId(str2, this.context).intValue());
    }

    public int getItemCount() {
        return this.blockObjects.size();
    }

    private void setParams(View view) {
        if (this.orientation == 1) {
            view.setLayoutParams(new LayoutParams(-1, -2));
        } else {
            view.setLayoutParams(new LayoutParams(-2, -1));
        }
    }
}
