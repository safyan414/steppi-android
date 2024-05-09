package org.xms.g.fitness.result;

public class DataTypeResult extends org.xms.g.utils.XObject implements android.os.Parcelable, org.xms.g.common.api.Result {
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {
        
        public org.xms.g.fitness.result.DataTypeResult createFromParcel(android.os.Parcel param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                com.huawei.hms.hihealth.result.DataTypeResult hReturn = com.huawei.hms.hihealth.result.DataTypeResult.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.result.DataTypeResult(new org.xms.g.utils.XBox(null, hReturn));
            } else {
                com.google.android.gms.fitness.result.DataTypeResult gReturn = com.google.android.gms.fitness.result.DataTypeResult.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.result.DataTypeResult(new org.xms.g.utils.XBox(gReturn, null));
            }
        }
        
        public org.xms.g.fitness.result.DataTypeResult[] newArray(int param0) {
            return new org.xms.g.fitness.result.DataTypeResult[param0];
        }
    };
    
    public DataTypeResult(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public boolean equals(java.lang.Object param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.result.DataTypeResult) this.getHInstance()).equals(param0)");
            return ((com.huawei.hms.hihealth.result.DataTypeResult) this.getHInstance()).equals(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.result.DataTypeResult) this.getGInstance()).equals(param0)");
            return ((com.google.android.gms.fitness.result.DataTypeResult) this.getGInstance()).equals(param0);
        }
    }
    
    public org.xms.g.fitness.data.DataType getDataType() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.result.DataTypeResult) this.getHInstance()).getDataType()");
            com.huawei.hms.hihealth.data.DataType hReturn = ((com.huawei.hms.hihealth.result.DataTypeResult) this.getHInstance()).getDataType();
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.result.DataTypeResult) this.getGInstance()).getDataType()");
            com.google.android.gms.fitness.data.DataType gReturn = ((com.google.android.gms.fitness.result.DataTypeResult) this.getGInstance()).getDataType();
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.common.api.Status getStatus() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.result.DataTypeResult) this.getHInstance()).getStatus()");
            com.huawei.hms.support.api.client.Status hReturn = ((com.huawei.hms.hihealth.result.DataTypeResult) this.getHInstance()).getStatus();
            return ((hReturn) == null ? null : (new org.xms.g.common.api.Status(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.result.DataTypeResult) this.getGInstance()).getStatus()");
            com.google.android.gms.common.api.Status gReturn = ((com.google.android.gms.fitness.result.DataTypeResult) this.getGInstance()).getStatus();
            return ((gReturn) == null ? null : (new org.xms.g.common.api.Status(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public int hashCode() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.result.DataTypeResult) this.getHInstance()).hashCode()");
            return ((com.huawei.hms.hihealth.result.DataTypeResult) this.getHInstance()).hashCode();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.result.DataTypeResult) this.getGInstance()).hashCode()");
            return ((com.google.android.gms.fitness.result.DataTypeResult) this.getGInstance()).hashCode();
        }
    }
    
    public java.lang.String toString() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.result.DataTypeResult) this.getHInstance()).toString()");
            return ((com.huawei.hms.hihealth.result.DataTypeResult) this.getHInstance()).toString();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.result.DataTypeResult) this.getGInstance()).toString()");
            return ((com.google.android.gms.fitness.result.DataTypeResult) this.getGInstance()).toString();
        }
    }
    
    public void writeToParcel(android.os.Parcel param0, int param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.result.DataTypeResult) this.getHInstance()).writeToParcel(param0, param1)");
            ((com.huawei.hms.hihealth.result.DataTypeResult) this.getHInstance()).writeToParcel(param0, param1);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.result.DataTypeResult) this.getGInstance()).writeToParcel(param0, param1)");
            ((com.google.android.gms.fitness.result.DataTypeResult) this.getGInstance()).writeToParcel(param0, param1);
        }
    }
    
    public int describeContents() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.result.DataTypeResult dynamicCast(java.lang.Object param0) {
        if (param0 instanceof org.xms.g.fitness.result.DataTypeResult) {
            return ((org.xms.g.fitness.result.DataTypeResult) param0);
        }
        if (param0 instanceof org.xms.g.utils.XGettable) {
            com.google.android.gms.fitness.result.DataTypeResult gReturn = ((com.google.android.gms.fitness.result.DataTypeResult) ((org.xms.g.utils.XGettable) param0).getGInstance());
            com.huawei.hms.hihealth.result.DataTypeResult hReturn = ((com.huawei.hms.hihealth.result.DataTypeResult) ((org.xms.g.utils.XGettable) param0).getHInstance());
            return new org.xms.g.fitness.result.DataTypeResult(new org.xms.g.utils.XBox(gReturn, hReturn));
        }
        return ((org.xms.g.fitness.result.DataTypeResult) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.result.DataTypeResult;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.result.DataTypeResult;
        }
    }
}