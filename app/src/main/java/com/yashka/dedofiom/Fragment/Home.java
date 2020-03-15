package com.yashka.dedofiom.Fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.yashka.dedofiom.Activity.MainActivity;

import com.yashka.dedofiom.Adapter.BlockListAdapter;
import com.yashka.dedofiom.Adapter.ToolListAdapter;
import com.yashka.dedofiom.Dialog.BlockDialog;
import com.yashka.dedofiom.Dialog.TooLDialog;
import com.yashka.dedofiom.Job.ThingsPeak;
import com.yashka.dedofiom.Model.BlockObject;
import com.yashka.dedofiom.Model.ToolObject;
import com.yashka.dedofiom.R;
import com.yashka.dedofiom.Utill.DataBase;
import com.yashka.dedofiom.Utill.IOnBackPressed;
import com.yashka.dedofiom.Utill.TCPClient;
import com.yashka.dedofiom.Utill.TCPSocket;
import com.yashka.dedofiom.Utill.Utility;

import es.dmoral.toasty.Toasty;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Home extends Fragment implements IOnBackPressed {
    public static final int blockLoaded = 1;
//    private static List<BlockObject> blockObjects = null;
    private static List<BlockObject> blockObjects=null;
    /* access modifiers changed from: private */
    private static MainActivity.connectTask conctTask = null;
    /* access modifiers changed from: private */
    private static DataBase dataBase = null;
    private static String ipAddressOfServerDevice = null;
    /* access modifiers changed from: private */
    private static int isLoaded = 0;
    /* access modifiers changed from: private */
    private static TCPClient mTcpClient = null;
    public static int orientation = 0;
    private static int portNumber = 0;
    public static final int toolLoaded = 2;
    /* access modifiers changed from: private */
    private RecyclerView RecyclerView;
    /* access modifiers changed from: private */
    private AdView adView;
    private Button addItem;
    /* access modifiers changed from: private */
    private TextView allDevice;
    /* access modifiers changed from: private */
    private TextView allDeviceRunning;
    /* access modifiers changed from: private */
private BlockListAdapter blockListAdapter;

    private RelativeLayout body;
    private Button connectToClient;
    /* access modifiers changed from: private */
    private int connectionType;
    /* access modifiers changed from: private */
    public Context context;
    private DrawerLayout drawer;
    int drop;
    /* access modifiers changed from: private */
    private final Handler handlerJob = new Handler();
    private int idClicked;
    private  int imageAc;
    private int imageAlam;
    private int imageBoxFan;
    private int imageBulb;
    private  int imageCarLock;
    private int imageCelingFan;
    private int imageCfl;
    private int imageFan;
    private int imageFridge;
    private  int imageFridge1;
    private  int imageLamp;
    private int imageLampTall;
    private int imageLedBulb;
    private  int imageLock;
    private  int imageMusicPlayer;
    private int imagePowerSubly;
    private int imageRadio;
    private  int imageSingleDoor;
    private  int imageTableLamb;
    private   int imageTv;
    private  int imageTv1;
    private int imageWashingMachin;
    private int imageWindow;
    private int imageXenon;
    private int imgbarthRoom;
    private  int imgbedRoom;
    private  int imgdiningRoom;
    private  int imggarage;
    private  int imggarden;
    private  int imgkitchen;
    private  int imglivingRoom;
    private  int imgsink;
    private  int imgtoilet;
    private  int light;
    /* access modifiers changed from: private */
    private InterstitialAd mInterstitialAd;
    private LinearLayout main_container;
    private Button moreInfor;
    /* access modifiers changed from: private */
    private LinearLayout noDataLogo;
    /* access modifiers changed from: private */
    private ImageView notifyImage;
    private RelativeLayout notifyLayout;
    /* access modifiers changed from: private */
    private ProgressDialog pDialog;
    int rain;
    int temperature;
    private Typeface textFont;
    private Timer timer;
    private TimerTask timerTask;
    /* access modifiers changed from: private */
    private ToolListAdapter toolListAdapter;
    /* access modifiers changed from: private */
    private List<ToolObject> toolObjectList = new ArrayList();
    /* access modifiers changed from: private */
    private LinearLayout toolWrapper1;

    public class SubThingSpeak extends ThingsPeak {
        private int state;
        private ToolObject toolObject;

        public SubThingSpeak(Context context, int i, ToolObject toolObject2, boolean z) {
            super(context, i, toolObject2, z);
        }

        public SubThingSpeak(Context context, int i, ToolObject toolObject2, int i2, boolean z) {
            super(context, i, toolObject2, i2, z);
            this.toolObject = toolObject2;
            this.state = i2;
        }

        public void onPost(boolean z) {
            if (!z) {
                Home.this.pDialog.dismiss();
                Home.this.toolWrapper1.setEnabled(true);
            }
        }

        public void changeIndicaterStatus(String str, boolean z) {
            if (!z) {
                String str2 = " ";
                if (this.toolObject.deviceType == Utility.DEVICE) {
                    Home.dataBase.changeSate(this.toolObject.toolID, this.state);
                    TextView access1300 = Home.this.allDeviceRunning;
                    StringBuilder sb = new StringBuilder();
                    sb.append("");
                    sb.append(Home.dataBase.getAllRunningDeviceCount(1, Utility.DEVICE));
                    access1300.setText(sb.toString());
                    PrintStream printStream = System.out;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("laplap ");
                    sb2.append(this.state);
                    sb2.append(str2);
                    sb2.append(this.toolObject.toolID);
                    printStream.println(sb2.toString());
                } else {
                    Home.dataBase.updateSensorValue(this.toolObject.toolID, str);
                    PrintStream printStream2 = System.out;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("laplapdsdsd ");
                    sb3.append(str);
                    sb3.append(str2);
                    sb3.append(this.toolObject.toolID);
                    printStream2.println(sb3.toString());
                }
            }
            Home.this.toolObjectList = Home.dataBase.getToolInBlock(Home.this.idClicked);
            Home.this.toolListAdapter.setToolObjects(Home.this.toolObjectList);
            Home.this.toolListAdapter.notifyDataSetChanged();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.home, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        this.textFont = Typeface.createFromAsset(this.context.getAssets(), "fonts/BhashitaComplexBoldEnglish.ttf");
        dataBase = new DataBase(this.context);
        this.connectionType = dataBase.getConnectionType();
        insertRowData();
        loadimages();
        loadResource(view);
        stoptimertask();
        startTimer();
        orientation = getResources().getConfiguration().orientation;
        isLoaded = 1;
        if (isLoaded == 1) {
            if (dataBase.getAllBlock().size() == 0) {
                this.noDataLogo.setVisibility(View.VISIBLE);
            } else {
                this.noDataLogo.setVisibility(View.GONE);
            }
            this.notifyLayout.setVisibility(View.GONE);
        } else {
            if (this.connectionType == Utility.LOCAL) {
                if (dataBase.getToolInBlock(this.idClicked, Utility.DEVICE).size() == 0) {
                    this.noDataLogo.setVisibility(View.VISIBLE);
                } else {
                    this.noDataLogo.setVisibility(View.GONE);
                }
            } else if (dataBase.getToolInBlock(this.idClicked).size() == 0) {
                this.noDataLogo.setVisibility(View.VISIBLE);
            } else {
                this.noDataLogo.setVisibility(View.GONE);
            }
            if (dataBase.getConnectFirst() == 0) {
                this.notifyLayout.setVisibility(View.GONE);
            } else {
                this.notifyLayout.setVisibility(View.VISIBLE);
            }
        }
        buttonClick();
        createList();
        this.mInterstitialAd = new InterstitialAd(this.context);
        this.mInterstitialAd.setAdUnitId(this.context.getString(R.string.INTERSTITIAL_AD_ID));
    }

    public void onAttach(Context context2) {
        super.onAttach(context2);
        this.context = context2;
    }

    public void createBlockList(int i) {
        blockObjects = dataBase.getAllBlock();
        BlockListAdapter r1 = new BlockListAdapter(blockObjects, this.context, this.RecyclerView, this.noDataLogo, this.idClicked) {
            public void blockClick(int i) {
                Home.this.idClicked = Home.blockObjects.get(i).id;
                Home.isLoaded = 2;
                if (Home.this.connectionType == Utility.LOCAL) {
                    if (Home.dataBase.getToolInBlock(Home.this.idClicked, Utility.DEVICE).size() == 0) {
                        Home.this.noDataLogo.setVisibility(View.VISIBLE);
                    } else {
                        Home.this.noDataLogo.setVisibility(View.GONE);
                    }
                } else if (Home.dataBase.getToolInBlock(Home.this.idClicked).size() == 0) {
                    Home.this.noDataLogo.setVisibility(View.VISIBLE);
                } else {
                    Home.this.noDataLogo.setVisibility(View.GONE);
                }
                Home.this.createList();
            }

            public void blockLongClick(final int i) {
                final Dialog dialog = new Dialog(Home.this.context);
                dialog.setContentView(R.layout.edit_delete_diaog);
                Home.this.adView = dialog.findViewById(R.id.adView);
                if (Home.dataBase.isShowAdd() == Utility.SHOW_ADS) {
                    Home.this.adView.setVisibility(View.VISIBLE);
                    Home.this.adView.loadAd(new Builder().build());
                } else {
                    Home.this.adView.setVisibility(View.GONE);
                }
                if (VERSION.SDK_INT >= 19) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                dialog.show();
                final int i2 = Home.blockObjects.get(i).id;
                Button button = dialog.findViewById(R.id.delete);
                dialog.findViewById(R.id.edit).setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        BlockDialog r4 = new BlockDialog(Home.this.context, Home.this.RecyclerView, i2) {
                            public void loadImgEmpty() {
                                if (Home.dataBase.getAllBlock().size() == 0) {
                                    Home.this.noDataLogo.setVisibility(View.VISIBLE);
                                } else {
                                    Home.this.noDataLogo.setVisibility(View.GONE);
                                }
                            }

                            public void notifyDataSetChange() {
                                Home.this.blockListAdapter.setBlockObjects(Home.dataBase.getAllBlock());
                                Home.this.blockListAdapter.notifyDataSetChanged();
                            }
                        };
                        if (VERSION.SDK_INT >= 19) {
                            r4.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                        }
                        dialog.dismiss();
                        r4.show();
                    }
                });
                button.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        final Dialog dialog = new Dialog(Home.this.context);
                        dialog.setContentView(R.layout.exit_dialog);
                        Home.this.adView = dialog.findViewById(R.id.adView);
                        if (Home.dataBase.isShowAdd() == Utility.SHOW_ADS) {
                            Home.this.adView.loadAd(new Builder().build());
                        } else {
                            Home.this.adView.setVisibility(View.GONE);
                        }
                        dialog.dismiss();
                        dialog.show();
                        Button button = dialog.findViewById(R.id.btn_no);
                        Button button2 = dialog.findViewById(R.id.btn_yes);
                        ((TextView) dialog.findViewById(R.id.txt_confirm)).setText("Are you sure you want to delete?");
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
                                Home.dataBase.deleteBlock(i2);
                                dialog.dismiss();
                                dialog.dismiss();
                                if (Home.dataBase.getAllBlock().size() == 0) {
                                    Home.this.noDataLogo.setVisibility(View.VISIBLE);
                                } else {
                                    Home.this.noDataLogo.setVisibility(View.GONE);
                                }
                                TextView access800 = Home.this.allDevice;
                                StringBuilder sb = new StringBuilder();
                                sb.append("");
                                sb.append(Home.dataBase.getAllDeviceCountInBlock(Home.this.idClicked, Utility.DEVICE));
                                access800.setText(sb.toString());
                                Home.blockObjects = Home.dataBase.getAllBlock();
                                Home.this.blockListAdapter.setBlockObjects(Home.blockObjects);
                                Home.this.blockListAdapter.notifyItemRemoved(i);
                            }
                        });
                    }
                });
            }
        };
        this.blockListAdapter = r1;
        if (dataBase.getAllBlock().size() == 0) {
            this.noDataLogo.setVisibility(View.VISIBLE);
        } else {
            this.noDataLogo.setVisibility(View.GONE);
        }
        this.RecyclerView.setAdapter(this.blockListAdapter);
        this.RecyclerView.setLayoutManager(new GridLayoutManager(this.context, i, androidx.recyclerview.widget.RecyclerView.VERTICAL, false));
        TextView textView = this.allDevice;
        StringBuilder sb = new StringBuilder();
        String str = "";
        sb.append(str);
        sb.append(dataBase.getAllDeviceCount(Utility.DEVICE));
        textView.setText(sb.toString());
        TextView textView2 = this.allDeviceRunning;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append(dataBase.getAllRunningDeviceCount(1, Utility.DEVICE));
        textView2.setText(sb2.toString());
    }

    public void createToolList(int i) {
        if (this.connectionType == Utility.LOCAL) {
            this.toolObjectList = dataBase.getToolInBlock(this.idClicked, Utility.DEVICE);
            System.out.println("asjaksahks if");
        } else {
            this.toolObjectList = dataBase.getToolInBlock(this.idClicked);
            System.out.println("asjaksahks else");
        }
        ToolListAdapter r2 = new ToolListAdapter(this.toolObjectList, this.context, this.RecyclerView, this.noDataLogo, this.idClicked, this.allDevice, this.allDeviceRunning) {
            public void toolLongClick(int i) {
            }

            public void toolClick(int i, LinearLayout linearLayout) {
                String str;
                LinearLayout linearLayout2 = linearLayout;
                linearLayout2.setEnabled(false);
                Home.this.toolWrapper1 = linearLayout2;
                View inflate = LayoutInflater.from(Home.this.context).inflate(R.layout.progress, null);
                Home home = Home.this;
                home.pDialog = new ProgressDialog(home.context);
                if (VERSION.SDK_INT >= 19) {
                    Home.this.pDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                Home.this.pDialog.setIndeterminate(false);
                Home.this.pDialog.setCancelable(false);
                Home.this.pDialog.show();
                Home.this.pDialog.setContentView(inflate);
                int i2 = Home.this.toolObjectList.get(i).id;
                ToolObject toolByid = Home.dataBase.getToolByid(i2);
                int i3 = toolByid.toolState;
                if (i3 == 0) {
                    str = toolByid.toolOnCode;
                } else {
                    str = toolByid.toolOffCode;
                }
                String str2 = str;
                if (Home.this.connectionType == Utility.LOCAL) {
                    String str3 = toolByid.ip;
                    String str4 = toolByid.port;
                    StringBuilder sb = new StringBuilder();
                    sb.append(i2);
                    String str5 = "";
                    sb.append(str5);
                    String sb2 = sb.toString();
                    if (str3.equals(str5) || str4.equals(str5) || str2.equals(str5)) {
                        Toasty.error(Home.this.context, "Not set IP Address or Port number or ON/OFF code", 0, true).show();
                        Home.this.pDialog.dismiss();
                        linearLayout2.setEnabled(true);
                    } else {
                        final LinearLayout linearLayout3 = linearLayout;
                        final String str6 = sb2;
                        final int i4 = i3;
                        TCPSocket r0 = new TCPSocket(str3, str4, str2) {
                            public boolean isSuccess(boolean z) {
                                Home.this.pDialog.dismiss();
                                linearLayout3.setEnabled(true);
                                if (z) {
                                    Home.dataBase.changeSate(str6, i4);
                                    Home.this.toolObjectList = Home.dataBase.getToolInBlock(Home.this.idClicked, Utility.DEVICE);
                                    Home.this.toolListAdapter.setToolObjects(Home.this.toolObjectList);
                                    Home.this.toolListAdapter.notifyDataSetChanged();
                                    TextView access1300 = Home.this.allDeviceRunning;
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("");
                                    sb.append(Home.dataBase.getAllRunningDeviceCount(1, Utility.DEVICE));
                                    access1300.setText(sb.toString());
                                } else {
                                    Toasty.error(Home.this.context, "Device not connected", 0, true).show();
                                }
                                return false;
                            }
                        };
                        r0.send();
                    }
                } else if (Home.this.connectionType != Utility.THINGSPEAK) {
                    linearLayout2.setEnabled(true);
                    Home.this.pDialog.dismiss();
                    Toasty.error(Home.this.context, "Your connection mode is not set correctly", 0, true).show();
                } else if (toolByid.deviceType == Utility.DEVICE) {
                    Home home2 = Home.this;
                    SubThingSpeak subThingSpeak = new SubThingSpeak(home2.context, Utility.WRITE_FEED, toolByid, i3, false);
                    subThingSpeak.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                } else if (toolByid.deviceType == Utility.SENSOR) {
                    Home home3 = Home.this;
                    SubThingSpeak subThingSpeak2 = new SubThingSpeak(home3.context, Utility.READ_FEILD, toolByid, i3, false);
                    subThingSpeak2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                } else {
                    Home home4 = Home.this;
                    SubThingSpeak subThingSpeak3 = new SubThingSpeak(home4.context, Utility.READ_FEED, toolByid, i3, false);
                    subThingSpeak3.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }
                int updateKeyCount = Home.dataBase.updateKeyCount();
                AdRequest build = new Builder().addTestDevice("B3EEABB8EE11C2BE770B684D95219ECB").build();
                if (!Home.this.mInterstitialAd.isLoaded()) {
                    Home.this.mInterstitialAd.loadAd(build);
                }
                if (Home.dataBase.isShowAdd() == Utility.SHOW_ADS && updateKeyCount >= Utility.ADD_KEY_COUNT && Home.this.mInterstitialAd.isLoaded()) {
                    Home.this.mInterstitialAd.show();
                }
            }
        };
        this.toolListAdapter = r2;
        this.RecyclerView.setAdapter(this.toolListAdapter);
        this.RecyclerView.setLayoutManager(new GridLayoutManager(this.context, i, androidx.recyclerview.widget.RecyclerView.VERTICAL, false));
        TextView textView = this.allDevice;
        StringBuilder sb = new StringBuilder();
        String str = "";
        sb.append(str);
        sb.append(dataBase.getAllDeviceCountInBlock(this.idClicked, Utility.DEVICE));
        textView.setText(sb.toString());
        TextView textView2 = this.allDeviceRunning;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append(dataBase.getAllRunningDeviceCountInBlock(this.idClicked, 1, Utility.DEVICE));
        textView2.setText(sb2.toString());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003e, code lost:
        if (com.lmlab.madhurawanniarachchi.nodemcuwificontroller.Utill.Utility.getWith(r7.context) <= 800) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0023, code lost:
        if (com.lmlab.madhurawanniarachchi.nodemcuwificontroller.Utill.Utility.getWith(r7.context) <= 800) goto L_0x0042;
     */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004a  */
    public void createList() {
        int i = 3;
        if (orientation == 1) {
//            if (Utility.getWith(this.context) > 400) {
                Log.i("UTILITYWIDTH", String.valueOf(Utility.getWith(this.context)));
                if (Utility.getWith(this.context) > 400) {
                }
                i = 4;
                if (isLoaded == 1) {
                    createBlockList(i);
                    return;
                } else {
                    createToolList(i);
                    return;
                }
//            }
        } else
//            if (Utility.getHeight(this.context) > 400) {
            Log.i("UTILITYHEIGHT", String.valueOf(Utility.getHeight(this.context)));
            if (Utility.getWith(this.context) > 400) {
            }
            i = 4;
            if (isLoaded == 1) {
            }
//        }
        i = 2;
        if (isLoaded == 1) {
        }
    }

    public boolean onBackPressed() {
        if (isLoaded != 2) {
            return true;
        }
        isLoaded = 1;
        createList();
        return false;
    }

    public void buttonClick() {
        this.addItem.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (Home.isLoaded == 1) {
                    BlockDialog r11 = new BlockDialog(Home.this.context, Home.this.RecyclerView, 0) {
                        public void loadImgEmpty() {
                            if (Home.dataBase.getAllBlock().size() == 0) {
                                Home.this.noDataLogo.setVisibility(View.VISIBLE);
                            } else {
                                Home.this.noDataLogo.setVisibility(View.GONE);
                            }
                        }

                        public void notifyDataSetChange() {
                        //    Home.this.blockListAdapter.setBlockObjects(Home.blockObjects);
                            Home.blockObjects=Home.dataBase.getAllBlock();
                            Home.this.blockListAdapter.setBlockObjects(Home.blockObjects);
                            Home.this.blockListAdapter.notifyItemInserted(Home.blockObjects.size() - 1);
                            Home.this.blockListAdapter.notifyDataSetChanged();
                        }
                    };
                    if (VERSION.SDK_INT >= 19) {
                        Window window = r11.getWindow();
                        window.getClass();
                        window.setBackgroundDrawable(new ColorDrawable(0));
                    }
                    r11.show();
                    return;
                }
                TooLDialog r3 = new TooLDialog(Home.this.context, Home.this.idClicked, Home.this.allDevice, Home.this.allDeviceRunning, 0) {
                    public void loadImgempty() {
                        if (Home.dataBase.getToolInBlock(Home.this.idClicked).size() == 0) {
                            Home.this.noDataLogo.setVisibility(View.VISIBLE);
                        } else {
                            Home.this.noDataLogo.setVisibility(View.GONE);
                        }
                    }

                    public void notifyDataSetChange() {
                        if (Home.this.connectionType == Utility.LOCAL) {
                            Home.this.toolObjectList = Home.dataBase.getToolInBlock(Home.this.idClicked, Utility.DEVICE);
                            System.out.println("jsksjkajsk if");
                        } else {
                            System.out.println("jsksjkajsk else");
                            Home.this.toolObjectList = Home.dataBase.getToolInBlock(Home.this.idClicked);
                        }
                        TextView access800 = Home.this.allDevice;
                        StringBuilder sb = new StringBuilder();
                        sb.append("");
                        sb.append(Home.dataBase.getAllDeviceCountInBlock(Home.this.idClicked, Utility.DEVICE));
                        access800.setText(sb.toString());
                        Home.this.toolListAdapter.setToolObjects(Home.this.toolObjectList);
                        Home.this.toolListAdapter.notifyItemInserted(Home.this.toolObjectList.size() - 1);
                        Home.this.toolListAdapter.notifyDataSetChanged();
                    }
                };
                if (VERSION.SDK_INT >= 19) {
                    r3.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                r3.show();
            }
        });
        this.moreInfor.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Home.this.drawer.openDrawer(GravityCompat.START);
            }
        });
        this.connectToClient.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (Home.dataBase.getConnectFirst() == 1) {
                    Home.mTcpClient = null;
                    Home.conctTask = new MainActivity.connectTask();
                    Home.conctTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    if (Home.mTcpClient != null) {
                        Home.this.notifyImage.setImageResource(R.drawable.connect);
                    } else {
                        Home.this.notifyImage.setImageResource(R.drawable.disconect);
                    }
                }
            }
        });
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
                Home.this.handlerJob.post(new Runnable() {
                    public void run() {
                        if (Home.isLoaded == 2 && Home.this.connectionType == Utility.THINGSPEAK) {
                            SubThingSpeak subThingSpeak = new SubThingSpeak(Home.this.context, Utility.READ_FEED, Home.dataBase.getToolByDeviceType(Utility.SENSOR, Home.this.idClicked), true);
                            subThingSpeak.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                        }
                    }
                });
            }
        };
    }

    public void insertRowData() {
        dataBase.insertImage(1, "filemnt_bulb", 1, 1);
        dataBase.insertImage(2, NotificationCompat.CATEGORY_ALARM, 1, 1);
        dataBase.insertImage(3, "cfl", 1, 1);
        dataBase.insertImage(4, "fan", 1, 1);
        dataBase.insertImage(5, "fridge", 1, 1);
        dataBase.insertImage(6, "radio", 1, 1);
        dataBase.insertImage(7, "television1", 1, 1);
        dataBase.insertImage(8, "xenon_bulb", 1, 1);
        dataBase.insertImage(9, "ac", 1, 1);
        dataBase.insertImage(10, "box_fan", 1, 1);
        dataBase.insertImage(11, "celing_fan", 1, 1);
        dataBase.insertImage(12, "lamp", 1, 1);
        dataBase.insertImage(13, "led_bulb", 1, 1);
        dataBase.insertImage(14, "lock", 1, 1);
        dataBase.insertImage(15, "music_player", 1, 1);
        dataBase.insertImage(16, "power", 1, 1);
        dataBase.insertImage(17, "refrigerator", 1, 1);
        dataBase.insertImage(18, "single_door", 1, 1);
        dataBase.insertImage(19, "table_lamp", 1, 1);
        dataBase.insertImage(20, "television", 1, 1);
        dataBase.insertImage(21, "washing_machine", 1, 1);
        dataBase.insertImage(22, "lamp_tall", 1, 1);
        dataBase.insertImage(23, "window", 1, 1);
        dataBase.insertImage(24, "car_lock", 1, 1);
        dataBase.insertImage(100, "livingroom", 0, 1);
        dataBase.insertImage(101, "bed", 0, 1);
        dataBase.insertImage(102, "dinning", 0, 1);
        dataBase.insertImage(103, "kitchen", 0, 1);
        dataBase.insertImage(105, "sink", 0, 1);
        dataBase.insertImage(106, "bathtub", 0, 1);
        dataBase.insertImage(107, "toilet", 0, 1);
        dataBase.insertImage(108, "garage", 0, 1);
        dataBase.insertImage(109, "rain", 2, 1);
        dataBase.insertImage(110, "drop", 2, 1);
        dataBase.insertImage(111, "thermometer", 2, 1);
        dataBase.insertImage(112, "sun", 2, 1);
    }

    public void loadResource(View view) {
        this.addItem = view.findViewById(R.id.add_tool);
        this.RecyclerView = view.findViewById(R.id.block_list);
        this.moreInfor = view.findViewById(R.id.more_info);
        this.main_container = view.findViewById(R.id.main_container);
        this.allDevice = view.findViewById(R.id.txt_all_device);
        this.allDeviceRunning = view.findViewById(R.id.txt_all_device_running);
        this.noDataLogo = view.findViewById(R.id.no_data_logo);
        this.notifyImage = view.findViewById(R.id.img_connect);
        this.notifyLayout = view.findViewById(R.id.notify_connect);
        this.connectToClient = view.findViewById(R.id.btn_connect);
        this.body = view.findViewById(R.id.body);
        this.drawer = ((MainActivity) this.context).getDrawer();
        this.allDevice.setTypeface(this.textFont);
        this.allDeviceRunning.setTypeface(this.textFont);
    }

    public void loadimages() {
        this.imglivingRoom = R.drawable.livingroom;
        this.imgbedRoom = R.drawable.bed;
        this.imgdiningRoom = R.drawable.dinning;
        this.imgkitchen = R.drawable.kitchen;
        this.imggarage = R.drawable.garage;
        this.imgbarthRoom = R.drawable.bathtub;
        this.imgtoilet = R.drawable.toilet;
        this.imgsink = R.drawable.sink;
        this.imageAc = R.drawable.ac;
        this.imageAlam = R.drawable.alarm;
        this.imageBoxFan = R.drawable.box_fan;
        this.imageCarLock = R.drawable.car_lock;
        this.imageCelingFan = R.drawable.celing_fan;
        this.imageCfl = R.drawable.cfl;
        this.imageBulb = R.drawable.filemnt_bulb;
        this.imageFridge = R.drawable.fridge;
        this.imageLampTall = R.drawable.lamp_tall;
        this.imageLamp = R.drawable.lamp;
        this.imageLedBulb = R.drawable.led_bulb;
        this.imageLock = R.drawable.lock;
        this.imageMusicPlayer = R.drawable.music_player;
        this.imagePowerSubly = R.drawable.power;
        this.imageRadio = R.drawable.radio;
        this.imageFridge1 = R.drawable.refrigerator;
        this.imageSingleDoor = R.drawable.single_door;
        this.imageTableLamb = R.drawable.table_lamp;
        this.imageTv = R.drawable.television;
        this.imageTv1 = R.drawable.television1;
        this.imageWashingMachin = R.drawable.washing_machine;
        this.imageWindow = R.drawable.window;
        this.imageFan = R.drawable.fan;
        this.imageXenon = R.drawable.xenon_bulb;
        this.rain = R.drawable.rain;
        this.drop = R.drawable.drop;
        this.temperature = R.drawable.thermometer;
        this.light = R.drawable.sun;
    }
}
