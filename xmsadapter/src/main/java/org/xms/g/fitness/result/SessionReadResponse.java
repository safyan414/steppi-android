package org.xms.g.fitness.result;

public class SessionReadResponse extends org.xms.g.common.api.Response {
    
    public SessionReadResponse(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public java.util.List<org.xms.g.fitness.data.DataSet> getDataSet(org.xms.g.fitness.data.Session param0, org.xms.g.fitness.data.DataType param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.result.ActivityRecordReply) this.getHInstance()).getSampleSet(((com.huawei.hms.hihealth.data.ActivityRecord) ((param0) == null ? null : (param0.getHInstance()))), ((com.huawei.hms.hihealth.data.DataType) ((param1) == null ? null : (param1.getHInstance()))))");
            java.util.List hReturn = ((com.huawei.hms.hihealth.result.ActivityRecordReply) this.getHInstance()).getSampleSet(((com.huawei.hms.hihealth.data.ActivityRecord) ((param0) == null ? null : (param0.getHInstance()))), ((com.huawei.hms.hihealth.data.DataType) ((param1) == null ? null : (param1.getHInstance()))));
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(hReturn, new org.xms.g.utils.Function<com.huawei.hms.hihealth.data.SampleSet, org.xms.g.fitness.data.DataSet>() {
                
                public org.xms.g.fitness.data.DataSet apply(com.huawei.hms.hihealth.data.SampleSet param0) {
                    return new org.xms.g.fitness.data.DataSet(new org.xms.g.utils.XBox(null, param0));
                }
            }));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.result.SessionReadResponse) this.getGInstance()).getDataSet(((com.google.android.gms.fitness.data.Session) ((param0) == null ? null : (param0.getGInstance()))), ((com.google.android.gms.fitness.data.DataType) ((param1) == null ? null : (param1.getGInstance()))))");
            java.util.List gReturn = ((com.google.android.gms.fitness.result.SessionReadResponse) this.getGInstance()).getDataSet(((com.google.android.gms.fitness.data.Session) ((param0) == null ? null : (param0.getGInstance()))), ((com.google.android.gms.fitness.data.DataType) ((param1) == null ? null : (param1.getGInstance()))));
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(gReturn, new org.xms.g.utils.Function<com.google.android.gms.fitness.data.DataSet, org.xms.g.fitness.data.DataSet>() {
                
                public org.xms.g.fitness.data.DataSet apply(com.google.android.gms.fitness.data.DataSet param0) {
                    return new org.xms.g.fitness.data.DataSet(new org.xms.g.utils.XBox(param0, null));
                }
            }));
        }
    }
    
    public java.util.List<org.xms.g.fitness.data.DataSet> getDataSet(org.xms.g.fitness.data.Session param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.result.ActivityRecordReply) this.getHInstance()).getSampleSet(((com.huawei.hms.hihealth.data.ActivityRecord) ((param0) == null ? null : (param0.getHInstance()))))");
            java.util.List hReturn = ((com.huawei.hms.hihealth.result.ActivityRecordReply) this.getHInstance()).getSampleSet(((com.huawei.hms.hihealth.data.ActivityRecord) ((param0) == null ? null : (param0.getHInstance()))));
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(hReturn, new org.xms.g.utils.Function<com.huawei.hms.hihealth.data.SampleSet, org.xms.g.fitness.data.DataSet>() {
                
                public org.xms.g.fitness.data.DataSet apply(com.huawei.hms.hihealth.data.SampleSet param0) {
                    return new org.xms.g.fitness.data.DataSet(new org.xms.g.utils.XBox(null, param0));
                }
            }));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.result.SessionReadResponse) this.getGInstance()).getDataSet(((com.google.android.gms.fitness.data.Session) ((param0) == null ? null : (param0.getGInstance()))))");
            java.util.List gReturn = ((com.google.android.gms.fitness.result.SessionReadResponse) this.getGInstance()).getDataSet(((com.google.android.gms.fitness.data.Session) ((param0) == null ? null : (param0.getGInstance()))));
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(gReturn, new org.xms.g.utils.Function<com.google.android.gms.fitness.data.DataSet, org.xms.g.fitness.data.DataSet>() {
                
                public org.xms.g.fitness.data.DataSet apply(com.google.android.gms.fitness.data.DataSet param0) {
                    return new org.xms.g.fitness.data.DataSet(new org.xms.g.utils.XBox(param0, null));
                }
            }));
        }
    }
    
    public java.util.List<org.xms.g.fitness.data.Session> getSessions() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.result.ActivityRecordReply) this.getHInstance()).getActivityRecords()");
            java.util.List hReturn = ((com.huawei.hms.hihealth.result.ActivityRecordReply) this.getHInstance()).getActivityRecords();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(hReturn, new org.xms.g.utils.Function<com.huawei.hms.hihealth.data.ActivityRecord, org.xms.g.fitness.data.Session>() {
                
                public org.xms.g.fitness.data.Session apply(com.huawei.hms.hihealth.data.ActivityRecord param0) {
                    return new org.xms.g.fitness.data.Session(new org.xms.g.utils.XBox(null, param0));
                }
            }));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.result.SessionReadResponse) this.getGInstance()).getSessions()");
            java.util.List gReturn = ((com.google.android.gms.fitness.result.SessionReadResponse) this.getGInstance()).getSessions();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(gReturn, new org.xms.g.utils.Function<com.google.android.gms.fitness.data.Session, org.xms.g.fitness.data.Session>() {
                
                public org.xms.g.fitness.data.Session apply(com.google.android.gms.fitness.data.Session param0) {
                    return new org.xms.g.fitness.data.Session(new org.xms.g.utils.XBox(param0, null));
                }
            }));
        }
    }
    
    public org.xms.g.common.api.Status getStatus() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.result.ActivityRecordReply) this.getHInstance()).getStatus()");
            com.huawei.hms.support.api.client.Status hReturn = ((com.huawei.hms.hihealth.result.ActivityRecordReply) this.getHInstance()).getStatus();
            return ((hReturn) == null ? null : (new org.xms.g.common.api.Status(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.result.SessionReadResponse) this.getGInstance()).getStatus()");
            com.google.android.gms.common.api.Status gReturn = ((com.google.android.gms.fitness.result.SessionReadResponse) this.getGInstance()).getStatus();
            return ((gReturn) == null ? null : (new org.xms.g.common.api.Status(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.result.SessionReadResponse dynamicCast(java.lang.Object param0) {
        return ((org.xms.g.fitness.result.SessionReadResponse) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.result.ActivityRecordReply;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.result.SessionReadResponse;
        }
    }
}