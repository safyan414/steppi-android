package org.xms.g.fitness.data;

public final class Device extends org.xms.g.utils.XObject implements android.os.Parcelable {
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {
        
        public org.xms.g.fitness.data.Device createFromParcel(android.os.Parcel param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                com.huawei.hms.hihealth.data.DeviceInfo hReturn = com.huawei.hms.hihealth.data.DeviceInfo.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.data.Device(new org.xms.g.utils.XBox(null, hReturn));
            } else {
                com.google.android.gms.fitness.data.Device gReturn = com.google.android.gms.fitness.data.Device.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.data.Device(new org.xms.g.utils.XBox(gReturn, null));
            }
        }
        
        public org.xms.g.fitness.data.Device[] newArray(int param0) {
            return new org.xms.g.fitness.data.Device[param0];
        }
    };
    
    public Device(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public Device(java.lang.String param0, java.lang.String param1, java.lang.String param2, int param3) {
        super(((org.xms.g.utils.XBox) null));
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            this.setHInstance(new com.huawei.hms.hihealth.data.DeviceInfo(param0, param1, param2, param3));
        } else {
            this.setGInstance(new com.google.android.gms.fitness.data.Device(param0, param1, param2, param3));
        }
    }
    
    public static int getTYPE_CHEST_STRAP() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static int getTYPE_HEAD_MOUNTED() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static int getTYPE_PHONE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DeviceInfo.TYPE_PHONE");
            return com.huawei.hms.hihealth.data.DeviceInfo.TYPE_PHONE;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Device.TYPE_PHONE");
            return com.google.android.gms.fitness.data.Device.TYPE_PHONE;
        }
    }
    
    public static int getTYPE_SCALE() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static int getTYPE_TABLET() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static int getTYPE_UNKNOWN() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DeviceInfo.TYPE_UNKNOWN");
            return com.huawei.hms.hihealth.data.DeviceInfo.TYPE_UNKNOWN;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Device.TYPE_UNKNOWN");
            return com.google.android.gms.fitness.data.Device.TYPE_UNKNOWN;
        }
    }
    
    public static int getTYPE_WATCH() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DeviceInfo.TYPE_SMART_WATCH");
            return com.huawei.hms.hihealth.data.DeviceInfo.TYPE_SMART_WATCH;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Device.TYPE_WATCH");
            return com.google.android.gms.fitness.data.Device.TYPE_WATCH;
        }
    }
    
    public final boolean equals(java.lang.Object param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DeviceInfo) this.getHInstance()).equals(param0)");
            return ((com.huawei.hms.hihealth.data.DeviceInfo) this.getHInstance()).equals(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Device) this.getGInstance()).equals(param0)");
            return ((com.google.android.gms.fitness.data.Device) this.getGInstance()).equals(param0);
        }
    }
    
    public static org.xms.g.fitness.data.Device getLocalDevice(android.content.Context param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DeviceInfo.getLocalDevice(param0)");
            com.huawei.hms.hihealth.data.DeviceInfo hReturn = com.huawei.hms.hihealth.data.DeviceInfo.getLocalDevice(param0);
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Device(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Device.getLocalDevice(param0)");
            com.google.android.gms.fitness.data.Device gReturn = com.google.android.gms.fitness.data.Device.getLocalDevice(param0);
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Device(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public final java.lang.String getManufacturer() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DeviceInfo) this.getHInstance()).getManufacturer()");
            return ((com.huawei.hms.hihealth.data.DeviceInfo) this.getHInstance()).getManufacturer();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Device) this.getGInstance()).getManufacturer()");
            return ((com.google.android.gms.fitness.data.Device) this.getGInstance()).getManufacturer();
        }
    }
    
    public final java.lang.String getModel() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DeviceInfo) this.getHInstance()).getModelName()");
            return ((com.huawei.hms.hihealth.data.DeviceInfo) this.getHInstance()).getModelName();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Device) this.getGInstance()).getModel()");
            return ((com.google.android.gms.fitness.data.Device) this.getGInstance()).getModel();
        }
    }
    
    public final int getType() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DeviceInfo) this.getHInstance()).getDeviceType()");
            return ((com.huawei.hms.hihealth.data.DeviceInfo) this.getHInstance()).getDeviceType();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Device) this.getGInstance()).getType()");
            return ((com.google.android.gms.fitness.data.Device) this.getGInstance()).getType();
        }
    }
    
    public final java.lang.String getUid() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DeviceInfo) this.getHInstance()).getUuid()");
            return ((com.huawei.hms.hihealth.data.DeviceInfo) this.getHInstance()).getUuid();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Device) this.getGInstance()).getUid()");
            return ((com.google.android.gms.fitness.data.Device) this.getGInstance()).getUid();
        }
    }
    
    public final int hashCode() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DeviceInfo) this.getHInstance()).hashCode()");
            return ((com.huawei.hms.hihealth.data.DeviceInfo) this.getHInstance()).hashCode();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Device) this.getGInstance()).hashCode()");
            return ((com.google.android.gms.fitness.data.Device) this.getGInstance()).hashCode();
        }
    }
    
    public final java.lang.String toString() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DeviceInfo) this.getHInstance()).toString()");
            return ((com.huawei.hms.hihealth.data.DeviceInfo) this.getHInstance()).toString();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Device) this.getGInstance()).toString()");
            return ((com.google.android.gms.fitness.data.Device) this.getGInstance()).toString();
        }
    }
    
    public final void writeToParcel(android.os.Parcel param0, int param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DeviceInfo) this.getHInstance()).writeToParcel(param0, param1)");
            ((com.huawei.hms.hihealth.data.DeviceInfo) this.getHInstance()).writeToParcel(param0, param1);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Device) this.getGInstance()).writeToParcel(param0, param1)");
            ((com.google.android.gms.fitness.data.Device) this.getGInstance()).writeToParcel(param0, param1);
        }
    }
    
    public int describeContents() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.data.Device dynamicCast(java.lang.Object param0) {
        return ((org.xms.g.fitness.data.Device) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.data.DeviceInfo;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.data.Device;
        }
    }
}