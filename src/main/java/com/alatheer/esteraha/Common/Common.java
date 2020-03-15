package com.alatheer.esteraha.Common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.alatheer.esteraha.Data.remote.Models.RestModel;
import com.alatheer.esteraha.R;

import java.io.File;
import java.util.List;

public class Common {


    public static String getAppPath(Context context) {
        File dir = new File(android.os.Environment.getExternalStorageDirectory()
                +File.separator
                +context.getResources().getString(R.string.app_name)
                +File.separator);
        if(!dir.exists())
            dir.mkdir();
        return dir.getPath()+File.separator;
    }
}