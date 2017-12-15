package com.example.android.blendin.Fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.android.blendin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {
    RelativeLayout changePass, language;
    View v;
    CharSequence[] items = {"English", "Arabic"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_settings, container, false);
        init();
        return v;
    }

    public void init() {
        changePass = (RelativeLayout) v.findViewById(R.id.rl_changePassword_setting);
        language = (RelativeLayout) v.findViewById(R.id.rl_language_settings);

        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ChangePasswordFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.enter_left, R.anim.exit_right, R.anim.enter_right, R.anim.exit_left);
                fragmentTransaction.add(R.id.content_main, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setSingleChoiceItems(items, 0, null)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                                int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                                Toast.makeText(getActivity(), items[selectedPosition], Toast.LENGTH_SHORT).show();
                                // Do something useful withe the position of the selected radio button
                            }
                        })
                        .show();
            }
        });
    }

}
