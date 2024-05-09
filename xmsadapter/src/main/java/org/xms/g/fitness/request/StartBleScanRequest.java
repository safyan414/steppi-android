package org.xms.g.fitness.request;

public class StartBleScanRequest extends org.xms.g.utils.XObject implements android.os.Parcelable {
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {
        
        public org.xms.g.fitness.request.StartBleScanRequest createFromParcel(android.os.Parcel param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                com.huawei.hms.hihealth.options.StartBleScanOptions hReturn = com.huawei.hms.hihealth.options.StartBleScanOptions.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.request.StartBleScanRequest(new org.xms.g.utils.XBox(null, hReturn));
            } else {
                com.google.android.gms.fitness.request.StartBleScanRequest gReturn = com.google.android.gms.fitness.request.StartBleScanRequest.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.request.StartBleScanRequest(new org.xms.g.utils.XBox(gReturn, null));
            }
        }
        
        public org.xms.g.fitness.request.StartBleScanRequest[] newArray(int param0) {
            return new org.xms.g.fitness.request.StartBleScanRequest[param0];
        }
    };
    
    public StartBleScanRequest(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public java.util.List<org.xms.g.fitness.data.DataType> getDataTypes() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.StartBleScanOptions) this.getHInstance()).getDataTypes()");
            java.util.List hReturn = ((com.huawei.hms.hihealth.options.StartBleScanOptions) this.getHInstance()).getDataTypes();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(hReturn, new org.xms.g.utils.Function<com.huawei.hms.hihealth.data.DataType, org.xms.g.fitness.data.DataType>() {
                
                public org.xms.g.fitness.data.DataType apply(com.huawei.hms.hihealth.data.DataType param0) {
                    return new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, param0));
                }
            }));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.StartBleScanRequest) this.getGInstance()).getDataTypes()");
            java.util.List gReturn = ((com.google.android.gms.fitness.request.StartBleScanRequest) this.getGInstance()).getDataTypes();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(gReturn, new org.xms.g.utils.Function<com.google.android.gms.fitness.data.DataType, org.xms.g.fitness.data.DataType>() {
                
                public org.xms.g.fitness.data.DataType apply(com.google.android.gms.fitness.data.DataType param0) {
                    return new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(param0, null));
                }
            }));
        }
    }
    
    public int getTimeoutSecs() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.StartBleScanOptions) this.getHInstance()).getTimeOverSecs()");
            return ((com.huawei.hms.hihealth.options.StartBleScanOptions) this.getHInstance()).getTimeOverSecs();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.StartBleScanRequest) this.getGInstance()).getTimeoutSecs()");
            return ((com.google.android.gms.fitness.request.StartBleScanRequest) this.getGInstance()).getTimeoutSecs();
        }
    }
    
    public java.lang.String toString() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.StartBleScanOptions) this.getHInstance()).toString()");
            return ((com.huawei.hms.hihealth.options.StartBleScanOptions) this.getHInstance()).toString();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.StartBleScanRequest) this.getGInstance()).toString()");
            return ((com.google.android.gms.fitness.request.StartBleScanRequest) this.getGInstance()).toString();
        }
    }
    
    public void writeToParcel(android.os.Parcel param0, int param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.StartBleScanOptions) this.getHInstance()).writeToParcel(param0, param1)");
            ((com.huawei.hms.hihealth.options.StartBleScanOptions) this.getHInstance()).writeToParcel(param0, param1);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.StartBleScanRequest) this.getGInstance()).writeToParcel(param0, param1)");
            ((com.google.android.gms.fitness.request.StartBleScanRequest) this.getGInstance()).writeToParcel(param0, param1);
        }
    }
    
    public int describeContents() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.request.StartBleScanRequest dynamicCast(java.lang.Object param0) {
        return ((org.xms.g.fitness.request.StartBleScanRequest) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.options.StartBleScanOptions;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.request.StartBleScanRequest;
        }
    }
    
    public static class Builder extends org.xms.g.utils.XObject {
        
        public Builder(org.xms.g.utils.XBox param0) {
            super(param0);
        }
        
        public Builder() {
            super(((org.xms.g.utils.XBox) null));
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                this.setHInstance(new com.huawei.hms.hihealth.options.StartBleScanOptions.Builder());
            } else {
                this.setGInstance(new com.google.android.gms.fitness.request.StartBleScanRequest.Builder());
            }
        }
        
        public org.xms.g.fitness.request.StartBleScanRequest build() {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.StartBleScanOptions.Builder) this.getHInstance()).build()");
                com.huawei.hms.hihealth.options.StartBleScanOptions hReturn = ((com.huawei.hms.hihealth.options.StartBleScanOptions.Builder) this.getHInstance()).build();
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.StartBleScanRequest(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.StartBleScanRequest.Builder) this.getGInstance()).build()");
                com.google.android.gms.fitness.request.StartBleScanRequest gReturn = ((com.google.android.gms.fitness.request.StartBleScanRequest.Builder) this.getGInstance()).build();
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.StartBleScanRequest(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.StartBleScanRequest.Builder setBleScanCallback(org.xms.g.fitness.request.BleScanCallback param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.StartBleScanOptions.Builder) this.getHInstance()).setDeviceScanCallback(((com.huawei.hms.hihealth.options.BleScanCallback) ((param0) == null ? null : (param0.getHInstance()))))");
                com.huawei.hms.hihealth.options.StartBleScanOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.StartBleScanOptions.Builder) this.getHInstance()).setDeviceScanCallback(((com.huawei.hms.hihealth.options.BleScanCallback) ((param0) == null ? null : (param0.getHInstance()))));
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.StartBleScanRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.StartBleScanRequest.Builder) this.getGInstance()).setBleScanCallback(((com.google.android.gms.fitness.request.BleScanCallback) ((param0) == null ? null : (param0.getGInstance()))))");
                com.google.android.gms.fitness.request.StartBleScanRequest.Builder gReturn = ((com.google.android.gms.fitness.request.StartBleScanRequest.Builder) this.getGInstance()).setBleScanCallback(((com.google.android.gms.fitness.request.BleScanCallback) ((param0) == null ? null : (param0.getGInstance()))));
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.StartBleScanRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.StartBleScanRequest.Builder setDataTypes(org.xms.g.fitness.data.DataType... param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.StartBleScanOptions.Builder) this.getHInstance()).setDataTypes(((com.huawei.hms.hihealth.data.DataType[]) org.xms.g.utils.Utils.genericArrayCopy(param0, com.huawei.hms.hihealth.data.DataType.class, x -> (com.huawei.hms.hihealth.data.DataType)x.getHInstance())))");
                com.huawei.hms.hihealth.options.StartBleScanOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.StartBleScanOptions.Builder) this.getHInstance()).setDataTypes(((com.huawei.hms.hihealth.data.DataType[]) org.xms.g.utils.Utils.genericArrayCopy(param0, com.huawei.hms.hihealth.data.DataType.class, x -> (com.huawei.hms.hihealth.data.DataType)x.getHInstance())));
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.StartBleScanRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.StartBleScanRequest.Builder) this.getGInstance()).setDataTypes(((com.google.android.gms.fitness.data.DataType[]) org.xms.g.utils.Utils.genericArrayCopy(param0, com.google.android.gms.fitness.data.DataType.class, x -> (com.google.android.gms.fitness.data.DataType)x.getGInstance())))");
                com.google.android.gms.fitness.request.StartBleScanRequest.Builder gReturn = ((com.google.android.gms.fitness.request.StartBleScanRequest.Builder) this.getGInstance()).setDataTypes(((com.google.android.gms.fitness.data.DataType[]) org.xms.g.utils.Utils.genericArrayCopy(param0, com.google.android.gms.fitness.data.DataType.class, x -> (com.google.android.gms.fitness.data.DataType)x.getGInstance())));
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.StartBleScanRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.StartBleScanRequest.Builder setTimeoutSecs(int param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.StartBleScanOptions.Builder) this.getHInstance()).setTimeOverSecs(param0)");
                com.huawei.hms.hihealth.options.StartBleScanOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.StartBleScanOptions.Builder) this.getHInstance()).setTimeOverSecs(param0);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.StartBleScanRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.StartBleScanRequest.Builder) this.getGInstance()).setTimeoutSecs(param0)");
                com.google.android.gms.fitness.request.StartBleScanRequest.Builder gReturn = ((com.google.android.gms.fitness.request.StartBleScanRequest.Builder) this.getGInstance()).setTimeoutSecs(param0);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.StartBleScanRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public static org.xms.g.fitness.request.StartBleScanRequest.Builder dynamicCast(java.lang.Object param0) {
            return ((org.xms.g.fitness.request.StartBleScanRequest.Builder) param0);
        }
        
        public static boolean isInstance(java.lang.Object param0) {
            if (!(param0 instanceof org.xms.g.utils.XGettable)) {
                return false;
            }
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.options.StartBleScanOptions.Builder;
            } else {
                return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.request.StartBleScanRequest.Builder;
            }
        }
    }
}