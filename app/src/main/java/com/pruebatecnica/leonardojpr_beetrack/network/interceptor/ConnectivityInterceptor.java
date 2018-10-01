package com.pruebatecnica.leonardojpr_beetrack.network.interceptor;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.pruebatecnica.leonardojpr_beetrack.network.interceptor.Exception.NoConnectivityException;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
/**
 * Created by Leonardojpr on 06/05/18.
 */
public class ConnectivityInterceptor implements Interceptor {
 
    private Context mContext;
 
    public ConnectivityInterceptor(Context context) {
        mContext = context;
    }
 
    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!isOnline(mContext)) {
            throw new NoConnectivityException();
        }
 
        Request.Builder builder = chain.request().newBuilder();
        return chain.proceed(builder.build());
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }
}