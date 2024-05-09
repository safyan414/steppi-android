package org.xms.g.fitness.request;

public class SessionInsertRequest extends org.xms.g.utils.XObject implements android.os.Parcelable {
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {
        
        public org.xms.g.fitness.request.SessionInsertRequest createFromParcel(android.os.Parcel param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                com.huawei.hms.hihealth.options.ActivityRecordInsertOptions hReturn = com.huawei.hms.hihealth.options.ActivityRecordInsertOptions.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.request.SessionInsertRequest(new org.xms.g.utils.XBox(null, hReturn));
            } else {
                com.google.android.gms.fitness.request.SessionInsertRequest gReturn = com.google.android.gms.fitness.request.SessionInsertRequest.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.request.SessionInsertRequest(new org.xms.g.utils.XBox(gReturn, null));
            }
        }
        
        public org.xms.g.fitness.request.SessionInsertRequest[] newArray(int param0) {
            return new org.xms.g.fitness.request.SessionInsertRequest[param0];
        }
    };
    
    public SessionInsertRequest(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public boolean equals(java.lang.Object param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ActivityRecordInsertOptions) this.getHInstance()).equals(param0)");
            return ((com.huawei.hms.hihealth.options.ActivityRecordInsertOptions) this.getHInstance()).equals(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SessionInsertRequest) this.getGInstance()).equals(param0)");
            return ((com.google.android.gms.fitness.request.SessionInsertRequest) this.getGInstance()).equals(param0);
        }
    }
    
    public java.util.List<org.xms.g.fitness.data.DataPoint> getAggregateDataPoints() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ActivityRecordInsertOptions) this.getHInstance()).getPolymerizedSamplePoints()");
            java.util.List hReturn = ((com.huawei.hms.hihealth.options.ActivityRecordInsertOptions) this.getHInstance()).getPolymerizedSamplePoints();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(hReturn, new org.xms.g.utils.Function<com.huawei.hms.hihealth.data.SamplePoint, org.xms.g.fitness.data.DataPoint>() {
                
                public org.xms.g.fitness.data.DataPoint apply(com.huawei.hms.hihealth.data.SamplePoint param0) {
                    return new org.xms.g.fitness.data.DataPoint(new org.xms.g.utils.XBox(null, param0));
                }
            }));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SessionInsertRequest) this.getGInstance()).getAggregateDataPoints()");
            java.util.List gReturn = ((com.google.android.gms.fitness.request.SessionInsertRequest) this.getGInstance()).getAggregateDataPoints();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(gReturn, new org.xms.g.utils.Function<com.google.android.gms.fitness.data.DataPoint, org.xms.g.fitness.data.DataPoint>() {
                
                public org.xms.g.fitness.data.DataPoint apply(com.google.android.gms.fitness.data.DataPoint param0) {
                    return new org.xms.g.fitness.data.DataPoint(new org.xms.g.utils.XBox(param0, null));
                }
            }));
        }
    }
    
    public java.util.List<org.xms.g.fitness.data.DataSet> getDataSets() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ActivityRecordInsertOptions) this.getHInstance()).getSampleSets()");
            java.util.List hReturn = ((com.huawei.hms.hihealth.options.ActivityRecordInsertOptions) this.getHInstance()).getSampleSets();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(hReturn, new org.xms.g.utils.Function<com.huawei.hms.hihealth.data.SampleSet, org.xms.g.fitness.data.DataSet>() {
                
                public org.xms.g.fitness.data.DataSet apply(com.huawei.hms.hihealth.data.SampleSet param0) {
                    return new org.xms.g.fitness.data.DataSet(new org.xms.g.utils.XBox(null, param0));
                }
            }));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SessionInsertRequest) this.getGInstance()).getDataSets()");
            java.util.List gReturn = ((com.google.android.gms.fitness.request.SessionInsertRequest) this.getGInstance()).getDataSets();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(gReturn, new org.xms.g.utils.Function<com.google.android.gms.fitness.data.DataSet, org.xms.g.fitness.data.DataSet>() {
                
                public org.xms.g.fitness.data.DataSet apply(com.google.android.gms.fitness.data.DataSet param0) {
                    return new org.xms.g.fitness.data.DataSet(new org.xms.g.utils.XBox(param0, null));
                }
            }));
        }
    }
    
    public org.xms.g.fitness.data.Session getSession() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ActivityRecordInsertOptions) this.getHInstance()).getActivityRecord()");
            com.huawei.hms.hihealth.data.ActivityRecord hReturn = ((com.huawei.hms.hihealth.options.ActivityRecordInsertOptions) this.getHInstance()).getActivityRecord();
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Session(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SessionInsertRequest) this.getGInstance()).getSession()");
            com.google.android.gms.fitness.data.Session gReturn = ((com.google.android.gms.fitness.request.SessionInsertRequest) this.getGInstance()).getSession();
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Session(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public int hashCode() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ActivityRecordInsertOptions) this.getHInstance()).hashCode()");
            return ((com.huawei.hms.hihealth.options.ActivityRecordInsertOptions) this.getHInstance()).hashCode();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SessionInsertRequest) this.getGInstance()).hashCode()");
            return ((com.google.android.gms.fitness.request.SessionInsertRequest) this.getGInstance()).hashCode();
        }
    }
    
    public java.lang.String toString() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ActivityRecordInsertOptions) this.getHInstance()).toString()");
            return ((com.huawei.hms.hihealth.options.ActivityRecordInsertOptions) this.getHInstance()).toString();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SessionInsertRequest) this.getGInstance()).toString()");
            return ((com.google.android.gms.fitness.request.SessionInsertRequest) this.getGInstance()).toString();
        }
    }
    
    public void writeToParcel(android.os.Parcel param0, int param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ActivityRecordInsertOptions) this.getHInstance()).writeToParcel(param0, param1)");
            ((com.huawei.hms.hihealth.options.ActivityRecordInsertOptions) this.getHInstance()).writeToParcel(param0, param1);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SessionInsertRequest) this.getGInstance()).writeToParcel(param0, param1)");
            ((com.google.android.gms.fitness.request.SessionInsertRequest) this.getGInstance()).writeToParcel(param0, param1);
        }
    }
    
    public int describeContents() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.request.SessionInsertRequest dynamicCast(java.lang.Object param0) {
        return ((org.xms.g.fitness.request.SessionInsertRequest) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.options.ActivityRecordInsertOptions;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.request.SessionInsertRequest;
        }
    }
    
    public static class Builder extends org.xms.g.utils.XObject {
        
        public Builder(org.xms.g.utils.XBox param0) {
            super(param0);
        }
        
        public Builder() {
            super(((org.xms.g.utils.XBox) null));
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                this.setHInstance(new com.huawei.hms.hihealth.options.ActivityRecordInsertOptions.Builder());
            } else {
                this.setGInstance(new com.google.android.gms.fitness.request.SessionInsertRequest.Builder());
            }
        }
        
        public org.xms.g.fitness.request.SessionInsertRequest.Builder addAggregateDataPoint(org.xms.g.fitness.data.DataPoint param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ActivityRecordInsertOptions.Builder) this.getHInstance()).addPolymerizedSamplePoint(((com.huawei.hms.hihealth.data.SamplePoint) ((param0) == null ? null : (param0.getHInstance()))))");
                com.huawei.hms.hihealth.options.ActivityRecordInsertOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.ActivityRecordInsertOptions.Builder) this.getHInstance()).addPolymerizedSamplePoint(((com.huawei.hms.hihealth.data.SamplePoint) ((param0) == null ? null : (param0.getHInstance()))));
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.SessionInsertRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SessionInsertRequest.Builder) this.getGInstance()).addAggregateDataPoint(((com.google.android.gms.fitness.data.DataPoint) ((param0) == null ? null : (param0.getGInstance()))))");
                com.google.android.gms.fitness.request.SessionInsertRequest.Builder gReturn = ((com.google.android.gms.fitness.request.SessionInsertRequest.Builder) this.getGInstance()).addAggregateDataPoint(((com.google.android.gms.fitness.data.DataPoint) ((param0) == null ? null : (param0.getGInstance()))));
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.SessionInsertRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.SessionInsertRequest.Builder addDataSet(org.xms.g.fitness.data.DataSet param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ActivityRecordInsertOptions.Builder) this.getHInstance()).addSampleSet(((com.huawei.hms.hihealth.data.SampleSet) ((param0) == null ? null : (param0.getHInstance()))))");
                com.huawei.hms.hihealth.options.ActivityRecordInsertOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.ActivityRecordInsertOptions.Builder) this.getHInstance()).addSampleSet(((com.huawei.hms.hihealth.data.SampleSet) ((param0) == null ? null : (param0.getHInstance()))));
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.SessionInsertRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SessionInsertRequest.Builder) this.getGInstance()).addDataSet(((com.google.android.gms.fitness.data.DataSet) ((param0) == null ? null : (param0.getGInstance()))))");
                com.google.android.gms.fitness.request.SessionInsertRequest.Builder gReturn = ((com.google.android.gms.fitness.request.SessionInsertRequest.Builder) this.getGInstance()).addDataSet(((com.google.android.gms.fitness.data.DataSet) ((param0) == null ? null : (param0.getGInstance()))));
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.SessionInsertRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.SessionInsertRequest build() {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ActivityRecordInsertOptions.Builder) this.getHInstance()).build()");
                com.huawei.hms.hihealth.options.ActivityRecordInsertOptions hReturn = ((com.huawei.hms.hihealth.options.ActivityRecordInsertOptions.Builder) this.getHInstance()).build();
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.SessionInsertRequest(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SessionInsertRequest.Builder) this.getGInstance()).build()");
                com.google.android.gms.fitness.request.SessionInsertRequest gReturn = ((com.google.android.gms.fitness.request.SessionInsertRequest.Builder) this.getGInstance()).build();
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.SessionInsertRequest(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.SessionInsertRequest.Builder setSession(org.xms.g.fitness.data.Session param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ActivityRecordInsertOptions.Builder) this.getHInstance()).setActivityRecord(((com.huawei.hms.hihealth.data.ActivityRecord) ((param0) == null ? null : (param0.getHInstance()))))");
                com.huawei.hms.hihealth.options.ActivityRecordInsertOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.ActivityRecordInsertOptions.Builder) this.getHInstance()).setActivityRecord(((com.huawei.hms.hihealth.data.ActivityRecord) ((param0) == null ? null : (param0.getHInstance()))));
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.SessionInsertRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SessionInsertRequest.Builder) this.getGInstance()).setSession(((com.google.android.gms.fitness.data.Session) ((param0) == null ? null : (param0.getGInstance()))))");
                com.google.android.gms.fitness.request.SessionInsertRequest.Builder gReturn = ((com.google.android.gms.fitness.request.SessionInsertRequest.Builder) this.getGInstance()).setSession(((com.google.android.gms.fitness.data.Session) ((param0) == null ? null : (param0.getGInstance()))));
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.SessionInsertRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public static org.xms.g.fitness.request.SessionInsertRequest.Builder dynamicCast(java.lang.Object param0) {
            return ((org.xms.g.fitness.request.SessionInsertRequest.Builder) param0);
        }
        
        public static boolean isInstance(java.lang.Object param0) {
            if (!(param0 instanceof org.xms.g.utils.XGettable)) {
                return false;
            }
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.options.ActivityRecordInsertOptions.Builder;
            } else {
                return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.request.SessionInsertRequest.Builder;
            }
        }
    }
}