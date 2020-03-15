package com.yashka.dedofiom.Activity;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;

import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.facebook.stetho.Stetho;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.material.navigation.NavigationView;
import com.yashka.dedofiom.BuildConfig;
import com.yashka.dedofiom.Fragment.About;
import com.yashka.dedofiom.Fragment.Help;
import com.yashka.dedofiom.Fragment.Home;
import com.yashka.dedofiom.Fragment.IOnFocusListenable;
import com.yashka.dedofiom.Fragment.IRremote;
import com.yashka.dedofiom.Fragment.Settings;
import com.yashka.dedofiom.Job.PurchesControll;
import com.yashka.dedofiom.Model.AdsControll;

import com.yashka.dedofiom.R;
import com.yashka.dedofiom.Utill.DataBase;
import com.yashka.dedofiom.Utill.IOnBackPressed;
import com.yashka.dedofiom.Utill.SharedPre;
import com.yashka.dedofiom.Utill.TCPClient;
import com.yashka.dedofiom.Utill.Utility;


import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import es.dmoral.toasty.Toasty;
//cretted by act

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    /* access modifiers changed from: private */
    public static Context context;
    public static DataBase dataBase;
    /* access modifiers changed from: private */
    public static String ipAddressOfServerDevice;
    private static Activity mActivity;
    private static Context mContext;
    /* access modifiers changed from: private */
    public static TCPClient mTcpClient;
    private static int portNumber;
    /*test*/
//    AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener = new AcknowledgePurchaseResponseListener() {
//        public void onAcknowledgePurchaseResponse(BillingResult billingResult) {
//            if (billingResult.getResponseCode() == 0) {
//                System.out.println("aaalallalala Purchase acknowledged");
//                MainActivity.dataBase.BolockAds(Utility.HIDE_ADS_PERMENETLY);
//                MainActivity.dataBase.updateIsRefund(Utility.NOT_REFUND);
//                MainActivity.this.mAdView.setVisibility(View.VISIBLE);
//                MainActivity.this.showRemoveAds(false);
//                MainActivity.this.container_body.setVisibility(View.VISIBLE);
//                MainActivity.this.adsRemoveView.setVisibility(View.VISIBLE);
//            }
//            PrintStream printStream = System.out;
//            StringBuilder sb = new StringBuilder();
//            sb.append("aaalallalala Purchase acknowledged ");
//            sb.append(billingResult.getResponseCode());
//            sb.append(" ");
//            printStream.println(sb.toString());
//        }
//    };
    AdRequest adRequest;
    private AdView addMainBottom;
    /* access modifiers changed from: private */
    public View adsRemoveView;
    /* access modifiers changed from: private */
//    public BillingClient billingClient;
    /* access modifiers changed from: private */
    public Button btn_purchases;
    /* access modifiers changed from: private */
    public Button btn_watch_video;
    /* access modifiers changed from: private */
//    public ConsumeParams consumeParams;
//    ConsumeResponseListener consumeResponseListener = new ConsumeResponseListener() {
//        public void onConsumeResponse(BillingResult billingResult, String str) {
//            String str2 = "aaalallalala Purchase acknowledged ";
//            if (billingResult.getResponseCode() == 0) {
//                MainActivity.dataBase.BolockAds(Utility.HIDE_ADS_PERMENETLY);
//                MainActivity.dataBase.updateIsRefund(Utility.NOT_REFUND);
//                MainActivity.this.mAdView.setVisibility(View.VISIBLE);
//                MainActivity.this.showRemoveAds(false);
//                MainActivity.this.container_body.setVisibility(View.VISIBLE);
//                MainActivity.this.adsRemoveView.setVisibility(View.VISIBLE);
//                System.out.println(str2);
//            }
//            PrintStream printStream = System.out;
//            StringBuilder sb = new StringBuilder();
//            sb.append(str2);
//            sb.append(billingResult.getResponseCode());
//            sb.append(" ");
//            sb.append(str);
//            printStream.println(sb.toString());
//        }
//    };
    /* access modifiers changed from: private */
    public FrameLayout container_body;
    boolean doubleBackToExitPressedOnce = false;
    public DrawerLayout drawer;
    private AdView exitAddView;
    Fragment fragment = null;
    Handler handler;
    /* access modifiers changed from: private */
    public final Handler handlerJob = new Handler();
    /* access modifiers changed from: private */
    public Button image;
    /* access modifiers changed from: private */
    public boolean isPerchase = true;
    /* access modifiers changed from: private */
    public AdView mAdView;
    private Button moreInfor;
    private Menu nav_Menu;
    private NavigationView navigationView;
    /* access modifiers changed from: private */
    public ProgressDialog pDialog;
    private Menu removeAdseMenu;
    /* access modifiers changed from: private */
    public RewardedAd rewardedAd;
    Runnable runnableCode = new Runnable() {
        public void run() {
            Calendar instance = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
            String format = simpleDateFormat.format(instance.getTime());
            String onTime = MainActivity.dataBase.getOnTime(1);
            String offTime = MainActivity.dataBase.getOffTime(1);
            try {
                Date parse = simpleDateFormat.parse(onTime);
                Date parse2 = simpleDateFormat.parse(offTime);
                Date parse3 = simpleDateFormat.parse(format);
                boolean before = parse.before(parse3);
                boolean before2 = parse2.before(parse3);
                if (!parse.before(parse3) || !parse3.before(parse2)) {
                    System.out.println("lclc off");
                } else {
                    System.out.println("lclc on");
                }
                if (before && !before2) {
                    System.out.println("fmfm on");
                } else if (before2 && !before) {
                    System.out.println("fmfm off");
                }
                PrintStream printStream = System.out;
                StringBuilder sb = new StringBuilder();
                sb.append("psps on ");
                sb.append(before);
                sb.append(" off ");
                sb.append(before2);
                printStream.println(sb.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            PrintStream printStream2 = System.out;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("xlxl ");
            sb2.append(format);
            String str = " ";
            sb2.append(str);
            sb2.append(onTime);
            sb2.append(str);
            sb2.append(offTime);
            printStream2.println(sb2.toString());
            MainActivity.this.handler.postDelayed(this, 1000);
        }
    };
    private Typeface textFont;
    private Timer timer;
    private TimerTask timerTask;
    private TextView txtVersion;
    /* access modifiers changed from: private */
    public TextView txt_instruction;

    public static class connectTask extends AsyncTask<String, String, TCPClient> {
        /* access modifiers changed from: protected */
        public TCPClient doInBackground(String... strArr) {
            MainActivity.mTcpClient = new TCPClient(new TCPClient.OnMessageReceived() {
                public void messageReceived(String str) {
                    try {
                        connectTask.this.publishProgress(new String[]{str});
                        if (str != null) {
                            PrintStream printStream = System.out;
                            StringBuilder sb = new StringBuilder();
                            sb.append("Return Message from Socket::::: >>>>> ");
                            sb.append(str);
                            printStream.println(sb.toString());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, MainActivity.ipAddressOfServerDevice, 4200);
            MainActivity.mTcpClient.run();
            if (MainActivity.mTcpClient != null) {
                MainActivity.mTcpClient.sendMessage("Initial Message when connected with Socket Server");
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public void onProgressUpdate(String... strArr) {
            super.onProgressUpdate(strArr);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        mActivity = this;
        context = this;
        mContext = getApplicationContext();
        this.textFont = Typeface.createFromAsset(context.getAssets(), "fonts/BhashitaComplexBoldEnglish.ttf");
        dataBase = new DataBase(mContext);
        initialization();
//        setupBillingClient();
       // buttonClick();
        Stetho.initializeWithDefaults(MainActivity.this);


        this.navigationView.setNavigationItemSelectedListener(this);
        this.txtVersion = (TextView) this.navigationView.getHeaderView(0).findViewById(R.id.version);
        TextView textView = this.txtVersion;
        StringBuilder sb = new StringBuilder();
        sb.append("Version : ");
        sb.append(BuildConfig.VERSION_NAME);
        textView.setText(sb.toString());
        this.txtVersion.setTypeface(this.textFont);
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String str = "firstTimeMain";
        if (!defaultSharedPreferences.getBoolean(str, false)) {
            dataBase.insertConnectFirst(1, 0);
            dataBase.insertConnectionTypeData(Utility.LOCAL, 1);
            dataBase.insertConnectionTypeData(Utility.THINGSPEAK, 0);
            Editor edit = defaultSharedPreferences.edit();
            edit.putBoolean(str, true);
            edit.commit();
        }
        if (!SharedPre.getUpgradPremiumDontShow(context) && dataBase.isShowAdd() == Utility.SHOW_ADS) {
            openDialog();
        }
        this.handler = new Handler();
        this.fragment = new Home();
        fragmentTransaction();
        if (dataBase.isShowAdd() == Utility.SHOW_ADS) {
            this.mAdView.setVisibility(View.VISIBLE);
            this.adRequest = new AdRequest.Builder().build();
            this.mAdView.loadAd(this.adRequest);
            showRemoveAds(true);
        } else {
            this.mAdView.setVisibility(View.GONE);
            showRemoveAds(false);
        }
        stoptimertask();
        startTimer();
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        Fragment fragment2 = this.fragment;
        if (fragment2 instanceof IOnFocusListenable) {
            ((IOnFocusListenable) fragment2).onWindowFocusChanged(z);
            System.out.println("ddddqqqq if");
            return;
        }
        System.out.println("ddddqqqq else");
    }

    public void onBackPressed() {
        if (this.adsRemoveView.getVisibility() == View.VISIBLE) {
            this.adsRemoveView.setVisibility(View.VISIBLE);
            this.container_body.setVisibility(View.VISIBLE);
        } else {
            getCurentFragment();
            Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.container_body);
            if (!(findFragmentById instanceof IOnBackPressed) || !((IOnBackPressed) findFragmentById).onBackPressed()) {
                System.out.println("sdsdsd main if");
            } else if (dataBase.isShowAdd() == Utility.SHOW_ADS) {
                final Dialog dialog = new Dialog(mActivity);
                dialog.setContentView(R.layout.exit_dialog);
                dialog.show();
                Button button = (Button) dialog.findViewById(R.id.btn_no);
                Button button2 = (Button) dialog.findViewById(R.id.btn_yes);
                this.exitAddView = (AdView) dialog.findViewById(R.id.adView);
                this.exitAddView.setVisibility(View.VISIBLE);
                // Create an ad request.
                AdRequest.Builder adRequestBuilder = new AdRequest.Builder();

                this.exitAddView.loadAd(adRequestBuilder.build());
                if (VERSION.SDK_INT >= 19) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                button.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                button2.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        dialog.dismiss();
                        MainActivity.this.finish();
                    }
                });
                dialog.show();
            } else if (this.doubleBackToExitPressedOnce) {
                super.onBackPressed();
            } else {
                this.doubleBackToExitPressedOnce = true;
                Toasty.info((Context) this, (CharSequence) "Click again to exit", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        MainActivity.this.doubleBackToExitPressedOnce = false;
                    }
                }, 2000);
            }
        }
    }

    public void getCurentFragment() {
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.container_body);
        if (!(findFragmentById instanceof Home)) {
            if (findFragmentById instanceof Help) {
                this.fragment = new Home();
                fragmentTransaction();
            } else if (findFragmentById instanceof About) {
                this.fragment = new Home();
                fragmentTransaction();
            } else if (findFragmentById instanceof Settings) {
                this.fragment = new Home();
                fragmentTransaction();
            } else if (findFragmentById instanceof IRremote) {
                this.fragment = new Home();
                fragmentTransaction();
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return super.onOptionsItemSelected(menuItem);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.home) {
            this.fragment = new Home();
            this.container_body.setVisibility(View.VISIBLE);
            this.adsRemoveView.setVisibility(View.VISIBLE);
        } else if (itemId == R.id.settings) {
            this.fragment = new Settings();
            this.container_body.setVisibility(View.VISIBLE);
            this.adsRemoveView.setVisibility(View.VISIBLE);
        } else if (itemId == R.id.help) {
            this.fragment = new Help();
            this.container_body.setVisibility(View.VISIBLE);
            this.adsRemoveView.setVisibility(View.VISIBLE);
        } else if (itemId == R.id.about) {
            this.fragment = new About();
            this.container_body.setVisibility(View.VISIBLE);
            this.adsRemoveView.setVisibility(View.VISIBLE);
        } else if (itemId == R.id.remove_ads) {
            if (Utility.isNetworkConnected(context)) {
//                openAddRemoveView();
                this.fragment = null;
            } else {
                Toast.makeText(context, "Your device is not connected to internet", Toast.LENGTH_SHORT).show();
            }
        }
        if (this.fragment != null) {
            fragmentTransaction();
        }
        this.drawer.closeDrawer((int) GravityCompat.START);
        return true;
    }

    public void fragmentTransaction() {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.container_body, this.fragment);
        beginTransaction.commit();
    }

    public void stoptimertask() {
        Timer timer2 = this.timer;
        if (timer2 != null) {
            timer2.cancel();
            this.timer = null;
        }
    }

    public void startTimer() {
        this.timer = new Timer();
        initializeTimerTask();
        this.timer.schedule(this.timerTask, 20000, 60000);
    }

    public void initializeTimerTask() {
        this.timerTask = new TimerTask() {
            public void run() {
                MainActivity.this.handlerJob.post(new Runnable() {
                    public void run() {
                        if (MainActivity.dataBase.showAddAgain()) {
                            MainActivity.this.mAdView.setVisibility(View.VISIBLE);
                            MainActivity.this.showRemoveAds(true);
                        } else {
                            MainActivity.this.mAdView.setVisibility(View.VISIBLE);
                            MainActivity.this.showRemoveAds(false);
                        }
                       // new PurchesControll(MainActivity.context).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]);
                    }
                });
            }
        };
    }

    public AdView getmAdView() {
        return this.mAdView;
    }

    public DrawerLayout getDrawer() {
        return this.drawer;
    }

    public Menu getNav_Menu() {
        return this.nav_Menu;
    }

    private void openDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle((CharSequence) "Remove Ads");
        builder.setMultiChoiceItems((CharSequence[]) new String[]{"Don't show again"}, new boolean[]{false}, (OnMultiChoiceClickListener) new OnMultiChoiceClickListener() {
            public void onClick(DialogInterface dialogInterface, int i, boolean z) {
                SharedPre.setUpgradPremiumDontShow(MainActivity.context, z);
            }
        });
        builder.setPositiveButton((CharSequence) "OK", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                if (SharedPre.getUpgradPremiumDontShow(MainActivity.context)) {
                    return;
                }
                if (Utility.isNetworkConnected(MainActivity.context)) {
//                    MainActivity.this.openAddRemoveView();
                } else {
                    Toasty.error(MainActivity.context, (CharSequence) "Your device is not connected to internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton((CharSequence) "Cancel", (DialogInterface.OnClickListener) null);
        builder.create().show();
    }

    /* access modifiers changed from: private */
    public void showRemoveAds(boolean z) {
        this.nav_Menu = this.navigationView.getMenu();
        this.nav_Menu.findItem(R.id.remove_ads).setVisible(z);
    }

//    public void onPurchasesUpdated(BillingResult billingResult, List<Purchase> list) {
//        if (list != null && list.size() > 0 && ((Purchase) list.get(0)).getPurchaseState() == 1 && !((Purchase) list.get(0)).isAcknowledged()) {
//            this.consumeParams = ConsumeParams.newBuilder().setPurchaseToken(((Purchase) list.get(0)).getPurchaseToken()).setDeveloperPayload(((Purchase) list.get(0)).getDeveloperPayload()).build();
//            this.billingClient.consumeAsync(this.consumeParams, this.consumeResponseListener);
//            dataBase.updatePuchaseToken(((Purchase) list.get(0)).getPurchaseToken(), true);
//        }
//    }

//    private void setupBillingClient() {
//        this.billingClient = BillingClient.newBuilder(context).setListener(this).enablePendingPurchases().build();
//        this.billingClient.startConnection(new BillingClientStateListener() {
//            public void onBillingServiceDisconnected() {
//            }
//
//            public void onBillingSetupFinished(BillingResult billingResult) {
//                if (billingResult.getResponseCode() == 0) {
//                    MainActivity.this.billingClient.queryPurchaseHistoryAsync(SkuType.INAPP, new PurchaseHistoryResponseListener() {
//                        public void onPurchaseHistoryResponse(BillingResult billingResult, List<PurchaseHistoryRecord> list) {
//                            if (list != null && list.size() > 0) {
//                                try {
//                                    JSONObject jSONObject = new JSONObject(((PurchaseHistoryRecord) list.get(0)).getOriginalJson());
//                                    String string = jSONObject.getString("purchaseToken");
//                                    boolean z = jSONObject.getBoolean("acknowledged");
//                                    String string2 = jSONObject.getString(BillingHelper.EXTRA_PARAMS_DEVELOPER_PAYLOAD);
//                                    if (string2 == null) {
//                                        string2 = "";
//                                    }
//                                    MainActivity.dataBase.updatePuchaseToken(string, z);
//                                    if (!z) {
//                                        MainActivity.this.consumeParams = ConsumeParams.newBuilder().setPurchaseToken(string).setDeveloperPayload(string2).build();
//                                        MainActivity.this.billingClient.consumeAsync(MainActivity.this.consumeParams, MainActivity.this.consumeResponseListener);
//                                    } else {
//                                        MainActivity.dataBase.BolockAds(Utility.HIDE_ADS_PERMENETLY);
//                                        MainActivity.dataBase.updateIsRefund(Utility.NOT_REFUND);
//                                        MainActivity.this.mAdView.setVisibility(View.VISIBLE);
//                                        MainActivity.this.showRemoveAds(false);
//                                        MainActivity.this.container_body.setVisibility(View.VISIBLE);
//                                        MainActivity.this.adsRemoveView.setVisibility(View.VISIBLE);
//                                    }
//                                } catch (Exception e) {
//                                    PrintStream printStream = System.out;
//                                    StringBuilder sb = new StringBuilder();
//                                    sb.append("pfpspps ");
//                                    sb.append(e);
//                                    printStream.println(sb.toString());
//                                }
//                                PrintStream printStream2 = System.out;
//                                StringBuilder sb2 = new StringBuilder();
//                                sb2.append("asasads ");
//                                sb2.append(((PurchaseHistoryRecord) list.get(0)).getOriginalJson());
//                                printStream2.println(sb2.toString());
//                            }
//                        }
//                    });
//                }
//            }
//        });
//    }

    /* access modifiers changed from: private */
//    public void buyApp() {
//        if (this.billingClient.isReady()) {
//            this.billingClient.querySkuDetailsAsync(SkuDetailsParams.newBuilder().setSkusList(Arrays.asList(new String[]{"dedofi_nodemcu_ctrl_wam"})).setType(SkuType.INAPP).build(), new SkuDetailsResponseListener() {
//                public void onSkuDetailsResponse(BillingResult billingResult, List<SkuDetails> list) {
//                    if (billingResult.getResponseCode() == 0 && list != null && list.size() > 0) {
//                        MainActivity.this.billingClient.launchBillingFlow((Activity) MainActivity.context, BillingFlowParams.newBuilder().setSkuDetails((SkuDetails) list.get(0)).build());
//                    }
//                }
//            });
//        }
//    }

    private void buttonClick() {
        this.btn_purchases.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
//                MainActivity.this.isPerchase = true;
//                MainActivity.this.image.setBackground(ContextCompat.getDrawable(MainActivity.context, R.drawable.premium_badge_larg));
//                MainActivity.this.btn_purchases.setBackground(ContextCompat.getDrawable(MainActivity.context, R.drawable.perple_gradient));
//                MainActivity.this.btn_purchases.setTextColor(-1);
//                MainActivity.this.btn_watch_video.setBackgroundColor(-1);
//                MainActivity.this.btn_watch_video.setTextColor(Color.parseColor("#86469c"));
//                MainActivity.this.txt_instruction.setText(MainActivity.context.getString(R.string.preiume_message));
            }
        });
        this.btn_watch_video.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
//                MainActivity.this.isPerchase = false;
//                MainActivity.this.image.setBackground(ContextCompat.getDrawable(MainActivity.context, R.drawable.video_play));
//                MainActivity.this.txt_instruction.setText(MainActivity.context.getString(R.string.tempory_message));
//                MainActivity.this.btn_watch_video.setBackground(ContextCompat.getDrawable(MainActivity.context, R.drawable.perple_gradient));
//                MainActivity.this.btn_watch_video.setTextColor(-1);
//                MainActivity.this.btn_purchases.setBackgroundColor(-1);
//                MainActivity.this.btn_purchases.setTextColor(Color.parseColor("#86469c"));
            }
        });
//        this.image.setOnClickListener(new OnClickListener() {
//            public void onClick(View view) {
//                if (MainActivity.this.isPerchase) {
////                    MainActivity.this.buyApp();
//                } else if (MainActivity.this.rewardedAd.isLoaded()) {
//                    MainActivity.this.rewardedAd.show((Activity) MainActivity.context, new RewardedAdCallback() {
//                        public void onRewardedAdClosed() {
//                        }
//
//                        public void onRewardedAdOpened() {
//                        }
//
//                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
//                            Toasty.success(MainActivity.context, (CharSequence) "Ads will be blocked for 6 hours", Toast.LENGTH_SHORT).show();
//                            MainActivity.dataBase.BolockAds(Utility.HIDE_ADS_TEMPORY);
//                            MainActivity.this.mAdView.setVisibility(View.VISIBLE);
//                            MainActivity.this.showRemoveAds(false);
//                            MainActivity.this.container_body.setVisibility(View.VISIBLE);
//                            MainActivity.this.adsRemoveView.setVisibility(View.VISIBLE);
//                        }
//
//                        public void onRewardedAdFailedToShow(int i) {
//                            Toast.makeText(MainActivity.context, "Ad failed to display.", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                } else {
//                    String str = "The rewarded ad wasn't loaded yet.";
//                    Toast.makeText(MainActivity.context, str, Toast.LENGTH_SHORT).show();
//                    Log.d("TAG", str);
//                }
//            }
//        });
    }

    /* access modifiers changed from: private */
//    public void openAddRemoveView() {
//        this.adsRemoveView.setVisibility(View.VISIBLE);
//        this.container_body.setVisibility(View.VISIBLE);
//        if (!this.rewardedAd.isLoaded()) {
//            View inflate = LayoutInflater.from(context).inflate(R.layout.progress, null);
//            this.pDialog = new ProgressDialog(context);
//            if (VERSION.SDK_INT >= 19) {
//                this.pDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
//            }
//            this.pDialog.setIndeterminate(false);
//            this.pDialog.setCancelable(false);
//            this.pDialog.show();
//            this.pDialog.setContentView(inflate);
////            loadRewardAdd();
//        }
//    }

//    private void loadRewardAdd() {
//        Context context2 = context;
//        this.rewardedAd = new RewardedAd(context2, context2.getString(R.string.REAWARD_AD_ID));
//        this.rewardedAd.loadAd(new AdRequest.Builder().build(), (RewardedAdLoadCallback) new RewardedAdLoadCallback() {
//            public void onRewardedAdLoaded() {
//                MainActivity.this.pDialog.dismiss();
//            }
//
//            public void onRewardedAdFailedToLoad(int i) {
//                MainActivity.this.pDialog.dismiss();
//            }
//        });
//    }

    private void initialization() {
        this.drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.mAdView = (AdView) findViewById(R.id.adView);
        this.navigationView = (NavigationView) findViewById(R.id.nav_view);
        this.adsRemoveView = findViewById(R.id.remove_ads);
        this.btn_watch_video = (Button) findViewById(R.id.btn_watch_video);
        this.btn_purchases = (Button) findViewById(R.id.btn_purchases);
        this.image = (Button) findViewById(R.id.image);
        this.txt_instruction = (TextView) findViewById(R.id.txt_instruction);
        this.container_body = (FrameLayout) findViewById(R.id.container_body);
        this.btn_purchases.setTypeface(this.textFont);
        this.btn_watch_video.setTypeface(this.textFont);
        this.txt_instruction.setTypeface(this.textFont);
        this.moreInfor = (Button) findViewById(R.id.more_info);
        if (VERSION.SDK_INT >= 26) {
            this.txt_instruction.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        }
//        AdsControll adsControlData = dataBase.getAdsControlData();
//        if (adsControlData != null) {
//            String str = adsControlData.country;
//            if (!str.equals("India") && !str.equals("Sri Lanka")) {
//                str.equals("Hungary");
//            }
//            this.btn_watch_video.setVisibility(View.VISIBLE);
//        }
//        loadRewardAdd();
    }
}
