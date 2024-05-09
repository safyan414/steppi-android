package org.xms.g.fitness;

public class HistoryClient extends org.xms.g.common.api.ExtensionApi {
    
    public HistoryClient(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public org.xms.g.tasks.Task<java.lang.Void> deleteData(org.xms.g.fitness.request.DataDeleteRequest param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.DataController) this.getHInstance()).delete(((com.huawei.hms.hihealth.options.DeleteOptions) ((param0) == null ? null : (param0.getHInstance()))))");
            com.huawei.hmf.tasks.Task hReturn = ((com.huawei.hms.hihealth.DataController) this.getHInstance()).delete(((com.huawei.hms.hihealth.options.DeleteOptions) ((param0) == null ? null : (param0.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.HistoryClient) this.getGInstance()).deleteData(((com.google.android.gms.fitness.request.DataDeleteRequest) ((param0) == null ? null : (param0.getGInstance()))))");
            com.google.android.gms.tasks.Task gReturn = ((com.google.android.gms.fitness.HistoryClient) this.getGInstance()).deleteData(((com.google.android.gms.fitness.request.DataDeleteRequest) ((param0) == null ? null : (param0.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.tasks.Task<java.lang.Void> insertData(org.xms.g.fitness.data.DataSet param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.DataController) this.getHInstance()).insert(((com.huawei.hms.hihealth.data.SampleSet) ((param0) == null ? null : (param0.getHInstance()))))");
            com.huawei.hmf.tasks.Task hReturn = ((com.huawei.hms.hihealth.DataController) this.getHInstance()).insert(((com.huawei.hms.hihealth.data.SampleSet) ((param0) == null ? null : (param0.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.HistoryClient) this.getGInstance()).insertData(((com.google.android.gms.fitness.data.DataSet) ((param0) == null ? null : (param0.getGInstance()))))");
            com.google.android.gms.tasks.Task gReturn = ((com.google.android.gms.fitness.HistoryClient) this.getGInstance()).insertData(((com.google.android.gms.fitness.data.DataSet) ((param0) == null ? null : (param0.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.tasks.Task<org.xms.g.fitness.data.DataSet> readDailyTotal(org.xms.g.fitness.data.DataType param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.DataController) this.getHInstance()).readTodaySummation(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))))");
            com.huawei.hmf.tasks.Task hReturn = ((com.huawei.hms.hihealth.DataController) this.getHInstance()).readTodaySummation(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.HistoryClient) this.getGInstance()).readDailyTotal(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))))");
            com.google.android.gms.tasks.Task gReturn = ((com.google.android.gms.fitness.HistoryClient) this.getGInstance()).readDailyTotal(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.tasks.Task<org.xms.g.fitness.data.DataSet> readDailyTotalFromLocalDevice(org.xms.g.fitness.data.DataType param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.DataController) this.getHInstance()).readTodaySummationFromDevice(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))))");
            com.huawei.hmf.tasks.Task hReturn = ((com.huawei.hms.hihealth.DataController) this.getHInstance()).readTodaySummationFromDevice(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.HistoryClient) this.getGInstance()).readDailyTotalFromLocalDevice(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))))");
            com.google.android.gms.tasks.Task gReturn = ((com.google.android.gms.fitness.HistoryClient) this.getGInstance()).readDailyTotalFromLocalDevice(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.tasks.Task<org.xms.g.fitness.result.DataReadResponse> readData(org.xms.g.fitness.request.DataReadRequest param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.DataController) this.getHInstance()).read(((com.huawei.hms.hihealth.options.ReadOptions) ((param0) == null ? null : (param0.getHInstance()))))");
            com.huawei.hmf.tasks.Task hReturn = ((com.huawei.hms.hihealth.DataController) this.getHInstance()).read(((com.huawei.hms.hihealth.options.ReadOptions) ((param0) == null ? null : (param0.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.HistoryClient) this.getGInstance()).readData(((com.google.android.gms.fitness.request.DataReadRequest) ((param0) == null ? null : (param0.getGInstance()))))");
            com.google.android.gms.tasks.Task gReturn = ((com.google.android.gms.fitness.HistoryClient) this.getGInstance()).readData(((com.google.android.gms.fitness.request.DataReadRequest) ((param0) == null ? null : (param0.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.tasks.Task<java.lang.Void> registerDataUpdateListener(org.xms.g.fitness.request.DataUpdateListenerRegistrationRequest param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.DataController) this.getHInstance()).registerModifyDataMonitor(((com.huawei.hms.hihealth.options.ModifyDataMonitorOptions) ((param0) == null ? null : (param0.getHInstance()))))");
            com.huawei.hmf.tasks.Task hReturn = ((com.huawei.hms.hihealth.DataController) this.getHInstance()).registerModifyDataMonitor(((com.huawei.hms.hihealth.options.ModifyDataMonitorOptions) ((param0) == null ? null : (param0.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.HistoryClient) this.getGInstance()).registerDataUpdateListener(((com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest) ((param0) == null ? null : (param0.getGInstance()))))");
            com.google.android.gms.tasks.Task gReturn = ((com.google.android.gms.fitness.HistoryClient) this.getGInstance()).registerDataUpdateListener(((com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest) ((param0) == null ? null : (param0.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.tasks.Task<java.lang.Void> unregisterDataUpdateListener(android.app.PendingIntent param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.DataController) this.getHInstance()).unregisterModifyDataMonitor(param0)");
            com.huawei.hmf.tasks.Task hReturn = ((com.huawei.hms.hihealth.DataController) this.getHInstance()).unregisterModifyDataMonitor(param0);
            return ((hReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.HistoryClient) this.getGInstance()).unregisterDataUpdateListener(param0)");
            com.google.android.gms.tasks.Task gReturn = ((com.google.android.gms.fitness.HistoryClient) this.getGInstance()).unregisterDataUpdateListener(param0);
            return ((gReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.tasks.Task<java.lang.Void> updateData(org.xms.g.fitness.request.DataUpdateRequest param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.DataController) this.getHInstance()).update(((com.huawei.hms.hihealth.options.UpdateOptions) ((param0) == null ? null : (param0.getHInstance()))))");
            com.huawei.hmf.tasks.Task hReturn = ((com.huawei.hms.hihealth.DataController) this.getHInstance()).update(((com.huawei.hms.hihealth.options.UpdateOptions) ((param0) == null ? null : (param0.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.HistoryClient) this.getGInstance()).updateData(((com.google.android.gms.fitness.request.DataUpdateRequest) ((param0) == null ? null : (param0.getGInstance()))))");
            com.google.android.gms.tasks.Task gReturn = ((com.google.android.gms.fitness.HistoryClient) this.getGInstance()).updateData(((com.google.android.gms.fitness.request.DataUpdateRequest) ((param0) == null ? null : (param0.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public java.lang.Object getApiKey() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.HistoryClient dynamicCast(java.lang.Object param0) {
        if (param0 instanceof org.xms.g.fitness.HistoryClient) {
            return ((org.xms.g.fitness.HistoryClient) param0);
        }
        if (param0 instanceof org.xms.g.utils.XGettable) {
            com.google.android.gms.fitness.HistoryClient gReturn = ((com.google.android.gms.fitness.HistoryClient) ((org.xms.g.utils.XGettable) param0).getGInstance());
            com.huawei.hms.hihealth.DataController hReturn = ((com.huawei.hms.hihealth.DataController) ((org.xms.g.utils.XGettable) param0).getHInstance());
            return new org.xms.g.fitness.HistoryClient(new org.xms.g.utils.XBox(gReturn, hReturn));
        }
        return ((org.xms.g.fitness.HistoryClient) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.DataController;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.HistoryClient;
        }
    }
}