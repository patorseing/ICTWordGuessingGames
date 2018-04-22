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

import com.maipatgeorge.tequila.ictwordguessinggames.GuestStartGame;
import com.maipatgeorge.tequila.ictwordguessinggames.R;

public class SettingDialogsFreg extends DialogFragment {

    Switch aSwitch;
    SeekBar seekBar;
    Button button;

    MediaPlayer mysong;
    String oldname;
    String start;
    int volume;
    int pos;
    float reduce;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogsetting, container,false);

        if (savedInstanceState == null){
            oldname  = getArguments().getString("oldname").toString();
            start  = getArguments().getString("start").toString();
            volume  = getArguments().getInt("volume");
            pos  = getArguments().getInt("pos");
        } else {
            start = savedInstanceState.getString("start");
            volume = savedInstanceState.getInt("volume");
            pos = savedInstanceState.getInt("pos");
            oldname  = savedInstanceState.getString("name");
        }


        mysong = MediaPlayer.create(getContext(), R.raw.feelingsohappy);
        mysong.seekTo(pos);
        mysong.start();
        reduce=(float)(100 - volume)/100;
        mysong.setVolume(1-reduce, 1-reduce);
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
                    mysong.setVolume(1-reduce, 1-reduce);
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
                reduce=(float)(100 - volume)/100;
                mysong.setVolume(1-reduce, 1-reduce);
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
                        Intent welcome = new Intent(getActivity(), GuestStartGame.class);
                        welcome.putExtra("name", oldname);
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name", oldname);
        outState.putString("start", start);
        outState.putInt("volume", volume);
        outState.putInt("pos", mysong.getCurrentPosition());
    }
}
