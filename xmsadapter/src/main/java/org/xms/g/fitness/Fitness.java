package org.xms.g.fitness;

public class Fitness extends org.xms.g.utils.XObject {
    
    public Fitness(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public static java.lang.String getACTION_TRACK() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HuaweiHiHealth.ACTION_TRACK");
            return com.huawei.hms.hihealth.HuaweiHiHealth.ACTION_TRACK;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.Fitness.ACTION_TRACK");
            return com.google.android.gms.fitness.Fitness.ACTION_TRACK;
        }
    }
    
    public static java.lang.String getACTION_VIEW() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HuaweiHiHealth.ACTION_VIEW");
            return com.huawei.hms.hihealth.HuaweiHiHealth.ACTION_VIEW;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.Fitness.ACTION_VIEW");
            return com.google.android.gms.fitness.Fitness.ACTION_VIEW;
        }
    }
    
    public static java.lang.String getACTION_VIEW_GOAL() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HuaweiHiHealth.ACTION_VIEW_GOAL");
            return com.huawei.hms.hihealth.HuaweiHiHealth.ACTION_VIEW_GOAL;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.Fitness.ACTION_VIEW_GOAL");
            return com.google.android.gms.fitness.Fitness.ACTION_VIEW_GOAL;
        }
    }
    
    public static java.lang.String getEXTRA_END_TIME() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HuaweiHiHealth.EXTRA_END_TIME");
            return com.huawei.hms.hihealth.HuaweiHiHealth.EXTRA_END_TIME;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.Fitness.EXTRA_END_TIME");
            return com.google.android.gms.fitness.Fitness.EXTRA_END_TIME;
        }
    }
    
    public static java.lang.String getEXTRA_START_TIME() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HuaweiHiHealth.EXTRA_START_TIME");
            return com.huawei.hms.hihealth.HuaweiHiHealth.EXTRA_START_TIME;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.Fitness.EXTRA_START_TIME");
            return com.google.android.gms.fitness.Fitness.EXTRA_START_TIME;
        }
    }
    
    public static java.lang.Void getAPI() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.common.api.Api getBLE_API() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HuaweiHiHealth.BLE_MANAGER");
            com.huawei.hms.api.Api hReturn = null;
            hReturn = com.huawei.hms.hihealth.HuaweiHiHealth.BLE_MANAGER;
            return ((hReturn) == null ? null : (new org.xms.g.common.api.Api(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.Fitness.BLE_API");
            com.google.android.gms.common.api.Api gReturn = null;
            gReturn = com.google.android.gms.fitness.Fitness.BLE_API;
            return ((gReturn) == null ? null : (new org.xms.g.common.api.Api(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.BleApi getBleApi() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HuaweiHiHealth.BLEMANAGER");
            com.huawei.hms.hihealth.BleManager hReturn = null;
            hReturn = com.huawei.hms.hihealth.HuaweiHiHealth.BLEMANAGER;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.BleApi.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.Fitness.BleApi");
            com.google.android.gms.fitness.BleApi gReturn = null;
            gReturn = com.google.android.gms.fitness.Fitness.BleApi;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.BleApi.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.common.api.Api getCONFIG_API() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HuaweiHiHealth.SETTING_MANAGER");
            com.huawei.hms.api.Api hReturn = null;
            hReturn = com.huawei.hms.hihealth.HuaweiHiHealth.SETTING_MANAGER;
            return ((hReturn) == null ? null : (new org.xms.g.common.api.Api(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.Fitness.CONFIG_API");
            com.google.android.gms.common.api.Api gReturn = null;
            gReturn = com.google.android.gms.fitness.Fitness.CONFIG_API;
            return ((gReturn) == null ? null : (new org.xms.g.common.api.Api(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.ConfigApi getConfigApi() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HuaweiHiHealth.SETTINGMANAGER");
            com.huawei.hms.hihealth.SettingManager hReturn = null;
            hReturn = com.huawei.hms.hihealth.HuaweiHiHealth.SETTINGMANAGER;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.ConfigApi.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.Fitness.ConfigApi");
            com.google.android.gms.fitness.ConfigApi gReturn = null;
            gReturn = com.google.android.gms.fitness.Fitness.ConfigApi;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.ConfigApi.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.common.api.Api getGOALS_API() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.GoalsApi getGoalsApi() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.common.api.Api getHISTORY_API() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HuaweiHiHealth.DATA_MANAGER");
            com.huawei.hms.api.Api hReturn = null;
            hReturn = com.huawei.hms.hihealth.HuaweiHiHealth.DATA_MANAGER;
            return ((hReturn) == null ? null : (new org.xms.g.common.api.Api(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.Fitness.HISTORY_API");
            com.google.android.gms.common.api.Api gReturn = null;
            gReturn = com.google.android.gms.fitness.Fitness.HISTORY_API;
            return ((gReturn) == null ? null : (new org.xms.g.common.api.Api(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.HistoryApi getHistoryApi() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HuaweiHiHealth.DATAMANAGER");
            com.huawei.hms.hihealth.DataManager hReturn = null;
            hReturn = com.huawei.hms.hihealth.HuaweiHiHealth.DATAMANAGER;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.HistoryApi.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.Fitness.HistoryApi");
            com.google.android.gms.fitness.HistoryApi gReturn = null;
            gReturn = com.google.android.gms.fitness.Fitness.HistoryApi;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.HistoryApi.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.common.api.Api getRECORDING_API() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HuaweiHiHealth.AUTORECORDER_MANAGER");
            com.huawei.hms.api.Api hReturn = null;
            hReturn = com.huawei.hms.hihealth.HuaweiHiHealth.AUTORECORDER_MANAGER;
            return ((hReturn) == null ? null : (new org.xms.g.common.api.Api(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.Fitness.RECORDING_API");
            com.google.android.gms.common.api.Api gReturn = null;
            gReturn = com.google.android.gms.fitness.Fitness.RECORDING_API;
            return ((gReturn) == null ? null : (new org.xms.g.common.api.Api(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.RecordingApi getRecordingApi() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HuaweiHiHealth.AUTORECORDERMANAGER");
            com.huawei.hms.hihealth.AutoRecorderManager hReturn = null;
            hReturn = com.huawei.hms.hihealth.HuaweiHiHealth.AUTORECORDERMANAGER;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.RecordingApi.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.Fitness.RecordingApi");
            com.google.android.gms.fitness.RecordingApi gReturn = null;
            gReturn = com.google.android.gms.fitness.Fitness.RecordingApi;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.RecordingApi.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.common.api.Scope getSCOPE_ACTIVITY_READ() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.common.api.Scope getSCOPE_ACTIVITY_READ_WRITE() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.common.api.Scope getSCOPE_BODY_READ() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.common.api.Scope getSCOPE_BODY_READ_WRITE() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.common.api.Scope getSCOPE_LOCATION_READ() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.common.api.Scope getSCOPE_LOCATION_READ_WRITE() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.common.api.Scope getSCOPE_NUTRITION_READ() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.common.api.Scope getSCOPE_NUTRITION_READ_WRITE() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.common.api.Api getSENSORS_API() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HuaweiHiHealth.SENSORS_MANAGER");
            com.huawei.hms.api.Api hReturn = null;
            hReturn = com.huawei.hms.hihealth.HuaweiHiHealth.SENSORS_MANAGER;
            return ((hReturn) == null ? null : (new org.xms.g.common.api.Api(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.Fitness.SENSORS_API");
            com.google.android.gms.common.api.Api gReturn = null;
            gReturn = com.google.android.gms.fitness.Fitness.SENSORS_API;
            return ((gReturn) == null ? null : (new org.xms.g.common.api.Api(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.common.api.Api getSESSIONS_API() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HuaweiHiHealth.ACTIVITY_RECORD_MANAGER");
            com.huawei.hms.api.Api hReturn = null;
            hReturn = com.huawei.hms.hihealth.HuaweiHiHealth.ACTIVITY_RECORD_MANAGER;
            return ((hReturn) == null ? null : (new org.xms.g.common.api.Api(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.Fitness.SESSIONS_API");
            com.google.android.gms.common.api.Api gReturn = null;
            gReturn = com.google.android.gms.fitness.Fitness.SESSIONS_API;
            return ((gReturn) == null ? null : (new org.xms.g.common.api.Api(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.SensorsApi getSensorsApi() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HuaweiHiHealth.SENSORSMANAGER");
            com.huawei.hms.hihealth.SensorsManager hReturn = null;
            hReturn = com.huawei.hms.hihealth.HuaweiHiHealth.SENSORSMANAGER;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.SensorsApi.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.Fitness.SensorsApi");
            com.google.android.gms.fitness.SensorsApi gReturn = null;
            gReturn = com.google.android.gms.fitness.Fitness.SensorsApi;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.SensorsApi.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.SessionsApi getSessionsApi() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HuaweiHiHealth.ACTIVITYRECORDSMANAGER");
            com.huawei.hms.hihealth.ActivityRecordsManager hReturn = null;
            hReturn = com.huawei.hms.hihealth.HuaweiHiHealth.ACTIVITYRECORDSMANAGER;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.SessionsApi.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.Fitness.SessionsApi");
            com.google.android.gms.fitness.SessionsApi gReturn = null;
            gReturn = com.google.android.gms.fitness.Fitness.SessionsApi;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.SessionsApi.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.BleClient getBleClient(android.app.Activity param0, org.xms.g.auth.api.signin.ExtensionSignInAccount param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HuaweiHiHealth.getBleController(param0, ((com.huawei.hms.support.hwid.result.AuthHuaweiId) ((param1) == null ? null : (param1.getHInstance()))))");
            com.huawei.hms.hihealth.BleController hReturn = com.huawei.hms.hihealth.HuaweiHiHealth.getBleController(param0, ((com.huawei.hms.support.hwid.result.AuthHuaweiId) ((param1) == null ? null : (param1.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.fitness.BleClient(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.Fitness.getBleClient(param0, ((com.google.android.gms.auth.api.signin.GoogleSignInAccount) ((param1) == null ? null : (param1.getGInstance()))))");
            com.google.android.gms.fitness.BleClient gReturn = com.google.android.gms.fitness.Fitness.getBleClient(param0, ((com.google.android.gms.auth.api.signin.GoogleSignInAccount) ((param1) == null ? null : (param1.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.fitness.BleClient(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.BleClient getBleClient(android.content.Context param0, org.xms.g.auth.api.signin.ExtensionSignInAccount param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HuaweiHiHealth.getBleController(param0, ((com.huawei.hms.support.hwid.result.AuthHuaweiId) ((param1) == null ? null : (param1.getHInstance()))))");
            com.huawei.hms.hihealth.BleController hReturn = com.huawei.hms.hihealth.HuaweiHiHealth.getBleController(param0, ((com.huawei.hms.support.hwid.result.AuthHuaweiId) ((param1) == null ? null : (param1.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.fitness.BleClient(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.Fitness.getBleClient(param0, ((com.google.android.gms.auth.api.signin.GoogleSignInAccount) ((param1) == null ? null : (param1.getGInstance()))))");
            com.google.android.gms.fitness.BleClient gReturn = com.google.android.gms.fitness.Fitness.getBleClient(param0, ((com.google.android.gms.auth.api.signin.GoogleSignInAccount) ((param1) == null ? null : (param1.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.fitness.BleClient(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.ConfigClient getConfigClient(android.content.Context param0, org.xms.g.auth.api.signin.ExtensionSignInAccount param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HuaweiHiHealth.getSettingController(param0, ((com.huawei.hms.support.hwid.result.AuthHuaweiId) ((param1) == null ? null : (param1.getHInstance()))))");
            com.huawei.hms.hihealth.SettingController hReturn = com.huawei.hms.hihealth.HuaweiHiHealth.getSettingController(param0, ((com.huawei.hms.support.hwid.result.AuthHuaweiId) ((param1) == null ? null : (param1.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.fitness.ConfigClient(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.Fitness.getConfigClient(param0, ((com.google.android.gms.auth.api.signin.GoogleSignInAccount) ((param1) == null ? null : (param1.getGInstance()))))");
            com.google.android.gms.fitness.ConfigClient gReturn = com.google.android.gms.fitness.Fitness.getConfigClient(param0, ((com.google.android.gms.auth.api.signin.GoogleSignInAccount) ((param1) == null ? null : (param1.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.fitness.ConfigClient(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.ConfigClient getConfigClient(android.app.Activity param0, org.xms.g.auth.api.signin.ExtensionSignInAccount param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HuaweiHiHealth.getSettingController(param0, ((com.huawei.hms.support.hwid.result.AuthHuaweiId) ((param1) == null ? null : (param1.getHInstance()))))");
            com.huawei.hms.hihealth.SettingController hReturn = com.huawei.hms.hihealth.HuaweiHiHealth.getSettingController(param0, ((com.huawei.hms.support.hwid.result.AuthHuaweiId) ((param1) == null ? null : (param1.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.fitness.ConfigClient(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.Fitness.getConfigClient(param0, ((com.google.android.gms.auth.api.signin.GoogleSignInAccount) ((param1) == null ? null : (param1.getGInstance()))))");
            com.google.android.gms.fitness.ConfigClient gReturn = com.google.android.gms.fitness.Fitness.getConfigClient(param0, ((com.google.android.gms.auth.api.signin.GoogleSignInAccount) ((param1) == null ? null : (param1.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.fitness.ConfigClient(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static long getEndTime(android.content.Intent param0, java.util.concurrent.TimeUnit param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HuaweiHiHealth.getEndTime(param0, param1)");
            return com.huawei.hms.hihealth.HuaweiHiHealth.getEndTime(param0, param1);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.Fitness.getEndTime(param0, param1)");
            return com.google.android.gms.fitness.Fitness.getEndTime(param0, param1);
        }
    }
    
    public static org.xms.g.fitness.GoalsClient getGoalsClient(android.content.Context param0, org.xms.g.auth.api.signin.ExtensionSignInAccount param1) {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.GoalsClient getGoalsClient(android.app.Activity param0, org.xms.g.auth.api.signin.ExtensionSignInAccount param1) {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.HistoryClient getHistoryClient(android.app.Activity param0, org.xms.g.auth.api.signin.ExtensionSignInAccount param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HuaweiHiHealth.getDataController(param0, ((com.huawei.hms.support.hwid.result.AuthHuaweiId) ((param1) == null ? null : (param1.getHInstance()))))");
            com.huawei.hms.hihealth.DataController hReturn = com.huawei.hms.hihealth.HuaweiHiHealth.getDataController(param0, ((com.huawei.hms.support.hwid.result.AuthHuaweiId) ((param1) == null ? null : (param1.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.fitness.HistoryClient(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.Fitness.getHistoryClient(param0, ((com.google.android.gms.auth.api.signin.GoogleSignInAccount) ((param1) == null ? null : (param1.getGInstance()))))");
            com.google.android.gms.fitness.HistoryClient gReturn = com.google.android.gms.fitness.Fitness.getHistoryClient(param0, ((com.google.android.gms.auth.api.signin.GoogleSignInAccount) ((param1) == null ? null : (param1.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.fitness.HistoryClient(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.HistoryClient getHistoryClient(android.content.Context param0, org.xms.g.auth.api.signin.ExtensionSignInAccount param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HuaweiHiHealth.getDataController(param0, ((com.huawei.hms.support.hwid.result.AuthHuaweiId) ((param1) == null ? null : (param1.getHInstance()))))");
            com.huawei.hms.hihealth.DataController hReturn = com.huawei.hms.hihealth.HuaweiHiHealth.getDataController(param0, ((com.huawei.hms.support.hwid.result.AuthHuaweiId) ((param1) == null ? null : (param1.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.fitness.HistoryClient(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.Fitness.getHistoryClient(param0, ((com.google.android.gms.auth.api.signin.GoogleSignInAccount) ((param1) == null ? null : (param1.getGInstance()))))");
            com.google.android.gms.fitness.HistoryClient gReturn = com.google.android.gms.fitness.Fitness.getHistoryClient(param0, ((com.google.android.gms.auth.api.signin.GoogleSignInAccount) ((param1) == null ? null : (param1.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.fitness.HistoryClient(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.RecordingClient getRecordingClient(android.app.Activity param0, org.xms.g.auth.api.signin.ExtensionSignInAccount param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HuaweiHiHealth.getAutoRecorderController(param0, ((com.huawei.hms.support.hwid.result.AuthHuaweiId) ((param1) == null ? null : (param1.getHInstance()))))");
            com.huawei.hms.hihealth.AutoRecorderController hReturn = com.huawei.hms.hihealth.HuaweiHiHealth.getAutoRecorderController(param0, ((com.huawei.hms.support.hwid.result.AuthHuaweiId) ((param1) == null ? null : (param1.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.fitness.RecordingClient(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.Fitness.getRecordingClient(param0, ((com.google.android.gms.auth.api.signin.GoogleSignInAccount) ((param1) == null ? null : (param1.getGInstance()))))");
            com.google.android.gms.fitness.RecordingClient gReturn = com.google.android.gms.fitness.Fitness.getRecordingClient(param0, ((com.google.android.gms.auth.api.signin.GoogleSignInAccount) ((param1) == null ? null : (param1.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.fitness.RecordingClient(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.RecordingClient getRecordingClient(android.content.Context param0, org.xms.g.auth.api.signin.ExtensionSignInAccount param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HuaweiHiHealth.getAutoRecorderController(param0, ((com.huawei.hms.support.hwid.result.AuthHuaweiId) ((param1) == null ? null : (param1.getHInstance()))))");
            com.huawei.hms.hihealth.AutoRecorderController hReturn = com.huawei.hms.hihealth.HuaweiHiHealth.getAutoRecorderController(param0, ((com.huawei.hms.support.hwid.result.AuthHuaweiId) ((param1) == null ? null : (param1.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.fitness.RecordingClient(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.Fitness.getRecordingClient(param0, ((com.google.android.gms.auth.api.signin.GoogleSignInAccount) ((param1) == null ? null : (param1.getGInstance()))))");
            com.google.android.gms.fitness.RecordingClient gReturn = com.google.android.gms.fitness.Fitness.getRecordingClient(param0, ((com.google.android.gms.auth.api.signin.GoogleSignInAccount) ((param1) == null ? null : (param1.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.fitness.RecordingClient(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.SensorsClient getSensorsClient(android.app.Activity param0, org.xms.g.auth.api.signin.ExtensionSignInAccount param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HuaweiHiHealth.getSensorsController(param0, ((com.huawei.hms.support.hwid.result.AuthHuaweiId) ((param1) == null ? null : (param1.getHInstance()))))");
            com.huawei.hms.hihealth.SensorsController hReturn = com.huawei.hms.hihealth.HuaweiHiHealth.getSensorsController(param0, ((com.huawei.hms.support.hwid.result.AuthHuaweiId) ((param1) == null ? null : (param1.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.fitness.SensorsClient(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.Fitness.getSensorsClient(param0, ((com.google.android.gms.auth.api.signin.GoogleSignInAccount) ((param1) == null ? null : (param1.getGInstance()))))");
            com.google.android.gms.fitness.SensorsClient gReturn = com.google.android.gms.fitness.Fitness.getSensorsClient(param0, ((com.google.android.gms.auth.api.signin.GoogleSignInAccount) ((param1) == null ? null : (param1.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.fitness.SensorsClient(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.SensorsClient getSensorsClient(android.content.Context param0, org.xms.g.auth.api.signin.ExtensionSignInAccount param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HuaweiHiHealth.getSensorsController(param0, ((com.huawei.hms.support.hwid.result.AuthHuaweiId) ((param1) == null ? null : (param1.getHInstance()))))");
            com.huawei.hms.hihealth.SensorsController hReturn = com.huawei.hms.hihealth.HuaweiHiHealth.getSensorsController(param0, ((com.huawei.hms.support.hwid.result.AuthHuaweiId) ((param1) == null ? null : (param1.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.fitness.SensorsClient(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.Fitness.getSensorsClient(param0, ((com.google.android.gms.auth.api.signin.GoogleSignInAccount) ((param1) == null ? null : (param1.getGInstance()))))");
            com.google.android.gms.fitness.SensorsClient gReturn = com.google.android.gms.fitness.Fitness.getSensorsClient(param0, ((com.google.android.gms.auth.api.signin.GoogleSignInAccount) ((param1) == null ? null : (param1.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.fitness.SensorsClient(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.SessionsClient getSessionsClient(android.app.Activity param0, org.xms.g.auth.api.signin.ExtensionSignInAccount param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HuaweiHiHealth.getActivityRecordsController(param0, ((com.huawei.hms.support.hwid.result.AuthHuaweiId) ((param1) == null ? null : (param1.getHInstance()))))");
            com.huawei.hms.hihealth.ActivityRecordsController hReturn = com.huawei.hms.hihealth.HuaweiHiHealth.getActivityRecordsController(param0, ((com.huawei.hms.support.hwid.result.AuthHuaweiId) ((param1) == null ? null : (param1.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.fitness.SessionsClient(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.Fitness.getSessionsClient(param0, ((com.google.android.gms.auth.api.signin.GoogleSignInAccount) ((param1) == null ? null : (param1.getGInstance()))))");
            com.google.android.gms.fitness.SessionsClient gReturn = com.google.android.gms.fitness.Fitness.getSessionsClient(param0, ((com.google.android.gms.auth.api.signin.GoogleSignInAccount) ((param1) == null ? null : (param1.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.fitness.SessionsClient(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.SessionsClient getSessionsClient(android.content.Context param0, org.xms.g.auth.api.signin.ExtensionSignInAccount param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HuaweiHiHealth.getActivityRecordsController(param0, ((com.huawei.hms.support.hwid.result.AuthHuaweiId) ((param1) == null ? null : (param1.getHInstance()))))");
            com.huawei.hms.hihealth.ActivityRecordsController hReturn = com.huawei.hms.hihealth.HuaweiHiHealth.getActivityRecordsController(param0, ((com.huawei.hms.support.hwid.result.AuthHuaweiId) ((param1) == null ? null : (param1.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.fitness.SessionsClient(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.Fitness.getSessionsClient(param0, ((com.google.android.gms.auth.api.signin.GoogleSignInAccount) ((param1) == null ? null : (param1.getGInstance()))))");
            com.google.android.gms.fitness.SessionsClient gReturn = com.google.android.gms.fitness.Fitness.getSessionsClient(param0, ((com.google.android.gms.auth.api.signin.GoogleSignInAccount) ((param1) == null ? null : (param1.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.fitness.SessionsClient(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static long getStartTime(android.content.Intent param0, java.util.concurrent.TimeUnit param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HuaweiHiHealth.getStartTime(param0, param1)");
            return com.huawei.hms.hihealth.HuaweiHiHealth.getStartTime(param0, param1);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.Fitness.getStartTime(param0, param1)");
            return com.google.android.gms.fitness.Fitness.getStartTime(param0, param1);
        }
    }
    
    public static org.xms.g.fitness.Fitness dynamicCast(java.lang.Object param0) {
        return ((org.xms.g.fitness.Fitness) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.HuaweiHiHealth;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.Fitness;
        }
    }
}