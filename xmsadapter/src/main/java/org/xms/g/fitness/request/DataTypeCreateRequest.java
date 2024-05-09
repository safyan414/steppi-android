package org.xms.g.fitness.request;

public class DataTypeCreateRequest extends org.xms.g.utils.XObject implements android.os.Parcelable {
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {
        
        public org.xms.g.fitness.request.DataTypeCreateRequest createFromParcel(android.os.Parcel param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                com.huawei.hms.hihealth.options.DataTypeAddOptions hReturn = com.huawei.hms.hihealth.options.DataTypeAddOptions.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.request.DataTypeCreateRequest(new org.xms.g.utils.XBox(null, hReturn));
            } else {
                com.google.android.gms.fitness.request.DataTypeCreateRequest gReturn = com.google.android.gms.fitness.request.DataTypeCreateRequest.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.request.DataTypeCreateRequest(new org.xms.g.utils.XBox(gReturn, null));
            }
        }
        
        public org.xms.g.fitness.request.DataTypeCreateRequest[] newArray(int param0) {
            return new org.xms.g.fitness.request.DataTypeCreateRequest[param0];
        }
    };
    
    public DataTypeCreateRequest(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public boolean equals(java.lang.Object param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.DataTypeAddOptions) this.getHInstance()).equals(param0)");
            return ((com.huawei.hms.hihealth.options.DataTypeAddOptions) this.getHInstance()).equals(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataTypeCreateRequest) this.getGInstance()).equals(param0)");
            return ((com.google.android.gms.fitness.request.DataTypeCreateRequest) this.getGInstance()).equals(param0);
        }
    }
    
    public java.util.List<org.xms.g.fitness.data.Field> getFields() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.DataTypeAddOptions) this.getHInstance()).getFields()");
            java.util.List hReturn = ((com.huawei.hms.hihealth.options.DataTypeAddOptions) this.getHInstance()).getFields();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(hReturn, new org.xms.g.utils.Function<com.huawei.hms.hihealth.data.Field, org.xms.g.fitness.data.Field>() {
                
                public org.xms.g.fitness.data.Field apply(com.huawei.hms.hihealth.data.Field param0) {
                    return new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, param0));
                }
            }));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataTypeCreateRequest) this.getGInstance()).getFields()");
            java.util.List gReturn = ((com.google.android.gms.fitness.request.DataTypeCreateRequest) this.getGInstance()).getFields();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(gReturn, new org.xms.g.utils.Function<com.google.android.gms.fitness.data.Field, org.xms.g.fitness.data.Field>() {
                
                public org.xms.g.fitness.data.Field apply(com.google.android.gms.fitness.data.Field param0) {
                    return new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(param0, null));
                }
            }));
        }
    }
    
    public java.lang.String getName() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.DataTypeAddOptions) this.getHInstance()).getName()");
            return ((com.huawei.hms.hihealth.options.DataTypeAddOptions) this.getHInstance()).getName();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataTypeCreateRequest) this.getGInstance()).getName()");
            return ((com.google.android.gms.fitness.request.DataTypeCreateRequest) this.getGInstance()).getName();
        }
    }
    
    public int hashCode() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.DataTypeAddOptions) this.getHInstance()).hashCode()");
            return ((com.huawei.hms.hihealth.options.DataTypeAddOptions) this.getHInstance()).hashCode();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataTypeCreateRequest) this.getGInstance()).hashCode()");
            return ((com.google.android.gms.fitness.request.DataTypeCreateRequest) this.getGInstance()).hashCode();
        }
    }
    
    public java.lang.String toString() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.DataTypeAddOptions) this.getHInstance()).toString()");
            return ((com.huawei.hms.hihealth.options.DataTypeAddOptions) this.getHInstance()).toString();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataTypeCreateRequest) this.getGInstance()).toString()");
            return ((com.google.android.gms.fitness.request.DataTypeCreateRequest) this.getGInstance()).toString();
        }
    }
    
    public void writeToParcel(android.os.Parcel param0, int param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.DataTypeAddOptions) this.getHInstance()).writeToParcel(param0, param1)");
            ((com.huawei.hms.hihealth.options.DataTypeAddOptions) this.getHInstance()).writeToParcel(param0, param1);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataTypeCreateRequest) this.getGInstance()).writeToParcel(param0, param1)");
            ((com.google.android.gms.fitness.request.DataTypeCreateRequest) this.getGInstance()).writeToParcel(param0, param1);
        }
    }
    
    public int describeContents() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.request.DataTypeCreateRequest dynamicCast(java.lang.Object param0) {
        return ((org.xms.g.fitness.request.DataTypeCreateRequest) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.options.DataTypeAddOptions;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.request.DataTypeCreateRequest;
        }
    }
    
    public static class Builder extends org.xms.g.utils.XObject {
        
        public Builder(org.xms.g.utils.XBox param0) {
            super(param0);
        }
        
        public Builder() {
            super(((org.xms.g.utils.XBox) null));
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                this.setHInstance(new com.huawei.hms.hihealth.options.DataTypeAddOptions.Builder());
            } else {
                this.setGInstance(new com.google.android.gms.fitness.request.DataTypeCreateRequest.Builder());
            }
        }
        
        public org.xms.g.fitness.request.DataTypeCreateRequest.Builder addField(java.lang.String param0, int param1) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.DataTypeAddOptions.Builder) this.getHInstance()).addField(param0, param1)");
                com.huawei.hms.hihealth.options.DataTypeAddOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.DataTypeAddOptions.Builder) this.getHInstance()).addField(param0, param1);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.DataTypeCreateRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataTypeCreateRequest.Builder) this.getGInstance()).addField(param0, param1)");
                com.google.android.gms.fitness.request.DataTypeCreateRequest.Builder gReturn = ((com.google.android.gms.fitness.request.DataTypeCreateRequest.Builder) this.getGInstance()).addField(param0, param1);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.DataTypeCreateRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.DataTypeCreateRequest.Builder addField(org.xms.g.fitness.data.Field param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.DataTypeAddOptions.Builder) this.getHInstance()).addField(((com.huawei.hms.hihealth.data.Field) ((param0) == null ? null : (param0.getHInstance()))))");
                com.huawei.hms.hihealth.options.DataTypeAddOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.DataTypeAddOptions.Builder) this.getHInstance()).addField(((com.huawei.hms.hihealth.data.Field) ((param0) == null ? null : (param0.getHInstance()))));
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.DataTypeCreateRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataTypeCreateRequest.Builder) this.getGInstance()).addField(((com.google.android.gms.fitness.data.Field) ((param0) == null ? null : (param0.getGInstance()))))");
                com.google.android.gms.fitness.request.DataTypeCreateRequest.Builder gReturn = ((com.google.android.gms.fitness.request.DataTypeCreateRequest.Builder) this.getGInstance()).addField(((com.google.android.gms.fitness.data.Field) ((param0) == null ? null : (param0.getGInstance()))));
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.DataTypeCreateRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.DataTypeCreateRequest build() {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.DataTypeAddOptions.Builder) this.getHInstance()).build()");
                com.huawei.hms.hihealth.options.DataTypeAddOptions hReturn = ((com.huawei.hms.hihealth.options.DataTypeAddOptions.Builder) this.getHInstance()).build();
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.DataTypeCreateRequest(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataTypeCreateRequest.Builder) this.getGInstance()).build()");
                com.google.android.gms.fitness.request.DataTypeCreateRequest gReturn = ((com.google.android.gms.fitness.request.DataTypeCreateRequest.Builder) this.getGInstance()).build();
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.DataTypeCreateRequest(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.DataTypeCreateRequest.Builder setName(java.lang.String param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.DataTypeAddOptions.Builder) this.getHInstance()).setName(param0)");
                com.huawei.hms.hihealth.options.DataTypeAddOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.DataTypeAddOptions.Builder) this.getHInstance()).setName(param0);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.DataTypeCreateRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataTypeCreateRequest.Builder) this.getGInstance()).setName(param0)");
                com.google.android.gms.fitness.request.DataTypeCreateRequest.Builder gReturn = ((com.google.android.gms.fitness.request.DataTypeCreateRequest.Builder) this.getGInstance()).setName(param0);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.DataTypeCreateRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public static org.xms.g.fitness.request.DataTypeCreateRequest.Builder dynamicCast(java.lang.Object param0) {
            return ((org.xms.g.fitness.request.DataTypeCreateRequest.Builder) param0);
        }
        
        public static boolean isInstance(java.lang.Object param0) {
            if (!(param0 instanceof org.xms.g.utils.XGettable)) {
                return false;
            }
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.options.DataTypeAddOptions.Builder;
            } else {
                return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.request.DataTypeCreateRequest.Builder;
            }
        }
    }
}