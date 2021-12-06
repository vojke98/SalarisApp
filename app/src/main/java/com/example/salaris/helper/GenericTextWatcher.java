package com.example.salaris.helper;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class GenericTextWatcher implements TextWatcher {
    private EditText etCurrent, etNext;

    public GenericTextWatcher(EditText etCurrent, EditText etNext) {
        this.etCurrent = etCurrent;
        this.etNext = etNext;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        String text = editable.toString();
        if (text.length() == 1) etNext.requestFocus();
        else if (text.length() > 1) {
            etCurrent.setText("" + text.charAt(1));
            etCurrent.setSelection(1);
        }
    }
}
