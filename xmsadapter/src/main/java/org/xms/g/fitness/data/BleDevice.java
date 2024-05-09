package org.xms.g.fitness.data;

public class BleDevice extends org.xms.g.utils.XObject implements android.os.Parcelable {
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {
        
        public org.xms.g.fitness.data.BleDevice createFromParcel(android.os.Parcel param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                com.huawei.hms.hihealth.data.BleDeviceInfo hReturn = com.huawei.hms.hihealth.data.BleDeviceInfo.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.data.BleDevice(new org.xms.g.utils.XBox(null, hReturn));
            } else {
                com.google.android.gms.fitness.data.BleDevice gReturn = com.google.android.gms.fitness.data.BleDevice.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.data.BleDevice(new org.xms.g.utils.XBox(gReturn, null));
            }
        }
        
        public org.xms.g.fitness.data.BleDevice[] newArray(int param0) {
            return new org.xms.g.fitness.data.BleDevice[param0];
        }
    };
    
    public BleDevice(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public boolean equals(java.lang.Object param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.BleDeviceInfo) this.getHInstance()).equals(param0)");
            return ((com.huawei.hms.hihealth.data.BleDeviceInfo) this.getHInstance()).equals(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.BleDevice) this.getGInstance()).equals(param0)");
            return ((com.google.android.gms.fitness.data.BleDevice) this.getGInstance()).equals(param0);
        }
    }
    
    public java.lang.String getAddress() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.BleDeviceInfo) this.getHInstance()).getDeviceAddress()");
            return ((com.huawei.hms.hihealth.data.BleDeviceInfo) this.getHInstance()).getDeviceAddress();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.BleDevice) this.getGInstance()).getAddress()");
            return ((com.google.android.gms.fitness.data.BleDevice) this.getGInstance()).getAddress();
        }
    }
    
    public java.util.List<org.xms.g.fitness.data.DataType> getDataTypes() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.BleDeviceInfo) this.getHInstance()).getDataTypes()");
            java.util.List hReturn = ((com.huawei.hms.hihealth.data.BleDeviceInfo) this.getHInstance()).getDataTypes();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(hReturn, new org.xms.g.utils.Function<com.huawei.hms.hihealth.data.DataType, org.xms.g.fitness.data.DataType>() {
                
                public org.xms.g.fitness.data.DataType apply(com.huawei.hms.hihealth.data.DataType param0) {
                    return new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, param0));
                }
            }));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.BleDevice) this.getGInstance()).getDataTypes()");
            java.util.List gReturn = ((com.google.android.gms.fitness.data.BleDevice) this.getGInstance()).getDataTypes();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(gReturn, new org.xms.g.utils.Function<com.google.android.gms.fitness.data.DataType, org.xms.g.fitness.data.DataType>() {
                
                public org.xms.g.fitness.data.DataType apply(com.google.android.gms.fitness.data.DataType param0) {
                    return new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(param0, null));
                }
            }));
        }
    }
    
    public java.lang.String getName() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.BleDeviceInfo) this.getHInstance()).getDeviceName()");
            return ((com.huawei.hms.hihealth.data.BleDeviceInfo) this.getHInstance()).getDeviceName();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.BleDevice) this.getGInstance()).getName()");
            return ((com.google.android.gms.fitness.data.BleDevice) this.getGInstance()).getName();
        }
    }
    
    public java.util.List<java.lang.String> getSupportedProfiles() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.BleDeviceInfo) this.getHInstance()).getAvailableProfiles()");
            return ((com.huawei.hms.hihealth.data.BleDeviceInfo) this.getHInstance()).getAvailableProfiles();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.BleDevice) this.getGInstance()).getSupportedProfiles()");
            return ((com.google.android.gms.fitness.data.BleDevice) this.getGInstance()).getSupportedProfiles();
        }
    }
    
    public int hashCode() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.BleDeviceInfo) this.getHInstance()).hashCode()");
            return ((com.huawei.hms.hihealth.data.BleDeviceInfo) this.getHInstance()).hashCode();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.BleDevice) this.getGInstance()).hashCode()");
            return ((com.google.android.gms.fitness.data.BleDevice) this.getGInstance()).hashCode();
        }
    }
    
    public java.lang.String toString() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.BleDeviceInfo) this.getHInstance()).toString()");
            return ((com.huawei.hms.hihealth.data.BleDeviceInfo) this.getHInstance()).toString();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.BleDevice) this.getGInstance()).toString()");
            return ((com.google.android.gms.fitness.data.BleDevice) this.getGInstance()).toString();
        }
    }
    
    public void writeToParcel(android.os.Parcel param0, int param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.BleDeviceInfo) this.getHInstance()).writeToParcel(param0, param1)");
            ((com.huawei.hms.hihealth.data.BleDeviceInfo) this.getHInstance()).writeToParcel(param0, param1);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.BleDevice) this.getGInstance()).writeToParcel(param0, param1)");
            ((com.google.android.gms.fitness.data.BleDevice) this.getGInstance()).writeToParcel(param0, param1);
        }
    }
    
    public int describeContents() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.data.BleDevice dynamicCast(java.lang.Object param0) {
        return ((org.xms.g.fitness.data.BleDevice) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.data.BleDeviceInfo;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.data.BleDevice;
        }
    }
}