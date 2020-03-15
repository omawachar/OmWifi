package com.yashka.dedofiom.Fragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;
import com.yashka.dedofiom.Model.TimmerM;
import com.yashka.dedofiom.Utill.DataBase;
import com.yashka.dedofiom.R;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class Timmer extends Fragment {
    Button addTimer;
    Context context;
    DataBase dataBase;
    boolean ishowDate = false;
    LinearLayout noDataLogo;
    Button operDrawer;
    TimeCardAdapter timeCardAdapter;
    List<TimmerM> timmerMS;
    RecyclerView timmerRv;

    public class TimeCardAdapter extends RecyclerView.Adapter<TimeCardAdapter.MyViewHolder> {
        int dayValueFriday;
        int dayValueMonday;
        int dayValueSaturday;
        int dayValueSunday;
        int dayValueThursday;
        int dayValueTuesday;
        int dayValueWednesday;
        String offTime;
        String onTime;
        List<TimmerM> timmerMS;

        public class MyViewHolder extends ViewHolder implements OnClickListener, OnLongClickListener, OnTouchListener {
           DataBase dataBase;
            ImageView arrow_down;
            ImageView arrow_up;
            Button btn_enable;
            LinearLayout days_wrapper;
            Button delete;
            RelativeLayout friday;
            ImageView img_is_enable;
            RelativeLayout monday;
            Button offTime;
            Button onTime;
            RelativeLayout saturday;
            Button show_days;
            RelativeLayout sunday;
            RelativeLayout thursday;
            RelativeLayout tuesday;
            TextView txt_friday;
            TextView txt_monday;
            TextView txt_name;
            TextView txt_saturday;
            TextView txt_sunday;
            TextView txt_thursday;
            TextView txt_tuesday;
            TextView txt_wendnesday;
            RelativeLayout wendnesday;

            public void onClick(View view) {
            }

            public boolean onLongClick(View view) {
                return false;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }

            public MyViewHolder(@NonNull View view) {
                super(view);
                this.monday = (RelativeLayout) view.findViewById(R.id.monday);
                this.tuesday = (RelativeLayout) view.findViewById(R.id.tuesday);
                this.wendnesday = (RelativeLayout) view.findViewById(R.id.wendnesday);
                this.thursday = (RelativeLayout) view.findViewById(R.id.thursday);
                this.friday = (RelativeLayout) view.findViewById(R.id.friday);
                this.saturday = (RelativeLayout) view.findViewById(R.id.saturday);
                this.sunday = (RelativeLayout) view.findViewById(R.id.sunday);
                this.onTime = (Button) view.findViewById(R.id.on_time);
                this.offTime = (Button) view.findViewById(R.id.off_time);
                this.delete = (Button) view.findViewById(R.id.delete);
                this.show_days = (Button) view.findViewById(R.id.show_days);
                this.days_wrapper = (LinearLayout) view.findViewById(R.id.days_wrapper);
                this.arrow_up = (ImageView) view.findViewById(R.id.arrow_up);
                this.arrow_down = (ImageView) view.findViewById(R.id.arrow_down);
                this.btn_enable = (Button) view.findViewById(R.id.btn_enable);
                this.img_is_enable = (ImageView) view.findViewById(R.id.img_is_enable);
                this.txt_name = (TextView) view.findViewById(R.id.txt_name);
                this.txt_monday = (TextView) view.findViewById(R.id.txt_monday);
                this.txt_tuesday = (TextView) view.findViewById(R.id.txt_tuesday);
                this.txt_wendnesday = (TextView) view.findViewById(R.id.txt_wendnesday);
                this.txt_thursday = (TextView) view.findViewById(R.id.txt_thursday);
                this.txt_friday = (TextView) view.findViewById(R.id.txt_friday);
                this.txt_saturday = (TextView) view.findViewById(R.id.txt_saturday);
                this.txt_sunday = (TextView) view.findViewById(R.id.txt_sunday);
            }
        }

        public TimeCardAdapter(List<TimmerM> list) {
            this.timmerMS = list;
        }

        @NonNull
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.timer_card, viewGroup, false));
        }

        public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
            this.onTime = Timmer.this.dataBase.getOnTime(((TimmerM) this.timmerMS.get(i)).f90id);
            this.offTime = Timmer.this.dataBase.getOffTime(((TimmerM) this.timmerMS.get(i)).f90id);
            myViewHolder.onTime.setText(this.onTime);
            myViewHolder.offTime.setText(this.offTime);
            myViewHolder.txt_name.setText(((TimmerM) this.timmerMS.get(i)).name);
            this.dayValueMonday = Timmer.this.dataBase.getDayValue(((TimmerM) this.timmerMS.get(i)).f90id, "Monday");
            this.dayValueTuesday = Timmer.this.dataBase.getDayValue(((TimmerM) this.timmerMS.get(i)).f90id, "Tuesday");
            this.dayValueWednesday = Timmer.this.dataBase.getDayValue(((TimmerM) this.timmerMS.get(i)).f90id, "Wednesday");
            this.dayValueThursday = Timmer.this.dataBase.getDayValue(((TimmerM) this.timmerMS.get(i)).f90id, "Thursday");
            this.dayValueFriday = Timmer.this.dataBase.getDayValue(((TimmerM) this.timmerMS.get(i)).f90id, "Friday");
            this.dayValueSaturday = Timmer.this.dataBase.getDayValue(((TimmerM) this.timmerMS.get(i)).f90id, "Saturday");
            this.dayValueSunday = Timmer.this.dataBase.getDayValue(((TimmerM) this.timmerMS.get(i)).f90id, "Sunday");
            Timmer.this.showIsEnable(((TimmerM) this.timmerMS.get(i)).f90id, myViewHolder.img_is_enable);
            if (this.dayValueMonday == 0) {
                myViewHolder.txt_monday.setBackgroundResource(R.drawable.gray_orvel);
            } else {
                myViewHolder.txt_monday.setBackgroundResource(R.drawable.perple_orvel);
            }
            if (this.dayValueTuesday == 0) {
                myViewHolder.txt_tuesday.setBackgroundResource(R.drawable.gray_orvel);
            } else {
                myViewHolder.txt_tuesday.setBackgroundResource(R.drawable.perple_orvel);
            }
            if (this.dayValueWednesday == 0) {
                myViewHolder.txt_wendnesday.setBackgroundResource(R.drawable.gray_orvel);
            } else {
                myViewHolder.txt_wendnesday.setBackgroundResource(R.drawable.perple_orvel);
            }
            if (this.dayValueThursday == 0) {
                myViewHolder.txt_thursday.setBackgroundResource(R.drawable.gray_orvel);
            } else {
                myViewHolder.txt_thursday.setBackgroundResource(R.drawable.perple_orvel);
            }
            if (this.dayValueFriday == 0) {
                myViewHolder.txt_friday.setBackgroundResource(R.drawable.gray_orvel);
            } else {
                myViewHolder.txt_friday.setBackgroundResource(R.drawable.perple_orvel);
            }
            if (this.dayValueSaturday == 0) {
                myViewHolder.txt_saturday.setBackgroundResource(R.drawable.gray_orvel);
            } else {
                myViewHolder.txt_saturday.setBackgroundResource(R.drawable.perple_orvel);
            }
            if (this.dayValueSunday == 0) {
                myViewHolder.txt_sunday.setBackgroundResource(R.drawable.gray_orvel);
            } else {
                myViewHolder.txt_sunday.setBackgroundResource(R.drawable.perple_orvel);
            }
//            myViewHolder.onTime.setOnClickListener(new OnClickListener() {
//                public void onClick(View view) {
//                    TimePickerDialog r4 = new TimePickerDialog(Timmer.this.context, ((TimmerM) TimeCardAdapter.this.timmerMS.get(i)).f90id, 1) {
//                        public void updateTime() {
//                            TimeCardAdapter.this.onTime = this.dataBase.getOnTime(((TimmerM) TimeCardAdapter.this.timmerMS.get(i)).f90id);
//                            TimeCardAdapter.this.notifyItemChanged(i);
//                        }
//                    };
//                    if (VERSION.SDK_INT >= 19) {
//                        r4.getWindow().setBackgroundDrawable(new ColorDrawable(0));
//                    }
//                    r4.show();
//                }
//            });
//            myViewHolder.offTime.setOnClickListener(new OnClickListener() {
//                public void onClick(View view) {
//                    TimePickerDialog r4 = new TimePickerDialog(Timmer.this.context, ((TimmerM) TimeCardAdapter.this.timmerMS.get(i)).f90id, 0,) {
//                        public void updateTime() {
//                            TimeCardAdapter.this.offTime = dataBase.getOffTime((TimeCardAdapter.this.timmerMS.get(i)).f90id);
//                            TimeCardAdapter.this.notifyItemChanged(i);
//                        }
//                    };
//                    if (VERSION.SDK_INT >= 19) {
//                        r4.getWindow().setBackgroundDrawable(new ColorDrawable(0));
//                    }
//                    r4.show();
//                }
//            });
            myViewHolder.delete.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    try {
                        if (Timmer.this.dataBase.deleteTimmmer(((TimmerM) TimeCardAdapter.this.timmerMS.get(i)).f90id)) {
                            TimeCardAdapter.this.timmerMS.remove(i);
                            TimeCardAdapter.this.notifyItemRemoved(i);
                            TimeCardAdapter.this.notifyItemRangeRemoved(i, TimeCardAdapter.this.timmerMS.size());
                            if (TimeCardAdapter.this.timmerMS.size() <= 0) {
                                Timmer.this.noDataLogo.setVisibility(View.VISIBLE);
                            } else {
                                Timmer.this.noDataLogo.setVisibility(View.VISIBLE);
                            }
                        }
                    } catch (Exception unused) {
                    }
                }
            });
            myViewHolder.btn_enable.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    Timmer.this.dataBase.updateIsEnable(((TimmerM) TimeCardAdapter.this.timmerMS.get(i)).f90id);
                    Timmer.this.showIsEnable(((TimmerM) TimeCardAdapter.this.timmerMS.get(i)).f90id, myViewHolder.img_is_enable);
                    TimeCardAdapter.this.notifyItemChanged(i);
                }
            });
            myViewHolder.show_days.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    Timmer.this.showDays(myViewHolder.days_wrapper, Timmer.this.ishowDate, myViewHolder.arrow_up, myViewHolder.arrow_down);
                }
            });
            myViewHolder.monday.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    String str = "Monday";
                    Timmer.this.dataBase.updateDay(((TimmerM) TimeCardAdapter.this.timmerMS.get(i)).f90id, str);
                    TimeCardAdapter timeCardAdapter = TimeCardAdapter.this;
                    timeCardAdapter.dayValueMonday = Timmer.this.dataBase.getDayValue(((TimmerM) TimeCardAdapter.this.timmerMS.get(i)).f90id, str);
                    TimeCardAdapter.this.notifyItemChanged(i);
                }
            });
            myViewHolder.tuesday.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    String str = "Tuesday";
                    Timmer.this.dataBase.updateDay(((TimmerM) TimeCardAdapter.this.timmerMS.get(i)).f90id, str);
                    TimeCardAdapter timeCardAdapter = TimeCardAdapter.this;
                    timeCardAdapter.dayValueTuesday = Timmer.this.dataBase.getDayValue(((TimmerM) TimeCardAdapter.this.timmerMS.get(i)).f90id, str);
                    TimeCardAdapter.this.notifyItemChanged(i);
                }
            });
            myViewHolder.wendnesday.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    String str = "Wednesday";
                    Timmer.this.dataBase.updateDay(((TimmerM) TimeCardAdapter.this.timmerMS.get(i)).f90id, str);
                    TimeCardAdapter timeCardAdapter = TimeCardAdapter.this;
                    timeCardAdapter.dayValueWednesday = Timmer.this.dataBase.getDayValue(((TimmerM) TimeCardAdapter.this.timmerMS.get(i)).f90id, str);
                    TimeCardAdapter.this.notifyItemChanged(i);
                }
            });
            myViewHolder.thursday.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    String str = "Thursday";
                    Timmer.this.dataBase.updateDay(((TimmerM) TimeCardAdapter.this.timmerMS.get(i)).f90id, str);
                    TimeCardAdapter timeCardAdapter = TimeCardAdapter.this;
                    timeCardAdapter.dayValueThursday = Timmer.this.dataBase.getDayValue(((TimmerM) TimeCardAdapter.this.timmerMS.get(i)).f90id, str);
                    TimeCardAdapter.this.notifyItemChanged(i);
                }
            });
            myViewHolder.friday.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    String str = "Friday";
                    Timmer.this.dataBase.updateDay(((TimmerM) TimeCardAdapter.this.timmerMS.get(i)).f90id, str);
                    TimeCardAdapter timeCardAdapter = TimeCardAdapter.this;
                    timeCardAdapter.dayValueFriday = Timmer.this.dataBase.getDayValue(((TimmerM) TimeCardAdapter.this.timmerMS.get(i)).f90id, str);
                    TimeCardAdapter.this.notifyItemChanged(i);
                }
            });
            myViewHolder.saturday.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    String str = "Saturday";
                    Timmer.this.dataBase.updateDay(((TimmerM) TimeCardAdapter.this.timmerMS.get(i)).f90id, str);
                    TimeCardAdapter timeCardAdapter = TimeCardAdapter.this;
                    timeCardAdapter.dayValueSaturday = Timmer.this.dataBase.getDayValue(((TimmerM) TimeCardAdapter.this.timmerMS.get(i)).f90id, str);
                    TimeCardAdapter.this.notifyItemChanged(i);
                }
            });
            myViewHolder.sunday.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    String str = "Sunday";
                    Timmer.this.dataBase.updateDay(((TimmerM) TimeCardAdapter.this.timmerMS.get(i)).f90id, str);
                    TimeCardAdapter timeCardAdapter = TimeCardAdapter.this;
                    timeCardAdapter.dayValueSunday = Timmer.this.dataBase.getDayValue(((TimmerM) TimeCardAdapter.this.timmerMS.get(i)).f90id, str);
                    TimeCardAdapter.this.notifyItemChanged(i);
                }
            });
        }

        public int getItemCount() {
            return this.timmerMS.size();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.timmer, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        this.addTimer = (Button) view.findViewById(R.id.add_timer);
        this.operDrawer = (Button) view.findViewById(R.id.open_drawer);
        this.dataBase = new DataBase(this.context);
        this.timmerRv = (RecyclerView) view.findViewById(R.id.timer_list);
        this.noDataLogo = (LinearLayout) view.findViewById(R.id.no_data_logo);
        buttonClick();
        createTimmerList();
    }

    public void createTimmerList() {
        this.timmerMS = this.dataBase.getAllTimmer();
        this.timeCardAdapter = new TimeCardAdapter(this.timmerMS);
        this.timmerRv.setAdapter(this.timeCardAdapter);
        this.timmerRv.setLayoutManager(new GridLayoutManager(this.context, 1, RecyclerView.VERTICAL, false));
        if (this.timmerMS.size() <= 0) {
            this.noDataLogo.setVisibility(View.VISIBLE);
        } else {
            this.noDataLogo.setVisibility(View.VISIBLE);
        }
    }

    public void onAttach(Context context2) {
        super.onAttach(context2);
        this.context = context2;
    }

    public void showDays(LinearLayout linearLayout) {
        if (this.ishowDate) {
            linearLayout.setVisibility(View.VISIBLE);
        } else {
            linearLayout.setVisibility(View.VISIBLE);
        }
    }

    public void showDays(LinearLayout linearLayout, boolean z, ImageView imageView, ImageView imageView2) {
        if (z) {
            linearLayout.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
            this.ishowDate = false;
            return;
        }
        imageView2.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.VISIBLE);
        this.ishowDate = true;
    }

    public void showIsEnable(int i, ImageView imageView) {
        if (this.dataBase.getIsEnable(i) == 1) {
            imageView.setBackgroundResource(R.drawable.perple_orvel);
        } else {
            imageView.setBackgroundResource(R.drawable.gray_orvel);
        }
    }

    public void buttonClick() {
        this.addTimer.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                final Dialog dialog = new Dialog(Timmer.this.context);
                dialog.setContentView(R.layout.timer_dialog);
                dialog.show();
                Button button = (Button) dialog.findViewById(R.id.btn_cancel);
                Button button2 = (Button) dialog.findViewById(R.id.btn_save);
                final EditText editText = (EditText) dialog.findViewById(R.id.timer_name);
                ((AdView) dialog.findViewById(R.id.adView)).loadAd(new Builder().build());
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
                        String format = new SimpleDateFormat("hh:mm a").format(Calendar.getInstance().getTime());
                        String obj = editText.getText().toString();
                        if (obj.length() > 0) {
                            Timmer.this.dataBase.insertTimer(format, format, obj, 0, 0, 0, 0, 0, 0, 0);
                            dialog.dismiss();
                            Timmer.this.createTimmerList();
                            return;
                        }
                        Toasty.info(Timmer.this.getContext(), (CharSequence) "Please Enter Name", 0, true).show();
                    }
                });
            }
        });
    }
}
