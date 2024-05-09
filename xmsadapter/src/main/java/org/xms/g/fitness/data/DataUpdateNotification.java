package org.xms.g.fitness.data;

public class DataUpdateNotification extends org.xms.g.utils.XObject implements android.os.Parcelable {
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {
        
        public org.xms.g.fitness.data.DataUpdateNotification createFromParcel(android.os.Parcel param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                com.huawei.hms.hihealth.data.DataModifyInfo hReturn = com.huawei.hms.hihealth.data.DataModifyInfo.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.data.DataUpdateNotification(new org.xms.g.utils.XBox(null, hReturn));
            } else {
                com.google.android.gms.fitness.data.DataUpdateNotification gReturn = com.google.android.gms.fitness.data.DataUpdateNotification.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.data.DataUpdateNotification(new org.xms.g.utils.XBox(gReturn, null));
            }
        }
        
        public org.xms.g.fitness.data.DataUpdateNotification[] newArray(int param0) {
            return new org.xms.g.fitness.data.DataUpdateNotification[param0];
        }
    };
    
    public DataUpdateNotification(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public static java.lang.String getACTION() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataModifyInfo.ACTION");
            return com.huawei.hms.hihealth.data.DataModifyInfo.ACTION;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataUpdateNotification.ACTION");
            return com.google.android.gms.fitness.data.DataUpdateNotification.ACTION;
        }
    }
    
    public static java.lang.String getEXTRA_DATA_UPDATE_NOTIFICATION() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataModifyInfo.EXTRA_DATA_UPDATE_NOTIFICATION");
            return com.huawei.hms.hihealth.data.DataModifyInfo.EXTRA_DATA_UPDATE_NOTIFICATION;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataUpdateNotification.EXTRA_DATA_UPDATE_NOTIFICATION");
            return com.google.android.gms.fitness.data.DataUpdateNotification.EXTRA_DATA_UPDATE_NOTIFICATION;
        }
    }
    
    public static int getOPERATION_DELETE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataModifyInfo.OPERATION_DELETE");
            return com.huawei.hms.hihealth.data.DataModifyInfo.OPERATION_DELETE;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataUpdateNotification.OPERATION_DELETE");
            return com.google.android.gms.fitness.data.DataUpdateNotification.OPERATION_DELETE;
        }
    }
    
    public static int getOPERATION_INSERT() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataModifyInfo.OPERATION_INSERT");
            return com.huawei.hms.hihealth.data.DataModifyInfo.OPERATION_INSERT;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataUpdateNotification.OPERATION_INSERT");
            return com.google.android.gms.fitness.data.DataUpdateNotification.OPERATION_INSERT;
        }
    }
    
    public static int getOPERATION_UPDATE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataModifyInfo.OPERATION_UPDATE");
            return com.huawei.hms.hihealth.data.DataModifyInfo.OPERATION_UPDATE;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataUpdateNotification.OPERATION_UPDATE");
            return com.google.android.gms.fitness.data.DataUpdateNotification.OPERATION_UPDATE;
        }
    }
    
    public boolean equals(java.lang.Object param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataModifyInfo) this.getHInstance()).equals(param0)");
            return ((com.huawei.hms.hihealth.data.DataModifyInfo) this.getHInstance()).equals(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataUpdateNotification) this.getGInstance()).equals(param0)");
            return ((com.google.android.gms.fitness.data.DataUpdateNotification) this.getGInstance()).equals(param0);
        }
    }
    
    public org.xms.g.fitness.data.DataSource getDataSource() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataModifyInfo) this.getHInstance()).getDataCollector()");
            com.huawei.hms.hihealth.data.DataCollector hReturn = ((com.huawei.hms.hihealth.data.DataModifyInfo) this.getHInstance()).getDataCollector();
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataSource(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataUpdateNotification) this.getGInstance()).getDataSource()");
            com.google.android.gms.fitness.data.DataSource gReturn = ((com.google.android.gms.fitness.data.DataUpdateNotification) this.getGInstance()).getDataSource();
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataSource(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.fitness.data.DataType getDataType() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataModifyInfo) this.getHInstance()).getDataType()");
            com.huawei.hms.hihealth.data.DataType hReturn = ((com.huawei.hms.hihealth.data.DataModifyInfo) this.getHInstance()).getDataType();
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataUpdateNotification) this.getGInstance()).getDataType()");
            com.google.android.gms.fitness.data.DataType gReturn = ((com.google.android.gms.fitness.data.DataUpdateNotification) this.getGInstance()).getDataType();
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataUpdateNotification getDataUpdateNotification(android.content.Intent param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataModifyInfo.getModifyInfo(param0)");
            com.huawei.hms.hihealth.data.DataModifyInfo hReturn = com.huawei.hms.hihealth.data.DataModifyInfo.getModifyInfo(param0);
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataUpdateNotification(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataUpdateNotification.getDataUpdateNotification(param0)");
            com.google.android.gms.fitness.data.DataUpdateNotification gReturn = com.google.android.gms.fitness.data.DataUpdateNotification.getDataUpdateNotification(param0);
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataUpdateNotification(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public int getOperationType() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataModifyInfo) this.getHInstance()).getActionType()");
            return ((com.huawei.hms.hihealth.data.DataModifyInfo) this.getHInstance()).getActionType();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataUpdateNotification) this.getGInstance()).getOperationType()");
            return ((com.google.android.gms.fitness.data.DataUpdateNotification) this.getGInstance()).getOperationType();
        }
    }
    
    public long getUpdateEndTime(java.util.concurrent.TimeUnit param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataModifyInfo) this.getHInstance()).getModifyEndTime(param0)");
            return ((com.huawei.hms.hihealth.data.DataModifyInfo) this.getHInstance()).getModifyEndTime(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataUpdateNotification) this.getGInstance()).getUpdateEndTime(param0)");
            return ((com.google.android.gms.fitness.data.DataUpdateNotification) this.getGInstance()).getUpdateEndTime(param0);
        }
    }
    
    public long getUpdateStartTime(java.util.concurrent.TimeUnit param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataModifyInfo) this.getHInstance()).getModifyStartTime(param0)");
            return ((com.huawei.hms.hihealth.data.DataModifyInfo) this.getHInstance()).getModifyStartTime(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataUpdateNotification) this.getGInstance()).getUpdateStartTime(param0)");
            return ((com.google.android.gms.fitness.data.DataUpdateNotification) this.getGInstance()).getUpdateStartTime(param0);
        }
    }
    
    public int hashCode() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataModifyInfo) this.getHInstance()).hashCode()");
            return ((com.huawei.hms.hihealth.data.DataModifyInfo) this.getHInstance()).hashCode();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataUpdateNotification) this.getGInstance()).hashCode()");
            return ((com.google.android.gms.fitness.data.DataUpdateNotification) this.getGInstance()).hashCode();
        }
    }
    
    public java.lang.String toString() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataModifyInfo) this.getHInstance()).toString()");
            return ((com.huawei.hms.hihealth.data.DataModifyInfo) this.getHInstance()).toString();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataUpdateNotification) this.getGInstance()).toString()");
            return ((com.google.android.gms.fitness.data.DataUpdateNotification) this.getGInstance()).toString();
        }
    }
    
    public void writeToParcel(android.os.Parcel param0, int param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataModifyInfo) this.getHInstance()).writeToParcel(param0, param1)");
            ((com.huawei.hms.hihealth.data.DataModifyInfo) this.getHInstance()).writeToParcel(param0, param1);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataUpdateNotification) this.getGInstance()).writeToParcel(param0, param1)");
            ((com.google.android.gms.fitness.data.DataUpdateNotification) this.getGInstance()).writeToParcel(param0, param1);
        }
    }
    
    public int describeContents() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.data.DataUpdateNotification dynamicCast(java.lang.Object param0) {
        return ((org.xms.g.fitness.data.DataUpdateNotification) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.data.DataModifyInfo;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.data.DataUpdateNotification;
        }
    }
}