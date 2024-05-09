package org.xms.g.fitness;

public interface SessionsApi extends org.xms.g.utils.XInterface {
    
    public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> insertSession(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.request.SessionInsertRequest param1);
    
    public org.xms.g.common.api.PendingResult<org.xms.g.fitness.result.SessionReadResult> readSession(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.request.SessionReadRequest param1);
    
    public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> registerForSessions(org.xms.g.common.api.ExtensionApiClient param0, android.app.PendingIntent param1);
    
    public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> startSession(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.data.Session param1);
    
    public org.xms.g.common.api.PendingResult<org.xms.g.fitness.result.SessionStopResult> stopSession(org.xms.g.common.api.ExtensionApiClient param0, java.lang.String param1);
    
    public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> unregisterForSessions(org.xms.g.common.api.ExtensionApiClient param0, android.app.PendingIntent param1);
    
    default java.lang.Object getZInstanceSessionsApi() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    default com.google.android.gms.fitness.SessionsApi getGInstanceSessionsApi() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    default java.lang.Object getHInstanceSessionsApi() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.SessionsApi dynamicCast(java.lang.Object param0) {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static class XImpl extends org.xms.g.utils.XObject implements org.xms.g.fitness.SessionsApi {
        
        public XImpl(org.xms.g.utils.XBox param0) {
            super(param0);
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> insertSession(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.request.SessionInsertRequest param1) {
            throw new java.lang.RuntimeException("Not Supported");
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.fitness.result.SessionReadResult> readSession(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.request.SessionReadRequest param1) {
            throw new java.lang.RuntimeException("Not Supported");
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> registerForSessions(org.xms.g.common.api.ExtensionApiClient param0, android.app.PendingIntent param1) {
            throw new java.lang.RuntimeException("Not Supported");
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> startSession(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.data.Session param1) {
            throw new java.lang.RuntimeException("Not Supported");
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.fitness.result.SessionStopResult> stopSession(org.xms.g.common.api.ExtensionApiClient param0, java.lang.String param1) {
            throw new java.lang.RuntimeException("Not Supported");
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> unregisterForSessions(org.xms.g.common.api.ExtensionApiClient param0, android.app.PendingIntent param1) {
            throw new java.lang.RuntimeException("Not Supported");
        }
    }
    
    public static class ViewIntentBuilder extends org.xms.g.utils.XObject {
        
        public ViewIntentBuilder(org.xms.g.utils.XBox param0) {
            super(param0);
        }
        
        public ViewIntentBuilder(android.content.Context param0) {
            super(((org.xms.g.utils.XBox) null));
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                this.setHInstance(new com.huawei.hms.hihealth.ActivityRecordsManager.GetIntentInfos(param0));
            } else {
                this.setGInstance(new com.google.android.gms.fitness.SessionsApi.ViewIntentBuilder(param0));
            }
        }
        
        public android.content.Intent build() {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.ActivityRecordsManager.GetIntentInfos) this.getHInstance()).getActivityRecordsIntent()");
                return ((com.huawei.hms.hihealth.ActivityRecordsManager.GetIntentInfos) this.getHInstance()).getActivityRecordsIntent();
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.SessionsApi.ViewIntentBuilder) this.getGInstance()).build()");
                return ((com.google.android.gms.fitness.SessionsApi.ViewIntentBuilder) this.getGInstance()).build();
            }
        }
        
        public org.xms.g.fitness.SessionsApi.ViewIntentBuilder setPreferredApplication(java.lang.String param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.ActivityRecordsManager.GetIntentInfos) this.getHInstance()).setAppPackageName(param0)");
                com.huawei.hms.hihealth.ActivityRecordsManager.GetIntentInfos hReturn = ((com.huawei.hms.hihealth.ActivityRecordsManager.GetIntentInfos) this.getHInstance()).setAppPackageName(param0);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.SessionsApi.ViewIntentBuilder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.SessionsApi.ViewIntentBuilder) this.getGInstance()).setPreferredApplication(param0)");
                com.google.android.gms.fitness.SessionsApi.ViewIntentBuilder gReturn = ((com.google.android.gms.fitness.SessionsApi.ViewIntentBuilder) this.getGInstance()).setPreferredApplication(param0);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.SessionsApi.ViewIntentBuilder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.SessionsApi.ViewIntentBuilder setSession(org.xms.g.fitness.data.Session param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.ActivityRecordsManager.GetIntentInfos) this.getHInstance()).setActivityRecord(((com.huawei.hms.hihealth.data.ActivityRecord) ((param0) == null ? null : (param0.getHInstance()))))");
                com.huawei.hms.hihealth.ActivityRecordsManager.GetIntentInfos hReturn = ((com.huawei.hms.hihealth.ActivityRecordsManager.GetIntentInfos) this.getHInstance()).setActivityRecord(((com.huawei.hms.hihealth.data.ActivityRecord) ((param0) == null ? null : (param0.getHInstance()))));
                return ((hReturn) == null ? null : (new org.xms.g.fitness.SessionsApi.ViewIntentBuilder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.SessionsApi.ViewIntentBuilder) this.getGInstance()).setSession(((com.google.android.gms.fitness.data.Session) ((param0) == null ? null : (param0.getGInstance()))))");
                com.google.android.gms.fitness.SessionsApi.ViewIntentBuilder gReturn = ((com.google.android.gms.fitness.SessionsApi.ViewIntentBuilder) this.getGInstance()).setSession(((com.google.android.gms.fitness.data.Session) ((param0) == null ? null : (param0.getGInstance()))));
                return ((gReturn) == null ? null : (new org.xms.g.fitness.SessionsApi.ViewIntentBuilder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public static org.xms.g.fitness.SessionsApi.ViewIntentBuilder dynamicCast(java.lang.Object param0) {
            return ((org.xms.g.fitness.SessionsApi.ViewIntentBuilder) param0);
        }
        
        public static boolean isInstance(java.lang.Object param0) {
            if (!(param0 instanceof org.xms.g.utils.XGettable)) {
                return false;
            }
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.ActivityRecordsManager.GetIntentInfos;
            } else {
                return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.SessionsApi.ViewIntentBuilder;
            }
        }
    }
}