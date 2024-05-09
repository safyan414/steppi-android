package org.xms.g.fitness.request;

public class DataSourcesRequest extends org.xms.g.utils.XObject implements android.os.Parcelable {
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {
        
        public org.xms.g.fitness.request.DataSourcesRequest createFromParcel(android.os.Parcel param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                com.huawei.hms.hihealth.options.DataCollectorsOptions hReturn = com.huawei.hms.hihealth.options.DataCollectorsOptions.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.request.DataSourcesRequest(new org.xms.g.utils.XBox(null, hReturn));
            } else {
                com.google.android.gms.fitness.request.DataSourcesRequest gReturn = com.google.android.gms.fitness.request.DataSourcesRequest.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.request.DataSourcesRequest(new org.xms.g.utils.XBox(gReturn, null));
            }
        }
        
        public org.xms.g.fitness.request.DataSourcesRequest[] newArray(int param0) {
            return new org.xms.g.fitness.request.DataSourcesRequest[param0];
        }
    };
    
    public DataSourcesRequest(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public java.util.List<org.xms.g.fitness.data.DataType> getDataTypes() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.DataCollectorsOptions) this.getHInstance()).getDataTypes()");
            java.util.List hReturn = ((com.huawei.hms.hihealth.options.DataCollectorsOptions) this.getHInstance()).getDataTypes();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(hReturn, new org.xms.g.utils.Function<com.huawei.hms.hihealth.data.DataType, org.xms.g.fitness.data.DataType>() {
                
                public org.xms.g.fitness.data.DataType apply(com.huawei.hms.hihealth.data.DataType param0) {
                    return new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, param0));
                }
            }));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataSourcesRequest) this.getGInstance()).getDataTypes()");
            java.util.List gReturn = ((com.google.android.gms.fitness.request.DataSourcesRequest) this.getGInstance()).getDataTypes();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(gReturn, new org.xms.g.utils.Function<com.google.android.gms.fitness.data.DataType, org.xms.g.fitness.data.DataType>() {
                
                public org.xms.g.fitness.data.DataType apply(com.google.android.gms.fitness.data.DataType param0) {
                    return new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(param0, null));
                }
            }));
        }
    }
    
    public java.lang.String toString() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.DataCollectorsOptions) this.getHInstance()).toString()");
            return ((com.huawei.hms.hihealth.options.DataCollectorsOptions) this.getHInstance()).toString();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataSourcesRequest) this.getGInstance()).toString()");
            return ((com.google.android.gms.fitness.request.DataSourcesRequest) this.getGInstance()).toString();
        }
    }
    
    public void writeToParcel(android.os.Parcel param0, int param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.DataCollectorsOptions) this.getHInstance()).writeToParcel(param0, param1)");
            ((com.huawei.hms.hihealth.options.DataCollectorsOptions) this.getHInstance()).writeToParcel(param0, param1);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataSourcesRequest) this.getGInstance()).writeToParcel(param0, param1)");
            ((com.google.android.gms.fitness.request.DataSourcesRequest) this.getGInstance()).writeToParcel(param0, param1);
        }
    }
    
    public int describeContents() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.request.DataSourcesRequest dynamicCast(java.lang.Object param0) {
        return ((org.xms.g.fitness.request.DataSourcesRequest) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.options.DataCollectorsOptions;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.request.DataSourcesRequest;
        }
    }
    
    public static class Builder extends org.xms.g.utils.XObject {
        
        public Builder(org.xms.g.utils.XBox param0) {
            super(param0);
        }
        
        public Builder() {
            super(((org.xms.g.utils.XBox) null));
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                this.setHInstance(new com.huawei.hms.hihealth.options.DataCollectorsOptions.Builder());
            } else {
                this.setGInstance(new com.google.android.gms.fitness.request.DataSourcesRequest.Builder());
            }
        }
        
        public org.xms.g.fitness.request.DataSourcesRequest build() {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.DataCollectorsOptions.Builder) this.getHInstance()).build()");
                com.huawei.hms.hihealth.options.DataCollectorsOptions hReturn = ((com.huawei.hms.hihealth.options.DataCollectorsOptions.Builder) this.getHInstance()).build();
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.DataSourcesRequest(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataSourcesRequest.Builder) this.getGInstance()).build()");
                com.google.android.gms.fitness.request.DataSourcesRequest gReturn = ((com.google.android.gms.fitness.request.DataSourcesRequest.Builder) this.getGInstance()).build();
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.DataSourcesRequest(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.DataSourcesRequest.Builder setDataSourceTypes(int... param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.DataCollectorsOptions.Builder) this.getHInstance()).setDataCollectorTypes(param0)");
                com.huawei.hms.hihealth.options.DataCollectorsOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.DataCollectorsOptions.Builder) this.getHInstance()).setDataCollectorTypes(param0);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.DataSourcesRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataSourcesRequest.Builder) this.getGInstance()).setDataSourceTypes(param0)");
                com.google.android.gms.fitness.request.DataSourcesRequest.Builder gReturn = ((com.google.android.gms.fitness.request.DataSourcesRequest.Builder) this.getGInstance()).setDataSourceTypes(param0);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.DataSourcesRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.DataSourcesRequest.Builder setDataTypes(org.xms.g.fitness.data.DataType... param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.DataCollectorsOptions.Builder) this.getHInstance()).setDataTypes(((com.huawei.hms.hihealth.data.DataType[]) org.xms.g.utils.Utils.genericArrayCopy(param0, com.huawei.hms.hihealth.data.DataType.class, x -> (com.huawei.hms.hihealth.data.DataType)x.getHInstance())))");
                com.huawei.hms.hihealth.options.DataCollectorsOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.DataCollectorsOptions.Builder) this.getHInstance()).setDataTypes(((com.huawei.hms.hihealth.data.DataType[]) org.xms.g.utils.Utils.genericArrayCopy(param0, com.huawei.hms.hihealth.data.DataType.class, x -> (com.huawei.hms.hihealth.data.DataType)x.getHInstance())));
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.DataSourcesRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataSourcesRequest.Builder) this.getGInstance()).setDataTypes(((com.google.android.gms.fitness.data.DataType[]) org.xms.g.utils.Utils.genericArrayCopy(param0, com.google.android.gms.fitness.data.DataType.class, x -> (com.google.android.gms.fitness.data.DataType)x.getGInstance())))");
                com.google.android.gms.fitness.request.DataSourcesRequest.Builder gReturn = ((com.google.android.gms.fitness.request.DataSourcesRequest.Builder) this.getGInstance()).setDataTypes(((com.google.android.gms.fitness.data.DataType[]) org.xms.g.utils.Utils.genericArrayCopy(param0, com.google.android.gms.fitness.data.DataType.class, x -> (com.google.android.gms.fitness.data.DataType)x.getGInstance())));
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.DataSourcesRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public static org.xms.g.fitness.request.DataSourcesRequest.Builder dynamicCast(java.lang.Object param0) {
            return ((org.xms.g.fitness.request.DataSourcesRequest.Builder) param0);
        }
        
        public static boolean isInstance(java.lang.Object param0) {
            if (!(param0 instanceof org.xms.g.utils.XGettable)) {
                return false;
            }
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.options.DataCollectorsOptions.Builder;
            } else {
                return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.request.DataSourcesRequest.Builder;
            }
        }
    }
}