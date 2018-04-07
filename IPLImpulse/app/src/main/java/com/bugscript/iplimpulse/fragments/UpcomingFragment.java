package com.bugscript.iplimpulse.fragments;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bugscript.iplimpulse.MainActivity;
import com.bugscript.iplimpulse.R;

import org.w3c.dom.Text;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpcomingFragment extends Fragment{

    @BindView(R.id.textView4) TextView ma;
    @BindView(R.id.textView6) TextView sta;
    @BindView(R.id.textView9) TextView be;
    @BindView(R.id.imageView2) ImageView te_1;
    @BindView(R.id.imageView3) ImageView te_2;
    @BindView(R.id.imageView7) ImageView ar_1;
    @BindView(R.id.imageView8) ImageView ar_2;
    @BindView(R.id.textView18) TextView ar_val_1;
    @BindView(R.id.textView23) TextView ar_val_2;

    View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.upcoming,container,false);
        ButterKnife.bind(this,mView);
        ma.setText(MainActivity.current_upcoming);
        sta.setText(MainActivity.current_stadium);
        be.setText(MainActivity.current_bet_on.toUpperCase());
        if(MainActivity.flag_3==1){
            switch (MainActivity.ar_img_1_string){
                case "up":
                    ar_1.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
                    break;
                case "down":
                    ar_1.setImageResource(R.drawable.down_red);
                    break;
            }
        }
        if(MainActivity.flag_4==1){
            switch (MainActivity.ar_img_2_string){
                case "up":
                    ar_2.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
                    break;
                case "down":
                    ar_2.setImageResource(R.drawable.down_red);
                    break;
            }
        }
        if(MainActivity.ar_img_1_string.equals("up")){
            ar_val_1.setTextColor(Color.parseColor("#4CAF50"));
        }else{
            ar_val_1.setTextColor(Color.RED);
        }
        if(MainActivity.ar_img_2_string.equals("up")){
            ar_val_1.setTextColor(Color.parseColor("#4CAF50"));
        }else{
            ar_val_2.setTextColor(Color.RED);
        }
        ar_val_1.setText(MainActivity.ar_val_1_str);
        ar_val_2.setText(MainActivity.ar_val_2_str);
        if(MainActivity.flag_1==1) {
            switch (MainActivity.t1) {
                case "csk":
                    te_1.setImageResource(R.drawable.csk);
                    break;
                case "rcb":
                    te_1.setImageResource(R.drawable.rcb);
                    break;
                case "kxip":
                    te_1.setImageResource(R.drawable.pun);
                    break;
                case "dd":
                    te_1.setImageResource(R.drawable.dd);
                    break;
                case "kkr":
                    te_1.setImageResource(R.drawable.kkr);
                    break;
                case "rr":
                    te_1.setImageResource(R.drawable.rr);
                    break;
                case "mi":
                    te_1.setImageResource(R.drawable.mi);
                    break;
                case "srh":
                    te_1.setImageResource(R.drawable.srh);
                    break;
            }
        }
        if(MainActivity.flag_2==1) {
            switch (MainActivity.t2) {
                case "csk":
                    te_2.setImageResource(R.drawable.csk);
                    break;
                case "rcb":
                    te_2.setImageResource(R.drawable.rcb);
                    break;
                case "kxip":
                    te_2.setImageResource(R.drawable.pun);
                    break;
                case "dd":
                    te_2.setImageResource(R.drawable.dd);
                    break;
                case "kkr":
                    te_2.setImageResource(R.drawable.kkr);
                    break;
                case "rr":
                    te_2.setImageResource(R.drawable.rr);
                    break;
                case "mi":
                    te_2.setImageResource(R.drawable.mi);
                    break;
                case "srh":
                    te_2.setImageResource(R.drawable.srh);
                    break;
            }
        }
        return mView;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
