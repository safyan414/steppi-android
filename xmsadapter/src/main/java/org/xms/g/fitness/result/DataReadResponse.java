package org.xms.g.fitness.result;

public class DataReadResponse extends org.xms.g.common.api.Response {
    
    public DataReadResponse(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public java.util.List<org.xms.g.fitness.data.Bucket> getBuckets() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.result.ReadReply) this.getHInstance()).getGroups()");
            java.util.List hReturn = ((com.huawei.hms.hihealth.result.ReadReply) this.getHInstance()).getGroups();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(hReturn, new org.xms.g.utils.Function<com.huawei.hms.hihealth.data.Group, org.xms.g.fitness.data.Bucket>() {
                
                public org.xms.g.fitness.data.Bucket apply(com.huawei.hms.hihealth.data.Group param0) {
                    return new org.xms.g.fitness.data.Bucket(new org.xms.g.utils.XBox(null, param0));
                }
            }));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.result.DataReadResponse) this.getGInstance()).getBuckets()");
            java.util.List gReturn = ((com.google.android.gms.fitness.result.DataReadResponse) this.getGInstance()).getBuckets();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(gReturn, new org.xms.g.utils.Function<com.google.android.gms.fitness.data.Bucket, org.xms.g.fitness.data.Bucket>() {
                
                public org.xms.g.fitness.data.Bucket apply(com.google.android.gms.fitness.data.Bucket param0) {
                    return new org.xms.g.fitness.data.Bucket(new org.xms.g.utils.XBox(param0, null));
                }
            }));
        }
    }
    
    public org.xms.g.fitness.data.DataSet getDataSet(org.xms.g.fitness.data.DataType param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.result.ReadReply) this.getHInstance()).getSampleSet(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))))");
            com.huawei.hms.hihealth.data.SampleSet hReturn = ((com.huawei.hms.hihealth.result.ReadReply) this.getHInstance()).getSampleSet(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataSet(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.result.DataReadResponse) this.getGInstance()).getDataSet(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))))");
            com.google.android.gms.fitness.data.DataSet gReturn = ((com.google.android.gms.fitness.result.DataReadResponse) this.getGInstance()).getDataSet(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataSet(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.fitness.data.DataSet getDataSet(org.xms.g.fitness.data.DataSource param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.result.ReadReply) this.getHInstance()).getSampleSet(((com.huawei.hms.hihealth.data.DataCollector) ((param0) == null ? null : (param0.getHInstance()))))");
            com.huawei.hms.hihealth.data.SampleSet hReturn = ((com.huawei.hms.hihealth.result.ReadReply) this.getHInstance()).getSampleSet(((com.huawei.hms.hihealth.data.DataCollector) ((param0) == null ? null : (param0.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataSet(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.result.DataReadResponse) this.getGInstance()).getDataSet(((com.google.android.gms.fitness.data.DataSource) ((param0) == null ? null : (param0.getGInstance()))))");
            com.google.android.gms.fitness.data.DataSet gReturn = ((com.google.android.gms.fitness.result.DataReadResponse) this.getGInstance()).getDataSet(((com.google.android.gms.fitness.data.DataSource) ((param0) == null ? null : (param0.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataSet(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public java.util.List<org.xms.g.fitness.data.DataSet> getDataSets() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.result.ReadReply) this.getHInstance()).getSampleSets()");
            java.util.List hReturn = ((com.huawei.hms.hihealth.result.ReadReply) this.getHInstance()).getSampleSets();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(hReturn, new org.xms.g.utils.Function<com.huawei.hms.hihealth.data.SampleSet, org.xms.g.fitness.data.DataSet>() {
                
                public org.xms.g.fitness.data.DataSet apply(com.huawei.hms.hihealth.data.SampleSet param0) {
                    return new org.xms.g.fitness.data.DataSet(new org.xms.g.utils.XBox(null, param0));
                }
            }));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.result.DataReadResponse) this.getGInstance()).getDataSets()");
            java.util.List gReturn = ((com.google.android.gms.fitness.result.DataReadResponse) this.getGInstance()).getDataSets();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(gReturn, new org.xms.g.utils.Function<com.google.android.gms.fitness.data.DataSet, org.xms.g.fitness.data.DataSet>() {
                
                public org.xms.g.fitness.data.DataSet apply(com.google.android.gms.fitness.data.DataSet param0) {
                    return new org.xms.g.fitness.data.DataSet(new org.xms.g.utils.XBox(param0, null));
                }
            }));
        }
    }
    
    public org.xms.g.common.api.Status getStatus() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.result.ReadReply) this.getHInstance()).getStatus()");
            com.huawei.hms.support.api.client.Status hReturn = ((com.huawei.hms.hihealth.result.ReadReply) this.getHInstance()).getStatus();
            return ((hReturn) == null ? null : (new org.xms.g.common.api.Status(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.result.DataReadResponse) this.getGInstance()).getStatus()");
            com.google.android.gms.common.api.Status gReturn = ((com.google.android.gms.fitness.result.DataReadResponse) this.getGInstance()).getStatus();
            return ((gReturn) == null ? null : (new org.xms.g.common.api.Status(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.result.DataReadResponse dynamicCast(java.lang.Object param0) {
        return ((org.xms.g.fitness.result.DataReadResponse) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.result.ReadReply;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.result.DataReadResponse;
        }
    }
}