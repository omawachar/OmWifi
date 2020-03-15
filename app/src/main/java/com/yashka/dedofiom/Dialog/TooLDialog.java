package com.yashka.dedofiom.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yashka.dedofiom.Activity.MainActivity;
import com.yashka.dedofiom.Adapter.ImageAdapter;
import com.yashka.dedofiom.Adapter.StringAdapter;
import com.yashka.dedofiom.Model.ToolObject;
import com.yashka.dedofiom.R;
import com.yashka.dedofiom.Utill.DataBase;
import com.yashka.dedofiom.Utill.Utility;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;


public abstract class TooLDialog extends Dialog {
    /* access modifiers changed from: private */

    /* renamed from: IP */
    public EditText f74IP;
    /* access modifiers changed from: private */
    public EditText OffCode;
    private TextView all;
    /* access modifiers changed from: private */
    public String apiKey;
    private Switch btnSwitch;
    private Button cancel;
    /* access modifiers changed from: private */
    public String channelId;
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public int deviceType = Utility.DEVICE;
    /* access modifiers changed from: private */
    public EditText edtApiKey;
    /* access modifiers changed from: private */
    public EditText edtChanelId;
    /* access modifiers changed from: private */
    public EditText edtFieldNumber;
    /* access modifiers changed from: private */
    public String fieldNumber;
    private GridLayoutManager gridLayoutManager;
    /* access modifiers changed from: private */

    /* renamed from: id */
    public int f75id;
    /* access modifiers changed from: private */
    public String imageName;
    private LinearLayout ipPort;
    private boolean isEdid = false;
    private boolean isSensor = false;
    private LinearLayout local_wrapper;
    /* access modifiers changed from: private */
    public EditText name;
    /* access modifiers changed from: private */
    public EditText onCode;
    /* access modifiers changed from: private */
    public EditText port;
    private RecyclerView recyclerView;
    private TextView run;
    private Button save;
    /* access modifiers changed from: private */
    public String strIp;
    /* access modifiers changed from: private */
    public String strName;
    /* access modifiers changed from: private */
    public String strOffCode;
    /* access modifiers changed from: private */
    public String strOnCode;
    /* access modifiers changed from: private */
    public String strPort;
    /* access modifiers changed from: private */
    public StringAdapter stringAdapter;
    private Typeface textFont;
    private LinearLayout thingspeak_wrapper;
    /* access modifiers changed from: private */
    public int toolId;
    /* access modifiers changed from: private */
    public ImageAdapter toolListAdapter;
    private ToolObject toolObject;
    private LinearLayout tuggle_wraper;
    private TextView txt_api_key;
    private TextView txt_c_id;
    private TextView txt_field_no;
    private TextView txt_ip;
    private TextView txt_name;
    private TextView txt_off_code;
    private TextView txt_on_code;
    private TextView txt_port;
    private RecyclerView unitListView;

    public abstract void loadImgempty();

    public abstract void notifyDataSetChange();

    public TooLDialog(Context context2, int i, TextView textView, TextView textView2, int i2) {
        super(context2);
        this.f75id = i;
        this.toolId = i2;
        this.all = textView;
        this.run = textView2;
        this.context = context2;
        this.textFont = Typeface.createFromAsset(context2.getAssets(), "fonts/BhashitaComplexBoldEnglish.ttf");
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.edit_tool_layout);
        this.save = (Button) findViewById(R.id.btn_save);
        this.cancel = (Button) findViewById(R.id.btn_cancel);
        this.name = (EditText) findViewById(R.id.name);
        this.f74IP = (EditText) findViewById(R.id.ip);
        this.OffCode = (EditText) findViewById(R.id.off_code);
        this.onCode = (EditText) findViewById(R.id.on_code);
        this.port = (EditText) findViewById(R.id.port);
        this.ipPort = (LinearLayout) findViewById(R.id.ip_port);
        this.btnSwitch = (Switch) findViewById(R.id.btn_switch);
        this.txt_api_key = (TextView) findViewById(R.id.txt_api_key);
        this.edtApiKey = (EditText) findViewById(R.id.read_api_key);
        this.edtChanelId = (EditText) findViewById(R.id.channel_id);
        this.edtFieldNumber = (EditText) findViewById(R.id.field_no);
        this.thingspeak_wrapper = (LinearLayout) findViewById(R.id.thingspeak_wrapper);
        this.local_wrapper = (LinearLayout) findViewById(R.id.local_wrapper);
        this.tuggle_wraper = (LinearLayout) findViewById(R.id.tuggle_wraper);
        this.txt_name = (TextView) findViewById(R.id.txt_name);
        this.txt_ip = (TextView) findViewById(R.id.txt_ip);
        this.txt_port = (TextView) findViewById(R.id.txt_port);
        this.txt_on_code = (TextView) findViewById(R.id.txt_on_code);
        this.txt_off_code = (TextView) findViewById(R.id.txt_off_code);
        this.txt_c_id = (TextView) findViewById(R.id.txt_c_id);
        this.txt_field_no = (TextView) findViewById(R.id.txt_field_no);
        final DataBase dataBase = new DataBase(this.context);
        this.save.setTypeface(this.textFont);
        this.cancel.setTypeface(this.textFont);
        this.name.setTypeface(this.textFont);
        this.f74IP.setTypeface(this.textFont);
        this.OffCode.setTypeface(this.textFont);
        this.onCode.setTypeface(this.textFont);
        this.port.setTypeface(this.textFont);
        this.btnSwitch.setTypeface(this.textFont);
        this.txt_api_key.setTypeface(this.textFont);
        this.edtApiKey.setTypeface(this.textFont);
        this.edtChanelId.setTypeface(this.textFont);
        this.edtFieldNumber.setTypeface(this.textFont);
        this.txt_name.setTypeface(this.textFont);
        this.txt_ip.setTypeface(this.textFont);
        this.txt_port.setTypeface(this.textFont);
        this.txt_on_code.setTypeface(this.textFont);
        this.txt_off_code.setTypeface(this.textFont);
        this.txt_c_id.setTypeface(this.textFont);
        this.txt_field_no.setTypeface(this.textFont);
        int i = this.toolId;
        if (i == 0) {
            String str = "";
            this.name.setText(str);
            this.f74IP.setText(str);
            this.OffCode.setText(str);
            this.onCode.setText(str);
            this.port.setText(str);
            this.edtFieldNumber.setText(str);
            this.edtChanelId.setText(str);
            this.edtApiKey.setText(str);
            createUnitList(false);
            createImageList(1, false);
        } else {
            this.toolObject = dataBase.getToolByid(i);
            this.name.setText(this.toolObject.name);
            this.f74IP.setText(this.toolObject.ip);
            this.OffCode.setText(this.toolObject.toolOffCode);
            this.onCode.setText(this.toolObject.toolOnCode);
            this.port.setText(this.toolObject.port);
            this.edtFieldNumber.setText(this.toolObject.thingSpeakFieldNumber);
            this.edtChanelId.setText(this.toolObject.thingSpeakChanelId);
            this.edtApiKey.setText(this.toolObject.thingSpeakApiKey);
            createUnitList(true);
            createImageList(1, true);
        }
        this.unitListView.setVisibility(View.VISIBLE);
        if (dataBase.getConnectionType() == Utility.LOCAL) {
            this.thingspeak_wrapper.setVisibility(View.VISIBLE);
            this.tuggle_wraper.setVisibility(View.VISIBLE);
            this.local_wrapper.setVisibility(View.VISIBLE);
        } else if (dataBase.getConnectionType() == Utility.THINGSPEAK) {
            this.thingspeak_wrapper.setVisibility(View.VISIBLE);
            this.local_wrapper.setVisibility(View.VISIBLE);
            this.tuggle_wraper.setVisibility(View.VISIBLE);
        } else {
            this.thingspeak_wrapper.setVisibility(View.VISIBLE);
            this.local_wrapper.setVisibility(View.VISIBLE);
            this.tuggle_wraper.setVisibility(View.VISIBLE);
        }
        ToolObject toolObject2 = this.toolObject;
        if (toolObject2 != null) {
            if (toolObject2.deviceType == Utility.SENSOR) {
                this.isSensor = true;
                this.btnSwitch.setChecked(true);
            } else if (this.toolObject.deviceType == Utility.DEVICE) {
                this.isSensor = false;
                this.btnSwitch.setChecked(false);
            } else {
                Toasty.error(this.context, (CharSequence) "trouble with devise type selection").show();
            }
        }
        controlDeviseSelection(this.isSensor, this.btnSwitch);
        this.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String unit = TooLDialog.this.stringAdapter.getUnit();
                TooLDialog tooLDialog = TooLDialog.this;
                tooLDialog.imageName = tooLDialog.toolListAdapter.getmImage();
                TooLDialog tooLDialog2 = TooLDialog.this;
                tooLDialog2.strName = tooLDialog2.name.getText().toString();
                TooLDialog tooLDialog3 = TooLDialog.this;
                tooLDialog3.strOffCode = tooLDialog3.OffCode.getText().toString();
                TooLDialog tooLDialog4 = TooLDialog.this;
                tooLDialog4.strOnCode = tooLDialog4.onCode.getText().toString();
                TooLDialog tooLDialog5 = TooLDialog.this;
                tooLDialog5.strIp = tooLDialog5.f74IP.getText().toString();
                TooLDialog tooLDialog6 = TooLDialog.this;
                tooLDialog6.strPort = tooLDialog6.port.getText().toString();
                TooLDialog tooLDialog7 = TooLDialog.this;
                tooLDialog7.channelId = tooLDialog7.edtChanelId.getText().toString();
                TooLDialog tooLDialog8 = TooLDialog.this;
                tooLDialog8.apiKey = tooLDialog8.edtApiKey.getText().toString();
                TooLDialog tooLDialog9 = TooLDialog.this;
                tooLDialog9.fieldNumber = tooLDialog9.edtFieldNumber.getText().toString();
                ToolObject toolObject = new ToolObject();
                toolObject.unitId = TooLDialog.this.f75id;
                toolObject.name = TooLDialog.this.strName;
                toolObject.toolImage = TooLDialog.this.imageName;
                toolObject.toolOnCode = TooLDialog.this.strOnCode;
                toolObject.toolOffCode = TooLDialog.this.strOffCode;
                toolObject.toolState = 0;
                toolObject.port = TooLDialog.this.strPort;
                toolObject.ip = TooLDialog.this.strIp;
                toolObject.thingSpeakApiKey = TooLDialog.this.apiKey;
                toolObject.thingSpeakChanelId = TooLDialog.this.channelId;
                toolObject.thingSpeakFieldNumber = TooLDialog.this.fieldNumber;
                StringBuilder sb = new StringBuilder();
                sb.append(TooLDialog.this.toolId);
                String str = "";
                sb.append(str);
                toolObject.toolID = sb.toString();
                toolObject.deviceType = TooLDialog.this.deviceType;
                toolObject.siUnit = unit;
                String str2 = "Please Insert All Data";
                if (dataBase.getConnectionType() == Utility.LOCAL) {
                    if (TooLDialog.this.strName.equals(str) || TooLDialog.this.strOnCode.equals(str) || TooLDialog.this.strOffCode.equals(str) || TooLDialog.this.strPort.equals(str) || TooLDialog.this.strIp.equals(str)) {
                        Toasty.error(TooLDialog.this.context, (CharSequence) str2, 0, true).show();
                    } else if (Patterns.IP_ADDRESS.matcher(TooLDialog.this.strIp).matches()) {
                        if (TooLDialog.this.toolId == 0) {
                            dataBase.insertToolData(toolObject);
                        } else {
                            dataBase.updateToolData(toolObject);
                        }
                        TooLDialog.this.notifyDataSetChange();
                        TooLDialog.this.dismiss();
                    } else {
                        Toasty.error(TooLDialog.this.context, (CharSequence) "IP address is invalid", 0, true).show();
                    }
                } else if (TooLDialog.this.deviceType == Utility.DEVICE) {
                    if (TooLDialog.this.strName.equals(str) || TooLDialog.this.channelId.equals(str) || TooLDialog.this.apiKey.equals(str) || TooLDialog.this.fieldNumber.equals(str)) {
                        Toasty.error(TooLDialog.this.context, (CharSequence) str2, 0, true).show();
                    } else {
                        if (TooLDialog.this.toolId == 0) {
                            dataBase.insertToolData(toolObject);
                        } else {
                            dataBase.updateToolData(toolObject);
                        }
                        TooLDialog.this.notifyDataSetChange();
                        TooLDialog.this.dismiss();
                    }
                } else if (TooLDialog.this.strName.equals(str) || TooLDialog.this.channelId.equals(str) || TooLDialog.this.apiKey.equals(str) || TooLDialog.this.fieldNumber.equals(str)) {
                    Toasty.error(TooLDialog.this.context, (CharSequence) str2, 0, true).show();
                } else {
                    if (TooLDialog.this.toolId == 0) {
                        dataBase.insertToolData(toolObject);
                    } else {
                        dataBase.updateToolData(toolObject);
                    }
                    TooLDialog.this.notifyDataSetChange();
                    TooLDialog.this.dismiss();
                }
                TooLDialog.this.loadImgempty();
            }
        });
        this.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TooLDialog.this.dismiss();
            }
        });
        this.btnSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                TooLDialog.this.controlDeviseSelection(z, compoundButton);
            }
        });
    }

    public void createImageList(int i, boolean z) {
        List images = MainActivity.dataBase.getImages(i);
        this.recyclerView = (RecyclerView) findViewById(R.id.edit_tool);
        if (z) {
            this.toolListAdapter = new ImageAdapter(this.context, images, Utility.getIndexOfStringList(images, this.toolObject.toolImage));
        } else {
            this.toolListAdapter = new ImageAdapter(this.context, images, 0);
        }
        this.gridLayoutManager = new GridLayoutManager(this.context, 1, RecyclerView.HORIZONTAL, false);
        this.recyclerView.setLayoutManager(this.gridLayoutManager);
        this.recyclerView.setAdapter(this.toolListAdapter);
    }

    public void createUnitList(boolean z) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("\t");
        arrayList.add(this.context.getString(R.string.celsius));
        arrayList.add(this.context.getString(R.string.fahrenheit));
        arrayList.add(this.context.getString(R.string.kelvin));
        arrayList.add(this.context.getString(R.string.roomy));
        arrayList.add(this.context.getString(R.string.humidity));
        arrayList.add(this.context.getString(R.string.millimeter));
        arrayList.add(this.context.getString(R.string.centimeter));
        arrayList.add(this.context.getString(R.string.kiloimeter));
        arrayList.add(this.context.getString(R.string.milliliter));
        arrayList.add(this.context.getString(R.string.liter));
        arrayList.add(this.context.getString(R.string.milligram));
        arrayList.add(this.context.getString(R.string.gram));
        arrayList.add(this.context.getString(R.string.kilogram));
        arrayList.add(this.context.getString(R.string.pascal));
        this.unitListView = (RecyclerView) findViewById(R.id.unit_list);
        if (z) {
            this.stringAdapter = new StringAdapter(this.context, arrayList, Utility.getIndexOfStringList(arrayList, this.toolObject.siUnit));
        } else {
            this.stringAdapter = new StringAdapter(this.context, arrayList, 0);
        }
        this.gridLayoutManager = new GridLayoutManager(this.context, 1, RecyclerView.HORIZONTAL, false);
        this.unitListView.setLayoutManager(this.gridLayoutManager);
        this.unitListView.setAdapter(this.stringAdapter);
    }

    /* access modifiers changed from: private */
    public void controlDeviseSelection(boolean z, CompoundButton compoundButton) {
        if (z) {
            compoundButton.setText("Sensor");
            this.txt_api_key.setText("Write API Key");
            this.name.setHint("Temperature");
            if (this.toolId == 0) {
                createImageList(2, false);
            } else {
                createImageList(2, true);
            }
            this.unitListView.setVisibility(View.VISIBLE);
            this.deviceType = Utility.SENSOR;
            return;
        }
        compoundButton.setText("Device");
        this.txt_api_key.setText("Read API Key");
        this.name.setHint("Television");
        if (this.toolId == 0) {
            createImageList(1, false);
        } else {
            createImageList(1, true);
        }
        this.unitListView.setVisibility(View.VISIBLE);
        this.deviceType = Utility.DEVICE;
    }
}
