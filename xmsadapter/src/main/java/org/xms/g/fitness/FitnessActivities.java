package org.xms.g.fitness;

public class FitnessActivities extends org.xms.g.utils.XObject {
    
    public FitnessActivities(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public static java.lang.String getAEROBICS() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.AEROBICS");
            return com.huawei.hms.hihealth.HiHealthActivities.AEROBICS;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.AEROBICS");
            return com.google.android.gms.fitness.FitnessActivities.AEROBICS;
        }
    }
    
    public static java.lang.String getARCHERY() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.ARCHERY");
            return com.huawei.hms.hihealth.HiHealthActivities.ARCHERY;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.ARCHERY");
            return com.google.android.gms.fitness.FitnessActivities.ARCHERY;
        }
    }
    
    public static java.lang.String getBADMINTON() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.BADMINTON");
            return com.huawei.hms.hihealth.HiHealthActivities.BADMINTON;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.BADMINTON");
            return com.google.android.gms.fitness.FitnessActivities.BADMINTON;
        }
    }
    
    public static java.lang.String getBASEBALL() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.BASEBALL");
            return com.huawei.hms.hihealth.HiHealthActivities.BASEBALL;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.BASEBALL");
            return com.google.android.gms.fitness.FitnessActivities.BASEBALL;
        }
    }
    
    public static java.lang.String getBASKETBALL() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.BASKETBALL");
            return com.huawei.hms.hihealth.HiHealthActivities.BASKETBALL;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.BASKETBALL");
            return com.google.android.gms.fitness.FitnessActivities.BASKETBALL;
        }
    }
    
    public static java.lang.String getBIATHLON() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.BIATHLON");
            return com.huawei.hms.hihealth.HiHealthActivities.BIATHLON;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.BIATHLON");
            return com.google.android.gms.fitness.FitnessActivities.BIATHLON;
        }
    }
    
    public static java.lang.String getBIKING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.CYCLING");
            return com.huawei.hms.hihealth.HiHealthActivities.CYCLING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.BIKING");
            return com.google.android.gms.fitness.FitnessActivities.BIKING;
        }
    }
    
    public static java.lang.String getBIKING_HAND() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.CYCLING");
            return com.huawei.hms.hihealth.HiHealthActivities.CYCLING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.BIKING_HAND");
            return com.google.android.gms.fitness.FitnessActivities.BIKING_HAND;
        }
    }
    
    public static java.lang.String getBIKING_MOUNTAIN() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.CYCLING");
            return com.huawei.hms.hihealth.HiHealthActivities.CYCLING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.BIKING_MOUNTAIN");
            return com.google.android.gms.fitness.FitnessActivities.BIKING_MOUNTAIN;
        }
    }
    
    public static java.lang.String getBIKING_ROAD() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.CYCLING");
            return com.huawei.hms.hihealth.HiHealthActivities.CYCLING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.BIKING_ROAD");
            return com.google.android.gms.fitness.FitnessActivities.BIKING_ROAD;
        }
    }
    
    public static java.lang.String getBIKING_SPINNING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.CYCLING");
            return com.huawei.hms.hihealth.HiHealthActivities.CYCLING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.BIKING_SPINNING");
            return com.google.android.gms.fitness.FitnessActivities.BIKING_SPINNING;
        }
    }
    
    public static java.lang.String getBIKING_STATIONARY() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.CYCLING");
            return com.huawei.hms.hihealth.HiHealthActivities.CYCLING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.BIKING_STATIONARY");
            return com.google.android.gms.fitness.FitnessActivities.BIKING_STATIONARY;
        }
    }
    
    public static java.lang.String getBIKING_UTILITY() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.CYCLING");
            return com.huawei.hms.hihealth.HiHealthActivities.CYCLING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.BIKING_UTILITY");
            return com.google.android.gms.fitness.FitnessActivities.BIKING_UTILITY;
        }
    }
    
    public static java.lang.String getBOXING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.BOXING");
            return com.huawei.hms.hihealth.HiHealthActivities.BOXING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.BOXING");
            return com.google.android.gms.fitness.FitnessActivities.BOXING;
        }
    }
    
    public static java.lang.String getCALISTHENICS() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.CALISTHENICS");
            return com.huawei.hms.hihealth.HiHealthActivities.CALISTHENICS;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.CALISTHENICS");
            return com.google.android.gms.fitness.FitnessActivities.CALISTHENICS;
        }
    }
    
    public static java.lang.String getCIRCUIT_TRAINING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.CIRCUIT_TRAINING");
            return com.huawei.hms.hihealth.HiHealthActivities.CIRCUIT_TRAINING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.CIRCUIT_TRAINING");
            return com.google.android.gms.fitness.FitnessActivities.CIRCUIT_TRAINING;
        }
    }
    
    public static java.lang.String getCRICKET() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.CRICKET");
            return com.huawei.hms.hihealth.HiHealthActivities.CRICKET;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.CRICKET");
            return com.google.android.gms.fitness.FitnessActivities.CRICKET;
        }
    }
    
    public static java.lang.String getCROSSFIT() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.CROSSFIT");
            return com.huawei.hms.hihealth.HiHealthActivities.CROSSFIT;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.CROSSFIT");
            return com.google.android.gms.fitness.FitnessActivities.CROSSFIT;
        }
    }
    
    public static java.lang.String getCURLING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.CURLING");
            return com.huawei.hms.hihealth.HiHealthActivities.CURLING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.CURLING");
            return com.google.android.gms.fitness.FitnessActivities.CURLING;
        }
    }
    
    public static java.lang.String getDANCING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.DANCING");
            return com.huawei.hms.hihealth.HiHealthActivities.DANCING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.DANCING");
            return com.google.android.gms.fitness.FitnessActivities.DANCING;
        }
    }
    
    public static java.lang.String getDIVING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.DIVING");
            return com.huawei.hms.hihealth.HiHealthActivities.DIVING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.DIVING");
            return com.google.android.gms.fitness.FitnessActivities.DIVING;
        }
    }
    
    public static java.lang.String getELEVATOR() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.ELEVATOR");
            return com.huawei.hms.hihealth.HiHealthActivities.ELEVATOR;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.ELEVATOR");
            return com.google.android.gms.fitness.FitnessActivities.ELEVATOR;
        }
    }
    
    public static java.lang.String getELLIPTICAL() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.ELLIPTICAL");
            return com.huawei.hms.hihealth.HiHealthActivities.ELLIPTICAL;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.ELLIPTICAL");
            return com.google.android.gms.fitness.FitnessActivities.ELLIPTICAL;
        }
    }
    
    public static java.lang.String getERGOMETER() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.ERGOMETER");
            return com.huawei.hms.hihealth.HiHealthActivities.ERGOMETER;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.ERGOMETER");
            return com.google.android.gms.fitness.FitnessActivities.ERGOMETER;
        }
    }
    
    public static java.lang.String getESCALATOR() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.ESCALATOR");
            return com.huawei.hms.hihealth.HiHealthActivities.ESCALATOR;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.ESCALATOR");
            return com.google.android.gms.fitness.FitnessActivities.ESCALATOR;
        }
    }
    
    public static java.lang.String getEXTRA_STATUS() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.EXTRA_ACTION_STATUS");
            return com.huawei.hms.hihealth.HiHealthActivities.EXTRA_ACTION_STATUS;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.EXTRA_STATUS");
            return com.google.android.gms.fitness.FitnessActivities.EXTRA_STATUS;
        }
    }
    
    public static java.lang.String getFENCING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.FENCING");
            return com.huawei.hms.hihealth.HiHealthActivities.FENCING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.FENCING");
            return com.google.android.gms.fitness.FitnessActivities.FENCING;
        }
    }
    
    public static java.lang.String getFOOTBALL_AMERICAN() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.FOOTBALL_AMERICAN");
            return com.huawei.hms.hihealth.HiHealthActivities.FOOTBALL_AMERICAN;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.FOOTBALL_AMERICAN");
            return com.google.android.gms.fitness.FitnessActivities.FOOTBALL_AMERICAN;
        }
    }
    
    public static java.lang.String getFOOTBALL_AUSTRALIAN() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.FOOTBALL_AUSTRALIAN");
            return com.huawei.hms.hihealth.HiHealthActivities.FOOTBALL_AUSTRALIAN;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.FOOTBALL_AUSTRALIAN");
            return com.google.android.gms.fitness.FitnessActivities.FOOTBALL_AUSTRALIAN;
        }
    }
    
    public static java.lang.String getFOOTBALL_SOCCER() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.FOOTBALL_SOCCER");
            return com.huawei.hms.hihealth.HiHealthActivities.FOOTBALL_SOCCER;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.FOOTBALL_SOCCER");
            return com.google.android.gms.fitness.FitnessActivities.FOOTBALL_SOCCER;
        }
    }
    
    public static java.lang.String getFRISBEE_DISC() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.FLYING_DISC");
            return com.huawei.hms.hihealth.HiHealthActivities.FLYING_DISC;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.FRISBEE_DISC");
            return com.google.android.gms.fitness.FitnessActivities.FRISBEE_DISC;
        }
    }
    
    public static java.lang.String getGARDENING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.GARDENING");
            return com.huawei.hms.hihealth.HiHealthActivities.GARDENING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.GARDENING");
            return com.google.android.gms.fitness.FitnessActivities.GARDENING;
        }
    }
    
    public static java.lang.String getGOLF() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.GOLF");
            return com.huawei.hms.hihealth.HiHealthActivities.GOLF;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.GOLF");
            return com.google.android.gms.fitness.FitnessActivities.GOLF;
        }
    }
    
    public static java.lang.String getGYMNASTICS() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.GYMNASTICS");
            return com.huawei.hms.hihealth.HiHealthActivities.GYMNASTICS;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.GYMNASTICS");
            return com.google.android.gms.fitness.FitnessActivities.GYMNASTICS;
        }
    }
    
    public static java.lang.String getHANDBALL() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.HANDBALL");
            return com.huawei.hms.hihealth.HiHealthActivities.HANDBALL;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.HANDBALL");
            return com.google.android.gms.fitness.FitnessActivities.HANDBALL;
        }
    }
    
    public static java.lang.String getHIGH_INTENSITY_INTERVAL_TRAINING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.HIIT");
            return com.huawei.hms.hihealth.HiHealthActivities.HIIT;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.HIGH_INTENSITY_INTERVAL_TRAINING");
            return com.google.android.gms.fitness.FitnessActivities.HIGH_INTENSITY_INTERVAL_TRAINING;
        }
    }
    
    public static java.lang.String getHIKING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.HIKING");
            return com.huawei.hms.hihealth.HiHealthActivities.HIKING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.HIKING");
            return com.google.android.gms.fitness.FitnessActivities.HIKING;
        }
    }
    
    public static java.lang.String getHOCKEY() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.HOCKEY");
            return com.huawei.hms.hihealth.HiHealthActivities.HOCKEY;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.HOCKEY");
            return com.google.android.gms.fitness.FitnessActivities.HOCKEY;
        }
    }
    
    public static java.lang.String getHORSEBACK_RIDING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.HORSE_RIDING");
            return com.huawei.hms.hihealth.HiHealthActivities.HORSE_RIDING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.HORSEBACK_RIDING");
            return com.google.android.gms.fitness.FitnessActivities.HORSEBACK_RIDING;
        }
    }
    
    public static java.lang.String getHOUSEWORK() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.HOUSEWORK");
            return com.huawei.hms.hihealth.HiHealthActivities.HOUSEWORK;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.HOUSEWORK");
            return com.google.android.gms.fitness.FitnessActivities.HOUSEWORK;
        }
    }
    
    public static java.lang.String getICE_SKATING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.ICE_SKATING");
            return com.huawei.hms.hihealth.HiHealthActivities.ICE_SKATING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.ICE_SKATING");
            return com.google.android.gms.fitness.FitnessActivities.ICE_SKATING;
        }
    }
    
    public static java.lang.String getINTERVAL_TRAINING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.INTERVAL_TRAINING");
            return com.huawei.hms.hihealth.HiHealthActivities.INTERVAL_TRAINING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.INTERVAL_TRAINING");
            return com.google.android.gms.fitness.FitnessActivities.INTERVAL_TRAINING;
        }
    }
    
    public static java.lang.String getIN_VEHICLE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.IN_VEHICLE");
            return com.huawei.hms.hihealth.HiHealthActivities.IN_VEHICLE;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.IN_VEHICLE");
            return com.google.android.gms.fitness.FitnessActivities.IN_VEHICLE;
        }
    }
    
    public static java.lang.String getJUMP_ROPE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.JUMPING_ROPE");
            return com.huawei.hms.hihealth.HiHealthActivities.JUMPING_ROPE;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.JUMP_ROPE");
            return com.google.android.gms.fitness.FitnessActivities.JUMP_ROPE;
        }
    }
    
    public static java.lang.String getKAYAKING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.KAYAKING");
            return com.huawei.hms.hihealth.HiHealthActivities.KAYAKING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.KAYAKING");
            return com.google.android.gms.fitness.FitnessActivities.KAYAKING;
        }
    }
    
    public static java.lang.String getKETTLEBELL_TRAINING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.KETTLEBELL_TRAINING");
            return com.huawei.hms.hihealth.HiHealthActivities.KETTLEBELL_TRAINING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.KETTLEBELL_TRAINING");
            return com.google.android.gms.fitness.FitnessActivities.KETTLEBELL_TRAINING;
        }
    }
    
    public static java.lang.String getKICKBOXING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.KICKBOXING");
            return com.huawei.hms.hihealth.HiHealthActivities.KICKBOXING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.KICKBOXING");
            return com.google.android.gms.fitness.FitnessActivities.KICKBOXING;
        }
    }
    
    public static java.lang.String getKICK_SCOOTER() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.SCOOTER_RIDING");
            return com.huawei.hms.hihealth.HiHealthActivities.SCOOTER_RIDING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.KICK_SCOOTER");
            return com.google.android.gms.fitness.FitnessActivities.KICK_SCOOTER;
        }
    }
    
    public static java.lang.String getKITESURFING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.KITESURFING");
            return com.huawei.hms.hihealth.HiHealthActivities.KITESURFING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.KITESURFING");
            return com.google.android.gms.fitness.FitnessActivities.KITESURFING;
        }
    }
    
    public static java.lang.String getMARTIAL_ARTS() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.MARTIAL_ARTS");
            return com.huawei.hms.hihealth.HiHealthActivities.MARTIAL_ARTS;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.MARTIAL_ARTS");
            return com.google.android.gms.fitness.FitnessActivities.MARTIAL_ARTS;
        }
    }
    
    public static java.lang.String getMEDITATION() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.MEDITATION");
            return com.huawei.hms.hihealth.HiHealthActivities.MEDITATION;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.MEDITATION");
            return com.google.android.gms.fitness.FitnessActivities.MEDITATION;
        }
    }
    
    public static java.lang.String getMIME_TYPE_PREFIX() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.MIME_TYPE_PREFIX");
            return com.huawei.hms.hihealth.HiHealthActivities.MIME_TYPE_PREFIX;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.MIME_TYPE_PREFIX");
            return com.google.android.gms.fitness.FitnessActivities.MIME_TYPE_PREFIX;
        }
    }
    
    public static java.lang.String getMIXED_MARTIAL_ARTS() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.MIXED_MARTIAL_ARTS");
            return com.huawei.hms.hihealth.HiHealthActivities.MIXED_MARTIAL_ARTS;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.MIXED_MARTIAL_ARTS");
            return com.google.android.gms.fitness.FitnessActivities.MIXED_MARTIAL_ARTS;
        }
    }
    
    public static java.lang.String getON_FOOT() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.ON_FOOT");
            return com.huawei.hms.hihealth.HiHealthActivities.ON_FOOT;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.ON_FOOT");
            return com.google.android.gms.fitness.FitnessActivities.ON_FOOT;
        }
    }
    
    public static java.lang.String getOTHER() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.OTHER");
            return com.huawei.hms.hihealth.HiHealthActivities.OTHER;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.OTHER");
            return com.google.android.gms.fitness.FitnessActivities.OTHER;
        }
    }
    
    public static java.lang.String getP90X() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.P90X");
            return com.huawei.hms.hihealth.HiHealthActivities.P90X;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.P90X");
            return com.google.android.gms.fitness.FitnessActivities.P90X;
        }
    }
    
    public static java.lang.String getPARAGLIDING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.PARAGLIDING");
            return com.huawei.hms.hihealth.HiHealthActivities.PARAGLIDING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.PARAGLIDING");
            return com.google.android.gms.fitness.FitnessActivities.PARAGLIDING;
        }
    }
    
    public static java.lang.String getPILATES() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.PILATES");
            return com.huawei.hms.hihealth.HiHealthActivities.PILATES;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.PILATES");
            return com.google.android.gms.fitness.FitnessActivities.PILATES;
        }
    }
    
    public static java.lang.String getPOLO() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.POLO");
            return com.huawei.hms.hihealth.HiHealthActivities.POLO;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.POLO");
            return com.google.android.gms.fitness.FitnessActivities.POLO;
        }
    }
    
    public static java.lang.String getRACQUETBALL() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.RACQUETBALL");
            return com.huawei.hms.hihealth.HiHealthActivities.RACQUETBALL;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.RACQUETBALL");
            return com.google.android.gms.fitness.FitnessActivities.RACQUETBALL;
        }
    }
    
    public static java.lang.String getROCK_CLIMBING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.ROCK_CLIMBING");
            return com.huawei.hms.hihealth.HiHealthActivities.ROCK_CLIMBING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.ROCK_CLIMBING");
            return com.google.android.gms.fitness.FitnessActivities.ROCK_CLIMBING;
        }
    }
    
    public static java.lang.String getROWING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.ROWING");
            return com.huawei.hms.hihealth.HiHealthActivities.ROWING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.ROWING");
            return com.google.android.gms.fitness.FitnessActivities.ROWING;
        }
    }
    
    public static java.lang.String getROWING_MACHINE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.ROWING_MACHINE");
            return com.huawei.hms.hihealth.HiHealthActivities.ROWING_MACHINE;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.ROWING_MACHINE");
            return com.google.android.gms.fitness.FitnessActivities.ROWING_MACHINE;
        }
    }
    
    public static java.lang.String getRUGBY() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.RUGBY");
            return com.huawei.hms.hihealth.HiHealthActivities.RUGBY;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.RUGBY");
            return com.google.android.gms.fitness.FitnessActivities.RUGBY;
        }
    }
    
    public static java.lang.String getRUNNING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.RUNNING");
            return com.huawei.hms.hihealth.HiHealthActivities.RUNNING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.RUNNING");
            return com.google.android.gms.fitness.FitnessActivities.RUNNING;
        }
    }
    
    public static java.lang.String getRUNNING_JOGGING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.RUNNING");
            return com.huawei.hms.hihealth.HiHealthActivities.RUNNING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.RUNNING_JOGGING");
            return com.google.android.gms.fitness.FitnessActivities.RUNNING_JOGGING;
        }
    }
    
    public static java.lang.String getRUNNING_SAND() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.RUNNING");
            return com.huawei.hms.hihealth.HiHealthActivities.RUNNING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.RUNNING_SAND");
            return com.google.android.gms.fitness.FitnessActivities.RUNNING_SAND;
        }
    }
    
    public static java.lang.String getRUNNING_TREADMILL() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.RUNNING_MACHINE");
            return com.huawei.hms.hihealth.HiHealthActivities.RUNNING_MACHINE;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.RUNNING_TREADMILL");
            return com.google.android.gms.fitness.FitnessActivities.RUNNING_TREADMILL;
        }
    }
    
    public static java.lang.String getSAILING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.SAILING");
            return com.huawei.hms.hihealth.HiHealthActivities.SAILING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.SAILING");
            return com.google.android.gms.fitness.FitnessActivities.SAILING;
        }
    }
    
    public static java.lang.String getSCUBA_DIVING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.SCUBA_DIVING");
            return com.huawei.hms.hihealth.HiHealthActivities.SCUBA_DIVING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.SCUBA_DIVING");
            return com.google.android.gms.fitness.FitnessActivities.SCUBA_DIVING;
        }
    }
    
    public static java.lang.String getSKATEBOARDING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.SKATEBOARDING");
            return com.huawei.hms.hihealth.HiHealthActivities.SKATEBOARDING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.SKATEBOARDING");
            return com.google.android.gms.fitness.FitnessActivities.SKATEBOARDING;
        }
    }
    
    public static java.lang.String getSKATING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.SKATING");
            return com.huawei.hms.hihealth.HiHealthActivities.SKATING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.SKATING");
            return com.google.android.gms.fitness.FitnessActivities.SKATING;
        }
    }
    
    public static java.lang.String getSKATING_CROSS() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.SKATING");
            return com.huawei.hms.hihealth.HiHealthActivities.SKATING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.SKATING_CROSS");
            return com.google.android.gms.fitness.FitnessActivities.SKATING_CROSS;
        }
    }
    
    public static java.lang.String getSKATING_INDOOR() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.SKATING");
            return com.huawei.hms.hihealth.HiHealthActivities.SKATING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.SKATING_INDOOR");
            return com.google.android.gms.fitness.FitnessActivities.SKATING_INDOOR;
        }
    }
    
    public static java.lang.String getSKATING_INLINE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.SKATING");
            return com.huawei.hms.hihealth.HiHealthActivities.SKATING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.SKATING_INLINE");
            return com.google.android.gms.fitness.FitnessActivities.SKATING_INLINE;
        }
    }
    
    public static java.lang.String getSKIING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.SKIING");
            return com.huawei.hms.hihealth.HiHealthActivities.SKIING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.SKIING");
            return com.google.android.gms.fitness.FitnessActivities.SKIING;
        }
    }
    
    public static java.lang.String getSKIING_BACK_COUNTRY() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.SKIING");
            return com.huawei.hms.hihealth.HiHealthActivities.SKIING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.SKIING_BACK_COUNTRY");
            return com.google.android.gms.fitness.FitnessActivities.SKIING_BACK_COUNTRY;
        }
    }
    
    public static java.lang.String getSKIING_CROSS_COUNTRY() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.SKIING");
            return com.huawei.hms.hihealth.HiHealthActivities.SKIING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.SKIING_CROSS_COUNTRY");
            return com.google.android.gms.fitness.FitnessActivities.SKIING_CROSS_COUNTRY;
        }
    }
    
    public static java.lang.String getSKIING_DOWNHILL() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.SKIING");
            return com.huawei.hms.hihealth.HiHealthActivities.SKIING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.SKIING_DOWNHILL");
            return com.google.android.gms.fitness.FitnessActivities.SKIING_DOWNHILL;
        }
    }
    
    public static java.lang.String getSKIING_KITE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.SKIING");
            return com.huawei.hms.hihealth.HiHealthActivities.SKIING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.SKIING_KITE");
            return com.google.android.gms.fitness.FitnessActivities.SKIING_KITE;
        }
    }
    
    public static java.lang.String getSKIING_ROLLER() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.SKIING");
            return com.huawei.hms.hihealth.HiHealthActivities.SKIING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.SKIING_ROLLER");
            return com.google.android.gms.fitness.FitnessActivities.SKIING_ROLLER;
        }
    }
    
    public static java.lang.String getSLEDDING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.SLEDDING");
            return com.huawei.hms.hihealth.HiHealthActivities.SLEDDING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.SLEDDING");
            return com.google.android.gms.fitness.FitnessActivities.SLEDDING;
        }
    }
    
    public static java.lang.String getSLEEP() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.SLEEP");
            return com.huawei.hms.hihealth.HiHealthActivities.SLEEP;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.SLEEP");
            return com.google.android.gms.fitness.FitnessActivities.SLEEP;
        }
    }
    
    public static java.lang.String getSLEEP_AWAKE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.SLEEP_AWAKE");
            return com.huawei.hms.hihealth.HiHealthActivities.SLEEP_AWAKE;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.SLEEP_AWAKE");
            return com.google.android.gms.fitness.FitnessActivities.SLEEP_AWAKE;
        }
    }
    
    public static java.lang.String getSLEEP_DEEP() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.SLEEP_DEEP");
            return com.huawei.hms.hihealth.HiHealthActivities.SLEEP_DEEP;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.SLEEP_DEEP");
            return com.google.android.gms.fitness.FitnessActivities.SLEEP_DEEP;
        }
    }
    
    public static java.lang.String getSLEEP_LIGHT() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.SLEEP_LIGHT");
            return com.huawei.hms.hihealth.HiHealthActivities.SLEEP_LIGHT;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.SLEEP_LIGHT");
            return com.google.android.gms.fitness.FitnessActivities.SLEEP_LIGHT;
        }
    }
    
    public static java.lang.String getSLEEP_REM() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.SLEEP_REM");
            return com.huawei.hms.hihealth.HiHealthActivities.SLEEP_REM;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.SLEEP_REM");
            return com.google.android.gms.fitness.FitnessActivities.SLEEP_REM;
        }
    }
    
    public static java.lang.String getSNOWBOARDING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.SNOWBOARDING");
            return com.huawei.hms.hihealth.HiHealthActivities.SNOWBOARDING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.SNOWBOARDING");
            return com.google.android.gms.fitness.FitnessActivities.SNOWBOARDING;
        }
    }
    
    public static java.lang.String getSNOWMOBILE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.SNOWMOBILE");
            return com.huawei.hms.hihealth.HiHealthActivities.SNOWMOBILE;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.SNOWMOBILE");
            return com.google.android.gms.fitness.FitnessActivities.SNOWMOBILE;
        }
    }
    
    public static java.lang.String getSNOWSHOEING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.SNOWSHOEING");
            return com.huawei.hms.hihealth.HiHealthActivities.SNOWSHOEING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.SNOWSHOEING");
            return com.google.android.gms.fitness.FitnessActivities.SNOWSHOEING;
        }
    }
    
    public static java.lang.String getSOFTBALL() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.SOFTBALL");
            return com.huawei.hms.hihealth.HiHealthActivities.SOFTBALL;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.SOFTBALL");
            return com.google.android.gms.fitness.FitnessActivities.SOFTBALL;
        }
    }
    
    public static java.lang.String getSQUASH() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.SQUASH");
            return com.huawei.hms.hihealth.HiHealthActivities.SQUASH;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.SQUASH");
            return com.google.android.gms.fitness.FitnessActivities.SQUASH;
        }
    }
    
    public static java.lang.String getSTAIR_CLIMBING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.STAIR_CLIMBING");
            return com.huawei.hms.hihealth.HiHealthActivities.STAIR_CLIMBING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.STAIR_CLIMBING");
            return com.google.android.gms.fitness.FitnessActivities.STAIR_CLIMBING;
        }
    }
    
    public static java.lang.String getSTAIR_CLIMBING_MACHINE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.STAIR_CLIMBING_MACHINE");
            return com.huawei.hms.hihealth.HiHealthActivities.STAIR_CLIMBING_MACHINE;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.STAIR_CLIMBING_MACHINE");
            return com.google.android.gms.fitness.FitnessActivities.STAIR_CLIMBING_MACHINE;
        }
    }
    
    public static java.lang.String getSTANDUP_PADDLEBOARDING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.STANDUP_PADDLEBOARDING");
            return com.huawei.hms.hihealth.HiHealthActivities.STANDUP_PADDLEBOARDING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.STANDUP_PADDLEBOARDING");
            return com.google.android.gms.fitness.FitnessActivities.STANDUP_PADDLEBOARDING;
        }
    }
    
    public static java.lang.String getSTATUS_ACTIVE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.STATUS_ACTION_START");
            return com.huawei.hms.hihealth.HiHealthActivities.STATUS_ACTION_START;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.STATUS_ACTIVE");
            return com.google.android.gms.fitness.FitnessActivities.STATUS_ACTIVE;
        }
    }
    
    public static java.lang.String getSTATUS_COMPLETED() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.STATUS_ACTION_END");
            return com.huawei.hms.hihealth.HiHealthActivities.STATUS_ACTION_END;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.STATUS_COMPLETED");
            return com.google.android.gms.fitness.FitnessActivities.STATUS_COMPLETED;
        }
    }
    
    public static java.lang.String getSTILL() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.STILL");
            return com.huawei.hms.hihealth.HiHealthActivities.STILL;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.STILL");
            return com.google.android.gms.fitness.FitnessActivities.STILL;
        }
    }
    
    public static java.lang.String getSTRENGTH_TRAINING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.STRENGTH_TRAINING");
            return com.huawei.hms.hihealth.HiHealthActivities.STRENGTH_TRAINING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.STRENGTH_TRAINING");
            return com.google.android.gms.fitness.FitnessActivities.STRENGTH_TRAINING;
        }
    }
    
    public static java.lang.String getSURFING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.SURFING");
            return com.huawei.hms.hihealth.HiHealthActivities.SURFING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.SURFING");
            return com.google.android.gms.fitness.FitnessActivities.SURFING;
        }
    }
    
    public static java.lang.String getSWIMMING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.SWIMMING");
            return com.huawei.hms.hihealth.HiHealthActivities.SWIMMING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.SWIMMING");
            return com.google.android.gms.fitness.FitnessActivities.SWIMMING;
        }
    }
    
    public static java.lang.String getSWIMMING_OPEN_WATER() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.SWIMMING_OPEN_WATER");
            return com.huawei.hms.hihealth.HiHealthActivities.SWIMMING_OPEN_WATER;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.SWIMMING_OPEN_WATER");
            return com.google.android.gms.fitness.FitnessActivities.SWIMMING_OPEN_WATER;
        }
    }
    
    public static java.lang.String getSWIMMING_POOL() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.SWIMMING_POOL");
            return com.huawei.hms.hihealth.HiHealthActivities.SWIMMING_POOL;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.SWIMMING_POOL");
            return com.google.android.gms.fitness.FitnessActivities.SWIMMING_POOL;
        }
    }
    
    public static java.lang.String getTABLE_TENNIS() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.TABLE_TENNIS");
            return com.huawei.hms.hihealth.HiHealthActivities.TABLE_TENNIS;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.TABLE_TENNIS");
            return com.google.android.gms.fitness.FitnessActivities.TABLE_TENNIS;
        }
    }
    
    public static java.lang.String getTEAM_SPORTS() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.TEAM_SPORTS");
            return com.huawei.hms.hihealth.HiHealthActivities.TEAM_SPORTS;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.TEAM_SPORTS");
            return com.google.android.gms.fitness.FitnessActivities.TEAM_SPORTS;
        }
    }
    
    public static java.lang.String getTENNIS() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.TENNIS");
            return com.huawei.hms.hihealth.HiHealthActivities.TENNIS;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.TENNIS");
            return com.google.android.gms.fitness.FitnessActivities.TENNIS;
        }
    }
    
    public static java.lang.String getTILTING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.TILTING");
            return com.huawei.hms.hihealth.HiHealthActivities.TILTING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.TILTING");
            return com.google.android.gms.fitness.FitnessActivities.TILTING;
        }
    }
    
    public static java.lang.String getTREADMILL() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.RUNNING_MACHINE");
            return com.huawei.hms.hihealth.HiHealthActivities.RUNNING_MACHINE;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.TREADMILL");
            return com.google.android.gms.fitness.FitnessActivities.TREADMILL;
        }
    }
    
    public static java.lang.String getUNKNOWN() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.UNKNOWN");
            return com.huawei.hms.hihealth.HiHealthActivities.UNKNOWN;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.UNKNOWN");
            return com.google.android.gms.fitness.FitnessActivities.UNKNOWN;
        }
    }
    
    public static java.lang.String getVOLLEYBALL() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.VOLLEYBALL");
            return com.huawei.hms.hihealth.HiHealthActivities.VOLLEYBALL;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.VOLLEYBALL");
            return com.google.android.gms.fitness.FitnessActivities.VOLLEYBALL;
        }
    }
    
    public static java.lang.String getVOLLEYBALL_BEACH() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.VOLLEYBALL");
            return com.huawei.hms.hihealth.HiHealthActivities.VOLLEYBALL;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.VOLLEYBALL_BEACH");
            return com.google.android.gms.fitness.FitnessActivities.VOLLEYBALL_BEACH;
        }
    }
    
    public static java.lang.String getVOLLEYBALL_INDOOR() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.VOLLEYBALL");
            return com.huawei.hms.hihealth.HiHealthActivities.VOLLEYBALL;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.VOLLEYBALL_INDOOR");
            return com.google.android.gms.fitness.FitnessActivities.VOLLEYBALL_INDOOR;
        }
    }
    
    public static java.lang.String getWAKEBOARDING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.WAKEBOARDING");
            return com.huawei.hms.hihealth.HiHealthActivities.WAKEBOARDING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.WAKEBOARDING");
            return com.google.android.gms.fitness.FitnessActivities.WAKEBOARDING;
        }
    }
    
    public static java.lang.String getWALKING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.WALKING");
            return com.huawei.hms.hihealth.HiHealthActivities.WALKING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.WALKING");
            return com.google.android.gms.fitness.FitnessActivities.WALKING;
        }
    }
    
    public static java.lang.String getWALKING_FITNESS() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.WALKING");
            return com.huawei.hms.hihealth.HiHealthActivities.WALKING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.WALKING_FITNESS");
            return com.google.android.gms.fitness.FitnessActivities.WALKING_FITNESS;
        }
    }
    
    public static java.lang.String getWALKING_NORDIC() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.WALKING");
            return com.huawei.hms.hihealth.HiHealthActivities.WALKING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.WALKING_NORDIC");
            return com.google.android.gms.fitness.FitnessActivities.WALKING_NORDIC;
        }
    }
    
    public static java.lang.String getWALKING_STROLLER() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.WALKING");
            return com.huawei.hms.hihealth.HiHealthActivities.WALKING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.WALKING_STROLLER");
            return com.google.android.gms.fitness.FitnessActivities.WALKING_STROLLER;
        }
    }
    
    public static java.lang.String getWALKING_TREADMILL() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.WALKING");
            return com.huawei.hms.hihealth.HiHealthActivities.WALKING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.WALKING_TREADMILL");
            return com.google.android.gms.fitness.FitnessActivities.WALKING_TREADMILL;
        }
    }
    
    public static java.lang.String getWATER_POLO() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.WATER_POLO");
            return com.huawei.hms.hihealth.HiHealthActivities.WATER_POLO;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.WATER_POLO");
            return com.google.android.gms.fitness.FitnessActivities.WATER_POLO;
        }
    }
    
    public static java.lang.String getWEIGHTLIFTING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.WEIGHTLIFTING");
            return com.huawei.hms.hihealth.HiHealthActivities.WEIGHTLIFTING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.WEIGHTLIFTING");
            return com.google.android.gms.fitness.FitnessActivities.WEIGHTLIFTING;
        }
    }
    
    public static java.lang.String getWHEELCHAIR() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.WHEELCHAIR");
            return com.huawei.hms.hihealth.HiHealthActivities.WHEELCHAIR;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.WHEELCHAIR");
            return com.google.android.gms.fitness.FitnessActivities.WHEELCHAIR;
        }
    }
    
    public static java.lang.String getWINDSURFING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.WINDSURFING");
            return com.huawei.hms.hihealth.HiHealthActivities.WINDSURFING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.WINDSURFING");
            return com.google.android.gms.fitness.FitnessActivities.WINDSURFING;
        }
    }
    
    public static java.lang.String getYOGA() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.YOGA");
            return com.huawei.hms.hihealth.HiHealthActivities.YOGA;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.YOGA");
            return com.google.android.gms.fitness.FitnessActivities.YOGA;
        }
    }
    
    public static java.lang.String getZUMBA() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.ZUMBA");
            return com.huawei.hms.hihealth.HiHealthActivities.ZUMBA;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.ZUMBA");
            return com.google.android.gms.fitness.FitnessActivities.ZUMBA;
        }
    }
    
    public static java.lang.String getMimeType(java.lang.String param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthActivities.getMimeType(param0)");
            return com.huawei.hms.hihealth.HiHealthActivities.getMimeType(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessActivities.getMimeType(param0)");
            return com.google.android.gms.fitness.FitnessActivities.getMimeType(param0);
        }
    }
    
    public static org.xms.g.fitness.FitnessActivities dynamicCast(java.lang.Object param0) {
        return ((org.xms.g.fitness.FitnessActivities) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.HiHealthActivities;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.FitnessActivities;
        }
    }
}