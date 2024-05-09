package org.xms.g.fitness.data;

public final class DataType extends org.xms.g.utils.XObject implements android.os.Parcelable {
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {
        
        public org.xms.g.fitness.data.DataType createFromParcel(android.os.Parcel param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                com.huawei.hms.hihealth.data.DataType hReturn = com.huawei.hms.hihealth.data.DataType.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn));
            } else {
                com.google.android.gms.fitness.data.DataType gReturn = com.google.android.gms.fitness.data.DataType.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null));
            }
        }
        
        public org.xms.g.fitness.data.DataType[] newArray(int param0) {
            return new org.xms.g.fitness.data.DataType[param0];
        }
    };
    
    public DataType(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public static java.lang.String getMIME_TYPE_PREFIX() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.MIME_TYPE_PREFIX");
            return com.huawei.hms.hihealth.data.DataType.MIME_TYPE_PREFIX;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.MIME_TYPE_PREFIX");
            return com.google.android.gms.fitness.data.DataType.MIME_TYPE_PREFIX;
        }
    }
    
    public static org.xms.g.fitness.data.DataType getAGGREGATE_ACTIVITY_SUMMARY() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.POLYMERIZE_CONTINUOUS_ACTIVITY_STATISTICS");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.POLYMERIZE_CONTINUOUS_ACTIVITY_STATISTICS;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.AGGREGATE_ACTIVITY_SUMMARY");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.AGGREGATE_ACTIVITY_SUMMARY;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getAGGREGATE_BASAL_METABOLIC_RATE_SUMMARY() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.POLYMERIZE_CONTINUOUS_CALORIES_BMR_STATISTICS");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.POLYMERIZE_CONTINUOUS_CALORIES_BMR_STATISTICS;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.AGGREGATE_BASAL_METABOLIC_RATE_SUMMARY");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.AGGREGATE_BASAL_METABOLIC_RATE_SUMMARY;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getAGGREGATE_BODY_FAT_PERCENTAGE_SUMMARY() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.POLYMERIZE_CONTINUOUS_BODY_FAT_RATE_STATISTICS");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.POLYMERIZE_CONTINUOUS_BODY_FAT_RATE_STATISTICS;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.AGGREGATE_BODY_FAT_PERCENTAGE_SUMMARY");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.AGGREGATE_BODY_FAT_PERCENTAGE_SUMMARY;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getAGGREGATE_CALORIES_EXPENDED() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.POLYMERIZE_CALORIES_EXPENDED");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.POLYMERIZE_CALORIES_EXPENDED;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.AGGREGATE_CALORIES_EXPENDED");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.AGGREGATE_CALORIES_EXPENDED;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getAGGREGATE_DISTANCE_DELTA() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.POLYMERIZE_DISTANCE_DELTA");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.POLYMERIZE_DISTANCE_DELTA;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.AGGREGATE_DISTANCE_DELTA");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.AGGREGATE_DISTANCE_DELTA;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getAGGREGATE_HEART_POINTS() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.POLYMERIZE_CONTINUOUS_EXERCISE_INTENSITY_STATISTICS");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.POLYMERIZE_CONTINUOUS_EXERCISE_INTENSITY_STATISTICS;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.AGGREGATE_HEART_POINTS");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.AGGREGATE_HEART_POINTS;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getAGGREGATE_HEART_RATE_SUMMARY() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.POLYMERIZE_CONTINUOUS_HEART_RATE_STATISTICS");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.POLYMERIZE_CONTINUOUS_HEART_RATE_STATISTICS;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.AGGREGATE_HEART_RATE_SUMMARY");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.AGGREGATE_HEART_RATE_SUMMARY;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getAGGREGATE_HEIGHT_SUMMARY() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.POLYMERIZE_CONTINUOUS_HEIGHT_STATISTICS");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.POLYMERIZE_CONTINUOUS_HEIGHT_STATISTICS;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.AGGREGATE_HEIGHT_SUMMARY");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.AGGREGATE_HEIGHT_SUMMARY;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getAGGREGATE_HYDRATION() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.POLYMERIZE_HYDRATION");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.POLYMERIZE_HYDRATION;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.AGGREGATE_HYDRATION");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.AGGREGATE_HYDRATION;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getAGGREGATE_LOCATION_BOUNDING_BOX() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.POLYMERIZE_CONTINUOUS_LOCATION_BOUNDARY_RANGE");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.POLYMERIZE_CONTINUOUS_LOCATION_BOUNDARY_RANGE;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.AGGREGATE_LOCATION_BOUNDING_BOX");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.AGGREGATE_LOCATION_BOUNDING_BOX;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getAGGREGATE_MOVE_MINUTES() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.DT_CONTINUOUS_WORKOUT_DURATION");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.DT_CONTINUOUS_WORKOUT_DURATION;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.AGGREGATE_MOVE_MINUTES");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.AGGREGATE_MOVE_MINUTES;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getAGGREGATE_NUTRITION_SUMMARY() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.POLYMERIZE_CONTINUOUS_NUTRITION_FACTS_STATISTICS");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.POLYMERIZE_CONTINUOUS_NUTRITION_FACTS_STATISTICS;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.AGGREGATE_NUTRITION_SUMMARY");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.AGGREGATE_NUTRITION_SUMMARY;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getAGGREGATE_POWER_SUMMARY() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.POLYMERIZE_CONTINUOUS_POWER_STATISTICS");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.POLYMERIZE_CONTINUOUS_POWER_STATISTICS;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.AGGREGATE_POWER_SUMMARY");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.AGGREGATE_POWER_SUMMARY;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getAGGREGATE_SPEED_SUMMARY() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.POLYMERIZE_CONTINUOUS_SPEED_STATISTICS");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.POLYMERIZE_CONTINUOUS_SPEED_STATISTICS;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.AGGREGATE_SPEED_SUMMARY");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.AGGREGATE_SPEED_SUMMARY;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getAGGREGATE_STEP_COUNT_DELTA() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.POLYMERIZE_STEP_COUNT_DELTA");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.POLYMERIZE_STEP_COUNT_DELTA;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.AGGREGATE_STEP_COUNT_DELTA");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.AGGREGATE_STEP_COUNT_DELTA;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getAGGREGATE_WEIGHT_SUMMARY() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.POLYMERIZE_CONTINUOUS_BODY_WEIGHT_STATISTICS");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.POLYMERIZE_CONTINUOUS_BODY_WEIGHT_STATISTICS;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.AGGREGATE_WEIGHT_SUMMARY");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.AGGREGATE_WEIGHT_SUMMARY;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getTYPE_ACTIVITY_SAMPLES() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.DT_INSTANTANEOUS_ACTIVITY_SAMPLES");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.DT_INSTANTANEOUS_ACTIVITY_SAMPLES;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.TYPE_ACTIVITY_SAMPLES");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.TYPE_ACTIVITY_SAMPLES;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getTYPE_ACTIVITY_SEGMENT() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.DT_CONTINUOUS_ACTIVITY_SEGMENT");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.DT_CONTINUOUS_ACTIVITY_SEGMENT;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.TYPE_ACTIVITY_SEGMENT");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.TYPE_ACTIVITY_SEGMENT;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getTYPE_BASAL_METABOLIC_RATE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.DT_INSTANTANEOUS_CALORIES_BMR");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.DT_INSTANTANEOUS_CALORIES_BMR;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.TYPE_BASAL_METABOLIC_RATE");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.TYPE_BASAL_METABOLIC_RATE;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getTYPE_BODY_FAT_PERCENTAGE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.DT_INSTANTANEOUS_BODY_FAT_RATE");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.DT_INSTANTANEOUS_BODY_FAT_RATE;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.TYPE_BODY_FAT_PERCENTAGE");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.TYPE_BODY_FAT_PERCENTAGE;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getTYPE_CALORIES_EXPENDED() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.DT_CONTINUOUS_CALORIES_BURNT");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.DT_CONTINUOUS_CALORIES_BURNT;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.TYPE_CALORIES_EXPENDED");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.TYPE_CALORIES_EXPENDED;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getTYPE_CYCLING_PEDALING_CADENCE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.DT_INSTANTANEOUS_BIKING_PEDALING_RATE");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.DT_INSTANTANEOUS_BIKING_PEDALING_RATE;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.TYPE_CYCLING_PEDALING_CADENCE");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.TYPE_CYCLING_PEDALING_CADENCE;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getTYPE_CYCLING_PEDALING_CUMULATIVE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.DT_CONTINUOUS_BIKING_PEDALING_TOTAL");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.DT_CONTINUOUS_BIKING_PEDALING_TOTAL;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.TYPE_CYCLING_PEDALING_CUMULATIVE");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.TYPE_CYCLING_PEDALING_CUMULATIVE;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getTYPE_CYCLING_WHEEL_REVOLUTION() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.DT_CONTINUOUS_BIKING_WHEEL_ROTATION_TOTAL");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.DT_CONTINUOUS_BIKING_WHEEL_ROTATION_TOTAL;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.TYPE_CYCLING_WHEEL_REVOLUTION");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.TYPE_CYCLING_WHEEL_REVOLUTION;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getTYPE_CYCLING_WHEEL_RPM() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.DT_INSTANTANEOUS_BIKING_WHEEL_ROTATION");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.DT_INSTANTANEOUS_BIKING_WHEEL_ROTATION;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.TYPE_CYCLING_WHEEL_RPM");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.TYPE_CYCLING_WHEEL_RPM;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getTYPE_DISTANCE_DELTA() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.DT_CONTINUOUS_DISTANCE_DELTA");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.DT_CONTINUOUS_DISTANCE_DELTA;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.TYPE_DISTANCE_DELTA");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.TYPE_DISTANCE_DELTA;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getTYPE_HEART_POINTS() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.DT_CONTINUOUS_EXERCISE_INTENSITY");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.DT_CONTINUOUS_EXERCISE_INTENSITY;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.TYPE_HEART_POINTS");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.TYPE_HEART_POINTS;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getTYPE_HEART_RATE_BPM() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.DT_INSTANTANEOUS_HEART_RATE");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.DT_INSTANTANEOUS_HEART_RATE;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.TYPE_HEART_RATE_BPM");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.TYPE_HEART_RATE_BPM;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getTYPE_HEIGHT() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.DT_INSTANTANEOUS_HEIGHT");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.DT_INSTANTANEOUS_HEIGHT;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.TYPE_HEIGHT");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.TYPE_HEIGHT;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getTYPE_HYDRATION() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.DT_INSTANTANEOUS_HYDRATE");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.DT_INSTANTANEOUS_HYDRATE;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.TYPE_HYDRATION");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.TYPE_HYDRATION;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getTYPE_LOCATION_SAMPLE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.DT_INSTANTANEOUS_LOCATION_SAMPLE");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.DT_INSTANTANEOUS_LOCATION_SAMPLE;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.TYPE_LOCATION_SAMPLE");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.TYPE_LOCATION_SAMPLE;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getTYPE_LOCATION_TRACK() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.DT_INSTANTANEOUS_LOCATION_TRACE");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.DT_INSTANTANEOUS_LOCATION_TRACE;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.TYPE_LOCATION_TRACK");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.TYPE_LOCATION_TRACK;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getTYPE_MOVE_MINUTES() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.POLYMERIZE_CONTINUOUS_WORKOUT_DURATION");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.POLYMERIZE_CONTINUOUS_WORKOUT_DURATION;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.TYPE_MOVE_MINUTES");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.TYPE_MOVE_MINUTES;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getTYPE_NUTRITION() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.DT_INSTANTANEOUS_NUTRITION_FACTS");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.DT_INSTANTANEOUS_NUTRITION_FACTS;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.TYPE_NUTRITION");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.TYPE_NUTRITION;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getTYPE_POWER_SAMPLE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.DT_INSTANTANEOUS_POWER_SAMPLE");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.DT_INSTANTANEOUS_POWER_SAMPLE;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.TYPE_POWER_SAMPLE");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.TYPE_POWER_SAMPLE;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getTYPE_SPEED() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.DT_INSTANTANEOUS_SPEED");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.DT_INSTANTANEOUS_SPEED;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.TYPE_SPEED");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.TYPE_SPEED;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getTYPE_DISTANCE_CUMULATIVE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.DT_CONTINUOUS_DISTANCE_TOTAL");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.DT_CONTINUOUS_DISTANCE_TOTAL;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.TYPE_DISTANCE_CUMULATIVE");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.TYPE_DISTANCE_CUMULATIVE;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getTYPE_STEP_COUNT_CADENCE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.DT_INSTANTANEOUS_STEPS_RATE");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.DT_INSTANTANEOUS_STEPS_RATE;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.TYPE_STEP_COUNT_CADENCE");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.TYPE_STEP_COUNT_CADENCE;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getTYPE_STEP_COUNT_DELTA() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.DT_CONTINUOUS_STEPS_DELTA");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.DT_CONTINUOUS_STEPS_DELTA;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.TYPE_STEP_COUNT_DELTA");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.TYPE_STEP_COUNT_DELTA;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getTYPE_STEP_COUNT_CUMULATIVE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.DT_CONTINUOUS_STEPS_TOTAL");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.DT_CONTINUOUS_STEPS_TOTAL;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.TYPE_STEP_COUNT_CUMULATIVE");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.TYPE_STEP_COUNT_CUMULATIVE;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getTYPE_WEIGHT() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.DT_INSTANTANEOUS_BODY_WEIGHT");
            com.huawei.hms.hihealth.data.DataType hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.DataType.DT_INSTANTANEOUS_BODY_WEIGHT;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.TYPE_WEIGHT");
            com.google.android.gms.fitness.data.DataType gReturn = null;
            gReturn = com.google.android.gms.fitness.data.DataType.TYPE_WEIGHT;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataType getTYPE_WORKOUT_EXERCISE() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public final boolean equals(java.lang.Object param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataType) this.getHInstance()).equals(param0)");
            return ((com.huawei.hms.hihealth.data.DataType) this.getHInstance()).equals(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataType) this.getGInstance()).equals(param0)");
            return ((com.google.android.gms.fitness.data.DataType) this.getGInstance()).equals(param0);
        }
    }
    
    public static java.util.List<org.xms.g.fitness.data.DataType> getAggregatesForInput(org.xms.g.fitness.data.DataType param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.getPolymerizesForInput(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))))");
            java.util.List hReturn = com.huawei.hms.hihealth.data.DataType.getPolymerizesForInput(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))));
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(hReturn, new org.xms.g.utils.Function<com.huawei.hms.hihealth.data.DataType, org.xms.g.fitness.data.DataType>() {
                
                public org.xms.g.fitness.data.DataType apply(com.huawei.hms.hihealth.data.DataType param0) {
                    return new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, param0));
                }
            }));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.getAggregatesForInput(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))))");
            java.util.List gReturn = com.google.android.gms.fitness.data.DataType.getAggregatesForInput(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))));
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(gReturn, new org.xms.g.utils.Function<com.google.android.gms.fitness.data.DataType, org.xms.g.fitness.data.DataType>() {
                
                public org.xms.g.fitness.data.DataType apply(com.google.android.gms.fitness.data.DataType param0) {
                    return new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(param0, null));
                }
            }));
        }
    }
    
    public java.util.List<org.xms.g.fitness.data.Field> getFields() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataType) this.getHInstance()).getFields()");
            java.util.List hReturn = ((com.huawei.hms.hihealth.data.DataType) this.getHInstance()).getFields();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(hReturn, new org.xms.g.utils.Function<com.huawei.hms.hihealth.data.Field, org.xms.g.fitness.data.Field>() {
                
                public org.xms.g.fitness.data.Field apply(com.huawei.hms.hihealth.data.Field param0) {
                    return new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, param0));
                }
            }));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataType) this.getGInstance()).getFields()");
            java.util.List gReturn = ((com.google.android.gms.fitness.data.DataType) this.getGInstance()).getFields();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(gReturn, new org.xms.g.utils.Function<com.google.android.gms.fitness.data.Field, org.xms.g.fitness.data.Field>() {
                
                public org.xms.g.fitness.data.Field apply(com.google.android.gms.fitness.data.Field param0) {
                    return new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(param0, null));
                }
            }));
        }
    }
    
    public static java.lang.String getMimeType(org.xms.g.fitness.data.DataType param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.DataType.getMimeType(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))))");
            return com.huawei.hms.hihealth.data.DataType.getMimeType(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataType.getMimeType(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))))");
            return com.google.android.gms.fitness.data.DataType.getMimeType(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))));
        }
    }
    
    public final java.lang.String getName() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataType) this.getHInstance()).getName()");
            return ((com.huawei.hms.hihealth.data.DataType) this.getHInstance()).getName();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataType) this.getGInstance()).getName()");
            return ((com.google.android.gms.fitness.data.DataType) this.getGInstance()).getName();
        }
    }
    
    public final int hashCode() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataType) this.getHInstance()).hashCode()");
            return ((com.huawei.hms.hihealth.data.DataType) this.getHInstance()).hashCode();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataType) this.getGInstance()).hashCode()");
            return ((com.google.android.gms.fitness.data.DataType) this.getGInstance()).hashCode();
        }
    }
    
    public final int indexOf(org.xms.g.fitness.data.Field param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataType) this.getHInstance()).indexOf(((com.huawei.hms.hihealth.data.Field) ((param0) == null ? null : (param0.getHInstance()))))");
            return ((com.huawei.hms.hihealth.data.DataType) this.getHInstance()).indexOf(((com.huawei.hms.hihealth.data.Field) ((param0) == null ? null : (param0.getHInstance()))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataType) this.getGInstance()).indexOf(((com.google.android.gms.fitness.data.Field) ((param0) == null ? null : (param0.getGInstance()))))");
            return ((com.google.android.gms.fitness.data.DataType) this.getGInstance()).indexOf(((com.google.android.gms.fitness.data.Field) ((param0) == null ? null : (param0.getGInstance()))));
        }
    }
    
    public final java.lang.String toString() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataType) this.getHInstance()).toString()");
            return ((com.huawei.hms.hihealth.data.DataType) this.getHInstance()).toString();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataType) this.getGInstance()).toString()");
            return ((com.google.android.gms.fitness.data.DataType) this.getGInstance()).toString();
        }
    }
    
    public final void writeToParcel(android.os.Parcel param0, int param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.DataType) this.getHInstance()).writeToParcel(param0, param1)");
            ((com.huawei.hms.hihealth.data.DataType) this.getHInstance()).writeToParcel(param0, param1);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataType) this.getGInstance()).writeToParcel(param0, param1)");
            ((com.google.android.gms.fitness.data.DataType) this.getGInstance()).writeToParcel(param0, param1);
        }
    }
    
    public int describeContents() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.data.DataType dynamicCast(java.lang.Object param0) {
        return ((org.xms.g.fitness.data.DataType) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.data.DataType;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.data.DataType;
        }
    }
}