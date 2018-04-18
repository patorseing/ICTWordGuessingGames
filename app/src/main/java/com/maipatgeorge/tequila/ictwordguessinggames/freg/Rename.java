package com.maipatgeorge.tequila.ictwordguessinggames.freg;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maipatgeorge.tequila.ictwordguessinggames.R;

public class Rename extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_open, container, false);

        RenameDialogsFreg dialogs = new RenameDialogsFreg();
        dialogs.show(getFragmentManager(), "MyCustomerDialogs");

        return view;
    }
}
