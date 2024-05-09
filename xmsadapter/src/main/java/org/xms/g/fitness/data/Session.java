package org.xms.g.fitness.data;

public class Session extends org.xms.g.utils.XObject implements android.os.Parcelable {
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {
        
        public org.xms.g.fitness.data.Session createFromParcel(android.os.Parcel param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                com.huawei.hms.hihealth.data.ActivityRecord hReturn = com.huawei.hms.hihealth.data.ActivityRecord.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.data.Session(new org.xms.g.utils.XBox(null, hReturn));
            } else {
                com.google.android.gms.fitness.data.Session gReturn = com.google.android.gms.fitness.data.Session.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.data.Session(new org.xms.g.utils.XBox(gReturn, null));
            }
        }
        
        public org.xms.g.fitness.data.Session[] newArray(int param0) {
            return new org.xms.g.fitness.data.Session[param0];
        }
    };
    
    public Session(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public static java.lang.String getEXTRA_SESSION() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.ActivityRecord.EXTRA_ACTIVITY_RECORD");
            return com.huawei.hms.hihealth.data.ActivityRecord.EXTRA_ACTIVITY_RECORD;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Session.EXTRA_SESSION");
            return com.google.android.gms.fitness.data.Session.EXTRA_SESSION;
        }
    }
    
    public static java.lang.String getMIME_TYPE_PREFIX() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.ActivityRecord.MIME_TYPE_PREFIX");
            return com.huawei.hms.hihealth.data.ActivityRecord.MIME_TYPE_PREFIX;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Session.MIME_TYPE_PREFIX");
            return com.google.android.gms.fitness.data.Session.MIME_TYPE_PREFIX;
        }
    }
    
    public boolean equals(java.lang.Object param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.ActivityRecord) this.getHInstance()).equals(param0)");
            return ((com.huawei.hms.hihealth.data.ActivityRecord) this.getHInstance()).equals(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Session) this.getGInstance()).equals(param0)");
            return ((com.google.android.gms.fitness.data.Session) this.getGInstance()).equals(param0);
        }
    }
    
    public static org.xms.g.fitness.data.Session extract(android.content.Intent param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.ActivityRecord.extract(param0)");
            com.huawei.hms.hihealth.data.ActivityRecord hReturn = com.huawei.hms.hihealth.data.ActivityRecord.extract(param0);
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Session(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Session.extract(param0)");
            com.google.android.gms.fitness.data.Session gReturn = com.google.android.gms.fitness.data.Session.extract(param0);
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Session(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public long getActiveTime(java.util.concurrent.TimeUnit param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.ActivityRecord) this.getHInstance()).getDurationTime(param0)");
            return ((com.huawei.hms.hihealth.data.ActivityRecord) this.getHInstance()).getDurationTime(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Session) this.getGInstance()).getActiveTime(param0)");
            return ((com.google.android.gms.fitness.data.Session) this.getGInstance()).getActiveTime(param0);
        }
    }
    
    public java.lang.String getActivity() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.ActivityRecord) this.getHInstance()).getActivityType()");
            return ((com.huawei.hms.hihealth.data.ActivityRecord) this.getHInstance()).getActivityType();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Session) this.getGInstance()).getActivity()");
            return ((com.google.android.gms.fitness.data.Session) this.getGInstance()).getActivity();
        }
    }
    
    public java.lang.String getAppPackageName() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.ActivityRecord) this.getHInstance()).getPackageName()");
            return ((com.huawei.hms.hihealth.data.ActivityRecord) this.getHInstance()).getPackageName();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Session) this.getGInstance()).getAppPackageName()");
            return ((com.google.android.gms.fitness.data.Session) this.getGInstance()).getAppPackageName();
        }
    }
    
    public java.lang.String getDescription() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.ActivityRecord) this.getHInstance()).getDesc()");
            return ((com.huawei.hms.hihealth.data.ActivityRecord) this.getHInstance()).getDesc();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Session) this.getGInstance()).getDescription()");
            return ((com.google.android.gms.fitness.data.Session) this.getGInstance()).getDescription();
        }
    }
    
    public long getEndTime(java.util.concurrent.TimeUnit param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.ActivityRecord) this.getHInstance()).getEndTime(param0)");
            return ((com.huawei.hms.hihealth.data.ActivityRecord) this.getHInstance()).getEndTime(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Session) this.getGInstance()).getEndTime(param0)");
            return ((com.google.android.gms.fitness.data.Session) this.getGInstance()).getEndTime(param0);
        }
    }
    
    public java.lang.String getIdentifier() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.ActivityRecord) this.getHInstance()).getId()");
            return ((com.huawei.hms.hihealth.data.ActivityRecord) this.getHInstance()).getId();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Session) this.getGInstance()).getIdentifier()");
            return ((com.google.android.gms.fitness.data.Session) this.getGInstance()).getIdentifier();
        }
    }
    
    public static java.lang.String getMimeType(java.lang.String param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.ActivityRecord.getMimeType(param0)");
            return com.huawei.hms.hihealth.data.ActivityRecord.getMimeType(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.Session.getMimeType(param0)");
            return com.google.android.gms.fitness.data.Session.getMimeType(param0);
        }
    }
    
    public java.lang.String getName() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.ActivityRecord) this.getHInstance()).getName()");
            return ((com.huawei.hms.hihealth.data.ActivityRecord) this.getHInstance()).getName();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Session) this.getGInstance()).getName()");
            return ((com.google.android.gms.fitness.data.Session) this.getGInstance()).getName();
        }
    }
    
    public long getStartTime(java.util.concurrent.TimeUnit param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.ActivityRecord) this.getHInstance()).getStartTime(param0)");
            return ((com.huawei.hms.hihealth.data.ActivityRecord) this.getHInstance()).getStartTime(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Session) this.getGInstance()).getStartTime(param0)");
            return ((com.google.android.gms.fitness.data.Session) this.getGInstance()).getStartTime(param0);
        }
    }
    
    public boolean hasActiveTime() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.ActivityRecord) this.getHInstance()).hasDurationTime()");
            return ((com.huawei.hms.hihealth.data.ActivityRecord) this.getHInstance()).hasDurationTime();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Session) this.getGInstance()).hasActiveTime()");
            return ((com.google.android.gms.fitness.data.Session) this.getGInstance()).hasActiveTime();
        }
    }
    
    public int hashCode() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.ActivityRecord) this.getHInstance()).hashCode()");
            return ((com.huawei.hms.hihealth.data.ActivityRecord) this.getHInstance()).hashCode();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Session) this.getGInstance()).hashCode()");
            return ((com.google.android.gms.fitness.data.Session) this.getGInstance()).hashCode();
        }
    }
    
    public boolean isOngoing() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.ActivityRecord) this.getHInstance()).isKeepGoing()");
            return ((com.huawei.hms.hihealth.data.ActivityRecord) this.getHInstance()).isKeepGoing();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Session) this.getGInstance()).isOngoing()");
            return ((com.google.android.gms.fitness.data.Session) this.getGInstance()).isOngoing();
        }
    }
    
    public java.lang.String toString() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.ActivityRecord) this.getHInstance()).toString()");
            return ((com.huawei.hms.hihealth.data.ActivityRecord) this.getHInstance()).toString();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Session) this.getGInstance()).toString()");
            return ((com.google.android.gms.fitness.data.Session) this.getGInstance()).toString();
        }
    }
    
    public void writeToParcel(android.os.Parcel param0, int param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.ActivityRecord) this.getHInstance()).writeToParcel(param0, param1)");
            ((com.huawei.hms.hihealth.data.ActivityRecord) this.getHInstance()).writeToParcel(param0, param1);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Session) this.getGInstance()).writeToParcel(param0, param1)");
            ((com.google.android.gms.fitness.data.Session) this.getGInstance()).writeToParcel(param0, param1);
        }
    }
    
    public int describeContents() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.data.Session dynamicCast(java.lang.Object param0) {
        return ((org.xms.g.fitness.data.Session) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.data.ActivityRecord;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.data.Session;
        }
    }
    
    public static class Builder extends org.xms.g.utils.XObject {
        
        public Builder(org.xms.g.utils.XBox param0) {
            super(param0);
        }
        
        public Builder() {
            super(((org.xms.g.utils.XBox) null));
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                this.setHInstance(new com.huawei.hms.hihealth.data.ActivityRecord.Builder());
            } else {
                this.setGInstance(new com.google.android.gms.fitness.data.Session.Builder());
            }
        }
        
        public org.xms.g.fitness.data.Session build() {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.ActivityRecord.Builder) this.getHInstance()).build()");
                com.huawei.hms.hihealth.data.ActivityRecord hReturn = ((com.huawei.hms.hihealth.data.ActivityRecord.Builder) this.getHInstance()).build();
                return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Session(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Session.Builder) this.getGInstance()).build()");
                com.google.android.gms.fitness.data.Session gReturn = ((com.google.android.gms.fitness.data.Session.Builder) this.getGInstance()).build();
                return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Session(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.data.Session.Builder setActiveTime(long param0, java.util.concurrent.TimeUnit param1) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.ActivityRecord.Builder) this.getHInstance()).setDurationTime(param0, param1)");
                com.huawei.hms.hihealth.data.ActivityRecord.Builder hReturn = ((com.huawei.hms.hihealth.data.ActivityRecord.Builder) this.getHInstance()).setDurationTime(param0, param1);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Session.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Session.Builder) this.getGInstance()).setActiveTime(param0, param1)");
                com.google.android.gms.fitness.data.Session.Builder gReturn = ((com.google.android.gms.fitness.data.Session.Builder) this.getGInstance()).setActiveTime(param0, param1);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Session.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.data.Session.Builder setActivity(java.lang.String param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.ActivityRecord.Builder) this.getHInstance()).setActivityTypeId(param0)");
                com.huawei.hms.hihealth.data.ActivityRecord.Builder hReturn = ((com.huawei.hms.hihealth.data.ActivityRecord.Builder) this.getHInstance()).setActivityTypeId(param0);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Session.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Session.Builder) this.getGInstance()).setActivity(param0)");
                com.google.android.gms.fitness.data.Session.Builder gReturn = ((com.google.android.gms.fitness.data.Session.Builder) this.getGInstance()).setActivity(param0);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Session.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.data.Session.Builder setDescription(java.lang.String param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.ActivityRecord.Builder) this.getHInstance()).setDesc(param0)");
                com.huawei.hms.hihealth.data.ActivityRecord.Builder hReturn = ((com.huawei.hms.hihealth.data.ActivityRecord.Builder) this.getHInstance()).setDesc(param0);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Session.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Session.Builder) this.getGInstance()).setDescription(param0)");
                com.google.android.gms.fitness.data.Session.Builder gReturn = ((com.google.android.gms.fitness.data.Session.Builder) this.getGInstance()).setDescription(param0);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Session.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.data.Session.Builder setEndTime(long param0, java.util.concurrent.TimeUnit param1) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.ActivityRecord.Builder) this.getHInstance()).setEndTime(param0, param1)");
                com.huawei.hms.hihealth.data.ActivityRecord.Builder hReturn = ((com.huawei.hms.hihealth.data.ActivityRecord.Builder) this.getHInstance()).setEndTime(param0, param1);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Session.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Session.Builder) this.getGInstance()).setEndTime(param0, param1)");
                com.google.android.gms.fitness.data.Session.Builder gReturn = ((com.google.android.gms.fitness.data.Session.Builder) this.getGInstance()).setEndTime(param0, param1);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Session.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.data.Session.Builder setIdentifier(java.lang.String param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.ActivityRecord.Builder) this.getHInstance()).setId(param0)");
                com.huawei.hms.hihealth.data.ActivityRecord.Builder hReturn = ((com.huawei.hms.hihealth.data.ActivityRecord.Builder) this.getHInstance()).setId(param0);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Session.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Session.Builder) this.getGInstance()).setIdentifier(param0)");
                com.google.android.gms.fitness.data.Session.Builder gReturn = ((com.google.android.gms.fitness.data.Session.Builder) this.getGInstance()).setIdentifier(param0);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Session.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.data.Session.Builder setName(java.lang.String param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.ActivityRecord.Builder) this.getHInstance()).setName(param0)");
                com.huawei.hms.hihealth.data.ActivityRecord.Builder hReturn = ((com.huawei.hms.hihealth.data.ActivityRecord.Builder) this.getHInstance()).setName(param0);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Session.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Session.Builder) this.getGInstance()).setName(param0)");
                com.google.android.gms.fitness.data.Session.Builder gReturn = ((com.google.android.gms.fitness.data.Session.Builder) this.getGInstance()).setName(param0);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Session.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.data.Session.Builder setStartTime(long param0, java.util.concurrent.TimeUnit param1) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.ActivityRecord.Builder) this.getHInstance()).setStartTime(param0, param1)");
                com.huawei.hms.hihealth.data.ActivityRecord.Builder hReturn = ((com.huawei.hms.hihealth.data.ActivityRecord.Builder) this.getHInstance()).setStartTime(param0, param1);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.data.Session.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.Session.Builder) this.getGInstance()).setStartTime(param0, param1)");
                com.google.android.gms.fitness.data.Session.Builder gReturn = ((com.google.android.gms.fitness.data.Session.Builder) this.getGInstance()).setStartTime(param0, param1);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.data.Session.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public static org.xms.g.fitness.data.Session.Builder dynamicCast(java.lang.Object param0) {
            return ((org.xms.g.fitness.data.Session.Builder) param0);
        }
        
        public static boolean isInstance(java.lang.Object param0) {
            if (!(param0 instanceof org.xms.g.utils.XGettable)) {
                return false;
            }
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.data.ActivityRecord.Builder;
            } else {
                return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.data.Session.Builder;
            }
        }
    }
}