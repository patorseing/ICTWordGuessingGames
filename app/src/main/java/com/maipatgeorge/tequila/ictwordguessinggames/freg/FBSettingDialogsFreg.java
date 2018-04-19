package com.maipatgeorge.tequila.ictwordguessinggames.freg;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import com.maipatgeorge.tequila.ictwordguessinggames.FBuserStartGame;
import com.maipatgeorge.tequila.ictwordguessinggames.R;

public class FBSettingDialogsFreg extends DialogFragment {

    Switch aSwitch;
    SeekBar seekBar;
    Button button;

    MediaPlayer mysong;
    String name;
    String id;
    String start;
    int volume;
    int pos;
    float log1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogsetting, container,false);

        name  = getArguments().getString("name").toString();
        id  = getArguments().getString("id").toString();
        start  = getArguments().getString("start").toString();
        start  = getArguments().getString("start").toString();
        volume  = getArguments().getInt("volume");
        pos  = getArguments().getInt("pos");

        mysong = MediaPlayer.create(getContext(), R.raw.feelingsohappy);
        mysong.seekTo(pos);
        mysong.start();
        log1=(float)(Math.log(100-volume)/Math.log(volume));
        mysong.setVolume(1-log1, 1-log1);
        mysong.setLooping(true);

        aSwitch = (Switch) view.findViewById(R.id.switch1);


        if (start.equals("true")){
            aSwitch.setChecked(true);
            start = "true";
        } else {
            aSwitch.setChecked(false);
            mysong.stop();
            start = "false";
        }

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mysong = MediaPlayer.create(getContext(), R.raw.feelingsohappy);
                    mysong.start();
                    mysong.setVolume(1-log1, 1-log1);
                    start = "true";
                } else {
                    //Toast.makeText(getContext(),"off", Toast.LENGTH_SHORT).show();
                    mysong.stop();
                    start = "false";
                }
            }
        });

        seekBar = (SeekBar) view.findViewById(R.id.seekBar);

        seekBar.setProgress(volume);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                volume = i;
                if (volume == 0){
                    volume = 1;
                }
                log1 = (float)(Math.log(100-volume)/Math.log(volume));
                mysong.setVolume(1-log1, 1-log1);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getContext(),""+volume, Toast.LENGTH_SHORT).show();
            }
        });

        button = (Button) view.findViewById(R.id.setclose);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(),"click", Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent welcome = new Intent(getActivity(), FBuserStartGame.class);
                        welcome.putExtra("name", name);
                        welcome.putExtra("id", id);
                        welcome.putExtra("start", start);
                        welcome.putExtra("volume", volume);
                        welcome.putExtra("pos", mysong.getCurrentPosition());
                        mysong.stop();
                        startActivity(welcome);
                    }
                }, 1);
                getDialog().dismiss();
            }
        });

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        mysong.stop();
    }
}

