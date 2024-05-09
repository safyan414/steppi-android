package org.xms.g.fitness.data;

public final class HealthDataTypes extends org.xms.g.utils.XObject {
    
    public HealthDataTypes(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public static org.xms.g.fitness.data.DataType getAGGREGATE_BASAL_BODY_TEMPERATURE_SUMMARY() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthDataTypes.POLYMERIZE_CONTINUOUS_BODY_TEMPERATURE_REST_STATISTICS");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthDataTypes.POLYMERIZE_CONTINUOUS_BODY_TEMPERATURE_REST_STATISTICS;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthDataTypes.AGGREGATE_BASAL_BODY_TEMPERATURE_SUMMARY");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthDataTypes.AGGREGATE_BASAL_BODY_TEMPERATURE_SUMMARY;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getAGGREGATE_BLOOD_GLUCOSE_SUMMARY() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthDataTypes.POLYMERIZE_CONTINUOUS_BODY_BLOOD_GLUCOSE_STATISTICS");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthDataTypes.POLYMERIZE_CONTINUOUS_BODY_BLOOD_GLUCOSE_STATISTICS;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthDataTypes.AGGREGATE_BLOOD_GLUCOSE_SUMMARY");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthDataTypes.AGGREGATE_BLOOD_GLUCOSE_SUMMARY;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getAGGREGATE_BLOOD_PRESSURE_SUMMARY() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthDataTypes.POLYMERIZE_CONTINUOUS_BODY_BLOOD_PRESSURE_STATISTICS");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthDataTypes.POLYMERIZE_CONTINUOUS_BODY_BLOOD_PRESSURE_STATISTICS;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthDataTypes.AGGREGATE_BLOOD_PRESSURE_SUMMARY");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthDataTypes.AGGREGATE_BLOOD_PRESSURE_SUMMARY;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getAGGREGATE_BODY_TEMPERATURE_SUMMARY() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthDataTypes.POLYMERIZE_CONTINUOUS_BODY_TEMPERATURE_STATISTICS");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthDataTypes.POLYMERIZE_CONTINUOUS_BODY_TEMPERATURE_STATISTICS;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthDataTypes.AGGREGATE_BODY_TEMPERATURE_SUMMARY");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthDataTypes.AGGREGATE_BODY_TEMPERATURE_SUMMARY;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getAGGREGATE_OXYGEN_SATURATION_SUMMARY() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthDataTypes.POLYMERIZE_CONTINUOUS_SPO2_STATISTICS");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthDataTypes.POLYMERIZE_CONTINUOUS_SPO2_STATISTICS;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthDataTypes.AGGREGATE_OXYGEN_SATURATION_SUMMARY");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthDataTypes.AGGREGATE_OXYGEN_SATURATION_SUMMARY;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getTYPE_BASAL_BODY_TEMPERATURE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthDataTypes.DT_INSTANTANEOUS_BODY_TEMPERATURE_REST");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthDataTypes.DT_INSTANTANEOUS_BODY_TEMPERATURE_REST;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthDataTypes.TYPE_BASAL_BODY_TEMPERATURE");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthDataTypes.TYPE_BASAL_BODY_TEMPERATURE;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getTYPE_BLOOD_GLUCOSE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthDataTypes.DT_INSTANTANEOUS_BLOOD_GLUCOSE");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthDataTypes.DT_INSTANTANEOUS_BLOOD_GLUCOSE;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthDataTypes.TYPE_BLOOD_GLUCOSE");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthDataTypes.TYPE_BLOOD_GLUCOSE;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getTYPE_BLOOD_PRESSURE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthDataTypes.DT_INSTANTANEOUS_BLOOD_PRESSURE");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthDataTypes.DT_INSTANTANEOUS_BLOOD_PRESSURE;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthDataTypes.TYPE_BLOOD_PRESSURE");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthDataTypes.TYPE_BLOOD_PRESSURE;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getTYPE_BODY_TEMPERATURE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthDataTypes.DT_INSTANTANEOUS_BODY_TEMPERATURE");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthDataTypes.DT_INSTANTANEOUS_BODY_TEMPERATURE;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthDataTypes.TYPE_BODY_TEMPERATURE");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthDataTypes.TYPE_BODY_TEMPERATURE;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getTYPE_CERVICAL_MUCUS() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthDataTypes.DT_INSTANTANEOUS_CERVICAL_MUCUS");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthDataTypes.DT_INSTANTANEOUS_CERVICAL_MUCUS;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthDataTypes.TYPE_CERVICAL_MUCUS");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthDataTypes.TYPE_CERVICAL_MUCUS;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getTYPE_CERVICAL_POSITION() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthDataTypes.DT_INSTANTANEOUS_CERVICAL_STATUS");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthDataTypes.DT_INSTANTANEOUS_CERVICAL_STATUS;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthDataTypes.TYPE_CERVICAL_POSITION");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthDataTypes.TYPE_CERVICAL_POSITION;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getTYPE_MENSTRUATION() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthDataTypes.DT_CONTINUOUS_MENSTRUAL_FLOW");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthDataTypes.DT_CONTINUOUS_MENSTRUAL_FLOW;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthDataTypes.TYPE_MENSTRUATION");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthDataTypes.TYPE_MENSTRUATION;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getTYPE_OVULATION_TEST() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthDataTypes.DT_INSTANTANEOUS_OVULATION_DETECTION");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthDataTypes.DT_INSTANTANEOUS_OVULATION_DETECTION;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthDataTypes.TYPE_OVULATION_TEST");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthDataTypes.TYPE_OVULATION_TEST;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getTYPE_OXYGEN_SATURATION() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthDataTypes.DT_INSTANTANEOUS_SPO2");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthDataTypes.DT_INSTANTANEOUS_SPO2;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthDataTypes.TYPE_OXYGEN_SATURATION");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthDataTypes.TYPE_OXYGEN_SATURATION;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getTYPE_VAGINAL_SPOTTING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthDataTypes.DT_INSTANTANEOUS_VAGINAL_SPECKLE");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthDataTypes.DT_INSTANTANEOUS_VAGINAL_SPECKLE;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthDataTypes.TYPE_VAGINAL_SPOTTING");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthDataTypes.TYPE_VAGINAL_SPOTTING;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.HealthDataTypes dynamicCast(java.lang.Object param0) {
        return ((org.xms.g.fitness.data.HealthDataTypes) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.data.HealthDataTypes;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.data.HealthDataTypes;
        }
    }
}