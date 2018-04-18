package com.maipatgeorge.tequila.ictwordguessinggames.freg;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.maipatgeorge.tequila.ictwordguessinggames.DB.DBHelper;
import com.maipatgeorge.tequila.ictwordguessinggames.GuestStartGame;
import com.maipatgeorge.tequila.ictwordguessinggames.R;

import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.KEY_Gname;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.KEY_NAME;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.TABLE_Guest;
import static com.maipatgeorge.tequila.ictwordguessinggames.DB.Constant.TABLE_GuestPass;

public class RenameDialogsFreg extends DialogFragment {

    private EditText editText;
    private Button back;
    private Button update;
    DBHelper helper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogrename, container,false);


        editText = (EditText) view.findViewById(R.id.editText);
        back = (Button) view.findViewById(R.id.back_to_the_main);
        update = (Button) view.findViewById(R.id.update_name);
        helper = new DBHelper(getContext());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = editText.getText().toString();

                if(name.equals("")){
                    Toast.makeText(getContext(), "please input some character", Toast.LENGTH_SHORT).show();
                } else {
                    SQLiteDatabase db = helper.getWritableDatabase();

                    ContentValues cv = new ContentValues();

                    String oldname  = getArguments().getString("oldname").toString();

                    cv.put(KEY_NAME,name);

                    db.update(TABLE_Guest, cv, KEY_NAME +"= ?", new String[]{oldname});

                    String sql = "SELECT * FROM "+TABLE_GuestPass+" WHERE "+KEY_Gname+" = ?";

                    Cursor cursor = db.rawQuery(sql, new String[]{oldname});
                    if (cursor.getCount() > 0){
                        db.update(TABLE_GuestPass, cv, KEY_Gname +"= ?", new String[]{oldname});
                    }

                    /*
                    Intent welcome = new Intent(getActivity(), GuestStartGame.class);
                    welcome.putExtra("name", name);
                    startActivity(welcome);
                     */

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent welcome = new Intent(getActivity(), GuestStartGame.class);
                            welcome.putExtra("name", name);
                            startActivity(welcome);
                        }
                    }, 1);

                    getDialog().dismiss();
                }
            }
        });


        return view;
    }
}