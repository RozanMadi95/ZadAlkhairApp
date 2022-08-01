package com.dev.zadalkhairapp;

import com.google.android.material.textfield.TextInputEditText;

public class ReusableCodeForAll {
    public static void giveMassageError(TextInputEditText editText, String str){
        editText.setError(str);
        editText.requestFocus();
    }
}
