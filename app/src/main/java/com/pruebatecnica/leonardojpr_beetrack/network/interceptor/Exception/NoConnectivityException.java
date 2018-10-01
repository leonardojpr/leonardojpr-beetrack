package com.pruebatecnica.leonardojpr_beetrack.network.interceptor.Exception;

import com.pruebatecnica.leonardojpr_beetrack.R;
import com.pruebatecnica.leonardojpr_beetrack.util.Commons;

import java.io.IOException;
/**
 * Created by Leonardojpr on 06/05/18.
 */
public class NoConnectivityException extends IOException {

    @Override
    public String getMessage() {
        return Commons.getString(R.string.connectivity_exception);
    }

}