package com.yashka.dedofiom.Model;


import com.yashka.dedofiom.Utill.Utility;

public class AdsControll {
    public String acknowledged;
    public int adState;
    public String appVersion;
    public String country;
    public String device_type;
    public String installDate;
    public int isRefund = Utility.NOT_REFUND;
    public int keyCount;
    public String purchaseToken;

    public AdsControll() {
        String str = "";
        this.installDate = str;
        this.purchaseToken = str;
        this.country = str;
        this.device_type = str;
        this.appVersion = str;
        this.adState = Utility.SHOW_ADS;
        this.keyCount = 0;
        this.acknowledged = "false";
    }
}
