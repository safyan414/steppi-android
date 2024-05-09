package org.xms.g.fitness;

public interface HistoryApi extends org.xms.g.utils.XInterface {
    
    public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> deleteData(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.request.DataDeleteRequest param1);
    
    public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> insertData(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.data.DataSet param1);
    
    public org.xms.g.common.api.PendingResult<org.xms.g.fitness.result.DailyTotalResult> readDailyTotal(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.data.DataType param1);
    
    public org.xms.g.common.api.PendingResult<org.xms.g.fitness.result.DailyTotalResult> readDailyTotalFromLocalDevice(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.data.DataType param1);
    
    public org.xms.g.common.api.PendingResult<org.xms.g.fitness.result.DataReadResult> readData(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.request.DataReadRequest param1);
    
    public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> registerDataUpdateListener(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.request.DataUpdateListenerRegistrationRequest param1);
    
    public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> unregisterDataUpdateListener(org.xms.g.common.api.ExtensionApiClient param0, android.app.PendingIntent param1);
    
    public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> updateData(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.request.DataUpdateRequest param1);
    
    default java.lang.Object getZInstanceHistoryApi() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    default com.google.android.gms.fitness.HistoryApi getGInstanceHistoryApi() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    default java.lang.Object getHInstanceHistoryApi() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.HistoryApi dynamicCast(java.lang.Object param0) {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static class XImpl extends org.xms.g.utils.XObject implements org.xms.g.fitness.HistoryApi {
        
        public XImpl(org.xms.g.utils.XBox param0) {
            super(param0);
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> deleteData(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.request.DataDeleteRequest param1) {
            throw new java.lang.RuntimeException("Not Supported");
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> insertData(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.data.DataSet param1) {
            throw new java.lang.RuntimeException("Not Supported");
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.fitness.result.DailyTotalResult> readDailyTotal(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.data.DataType param1) {
            throw new java.lang.RuntimeException("Not Supported");
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.fitness.result.DailyTotalResult> readDailyTotalFromLocalDevice(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.data.DataType param1) {
            throw new java.lang.RuntimeException("Not Supported");
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.fitness.result.DataReadResult> readData(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.request.DataReadRequest param1) {
            throw new java.lang.RuntimeException("Not Supported");
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> registerDataUpdateListener(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.request.DataUpdateListenerRegistrationRequest param1) {
            throw new java.lang.RuntimeException("Not Supported");
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> unregisterDataUpdateListener(org.xms.g.common.api.ExtensionApiClient param0, android.app.PendingIntent param1) {
            throw new java.lang.RuntimeException("Not Supported");
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> updateData(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.request.DataUpdateRequest param1) {
            throw new java.lang.RuntimeException("Not Supported");
        }
    }
    
    public static class ViewIntentBuilder extends org.xms.g.utils.XObject {
        
        public ViewIntentBuilder(org.xms.g.utils.XBox param0) {
            super(param0);
        }
        
        public ViewIntentBuilder(android.content.Context param0, org.xms.g.fitness.data.DataType param1) {
            super(((org.xms.g.utils.XBox) null));
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                this.setHInstance(new com.huawei.hms.hihealth.DataManager.GetIntentInfos(param0, ((com.huawei.hms.hihealth.data.DataType) ((param1) == null ? null : (param1.getHInstance())))));
            } else {
                this.setGInstance(new com.google.android.gms.fitness.HistoryApi.ViewIntentBuilder(param0, ((com.google.android.gms.fitness.data.DataType) ((param1) == null ? null : (param1.getGInstance())))));
            }
        }
        
        public android.content.Intent build() {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.DataManager.GetIntentInfos) this.getHInstance()).getIntent()");
                return ((com.huawei.hms.hihealth.DataManager.GetIntentInfos) this.getHInstance()).getIntent();
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.HistoryApi.ViewIntentBuilder) this.getGInstance()).build()");
                return ((com.google.android.gms.fitness.HistoryApi.ViewIntentBuilder) this.getGInstance()).build();
            }
        }
        
        public org.xms.g.fitness.HistoryApi.ViewIntentBuilder setDataSource(org.xms.g.fitness.data.DataSource param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.DataManager.GetIntentInfos) this.getHInstance()).setDataCollector(((com.huawei.hms.hihealth.data.DataCollector) ((param0) == null ? null : (param0.getHInstance()))))");
                com.huawei.hms.hihealth.DataManager.GetIntentInfos hReturn = ((com.huawei.hms.hihealth.DataManager.GetIntentInfos) this.getHInstance()).setDataCollector(((com.huawei.hms.hihealth.data.DataCollector) ((param0) == null ? null : (param0.getHInstance()))));
                return ((hReturn) == null ? null : (new org.xms.g.fitness.HistoryApi.ViewIntentBuilder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.HistoryApi.ViewIntentBuilder) this.getGInstance()).setDataSource(((com.google.android.gms.fitness.data.DataSource) ((param0) == null ? null : (param0.getGInstance()))))");
                com.google.android.gms.fitness.HistoryApi.ViewIntentBuilder gReturn = ((com.google.android.gms.fitness.HistoryApi.ViewIntentBuilder) this.getGInstance()).setDataSource(((com.google.android.gms.fitness.data.DataSource) ((param0) == null ? null : (param0.getGInstance()))));
                return ((gReturn) == null ? null : (new org.xms.g.fitness.HistoryApi.ViewIntentBuilder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.HistoryApi.ViewIntentBuilder setPreferredApplication(java.lang.String param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.DataManager.GetIntentInfos) this.getHInstance()).selectApplication(param0)");
                com.huawei.hms.hihealth.DataManager.GetIntentInfos hReturn = ((com.huawei.hms.hihealth.DataManager.GetIntentInfos) this.getHInstance()).selectApplication(param0);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.HistoryApi.ViewIntentBuilder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.HistoryApi.ViewIntentBuilder) this.getGInstance()).setPreferredApplication(param0)");
                com.google.android.gms.fitness.HistoryApi.ViewIntentBuilder gReturn = ((com.google.android.gms.fitness.HistoryApi.ViewIntentBuilder) this.getGInstance()).setPreferredApplication(param0);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.HistoryApi.ViewIntentBuilder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.HistoryApi.ViewIntentBuilder setTimeInterval(long param0, long param1, java.util.concurrent.TimeUnit param2) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.DataManager.GetIntentInfos) this.getHInstance()).setTimeInterval(param0, param1, param2)");
                com.huawei.hms.hihealth.DataManager.GetIntentInfos hReturn = ((com.huawei.hms.hihealth.DataManager.GetIntentInfos) this.getHInstance()).setTimeInterval(param0, param1, param2);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.HistoryApi.ViewIntentBuilder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.HistoryApi.ViewIntentBuilder) this.getGInstance()).setTimeInterval(param0, param1, param2)");
                com.google.android.gms.fitness.HistoryApi.ViewIntentBuilder gReturn = ((com.google.android.gms.fitness.HistoryApi.ViewIntentBuilder) this.getGInstance()).setTimeInterval(param0, param1, param2);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.HistoryApi.ViewIntentBuilder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public static org.xms.g.fitness.HistoryApi.ViewIntentBuilder dynamicCast(java.lang.Object param0) {
            return ((org.xms.g.fitness.HistoryApi.ViewIntentBuilder) param0);
        }
        
        public static boolean isInstance(java.lang.Object param0) {
            if (!(param0 instanceof org.xms.g.utils.XGettable)) {
                return false;
            }
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.DataManager.GetIntentInfos;
            } else {
                return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.HistoryApi.ViewIntentBuilder;
            }
        }
    }
}