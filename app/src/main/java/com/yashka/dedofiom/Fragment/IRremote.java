package com.yashka.dedofiom.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.yashka.dedofiom.R;

public class IRremote extends Fragment {

    /* renamed from: IR */
    int f76IR = 0;
    int WIFI = 1;
    ImageButton arrowDown;
    ImageButton arrowLeft;
    ImageButton arrowRight;
    ImageButton arrowUp;
    ImageButton chDown;
    ImageButton chUp;
    Button eight;

    /* renamed from: f1 */
    ImageButton f77f1;
    ImageButton f10;
    ImageButton f11;
    ImageButton f12;

    /* renamed from: f2 */
    ImageButton f78f2;

    /* renamed from: f3 */
    ImageButton f79f3;

    /* renamed from: f4 */
    ImageButton f80f4;

    /* renamed from: f5 */
    ImageButton f81f5;

    /* renamed from: f6 */
    ImageButton f82f6;

    /* renamed from: f7 */
    ImageButton f83f7;

    /* renamed from: f8 */
    ImageButton f84f8;

    /* renamed from: f9 */
    ImageButton f85f9;
    Button five;
    Button four;
    ImageButton mute;
    Button nine;

    /* renamed from: ok */
    ImageButton f86ok;
    Button one;
    ImageButton power;
    Button seven;
    Button six;
    Button three;
    Button two;
    ImageButton volDown;
    ImageButton volUp;
    Button zero;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.ir_remote, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        initialize(view);
        buttonClickIR();
    }

    public void initialize(View view) {
        this.mute = (ImageButton) view.findViewById(R.id.btn_mute);
        this.power = (ImageButton) view.findViewById(R.id.btn_power);
        this.one = (Button) view.findViewById(R.id.btn_1);
        this.two = (Button) view.findViewById(R.id.btn_2);
        this.three = (Button) view.findViewById(R.id.btn_3);
        this.four = (Button) view.findViewById(R.id.btn_4);
        this.five = (Button) view.findViewById(R.id.btn_5);
        this.six = (Button) view.findViewById(R.id.btn_6);
        this.seven = (Button) view.findViewById(R.id.btn_7);
        this.eight = (Button) view.findViewById(R.id.btn_8);
        this.nine = (Button) view.findViewById(R.id.btn_9);
        this.zero = (Button) view.findViewById(R.id.btn_0);
        this.f77f1 = (ImageButton) view.findViewById(R.id.btn_f1);
        this.f78f2 = (ImageButton) view.findViewById(R.id.btn_f2);
        this.f79f3 = (ImageButton) view.findViewById(R.id.btn_f3);
        this.f80f4 = (ImageButton) view.findViewById(R.id.btn_f4);
        this.f81f5 = (ImageButton) view.findViewById(R.id.btn_f5);
        this.f82f6 = (ImageButton) view.findViewById(R.id.btn_f6);
        this.f83f7 = (ImageButton) view.findViewById(R.id.btn_f7);
        this.f84f8 = (ImageButton) view.findViewById(R.id.btn_f8);
        this.f85f9 = (ImageButton) view.findViewById(R.id.btn_f9);
        this.f10 = (ImageButton) view.findViewById(R.id.btn_f10);
        this.f11 = (ImageButton) view.findViewById(R.id.btn_f11);
        this.f12 = (ImageButton) view.findViewById(R.id.btn_f12);
        this.arrowUp = (ImageButton) view.findViewById(R.id.btn_arrow_up);
        this.arrowDown = (ImageButton) view.findViewById(R.id.btn_arrow_down);
        this.arrowLeft = (ImageButton) view.findViewById(R.id.btn_arrow_left);
        this.arrowRight = (ImageButton) view.findViewById(R.id.btn_arrow_right);
        this.f86ok = (ImageButton) view.findViewById(R.id.btn_ok);
        this.volUp = (ImageButton) view.findViewById(R.id.btn_vol_up);
        this.volDown = (ImageButton) view.findViewById(R.id.btn_vol_down);
        this.chDown = (ImageButton) view.findViewById(R.id.btn_ch_down);
        this.chUp = (ImageButton) view.findViewById(R.id.btn_ch_up);
    }

    public void buttonClickIR() {
        this.mute.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.power.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.one.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.two.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.three.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.four.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.five.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.six.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.seven.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.eight.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.nine.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.zero.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.f77f1.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.f78f2.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.f79f3.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.f80f4.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.f81f5.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.f82f6.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.f83f7.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.f84f8.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.f85f9.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.f10.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.arrowDown.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.arrowUp.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.arrowLeft.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.arrowRight.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.f86ok.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.volDown.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.volUp.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.chDown.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.chUp.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
    }
}
