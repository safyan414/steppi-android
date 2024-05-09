package org.xms.g.fitness;

public class ConfigClient extends org.xms.g.common.api.ExtensionApi {
    
    public ConfigClient(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public org.xms.g.tasks.Task<org.xms.g.fitness.data.DataType> createCustomDataType(org.xms.g.fitness.request.DataTypeCreateRequest param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.SettingController) this.getHInstance()).addDataType(((com.huawei.hms.hihealth.options.DataTypeAddOptions) ((param0) == null ? null : (param0.getHInstance()))))");
            com.huawei.hmf.tasks.Task hReturn = ((com.huawei.hms.hihealth.SettingController) this.getHInstance()).addDataType(((com.huawei.hms.hihealth.options.DataTypeAddOptions) ((param0) == null ? null : (param0.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.ConfigClient) this.getGInstance()).createCustomDataType(((com.google.android.gms.fitness.request.DataTypeCreateRequest) ((param0) == null ? null : (param0.getGInstance()))))");
            com.google.android.gms.tasks.Task gReturn = ((com.google.android.gms.fitness.ConfigClient) this.getGInstance()).createCustomDataType(((com.google.android.gms.fitness.request.DataTypeCreateRequest) ((param0) == null ? null : (param0.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.tasks.Task<java.lang.Void> disableFit() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.SettingController) this.getHInstance()).disableHiHealth()");
            com.huawei.hmf.tasks.Task hReturn = ((com.huawei.hms.hihealth.SettingController) this.getHInstance()).disableHiHealth();
            return ((hReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.ConfigClient) this.getGInstance()).disableFit()");
            com.google.android.gms.tasks.Task gReturn = ((com.google.android.gms.fitness.ConfigClient) this.getGInstance()).disableFit();
            return ((gReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.tasks.Task<org.xms.g.fitness.data.DataType> readDataType(java.lang.String param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.SettingController) this.getHInstance()).readDataType(param0)");
            com.huawei.hmf.tasks.Task hReturn = ((com.huawei.hms.hihealth.SettingController) this.getHInstance()).readDataType(param0);
            return ((hReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.ConfigClient) this.getGInstance()).readDataType(param0)");
            com.google.android.gms.tasks.Task gReturn = ((com.google.android.gms.fitness.ConfigClient) this.getGInstance()).readDataType(param0);
            return ((gReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public java.lang.Object getApiKey() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.ConfigClient dynamicCast(java.lang.Object param0) {
        if (param0 instanceof org.xms.g.fitness.ConfigClient) {
            return ((org.xms.g.fitness.ConfigClient) param0);
        }
        if (param0 instanceof org.xms.g.utils.XGettable) {
            com.google.android.gms.fitness.ConfigClient gReturn = ((com.google.android.gms.fitness.ConfigClient) ((org.xms.g.utils.XGettable) param0).getGInstance());
            com.huawei.hms.hihealth.SettingController hReturn = ((com.huawei.hms.hihealth.SettingController) ((org.xms.g.utils.XGettable) param0).getHInstance());
            return new org.xms.g.fitness.ConfigClient(new org.xms.g.utils.XBox(gReturn, hReturn));
        }
        return ((org.xms.g.fitness.ConfigClient) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.SettingController;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.ConfigClient;
        }
    }
}