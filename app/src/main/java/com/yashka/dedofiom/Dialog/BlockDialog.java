package com.yashka.dedofiom.Dialog;



import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;
import com.yashka.dedofiom.Adapter.ImageAdapter;
import com.yashka.dedofiom.Model.BlockObject;
import com.yashka.dedofiom.R;
import com.yashka.dedofiom.Utill.DataBase;
import com.yashka.dedofiom.Utill.Utility;

import es.dmoral.toasty.Toasty;
import java.util.List;

public abstract class BlockDialog extends Dialog {
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public DataBase dataBase;
    /* access modifiers changed from: private */
    public int id;
    /* access modifiers changed from: private */
    public ImageAdapter imageAdapter;
    /* access modifiers changed from: private */
    public String imageName;
    /* access modifiers changed from: private */
    public EditText ip;
    /* access modifiers changed from: private */
    public EditText name;
    /* access modifiers changed from: private */
    public EditText port;
    private RecyclerView rv;
    private Typeface textFont;
    private TextView txt_name;

    public void createBlockList() {
    }

    public abstract void loadImgEmpty();

    public abstract void notifyDataSetChange();

    public BlockDialog(Context context2, RecyclerView recyclerView, int i) {
        super(context2);
        this.context = context2;
        this.rv = recyclerView;
        this.id = i;
        this.textFont = Typeface.createFromAsset(context2.getAssets(), "fonts/BhashitaComplexBoldEnglish.ttf");
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.add_block);
        Button button = (Button) findViewById(R.id.btn_save);
        Button button2 = (Button) findViewById(R.id.btn_cancel);
        this.name = (EditText) findViewById(R.id.name);
        this.txt_name = (TextView) findViewById(R.id.txt_name);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ip_port);
        this.ip = (EditText) findViewById(R.id.ip);
        this.port = (EditText) findViewById(R.id.port);
        this.name.setTypeface(this.textFont);
        this.txt_name.setTypeface(this.textFont);
        button.setTypeface(this.textFont);
        button2.setTypeface(this.textFont);
        this.dataBase = new DataBase(this.context);
        List images = this.dataBase.getImages(0);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.edit_tool);
        int i = this.id;
        if (i == 0) {
            this.name.setText("");
            this.imageAdapter = new ImageAdapter(this.context, images, 0);
        } else {
            BlockObject blockByid = this.dataBase.getBlockByid(i);
            this.imageAdapter = new ImageAdapter(this.context, images, Utility.getIndexOfStringList(images, blockByid.image));
            this.name.setText(blockByid.name);
        }//end of else
//        AdView adView = (AdView) findViewById(R.id.adView);
        recyclerView.setLayoutManager(new GridLayoutManager(this.context, 1, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(this.imageAdapter);
        if (this.dataBase.getConnectFirst() == 0) {
            linearLayout.setVisibility(View.GONE);
        } else {
            linearLayout.setVisibility(View.VISIBLE);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String obj = BlockDialog.this.name.getText().toString();
                String obj2 = BlockDialog.this.ip.getText().toString();
                String obj3 = BlockDialog.this.port.getText().toString();
                String str = "Please Enter Name";
                String str2 = "";
                if (BlockDialog.this.dataBase.getConnectFirst() == 0) {
                    if (!obj.equals(str2)) {
                        BlockDialog blockDialog = BlockDialog.this;
                        blockDialog.imageName = blockDialog.imageAdapter.getmImage();
                        if (BlockDialog.this.id == 0) {
                            BlockDialog.this.dataBase.insertBlock(obj, BlockDialog.this.imageName);
                        } else {
                            BlockDialog.this.dataBase.updateBlock(BlockDialog.this.id, obj, BlockDialog.this.imageName);
                        }
                        BlockDialog.this.notifyDataSetChange();
                        BlockDialog.this.dismiss();
                    } else {
                        Toasty.error(BlockDialog.this.context, (CharSequence) str, 0, true).show();
                    }
                } else if (!obj.equals(str2) || !obj2.equals(str2) || !obj3.equals(str2)) {
                    BlockDialog blockDialog2 = BlockDialog.this;
                    blockDialog2.imageName = blockDialog2.imageAdapter.getmImage();
                    if (BlockDialog.this.id == 0) {
                        BlockDialog.this.dataBase.insertBlock(obj, BlockDialog.this.imageName, obj2, obj3);
                    } else {
                        BlockDialog.this.dataBase.updateBlock(BlockDialog.this.id, obj, BlockDialog.this.imageName, obj2, obj3);
                    }
                    BlockDialog.this.dismiss();
                } else {
                    Toasty.error(BlockDialog.this.context, (CharSequence) str, 0, true).show();
                }
                BlockDialog.this.loadImgEmpty();
            }

        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BlockDialog.this.dismiss();
            }
        });
        if (this.dataBase.isShowAdd() == Utility.SHOW_ADS) {
//            adView.setVisibility(View.VISIBLE);
//            adView.loadAd(new Builder().build());
            return;
        }
//        adView.setVisibility(View.GONE);
    }
}




























































//
//import android.app.Dialog;
//import android.content.Context;
//import android.graphics.Typeface;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.android.gms.ads.AdRequest.Builder;
//import com.google.android.gms.ads.AdView;
//import com.yashka.dedofiom.Adapter.ImageAdapter;
//import com.yashka.dedofiom.Model.BlockObject;
//import com.yashka.dedofiom.R;
//import com.yashka.dedofiom.Utill.DataBase;
//import com.yashka.dedofiom.Utill.Utility;
//
//import java.util.List;
//
//import es.dmoral.toasty.Toasty;
//
//
//public abstract class BlockDialog extends Dialog {
//    /* access modifiers changed from: private */
//    public Context context;
//    /* access modifiers changed from: private */
//    public DataBase dataBase=null;
//    /* access modifiers changed from: private */
//
//    /* renamed from: id */
//    public int f69id;
//    /* access modifiers changed from: private */
//    public ImageAdapter imageAdapter;
//    /* access modifiers changed from: private */
//    public String imageName;
//    /* access modifiers changed from: private */
//
//    /* renamed from: ip */
//    public EditText f70ip;
//    /* access modifiers changed from: private */
//    public EditText name;
//    /* access modifiers changed from: private */
//    public EditText port;
//
//    /* renamed from: rv */
//    private RecyclerView f71rv;
//    private Typeface textFont;
//    private TextView txt_name;
//
//    public void createBlockList() {
//    }
//
//    public abstract void loadImgEmpty();
//
//    public abstract void notifyDataSetChange();
//
//    public BlockDialog(Context context2, RecyclerView recyclerView, int i) {
//        super(context2);
//        this.context = context2;
//        this.f71rv = recyclerView;
//        this.f69id = i;
//        this.textFont = Typeface.createFromAsset(context2.getAssets(), "fonts/BhashitaComplexBoldEnglish.ttf");
//    }
//
//    /* access modifiers changed from: protected */
//    public void onCreate(Bundle bundle) {
//        super.onCreate(bundle);
//        setContentView(R.layout.add_block);
//        Button button = (Button) findViewById(R.id.btn_save);
//        Button button2 = (Button) findViewById(R.id.btn_cancel);
//        this.name = (EditText) findViewById(R.id.name);
//        this.txt_name = (TextView) findViewById(R.id.txt_name);
//        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ip_port);
//        this.f70ip = (EditText) findViewById(R.id.ip);
//        this.port = (EditText) findViewById(R.id.port);
//        this.name.setTypeface(this.textFont);
//        this.txt_name.setTypeface(this.textFont);
//        button.setTypeface(this.textFont);
//        button2.setTypeface(this.textFont);
//        this.dataBase = new DataBase(this.context);
//        List images = this.dataBase.getImages(0);
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.edit_tool);
//        int i = this.f69id;
//        if (i == 0) {
//            this.name.setText("");
//            this.imageAdapter = new ImageAdapter(this.context, images, 0);
//        } else {
//            BlockObject blockByid = this.dataBase.getBlockByid(i);
//            this.imageAdapter = new ImageAdapter(this.context, images, Utility.getIndexOfStringList(images, blockByid.image));
//            this.name.setText(blockByid.name);
//        }
//   //     AdView adView = (AdView) findViewById(R.id.adView);
//        recyclerView.setLayoutManager(new GridLayoutManager(this.context, 1, RecyclerView.HORIZONTAL, false));
//        recyclerView.setAdapter(this.imageAdapter);
//        if (this.dataBase.getConnectFirst() == 0) {
//            linearLayout.setVisibility(View.VISIBLE);
//        } else {
//            linearLayout.setVisibility(View.VISIBLE);
//        }
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String obj = BlockDialog.this.name.getText().toString();
//                String obj2 = BlockDialog.this.f70ip.getText().toString();
//                String obj3 = BlockDialog.this.port.getText().toString();
//                String str = "Please Enter Name";
//                String str2 = "";
//                if (BlockDialog.this.dataBase.getConnectFirst() == 0) {
//                    if (!obj.equals(str2)) {
//                        BlockDialog blockDialog = BlockDialog.this;
//                        blockDialog.imageName = blockDialog.imageAdapter.getmImage();
//                        if (BlockDialog.this.f69id == 0) {
//                            BlockDialog.this.dataBase.insertBlock(obj, BlockDialog.this.imageName);
//                        } else {
//                            BlockDialog.this.dataBase.updateBlock(BlockDialog.this.f69id, obj, BlockDialog.this.imageName);
//                        }
//                        BlockDialog.this.notifyDataSetChange();
//                        BlockDialog.this.dismiss();
//                    } else {
//                        Toasty.error(BlockDialog.this.context, (CharSequence) str, 0, true).show();
//                    }
//                } else if (!obj.equals(str2) || !obj2.equals(str2) || !obj3.equals(str2)) {
//                    BlockDialog blockDialog2 = BlockDialog.this;
//                    blockDialog2.imageName = blockDialog2.imageAdapter.getmImage();
//                    if (BlockDialog.this.f69id == 0) {
//                        BlockDialog.this.dataBase.insertBlock(obj, BlockDialog.this.imageName, obj2, obj3);
//                    } else {
//                        BlockDialog.this.dataBase.updateBlock(BlockDialog.this.f69id, obj, BlockDialog.this.imageName, obj2, obj3);
//                    }
//                    BlockDialog.this.dismiss();
//                } else {
//                    Toasty.error(BlockDialog.this.context, (CharSequence) str, 0, true).show();
//                }
//                BlockDialog.this.loadImgEmpty();
//            }
//        });
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                BlockDialog.this.dismiss();
//            }
//        });
//        if (this.dataBase.isShowAdd() == Utility.SHOW_ADS) {
////            adView.setVisibility(View.VISIBLE);
////            adView.loadAd(new Builder().build());
//            return;
//        }
//      //  adView.setVisibility(View.VISIBLE);
//    }
//}
