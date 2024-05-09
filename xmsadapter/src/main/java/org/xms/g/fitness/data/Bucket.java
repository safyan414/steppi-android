package org.xms.g.fitness.data;

public class Bucket extends org.xms.g.utils.XObject implements android.os.Parcelable {
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {
        
        public org.xms.g.fitness.data.Bucket createFromParcel(android.os.Parcel param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                com.huawei.hms.hihealth.data.Group hReturn = com.huawei.hms.hihealth.data.Group.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.data.Bucket(new org.xms.g.utils.XBox(null, hReturn));
            } else {
                com.google.android.gms.fitness.data.Bucket gReturn = com.google.android.gms.fitness.data.Bucket.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.data.Bucket(new org.xms.g.utils.XBox(gReturn, null));
            }
        }
        
        public org.xms.g.fitness.data.Bucket[] newArray(int param0) {
            return new org.xms.g.fitness.data.Bucket[param0];
        }
    };
    
    public Bucket(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public static int getTYPE_ACTIVITY_SEGMENT() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Group.TYPE_ACTIVITY_FRAGMENT");
            return com.huawei.hms.hihealth.data.Group.TYPE_ACTIVITY_FRAGMENT;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Bucket.TYPE_ACTIVITY_SEGMENT");
            return com.google.android.gms.fitness.data.Bucket.TYPE_ACTIVITY_SEGMENT;
        }
    }
    
    public static int getTYPE_ACTIVITY_TYPE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Group.TYPE_ACTIVITY_TYPE");
            return com.huawei.hms.hihealth.data.Group.TYPE_ACTIVITY_TYPE;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Bucket.TYPE_ACTIVITY_TYPE");
            return com.google.android.gms.fitness.data.Bucket.TYPE_ACTIVITY_TYPE;
        }
    }
    
    public static int getTYPE_SESSION() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Group.TYPE_ACTIVITY_RECORD");
            return com.huawei.hms.hihealth.data.Group.TYPE_ACTIVITY_RECORD;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Bucket.TYPE_SESSION");
            return com.google.android.gms.fitness.data.Bucket.TYPE_SESSION;
        }
    }
    
    public static int getTYPE_TIME() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.Group.TYPE_TIME");
            return com.huawei.hms.hihealth.data.Group.TYPE_TIME;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Bucket.TYPE_TIME");
            return com.google.android.gms.fitness.data.Bucket.TYPE_TIME;
        }
    }
    
    public boolean equals(java.lang.Object param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.Group) this.getHInstance()).equals(param0)");
            return ((com.huawei.hms.hihealth.data.Group) this.getHInstance()).equals(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Bucket) this.getGInstance()).equals(param0)");
            return ((com.google.android.gms.fitness.data.Bucket) this.getGInstance()).equals(param0);
        }
    }
    
    public java.lang.String getActivity() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.Group) this.getHInstance()).getActivity()");
            return ((com.huawei.hms.hihealth.data.Group) this.getHInstance()).getActivity();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Bucket) this.getGInstance()).getActivity()");
            return ((com.google.android.gms.fitness.data.Bucket) this.getGInstance()).getActivity();
        }
    }
    
    public int getBucketType() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.Group) this.getHInstance()).getGroupType()");
            return ((com.huawei.hms.hihealth.data.Group) this.getHInstance()).getGroupType();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Bucket) this.getGInstance()).getBucketType()");
            return ((com.google.android.gms.fitness.data.Bucket) this.getGInstance()).getBucketType();
        }
    }
    
    public org.xms.g.fitness.data.DataSet getDataSet(org.xms.g.fitness.data.DataType param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.Group) this.getHInstance()).getSampleSet(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))))");
            com.huawei.hms.hihealth.data.SampleSet hReturn = ((com.huawei.hms.hihealth.data.Group) this.getHInstance()).getSampleSet(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataSet(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Bucket) this.getGInstance()).getDataSet(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))))");
            com.google.android.gms.fitness.data.DataSet gReturn = ((com.google.android.gms.fitness.data.Bucket) this.getGInstance()).getDataSet(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataSet(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public java.util.List<org.xms.g.fitness.data.DataSet> getDataSets() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.Group) this.getHInstance()).getSampleSets()");
            java.util.List hReturn = ((com.huawei.hms.hihealth.data.Group) this.getHInstance()).getSampleSets();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(hReturn, new org.xms.g.utils.Function<com.huawei.hms.hihealth.data.SampleSet, org.xms.g.fitness.data.DataSet>() {
                
                public org.xms.g.fitness.data.DataSet apply(com.huawei.hms.hihealth.data.SampleSet param0) {
                    return new org.xms.g.fitness.data.DataSet(new org.xms.g.utils.XBox(null, param0));
                }
            }));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Bucket) this.getGInstance()).getDataSets()");
            java.util.List gReturn = ((com.google.android.gms.fitness.data.Bucket) this.getGInstance()).getDataSets();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(gReturn, new org.xms.g.utils.Function<com.google.android.gms.fitness.data.DataSet, org.xms.g.fitness.data.DataSet>() {
                
                public org.xms.g.fitness.data.DataSet apply(com.google.android.gms.fitness.data.DataSet param0) {
                    return new org.xms.g.fitness.data.DataSet(new org.xms.g.utils.XBox(param0, null));
                }
            }));
        }
    }
    
    public long getEndTime(java.util.concurrent.TimeUnit param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.Group) this.getHInstance()).getEndTime(param0)");
            return ((com.huawei.hms.hihealth.data.Group) this.getHInstance()).getEndTime(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Bucket) this.getGInstance()).getEndTime(param0)");
            return ((com.google.android.gms.fitness.data.Bucket) this.getGInstance()).getEndTime(param0);
        }
    }
    
    public org.xms.g.fitness.data.Session getSession() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.Group) this.getHInstance()).getActivityRecord()");
            com.huawei.hms.hihealth.data.ActivityRecord hReturn = ((com.huawei.hms.hihealth.data.Group) this.getHInstance()).getActivityRecord();
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Session(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Bucket) this.getGInstance()).getSession()");
            com.google.android.gms.fitness.data.Session gReturn = ((com.google.android.gms.fitness.data.Bucket) this.getGInstance()).getSession();
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Session(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public long getStartTime(java.util.concurrent.TimeUnit param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.Group) this.getHInstance()).getStartTime(param0)");
            return ((com.huawei.hms.hihealth.data.Group) this.getHInstance()).getStartTime(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Bucket) this.getGInstance()).getStartTime(param0)");
            return ((com.google.android.gms.fitness.data.Bucket) this.getGInstance()).getStartTime(param0);
        }
    }
    
    public int hashCode() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.Group) this.getHInstance()).hashCode()");
            return ((com.huawei.hms.hihealth.data.Group) this.getHInstance()).hashCode();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Bucket) this.getGInstance()).hashCode()");
            return ((com.google.android.gms.fitness.data.Bucket) this.getGInstance()).hashCode();
        }
    }
    
    public java.lang.String toString() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.Group) this.getHInstance()).toString()");
            return ((com.huawei.hms.hihealth.data.Group) this.getHInstance()).toString();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Bucket) this.getGInstance()).toString()");
            return ((com.google.android.gms.fitness.data.Bucket) this.getGInstance()).toString();
        }
    }
    
    public void writeToParcel(android.os.Parcel param0, int param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.Group) this.getHInstance()).writeToParcel(param0, param1)");
            ((com.huawei.hms.hihealth.data.Group) this.getHInstance()).writeToParcel(param0, param1);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Bucket) this.getGInstance()).writeToParcel(param0, param1)");
            ((com.google.android.gms.fitness.data.Bucket) this.getGInstance()).writeToParcel(param0, param1);
        }
    }
    
    public int describeContents() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.data.Bucket dynamicCast(java.lang.Object param0) {
        return ((org.xms.g.fitness.data.Bucket) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.data.Group;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.data.Bucket;
        }
    }
}