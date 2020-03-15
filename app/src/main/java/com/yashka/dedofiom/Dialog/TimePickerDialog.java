package com.yashka.dedofiom.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.yashka.dedofiom.R;
import com.yashka.dedofiom.Utill.DataBase;

import java.util.Calendar;

public abstract class TimePickerDialog extends Dialog {
    private Calendar calendar;
    private Button cancel;
    public Context context;
    public DataBase dataBase;
    private String format = "";

    /* renamed from: id */
    public int f72id;

    /* renamed from: ok */
    private Button f73ok;
    public int on_off;
    private TextView time;
    /* access modifiers changed from: private */
    public TimePicker timePicker;

    public abstract void updateTime();

    public TimePickerDialog(Context context2, int i, int i2) {
        super(context2);
        this.context = context2;
        this.f72id = i;
        this.on_off = i2;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.time_picker);
        this.timePicker = (TimePicker) findViewById(R.id.time_picker);
        this.calendar = Calendar.getInstance();
        this.cancel = (Button) findViewById(R.id.cancel);
        this.f73ok = (Button) findViewById(R.id.ok);
        this.calendar.get(Calendar.HOUR_OF_DAY);
        this.calendar.get(Calendar.MINUTE);
        this.dataBase = new DataBase(this.context);
        this.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.this.dismiss();
            }
        });
        this.f73ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = TimePickerDialog.this;
                timePickerDialog.setTime(timePickerDialog.timePicker);
                TimePickerDialog.this.dismiss();
                TimePickerDialog.this.updateTime();
            }
        });
    }

    public void setTime(View view) {
        int intValue = this.timePicker.getCurrentHour().intValue();
        int intValue2 = this.timePicker.getCurrentMinute().intValue();
        if (this.on_off == 1) {
            this.dataBase.updateOntime(this.f72id, createTime(intValue, intValue2));
        } else {
            this.dataBase.updateOfftime(this.f72id, createTime(intValue, intValue2));
        }
    }

    public String createTime(int i, int i2) {
        String str;
        String str2 = "AM";
        if (i == 0) {
            i += 12;
            this.format = str2;
        } else {
            String str3 = "PM";
            if (i == 12) {
                this.format = str3;
            } else if (i > 12) {
                i -= 12;
                this.format = str3;
            } else {
                this.format = str2;
            }
        }
        if (i2 < 10) {
            StringBuilder sb = new StringBuilder();
            sb.append("0");
            sb.append(i2);
            str = sb.toString();
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(i2);
            sb2.append("");
            str = sb2.toString();
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append(i);
        sb3.append(":");
        sb3.append(str);
        sb3.append(" ");
        sb3.append(this.format);
        return sb3.toString();
    }
}
