package org.xms.g.fitness.request;

public class DataUpdateRequest extends org.xms.g.utils.XObject implements android.os.Parcelable {
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {
        
        public org.xms.g.fitness.request.DataUpdateRequest createFromParcel(android.os.Parcel param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                com.huawei.hms.hihealth.options.UpdateOptions hReturn = com.huawei.hms.hihealth.options.UpdateOptions.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.request.DataUpdateRequest(new org.xms.g.utils.XBox(null, hReturn));
            } else {
                com.google.android.gms.fitness.request.DataUpdateRequest gReturn = com.google.android.gms.fitness.request.DataUpdateRequest.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.request.DataUpdateRequest(new org.xms.g.utils.XBox(gReturn, null));
            }
        }
        
        public org.xms.g.fitness.request.DataUpdateRequest[] newArray(int param0) {
            return new org.xms.g.fitness.request.DataUpdateRequest[param0];
        }
    };
    
    public DataUpdateRequest(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public boolean equals(java.lang.Object param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.UpdateOptions) this.getHInstance()).equals(param0)");
            return ((com.huawei.hms.hihealth.options.UpdateOptions) this.getHInstance()).equals(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataUpdateRequest) this.getGInstance()).equals(param0)");
            return ((com.google.android.gms.fitness.request.DataUpdateRequest) this.getGInstance()).equals(param0);
        }
    }
    
    public android.os.IBinder getCallbackBinder() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public org.xms.g.fitness.data.DataSet getDataSet() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.UpdateOptions) this.getHInstance()).getSampleSet()");
            com.huawei.hms.hihealth.data.SampleSet hReturn = ((com.huawei.hms.hihealth.options.UpdateOptions) this.getHInstance()).getSampleSet();
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataSet(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataUpdateRequest) this.getGInstance()).getDataSet()");
            com.google.android.gms.fitness.data.DataSet gReturn = ((com.google.android.gms.fitness.request.DataUpdateRequest) this.getGInstance()).getDataSet();
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataSet(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public long getEndTime(java.util.concurrent.TimeUnit param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.UpdateOptions) this.getHInstance()).getEndTime(param0)");
            return ((com.huawei.hms.hihealth.options.UpdateOptions) this.getHInstance()).getEndTime(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataUpdateRequest) this.getGInstance()).getEndTime(param0)");
            return ((com.google.android.gms.fitness.request.DataUpdateRequest) this.getGInstance()).getEndTime(param0);
        }
    }
    
    public long getStartTime(java.util.concurrent.TimeUnit param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.UpdateOptions) this.getHInstance()).getStartTime(param0)");
            return ((com.huawei.hms.hihealth.options.UpdateOptions) this.getHInstance()).getStartTime(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataUpdateRequest) this.getGInstance()).getStartTime(param0)");
            return ((com.google.android.gms.fitness.request.DataUpdateRequest) this.getGInstance()).getStartTime(param0);
        }
    }
    
    public int hashCode() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.UpdateOptions) this.getHInstance()).hashCode()");
            return ((com.huawei.hms.hihealth.options.UpdateOptions) this.getHInstance()).hashCode();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataUpdateRequest) this.getGInstance()).hashCode()");
            return ((com.google.android.gms.fitness.request.DataUpdateRequest) this.getGInstance()).hashCode();
        }
    }
    
    public java.lang.String toString() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.UpdateOptions) this.getHInstance()).toString()");
            return ((com.huawei.hms.hihealth.options.UpdateOptions) this.getHInstance()).toString();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataUpdateRequest) this.getGInstance()).toString()");
            return ((com.google.android.gms.fitness.request.DataUpdateRequest) this.getGInstance()).toString();
        }
    }
    
    public void writeToParcel(android.os.Parcel param0, int param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.UpdateOptions) this.getHInstance()).writeToParcel(param0, param1)");
            ((com.huawei.hms.hihealth.options.UpdateOptions) this.getHInstance()).writeToParcel(param0, param1);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataUpdateRequest) this.getGInstance()).writeToParcel(param0, param1)");
            ((com.google.android.gms.fitness.request.DataUpdateRequest) this.getGInstance()).writeToParcel(param0, param1);
        }
    }
    
    public int describeContents() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.request.DataUpdateRequest dynamicCast(java.lang.Object param0) {
        return ((org.xms.g.fitness.request.DataUpdateRequest) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.options.UpdateOptions;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.request.DataUpdateRequest;
        }
    }
    
    public static class Builder extends org.xms.g.utils.XObject {
        
        public Builder(org.xms.g.utils.XBox param0) {
            super(param0);
        }
        
        public Builder() {
            super(((org.xms.g.utils.XBox) null));
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                this.setHInstance(new com.huawei.hms.hihealth.options.UpdateOptions.Builder());
            } else {
                this.setGInstance(new com.google.android.gms.fitness.request.DataUpdateRequest.Builder());
            }
        }
        
        public org.xms.g.fitness.request.DataUpdateRequest build() {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.UpdateOptions.Builder) this.getHInstance()).build()");
                com.huawei.hms.hihealth.options.UpdateOptions hReturn = ((com.huawei.hms.hihealth.options.UpdateOptions.Builder) this.getHInstance()).build();
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.DataUpdateRequest(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataUpdateRequest.Builder) this.getGInstance()).build()");
                com.google.android.gms.fitness.request.DataUpdateRequest gReturn = ((com.google.android.gms.fitness.request.DataUpdateRequest.Builder) this.getGInstance()).build();
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.DataUpdateRequest(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.DataUpdateRequest.Builder setDataSet(org.xms.g.fitness.data.DataSet param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.UpdateOptions.Builder) this.getHInstance()).setSampleSet(((com.huawei.hms.hihealth.data.SampleSet) ((param0) == null ? null : (param0.getHInstance()))))");
                com.huawei.hms.hihealth.options.UpdateOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.UpdateOptions.Builder) this.getHInstance()).setSampleSet(((com.huawei.hms.hihealth.data.SampleSet) ((param0) == null ? null : (param0.getHInstance()))));
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.DataUpdateRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataUpdateRequest.Builder) this.getGInstance()).setDataSet(((com.google.android.gms.fitness.data.DataSet) ((param0) == null ? null : (param0.getGInstance()))))");
                com.google.android.gms.fitness.request.DataUpdateRequest.Builder gReturn = ((com.google.android.gms.fitness.request.DataUpdateRequest.Builder) this.getGInstance()).setDataSet(((com.google.android.gms.fitness.data.DataSet) ((param0) == null ? null : (param0.getGInstance()))));
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.DataUpdateRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.DataUpdateRequest.Builder setTimeInterval(long param0, long param1, java.util.concurrent.TimeUnit param2) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.UpdateOptions.Builder) this.getHInstance()).setTimeInterval(param0, param1, param2)");
                com.huawei.hms.hihealth.options.UpdateOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.UpdateOptions.Builder) this.getHInstance()).setTimeInterval(param0, param1, param2);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.DataUpdateRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataUpdateRequest.Builder) this.getGInstance()).setTimeInterval(param0, param1, param2)");
                com.google.android.gms.fitness.request.DataUpdateRequest.Builder gReturn = ((com.google.android.gms.fitness.request.DataUpdateRequest.Builder) this.getGInstance()).setTimeInterval(param0, param1, param2);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.DataUpdateRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public static org.xms.g.fitness.request.DataUpdateRequest.Builder dynamicCast(java.lang.Object param0) {
            return ((org.xms.g.fitness.request.DataUpdateRequest.Builder) param0);
        }
        
        public static boolean isInstance(java.lang.Object param0) {
            if (!(param0 instanceof org.xms.g.utils.XGettable)) {
                return false;
            }
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.options.UpdateOptions.Builder;
            } else {
                return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.request.DataUpdateRequest.Builder;
            }
        }
    }
}