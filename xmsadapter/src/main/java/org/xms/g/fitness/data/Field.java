package org.xms.g.fitness.data;

public final class Field extends org.xms.g.utils.XObject implements android.os.Parcelable {
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {
        
        public org.xms.g.fitness.data.Field createFromParcel(android.os.Parcel param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                com.huawei.hms.hihealth.data.Field hReturn = com.huawei.hms.hihealth.data.Field.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn));
            } else {
                com.google.android.gms.fitness.data.Field gReturn = com.google.android.gms.fitness.data.Field.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null));
            }
        }
        
        public org.xms.g.fitness.data.Field[] newArray(int param0) {
            return new org.xms.g.fitness.data.Field[param0];
        }
    };
    
    public Field(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public static int getFORMAT_FLOAT() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FORMAT_FLOAT");
            return com.huawei.hms.hihealth.data.Field.FORMAT_FLOAT;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FORMAT_FLOAT");
            return com.google.android.gms.fitness.data.Field.FORMAT_FLOAT;
        }
    }
    
    public static int getFORMAT_INT32() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FORMAT_INT32");
            return com.huawei.hms.hihealth.data.Field.FORMAT_INT32;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FORMAT_INT32");
            return com.google.android.gms.fitness.data.Field.FORMAT_INT32;
        }
    }
    
    public static int getFORMAT_MAP() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FORMAT_MAP");
            return com.huawei.hms.hihealth.data.Field.FORMAT_MAP;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FORMAT_MAP");
            return com.google.android.gms.fitness.data.Field.FORMAT_MAP;
        }
    }
    
    public static int getFORMAT_STRING() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FORMAT_STRING");
            return com.huawei.hms.hihealth.data.Field.FORMAT_STRING;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FORMAT_STRING");
            return com.google.android.gms.fitness.data.Field.FORMAT_STRING;
        }
    }
    
    public static int getMEAL_TYPE_BREAKFAST() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.MEAL_BREAKFAST");
            return com.huawei.hms.hihealth.data.Field.MEAL_BREAKFAST;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.MEAL_TYPE_BREAKFAST");
            return com.google.android.gms.fitness.data.Field.MEAL_TYPE_BREAKFAST;
        }
    }
    
    public static int getMEAL_TYPE_DINNER() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.MEAL_DINNER");
            return com.huawei.hms.hihealth.data.Field.MEAL_DINNER;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.MEAL_TYPE_DINNER");
            return com.google.android.gms.fitness.data.Field.MEAL_TYPE_DINNER;
        }
    }
    
    public static int getMEAL_TYPE_LUNCH() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.MEAL_LUNCH");
            return com.huawei.hms.hihealth.data.Field.MEAL_LUNCH;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.MEAL_TYPE_LUNCH");
            return com.google.android.gms.fitness.data.Field.MEAL_TYPE_LUNCH;
        }
    }
    
    public static int getMEAL_TYPE_SNACK() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.MEAL_SNACK");
            return com.huawei.hms.hihealth.data.Field.MEAL_SNACK;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.MEAL_TYPE_SNACK");
            return com.google.android.gms.fitness.data.Field.MEAL_TYPE_SNACK;
        }
    }
    
    public static int getMEAL_TYPE_UNKNOWN() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.MEAL_UNKNOWN");
            return com.huawei.hms.hihealth.data.Field.MEAL_UNKNOWN;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.MEAL_TYPE_UNKNOWN");
            return com.google.android.gms.fitness.data.Field.MEAL_TYPE_UNKNOWN;
        }
    }
    
    public static java.lang.String getNUTRIENT_CALCIUM() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.NUTRIENTS_FACTS_CALCIUM");
            return com.huawei.hms.hihealth.data.Field.NUTRIENTS_FACTS_CALCIUM;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.NUTRIENT_CALCIUM");
            return com.google.android.gms.fitness.data.Field.NUTRIENT_CALCIUM;
        }
    }
    
    public static java.lang.String getNUTRIENT_CALORIES() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.NUTRIENTS_FACTS_CALORIES");
            return com.huawei.hms.hihealth.data.Field.NUTRIENTS_FACTS_CALORIES;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.NUTRIENT_CALORIES");
            return com.google.android.gms.fitness.data.Field.NUTRIENT_CALORIES;
        }
    }
    
    public static java.lang.String getNUTRIENT_CHOLESTEROL() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.NUTRIENTS_FACTS_CHOLESTEROL");
            return com.huawei.hms.hihealth.data.Field.NUTRIENTS_FACTS_CHOLESTEROL;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.NUTRIENT_CHOLESTEROL");
            return com.google.android.gms.fitness.data.Field.NUTRIENT_CHOLESTEROL;
        }
    }
    
    public static java.lang.String getNUTRIENT_DIETARY_FIBER() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.NUTRIENTS_FACTS_DIETARY_FIBER");
            return com.huawei.hms.hihealth.data.Field.NUTRIENTS_FACTS_DIETARY_FIBER;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.NUTRIENT_DIETARY_FIBER");
            return com.google.android.gms.fitness.data.Field.NUTRIENT_DIETARY_FIBER;
        }
    }
    
    public static java.lang.String getNUTRIENT_IRON() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.NUTRIENTS_FACTS_IRON");
            return com.huawei.hms.hihealth.data.Field.NUTRIENTS_FACTS_IRON;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.NUTRIENT_IRON");
            return com.google.android.gms.fitness.data.Field.NUTRIENT_IRON;
        }
    }
    
    public static java.lang.String getNUTRIENT_MONOUNSATURATED_FAT() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.NUTRIENTS_FACTS_MONOUNSATURATED_FAT");
            return com.huawei.hms.hihealth.data.Field.NUTRIENTS_FACTS_MONOUNSATURATED_FAT;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.NUTRIENT_MONOUNSATURATED_FAT");
            return com.google.android.gms.fitness.data.Field.NUTRIENT_MONOUNSATURATED_FAT;
        }
    }
    
    public static java.lang.String getNUTRIENT_POLYUNSATURATED_FAT() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.NUTRIENTS_FACTS_POLYUNSATURATED_FAT");
            return com.huawei.hms.hihealth.data.Field.NUTRIENTS_FACTS_POLYUNSATURATED_FAT;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.NUTRIENT_POLYUNSATURATED_FAT");
            return com.google.android.gms.fitness.data.Field.NUTRIENT_POLYUNSATURATED_FAT;
        }
    }
    
    public static java.lang.String getNUTRIENT_POTASSIUM() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.NUTRIENTS_FACTS_POTASSIUM");
            return com.huawei.hms.hihealth.data.Field.NUTRIENTS_FACTS_POTASSIUM;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.NUTRIENT_POTASSIUM");
            return com.google.android.gms.fitness.data.Field.NUTRIENT_POTASSIUM;
        }
    }
    
    public static java.lang.String getNUTRIENT_PROTEIN() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.NUTRIENTS_FACTS_PROTEIN");
            return com.huawei.hms.hihealth.data.Field.NUTRIENTS_FACTS_PROTEIN;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.NUTRIENT_PROTEIN");
            return com.google.android.gms.fitness.data.Field.NUTRIENT_PROTEIN;
        }
    }
    
    public static java.lang.String getNUTRIENT_SATURATED_FAT() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.NUTRIENTS_FACTS_SATURATED_FAT");
            return com.huawei.hms.hihealth.data.Field.NUTRIENTS_FACTS_SATURATED_FAT;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.NUTRIENT_SATURATED_FAT");
            return com.google.android.gms.fitness.data.Field.NUTRIENT_SATURATED_FAT;
        }
    }
    
    public static java.lang.String getNUTRIENT_SODIUM() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.NUTRIENTS_FACTS_SODIUM");
            return com.huawei.hms.hihealth.data.Field.NUTRIENTS_FACTS_SODIUM;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.NUTRIENT_SODIUM");
            return com.google.android.gms.fitness.data.Field.NUTRIENT_SODIUM;
        }
    }
    
    public static java.lang.String getNUTRIENT_SUGAR() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.NUTRIENTS_FACTS_SUGAR");
            return com.huawei.hms.hihealth.data.Field.NUTRIENTS_FACTS_SUGAR;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.NUTRIENT_SUGAR");
            return com.google.android.gms.fitness.data.Field.NUTRIENT_SUGAR;
        }
    }
    
    public static java.lang.String getNUTRIENT_TOTAL_CARBS() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.NUTRIENTS_FACTS_TOTAL_CARBS");
            return com.huawei.hms.hihealth.data.Field.NUTRIENTS_FACTS_TOTAL_CARBS;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.NUTRIENT_TOTAL_CARBS");
            return com.google.android.gms.fitness.data.Field.NUTRIENT_TOTAL_CARBS;
        }
    }
    
    public static java.lang.String getNUTRIENT_TOTAL_FAT() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.NUTRIENTS_FACTS_TOTAL_FAT");
            return com.huawei.hms.hihealth.data.Field.NUTRIENTS_FACTS_TOTAL_FAT;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.NUTRIENT_TOTAL_FAT");
            return com.google.android.gms.fitness.data.Field.NUTRIENT_TOTAL_FAT;
        }
    }
    
    public static java.lang.String getNUTRIENT_TRANS_FAT() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.NUTRIENTS_FACTS_TRANS_FAT");
            return com.huawei.hms.hihealth.data.Field.NUTRIENTS_FACTS_TRANS_FAT;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.NUTRIENT_TRANS_FAT");
            return com.google.android.gms.fitness.data.Field.NUTRIENT_TRANS_FAT;
        }
    }
    
    public static java.lang.String getNUTRIENT_UNSATURATED_FAT() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.NUTRIENTS_FACTS_UNSATURATED_FAT");
            return com.huawei.hms.hihealth.data.Field.NUTRIENTS_FACTS_UNSATURATED_FAT;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.NUTRIENT_UNSATURATED_FAT");
            return com.google.android.gms.fitness.data.Field.NUTRIENT_UNSATURATED_FAT;
        }
    }
    
    public static java.lang.String getNUTRIENT_VITAMIN_A() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.NUTRIENTS_FACTS_VITAMIN_A");
            return com.huawei.hms.hihealth.data.Field.NUTRIENTS_FACTS_VITAMIN_A;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.NUTRIENT_VITAMIN_A");
            return com.google.android.gms.fitness.data.Field.NUTRIENT_VITAMIN_A;
        }
    }
    
    public static java.lang.String getNUTRIENT_VITAMIN_C() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.NUTRIENTS_FACTS_VITAMIN_C");
            return com.huawei.hms.hihealth.data.Field.NUTRIENTS_FACTS_VITAMIN_C;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.NUTRIENT_VITAMIN_C");
            return com.google.android.gms.fitness.data.Field.NUTRIENT_VITAMIN_C;
        }
    }
    
    public static int getRESISTANCE_TYPE_BARBELL() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.TYPE_OF_RESISTANCE_BARBELL");
            return com.huawei.hms.hihealth.data.Field.TYPE_OF_RESISTANCE_BARBELL;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.RESISTANCE_TYPE_BARBELL");
            return com.google.android.gms.fitness.data.Field.RESISTANCE_TYPE_BARBELL;
        }
    }
    
    public static int getRESISTANCE_TYPE_BODY() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.TYPE_OF_RESISTANCE_BODY");
            return com.huawei.hms.hihealth.data.Field.TYPE_OF_RESISTANCE_BODY;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.RESISTANCE_TYPE_BODY");
            return com.google.android.gms.fitness.data.Field.RESISTANCE_TYPE_BODY;
        }
    }
    
    public static int getRESISTANCE_TYPE_CABLE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.TYPE_OF_RESISTANCE_CABLE");
            return com.huawei.hms.hihealth.data.Field.TYPE_OF_RESISTANCE_CABLE;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.RESISTANCE_TYPE_CABLE");
            return com.google.android.gms.fitness.data.Field.RESISTANCE_TYPE_CABLE;
        }
    }
    
    public static int getRESISTANCE_TYPE_DUMBBELL() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.TYPE_OF_RESISTANCE_DUMBBELL");
            return com.huawei.hms.hihealth.data.Field.TYPE_OF_RESISTANCE_DUMBBELL;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.RESISTANCE_TYPE_DUMBBELL");
            return com.google.android.gms.fitness.data.Field.RESISTANCE_TYPE_DUMBBELL;
        }
    }
    
    public static int getRESISTANCE_TYPE_KETTLEBELL() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.TYPE_OF_RESISTANCE_KETTLEBELL");
            return com.huawei.hms.hihealth.data.Field.TYPE_OF_RESISTANCE_KETTLEBELL;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.RESISTANCE_TYPE_KETTLEBELL");
            return com.google.android.gms.fitness.data.Field.RESISTANCE_TYPE_KETTLEBELL;
        }
    }
    
    public static int getRESISTANCE_TYPE_MACHINE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.TYPE_OF_RESISTANCE_MACHINE");
            return com.huawei.hms.hihealth.data.Field.TYPE_OF_RESISTANCE_MACHINE;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.RESISTANCE_TYPE_MACHINE");
            return com.google.android.gms.fitness.data.Field.RESISTANCE_TYPE_MACHINE;
        }
    }
    
    public static int getRESISTANCE_TYPE_UNKNOWN() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.TYPE_OF_RESISTANCE_UNKNOWN");
            return com.huawei.hms.hihealth.data.Field.TYPE_OF_RESISTANCE_UNKNOWN;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.RESISTANCE_TYPE_UNKNOWN");
            return com.google.android.gms.fitness.data.Field.RESISTANCE_TYPE_UNKNOWN;
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_ACCURACY() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FIELD_PRECISION");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.Field.FIELD_PRECISION;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FIELD_ACCURACY");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.Field.FIELD_ACCURACY;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_ACTIVITY() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FIELD_TYPE_OF_ACTIVITY");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.Field.FIELD_TYPE_OF_ACTIVITY;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FIELD_ACTIVITY");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.Field.FIELD_ACTIVITY;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_ACTIVITY_CONFIDENCE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FIELD_POSSIBILITY_OF_ACTIVITY");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.Field.FIELD_POSSIBILITY_OF_ACTIVITY;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FIELD_ACTIVITY_CONFIDENCE");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.Field.FIELD_ACTIVITY_CONFIDENCE;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_ALTITUDE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FIELD_ALTITUDE");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.Field.FIELD_ALTITUDE;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FIELD_ALTITUDE");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.Field.FIELD_ALTITUDE;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_AVERAGE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FIELD_AVG");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.Field.FIELD_AVG;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FIELD_AVERAGE");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.Field.FIELD_AVERAGE;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_BPM() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FIELD_BPM");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.Field.FIELD_BPM;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FIELD_BPM");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.Field.FIELD_BPM;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_CALORIES() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FIELD_CALORIES");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.Field.FIELD_CALORIES;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FIELD_CALORIES");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.Field.FIELD_CALORIES;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_CIRCUMFERENCE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FIELD_CIRCUMFERENCE");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.Field.FIELD_CIRCUMFERENCE;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FIELD_CIRCUMFERENCE");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.Field.FIELD_CIRCUMFERENCE;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_CONFIDENCE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FIELD_POSSIBILITY");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.Field.FIELD_POSSIBILITY;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FIELD_CONFIDENCE");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.Field.FIELD_CONFIDENCE;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_DISTANCE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FIELD_DISTANCE");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.Field.FIELD_DISTANCE;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FIELD_DISTANCE");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.Field.FIELD_DISTANCE;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_DURATION() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FIELD_SPAN");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.Field.FIELD_SPAN;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FIELD_DURATION");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.Field.FIELD_DURATION;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_EXERCISE() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_FOOD_ITEM() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FIELD_FOOD");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.Field.FIELD_FOOD;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FIELD_FOOD_ITEM");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.Field.FIELD_FOOD_ITEM;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_HEIGHT() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FIELD_HEIGHT");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.Field.FIELD_HEIGHT;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FIELD_HEIGHT");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.Field.FIELD_HEIGHT;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_HIGH_LATITUDE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FIELD_MAX_LATITUDE");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.Field.FIELD_MAX_LATITUDE;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FIELD_HIGH_LATITUDE");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.Field.FIELD_HIGH_LATITUDE;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_HIGH_LONGITUDE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FIELD_MAX_LONGITUDE");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.Field.FIELD_MAX_LONGITUDE;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FIELD_HIGH_LONGITUDE");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.Field.FIELD_HIGH_LONGITUDE;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_INTENSITY() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FIELD_INTENSITY");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.Field.FIELD_INTENSITY;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FIELD_INTENSITY");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.Field.FIELD_INTENSITY;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_LATITUDE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FIELD_LATITUDE");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.Field.FIELD_LATITUDE;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FIELD_LATITUDE");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.Field.FIELD_LATITUDE;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_LONGITUDE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FIELD_LONGITUDE");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.Field.FIELD_LONGITUDE;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FIELD_LONGITUDE");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.Field.FIELD_LONGITUDE;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_LOW_LATITUDE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FIELD_MIN_LATITUDE");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.Field.FIELD_MIN_LATITUDE;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FIELD_LOW_LATITUDE");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.Field.FIELD_LOW_LATITUDE;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_LOW_LONGITUDE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FIELD_MIN_LONGITUDE");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.Field.FIELD_MIN_LONGITUDE;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FIELD_LOW_LONGITUDE");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.Field.FIELD_LOW_LONGITUDE;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_MAX() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FIELD_MAX");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.Field.FIELD_MAX;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FIELD_MAX");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.Field.FIELD_MAX;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_MEAL_TYPE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FIELD_MEAL");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.Field.FIELD_MEAL;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FIELD_MEAL_TYPE");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.Field.FIELD_MEAL_TYPE;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_MIN() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FIELD_MIN");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.Field.FIELD_MIN;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FIELD_MIN");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.Field.FIELD_MIN;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_NUM_SEGMENTS() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FIELD_FRAGMENTS");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.Field.FIELD_FRAGMENTS;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FIELD_NUM_SEGMENTS");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.Field.FIELD_NUM_SEGMENTS;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_NUTRIENTS() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FIELD_NUTRIENTS_FACTS");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.Field.FIELD_NUTRIENTS_FACTS;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FIELD_NUTRIENTS");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.Field.FIELD_NUTRIENTS;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_OCCURRENCES() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FIELD_APPEARANCE");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.Field.FIELD_APPEARANCE;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FIELD_OCCURRENCES");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.Field.FIELD_OCCURRENCES;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_PERCENTAGE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FIELD_BODY_FAT_RATE");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.Field.FIELD_BODY_FAT_RATE;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FIELD_PERCENTAGE");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.Field.FIELD_PERCENTAGE;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_REPETITIONS() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_RESISTANCE() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_RESISTANCE_TYPE() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_REVOLUTIONS() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FIELD_ROTATION");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.Field.FIELD_ROTATION;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FIELD_REVOLUTIONS");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.Field.FIELD_REVOLUTIONS;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_RPM() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FIELD_RPM");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.Field.FIELD_RPM;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FIELD_RPM");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.Field.FIELD_RPM;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_SPEED() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FIELD_SPEED");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.Field.FIELD_SPEED;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FIELD_SPEED");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.Field.FIELD_SPEED;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_STEPS() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FIELD_STEPS");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.Field.FIELD_STEPS;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FIELD_STEPS");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.Field.FIELD_STEPS;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_STEP_LENGTH() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FIELD_STEP_LENGTH");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.Field.FIELD_STEP_LENGTH;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FIELD_STEP_LENGTH");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.Field.FIELD_STEP_LENGTH;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_VOLUME() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FIELD_HYDRATE");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.Field.FIELD_HYDRATE;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FIELD_VOLUME");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.Field.FIELD_VOLUME;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_WATTS() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FIELD_POWER");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.Field.FIELD_POWER;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FIELD_WATTS");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.Field.FIELD_WATTS;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.Field getFIELD_WEIGHT() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Field.FIELD_BODY_WEIGHT");
            com.huawei.hms.hihealth.data.Field hReturn = null;
            hReturn = com.huawei.hms.hihealth.data.Field.FIELD_BODY_WEIGHT;
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Field.FIELD_WEIGHT");
            com.google.android.gms.fitness.data.Field gReturn = null;
            gReturn = com.google.android.gms.fitness.data.Field.FIELD_WEIGHT;
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Field(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public final boolean equals(java.lang.Object param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.Field) this.getHInstance()).equals(param0)");
            return ((com.huawei.hms.hihealth.data.Field) this.getHInstance()).equals(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Field) this.getGInstance()).equals(param0)");
            return ((com.google.android.gms.fitness.data.Field) this.getGInstance()).equals(param0);
        }
    }
    
    public final int getFormat() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.Field) this.getHInstance()).getFormat()");
            return ((com.huawei.hms.hihealth.data.Field) this.getHInstance()).getFormat();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Field) this.getGInstance()).getFormat()");
            return ((com.google.android.gms.fitness.data.Field) this.getGInstance()).getFormat();
        }
    }
    
    public final java.lang.String getName() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.Field) this.getHInstance()).getName()");
            return ((com.huawei.hms.hihealth.data.Field) this.getHInstance()).getName();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Field) this.getGInstance()).getName()");
            return ((com.google.android.gms.fitness.data.Field) this.getGInstance()).getName();
        }
    }
    
    public final int hashCode() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.Field) this.getHInstance()).hashCode()");
            return ((com.huawei.hms.hihealth.data.Field) this.getHInstance()).hashCode();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Field) this.getGInstance()).hashCode()");
            return ((com.google.android.gms.fitness.data.Field) this.getGInstance()).hashCode();
        }
    }
    
    public final java.lang.Boolean isOptional() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.Field) this.getHInstance()).isOptional()");
            return ((com.huawei.hms.hihealth.data.Field) this.getHInstance()).isOptional();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Field) this.getGInstance()).isOptional()");
            return ((com.google.android.gms.fitness.data.Field) this.getGInstance()).isOptional();
        }
    }
    
    public final java.lang.String toString() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.Field) this.getHInstance()).toString()");
            return ((com.huawei.hms.hihealth.data.Field) this.getHInstance()).toString();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Field) this.getGInstance()).toString()");
            return ((com.google.android.gms.fitness.data.Field) this.getGInstance()).toString();
        }
    }
    
    public final void writeToParcel(android.os.Parcel param0, int param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.Field) this.getHInstance()).writeToParcel(param0, param1)");
            ((com.huawei.hms.hihealth.data.Field) this.getHInstance()).writeToParcel(param0, param1);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Field) this.getGInstance()).writeToParcel(param0, param1)");
            ((com.google.android.gms.fitness.data.Field) this.getGInstance()).writeToParcel(param0, param1);
        }
    }
    
    public int describeContents() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.data.Field dynamicCast(java.lang.Object param0) {
        return ((org.xms.g.fitness.data.Field) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.data.Field;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.data.Field;
        }
    }
}