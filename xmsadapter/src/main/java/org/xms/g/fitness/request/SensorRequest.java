package org.xms.g.fitness.request;

public class SensorRequest extends org.xms.g.utils.XObject {
    
    public SensorRequest(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public static int getACCURACY_MODE_DEFAULT() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.options.SensorOptions.ACCURACY_LEVEL_DEFAULT");
            return com.huawei.hms.hihealth.options.SensorOptions.ACCURACY_LEVEL_DEFAULT;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.request.SensorRequest.ACCURACY_MODE_DEFAULT");
            return com.google.android.gms.fitness.request.SensorRequest.ACCURACY_MODE_DEFAULT;
        }
    }
    
    public static int getACCURACY_MODE_HIGH() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.options.SensorOptions.ACCURACY_LEVEL_HIGH");
            return com.huawei.hms.hihealth.options.SensorOptions.ACCURACY_LEVEL_HIGH;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.request.SensorRequest.ACCURACY_MODE_HIGH");
            return com.google.android.gms.fitness.request.SensorRequest.ACCURACY_MODE_HIGH;
        }
    }
    
    public static int getACCURACY_MODE_LOW() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.options.SensorOptions.ACCURACY_LEVEL_LOW");
            return com.huawei.hms.hihealth.options.SensorOptions.ACCURACY_LEVEL_LOW;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.request.SensorRequest.ACCURACY_MODE_LOW");
            return com.google.android.gms.fitness.request.SensorRequest.ACCURACY_MODE_LOW;
        }
    }
    
    public boolean equals(java.lang.Object param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.SensorOptions) this.getHInstance()).equals(param0)");
            return ((com.huawei.hms.hihealth.options.SensorOptions) this.getHInstance()).equals(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SensorRequest) this.getGInstance()).equals(param0)");
            return ((com.google.android.gms.fitness.request.SensorRequest) this.getGInstance()).equals(param0);
        }
    }
    
    public int getAccuracyMode() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.SensorOptions) this.getHInstance()).getAccuracyLevel()");
            return ((com.huawei.hms.hihealth.options.SensorOptions) this.getHInstance()).getAccuracyLevel();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SensorRequest) this.getGInstance()).getAccuracyMode()");
            return ((com.google.android.gms.fitness.request.SensorRequest) this.getGInstance()).getAccuracyMode();
        }
    }
    
    public org.xms.g.fitness.data.DataSource getDataSource() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.SensorOptions) this.getHInstance()).getDataCollector()");
            com.huawei.hms.hihealth.data.DataCollector hReturn = ((com.huawei.hms.hihealth.options.SensorOptions) this.getHInstance()).getDataCollector();
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataSource(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SensorRequest) this.getGInstance()).getDataSource()");
            com.google.android.gms.fitness.data.DataSource gReturn = ((com.google.android.gms.fitness.request.SensorRequest) this.getGInstance()).getDataSource();
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataSource(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.fitness.data.DataType getDataType() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.SensorOptions) this.getHInstance()).getDataType()");
            com.huawei.hms.hihealth.data.DataType hReturn = ((com.huawei.hms.hihealth.options.SensorOptions) this.getHInstance()).getDataType();
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SensorRequest) this.getGInstance()).getDataType()");
            com.google.android.gms.fitness.data.DataType gReturn = ((com.google.android.gms.fitness.request.SensorRequest) this.getGInstance()).getDataType();
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public long getFastestRate(java.util.concurrent.TimeUnit param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.SensorOptions) this.getHInstance()).getHighestRate(param0)");
            return ((com.huawei.hms.hihealth.options.SensorOptions) this.getHInstance()).getHighestRate(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SensorRequest) this.getGInstance()).getFastestRate(param0)");
            return ((com.google.android.gms.fitness.request.SensorRequest) this.getGInstance()).getFastestRate(param0);
        }
    }
    
    public long getMaxDeliveryLatency(java.util.concurrent.TimeUnit param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.SensorOptions) this.getHInstance()).getMaxDeliveryDelay(param0)");
            return ((com.huawei.hms.hihealth.options.SensorOptions) this.getHInstance()).getMaxDeliveryDelay(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SensorRequest) this.getGInstance()).getMaxDeliveryLatency(param0)");
            return ((com.google.android.gms.fitness.request.SensorRequest) this.getGInstance()).getMaxDeliveryLatency(param0);
        }
    }
    
    public long getSamplingRate(java.util.concurrent.TimeUnit param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.SensorOptions) this.getHInstance()).getCollectionRate(param0)");
            return ((com.huawei.hms.hihealth.options.SensorOptions) this.getHInstance()).getCollectionRate(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SensorRequest) this.getGInstance()).getSamplingRate(param0)");
            return ((com.google.android.gms.fitness.request.SensorRequest) this.getGInstance()).getSamplingRate(param0);
        }
    }
    
    public int hashCode() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.SensorOptions) this.getHInstance()).hashCode()");
            return ((com.huawei.hms.hihealth.options.SensorOptions) this.getHInstance()).hashCode();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SensorRequest) this.getGInstance()).hashCode()");
            return ((com.google.android.gms.fitness.request.SensorRequest) this.getGInstance()).hashCode();
        }
    }
    
    public java.lang.String toString() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.SensorOptions) this.getHInstance()).toString()");
            return ((com.huawei.hms.hihealth.options.SensorOptions) this.getHInstance()).toString();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SensorRequest) this.getGInstance()).toString()");
            return ((com.google.android.gms.fitness.request.SensorRequest) this.getGInstance()).toString();
        }
    }
    
    public static org.xms.g.fitness.request.SensorRequest dynamicCast(java.lang.Object param0) {
        return ((org.xms.g.fitness.request.SensorRequest) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.options.SensorOptions;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.request.SensorRequest;
        }
    }
    
    public static class Builder extends org.xms.g.utils.XObject {
        
        public Builder(org.xms.g.utils.XBox param0) {
            super(param0);
        }
        
        public Builder() {
            super(((org.xms.g.utils.XBox) null));
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                this.setHInstance(new com.huawei.hms.hihealth.options.SensorOptions.Builder());
            } else {
                this.setGInstance(new com.google.android.gms.fitness.request.SensorRequest.Builder());
            }
        }
        
        public org.xms.g.fitness.request.SensorRequest build() {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.SensorOptions.Builder) this.getHInstance()).build()");
                com.huawei.hms.hihealth.options.SensorOptions hReturn = ((com.huawei.hms.hihealth.options.SensorOptions.Builder) this.getHInstance()).build();
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.SensorRequest(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SensorRequest.Builder) this.getGInstance()).build()");
                com.google.android.gms.fitness.request.SensorRequest gReturn = ((com.google.android.gms.fitness.request.SensorRequest.Builder) this.getGInstance()).build();
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.SensorRequest(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.SensorRequest.Builder setAccuracyMode(int param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.SensorOptions.Builder) this.getHInstance()).setAccuracyLevel(param0)");
                com.huawei.hms.hihealth.options.SensorOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.SensorOptions.Builder) this.getHInstance()).setAccuracyLevel(param0);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.SensorRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SensorRequest.Builder) this.getGInstance()).setAccuracyMode(param0)");
                com.google.android.gms.fitness.request.SensorRequest.Builder gReturn = ((com.google.android.gms.fitness.request.SensorRequest.Builder) this.getGInstance()).setAccuracyMode(param0);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.SensorRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.SensorRequest.Builder setDataSource(org.xms.g.fitness.data.DataSource param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.SensorOptions.Builder) this.getHInstance()).setDataCollector(((com.huawei.hms.hihealth.data.DataCollector) ((param0) == null ? null : (param0.getHInstance()))))");
                com.huawei.hms.hihealth.options.SensorOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.SensorOptions.Builder) this.getHInstance()).setDataCollector(((com.huawei.hms.hihealth.data.DataCollector) ((param0) == null ? null : (param0.getHInstance()))));
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.SensorRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SensorRequest.Builder) this.getGInstance()).setDataSource(((com.google.android.gms.fitness.data.DataSource) ((param0) == null ? null : (param0.getGInstance()))))");
                com.google.android.gms.fitness.request.SensorRequest.Builder gReturn = ((com.google.android.gms.fitness.request.SensorRequest.Builder) this.getGInstance()).setDataSource(((com.google.android.gms.fitness.data.DataSource) ((param0) == null ? null : (param0.getGInstance()))));
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.SensorRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.SensorRequest.Builder setDataType(org.xms.g.fitness.data.DataType param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.SensorOptions.Builder) this.getHInstance()).setDataType(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))))");
                com.huawei.hms.hihealth.options.SensorOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.SensorOptions.Builder) this.getHInstance()).setDataType(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))));
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.SensorRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SensorRequest.Builder) this.getGInstance()).setDataType(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))))");
                com.google.android.gms.fitness.request.SensorRequest.Builder gReturn = ((com.google.android.gms.fitness.request.SensorRequest.Builder) this.getGInstance()).setDataType(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))));
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.SensorRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.SensorRequest.Builder setFastestRate(int param0, java.util.concurrent.TimeUnit param1) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.SensorOptions.Builder) this.getHInstance()).setHighestRate(param0, param1)");
                com.huawei.hms.hihealth.options.SensorOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.SensorOptions.Builder) this.getHInstance()).setHighestRate(param0, param1);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.SensorRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SensorRequest.Builder) this.getGInstance()).setFastestRate(param0, param1)");
                com.google.android.gms.fitness.request.SensorRequest.Builder gReturn = ((com.google.android.gms.fitness.request.SensorRequest.Builder) this.getGInstance()).setFastestRate(param0, param1);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.SensorRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.SensorRequest.Builder setMaxDeliveryLatency(int param0, java.util.concurrent.TimeUnit param1) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.SensorOptions.Builder) this.getHInstance()).setMaxDeliveryDelay(param0, param1)");
                com.huawei.hms.hihealth.options.SensorOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.SensorOptions.Builder) this.getHInstance()).setMaxDeliveryDelay(param0, param1);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.SensorRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SensorRequest.Builder) this.getGInstance()).setMaxDeliveryLatency(param0, param1)");
                com.google.android.gms.fitness.request.SensorRequest.Builder gReturn = ((com.google.android.gms.fitness.request.SensorRequest.Builder) this.getGInstance()).setMaxDeliveryLatency(param0, param1);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.SensorRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.SensorRequest.Builder setSamplingRate(long param0, java.util.concurrent.TimeUnit param1) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.SensorOptions.Builder) this.getHInstance()).setCollectionRate(param0, param1)");
                com.huawei.hms.hihealth.options.SensorOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.SensorOptions.Builder) this.getHInstance()).setCollectionRate(param0, param1);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.SensorRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SensorRequest.Builder) this.getGInstance()).setSamplingRate(param0, param1)");
                com.google.android.gms.fitness.request.SensorRequest.Builder gReturn = ((com.google.android.gms.fitness.request.SensorRequest.Builder) this.getGInstance()).setSamplingRate(param0, param1);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.SensorRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.SensorRequest.Builder setTimeout(long param0, java.util.concurrent.TimeUnit param1) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.SensorOptions.Builder) this.getHInstance()).setOverTime(param0, param1)");
                com.huawei.hms.hihealth.options.SensorOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.SensorOptions.Builder) this.getHInstance()).setOverTime(param0, param1);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.SensorRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SensorRequest.Builder) this.getGInstance()).setTimeout(param0, param1)");
                com.google.android.gms.fitness.request.SensorRequest.Builder gReturn = ((com.google.android.gms.fitness.request.SensorRequest.Builder) this.getGInstance()).setTimeout(param0, param1);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.SensorRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public static org.xms.g.fitness.request.SensorRequest.Builder dynamicCast(java.lang.Object param0) {
            return ((org.xms.g.fitness.request.SensorRequest.Builder) param0);
        }
        
        public static boolean isInstance(java.lang.Object param0) {
            if (!(param0 instanceof org.xms.g.utils.XGettable)) {
                return false;
            }
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.options.SensorOptions.Builder;
            } else {
                return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.request.SensorRequest.Builder;
            }
        }
    }
}