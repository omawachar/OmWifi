package com.yashka.dedofiom.Fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.yashka.dedofiom.Activity.MainActivity;
import com.yashka.dedofiom.R;
import com.yashka.dedofiom.Utill.DataBase;
import com.yashka.dedofiom.Utill.Utility;

import es.dmoral.toasty.Toasty;


public class Settings extends Fragment {
    private Context context;
    /* access modifiers changed from: private */
    public DataBase dataBase;
    /* access modifiers changed from: private */
    public DrawerLayout drawer;
    private Button moreInfor;
    private Switch switchLocal;
    private Switch switchThingsPeak;
    private Typeface textFont;
    private TextView txt_instruction;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.settings, viewGroup, false);
    }

    public void onAttach(Context context2) {
        this.context = context2;
        super.onAttach(context2);
    }

    public void onViewCreated(View view, Bundle bundle) {
        this.dataBase = new DataBase(this.context);
        loadResource(view);
        this.textFont = Typeface.createFromAsset(this.context.getAssets(), "fonts/BhashitaComplexBoldEnglish.ttf");
        setButtonState();
        this.switchThingsPeak.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    Settings.this.dataBase.updateConnectionType(Utility.THINGSPEAK, 1);
                    Settings.this.setButtonState();
                    return;
                }
                Settings.this.dataBase.updateConnectionType(Utility.LOCAL, 1);
                Settings.this.setButtonState();
            }
        });
        this.switchLocal.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    Settings.this.dataBase.updateConnectionType(Utility.LOCAL, 1);
                    Settings.this.setButtonState();
                    return;
                }
                Settings.this.dataBase.updateConnectionType(Utility.THINGSPEAK, 1);
                Settings.this.setButtonState();
            }
        });
        this.moreInfor.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Settings.this.drawer.openDrawer((int) GravityCompat.START);
            }
        });
    }

    private void loadResource(View view) {
        this.switchThingsPeak = (Switch) view.findViewById(R.id.things_peak);
        this.switchLocal = (Switch) view.findViewById(R.id.local);
        this.txt_instruction = (TextView) view.findViewById(R.id.txt_instruction);
        this.drawer = ((MainActivity) this.context).getDrawer();
        this.moreInfor = (Button) view.findViewById(R.id.more_info);
        this.txt_instruction.setTypeface(this.textFont);
        this.switchLocal.setTypeface(this.textFont);
        this.switchThingsPeak.setTypeface(this.textFont);
    }

    /* access modifiers changed from: private */
    public void setButtonState() {
        int connectionType = this.dataBase.getConnectionType();
        if (connectionType == Utility.LOCAL) {
            this.switchThingsPeak.setChecked(false);
            this.switchLocal.setChecked(true);
            this.txt_instruction.setText(this.context.getString(R.string.local_network_instruction));
        } else if (connectionType == Utility.THINGSPEAK) {
            this.switchLocal.setChecked(false);
            this.switchThingsPeak.setChecked(true);
            this.txt_instruction.setText(this.context.getString(R.string.thingspeak_instructions));
        } else {
            Toasty.error(this.context, (CharSequence) "Trouble with the Connection Settings").show();
        }
    }
}
