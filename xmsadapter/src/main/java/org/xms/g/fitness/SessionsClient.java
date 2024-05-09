package org.xms.g.fitness;

public class SessionsClient extends org.xms.g.common.api.ExtensionApi {
    
    public SessionsClient(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public org.xms.g.tasks.Task<java.lang.Void> insertSession(org.xms.g.fitness.request.SessionInsertRequest param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.ActivityRecordsController) this.getHInstance()).addActivityRecord(((com.huawei.hms.hihealth.options.ActivityRecordInsertOptions) ((param0) == null ? null : (param0.getHInstance()))))");
            com.huawei.hmf.tasks.Task hReturn = ((com.huawei.hms.hihealth.ActivityRecordsController) this.getHInstance()).addActivityRecord(((com.huawei.hms.hihealth.options.ActivityRecordInsertOptions) ((param0) == null ? null : (param0.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.SessionsClient) this.getGInstance()).insertSession(((com.google.android.gms.fitness.request.SessionInsertRequest) ((param0) == null ? null : (param0.getGInstance()))))");
            com.google.android.gms.tasks.Task gReturn = ((com.google.android.gms.fitness.SessionsClient) this.getGInstance()).insertSession(((com.google.android.gms.fitness.request.SessionInsertRequest) ((param0) == null ? null : (param0.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.tasks.Task<org.xms.g.fitness.result.SessionReadResponse> readSession(org.xms.g.fitness.request.SessionReadRequest param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.ActivityRecordsController) this.getHInstance()).getActivityRecord(((com.huawei.hms.hihealth.options.ActivityRecordReadOptions) ((param0) == null ? null : (param0.getHInstance()))))");
            com.huawei.hmf.tasks.Task hReturn = ((com.huawei.hms.hihealth.ActivityRecordsController) this.getHInstance()).getActivityRecord(((com.huawei.hms.hihealth.options.ActivityRecordReadOptions) ((param0) == null ? null : (param0.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.SessionsClient) this.getGInstance()).readSession(((com.google.android.gms.fitness.request.SessionReadRequest) ((param0) == null ? null : (param0.getGInstance()))))");
            com.google.android.gms.tasks.Task gReturn = ((com.google.android.gms.fitness.SessionsClient) this.getGInstance()).readSession(((com.google.android.gms.fitness.request.SessionReadRequest) ((param0) == null ? null : (param0.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.tasks.Task<java.lang.Void> registerForSessions(android.app.PendingIntent param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.ActivityRecordsController) this.getHInstance()).addActivityRecordsMonitor(param0)");
            com.huawei.hmf.tasks.Task hReturn = ((com.huawei.hms.hihealth.ActivityRecordsController) this.getHInstance()).addActivityRecordsMonitor(param0);
            return ((hReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.SessionsClient) this.getGInstance()).registerForSessions(param0)");
            com.google.android.gms.tasks.Task gReturn = ((com.google.android.gms.fitness.SessionsClient) this.getGInstance()).registerForSessions(param0);
            return ((gReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.tasks.Task<java.lang.Void> startSession(org.xms.g.fitness.data.Session param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.ActivityRecordsController) this.getHInstance()).beginActivityRecord(((com.huawei.hms.hihealth.data.ActivityRecord) ((param0) == null ? null : (param0.getHInstance()))))");
            com.huawei.hmf.tasks.Task hReturn = ((com.huawei.hms.hihealth.ActivityRecordsController) this.getHInstance()).beginActivityRecord(((com.huawei.hms.hihealth.data.ActivityRecord) ((param0) == null ? null : (param0.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.SessionsClient) this.getGInstance()).startSession(((com.google.android.gms.fitness.data.Session) ((param0) == null ? null : (param0.getGInstance()))))");
            com.google.android.gms.tasks.Task gReturn = ((com.google.android.gms.fitness.SessionsClient) this.getGInstance()).startSession(((com.google.android.gms.fitness.data.Session) ((param0) == null ? null : (param0.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.tasks.Task<java.util.List<org.xms.g.fitness.data.Session>> stopSession(java.lang.String param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.ActivityRecordsController) this.getHInstance()).endActivityRecord(param0)");
            com.huawei.hmf.tasks.Task hReturn = ((com.huawei.hms.hihealth.ActivityRecordsController) this.getHInstance()).endActivityRecord(param0);
            return ((hReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.SessionsClient) this.getGInstance()).stopSession(param0)");
            com.google.android.gms.tasks.Task gReturn = ((com.google.android.gms.fitness.SessionsClient) this.getGInstance()).stopSession(param0);
            return ((gReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.tasks.Task<java.lang.Void> unregisterForSessions(android.app.PendingIntent param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.ActivityRecordsController) this.getHInstance()).removeActivityRecordsMonitor(param0)");
            com.huawei.hmf.tasks.Task hReturn = ((com.huawei.hms.hihealth.ActivityRecordsController) this.getHInstance()).removeActivityRecordsMonitor(param0);
            return ((hReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.SessionsClient) this.getGInstance()).unregisterForSessions(param0)");
            com.google.android.gms.tasks.Task gReturn = ((com.google.android.gms.fitness.SessionsClient) this.getGInstance()).unregisterForSessions(param0);
            return ((gReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public java.lang.Object getApiKey() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.SessionsClient dynamicCast(java.lang.Object param0) {
        if (param0 instanceof org.xms.g.fitness.SessionsClient) {
            return ((org.xms.g.fitness.SessionsClient) param0);
        }
        if (param0 instanceof org.xms.g.utils.XGettable) {
            com.google.android.gms.fitness.SessionsClient gReturn = ((com.google.android.gms.fitness.SessionsClient) ((org.xms.g.utils.XGettable) param0).getGInstance());
            com.huawei.hms.hihealth.ActivityRecordsController hReturn = ((com.huawei.hms.hihealth.ActivityRecordsController) ((org.xms.g.utils.XGettable) param0).getHInstance());
            return new org.xms.g.fitness.SessionsClient(new org.xms.g.utils.XBox(gReturn, hReturn));
        }
        return ((org.xms.g.fitness.SessionsClient) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.ActivityRecordsController;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.SessionsClient;
        }
    }
}