package com.yashka.dedofiom.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build.VERSION;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;
import com.yashka.dedofiom.Dialog.TooLDialog;
import com.yashka.dedofiom.Model.ToolObject;
import com.yashka.dedofiom.R;
import com.yashka.dedofiom.Utill.DataBase;
import com.yashka.dedofiom.Utill.Utility;

import java.io.PrintStream;
import java.util.List;

public abstract class ToolListAdapter extends RecyclerView.Adapter<ToolListAdapter.MyViewHolder> {
    /* access modifiers changed from: private */
    public AdView adView;
    /* access modifiers changed from: private */
    public TextView allDevice;
    /* access modifiers changed from: private */
    public TextView allDeviceRunning;
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public DataBase dataBase;
    /* access modifiers changed from: private */
    public int idClicked;
    /* access modifiers changed from: private */
    public LinearLayout noDataLogo;

    /* renamed from: rv */
    private RecyclerView f68rv;
    /* access modifiers changed from: private */
    public Typeface textFont;
    /* access modifiers changed from: private */
    public List<ToolObject> toolObjects;
    private int toolState;
    /* access modifiers changed from: private */
    public LinearLayout toolWrapper;

    public class MyViewHolder extends ViewHolder implements OnClickListener, OnLongClickListener, OnTouchListener {
        /* access modifiers changed from: private */
        public ImageView image;
        /* access modifiers changed from: private */
        public TextView name;
        /* access modifiers changed from: private */
        public TextView sensorValue;
        /* access modifiers changed from: private */
        public ImageView stateView;
        /* access modifiers changed from: private */
        public int toolId;

        public void onClick(View view) {
        }

        public boolean onLongClick(View view) {
            return false;
        }

        private MyViewHolder(@NonNull View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.txt_Name);
            this.image = (ImageView) view.findViewById(R.id.tool_image);
            this.stateView = (ImageView) view.findViewById(R.id.switch_image);
            ToolListAdapter.this.toolWrapper = (LinearLayout) view.findViewById(R.id.tool_wrapper);
            this.sensorValue = (TextView) view.findViewById(R.id.txt_sensor_value);
            this.name.setTypeface(ToolListAdapter.this.textFont);
            ToolListAdapter.this.toolWrapper.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ToolListAdapter.this.toolClick(MyViewHolder.this.getAdapterPosition(), ToolListAdapter.this.toolWrapper);
                }
            });
            ToolListAdapter.this.toolWrapper.setOnLongClickListener(new OnLongClickListener() {
                public boolean onLongClick(View view) {
                    final Dialog dialog = new Dialog(ToolListAdapter.this.context);
                    dialog.setContentView(R.layout.edit_delete_diaog);
                    ToolListAdapter.this.adView = (AdView) dialog.findViewById(R.id.adView);
                    if (ToolListAdapter.this.dataBase.isShowAdd() == Utility.SHOW_ADS) {
                        ToolListAdapter.this.adView.loadAd(new Builder().build());
                    } else {
                        ToolListAdapter.this.adView.setVisibility(View.VISIBLE);
                    }
                    if (VERSION.SDK_INT >= 19) {
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                    }
                    dialog.show();
                    int adapterPosition = MyViewHolder.this.getAdapterPosition();
                    MyViewHolder myViewHolder = MyViewHolder.this;
                    myViewHolder.toolId = ((ToolObject) ToolListAdapter.this.toolObjects.get(adapterPosition)).id;
                    Button button = (Button) dialog.findViewById(R.id.delete);
                    ((Button) dialog.findViewById(R.id.edit)).setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            TooLDialog r0 = new TooLDialog(ToolListAdapter.this.context, ToolListAdapter.this.idClicked, ToolListAdapter.this.allDevice, ToolListAdapter.this.allDeviceRunning, MyViewHolder.this.toolId) {
                                public void loadImgempty() {
                                    if (ToolListAdapter.this.dataBase.getConnectionType() == Utility.LOCAL) {
                                        if (ToolListAdapter.this.dataBase.getToolInBlock(ToolListAdapter.this.idClicked, Utility.DEVICE).size() == 0) {
                                            ToolListAdapter.this.noDataLogo.setVisibility(View.VISIBLE);
                                        } else {
                                            ToolListAdapter.this.noDataLogo.setVisibility(View.VISIBLE);
                                        }
                                    } else if (ToolListAdapter.this.dataBase.getToolInBlock(ToolListAdapter.this.idClicked).size() == 0) {
                                        ToolListAdapter.this.noDataLogo.setVisibility(View.VISIBLE);
                                    } else {
                                        ToolListAdapter.this.noDataLogo.setVisibility(View.VISIBLE);
                                    }
                                }

                                public void notifyDataSetChange() {
                                    if (ToolListAdapter.this.dataBase.getConnectionType() == Utility.LOCAL) {
                                        ToolListAdapter.this.toolObjects = ToolListAdapter.this.dataBase.getToolInBlock(ToolListAdapter.this.idClicked, Utility.DEVICE);
                                    } else {
                                        ToolListAdapter.this.toolObjects = ToolListAdapter.this.dataBase.getToolInBlock(ToolListAdapter.this.idClicked);
                                    }
                                    ToolListAdapter.this.notifyItemInserted(ToolListAdapter.this.toolObjects.size() - 1);
                                    ToolListAdapter.this.notifyDataSetChanged();
                                }
                            };
                            if (VERSION.SDK_INT >= 19) {
                                r0.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                            }
                            dialog.dismiss();
                            r0.show();
                        }
                    });
                    button.setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            final Dialog dialog = new Dialog(ToolListAdapter.this.context);
                            dialog.setContentView(R.layout.exit_dialog);
                            ToolListAdapter.this.adView = (AdView) dialog.findViewById(R.id.adView);
                            if (ToolListAdapter.this.dataBase.isShowAdd() == Utility.SHOW_ADS) {
                                ToolListAdapter.this.adView.loadAd(new Builder().build());
                            } else {
                                ToolListAdapter.this.adView.setVisibility(View.VISIBLE);
                            }
                            dialog.dismiss();
                            dialog.show();
                            Button button = (Button) dialog.findViewById(R.id.btn_no);
                            Button button2 = (Button) dialog.findViewById(R.id.btn_yes);
                            ((TextView) dialog.findViewById(R.id.txt_confirm)).setText("Are you sure you want to delete?");
                            if (VERSION.SDK_INT >= 19) {
                                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                            }
                            button.setOnClickListener(new OnClickListener() {
                                public void onClick(View view) {
                                    dialog.dismiss();
                                    dialog.dismiss();
                                }
                            });
                            button2.setOnClickListener(new OnClickListener() {
                                public void onClick(View view) {
                                    ToolListAdapter.this.dataBase.deleteTool(MyViewHolder.this.toolId);
                                    dialog.dismiss();
                                    dialog.dismiss();
                                    if (ToolListAdapter.this.dataBase.getConnectionType() == Utility.LOCAL) {
                                        ToolListAdapter.this.toolObjects = ToolListAdapter.this.dataBase.getToolInBlock(ToolListAdapter.this.idClicked, Utility.DEVICE);
                                        if (ToolListAdapter.this.toolObjects.size() == 0) {
                                            ToolListAdapter.this.noDataLogo.setVisibility(View.VISIBLE);
                                        } else {
                                            ToolListAdapter.this.noDataLogo.setVisibility(View.VISIBLE);
                                        }
                                    } else {
                                        ToolListAdapter.this.toolObjects = ToolListAdapter.this.dataBase.getToolInBlock(ToolListAdapter.this.idClicked);
                                        if (ToolListAdapter.this.toolObjects.size() == 0) {
                                            ToolListAdapter.this.noDataLogo.setVisibility(View.VISIBLE);
                                        } else {
                                            ToolListAdapter.this.noDataLogo.setVisibility(View.VISIBLE);
                                        }
                                    }
                                    ToolListAdapter.this.notifyDataSetChanged();
                                }
                            });
                        }
                    });
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

    public abstract void toolClick(int i, LinearLayout linearLayout);

    public abstract void toolLongClick(int i);

    public void setToolObjects(List<ToolObject> list) {
        this.toolObjects = list;
    }

    public ToolListAdapter(List<ToolObject> list, Context context2, RecyclerView recyclerView, LinearLayout linearLayout, int i, TextView textView, TextView textView2) {
        this.toolObjects = list;
        this.context = context2;
        this.f68rv = recyclerView;
        this.noDataLogo = linearLayout;
        this.idClicked = i;
        this.allDevice = textView;
        this.allDeviceRunning = textView2;
        this.textFont = Typeface.createFromAsset(context2.getAssets(), "fonts/BhashitaComplexBoldEnglish.ttf");
        this.dataBase = new DataBase(context2);
    }

    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tool_card, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        this.toolState = ((ToolObject) this.toolObjects.get(i)).toolState;
        myViewHolder.name.setText(((ToolObject) this.toolObjects.get(i)).name);
        myViewHolder.image.setImageResource(Utility.getImageId(((ToolObject) this.toolObjects.get(i)).getToolImage(), this.context).intValue());
        TextView access$300 = myViewHolder.sensorValue;
        StringBuilder sb = new StringBuilder();
        sb.append(this.toolState);
        sb.append("");
        sb.append(((ToolObject) this.toolObjects.get(i)).siUnit);
        access$300.setText(sb.toString());
        if (this.toolState == 1) {
            myViewHolder.stateView.setImageResource(R.drawable.on);
        } else {
            myViewHolder.stateView.setImageResource(R.drawable.off);
        }
        PrintStream printStream = System.out;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("hjhsajsh ");
        sb2.append(((ToolObject) this.toolObjects.get(i)).toolState);
        printStream.println(sb2.toString());
        if (((ToolObject) this.toolObjects.get(i)).deviceType == Utility.SENSOR) {
            myViewHolder.sensorValue.setVisibility(View.VISIBLE);
            myViewHolder.stateView.setVisibility(View.VISIBLE);
            return;
        }
        myViewHolder.sensorValue.setVisibility(View.VISIBLE);
        myViewHolder.stateView.setVisibility(View.VISIBLE);
    }

    public int getItemCount() {
        return this.toolObjects.size();
    }
}
