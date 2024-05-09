package org.xms.g.fitness.data;

public final class Value extends org.xms.g.utils.XObject implements android.os.Parcelable {
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {
        
        public org.xms.g.fitness.data.Value createFromParcel(android.os.Parcel param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                com.huawei.hms.hihealth.data.Value hReturn = com.huawei.hms.hihealth.data.Value.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.data.Value(new org.xms.g.utils.XBox(null, hReturn));
            } else {
                com.google.android.gms.fitness.data.Value gReturn = com.google.android.gms.fitness.data.Value.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.data.Value(new org.xms.g.utils.XBox(gReturn, null));
            }
        }
        
        public org.xms.g.fitness.data.Value[] newArray(int param0) {
            return new org.xms.g.fitness.data.Value[param0];
        }
    };
    
    public Value(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public final java.lang.String asActivity() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.Value) this.getHInstance()).asActivityType()");
            return ((com.huawei.hms.hihealth.data.Value) this.getHInstance()).asActivityType();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Value) this.getGInstance()).asActivity()");
            return ((com.google.android.gms.fitness.data.Value) this.getGInstance()).asActivity();
        }
    }
    
    public final float asFloat() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.Value) this.getHInstance()).asFloatValue()");
            return ((com.huawei.hms.hihealth.data.Value) this.getHInstance()).asFloatValue();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Value) this.getGInstance()).asFloat()");
            return ((com.google.android.gms.fitness.data.Value) this.getGInstance()).asFloat();
        }
    }
    
    public final int asInt() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.Value) this.getHInstance()).asIntValue()");
            return ((com.huawei.hms.hihealth.data.Value) this.getHInstance()).asIntValue();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Value) this.getGInstance()).asInt()");
            return ((com.google.android.gms.fitness.data.Value) this.getGInstance()).asInt();
        }
    }
    
    public final java.lang.String asString() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.Value) this.getHInstance()).asStringValue()");
            return ((com.huawei.hms.hihealth.data.Value) this.getHInstance()).asStringValue();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Value) this.getGInstance()).asString()");
            return ((com.google.android.gms.fitness.data.Value) this.getGInstance()).asString();
        }
    }
    
    public final void clearKey(java.lang.String param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.Value) this.getHInstance()).removeMapValue(param0)");
            ((com.huawei.hms.hihealth.data.Value) this.getHInstance()).removeMapValue(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Value) this.getGInstance()).clearKey(param0)");
            ((com.google.android.gms.fitness.data.Value) this.getGInstance()).clearKey(param0);
        }
    }
    
    public final boolean equals(java.lang.Object param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.Value) this.getHInstance()).equals(param0)");
            return ((com.huawei.hms.hihealth.data.Value) this.getHInstance()).equals(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Value) this.getGInstance()).equals(param0)");
            return ((com.google.android.gms.fitness.data.Value) this.getGInstance()).equals(param0);
        }
    }
    
    public final int getFormat() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.Value) this.getHInstance()).getFormat()");
            return ((com.huawei.hms.hihealth.data.Value) this.getHInstance()).getFormat();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Value) this.getGInstance()).getFormat()");
            return ((com.google.android.gms.fitness.data.Value) this.getGInstance()).getFormat();
        }
    }
    
    public final java.lang.Float getKeyValue(java.lang.String param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.Value) this.getHInstance()).getMapValue(param0)");
            return ((com.huawei.hms.hihealth.data.Value) this.getHInstance()).getMapValue(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Value) this.getGInstance()).getKeyValue(param0)");
            return ((com.google.android.gms.fitness.data.Value) this.getGInstance()).getKeyValue(param0);
        }
    }
    
    public final int hashCode() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.Value) this.getHInstance()).hashCode()");
            return ((com.huawei.hms.hihealth.data.Value) this.getHInstance()).hashCode();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Value) this.getGInstance()).hashCode()");
            return ((com.google.android.gms.fitness.data.Value) this.getGInstance()).hashCode();
        }
    }
    
    public final boolean isSet() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.Value) this.getHInstance()).isSet()");
            return ((com.huawei.hms.hihealth.data.Value) this.getHInstance()).isSet();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Value) this.getGInstance()).isSet()");
            return ((com.google.android.gms.fitness.data.Value) this.getGInstance()).isSet();
        }
    }
    
    public final void setActivity(java.lang.String param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.Value) this.getHInstance()).setActivityIndexValue(param0)");
            ((com.huawei.hms.hihealth.data.Value) this.getHInstance()).setActivityIndexValue(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Value) this.getGInstance()).setActivity(param0)");
            ((com.google.android.gms.fitness.data.Value) this.getGInstance()).setActivity(param0);
        }
    }
    
    public final void setFloat(float param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.Value) this.getHInstance()).setFloatValue(param0)");
            ((com.huawei.hms.hihealth.data.Value) this.getHInstance()).setFloatValue(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Value) this.getGInstance()).setFloat(param0)");
            ((com.google.android.gms.fitness.data.Value) this.getGInstance()).setFloat(param0);
        }
    }
    
    public final void setInt(int param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.Value) this.getHInstance()).setIntValue(param0)");
            ((com.huawei.hms.hihealth.data.Value) this.getHInstance()).setIntValue(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Value) this.getGInstance()).setInt(param0)");
            ((com.google.android.gms.fitness.data.Value) this.getGInstance()).setInt(param0);
        }
    }
    
    public final void setKeyValue(java.lang.String param0, float param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.Value) this.getHInstance()).setKeyValue(param0, param1)");
            ((com.huawei.hms.hihealth.data.Value) this.getHInstance()).setKeyValue(param0, param1);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Value) this.getGInstance()).setKeyValue(param0, param1)");
            ((com.google.android.gms.fitness.data.Value) this.getGInstance()).setKeyValue(param0, param1);
        }
    }
    
    public final void setString(java.lang.String param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.Value) this.getHInstance()).setStringValue(param0)");
            ((com.huawei.hms.hihealth.data.Value) this.getHInstance()).setStringValue(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Value) this.getGInstance()).setString(param0)");
            ((com.google.android.gms.fitness.data.Value) this.getGInstance()).setString(param0);
        }
    }
    
    public final java.lang.String toString() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.Value) this.getHInstance()).toString()");
            return ((com.huawei.hms.hihealth.data.Value) this.getHInstance()).toString();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Value) this.getGInstance()).toString()");
            return ((com.google.android.gms.fitness.data.Value) this.getGInstance()).toString();
        }
    }
    
    public final void writeToParcel(android.os.Parcel param0, int param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.Value) this.getHInstance()).writeToParcel(param0, param1)");
            ((com.huawei.hms.hihealth.data.Value) this.getHInstance()).writeToParcel(param0, param1);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Value) this.getGInstance()).writeToParcel(param0, param1)");
            ((com.google.android.gms.fitness.data.Value) this.getGInstance()).writeToParcel(param0, param1);
        }
    }
    
    public int describeContents() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.data.Value dynamicCast(java.lang.Object param0) {
        return ((org.xms.g.fitness.data.Value) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.data.Value;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.data.Value;
        }
    }
}