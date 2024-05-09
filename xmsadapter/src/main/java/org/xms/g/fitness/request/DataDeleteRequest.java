package org.xms.g.fitness.request;

public class DataDeleteRequest extends org.xms.g.utils.XObject implements android.os.Parcelable {
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {
        
        public org.xms.g.fitness.request.DataDeleteRequest createFromParcel(android.os.Parcel param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                com.huawei.hms.hihealth.options.DeleteOptions hReturn = com.huawei.hms.hihealth.options.DeleteOptions.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.request.DataDeleteRequest(new org.xms.g.utils.XBox(null, hReturn));
            } else {
                com.google.android.gms.fitness.request.DataDeleteRequest gReturn = com.google.android.gms.fitness.request.DataDeleteRequest.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.request.DataDeleteRequest(new org.xms.g.utils.XBox(gReturn, null));
            }
        }
        
        public org.xms.g.fitness.request.DataDeleteRequest[] newArray(int param0) {
            return new org.xms.g.fitness.request.DataDeleteRequest[param0];
        }
    };
    
    public DataDeleteRequest(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public boolean deleteAllData() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.DeleteOptions) this.getHInstance()).deleteAllData()");
            return ((com.huawei.hms.hihealth.options.DeleteOptions) this.getHInstance()).deleteAllData();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataDeleteRequest) this.getGInstance()).deleteAllData()");
            return ((com.google.android.gms.fitness.request.DataDeleteRequest) this.getGInstance()).deleteAllData();
        }
    }
    
    public boolean deleteAllSessions() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.DeleteOptions) this.getHInstance()).deleteAllActivityRecords()");
            return ((com.huawei.hms.hihealth.options.DeleteOptions) this.getHInstance()).deleteAllActivityRecords();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataDeleteRequest) this.getGInstance()).deleteAllSessions()");
            return ((com.google.android.gms.fitness.request.DataDeleteRequest) this.getGInstance()).deleteAllSessions();
        }
    }
    
    public boolean equals(java.lang.Object param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.DeleteOptions) this.getHInstance()).equals(param0)");
            return ((com.huawei.hms.hihealth.options.DeleteOptions) this.getHInstance()).equals(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataDeleteRequest) this.getGInstance()).equals(param0)");
            return ((com.google.android.gms.fitness.request.DataDeleteRequest) this.getGInstance()).equals(param0);
        }
    }
    
    public java.util.List<org.xms.g.fitness.data.DataSource> getDataSources() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.DeleteOptions) this.getHInstance()).getDataCollectors()");
            java.util.List hReturn = ((com.huawei.hms.hihealth.options.DeleteOptions) this.getHInstance()).getDataCollectors();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(hReturn, new org.xms.g.utils.Function<com.huawei.hms.hihealth.data.DataCollector, org.xms.g.fitness.data.DataSource>() {
                
                public org.xms.g.fitness.data.DataSource apply(com.huawei.hms.hihealth.data.DataCollector param0) {
                    return new org.xms.g.fitness.data.DataSource(new org.xms.g.utils.XBox(null, param0));
                }
            }));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataDeleteRequest) this.getGInstance()).getDataSources()");
            java.util.List gReturn = ((com.google.android.gms.fitness.request.DataDeleteRequest) this.getGInstance()).getDataSources();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(gReturn, new org.xms.g.utils.Function<com.google.android.gms.fitness.data.DataSource, org.xms.g.fitness.data.DataSource>() {
                
                public org.xms.g.fitness.data.DataSource apply(com.google.android.gms.fitness.data.DataSource param0) {
                    return new org.xms.g.fitness.data.DataSource(new org.xms.g.utils.XBox(param0, null));
                }
            }));
        }
    }
    
    public java.util.List<org.xms.g.fitness.data.DataType> getDataTypes() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.DeleteOptions) this.getHInstance()).getDataTypes()");
            java.util.List hReturn = ((com.huawei.hms.hihealth.options.DeleteOptions) this.getHInstance()).getDataTypes();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(hReturn, new org.xms.g.utils.Function<com.huawei.hms.hihealth.data.DataType, org.xms.g.fitness.data.DataType>() {
                
                public org.xms.g.fitness.data.DataType apply(com.huawei.hms.hihealth.data.DataType param0) {
                    return new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, param0));
                }
            }));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataDeleteRequest) this.getGInstance()).getDataTypes()");
            java.util.List gReturn = ((com.google.android.gms.fitness.request.DataDeleteRequest) this.getGInstance()).getDataTypes();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(gReturn, new org.xms.g.utils.Function<com.google.android.gms.fitness.data.DataType, org.xms.g.fitness.data.DataType>() {
                
                public org.xms.g.fitness.data.DataType apply(com.google.android.gms.fitness.data.DataType param0) {
                    return new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(param0, null));
                }
            }));
        }
    }
    
    public long getEndTime(java.util.concurrent.TimeUnit param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.DeleteOptions) this.getHInstance()).getEndTime(param0)");
            return ((com.huawei.hms.hihealth.options.DeleteOptions) this.getHInstance()).getEndTime(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataDeleteRequest) this.getGInstance()).getEndTime(param0)");
            return ((com.google.android.gms.fitness.request.DataDeleteRequest) this.getGInstance()).getEndTime(param0);
        }
    }
    
    public java.util.List<org.xms.g.fitness.data.Session> getSessions() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.DeleteOptions) this.getHInstance()).getActivityRecords()");
            java.util.List hReturn = ((com.huawei.hms.hihealth.options.DeleteOptions) this.getHInstance()).getActivityRecords();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(hReturn, new org.xms.g.utils.Function<com.huawei.hms.hihealth.data.ActivityRecord, org.xms.g.fitness.data.Session>() {
                
                public org.xms.g.fitness.data.Session apply(com.huawei.hms.hihealth.data.ActivityRecord param0) {
                    return new org.xms.g.fitness.data.Session(new org.xms.g.utils.XBox(null, param0));
                }
            }));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataDeleteRequest) this.getGInstance()).getSessions()");
            java.util.List gReturn = ((com.google.android.gms.fitness.request.DataDeleteRequest) this.getGInstance()).getSessions();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(gReturn, new org.xms.g.utils.Function<com.google.android.gms.fitness.data.Session, org.xms.g.fitness.data.Session>() {
                
                public org.xms.g.fitness.data.Session apply(com.google.android.gms.fitness.data.Session param0) {
                    return new org.xms.g.fitness.data.Session(new org.xms.g.utils.XBox(param0, null));
                }
            }));
        }
    }
    
    public long getStartTime(java.util.concurrent.TimeUnit param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.DeleteOptions) this.getHInstance()).getStartTime(param0)");
            return ((com.huawei.hms.hihealth.options.DeleteOptions) this.getHInstance()).getStartTime(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataDeleteRequest) this.getGInstance()).getStartTime(param0)");
            return ((com.google.android.gms.fitness.request.DataDeleteRequest) this.getGInstance()).getStartTime(param0);
        }
    }
    
    public int hashCode() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.DeleteOptions) this.getHInstance()).hashCode()");
            return ((com.huawei.hms.hihealth.options.DeleteOptions) this.getHInstance()).hashCode();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataDeleteRequest) this.getGInstance()).hashCode()");
            return ((com.google.android.gms.fitness.request.DataDeleteRequest) this.getGInstance()).hashCode();
        }
    }
    
    public java.lang.String toString() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.DeleteOptions) this.getHInstance()).toString()");
            return ((com.huawei.hms.hihealth.options.DeleteOptions) this.getHInstance()).toString();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataDeleteRequest) this.getGInstance()).toString()");
            return ((com.google.android.gms.fitness.request.DataDeleteRequest) this.getGInstance()).toString();
        }
    }
    
    public void writeToParcel(android.os.Parcel param0, int param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.DeleteOptions) this.getHInstance()).writeToParcel(param0, param1)");
            ((com.huawei.hms.hihealth.options.DeleteOptions) this.getHInstance()).writeToParcel(param0, param1);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataDeleteRequest) this.getGInstance()).writeToParcel(param0, param1)");
            ((com.google.android.gms.fitness.request.DataDeleteRequest) this.getGInstance()).writeToParcel(param0, param1);
        }
    }
    
    public int describeContents() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.request.DataDeleteRequest dynamicCast(java.lang.Object param0) {
        return ((org.xms.g.fitness.request.DataDeleteRequest) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.options.DeleteOptions;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.request.DataDeleteRequest;
        }
    }
    
    public static class Builder extends org.xms.g.utils.XObject {
        
        public Builder(org.xms.g.utils.XBox param0) {
            super(param0);
        }
        
        public Builder() {
            super(((org.xms.g.utils.XBox) null));
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                this.setHInstance(new com.huawei.hms.hihealth.options.DeleteOptions.Builder());
            } else {
                this.setGInstance(new com.google.android.gms.fitness.request.DataDeleteRequest.Builder());
            }
        }
        
        public org.xms.g.fitness.request.DataDeleteRequest.Builder addDataSource(org.xms.g.fitness.data.DataSource param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.DeleteOptions.Builder) this.getHInstance()).addDataCollector(((com.huawei.hms.hihealth.data.DataCollector) ((param0) == null ? null : (param0.getHInstance()))))");
                com.huawei.hms.hihealth.options.DeleteOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.DeleteOptions.Builder) this.getHInstance()).addDataCollector(((com.huawei.hms.hihealth.data.DataCollector) ((param0) == null ? null : (param0.getHInstance()))));
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.DataDeleteRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataDeleteRequest.Builder) this.getGInstance()).addDataSource(((com.google.android.gms.fitness.data.DataSource) ((param0) == null ? null : (param0.getGInstance()))))");
                com.google.android.gms.fitness.request.DataDeleteRequest.Builder gReturn = ((com.google.android.gms.fitness.request.DataDeleteRequest.Builder) this.getGInstance()).addDataSource(((com.google.android.gms.fitness.data.DataSource) ((param0) == null ? null : (param0.getGInstance()))));
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.DataDeleteRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.DataDeleteRequest.Builder addDataType(org.xms.g.fitness.data.DataType param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.DeleteOptions.Builder) this.getHInstance()).addDataType(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))))");
                com.huawei.hms.hihealth.options.DeleteOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.DeleteOptions.Builder) this.getHInstance()).addDataType(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))));
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.DataDeleteRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataDeleteRequest.Builder) this.getGInstance()).addDataType(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))))");
                com.google.android.gms.fitness.request.DataDeleteRequest.Builder gReturn = ((com.google.android.gms.fitness.request.DataDeleteRequest.Builder) this.getGInstance()).addDataType(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))));
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.DataDeleteRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.DataDeleteRequest.Builder addSession(org.xms.g.fitness.data.Session param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.DeleteOptions.Builder) this.getHInstance()).addActivityRecord(((com.huawei.hms.hihealth.data.ActivityRecord) ((param0) == null ? null : (param0.getHInstance()))))");
                com.huawei.hms.hihealth.options.DeleteOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.DeleteOptions.Builder) this.getHInstance()).addActivityRecord(((com.huawei.hms.hihealth.data.ActivityRecord) ((param0) == null ? null : (param0.getHInstance()))));
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.DataDeleteRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataDeleteRequest.Builder) this.getGInstance()).addSession(((com.google.android.gms.fitness.data.Session) ((param0) == null ? null : (param0.getGInstance()))))");
                com.google.android.gms.fitness.request.DataDeleteRequest.Builder gReturn = ((com.google.android.gms.fitness.request.DataDeleteRequest.Builder) this.getGInstance()).addSession(((com.google.android.gms.fitness.data.Session) ((param0) == null ? null : (param0.getGInstance()))));
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.DataDeleteRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.DataDeleteRequest build() {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.DeleteOptions.Builder) this.getHInstance()).build()");
                com.huawei.hms.hihealth.options.DeleteOptions hReturn = ((com.huawei.hms.hihealth.options.DeleteOptions.Builder) this.getHInstance()).build();
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.DataDeleteRequest(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataDeleteRequest.Builder) this.getGInstance()).build()");
                com.google.android.gms.fitness.request.DataDeleteRequest gReturn = ((com.google.android.gms.fitness.request.DataDeleteRequest.Builder) this.getGInstance()).build();
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.DataDeleteRequest(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.DataDeleteRequest.Builder deleteAllData() {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.DeleteOptions.Builder) this.getHInstance()).deleteAllData()");
                com.huawei.hms.hihealth.options.DeleteOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.DeleteOptions.Builder) this.getHInstance()).deleteAllData();
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.DataDeleteRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataDeleteRequest.Builder) this.getGInstance()).deleteAllData()");
                com.google.android.gms.fitness.request.DataDeleteRequest.Builder gReturn = ((com.google.android.gms.fitness.request.DataDeleteRequest.Builder) this.getGInstance()).deleteAllData();
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.DataDeleteRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.DataDeleteRequest.Builder deleteAllSessions() {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.DeleteOptions.Builder) this.getHInstance()).deleteAllActivityRecords()");
                com.huawei.hms.hihealth.options.DeleteOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.DeleteOptions.Builder) this.getHInstance()).deleteAllActivityRecords();
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.DataDeleteRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataDeleteRequest.Builder) this.getGInstance()).deleteAllSessions()");
                com.google.android.gms.fitness.request.DataDeleteRequest.Builder gReturn = ((com.google.android.gms.fitness.request.DataDeleteRequest.Builder) this.getGInstance()).deleteAllSessions();
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.DataDeleteRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.DataDeleteRequest.Builder setTimeInterval(long param0, long param1, java.util.concurrent.TimeUnit param2) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.DeleteOptions.Builder) this.getHInstance()).setTimeInterval(param0, param1, param2)");
                com.huawei.hms.hihealth.options.DeleteOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.DeleteOptions.Builder) this.getHInstance()).setTimeInterval(param0, param1, param2);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.DataDeleteRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataDeleteRequest.Builder) this.getGInstance()).setTimeInterval(param0, param1, param2)");
                com.google.android.gms.fitness.request.DataDeleteRequest.Builder gReturn = ((com.google.android.gms.fitness.request.DataDeleteRequest.Builder) this.getGInstance()).setTimeInterval(param0, param1, param2);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.DataDeleteRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public static org.xms.g.fitness.request.DataDeleteRequest.Builder dynamicCast(java.lang.Object param0) {
            return ((org.xms.g.fitness.request.DataDeleteRequest.Builder) param0);
        }
        
        public static boolean isInstance(java.lang.Object param0) {
            if (!(param0 instanceof org.xms.g.utils.XGettable)) {
                return false;
            }
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.options.DeleteOptions.Builder;
            } else {
                return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.request.DataDeleteRequest.Builder;
            }
        }
    }
}