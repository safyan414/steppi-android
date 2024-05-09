package org.xms.g.fitness;

public class RecordingClient extends org.xms.g.common.api.ExtensionApi {
    
    public RecordingClient(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public org.xms.g.tasks.Task<java.util.List<org.xms.g.fitness.data.Subscription>> listSubscriptions() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.AutoRecorderController) this.getHInstance()).getRecords()");
            com.huawei.hmf.tasks.Task hReturn = ((com.huawei.hms.hihealth.AutoRecorderController) this.getHInstance()).getRecords();
            return ((hReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.RecordingClient) this.getGInstance()).listSubscriptions()");
            com.google.android.gms.tasks.Task gReturn = ((com.google.android.gms.fitness.RecordingClient) this.getGInstance()).listSubscriptions();
            return ((gReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.tasks.Task<java.util.List<org.xms.g.fitness.data.Subscription>> listSubscriptions(org.xms.g.fitness.data.DataType param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.AutoRecorderController) this.getHInstance()).getRecords(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))))");
            com.huawei.hmf.tasks.Task hReturn = ((com.huawei.hms.hihealth.AutoRecorderController) this.getHInstance()).getRecords(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.RecordingClient) this.getGInstance()).listSubscriptions(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))))");
            com.google.android.gms.tasks.Task gReturn = ((com.google.android.gms.fitness.RecordingClient) this.getGInstance()).listSubscriptions(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.tasks.Task<java.lang.Void> subscribe(org.xms.g.fitness.data.DataSource param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.AutoRecorderController) this.getHInstance()).startRecord(((com.huawei.hms.hihealth.data.DataCollector) ((param0) == null ? null : (param0.getHInstance()))))");
            com.huawei.hmf.tasks.Task hReturn = ((com.huawei.hms.hihealth.AutoRecorderController) this.getHInstance()).startRecord(((com.huawei.hms.hihealth.data.DataCollector) ((param0) == null ? null : (param0.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.RecordingClient) this.getGInstance()).subscribe(((com.google.android.gms.fitness.data.DataSource) ((param0) == null ? null : (param0.getGInstance()))))");
            com.google.android.gms.tasks.Task gReturn = ((com.google.android.gms.fitness.RecordingClient) this.getGInstance()).subscribe(((com.google.android.gms.fitness.data.DataSource) ((param0) == null ? null : (param0.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.tasks.Task<java.lang.Void> subscribe(org.xms.g.fitness.data.DataType param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.AutoRecorderController) this.getHInstance()).startRecord(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))))");
            com.huawei.hmf.tasks.Task hReturn = ((com.huawei.hms.hihealth.AutoRecorderController) this.getHInstance()).startRecord(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.RecordingClient) this.getGInstance()).subscribe(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))))");
            com.google.android.gms.tasks.Task gReturn = ((com.google.android.gms.fitness.RecordingClient) this.getGInstance()).subscribe(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.tasks.Task<java.lang.Void> unsubscribe(org.xms.g.fitness.data.Subscription param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.AutoRecorderController) this.getHInstance()).stopRecord(((com.huawei.hms.hihealth.data.Record) ((param0) == null ? null : (param0.getHInstance()))))");
            com.huawei.hmf.tasks.Task hReturn = ((com.huawei.hms.hihealth.AutoRecorderController) this.getHInstance()).stopRecord(((com.huawei.hms.hihealth.data.Record) ((param0) == null ? null : (param0.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.RecordingClient) this.getGInstance()).unsubscribe(((com.google.android.gms.fitness.data.Subscription) ((param0) == null ? null : (param0.getGInstance()))))");
            com.google.android.gms.tasks.Task gReturn = ((com.google.android.gms.fitness.RecordingClient) this.getGInstance()).unsubscribe(((com.google.android.gms.fitness.data.Subscription) ((param0) == null ? null : (param0.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.tasks.Task<java.lang.Void> unsubscribe(org.xms.g.fitness.data.DataType param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.AutoRecorderController) this.getHInstance()).stopRecord(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))))");
            com.huawei.hmf.tasks.Task hReturn = ((com.huawei.hms.hihealth.AutoRecorderController) this.getHInstance()).stopRecord(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.RecordingClient) this.getGInstance()).unsubscribe(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))))");
            com.google.android.gms.tasks.Task gReturn = ((com.google.android.gms.fitness.RecordingClient) this.getGInstance()).unsubscribe(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.tasks.Task<java.lang.Void> unsubscribe(org.xms.g.fitness.data.DataSource param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.AutoRecorderController) this.getHInstance()).stopRecord(((com.huawei.hms.hihealth.data.DataCollector) ((param0) == null ? null : (param0.getHInstance()))))");
            com.huawei.hmf.tasks.Task hReturn = ((com.huawei.hms.hihealth.AutoRecorderController) this.getHInstance()).stopRecord(((com.huawei.hms.hihealth.data.DataCollector) ((param0) == null ? null : (param0.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.RecordingClient) this.getGInstance()).unsubscribe(((com.google.android.gms.fitness.data.DataSource) ((param0) == null ? null : (param0.getGInstance()))))");
            com.google.android.gms.tasks.Task gReturn = ((com.google.android.gms.fitness.RecordingClient) this.getGInstance()).unsubscribe(((com.google.android.gms.fitness.data.DataSource) ((param0) == null ? null : (param0.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public java.lang.Object getApiKey() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.RecordingClient dynamicCast(java.lang.Object param0) {
        if (param0 instanceof org.xms.g.fitness.RecordingClient) {
            return ((org.xms.g.fitness.RecordingClient) param0);
        }
        if (param0 instanceof org.xms.g.utils.XGettable) {
            com.google.android.gms.fitness.RecordingClient gReturn = ((com.google.android.gms.fitness.RecordingClient) ((org.xms.g.utils.XGettable) param0).getGInstance());
            com.huawei.hms.hihealth.AutoRecorderController hReturn = ((com.huawei.hms.hihealth.AutoRecorderController) ((org.xms.g.utils.XGettable) param0).getHInstance());
            return new org.xms.g.fitness.RecordingClient(new org.xms.g.utils.XBox(gReturn, hReturn));
        }
        return ((org.xms.g.fitness.RecordingClient) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.AutoRecorderController;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.RecordingClient;
        }
    }
}