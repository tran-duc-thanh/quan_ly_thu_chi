package com.example.quan_ly_thu_chi.utils;

import android.content.Context;

import androidx.appcompat.app.AlertDialog.Builder;

public class DialogUtils {
    public static Builder createAlertDialog (Context context, String title, String message, int iconId) {
        Builder builder = new Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setIcon(iconId);
        return builder;
    }
}
