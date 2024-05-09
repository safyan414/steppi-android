package org.xms.g.fitness.result;

public class BleDevicesResult extends org.xms.g.utils.XObject implements android.os.Parcelable, org.xms.g.common.api.Result {
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {
        
        public org.xms.g.fitness.result.BleDevicesResult createFromParcel(android.os.Parcel param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                com.huawei.hms.hihealth.result.BleDeviceInfosResult hReturn = com.huawei.hms.hihealth.result.BleDeviceInfosResult.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.result.BleDevicesResult(new org.xms.g.utils.XBox(null, hReturn));
            } else {
                com.google.android.gms.fitness.result.BleDevicesResult gReturn = com.google.android.gms.fitness.result.BleDevicesResult.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.result.BleDevicesResult(new org.xms.g.utils.XBox(gReturn, null));
            }
        }
        
        public org.xms.g.fitness.result.BleDevicesResult[] newArray(int param0) {
            return new org.xms.g.fitness.result.BleDevicesResult[param0];
        }
    };
    
    public BleDevicesResult(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public boolean equals(java.lang.Object param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.result.BleDeviceInfosResult) this.getHInstance()).equals(param0)");
            return ((com.huawei.hms.hihealth.result.BleDeviceInfosResult) this.getHInstance()).equals(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.result.BleDevicesResult) this.getGInstance()).equals(param0)");
            return ((com.google.android.gms.fitness.result.BleDevicesResult) this.getGInstance()).equals(param0);
        }
    }
    
    public java.util.List<org.xms.g.fitness.data.BleDevice> getClaimedBleDevices(org.xms.g.fitness.data.DataType param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.result.BleDeviceInfosResult) this.getHInstance()).getSavedDevices(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))))");
            java.util.List hReturn = ((com.huawei.hms.hihealth.result.BleDeviceInfosResult) this.getHInstance()).getSavedDevices(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))));
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(hReturn, new org.xms.g.utils.Function<com.huawei.hms.hihealth.data.BleDeviceInfo, org.xms.g.fitness.data.BleDevice>() {
                
                public org.xms.g.fitness.data.BleDevice apply(com.huawei.hms.hihealth.data.BleDeviceInfo param0) {
                    return new org.xms.g.fitness.data.BleDevice(new org.xms.g.utils.XBox(null, param0));
                }
            }));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.result.BleDevicesResult) this.getGInstance()).getClaimedBleDevices(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))))");
            java.util.List gReturn = ((com.google.android.gms.fitness.result.BleDevicesResult) this.getGInstance()).getClaimedBleDevices(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))));
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(gReturn, new org.xms.g.utils.Function<com.google.android.gms.fitness.data.BleDevice, org.xms.g.fitness.data.BleDevice>() {
                
                public org.xms.g.fitness.data.BleDevice apply(com.google.android.gms.fitness.data.BleDevice param0) {
                    return new org.xms.g.fitness.data.BleDevice(new org.xms.g.utils.XBox(param0, null));
                }
            }));
        }
    }
    
    public java.util.List<org.xms.g.fitness.data.BleDevice> getClaimedBleDevices() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.result.BleDeviceInfosResult) this.getHInstance()).getSavedDevices()");
            java.util.List hReturn = ((com.huawei.hms.hihealth.result.BleDeviceInfosResult) this.getHInstance()).getSavedDevices();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(hReturn, new org.xms.g.utils.Function<com.huawei.hms.hihealth.data.BleDeviceInfo, org.xms.g.fitness.data.BleDevice>() {
                
                public org.xms.g.fitness.data.BleDevice apply(com.huawei.hms.hihealth.data.BleDeviceInfo param0) {
                    return new org.xms.g.fitness.data.BleDevice(new org.xms.g.utils.XBox(null, param0));
                }
            }));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.result.BleDevicesResult) this.getGInstance()).getClaimedBleDevices()");
            java.util.List gReturn = ((com.google.android.gms.fitness.result.BleDevicesResult) this.getGInstance()).getClaimedBleDevices();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(gReturn, new org.xms.g.utils.Function<com.google.android.gms.fitness.data.BleDevice, org.xms.g.fitness.data.BleDevice>() {
                
                public org.xms.g.fitness.data.BleDevice apply(com.google.android.gms.fitness.data.BleDevice param0) {
                    return new org.xms.g.fitness.data.BleDevice(new org.xms.g.utils.XBox(param0, null));
                }
            }));
        }
    }
    
    public org.xms.g.common.api.Status getStatus() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.result.BleDeviceInfosResult) this.getHInstance()).getStatus()");
            com.huawei.hms.support.api.client.Status hReturn = ((com.huawei.hms.hihealth.result.BleDeviceInfosResult) this.getHInstance()).getStatus();
            return ((hReturn) == null ? null : (new org.xms.g.common.api.Status(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.result.BleDevicesResult) this.getGInstance()).getStatus()");
            com.google.android.gms.common.api.Status gReturn = ((com.google.android.gms.fitness.result.BleDevicesResult) this.getGInstance()).getStatus();
            return ((gReturn) == null ? null : (new org.xms.g.common.api.Status(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public int hashCode() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.result.BleDeviceInfosResult) this.getHInstance()).hashCode()");
            return ((com.huawei.hms.hihealth.result.BleDeviceInfosResult) this.getHInstance()).hashCode();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.result.BleDevicesResult) this.getGInstance()).hashCode()");
            return ((com.google.android.gms.fitness.result.BleDevicesResult) this.getGInstance()).hashCode();
        }
    }
    
    public java.lang.String toString() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.result.BleDeviceInfosResult) this.getHInstance()).toString()");
            return ((com.huawei.hms.hihealth.result.BleDeviceInfosResult) this.getHInstance()).toString();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.result.BleDevicesResult) this.getGInstance()).toString()");
            return ((com.google.android.gms.fitness.result.BleDevicesResult) this.getGInstance()).toString();
        }
    }
    
    public void writeToParcel(android.os.Parcel param0, int param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.result.BleDeviceInfosResult) this.getHInstance()).writeToParcel(param0, param1)");
            ((com.huawei.hms.hihealth.result.BleDeviceInfosResult) this.getHInstance()).writeToParcel(param0, param1);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.result.BleDevicesResult) this.getGInstance()).writeToParcel(param0, param1)");
            ((com.google.android.gms.fitness.result.BleDevicesResult) this.getGInstance()).writeToParcel(param0, param1);
        }
    }
    
    public int describeContents() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.result.BleDevicesResult dynamicCast(java.lang.Object param0) {
        if (param0 instanceof org.xms.g.fitness.result.BleDevicesResult) {
            return ((org.xms.g.fitness.result.BleDevicesResult) param0);
        }
        if (param0 instanceof org.xms.g.utils.XGettable) {
            com.google.android.gms.fitness.result.BleDevicesResult gReturn = ((com.google.android.gms.fitness.result.BleDevicesResult) ((org.xms.g.utils.XGettable) param0).getGInstance());
            com.huawei.hms.hihealth.result.BleDeviceInfosResult hReturn = ((com.huawei.hms.hihealth.result.BleDeviceInfosResult) ((org.xms.g.utils.XGettable) param0).getHInstance());
            return new org.xms.g.fitness.result.BleDevicesResult(new org.xms.g.utils.XBox(gReturn, hReturn));
        }
        return ((org.xms.g.fitness.result.BleDevicesResult) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.result.BleDeviceInfosResult;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.result.BleDevicesResult;
        }
    }
}