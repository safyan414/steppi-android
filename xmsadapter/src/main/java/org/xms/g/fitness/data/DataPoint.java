package org.xms.g.fitness.data;

public final class DataPoint extends org.xms.g.utils.XObject implements android.os.Parcelable {
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {
        
        public org.xms.g.fitness.data.DataPoint createFromParcel(android.os.Parcel param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                com.huawei.hms.hihealth.data.SamplePoint hReturn = com.huawei.hms.hihealth.data.SamplePoint.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.data.DataPoint(new org.xms.g.utils.XBox(null, hReturn));
            } else {
                com.google.android.gms.fitness.data.DataPoint gReturn = com.google.android.gms.fitness.data.DataPoint.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.data.DataPoint(new org.xms.g.utils.XBox(gReturn, null));
            }
        }
        
        public org.xms.g.fitness.data.DataPoint[] newArray(int param0) {
            return new org.xms.g.fitness.data.DataPoint[param0];
        }
    };
    
    public DataPoint(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public static org.xms.g.fitness.data.DataPoint.Builder builder(org.xms.g.fitness.data.DataSource param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.SamplePoint.builder(((com.huawei.hms.hihealth.data.DataCollector) ((param0) == null ? null : (param0.getHInstance()))))");
            com.huawei.hms.hihealth.data.SamplePoint.Builder hReturn = com.huawei.hms.hihealth.data.SamplePoint.builder(((com.huawei.hms.hihealth.data.DataCollector) ((param0) == null ? null : (param0.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataPoint.Builder(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataPoint.builder(((com.google.android.gms.fitness.data.DataSource) ((param0) == null ? null : (param0.getGInstance()))))");
            com.google.android.gms.fitness.data.DataPoint.Builder gReturn = com.google.android.gms.fitness.data.DataPoint.builder(((com.google.android.gms.fitness.data.DataSource) ((param0) == null ? null : (param0.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataPoint.Builder(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataPoint create(org.xms.g.fitness.data.DataSource param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.SamplePoint.create(((com.huawei.hms.hihealth.data.DataCollector) ((param0) == null ? null : (param0.getHInstance()))))");
            com.huawei.hms.hihealth.data.SamplePoint hReturn = com.huawei.hms.hihealth.data.SamplePoint.create(((com.huawei.hms.hihealth.data.DataCollector) ((param0) == null ? null : (param0.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataPoint(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataPoint.create(((com.google.android.gms.fitness.data.DataSource) ((param0) == null ? null : (param0.getGInstance()))))");
            com.google.android.gms.fitness.data.DataPoint gReturn = com.google.android.gms.fitness.data.DataPoint.create(((com.google.android.gms.fitness.data.DataSource) ((param0) == null ? null : (param0.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataPoint(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public final boolean equals(java.lang.Object param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SamplePoint) this.getHInstance()).equals(param0)");
            return ((com.huawei.hms.hihealth.data.SamplePoint) this.getHInstance()).equals(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataPoint) this.getGInstance()).equals(param0)");
            return ((com.google.android.gms.fitness.data.DataPoint) this.getGInstance()).equals(param0);
        }
    }
    
    public static org.xms.g.fitness.data.DataPoint extract(android.content.Intent param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.SamplePoint.extract(param0)");
            com.huawei.hms.hihealth.data.SamplePoint hReturn = com.huawei.hms.hihealth.data.SamplePoint.extract(param0);
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataPoint(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataPoint.extract(param0)");
            com.google.android.gms.fitness.data.DataPoint gReturn = com.google.android.gms.fitness.data.DataPoint.extract(param0);
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataPoint(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public final org.xms.g.fitness.data.DataSource getDataSource() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SamplePoint) this.getHInstance()).getDataCollector()");
            com.huawei.hms.hihealth.data.DataCollector hReturn = ((com.huawei.hms.hihealth.data.SamplePoint) this.getHInstance()).getDataCollector();
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataSource(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataPoint) this.getGInstance()).getDataSource()");
            com.google.android.gms.fitness.data.DataSource gReturn = ((com.google.android.gms.fitness.data.DataPoint) this.getGInstance()).getDataSource();
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataSource(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public final org.xms.g.fitness.data.DataType getDataType() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SamplePoint) this.getHInstance()).getDataType()");
            com.huawei.hms.hihealth.data.DataType hReturn = ((com.huawei.hms.hihealth.data.SamplePoint) this.getHInstance()).getDataType();
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataPoint) this.getGInstance()).getDataType()");
            com.google.android.gms.fitness.data.DataType gReturn = ((com.google.android.gms.fitness.data.DataPoint) this.getGInstance()).getDataType();
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public final long getEndTime(java.util.concurrent.TimeUnit param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SamplePoint) this.getHInstance()).getEndTime(param0)");
            return ((com.huawei.hms.hihealth.data.SamplePoint) this.getHInstance()).getEndTime(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataPoint) this.getGInstance()).getEndTime(param0)");
            return ((com.google.android.gms.fitness.data.DataPoint) this.getGInstance()).getEndTime(param0);
        }
    }
    
    public final org.xms.g.fitness.data.DataSource getOriginalDataSource() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SamplePoint) this.getHInstance()).getRawDataCollector()");
            com.huawei.hms.hihealth.data.DataCollector hReturn = ((com.huawei.hms.hihealth.data.SamplePoint) this.getHInstance()).getRawDataCollector();
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataSource(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataPoint) this.getGInstance()).getOriginalDataSource()");
            com.google.android.gms.fitness.data.DataSource gReturn = ((com.google.android.gms.fitness.data.DataPoint) this.getGInstance()).getOriginalDataSource();
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataSource(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public final long getStartTime(java.util.concurrent.TimeUnit param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SamplePoint) this.getHInstance()).getStartTime(param0)");
            return ((com.huawei.hms.hihealth.data.SamplePoint) this.getHInstance()).getStartTime(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataPoint) this.getGInstance()).getStartTime(param0)");
            return ((com.google.android.gms.fitness.data.DataPoint) this.getGInstance()).getStartTime(param0);
        }
    }
    
    public final long getTimestamp(java.util.concurrent.TimeUnit param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SamplePoint) this.getHInstance()).getSamplingTime(param0)");
            return ((com.huawei.hms.hihealth.data.SamplePoint) this.getHInstance()).getSamplingTime(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataPoint) this.getGInstance()).getTimestamp(param0)");
            return ((com.google.android.gms.fitness.data.DataPoint) this.getGInstance()).getTimestamp(param0);
        }
    }
    
    public final org.xms.g.fitness.data.Value getValue(org.xms.g.fitness.data.Field param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SamplePoint) this.getHInstance()).getFieldValue(((com.huawei.hms.hihealth.data.Field) ((param0) == null ? null : (param0.getHInstance()))))");
            com.huawei.hms.hihealth.data.Value hReturn = ((com.huawei.hms.hihealth.data.SamplePoint) this.getHInstance()).getFieldValue(((com.huawei.hms.hihealth.data.Field) ((param0) == null ? null : (param0.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Value(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataPoint) this.getGInstance()).getValue(((com.google.android.gms.fitness.data.Field) ((param0) == null ? null : (param0.getGInstance()))))");
            com.google.android.gms.fitness.data.Value gReturn = ((com.google.android.gms.fitness.data.DataPoint) this.getGInstance()).getValue(((com.google.android.gms.fitness.data.Field) ((param0) == null ? null : (param0.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Value(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public final int hashCode() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SamplePoint) this.getHInstance()).hashCode()");
            return ((com.huawei.hms.hihealth.data.SamplePoint) this.getHInstance()).hashCode();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataPoint) this.getGInstance()).hashCode()");
            return ((com.google.android.gms.fitness.data.DataPoint) this.getGInstance()).hashCode();
        }
    }
    
    public org.xms.g.fitness.data.DataPoint setFloatValues(float... param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SamplePoint) this.getHInstance()).setFloatValues(param0)");
            com.huawei.hms.hihealth.data.SamplePoint hReturn = ((com.huawei.hms.hihealth.data.SamplePoint) this.getHInstance()).setFloatValues(param0);
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataPoint(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataPoint) this.getGInstance()).setFloatValues(param0)");
            com.google.android.gms.fitness.data.DataPoint gReturn = ((com.google.android.gms.fitness.data.DataPoint) this.getGInstance()).setFloatValues(param0);
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataPoint(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.fitness.data.DataPoint setIntValues(int... param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SamplePoint) this.getHInstance()).setIntValues(param0)");
            com.huawei.hms.hihealth.data.SamplePoint hReturn = ((com.huawei.hms.hihealth.data.SamplePoint) this.getHInstance()).setIntValues(param0);
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataPoint(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataPoint) this.getGInstance()).setIntValues(param0)");
            com.google.android.gms.fitness.data.DataPoint gReturn = ((com.google.android.gms.fitness.data.DataPoint) this.getGInstance()).setIntValues(param0);
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataPoint(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public final org.xms.g.fitness.data.DataPoint setTimeInterval(long param0, long param1, java.util.concurrent.TimeUnit param2) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SamplePoint) this.getHInstance()).setTimeInterval(param0, param1, param2)");
            com.huawei.hms.hihealth.data.SamplePoint hReturn = ((com.huawei.hms.hihealth.data.SamplePoint) this.getHInstance()).setTimeInterval(param0, param1, param2);
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataPoint(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataPoint) this.getGInstance()).setTimeInterval(param0, param1, param2)");
            com.google.android.gms.fitness.data.DataPoint gReturn = ((com.google.android.gms.fitness.data.DataPoint) this.getGInstance()).setTimeInterval(param0, param1, param2);
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataPoint(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public final org.xms.g.fitness.data.DataPoint setTimestamp(long param0, java.util.concurrent.TimeUnit param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SamplePoint) this.getHInstance()).setSamplingTime(param0, param1)");
            com.huawei.hms.hihealth.data.SamplePoint hReturn = ((com.huawei.hms.hihealth.data.SamplePoint) this.getHInstance()).setSamplingTime(param0, param1);
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataPoint(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataPoint) this.getGInstance()).setTimestamp(param0, param1)");
            com.google.android.gms.fitness.data.DataPoint gReturn = ((com.google.android.gms.fitness.data.DataPoint) this.getGInstance()).setTimestamp(param0, param1);
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataPoint(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public final java.lang.String toString() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SamplePoint) this.getHInstance()).toString()");
            return ((com.huawei.hms.hihealth.data.SamplePoint) this.getHInstance()).toString();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataPoint) this.getGInstance()).toString()");
            return ((com.google.android.gms.fitness.data.DataPoint) this.getGInstance()).toString();
        }
    }
    
    public final void writeToParcel(android.os.Parcel param0, int param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SamplePoint) this.getHInstance()).writeToParcel(param0, param1)");
            ((com.huawei.hms.hihealth.data.SamplePoint) this.getHInstance()).writeToParcel(param0, param1);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataPoint) this.getGInstance()).writeToParcel(param0, param1)");
            ((com.google.android.gms.fitness.data.DataPoint) this.getGInstance()).writeToParcel(param0, param1);
        }
    }
    
    public int describeContents() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.data.DataPoint dynamicCast(java.lang.Object param0) {
        return ((org.xms.g.fitness.data.DataPoint) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.data.SamplePoint;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.data.DataPoint;
        }
    }
    
    public static class Builder extends org.xms.g.utils.XObject {
        
        public Builder(org.xms.g.utils.XBox param0) {
            super(param0);
        }
        
        public org.xms.g.fitness.data.DataPoint build() {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SamplePoint.Builder) this.getHInstance()).build()");
                com.huawei.hms.hihealth.data.SamplePoint hReturn = ((com.huawei.hms.hihealth.data.SamplePoint.Builder) this.getHInstance()).build();
                return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataPoint(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataPoint.Builder) this.getGInstance()).build()");
                com.google.android.gms.fitness.data.DataPoint gReturn = ((com.google.android.gms.fitness.data.DataPoint.Builder) this.getGInstance()).build();
                return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataPoint(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.data.DataPoint.Builder setActivityField(org.xms.g.fitness.data.Field param0, java.lang.String param1) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SamplePoint.Builder) this.getHInstance()).setActivityField(((com.huawei.hms.hihealth.data.Field) ((param0) == null ? null : (param0.getHInstance()))), param1)");
                com.huawei.hms.hihealth.data.SamplePoint.Builder hReturn = ((com.huawei.hms.hihealth.data.SamplePoint.Builder) this.getHInstance()).setActivityField(((com.huawei.hms.hihealth.data.Field) ((param0) == null ? null : (param0.getHInstance()))), param1);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataPoint.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataPoint.Builder) this.getGInstance()).setActivityField(((com.google.android.gms.fitness.data.Field) ((param0) == null ? null : (param0.getGInstance()))), param1)");
                com.google.android.gms.fitness.data.DataPoint.Builder gReturn = ((com.google.android.gms.fitness.data.DataPoint.Builder) this.getGInstance()).setActivityField(((com.google.android.gms.fitness.data.Field) ((param0) == null ? null : (param0.getGInstance()))), param1);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataPoint.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.data.DataPoint.Builder setField(org.xms.g.fitness.data.Field param0, float param1) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SamplePoint.Builder) this.getHInstance()).setFieldValue(((com.huawei.hms.hihealth.data.Field) ((param0) == null ? null : (param0.getHInstance()))), param1)");
                com.huawei.hms.hihealth.data.SamplePoint.Builder hReturn = ((com.huawei.hms.hihealth.data.SamplePoint.Builder) this.getHInstance()).setFieldValue(((com.huawei.hms.hihealth.data.Field) ((param0) == null ? null : (param0.getHInstance()))), param1);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataPoint.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataPoint.Builder) this.getGInstance()).setField(((com.google.android.gms.fitness.data.Field) ((param0) == null ? null : (param0.getGInstance()))), param1)");
                com.google.android.gms.fitness.data.DataPoint.Builder gReturn = ((com.google.android.gms.fitness.data.DataPoint.Builder) this.getGInstance()).setField(((com.google.android.gms.fitness.data.Field) ((param0) == null ? null : (param0.getGInstance()))), param1);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataPoint.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.data.DataPoint.Builder setField(org.xms.g.fitness.data.Field param0, java.util.Map<String, Float> param1) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SamplePoint.Builder) this.getHInstance()).setFieldValue(((com.huawei.hms.hihealth.data.Field) ((param0) == null ? null : (param0.getHInstance()))), param1)");
                com.huawei.hms.hihealth.data.SamplePoint.Builder hReturn = ((com.huawei.hms.hihealth.data.SamplePoint.Builder) this.getHInstance()).setFieldValue(((com.huawei.hms.hihealth.data.Field) ((param0) == null ? null : (param0.getHInstance()))), param1);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataPoint.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataPoint.Builder) this.getGInstance()).setField(((com.google.android.gms.fitness.data.Field) ((param0) == null ? null : (param0.getGInstance()))), param1)");
                com.google.android.gms.fitness.data.DataPoint.Builder gReturn = ((com.google.android.gms.fitness.data.DataPoint.Builder) this.getGInstance()).setField(((com.google.android.gms.fitness.data.Field) ((param0) == null ? null : (param0.getGInstance()))), param1);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataPoint.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.data.DataPoint.Builder setField(org.xms.g.fitness.data.Field param0, int param1) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SamplePoint.Builder) this.getHInstance()).setFieldValue(((com.huawei.hms.hihealth.data.Field) ((param0) == null ? null : (param0.getHInstance()))), param1)");
                com.huawei.hms.hihealth.data.SamplePoint.Builder hReturn = ((com.huawei.hms.hihealth.data.SamplePoint.Builder) this.getHInstance()).setFieldValue(((com.huawei.hms.hihealth.data.Field) ((param0) == null ? null : (param0.getHInstance()))), param1);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataPoint.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataPoint.Builder) this.getGInstance()).setField(((com.google.android.gms.fitness.data.Field) ((param0) == null ? null : (param0.getGInstance()))), param1)");
                com.google.android.gms.fitness.data.DataPoint.Builder gReturn = ((com.google.android.gms.fitness.data.DataPoint.Builder) this.getGInstance()).setField(((com.google.android.gms.fitness.data.Field) ((param0) == null ? null : (param0.getGInstance()))), param1);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataPoint.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.data.DataPoint.Builder setField(org.xms.g.fitness.data.Field param0, java.lang.String param1) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SamplePoint.Builder) this.getHInstance()).setFieldValue(((com.huawei.hms.hihealth.data.Field) ((param0) == null ? null : (param0.getHInstance()))), param1)");
                com.huawei.hms.hihealth.data.SamplePoint.Builder hReturn = ((com.huawei.hms.hihealth.data.SamplePoint.Builder) this.getHInstance()).setFieldValue(((com.huawei.hms.hihealth.data.Field) ((param0) == null ? null : (param0.getHInstance()))), param1);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataPoint.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataPoint.Builder) this.getGInstance()).setField(((com.google.android.gms.fitness.data.Field) ((param0) == null ? null : (param0.getGInstance()))), param1)");
                com.google.android.gms.fitness.data.DataPoint.Builder gReturn = ((com.google.android.gms.fitness.data.DataPoint.Builder) this.getGInstance()).setField(((com.google.android.gms.fitness.data.Field) ((param0) == null ? null : (param0.getGInstance()))), param1);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataPoint.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.data.DataPoint.Builder setFloatValues(float... param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SamplePoint.Builder) this.getHInstance()).setFloatValueBatch(param0)");
                com.huawei.hms.hihealth.data.SamplePoint.Builder hReturn = ((com.huawei.hms.hihealth.data.SamplePoint.Builder) this.getHInstance()).setFloatValueBatch(param0);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataPoint.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataPoint.Builder) this.getGInstance()).setFloatValues(param0)");
                com.google.android.gms.fitness.data.DataPoint.Builder gReturn = ((com.google.android.gms.fitness.data.DataPoint.Builder) this.getGInstance()).setFloatValues(param0);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataPoint.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.data.DataPoint.Builder setIntValues(int... param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SamplePoint.Builder) this.getHInstance()).setIntValueBatch(param0)");
                com.huawei.hms.hihealth.data.SamplePoint.Builder hReturn = ((com.huawei.hms.hihealth.data.SamplePoint.Builder) this.getHInstance()).setIntValueBatch(param0);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataPoint.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataPoint.Builder) this.getGInstance()).setIntValues(param0)");
                com.google.android.gms.fitness.data.DataPoint.Builder gReturn = ((com.google.android.gms.fitness.data.DataPoint.Builder) this.getGInstance()).setIntValues(param0);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataPoint.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.data.DataPoint.Builder setTimeInterval(long param0, long param1, java.util.concurrent.TimeUnit param2) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SamplePoint.Builder) this.getHInstance()).setTimeInterval(param0, param1, param2)");
                com.huawei.hms.hihealth.data.SamplePoint.Builder hReturn = ((com.huawei.hms.hihealth.data.SamplePoint.Builder) this.getHInstance()).setTimeInterval(param0, param1, param2);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataPoint.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataPoint.Builder) this.getGInstance()).setTimeInterval(param0, param1, param2)");
                com.google.android.gms.fitness.data.DataPoint.Builder gReturn = ((com.google.android.gms.fitness.data.DataPoint.Builder) this.getGInstance()).setTimeInterval(param0, param1, param2);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataPoint.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.data.DataPoint.Builder setTimestamp(long param0, java.util.concurrent.TimeUnit param1) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SamplePoint.Builder) this.getHInstance()).setSamplingTime(param0, param1)");
                com.huawei.hms.hihealth.data.SamplePoint.Builder hReturn = ((com.huawei.hms.hihealth.data.SamplePoint.Builder) this.getHInstance()).setSamplingTime(param0, param1);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataPoint.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataPoint.Builder) this.getGInstance()).setTimestamp(param0, param1)");
                com.google.android.gms.fitness.data.DataPoint.Builder gReturn = ((com.google.android.gms.fitness.data.DataPoint.Builder) this.getGInstance()).setTimestamp(param0, param1);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataPoint.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public static org.xms.g.fitness.data.DataPoint.Builder dynamicCast(java.lang.Object param0) {
            return ((org.xms.g.fitness.data.DataPoint.Builder) param0);
        }
        
        public static boolean isInstance(java.lang.Object param0) {
            if (!(param0 instanceof org.xms.g.utils.XGettable)) {
                return false;
            }
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.data.SamplePoint.Builder;
            } else {
                return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.data.DataPoint.Builder;
            }
        }
    }
}