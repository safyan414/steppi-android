package org.xms.g.fitness;

public class FitnessStatusCodes extends org.xms.g.common.api.CommonStatusCodes {
    private boolean wrapper = true;
    
    public FitnessStatusCodes(org.xms.g.utils.XBox param0) {
        super(param0);
        wrapper = true;
    }
    
    public FitnessStatusCodes() {
        super(((org.xms.g.utils.XBox) null));
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            this.setHInstance(new HImpl());
        } else {
            this.setGInstance(new GImpl());
        }
        wrapper = false;
    }
    
    public static int getAGGREGATION_NOT_SUPPORTED() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthStatusCodes.POLYMERIZED_NOT_SUPPORTED_ERROR");
            return com.huawei.hms.hihealth.HiHealthStatusCodes.POLYMERIZED_NOT_SUPPORTED_ERROR;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessStatusCodes.AGGREGATION_NOT_SUPPORTED");
            return com.google.android.gms.fitness.FitnessStatusCodes.AGGREGATION_NOT_SUPPORTED;
        }
    }
    
    public static int getAPI_EXCEPTION() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthStatusCodes.API_EXCEPTION_ERROR");
            return com.huawei.hms.hihealth.HiHealthStatusCodes.API_EXCEPTION_ERROR;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessStatusCodes.API_EXCEPTION");
            return com.google.android.gms.fitness.FitnessStatusCodes.API_EXCEPTION;
        }
    }
    
    public static int getAPP_MISMATCH() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthStatusCodes.APP_MISMATCH_ERROR");
            return com.huawei.hms.hihealth.HiHealthStatusCodes.APP_MISMATCH_ERROR;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessStatusCodes.APP_MISMATCH");
            return com.google.android.gms.fitness.FitnessStatusCodes.APP_MISMATCH;
        }
    }
    
    public static int getAPP_NOT_FIT_ENABLED() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthStatusCodes.APP_NOT_ENABLED_ERROR");
            return com.huawei.hms.hihealth.HiHealthStatusCodes.APP_NOT_ENABLED_ERROR;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessStatusCodes.APP_NOT_FIT_ENABLED");
            return com.google.android.gms.fitness.FitnessStatusCodes.APP_NOT_FIT_ENABLED;
        }
    }
    
    public static int getCONFLICTING_DATA_TYPE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthStatusCodes.DUPLICATED_DATA_TYPE_ERROR");
            return com.huawei.hms.hihealth.HiHealthStatusCodes.DUPLICATED_DATA_TYPE_ERROR;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessStatusCodes.CONFLICTING_DATA_TYPE");
            return com.google.android.gms.fitness.FitnessStatusCodes.CONFLICTING_DATA_TYPE;
        }
    }
    
    public static int getDATASET_TIMESTAMP_INCONSISTENT_WITH_UPDATE_TIME_RANGE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthStatusCodes.SAMPLE_SET_TIMESTAMP_ERROR");
            return com.huawei.hms.hihealth.HiHealthStatusCodes.SAMPLE_SET_TIMESTAMP_ERROR;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessStatusCodes.DATASET_TIMESTAMP_INCONSISTENT_WITH_UPDATE_TIME_RANGE");
            return com.google.android.gms.fitness.FitnessStatusCodes.DATASET_TIMESTAMP_INCONSISTENT_WITH_UPDATE_TIME_RANGE;
        }
    }
    
    public static int getDATA_SOURCE_NOT_FOUND() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthStatusCodes.NONEXISTENT_DATA_COLLECTOR_ERROR");
            return com.huawei.hms.hihealth.HiHealthStatusCodes.NONEXISTENT_DATA_COLLECTOR_ERROR;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessStatusCodes.DATA_SOURCE_NOT_FOUND");
            return com.google.android.gms.fitness.FitnessStatusCodes.DATA_SOURCE_NOT_FOUND;
        }
    }
    
    public static int getDATA_TYPE_NOT_ALLOWED_FOR_API() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthStatusCodes.UNSUPPORTED_DATA_TYPE_ERROR");
            return com.huawei.hms.hihealth.HiHealthStatusCodes.UNSUPPORTED_DATA_TYPE_ERROR;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessStatusCodes.DATA_TYPE_NOT_ALLOWED_FOR_API");
            return com.google.android.gms.fitness.FitnessStatusCodes.DATA_TYPE_NOT_ALLOWED_FOR_API;
        }
    }
    
    public static int getDATA_TYPE_NOT_FOUND() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthStatusCodes.NOT_EXIST_DATA_TYPE_ERROR");
            return com.huawei.hms.hihealth.HiHealthStatusCodes.NOT_EXIST_DATA_TYPE_ERROR;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessStatusCodes.DATA_TYPE_NOT_FOUND");
            return com.google.android.gms.fitness.FitnessStatusCodes.DATA_TYPE_NOT_FOUND;
        }
    }
    
    public static int getDISABLED_BLUETOOTH() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthStatusCodes.BLUETOOTH_FORBIDDEN_ERROR");
            return com.huawei.hms.hihealth.HiHealthStatusCodes.BLUETOOTH_FORBIDDEN_ERROR;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessStatusCodes.DISABLED_BLUETOOTH");
            return com.google.android.gms.fitness.FitnessStatusCodes.DISABLED_BLUETOOTH;
        }
    }
    
    public static int getEQUIVALENT_SESSION_ENDED() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthStatusCodes.ACTIVITY_RECORD_ENDED_ERROR");
            return com.huawei.hms.hihealth.HiHealthStatusCodes.ACTIVITY_RECORD_ENDED_ERROR;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessStatusCodes.EQUIVALENT_SESSION_ENDED");
            return com.google.android.gms.fitness.FitnessStatusCodes.EQUIVALENT_SESSION_ENDED;
        }
    }
    
    public static int getINCONSISTENT_DATA_TYPE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthStatusCodes.MISMATCH_DATA_TYPE_ERROR");
            return com.huawei.hms.hihealth.HiHealthStatusCodes.MISMATCH_DATA_TYPE_ERROR;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessStatusCodes.INCONSISTENT_DATA_TYPE");
            return com.google.android.gms.fitness.FitnessStatusCodes.INCONSISTENT_DATA_TYPE;
        }
    }
    
    public static int getINCONSISTENT_PACKAGE_NAME() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthStatusCodes.MISMATCH_PACKAGE_NAME_ERROR");
            return com.huawei.hms.hihealth.HiHealthStatusCodes.MISMATCH_PACKAGE_NAME_ERROR;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessStatusCodes.INCONSISTENT_PACKAGE_NAME");
            return com.google.android.gms.fitness.FitnessStatusCodes.INCONSISTENT_PACKAGE_NAME;
        }
    }
    
    public static int getINVALID_DATA_POINT() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthStatusCodes.USELESS_SAMPLE_POINT_ERROR");
            return com.huawei.hms.hihealth.HiHealthStatusCodes.USELESS_SAMPLE_POINT_ERROR;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessStatusCodes.INVALID_DATA_POINT");
            return com.google.android.gms.fitness.FitnessStatusCodes.INVALID_DATA_POINT;
        }
    }
    
    public static int getINVALID_SESSION_TIMESTAMPS() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthStatusCodes.ACTIVITY_RECORD_TIMESTAMPS_ERROR");
            return com.huawei.hms.hihealth.HiHealthStatusCodes.ACTIVITY_RECORD_TIMESTAMPS_ERROR;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessStatusCodes.INVALID_SESSION_TIMESTAMPS");
            return com.google.android.gms.fitness.FitnessStatusCodes.INVALID_SESSION_TIMESTAMPS;
        }
    }
    
    public static int getINVALID_TIMESTAMP() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthStatusCodes.USELESS_TIMESTAMP_ERROR");
            return com.huawei.hms.hihealth.HiHealthStatusCodes.USELESS_TIMESTAMP_ERROR;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessStatusCodes.INVALID_TIMESTAMP");
            return com.google.android.gms.fitness.FitnessStatusCodes.INVALID_TIMESTAMP;
        }
    }
    
    public static int getMISSING_BLE_PERMISSION() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthStatusCodes.NO_BLE_PERMISSION_ERROR");
            return com.huawei.hms.hihealth.HiHealthStatusCodes.NO_BLE_PERMISSION_ERROR;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessStatusCodes.MISSING_BLE_PERMISSION");
            return com.google.android.gms.fitness.FitnessStatusCodes.MISSING_BLE_PERMISSION;
        }
    }
    
    public static int getNEEDS_OAUTH_PERMISSIONS() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthStatusCodes.NO_AUTHORITY_ERROR");
            return com.huawei.hms.hihealth.HiHealthStatusCodes.NO_AUTHORITY_ERROR;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessStatusCodes.NEEDS_OAUTH_PERMISSIONS");
            return com.google.android.gms.fitness.FitnessStatusCodes.NEEDS_OAUTH_PERMISSIONS;
        }
    }
    
    public static int getREQUIRES_APP_WHITELISTING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthStatusCodes.NEED_APP_TRUSTLIST_ERROR");
            return com.huawei.hms.hihealth.HiHealthStatusCodes.NEED_APP_TRUSTLIST_ERROR;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessStatusCodes.REQUIRES_APP_WHITELISTING");
            return com.google.android.gms.fitness.FitnessStatusCodes.REQUIRES_APP_WHITELISTING;
        }
    }
    
    public static int getSUCCESS_ALREADY_SUBSCRIBED() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthStatusCodes.EXISTED_RECORDER_ERROR");
            return com.huawei.hms.hihealth.HiHealthStatusCodes.EXISTED_RECORDER_ERROR;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessStatusCodes.SUCCESS_ALREADY_SUBSCRIBED");
            return com.google.android.gms.fitness.FitnessStatusCodes.SUCCESS_ALREADY_SUBSCRIBED;
        }
    }
    
    public static int getSUCCESS_LISTENER_NOT_REGISTERED_FOR_FITNESS_DATA_UPDATES() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthStatusCodes.NO_MONITOR_ERROR");
            return com.huawei.hms.hihealth.HiHealthStatusCodes.NO_MONITOR_ERROR;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessStatusCodes.SUCCESS_LISTENER_NOT_REGISTERED_FOR_FITNESS_DATA_UPDATES");
            return com.google.android.gms.fitness.FitnessStatusCodes.SUCCESS_LISTENER_NOT_REGISTERED_FOR_FITNESS_DATA_UPDATES;
        }
    }
    
    public static int getSUCCESS_NO_CLAIMED_DEVICE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthStatusCodes.NO_SAVED_DEVICE_ERROR");
            return com.huawei.hms.hihealth.HiHealthStatusCodes.NO_SAVED_DEVICE_ERROR;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessStatusCodes.SUCCESS_NO_CLAIMED_DEVICE");
            return com.google.android.gms.fitness.FitnessStatusCodes.SUCCESS_NO_CLAIMED_DEVICE;
        }
    }
    
    public static int getSUCCESS_NO_DATA_SOURCES() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthStatusCodes.NO_DATA_COLLECTOR_ERROR");
            return com.huawei.hms.hihealth.HiHealthStatusCodes.NO_DATA_COLLECTOR_ERROR;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessStatusCodes.SUCCESS_NO_DATA_SOURCES");
            return com.google.android.gms.fitness.FitnessStatusCodes.SUCCESS_NO_DATA_SOURCES;
        }
    }
    
    public static int getTRANSIENT_ERROR() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthStatusCodes.TRY_AGAIN_ERROR");
            return com.huawei.hms.hihealth.HiHealthStatusCodes.TRY_AGAIN_ERROR;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessStatusCodes.TRANSIENT_ERROR");
            return com.google.android.gms.fitness.FitnessStatusCodes.TRANSIENT_ERROR;
        }
    }
    
    public static int getUNKNOWN_AUTH_ERROR() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthStatusCodes.UNKNOWN_AUTH_ERROR");
            return com.huawei.hms.hihealth.HiHealthStatusCodes.UNKNOWN_AUTH_ERROR;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessStatusCodes.UNKNOWN_AUTH_ERROR");
            return com.google.android.gms.fitness.FitnessStatusCodes.UNKNOWN_AUTH_ERROR;
        }
    }
    
    public static int getUNSUPPORTED_ACCOUNT() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthStatusCodes.UNSUPPORTED_ACCOUNT_ERROR");
            return com.huawei.hms.hihealth.HiHealthStatusCodes.UNSUPPORTED_ACCOUNT_ERROR;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessStatusCodes.UNSUPPORTED_ACCOUNT");
            return com.google.android.gms.fitness.FitnessStatusCodes.UNSUPPORTED_ACCOUNT;
        }
    }
    
    public static int getUNSUPPORTED_PLATFORM() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthStatusCodes.UNSUPPORTED_PLATFORM_ERROR");
            return com.huawei.hms.hihealth.HiHealthStatusCodes.UNSUPPORTED_PLATFORM_ERROR;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessStatusCodes.UNSUPPORTED_PLATFORM");
            return com.google.android.gms.fitness.FitnessStatusCodes.UNSUPPORTED_PLATFORM;
        }
    }
    
    public static org.xms.g.fitness.FitnessStatusCodes dynamicCast(java.lang.Object param0) {
        return ((org.xms.g.fitness.FitnessStatusCodes) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.HiHealthStatusCodes;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.FitnessStatusCodes;
        }
    }
    
    private class GImpl extends com.google.android.gms.fitness.FitnessStatusCodes {
        
        public GImpl() {
            super();
        }
    }
    
    private class HImpl extends com.huawei.hms.hihealth.HiHealthStatusCodes {
        
        public HImpl() {
            super();
        }
    }
}