package org.xms.g.utils;

import java.util.Arrays;

import android.content.Context;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

/**
 * Class for Global Environment Settings
 */
public class GlobalEnvSetting {

    private static boolean isHms;

    /**
     * org.xms.g.utils.GlobalEnvSetting.init(Context ctx, XmsLog.XmsLogInterface logImpl) init Xms Adapter<br/>
     *
     * @param ctx     Context
     * @param logImpl If you want to print the log to your own log file system, please implement {@link XmsLog.XmsLogInterface}.If you put Null logImpl, then you will use default Android Logcat to print XmsAdapter's log.
     */
    public static void init(Context ctx, XmsLog.XmsLogInterface logImpl) {
        XmsLog.newInstance(logImpl);
        boolean gAvailable = !Arrays.asList(ConnectionResult.SERVICE_DISABLED,
                ConnectionResult.SERVICE_MISSING,
                ConnectionResult.SERVICE_INVALID).contains(
                GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(ctx));
        boolean hAvailable = !Arrays.asList(com.huawei.hms.api.ConnectionResult.SERVICE_DISABLED,
                com.huawei.hms.api.ConnectionResult.SERVICE_MISSING,
                com.huawei.hms.api.ConnectionResult.SERVICE_INVALID).contains(
                com.huawei.hms.api.HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(ctx));
        //isHms = !gAvailable; // || hAvailable;
        isHms = !gAvailable; // || hAvailable;
        //isHms = hAvailable;
    }

    /**
     * org.xms.g.utils.GlobalEnvSetting.isHms() judge if the environment is hms or not.<br/>
     *
     * @return true if the environment is hms, otherwise false
     */
    public static boolean isHms() {
        return isHms;
    }

    /**
     * org.xms.g.utils.GlobalEnvSetting.useGms() Before calling this method, please make sure whether Google mobile services are supported in the runtime environment of mobile phone<br/>
     */
    public static void useGms() {
        isHms = false;
    }

    /**
     * org.xms.g.utils.GlobalEnvSetting.useHms() Before calling this method, please make sure whether Huawei mobile services are supported in the runtime environment of mobile phone<br/>
     */
    public static void useHms() {
        isHms = true;
    }
}
