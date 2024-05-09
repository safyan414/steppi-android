package org.xms.g.fitness.data;

public class DataSource extends org.xms.g.utils.XObject implements android.os.Parcelable {
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {
        
        public org.xms.g.fitness.data.DataSource createFromParcel(android.os.Parcel param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                com.huawei.hms.hihealth.data.DataCollector hReturn = com.huawei.hms.hihealth.data.DataCollector.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.data.DataSource(new org.xms.g.utils.XBox(null, hReturn));
            } else {
                com.google.android.gms.fitness.data.DataSource gReturn = com.google.android.gms.fitness.data.DataSource.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.data.DataSource(new org.xms.g.utils.XBox(gReturn, null));
            }
        }
        
        public org.xms.g.fitness.data.DataSource[] newArray(int param0) {
            return new org.xms.g.fitness.data.DataSource[param0];
        }
    };
    
    public DataSource(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public static int getDATA_QUALITY_BLOOD_GLUCOSE_ISO151972003() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataCollector.HEALTH_DATA_QUALITY_BLOOD_GLUCOSE_ISO151972003");
            return com.huawei.hms.hihealth.data.DataCollector.HEALTH_DATA_QUALITY_BLOOD_GLUCOSE_ISO151972003;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataSource.DATA_QUALITY_BLOOD_GLUCOSE_ISO151972003");
            return com.google.android.gms.fitness.data.DataSource.DATA_QUALITY_BLOOD_GLUCOSE_ISO151972003;
        }
    }
    
    public static int getDATA_QUALITY_BLOOD_GLUCOSE_ISO151972013() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataCollector.HEALTH_DATA_QUALITY_BLOOD_GLUCOSE_ISO151972013");
            return com.huawei.hms.hihealth.data.DataCollector.HEALTH_DATA_QUALITY_BLOOD_GLUCOSE_ISO151972013;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataSource.DATA_QUALITY_BLOOD_GLUCOSE_ISO151972013");
            return com.google.android.gms.fitness.data.DataSource.DATA_QUALITY_BLOOD_GLUCOSE_ISO151972013;
        }
    }
    
    public static int getDATA_QUALITY_BLOOD_PRESSURE_AAMI() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataCollector.HEALTH_DATA_QUALITY_BLOOD_PRESSURE_AAMI");
            return com.huawei.hms.hihealth.data.DataCollector.HEALTH_DATA_QUALITY_BLOOD_PRESSURE_AAMI;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataSource.DATA_QUALITY_BLOOD_PRESSURE_AAMI");
            return com.google.android.gms.fitness.data.DataSource.DATA_QUALITY_BLOOD_PRESSURE_AAMI;
        }
    }
    
    public static int getDATA_QUALITY_BLOOD_PRESSURE_BHS_A_A() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataCollector.HEALTH_DATA_QUALITY_BLOOD_PRESSURE_BHS_A_A");
            return com.huawei.hms.hihealth.data.DataCollector.HEALTH_DATA_QUALITY_BLOOD_PRESSURE_BHS_A_A;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataSource.DATA_QUALITY_BLOOD_PRESSURE_BHS_A_A");
            return com.google.android.gms.fitness.data.DataSource.DATA_QUALITY_BLOOD_PRESSURE_BHS_A_A;
        }
    }
    
    public static int getDATA_QUALITY_BLOOD_PRESSURE_BHS_A_B() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataCollector.HEALTH_DATA_QUALITY_BLOOD_PRESSURE_BHS_A_B");
            return com.huawei.hms.hihealth.data.DataCollector.HEALTH_DATA_QUALITY_BLOOD_PRESSURE_BHS_A_B;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataSource.DATA_QUALITY_BLOOD_PRESSURE_BHS_A_B");
            return com.google.android.gms.fitness.data.DataSource.DATA_QUALITY_BLOOD_PRESSURE_BHS_A_B;
        }
    }
    
    public static int getDATA_QUALITY_BLOOD_PRESSURE_BHS_B_A() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataCollector.HEALTH_DATA_QUALITY_BLOOD_PRESSURE_BHS_B_A");
            return com.huawei.hms.hihealth.data.DataCollector.HEALTH_DATA_QUALITY_BLOOD_PRESSURE_BHS_B_A;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataSource.DATA_QUALITY_BLOOD_PRESSURE_BHS_B_A");
            return com.google.android.gms.fitness.data.DataSource.DATA_QUALITY_BLOOD_PRESSURE_BHS_B_A;
        }
    }
    
    public static int getDATA_QUALITY_BLOOD_PRESSURE_BHS_B_B() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataCollector.HEALTH_DATA_QUALITY_BLOOD_PRESSURE_BHS_B_B");
            return com.huawei.hms.hihealth.data.DataCollector.HEALTH_DATA_QUALITY_BLOOD_PRESSURE_BHS_B_B;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataSource.DATA_QUALITY_BLOOD_PRESSURE_BHS_B_B");
            return com.google.android.gms.fitness.data.DataSource.DATA_QUALITY_BLOOD_PRESSURE_BHS_B_B;
        }
    }
    
    public static int getDATA_QUALITY_BLOOD_PRESSURE_ESH2002() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataCollector.HEALTH_DATA_QUALITY_BLOOD_PRESSURE_ESH2002");
            return com.huawei.hms.hihealth.data.DataCollector.HEALTH_DATA_QUALITY_BLOOD_PRESSURE_ESH2002;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataSource.DATA_QUALITY_BLOOD_PRESSURE_ESH2002");
            return com.google.android.gms.fitness.data.DataSource.DATA_QUALITY_BLOOD_PRESSURE_ESH2002;
        }
    }
    
    public static int getDATA_QUALITY_BLOOD_PRESSURE_ESH2010() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataCollector.HEALTH_DATA_QUALITY_BLOOD_PRESSURE_ESH2010");
            return com.huawei.hms.hihealth.data.DataCollector.HEALTH_DATA_QUALITY_BLOOD_PRESSURE_ESH2010;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataSource.DATA_QUALITY_BLOOD_PRESSURE_ESH2010");
            return com.google.android.gms.fitness.data.DataSource.DATA_QUALITY_BLOOD_PRESSURE_ESH2010;
        }
    }
    
    public static java.lang.String getEXTRA_DATA_SOURCE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataCollector.EXTRA_DATA_SOURCE");
            return com.huawei.hms.hihealth.data.DataCollector.EXTRA_DATA_SOURCE;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataSource.EXTRA_DATA_SOURCE");
            return com.google.android.gms.fitness.data.DataSource.EXTRA_DATA_SOURCE;
        }
    }
    
    public static int getTYPE_DERIVED() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataCollector.DATA_TYPE_DERIVED");
            return com.huawei.hms.hihealth.data.DataCollector.DATA_TYPE_DERIVED;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataSource.TYPE_DERIVED");
            return com.google.android.gms.fitness.data.DataSource.TYPE_DERIVED;
        }
    }
    
    public static int getTYPE_RAW() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataCollector.DATA_TYPE_RAW");
            return com.huawei.hms.hihealth.data.DataCollector.DATA_TYPE_RAW;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataSource.TYPE_RAW");
            return com.google.android.gms.fitness.data.DataSource.TYPE_RAW;
        }
    }
    
    public boolean equals(java.lang.Object param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataCollector) this.getHInstance()).equals(param0)");
            return ((com.huawei.hms.hihealth.data.DataCollector) this.getHInstance()).equals(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataSource) this.getGInstance()).equals(param0)");
            return ((com.google.android.gms.fitness.data.DataSource) this.getGInstance()).equals(param0);
        }
    }
    
    public static org.xms.g.fitness.data.DataSource extract(android.content.Intent param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataCollector.extract(param0)");
            com.huawei.hms.hihealth.data.DataCollector hReturn = com.huawei.hms.hihealth.data.DataCollector.extract(param0);
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataSource(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataSource.extract(param0)");
            com.google.android.gms.fitness.data.DataSource gReturn = com.google.android.gms.fitness.data.DataSource.extract(param0);
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataSource(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public java.lang.String getAppPackageName() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataCollector) this.getHInstance()).getPackageName()");
            return ((com.huawei.hms.hihealth.data.DataCollector) this.getHInstance()).getPackageName();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataSource) this.getGInstance()).getAppPackageName()");
            return ((com.google.android.gms.fitness.data.DataSource) this.getGInstance()).getAppPackageName();
        }
    }
    
    public int[] getDataQualityStandards() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataCollector) this.getHInstance()).getQualityMetrics()");
            return ((com.huawei.hms.hihealth.data.DataCollector) this.getHInstance()).getQualityMetrics();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataSource) this.getGInstance()).getDataQualityStandards()");
            return ((com.google.android.gms.fitness.data.DataSource) this.getGInstance()).getDataQualityStandards();
        }
    }
    
    public org.xms.g.fitness.data.DataType getDataType() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataCollector) this.getHInstance()).getDataType()");
            com.huawei.hms.hihealth.data.DataType hReturn = ((com.huawei.hms.hihealth.data.DataCollector) this.getHInstance()).getDataType();
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataSource) this.getGInstance()).getDataType()");
            com.google.android.gms.fitness.data.DataType gReturn = ((com.google.android.gms.fitness.data.DataSource) this.getGInstance()).getDataType();
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.fitness.data.Device getDevice() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataCollector) this.getHInstance()).getDeviceInfo()");
            com.huawei.hms.hihealth.data.DeviceInfo hReturn = ((com.huawei.hms.hihealth.data.DataCollector) this.getHInstance()).getDeviceInfo();
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Device(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataSource) this.getGInstance()).getDevice()");
            com.google.android.gms.fitness.data.Device gReturn = ((com.google.android.gms.fitness.data.DataSource) this.getGInstance()).getDevice();
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Device(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public java.lang.String getName() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataCollector) this.getHInstance()).getDataCollectorName()");
            return ((com.huawei.hms.hihealth.data.DataCollector) this.getHInstance()).getDataCollectorName();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataSource) this.getGInstance()).getName()");
            return ((com.google.android.gms.fitness.data.DataSource) this.getGInstance()).getName();
        }
    }
    
    public java.lang.String getStreamIdentifier() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataCollector) this.getHInstance()).getDataStreamId()");
            return ((com.huawei.hms.hihealth.data.DataCollector) this.getHInstance()).getDataStreamId();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataSource) this.getGInstance()).getStreamIdentifier()");
            return ((com.google.android.gms.fitness.data.DataSource) this.getGInstance()).getStreamIdentifier();
        }
    }
    
    public java.lang.String getStreamName() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataCollector) this.getHInstance()).getDataStreamName()");
            return ((com.huawei.hms.hihealth.data.DataCollector) this.getHInstance()).getDataStreamName();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataSource) this.getGInstance()).getStreamName()");
            return ((com.google.android.gms.fitness.data.DataSource) this.getGInstance()).getStreamName();
        }
    }
    
    public int getType() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataCollector) this.getHInstance()).getDataGenerateType()");
            return ((com.huawei.hms.hihealth.data.DataCollector) this.getHInstance()).getDataGenerateType();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataSource) this.getGInstance()).getType()");
            return ((com.google.android.gms.fitness.data.DataSource) this.getGInstance()).getType();
        }
    }
    
    public int hashCode() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataCollector) this.getHInstance()).hashCode()");
            return ((com.huawei.hms.hihealth.data.DataCollector) this.getHInstance()).hashCode();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataSource) this.getGInstance()).hashCode()");
            return ((com.google.android.gms.fitness.data.DataSource) this.getGInstance()).hashCode();
        }
    }
    
    public java.lang.String toString() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataCollector) this.getHInstance()).toString()");
            return ((com.huawei.hms.hihealth.data.DataCollector) this.getHInstance()).toString();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataSource) this.getGInstance()).toString()");
            return ((com.google.android.gms.fitness.data.DataSource) this.getGInstance()).toString();
        }
    }
    
    public void writeToParcel(android.os.Parcel param0, int param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataCollector) this.getHInstance()).writeToParcel(param0, param1)");
            ((com.huawei.hms.hihealth.data.DataCollector) this.getHInstance()).writeToParcel(param0, param1);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataSource) this.getGInstance()).writeToParcel(param0, param1)");
            ((com.google.android.gms.fitness.data.DataSource) this.getGInstance()).writeToParcel(param0, param1);
        }
    }
    
    public int describeContents() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.data.DataSource dynamicCast(java.lang.Object param0) {
        return ((org.xms.g.fitness.data.DataSource) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.data.DataCollector;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.data.DataSource;
        }
    }
    
    public static final class Builder extends org.xms.g.utils.XObject {
        
        public Builder(org.xms.g.utils.XBox param0) {
            super(param0);
        }
        
        public Builder() {
            super(((org.xms.g.utils.XBox) null));
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                this.setHInstance(new com.huawei.hms.hihealth.data.DataCollector.Builder());
            } else {
                this.setGInstance(new com.google.android.gms.fitness.data.DataSource.Builder());
            }
        }
        
        public final org.xms.g.fitness.data.DataSource build() {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataCollector.Builder) this.getHInstance()).build()");
                com.huawei.hms.hihealth.data.DataCollector hReturn = ((com.huawei.hms.hihealth.data.DataCollector.Builder) this.getHInstance()).build();
                return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataSource(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataSource.Builder) this.getGInstance()).build()");
                com.google.android.gms.fitness.data.DataSource gReturn = ((com.google.android.gms.fitness.data.DataSource.Builder) this.getGInstance()).build();
                return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataSource(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public final org.xms.g.fitness.data.DataSource.Builder setAppPackageName(java.lang.String param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataCollector.Builder) this.getHInstance()).setPackageName(param0)");
                com.huawei.hms.hihealth.data.DataCollector.Builder hReturn = ((com.huawei.hms.hihealth.data.DataCollector.Builder) this.getHInstance()).setPackageName(param0);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataSource.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataSource.Builder) this.getGInstance()).setAppPackageName(param0)");
                com.google.android.gms.fitness.data.DataSource.Builder gReturn = ((com.google.android.gms.fitness.data.DataSource.Builder) this.getGInstance()).setAppPackageName(param0);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataSource.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public final org.xms.g.fitness.data.DataSource.Builder setAppPackageName(android.content.Context param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataCollector.Builder) this.getHInstance()).setPackageName(param0)");
                com.huawei.hms.hihealth.data.DataCollector.Builder hReturn = ((com.huawei.hms.hihealth.data.DataCollector.Builder) this.getHInstance()).setPackageName(param0);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataSource.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataSource.Builder) this.getGInstance()).setAppPackageName(param0)");
                com.google.android.gms.fitness.data.DataSource.Builder gReturn = ((com.google.android.gms.fitness.data.DataSource.Builder) this.getGInstance()).setAppPackageName(param0);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataSource.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.data.DataSource.Builder setDataQualityStandards(int... param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataCollector.Builder) this.getHInstance()).setQualityMetrics(param0)");
                com.huawei.hms.hihealth.data.DataCollector.Builder hReturn = ((com.huawei.hms.hihealth.data.DataCollector.Builder) this.getHInstance()).setQualityMetrics(param0);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataSource.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataSource.Builder) this.getGInstance()).setDataQualityStandards(param0)");
                com.google.android.gms.fitness.data.DataSource.Builder gReturn = ((com.google.android.gms.fitness.data.DataSource.Builder) this.getGInstance()).setDataQualityStandards(param0);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataSource.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public final org.xms.g.fitness.data.DataSource.Builder setDataType(org.xms.g.fitness.data.DataType param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataCollector.Builder) this.getHInstance()).setDataType(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))))");
                com.huawei.hms.hihealth.data.DataCollector.Builder hReturn = ((com.huawei.hms.hihealth.data.DataCollector.Builder) this.getHInstance()).setDataType(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))));
                return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataSource.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataSource.Builder) this.getGInstance()).setDataType(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))))");
                com.google.android.gms.fitness.data.DataSource.Builder gReturn = ((com.google.android.gms.fitness.data.DataSource.Builder) this.getGInstance()).setDataType(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))));
                return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataSource.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public final org.xms.g.fitness.data.DataSource.Builder setDevice(org.xms.g.fitness.data.Device param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataCollector.Builder) this.getHInstance()).setDeviceInfo(((com.huawei.hms.hihealth.data.DeviceInfo) ((param0) == null ? null : (param0.getHInstance()))))");
                com.huawei.hms.hihealth.data.DataCollector.Builder hReturn = ((com.huawei.hms.hihealth.data.DataCollector.Builder) this.getHInstance()).setDeviceInfo(((com.huawei.hms.hihealth.data.DeviceInfo) ((param0) == null ? null : (param0.getHInstance()))));
                return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataSource.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataSource.Builder) this.getGInstance()).setDevice(((com.google.android.gms.fitness.data.Device) ((param0) == null ? null : (param0.getGInstance()))))");
                com.google.android.gms.fitness.data.DataSource.Builder gReturn = ((com.google.android.gms.fitness.data.DataSource.Builder) this.getGInstance()).setDevice(((com.google.android.gms.fitness.data.Device) ((param0) == null ? null : (param0.getGInstance()))));
                return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataSource.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public final org.xms.g.fitness.data.DataSource.Builder setName(java.lang.String param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataCollector.Builder) this.getHInstance()).setDataCollectorName(param0)");
                com.huawei.hms.hihealth.data.DataCollector.Builder hReturn = ((com.huawei.hms.hihealth.data.DataCollector.Builder) this.getHInstance()).setDataCollectorName(param0);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataSource.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataSource.Builder) this.getGInstance()).setName(param0)");
                com.google.android.gms.fitness.data.DataSource.Builder gReturn = ((com.google.android.gms.fitness.data.DataSource.Builder) this.getGInstance()).setName(param0);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataSource.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public final org.xms.g.fitness.data.DataSource.Builder setStreamName(java.lang.String param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataCollector.Builder) this.getHInstance()).setDataStreamName(param0)");
                com.huawei.hms.hihealth.data.DataCollector.Builder hReturn = ((com.huawei.hms.hihealth.data.DataCollector.Builder) this.getHInstance()).setDataStreamName(param0);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataSource.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataSource.Builder) this.getGInstance()).setStreamName(param0)");
                com.google.android.gms.fitness.data.DataSource.Builder gReturn = ((com.google.android.gms.fitness.data.DataSource.Builder) this.getGInstance()).setStreamName(param0);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataSource.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public final org.xms.g.fitness.data.DataSource.Builder setType(int param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataCollector.Builder) this.getHInstance()).setDataGenerateType(param0)");
                com.huawei.hms.hihealth.data.DataCollector.Builder hReturn = ((com.huawei.hms.hihealth.data.DataCollector.Builder) this.getHInstance()).setDataGenerateType(param0);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataSource.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataSource.Builder) this.getGInstance()).setType(param0)");
                com.google.android.gms.fitness.data.DataSource.Builder gReturn = ((com.google.android.gms.fitness.data.DataSource.Builder) this.getGInstance()).setType(param0);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataSource.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public static org.xms.g.fitness.data.DataSource.Builder dynamicCast(java.lang.Object param0) {
            return ((org.xms.g.fitness.data.DataSource.Builder) param0);
        }
        
        public static boolean isInstance(java.lang.Object param0) {
            if (!(param0 instanceof org.xms.g.utils.XGettable)) {
                return false;
            }
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.data.DataCollector.Builder;
            } else {
                return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.data.DataSource.Builder;
            }
        }
    }
}