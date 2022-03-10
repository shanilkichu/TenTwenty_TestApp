package com.myapp.tentwenty_movie.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import com.myapp.tentwenty_movie.R;


public class CustomDialog extends Dialog {

    public CustomDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_progress);

        /* Custom setting to change TextView text,Color and Text Size according to your Preference*/

        TextView progressTv = findViewById(R.id.progress_tv);
        progressTv.setText("Loading");
        progressTv.setTextColor(ContextCompat.getColor(context, R.color.bottom_nav_color));
        if(getWindow() != null)
            getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

       setCancelable(false);
    }

}
