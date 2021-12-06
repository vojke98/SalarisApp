package com.example.salaris.helper;

import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

public class GenericOnKeyListener implements View.OnKeyListener {
    private EditText etPrev;
    private EditText etCurrent;

    public GenericOnKeyListener(EditText etPrev, EditText etCurrent) {
        this.etPrev = etPrev;
        this.etCurrent = etCurrent;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DEL && etCurrent.getText().length() == 0) {
            etPrev.requestFocus();
            System.out.println("radiii");
        }
        return true;
    }
}
