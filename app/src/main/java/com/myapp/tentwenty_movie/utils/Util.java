package com.myapp.tentwenty_movie.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.myapp.tentwenty_movie.R;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class Util {
    private Context context;

    public Util(Context context) {
        this.context = context;
    }

    public void showError(String msg)
    {
        BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(context, R.style.AppBottomSheetDialogTheme);
        View sheetView = LayoutInflater.from(context).inflate(R.layout.bottomsheet_layout, null);
        mBottomSheetDialog.setContentView(sheetView);
        TextView tvMessage=mBottomSheetDialog.findViewById(R.id.tvMsg);
        tvMessage.setText(msg);
        mBottomSheetDialog.show();
    }
//    public void showError(EditText editText,String msg)
//    {
//        editText.setError(msg);
//        editText.requestFocus();
//    }
    public String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static boolean isNetworkAvailable(Context context) {

        boolean flag = false;
        ConnectivityManager connMgr =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connMgr!= null) {
            NetworkInfo networkInfo= connMgr.getActiveNetworkInfo();
            flag = (networkInfo != null && networkInfo.isConnected());
        }
        return (flag);
    }
}
