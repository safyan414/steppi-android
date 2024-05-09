package org.xms.g.fitness.data;

public final class HealthFields extends org.xms.g.utils.XObject {
    
    public HealthFields(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public static int getBLOOD_GLUCOSE_SPECIMEN_SOURCE_CAPILLARY_BLOOD() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.SAMPLE_SOURCE_CAPILLARY_BLOOD");
            return com.huawei.hms.hihealth.data.HealthFields.SAMPLE_SOURCE_CAPILLARY_BLOOD;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.BLOOD_GLUCOSE_SPECIMEN_SOURCE_CAPILLARY_BLOOD");
            return com.google.android.gms.fitness.data.HealthFields.BLOOD_GLUCOSE_SPECIMEN_SOURCE_CAPILLARY_BLOOD;
        }
    }
    
    public static int getBLOOD_GLUCOSE_SPECIMEN_SOURCE_INTERSTITIAL_FLUID() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.SAMPLE_SOURCE_INTERSTITIAL_FLUID");
            return com.huawei.hms.hihealth.data.HealthFields.SAMPLE_SOURCE_INTERSTITIAL_FLUID;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.BLOOD_GLUCOSE_SPECIMEN_SOURCE_INTERSTITIAL_FLUID");
            return com.google.android.gms.fitness.data.HealthFields.BLOOD_GLUCOSE_SPECIMEN_SOURCE_INTERSTITIAL_FLUID;
        }
    }
    
    public static int getBLOOD_GLUCOSE_SPECIMEN_SOURCE_PLASMA() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.SAMPLE_SOURCE_PLASMA");
            return com.huawei.hms.hihealth.data.HealthFields.SAMPLE_SOURCE_PLASMA;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.BLOOD_GLUCOSE_SPECIMEN_SOURCE_PLASMA");
            return com.google.android.gms.fitness.data.HealthFields.BLOOD_GLUCOSE_SPECIMEN_SOURCE_PLASMA;
        }
    }
    
    public static int getBLOOD_GLUCOSE_SPECIMEN_SOURCE_SERUM() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.SAMPLE_SOURCE_SERUM");
            return com.huawei.hms.hihealth.data.HealthFields.SAMPLE_SOURCE_SERUM;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.BLOOD_GLUCOSE_SPECIMEN_SOURCE_SERUM");
            return com.google.android.gms.fitness.data.HealthFields.BLOOD_GLUCOSE_SPECIMEN_SOURCE_SERUM;
        }
    }
    
    public static int getBLOOD_GLUCOSE_SPECIMEN_SOURCE_TEARS() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.SAMPLE_SOURCE_TEARS");
            return com.huawei.hms.hihealth.data.HealthFields.SAMPLE_SOURCE_TEARS;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.BLOOD_GLUCOSE_SPECIMEN_SOURCE_TEARS");
            return com.google.android.gms.fitness.data.HealthFields.BLOOD_GLUCOSE_SPECIMEN_SOURCE_TEARS;
        }
    }
    
    public static int getBLOOD_GLUCOSE_SPECIMEN_SOURCE_WHOLE_BLOOD() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.SAMPLE_SOURCE_WHOLE_BLOOD");
            return com.huawei.hms.hihealth.data.HealthFields.SAMPLE_SOURCE_WHOLE_BLOOD;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.BLOOD_GLUCOSE_SPECIMEN_SOURCE_WHOLE_BLOOD");
            return com.google.android.gms.fitness.data.HealthFields.BLOOD_GLUCOSE_SPECIMEN_SOURCE_WHOLE_BLOOD;
        }
    }
    
    public static int getBLOOD_PRESSURE_MEASUREMENT_LOCATION_LEFT_UPPER_ARM() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.MEASURE_BODY_PART_OF_BLOOD_PRESSURE_LEFT_UPPER_ARM");
            return com.huawei.hms.hihealth.data.HealthFields.MEASURE_BODY_PART_OF_BLOOD_PRESSURE_LEFT_UPPER_ARM;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.BLOOD_PRESSURE_MEASUREMENT_LOCATION_LEFT_UPPER_ARM");
            return com.google.android.gms.fitness.data.HealthFields.BLOOD_PRESSURE_MEASUREMENT_LOCATION_LEFT_UPPER_ARM;
        }
    }
    
    public static int getBLOOD_PRESSURE_MEASUREMENT_LOCATION_LEFT_WRIST() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.MEASURE_BODY_PART_OF_BLOOD_PRESSURE_LEFT_WRIST");
            return com.huawei.hms.hihealth.data.HealthFields.MEASURE_BODY_PART_OF_BLOOD_PRESSURE_LEFT_WRIST;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.BLOOD_PRESSURE_MEASUREMENT_LOCATION_LEFT_WRIST");
            return com.google.android.gms.fitness.data.HealthFields.BLOOD_PRESSURE_MEASUREMENT_LOCATION_LEFT_WRIST;
        }
    }
    
    public static int getBLOOD_PRESSURE_MEASUREMENT_LOCATION_RIGHT_UPPER_ARM() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.MEASURE_BODY_PART_OF_BLOOD_PRESSURE_RIGHT_UPPER_ARM");
            return com.huawei.hms.hihealth.data.HealthFields.MEASURE_BODY_PART_OF_BLOOD_PRESSURE_RIGHT_UPPER_ARM;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.BLOOD_PRESSURE_MEASUREMENT_LOCATION_RIGHT_UPPER_ARM");
            return com.google.android.gms.fitness.data.HealthFields.BLOOD_PRESSURE_MEASUREMENT_LOCATION_RIGHT_UPPER_ARM;
        }
    }
    
    public static int getBLOOD_PRESSURE_MEASUREMENT_LOCATION_RIGHT_WRIST() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.MEASURE_BODY_PART_OF_BLOOD_PRESSURE_RIGHT_WRIST");
            return com.huawei.hms.hihealth.data.HealthFields.MEASURE_BODY_PART_OF_BLOOD_PRESSURE_RIGHT_WRIST;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.BLOOD_PRESSURE_MEASUREMENT_LOCATION_RIGHT_WRIST");
            return com.google.android.gms.fitness.data.HealthFields.BLOOD_PRESSURE_MEASUREMENT_LOCATION_RIGHT_WRIST;
        }
    }
    
    public static int getBODY_POSITION_LYING_DOWN() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.BODY_POSTURE_LYING_DOWN");
            return com.huawei.hms.hihealth.data.HealthFields.BODY_POSTURE_LYING_DOWN;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.BODY_POSITION_LYING_DOWN");
            return com.google.android.gms.fitness.data.HealthFields.BODY_POSITION_LYING_DOWN;
        }
    }
    
    public static int getBODY_POSITION_SEMI_RECUMBENT() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.BODY_POSTURE_SEMI_RECUMBENT");
            return com.huawei.hms.hihealth.data.HealthFields.BODY_POSTURE_SEMI_RECUMBENT;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.BODY_POSITION_SEMI_RECUMBENT");
            return com.google.android.gms.fitness.data.HealthFields.BODY_POSITION_SEMI_RECUMBENT;
        }
    }
    
    public static int getBODY_POSITION_SITTING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.BODY_POSTURE_SITTING");
            return com.huawei.hms.hihealth.data.HealthFields.BODY_POSTURE_SITTING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.BODY_POSITION_SITTING");
            return com.google.android.gms.fitness.data.HealthFields.BODY_POSITION_SITTING;
        }
    }
    
    public static int getBODY_POSITION_STANDING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.BODY_POSTURE_STANDING");
            return com.huawei.hms.hihealth.data.HealthFields.BODY_POSTURE_STANDING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.BODY_POSITION_STANDING");
            return com.google.android.gms.fitness.data.HealthFields.BODY_POSITION_STANDING;
        }
    }
    
    public static int getBODY_TEMPERATURE_MEASUREMENT_LOCATION_AXILLARY() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.MEASURE_BODY_PART_OF_TEMPERATURE_AXILLARY");
            return com.huawei.hms.hihealth.data.HealthFields.MEASURE_BODY_PART_OF_TEMPERATURE_AXILLARY;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.BODY_TEMPERATURE_MEASUREMENT_LOCATION_AXILLARY");
            return com.google.android.gms.fitness.data.HealthFields.BODY_TEMPERATURE_MEASUREMENT_LOCATION_AXILLARY;
        }
    }
    
    public static int getBODY_TEMPERATURE_MEASUREMENT_LOCATION_FINGER() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.MEASURE_BODY_PART_OF_TEMPERATURE_FINGER");
            return com.huawei.hms.hihealth.data.HealthFields.MEASURE_BODY_PART_OF_TEMPERATURE_FINGER;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.BODY_TEMPERATURE_MEASUREMENT_LOCATION_FINGER");
            return com.google.android.gms.fitness.data.HealthFields.BODY_TEMPERATURE_MEASUREMENT_LOCATION_FINGER;
        }
    }
    
    public static int getBODY_TEMPERATURE_MEASUREMENT_LOCATION_FOREHEAD() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.MEASURE_BODY_PART_OF_TEMPERATURE_FOREHEAD");
            return com.huawei.hms.hihealth.data.HealthFields.MEASURE_BODY_PART_OF_TEMPERATURE_FOREHEAD;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.BODY_TEMPERATURE_MEASUREMENT_LOCATION_FOREHEAD");
            return com.google.android.gms.fitness.data.HealthFields.BODY_TEMPERATURE_MEASUREMENT_LOCATION_FOREHEAD;
        }
    }
    
    public static int getBODY_TEMPERATURE_MEASUREMENT_LOCATION_ORAL() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.MEASURE_BODY_PART_OF_TEMPERATURE_ORAL");
            return com.huawei.hms.hihealth.data.HealthFields.MEASURE_BODY_PART_OF_TEMPERATURE_ORAL;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.BODY_TEMPERATURE_MEASUREMENT_LOCATION_ORAL");
            return com.google.android.gms.fitness.data.HealthFields.BODY_TEMPERATURE_MEASUREMENT_LOCATION_ORAL;
        }
    }
    
    public static int getBODY_TEMPERATURE_MEASUREMENT_LOCATION_RECTAL() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.MEASURE_BODY_PART_OF_TEMPERATURE_RECTAL");
            return com.huawei.hms.hihealth.data.HealthFields.MEASURE_BODY_PART_OF_TEMPERATURE_RECTAL;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.BODY_TEMPERATURE_MEASUREMENT_LOCATION_RECTAL");
            return com.google.android.gms.fitness.data.HealthFields.BODY_TEMPERATURE_MEASUREMENT_LOCATION_RECTAL;
        }
    }
    
    public static int getBODY_TEMPERATURE_MEASUREMENT_LOCATION_TEMPORAL_ARTERY() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.MEASURE_BODY_PART_OF_TEMPERATURE_TEMPORAL_ARTERY");
            return com.huawei.hms.hihealth.data.HealthFields.MEASURE_BODY_PART_OF_TEMPERATURE_TEMPORAL_ARTERY;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.BODY_TEMPERATURE_MEASUREMENT_LOCATION_TEMPORAL_ARTERY");
            return com.google.android.gms.fitness.data.HealthFields.BODY_TEMPERATURE_MEASUREMENT_LOCATION_TEMPORAL_ARTERY;
        }
    }
    
    public static int getBODY_TEMPERATURE_MEASUREMENT_LOCATION_TOE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.MEASURE_BODY_PART_OF_TEMPERATURE_TOE");
            return com.huawei.hms.hihealth.data.HealthFields.MEASURE_BODY_PART_OF_TEMPERATURE_TOE;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.BODY_TEMPERATURE_MEASUREMENT_LOCATION_TOE");
            return com.google.android.gms.fitness.data.HealthFields.BODY_TEMPERATURE_MEASUREMENT_LOCATION_TOE;
        }
    }
    
    public static int getBODY_TEMPERATURE_MEASUREMENT_LOCATION_TYMPANIC() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.MEASURE_BODY_PART_OF_TEMPERATURE_TYMPANIC");
            return com.huawei.hms.hihealth.data.HealthFields.MEASURE_BODY_PART_OF_TEMPERATURE_TYMPANIC;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.BODY_TEMPERATURE_MEASUREMENT_LOCATION_TYMPANIC");
            return com.google.android.gms.fitness.data.HealthFields.BODY_TEMPERATURE_MEASUREMENT_LOCATION_TYMPANIC;
        }
    }
    
    public static int getBODY_TEMPERATURE_MEASUREMENT_LOCATION_VAGINAL() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.MEASURE_BODY_PART_OF_TEMPERATURE_VAGINAL");
            return com.huawei.hms.hihealth.data.HealthFields.MEASURE_BODY_PART_OF_TEMPERATURE_VAGINAL;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.BODY_TEMPERATURE_MEASUREMENT_LOCATION_VAGINAL");
            return com.google.android.gms.fitness.data.HealthFields.BODY_TEMPERATURE_MEASUREMENT_LOCATION_VAGINAL;
        }
    }
    
    public static int getBODY_TEMPERATURE_MEASUREMENT_LOCATION_WRIST() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.MEASURE_BODY_PART_OF_TEMPERATURE_WRIST");
            return com.huawei.hms.hihealth.data.HealthFields.MEASURE_BODY_PART_OF_TEMPERATURE_WRIST;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.BODY_TEMPERATURE_MEASUREMENT_LOCATION_WRIST");
            return com.google.android.gms.fitness.data.HealthFields.BODY_TEMPERATURE_MEASUREMENT_LOCATION_WRIST;
        }
    }
    
    public static int getCERVICAL_DILATION_CLOSED() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.DILATION_STATUS_CLOSED");
            return com.huawei.hms.hihealth.data.HealthFields.DILATION_STATUS_CLOSED;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.CERVICAL_DILATION_CLOSED");
            return com.google.android.gms.fitness.data.HealthFields.CERVICAL_DILATION_CLOSED;
        }
    }
    
    public static int getCERVICAL_DILATION_MEDIUM() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.DILATION_STATUS_MEDIUM");
            return com.huawei.hms.hihealth.data.HealthFields.DILATION_STATUS_MEDIUM;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.CERVICAL_DILATION_MEDIUM");
            return com.google.android.gms.fitness.data.HealthFields.CERVICAL_DILATION_MEDIUM;
        }
    }
    
    public static int getCERVICAL_DILATION_OPEN() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.DILATION_STATUS_OPEN");
            return com.huawei.hms.hihealth.data.HealthFields.DILATION_STATUS_OPEN;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.CERVICAL_DILATION_OPEN");
            return com.google.android.gms.fitness.data.HealthFields.CERVICAL_DILATION_OPEN;
        }
    }
    
    public static int getCERVICAL_FIRMNESS_FIRM() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIRMNESS_LEVEL_FIRM");
            return com.huawei.hms.hihealth.data.HealthFields.FIRMNESS_LEVEL_FIRM;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.CERVICAL_FIRMNESS_FIRM");
            return com.google.android.gms.fitness.data.HealthFields.CERVICAL_FIRMNESS_FIRM;
        }
    }
    
    public static int getCERVICAL_FIRMNESS_MEDIUM() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIRMNESS_LEVEL_MEDIUM");
            return com.huawei.hms.hihealth.data.HealthFields.FIRMNESS_LEVEL_MEDIUM;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.CERVICAL_FIRMNESS_MEDIUM");
            return com.google.android.gms.fitness.data.HealthFields.CERVICAL_FIRMNESS_MEDIUM;
        }
    }
    
    public static int getCERVICAL_FIRMNESS_SOFT() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIRMNESS_LEVEL_SOFT");
            return com.huawei.hms.hihealth.data.HealthFields.FIRMNESS_LEVEL_SOFT;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.CERVICAL_FIRMNESS_SOFT");
            return com.google.android.gms.fitness.data.HealthFields.CERVICAL_FIRMNESS_SOFT;
        }
    }
    
    public static int getCERVICAL_MUCUS_AMOUNT_HEAVY() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.AMOUNT_HEAVY");
            return com.huawei.hms.hihealth.data.HealthFields.AMOUNT_HEAVY;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.CERVICAL_MUCUS_AMOUNT_HEAVY");
            return com.google.android.gms.fitness.data.HealthFields.CERVICAL_MUCUS_AMOUNT_HEAVY;
        }
    }
    
    public static int getCERVICAL_MUCUS_AMOUNT_LIGHT() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.AMOUNT_LIGHT");
            return com.huawei.hms.hihealth.data.HealthFields.AMOUNT_LIGHT;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.CERVICAL_MUCUS_AMOUNT_LIGHT");
            return com.google.android.gms.fitness.data.HealthFields.CERVICAL_MUCUS_AMOUNT_LIGHT;
        }
    }
    
    public static int getCERVICAL_MUCUS_AMOUNT_MEDIUM() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.AMOUNT_MEDIUM");
            return com.huawei.hms.hihealth.data.HealthFields.AMOUNT_MEDIUM;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.CERVICAL_MUCUS_AMOUNT_MEDIUM");
            return com.google.android.gms.fitness.data.HealthFields.CERVICAL_MUCUS_AMOUNT_MEDIUM;
        }
    }
    
    public static int getCERVICAL_MUCUS_TEXTURE_CREAMY() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.TEXTURE_CREAMY");
            return com.huawei.hms.hihealth.data.HealthFields.TEXTURE_CREAMY;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.CERVICAL_MUCUS_TEXTURE_CREAMY");
            return com.google.android.gms.fitness.data.HealthFields.CERVICAL_MUCUS_TEXTURE_CREAMY;
        }
    }
    
    public static int getCERVICAL_MUCUS_TEXTURE_DRY() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.TEXTURE_DRY");
            return com.huawei.hms.hihealth.data.HealthFields.TEXTURE_DRY;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.CERVICAL_MUCUS_TEXTURE_DRY");
            return com.google.android.gms.fitness.data.HealthFields.CERVICAL_MUCUS_TEXTURE_DRY;
        }
    }
    
    public static int getCERVICAL_MUCUS_TEXTURE_EGG_WHITE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.TEXTURE_EGG_WHITE");
            return com.huawei.hms.hihealth.data.HealthFields.TEXTURE_EGG_WHITE;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.CERVICAL_MUCUS_TEXTURE_EGG_WHITE");
            return com.google.android.gms.fitness.data.HealthFields.CERVICAL_MUCUS_TEXTURE_EGG_WHITE;
        }
    }
    
    public static int getCERVICAL_MUCUS_TEXTURE_STICKY() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.TEXTURE_STICKY");
            return com.huawei.hms.hihealth.data.HealthFields.TEXTURE_STICKY;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.CERVICAL_MUCUS_TEXTURE_STICKY");
            return com.google.android.gms.fitness.data.HealthFields.CERVICAL_MUCUS_TEXTURE_STICKY;
        }
    }
    
    public static int getCERVICAL_MUCUS_TEXTURE_WATERY() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.TEXTURE_WATERY");
            return com.huawei.hms.hihealth.data.HealthFields.TEXTURE_WATERY;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.CERVICAL_MUCUS_TEXTURE_WATERY");
            return com.google.android.gms.fitness.data.HealthFields.CERVICAL_MUCUS_TEXTURE_WATERY;
        }
    }
    
    public static int getCERVICAL_POSITION_HIGH() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.POSITION_HIGH");
            return com.huawei.hms.hihealth.data.HealthFields.POSITION_HIGH;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.CERVICAL_POSITION_HIGH");
            return com.google.android.gms.fitness.data.HealthFields.CERVICAL_POSITION_HIGH;
        }
    }
    
    public static int getCERVICAL_POSITION_LOW() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.POSITION_LOW");
            return com.huawei.hms.hihealth.data.HealthFields.POSITION_LOW;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.CERVICAL_POSITION_LOW");
            return com.google.android.gms.fitness.data.HealthFields.CERVICAL_POSITION_LOW;
        }
    }
    
    public static int getCERVICAL_POSITION_MEDIUM() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.POSITION_MEDIUM");
            return com.huawei.hms.hihealth.data.HealthFields.POSITION_MEDIUM;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.CERVICAL_POSITION_MEDIUM");
            return com.google.android.gms.fitness.data.HealthFields.CERVICAL_POSITION_MEDIUM;
        }
    }
    
    public static int getFIELD_TEMPORAL_RELATION_TO_MEAL_AFTER_MEAL() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_CORRELATION_WITH_MEALTIME_AFTER_MEAL");
            return com.huawei.hms.hihealth.data.HealthFields.FIELD_CORRELATION_WITH_MEALTIME_AFTER_MEAL;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_TEMPORAL_RELATION_TO_MEAL_AFTER_MEAL");
            return com.google.android.gms.fitness.data.HealthFields.FIELD_TEMPORAL_RELATION_TO_MEAL_AFTER_MEAL;
        }
    }
    
    public static int getFIELD_TEMPORAL_RELATION_TO_MEAL_BEFORE_MEAL() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_CORRELATION_WITH_MEALTIME_BEFORE_MEAL");
            return com.huawei.hms.hihealth.data.HealthFields.FIELD_CORRELATION_WITH_MEALTIME_BEFORE_MEAL;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_TEMPORAL_RELATION_TO_MEAL_BEFORE_MEAL");
            return com.google.android.gms.fitness.data.HealthFields.FIELD_TEMPORAL_RELATION_TO_MEAL_BEFORE_MEAL;
        }
    }
    
    public static int getFIELD_TEMPORAL_RELATION_TO_MEAL_FASTING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_CORRELATION_WITH_MEALTIME_FASTING");
            return com.huawei.hms.hihealth.data.HealthFields.FIELD_CORRELATION_WITH_MEALTIME_FASTING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_TEMPORAL_RELATION_TO_MEAL_FASTING");
            return com.google.android.gms.fitness.data.HealthFields.FIELD_TEMPORAL_RELATION_TO_MEAL_FASTING;
        }
    }
    
    public static int getFIELD_TEMPORAL_RELATION_TO_MEAL_GENERAL() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_CORRELATION_WITH_MEALTIME_GENERAL");
            return com.huawei.hms.hihealth.data.HealthFields.FIELD_CORRELATION_WITH_MEALTIME_GENERAL;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_TEMPORAL_RELATION_TO_MEAL_GENERAL");
            return com.google.android.gms.fitness.data.HealthFields.FIELD_TEMPORAL_RELATION_TO_MEAL_GENERAL;
        }
    }
    
    public static int getMENSTRUAL_FLOW_HEAVY() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.VOLUME_HEAVY");
            return com.huawei.hms.hihealth.data.HealthFields.VOLUME_HEAVY;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.MENSTRUAL_FLOW_HEAVY");
            return com.google.android.gms.fitness.data.HealthFields.MENSTRUAL_FLOW_HEAVY;
        }
    }
    
    public static int getMENSTRUAL_FLOW_LIGHT() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.VOLUME_LIGHT");
            return com.huawei.hms.hihealth.data.HealthFields.VOLUME_LIGHT;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.MENSTRUAL_FLOW_LIGHT");
            return com.google.android.gms.fitness.data.HealthFields.MENSTRUAL_FLOW_LIGHT;
        }
    }
    
    public static int getMENSTRUAL_FLOW_MEDIUM() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.VOLUME_MEDIUM");
            return com.huawei.hms.hihealth.data.HealthFields.VOLUME_MEDIUM;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.MENSTRUAL_FLOW_MEDIUM");
            return com.google.android.gms.fitness.data.HealthFields.MENSTRUAL_FLOW_MEDIUM;
        }
    }
    
    public static int getMENSTRUAL_FLOW_SPOTTING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.VOLUME_SPOTTING");
            return com.huawei.hms.hihealth.data.HealthFields.VOLUME_SPOTTING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.MENSTRUAL_FLOW_SPOTTING");
            return com.google.android.gms.fitness.data.HealthFields.MENSTRUAL_FLOW_SPOTTING;
        }
    }
    
    public static int getOVULATION_TEST_RESULT_NEGATIVE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.DETECTION_RESULT_NEGATIVE");
            return com.huawei.hms.hihealth.data.HealthFields.DETECTION_RESULT_NEGATIVE;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.OVULATION_TEST_RESULT_NEGATIVE");
            return com.google.android.gms.fitness.data.HealthFields.OVULATION_TEST_RESULT_NEGATIVE;
        }
    }
    
    public static int getOVULATION_TEST_RESULT_POSITIVE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.DETECTION_RESULT_POSITIVE");
            return com.huawei.hms.hihealth.data.HealthFields.DETECTION_RESULT_POSITIVE;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.OVULATION_TEST_RESULT_POSITIVE");
            return com.google.android.gms.fitness.data.HealthFields.OVULATION_TEST_RESULT_POSITIVE;
        }
    }
    
    public static int getOXYGEN_SATURATION_MEASUREMENT_METHOD_PULSE_OXIMETRY() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.SPO2_MEASUREMENT_APPROACH_PULSE_OXIMETRY");
            return com.huawei.hms.hihealth.data.HealthFields.SPO2_MEASUREMENT_APPROACH_PULSE_OXIMETRY;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.OXYGEN_SATURATION_MEASUREMENT_METHOD_PULSE_OXIMETRY");
            return com.google.android.gms.fitness.data.HealthFields.OXYGEN_SATURATION_MEASUREMENT_METHOD_PULSE_OXIMETRY;
        }
    }
    
    public static int getOXYGEN_SATURATION_SYSTEM_PERIPHERAL_CAPILLARY() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.SPO2_MEASUREMENT_MECHANISM_PERIPHERAL_CAPILLARY");
            return com.huawei.hms.hihealth.data.HealthFields.SPO2_MEASUREMENT_MECHANISM_PERIPHERAL_CAPILLARY;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.OXYGEN_SATURATION_SYSTEM_PERIPHERAL_CAPILLARY");
            return com.google.android.gms.fitness.data.HealthFields.OXYGEN_SATURATION_SYSTEM_PERIPHERAL_CAPILLARY;
        }
    }
    
    public static int getOXYGEN_THERAPY_ADMINISTRATION_MODE_NASAL_CANULA() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.OXYGEN_THERAPY_NASAL_CANULA");
            return com.huawei.hms.hihealth.data.HealthFields.OXYGEN_THERAPY_NASAL_CANULA;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.OXYGEN_THERAPY_ADMINISTRATION_MODE_NASAL_CANULA");
            return com.google.android.gms.fitness.data.HealthFields.OXYGEN_THERAPY_ADMINISTRATION_MODE_NASAL_CANULA;
        }
    }
    
    public static int getTEMPORAL_RELATION_TO_SLEEP_BEFORE_SLEEP() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.CORRELATION_WITH_SLEEP_STATE_BEFORE_SLEEP");
            return com.huawei.hms.hihealth.data.HealthFields.CORRELATION_WITH_SLEEP_STATE_BEFORE_SLEEP;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.TEMPORAL_RELATION_TO_SLEEP_BEFORE_SLEEP");
            return com.google.android.gms.fitness.data.HealthFields.TEMPORAL_RELATION_TO_SLEEP_BEFORE_SLEEP;
        }
    }
    
    public static int getTEMPORAL_RELATION_TO_SLEEP_DURING_SLEEP() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.CORRELATION_WITH_SLEEP_STATE_DURING_SLEEP");
            return com.huawei.hms.hihealth.data.HealthFields.CORRELATION_WITH_SLEEP_STATE_DURING_SLEEP;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.TEMPORAL_RELATION_TO_SLEEP_DURING_SLEEP");
            return com.google.android.gms.fitness.data.HealthFields.TEMPORAL_RELATION_TO_SLEEP_DURING_SLEEP;
        }
    }
    
    public static int getTEMPORAL_RELATION_TO_SLEEP_FULLY_AWAKE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.CORRELATION_WITH_SLEEP_STATE_FULLY_AWAKE");
            return com.huawei.hms.hihealth.data.HealthFields.CORRELATION_WITH_SLEEP_STATE_FULLY_AWAKE;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.TEMPORAL_RELATION_TO_SLEEP_FULLY_AWAKE");
            return com.google.android.gms.fitness.data.HealthFields.TEMPORAL_RELATION_TO_SLEEP_FULLY_AWAKE;
        }
    }
    
    public static int getTEMPORAL_RELATION_TO_SLEEP_ON_WAKING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.CORRELATION_WITH_SLEEP_STATE_ON_WAKING");
            return com.huawei.hms.hihealth.data.HealthFields.CORRELATION_WITH_SLEEP_STATE_ON_WAKING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.TEMPORAL_RELATION_TO_SLEEP_ON_WAKING");
            return com.google.android.gms.fitness.data.HealthFields.TEMPORAL_RELATION_TO_SLEEP_ON_WAKING;
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_BLOOD_GLUCOSE_LEVEL() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_LEVEL");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthFields.FIELD_LEVEL;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_BLOOD_GLUCOSE_LEVEL");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthFields.FIELD_BLOOD_GLUCOSE_LEVEL;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_BLOOD_GLUCOSE_SPECIMEN_SOURCE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_SAMPLE_SOURCE");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthFields.FIELD_SAMPLE_SOURCE;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_BLOOD_GLUCOSE_SPECIMEN_SOURCE");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthFields.FIELD_BLOOD_GLUCOSE_SPECIMEN_SOURCE;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_BLOOD_PRESSURE_DIASTOLIC() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_DIASTOLIC_PRESSURE");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthFields.FIELD_DIASTOLIC_PRESSURE;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_BLOOD_PRESSURE_DIASTOLIC");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthFields.FIELD_BLOOD_PRESSURE_DIASTOLIC;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_BLOOD_PRESSURE_DIASTOLIC_AVERAGE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_DIASTOLIC_PRESSURE_AVG");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthFields.FIELD_DIASTOLIC_PRESSURE_AVG;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_BLOOD_PRESSURE_DIASTOLIC_AVERAGE");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthFields.FIELD_BLOOD_PRESSURE_DIASTOLIC_AVERAGE;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_BLOOD_PRESSURE_DIASTOLIC_MAX() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_DIASTOLIC_PRESSURE_MAX");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthFields.FIELD_DIASTOLIC_PRESSURE_MAX;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_BLOOD_PRESSURE_DIASTOLIC_MAX");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthFields.FIELD_BLOOD_PRESSURE_DIASTOLIC_MAX;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_BLOOD_PRESSURE_DIASTOLIC_MIN() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_DIASTOLIC_PRESSURE_MIN");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthFields.FIELD_DIASTOLIC_PRESSURE_MIN;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_BLOOD_PRESSURE_DIASTOLIC_MIN");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthFields.FIELD_BLOOD_PRESSURE_DIASTOLIC_MIN;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_BLOOD_PRESSURE_MEASUREMENT_LOCATION() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_MEASURE_BODY_PART_OF_BLOOD_PRESSURE");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthFields.FIELD_MEASURE_BODY_PART_OF_BLOOD_PRESSURE;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_BLOOD_PRESSURE_MEASUREMENT_LOCATION");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthFields.FIELD_BLOOD_PRESSURE_MEASUREMENT_LOCATION;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_BLOOD_PRESSURE_SYSTOLIC() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_SYSTOLIC_PRESSURE");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthFields.FIELD_SYSTOLIC_PRESSURE;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_BLOOD_PRESSURE_SYSTOLIC");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthFields.FIELD_BLOOD_PRESSURE_SYSTOLIC;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_BLOOD_PRESSURE_SYSTOLIC_AVERAGE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_SYSTOLIC_PRESSURE_AVG");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthFields.FIELD_SYSTOLIC_PRESSURE_AVG;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_BLOOD_PRESSURE_SYSTOLIC_AVERAGE");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthFields.FIELD_BLOOD_PRESSURE_SYSTOLIC_AVERAGE;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_BLOOD_PRESSURE_SYSTOLIC_MAX() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_SYSTOLIC_PRESSURE_MAX");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthFields.FIELD_SYSTOLIC_PRESSURE_MAX;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_BLOOD_PRESSURE_SYSTOLIC_MAX");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthFields.FIELD_BLOOD_PRESSURE_SYSTOLIC_MAX;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_BLOOD_PRESSURE_SYSTOLIC_MIN() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_SYSTOLIC_PRESSURE_MIN");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthFields.FIELD_SYSTOLIC_PRESSURE_MIN;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_BLOOD_PRESSURE_SYSTOLIC_MIN");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthFields.FIELD_BLOOD_PRESSURE_SYSTOLIC_MIN;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_BODY_POSITION() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_BODY_POSTURE");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthFields.FIELD_BODY_POSTURE;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_BODY_POSITION");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthFields.FIELD_BODY_POSITION;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_BODY_TEMPERATURE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_TEMPERATURE");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthFields.FIELD_TEMPERATURE;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_BODY_TEMPERATURE");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthFields.FIELD_BODY_TEMPERATURE;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_BODY_TEMPERATURE_MEASUREMENT_LOCATION() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_MEASURE_BODY_PART_OF_TEMPERATURE");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthFields.FIELD_MEASURE_BODY_PART_OF_TEMPERATURE;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_BODY_TEMPERATURE_MEASUREMENT_LOCATION");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthFields.FIELD_BODY_TEMPERATURE_MEASUREMENT_LOCATION;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_CERVICAL_DILATION() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_DILATION_STATUS");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthFields.FIELD_DILATION_STATUS;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_CERVICAL_DILATION");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthFields.FIELD_CERVICAL_DILATION;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_CERVICAL_FIRMNESS() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_FIRMNESS_LEVEL");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthFields.FIELD_FIRMNESS_LEVEL;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_CERVICAL_FIRMNESS");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthFields.FIELD_CERVICAL_FIRMNESS;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_CERVICAL_MUCUS_AMOUNT() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_AMOUNT");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthFields.FIELD_AMOUNT;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_CERVICAL_MUCUS_AMOUNT");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthFields.FIELD_CERVICAL_MUCUS_AMOUNT;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_CERVICAL_MUCUS_TEXTURE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_TEXTURE");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthFields.FIELD_TEXTURE;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_CERVICAL_MUCUS_TEXTURE");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthFields.FIELD_CERVICAL_MUCUS_TEXTURE;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_CERVICAL_POSITION() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_POSITION");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthFields.FIELD_POSITION;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_CERVICAL_POSITION");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthFields.FIELD_CERVICAL_POSITION;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_MENSTRUAL_FLOW() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_VOLUME");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthFields.FIELD_VOLUME;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_MENSTRUAL_FLOW");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthFields.FIELD_MENSTRUAL_FLOW;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_OVULATION_TEST_RESULT() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_DETECTION_RESULT");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthFields.FIELD_DETECTION_RESULT;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_OVULATION_TEST_RESULT");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthFields.FIELD_OVULATION_TEST_RESULT;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_OXYGEN_SATURATION() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_SATURATION");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthFields.FIELD_SATURATION;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_OXYGEN_SATURATION");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthFields.FIELD_OXYGEN_SATURATION;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_OXYGEN_SATURATION_AVERAGE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_SATURATION_AVG");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthFields.FIELD_SATURATION_AVG;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_OXYGEN_SATURATION_AVERAGE");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthFields.FIELD_OXYGEN_SATURATION_AVERAGE;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_OXYGEN_SATURATION_MAX() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_SATURATION_MAX");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthFields.FIELD_SATURATION_MAX;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_OXYGEN_SATURATION_MAX");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthFields.FIELD_OXYGEN_SATURATION_MAX;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_OXYGEN_SATURATION_MEASUREMENT_METHOD() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_SPO2_MEASUREMENT_APPROACH");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthFields.FIELD_SPO2_MEASUREMENT_APPROACH;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_OXYGEN_SATURATION_MEASUREMENT_METHOD");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthFields.FIELD_OXYGEN_SATURATION_MEASUREMENT_METHOD;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_OXYGEN_SATURATION_MIN() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_SATURATION_MIN");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthFields.FIELD_SATURATION_MIN;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_OXYGEN_SATURATION_MIN");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthFields.FIELD_OXYGEN_SATURATION_MIN;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_OXYGEN_SATURATION_SYSTEM() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_SPO2_MEASUREMENT_MECHANISM");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthFields.FIELD_SPO2_MEASUREMENT_MECHANISM;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_OXYGEN_SATURATION_SYSTEM");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthFields.FIELD_OXYGEN_SATURATION_SYSTEM;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_OXYGEN_THERAPY_ADMINISTRATION_MODE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_OXYGEN_THERAPY");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthFields.FIELD_OXYGEN_THERAPY;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_OXYGEN_THERAPY_ADMINISTRATION_MODE");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthFields.FIELD_OXYGEN_THERAPY_ADMINISTRATION_MODE;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_OXYGEN_SUPPLY_FLOW_RATE");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthFields.FIELD_OXYGEN_SUPPLY_FLOW_RATE;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthFields.FIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE_AVERAGE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_OXYGEN_SUPPLY_FLOW_RATE_AVG");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthFields.FIELD_OXYGEN_SUPPLY_FLOW_RATE_AVG;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE_AVERAGE");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthFields.FIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE_AVERAGE;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE_MAX() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_OXYGEN_SUPPLY_FLOW_RATE_MAX");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthFields.FIELD_OXYGEN_SUPPLY_FLOW_RATE_MAX;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE_MAX");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthFields.FIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE_MAX;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE_MIN() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_OXYGEN_SUPPLY_FLOW_RATE_MIN");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthFields.FIELD_OXYGEN_SUPPLY_FLOW_RATE_MIN;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE_MIN");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthFields.FIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE_MIN;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_TEMPORAL_RELATION_TO_MEAL() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_CORRELATION_WITH_MEALTIME");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthFields.FIELD_CORRELATION_WITH_MEALTIME;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_TEMPORAL_RELATION_TO_MEAL");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthFields.FIELD_TEMPORAL_RELATION_TO_MEAL;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_TEMPORAL_RELATION_TO_SLEEP() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.HealthFields.FIELD_CORRELATION_WITH_SLEEP_STATE");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.HealthFields.FIELD_CORRELATION_WITH_SLEEP_STATE;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.HealthFields.FIELD_TEMPORAL_RELATION_TO_SLEEP");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.HealthFields.FIELD_TEMPORAL_RELATION_TO_SLEEP;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.HealthFields dynamicCast(java.lang.Object param0) {
        return ((org.xms.g.fitness.data.HealthFields) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.data.HealthFields;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.data.HealthFields;
        }
    }
}