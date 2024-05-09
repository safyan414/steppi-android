package org.xms.g.fitness.result;

public class DailyTotalResult extends org.xms.g.utils.XObject implements android.os.Parcelable, org.xms.g.common.api.Result {
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {
        
        public org.xms.g.fitness.result.DailyTotalResult createFromParcel(android.os.Parcel param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                com.huawei.hms.hihealth.result.DailyStatisticsResult hReturn = com.huawei.hms.hihealth.result.DailyStatisticsResult.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.result.DailyTotalResult(new org.xms.g.utils.XBox(null, hReturn));
            } else {
                com.google.android.gms.fitness.result.DailyTotalResult gReturn = com.google.android.gms.fitness.result.DailyTotalResult.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.result.DailyTotalResult(new org.xms.g.utils.XBox(gReturn, null));
            }
        }
        
        public org.xms.g.fitness.result.DailyTotalResult[] newArray(int param0) {
            return new org.xms.g.fitness.result.DailyTotalResult[param0];
        }
    };
    
    public DailyTotalResult(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public boolean equals(java.lang.Object param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.result.DailyStatisticsResult) this.getHInstance()).equals(param0)");
            return ((com.huawei.hms.hihealth.result.DailyStatisticsResult) this.getHInstance()).equals(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.result.DailyTotalResult) this.getGInstance()).equals(param0)");
            return ((com.google.android.gms.fitness.result.DailyTotalResult) this.getGInstance()).equals(param0);
        }
    }
    
    public org.xms.g.common.api.Status getStatus() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.result.DailyStatisticsResult) this.getHInstance()).getStatus()");
            com.huawei.hms.support.api.client.Status hReturn = ((com.huawei.hms.hihealth.result.DailyStatisticsResult) this.getHInstance()).getStatus();
            return ((hReturn) == null ? null : (new org.xms.g.common.api.Status(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.result.DailyTotalResult) this.getGInstance()).getStatus()");
            com.google.android.gms.common.api.Status gReturn = ((com.google.android.gms.fitness.result.DailyTotalResult) this.getGInstance()).getStatus();
            return ((gReturn) == null ? null : (new org.xms.g.common.api.Status(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.fitness.data.DataSet getTotal() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.result.DailyStatisticsResult) this.getHInstance()).getSummation()");
            com.huawei.hms.hihealth.data.SampleSet hReturn = ((com.huawei.hms.hihealth.result.DailyStatisticsResult) this.getHInstance()).getSummation();
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataSet(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.result.DailyTotalResult) this.getGInstance()).getTotal()");
            com.google.android.gms.fitness.data.DataSet gReturn = ((com.google.android.gms.fitness.result.DailyTotalResult) this.getGInstance()).getTotal();
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataSet(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public int hashCode() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.result.DailyStatisticsResult) this.getHInstance()).hashCode()");
            return ((com.huawei.hms.hihealth.result.DailyStatisticsResult) this.getHInstance()).hashCode();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.result.DailyTotalResult) this.getGInstance()).hashCode()");
            return ((com.google.android.gms.fitness.result.DailyTotalResult) this.getGInstance()).hashCode();
        }
    }
    
    public java.lang.String toString() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.result.DailyStatisticsResult) this.getHInstance()).toString()");
            return ((com.huawei.hms.hihealth.result.DailyStatisticsResult) this.getHInstance()).toString();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.result.DailyTotalResult) this.getGInstance()).toString()");
            return ((com.google.android.gms.fitness.result.DailyTotalResult) this.getGInstance()).toString();
        }
    }
    
    public int describeContents() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public void writeToParcel(android.os.Parcel param0, int param1) {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.result.DailyTotalResult dynamicCast(java.lang.Object param0) {
        if (param0 instanceof org.xms.g.fitness.result.DailyTotalResult) {
            return ((org.xms.g.fitness.result.DailyTotalResult) param0);
        }
        if (param0 instanceof org.xms.g.utils.XGettable) {
            com.google.android.gms.fitness.result.DailyTotalResult gReturn = ((com.google.android.gms.fitness.result.DailyTotalResult) ((org.xms.g.utils.XGettable) param0).getGInstance());
            com.huawei.hms.hihealth.result.DailyStatisticsResult hReturn = ((com.huawei.hms.hihealth.result.DailyStatisticsResult) ((org.xms.g.utils.XGettable) param0).getHInstance());
            return new org.xms.g.fitness.result.DailyTotalResult(new org.xms.g.utils.XBox(gReturn, hReturn));
        }
        return ((org.xms.g.fitness.result.DailyTotalResult) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.result.DailyStatisticsResult;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.result.DailyTotalResult;
        }
    }
}