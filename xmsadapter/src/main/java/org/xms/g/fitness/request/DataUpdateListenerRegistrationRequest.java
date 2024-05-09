package org.xms.g.fitness.request;

public class DataUpdateListenerRegistrationRequest extends org.xms.g.utils.XObject implements android.os.Parcelable {
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {
        
        public org.xms.g.fitness.request.DataUpdateListenerRegistrationRequest createFromParcel(android.os.Parcel param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                com.huawei.hms.hihealth.options.ModifyDataMonitorOptions hReturn = com.huawei.hms.hihealth.options.ModifyDataMonitorOptions.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.request.DataUpdateListenerRegistrationRequest(new org.xms.g.utils.XBox(null, hReturn));
            } else {
                com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest gReturn = com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.request.DataUpdateListenerRegistrationRequest(new org.xms.g.utils.XBox(gReturn, null));
            }
        }
        
        public org.xms.g.fitness.request.DataUpdateListenerRegistrationRequest[] newArray(int param0) {
            return new org.xms.g.fitness.request.DataUpdateListenerRegistrationRequest[param0];
        }
    };
    
    public DataUpdateListenerRegistrationRequest(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public boolean equals(java.lang.Object param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ModifyDataMonitorOptions) this.getHInstance()).equals(param0)");
            return ((com.huawei.hms.hihealth.options.ModifyDataMonitorOptions) this.getHInstance()).equals(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest) this.getGInstance()).equals(param0)");
            return ((com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest) this.getGInstance()).equals(param0);
        }
    }
    
    public org.xms.g.fitness.data.DataSource getDataSource() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ModifyDataMonitorOptions) this.getHInstance()).getModifyDataCollector()");
            com.huawei.hms.hihealth.data.DataCollector hReturn = ((com.huawei.hms.hihealth.options.ModifyDataMonitorOptions) this.getHInstance()).getModifyDataCollector();
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataSource(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest) this.getGInstance()).getDataSource()");
            com.google.android.gms.fitness.data.DataSource gReturn = ((com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest) this.getGInstance()).getDataSource();
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataSource(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.fitness.data.DataType getDataType() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ModifyDataMonitorOptions) this.getHInstance()).getModifyDataType()");
            com.huawei.hms.hihealth.data.DataType hReturn = ((com.huawei.hms.hihealth.options.ModifyDataMonitorOptions) this.getHInstance()).getModifyDataType();
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest) this.getGInstance()).getDataType()");
            com.google.android.gms.fitness.data.DataType gReturn = ((com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest) this.getGInstance()).getDataType();
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public android.app.PendingIntent getIntent() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ModifyDataMonitorOptions) this.getHInstance()).getModifyDataIntent()");
            return ((com.huawei.hms.hihealth.options.ModifyDataMonitorOptions) this.getHInstance()).getModifyDataIntent();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest) this.getGInstance()).getIntent()");
            return ((com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest) this.getGInstance()).getIntent();
        }
    }
    
    public int hashCode() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ModifyDataMonitorOptions) this.getHInstance()).hashCode()");
            return ((com.huawei.hms.hihealth.options.ModifyDataMonitorOptions) this.getHInstance()).hashCode();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest) this.getGInstance()).hashCode()");
            return ((com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest) this.getGInstance()).hashCode();
        }
    }
    
    public java.lang.String toString() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ModifyDataMonitorOptions) this.getHInstance()).toString()");
            return ((com.huawei.hms.hihealth.options.ModifyDataMonitorOptions) this.getHInstance()).toString();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest) this.getGInstance()).toString()");
            return ((com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest) this.getGInstance()).toString();
        }
    }
    
    public void writeToParcel(android.os.Parcel param0, int param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ModifyDataMonitorOptions) this.getHInstance()).writeToParcel(param0, param1)");
            ((com.huawei.hms.hihealth.options.ModifyDataMonitorOptions) this.getHInstance()).writeToParcel(param0, param1);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest) this.getGInstance()).writeToParcel(param0, param1)");
            ((com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest) this.getGInstance()).writeToParcel(param0, param1);
        }
    }
    
    public int describeContents() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.request.DataUpdateListenerRegistrationRequest dynamicCast(java.lang.Object param0) {
        return ((org.xms.g.fitness.request.DataUpdateListenerRegistrationRequest) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.options.ModifyDataMonitorOptions;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest;
        }
    }
    
    public static class Builder extends org.xms.g.utils.XObject {
        
        public Builder(org.xms.g.utils.XBox param0) {
            super(param0);
        }
        
        public Builder() {
            super(((org.xms.g.utils.XBox) null));
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                this.setHInstance(new com.huawei.hms.hihealth.options.ModifyDataMonitorOptions.Builder());
            } else {
                this.setGInstance(new com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest.Builder());
            }
        }
        
        public org.xms.g.fitness.request.DataUpdateListenerRegistrationRequest build() {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ModifyDataMonitorOptions.Builder) this.getHInstance()).build()");
                com.huawei.hms.hihealth.options.ModifyDataMonitorOptions hReturn = ((com.huawei.hms.hihealth.options.ModifyDataMonitorOptions.Builder) this.getHInstance()).build();
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.DataUpdateListenerRegistrationRequest(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest.Builder) this.getGInstance()).build()");
                com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest gReturn = ((com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest.Builder) this.getGInstance()).build();
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.DataUpdateListenerRegistrationRequest(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.DataUpdateListenerRegistrationRequest.Builder setDataSource(org.xms.g.fitness.data.DataSource param0) throws java.lang.NullPointerException {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ModifyDataMonitorOptions.Builder) this.getHInstance()).setModifyDataCollector(((com.huawei.hms.hihealth.data.DataCollector) ((param0) == null ? null : (param0.getHInstance()))))");
                com.huawei.hms.hihealth.options.ModifyDataMonitorOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.ModifyDataMonitorOptions.Builder) this.getHInstance()).setModifyDataCollector(((com.huawei.hms.hihealth.data.DataCollector) ((param0) == null ? null : (param0.getHInstance()))));
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.DataUpdateListenerRegistrationRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest.Builder) this.getGInstance()).setDataSource(((com.google.android.gms.fitness.data.DataSource) ((param0) == null ? null : (param0.getGInstance()))))");
                com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest.Builder gReturn = ((com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest.Builder) this.getGInstance()).setDataSource(((com.google.android.gms.fitness.data.DataSource) ((param0) == null ? null : (param0.getGInstance()))));
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.DataUpdateListenerRegistrationRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.DataUpdateListenerRegistrationRequest.Builder setDataType(org.xms.g.fitness.data.DataType param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ModifyDataMonitorOptions.Builder) this.getHInstance()).setModifyDataType(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))))");
                com.huawei.hms.hihealth.options.ModifyDataMonitorOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.ModifyDataMonitorOptions.Builder) this.getHInstance()).setModifyDataType(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))));
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.DataUpdateListenerRegistrationRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest.Builder) this.getGInstance()).setDataType(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))))");
                com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest.Builder gReturn = ((com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest.Builder) this.getGInstance()).setDataType(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))));
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.DataUpdateListenerRegistrationRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.DataUpdateListenerRegistrationRequest.Builder setPendingIntent(android.app.PendingIntent param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ModifyDataMonitorOptions.Builder) this.getHInstance()).setModifyDataIntent(param0)");
                com.huawei.hms.hihealth.options.ModifyDataMonitorOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.ModifyDataMonitorOptions.Builder) this.getHInstance()).setModifyDataIntent(param0);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.DataUpdateListenerRegistrationRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest.Builder) this.getGInstance()).setPendingIntent(param0)");
                com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest.Builder gReturn = ((com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest.Builder) this.getGInstance()).setPendingIntent(param0);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.DataUpdateListenerRegistrationRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public static org.xms.g.fitness.request.DataUpdateListenerRegistrationRequest.Builder dynamicCast(java.lang.Object param0) {
            return ((org.xms.g.fitness.request.DataUpdateListenerRegistrationRequest.Builder) param0);
        }
        
        public static boolean isInstance(java.lang.Object param0) {
            if (!(param0 instanceof org.xms.g.utils.XGettable)) {
                return false;
            }
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.options.ModifyDataMonitorOptions.Builder;
            } else {
                return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest.Builder;
            }
        }
    }
}