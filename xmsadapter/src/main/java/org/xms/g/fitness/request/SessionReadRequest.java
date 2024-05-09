package org.xms.g.fitness.request;

public class SessionReadRequest extends org.xms.g.utils.XObject implements android.os.Parcelable {
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {
        
        public org.xms.g.fitness.request.SessionReadRequest createFromParcel(android.os.Parcel param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                com.huawei.hms.hihealth.options.ActivityRecordReadOptions hReturn = com.huawei.hms.hihealth.options.ActivityRecordReadOptions.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.request.SessionReadRequest(new org.xms.g.utils.XBox(null, hReturn));
            } else {
                com.google.android.gms.fitness.request.SessionReadRequest gReturn = com.google.android.gms.fitness.request.SessionReadRequest.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.request.SessionReadRequest(new org.xms.g.utils.XBox(gReturn, null));
            }
        }
        
        public org.xms.g.fitness.request.SessionReadRequest[] newArray(int param0) {
            return new org.xms.g.fitness.request.SessionReadRequest[param0];
        }
    };
    
    public SessionReadRequest(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public boolean equals(java.lang.Object param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ActivityRecordReadOptions) this.getHInstance()).equals(param0)");
            return ((com.huawei.hms.hihealth.options.ActivityRecordReadOptions) this.getHInstance()).equals(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SessionReadRequest) this.getGInstance()).equals(param0)");
            return ((com.google.android.gms.fitness.request.SessionReadRequest) this.getGInstance()).equals(param0);
        }
    }
    
    public java.util.List<org.xms.g.fitness.data.DataSource> getDataSources() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ActivityRecordReadOptions) this.getHInstance()).getDataCollectors()");
            java.util.List hReturn = ((com.huawei.hms.hihealth.options.ActivityRecordReadOptions) this.getHInstance()).getDataCollectors();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(hReturn, new org.xms.g.utils.Function<com.huawei.hms.hihealth.data.DataCollector, org.xms.g.fitness.data.DataSource>() {
                
                public org.xms.g.fitness.data.DataSource apply(com.huawei.hms.hihealth.data.DataCollector param0) {
                    return new org.xms.g.fitness.data.DataSource(new org.xms.g.utils.XBox(null, param0));
                }
            }));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SessionReadRequest) this.getGInstance()).getDataSources()");
            java.util.List gReturn = ((com.google.android.gms.fitness.request.SessionReadRequest) this.getGInstance()).getDataSources();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(gReturn, new org.xms.g.utils.Function<com.google.android.gms.fitness.data.DataSource, org.xms.g.fitness.data.DataSource>() {
                
                public org.xms.g.fitness.data.DataSource apply(com.google.android.gms.fitness.data.DataSource param0) {
                    return new org.xms.g.fitness.data.DataSource(new org.xms.g.utils.XBox(param0, null));
                }
            }));
        }
    }
    
    public java.util.List<org.xms.g.fitness.data.DataType> getDataTypes() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ActivityRecordReadOptions) this.getHInstance()).getDataTypes()");
            java.util.List hReturn = ((com.huawei.hms.hihealth.options.ActivityRecordReadOptions) this.getHInstance()).getDataTypes();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(hReturn, new org.xms.g.utils.Function<com.huawei.hms.hihealth.data.DataType, org.xms.g.fitness.data.DataType>() {
                
                public org.xms.g.fitness.data.DataType apply(com.huawei.hms.hihealth.data.DataType param0) {
                    return new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, param0));
                }
            }));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SessionReadRequest) this.getGInstance()).getDataTypes()");
            java.util.List gReturn = ((com.google.android.gms.fitness.request.SessionReadRequest) this.getGInstance()).getDataTypes();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(gReturn, new org.xms.g.utils.Function<com.google.android.gms.fitness.data.DataType, org.xms.g.fitness.data.DataType>() {
                
                public org.xms.g.fitness.data.DataType apply(com.google.android.gms.fitness.data.DataType param0) {
                    return new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(param0, null));
                }
            }));
        }
    }
    
    public long getEndTime(java.util.concurrent.TimeUnit param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ActivityRecordReadOptions) this.getHInstance()).getEndTime(param0)");
            return ((com.huawei.hms.hihealth.options.ActivityRecordReadOptions) this.getHInstance()).getEndTime(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SessionReadRequest) this.getGInstance()).getEndTime(param0)");
            return ((com.google.android.gms.fitness.request.SessionReadRequest) this.getGInstance()).getEndTime(param0);
        }
    }
    
    public java.util.List<java.lang.String> getExcludedPackages() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ActivityRecordReadOptions) this.getHInstance()).getRemoveApplications()");
            return ((com.huawei.hms.hihealth.options.ActivityRecordReadOptions) this.getHInstance()).getRemoveApplications();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SessionReadRequest) this.getGInstance()).getExcludedPackages()");
            return ((com.google.android.gms.fitness.request.SessionReadRequest) this.getGInstance()).getExcludedPackages();
        }
    }
    
    public java.lang.String getSessionId() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ActivityRecordReadOptions) this.getHInstance()).getActivityRecordId()");
            return ((com.huawei.hms.hihealth.options.ActivityRecordReadOptions) this.getHInstance()).getActivityRecordId();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SessionReadRequest) this.getGInstance()).getSessionId()");
            return ((com.google.android.gms.fitness.request.SessionReadRequest) this.getGInstance()).getSessionId();
        }
    }
    
    public java.lang.String getSessionName() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ActivityRecordReadOptions) this.getHInstance()).getActivityRecordName()");
            return ((com.huawei.hms.hihealth.options.ActivityRecordReadOptions) this.getHInstance()).getActivityRecordName();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SessionReadRequest) this.getGInstance()).getSessionName()");
            return ((com.google.android.gms.fitness.request.SessionReadRequest) this.getGInstance()).getSessionName();
        }
    }
    
    public long getStartTime(java.util.concurrent.TimeUnit param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ActivityRecordReadOptions) this.getHInstance()).getStartTime(param0)");
            return ((com.huawei.hms.hihealth.options.ActivityRecordReadOptions) this.getHInstance()).getStartTime(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SessionReadRequest) this.getGInstance()).getStartTime(param0)");
            return ((com.google.android.gms.fitness.request.SessionReadRequest) this.getGInstance()).getStartTime(param0);
        }
    }
    
    public int hashCode() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ActivityRecordReadOptions) this.getHInstance()).hashCode()");
            return ((com.huawei.hms.hihealth.options.ActivityRecordReadOptions) this.getHInstance()).hashCode();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SessionReadRequest) this.getGInstance()).hashCode()");
            return ((com.google.android.gms.fitness.request.SessionReadRequest) this.getGInstance()).hashCode();
        }
    }
    
    public boolean includeSessionsFromAllApps() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ActivityRecordReadOptions) this.getHInstance()).isAllAppsSelected()");
            return ((com.huawei.hms.hihealth.options.ActivityRecordReadOptions) this.getHInstance()).isAllAppsSelected();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SessionReadRequest) this.getGInstance()).includeSessionsFromAllApps()");
            return ((com.google.android.gms.fitness.request.SessionReadRequest) this.getGInstance()).includeSessionsFromAllApps();
        }
    }
    
    public java.lang.String toString() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ActivityRecordReadOptions) this.getHInstance()).toString()");
            return ((com.huawei.hms.hihealth.options.ActivityRecordReadOptions) this.getHInstance()).toString();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SessionReadRequest) this.getGInstance()).toString()");
            return ((com.google.android.gms.fitness.request.SessionReadRequest) this.getGInstance()).toString();
        }
    }
    
    public void writeToParcel(android.os.Parcel param0, int param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ActivityRecordReadOptions) this.getHInstance()).writeToParcel(param0, param1)");
            ((com.huawei.hms.hihealth.options.ActivityRecordReadOptions) this.getHInstance()).writeToParcel(param0, param1);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SessionReadRequest) this.getGInstance()).writeToParcel(param0, param1)");
            ((com.google.android.gms.fitness.request.SessionReadRequest) this.getGInstance()).writeToParcel(param0, param1);
        }
    }
    
    public int describeContents() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.request.SessionReadRequest dynamicCast(java.lang.Object param0) {
        return ((org.xms.g.fitness.request.SessionReadRequest) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.options.ActivityRecordReadOptions;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.request.SessionReadRequest;
        }
    }
    
    public static class Builder extends org.xms.g.utils.XObject {
        
        public Builder(org.xms.g.utils.XBox param0) {
            super(param0);
        }
        
        public Builder() {
            super(((org.xms.g.utils.XBox) null));
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                this.setHInstance(new com.huawei.hms.hihealth.options.ActivityRecordReadOptions.Builder());
            } else {
                this.setGInstance(new com.google.android.gms.fitness.request.SessionReadRequest.Builder());
            }
        }
        
        public org.xms.g.fitness.request.SessionReadRequest build() {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ActivityRecordReadOptions.Builder) this.getHInstance()).build()");
                com.huawei.hms.hihealth.options.ActivityRecordReadOptions hReturn = ((com.huawei.hms.hihealth.options.ActivityRecordReadOptions.Builder) this.getHInstance()).build();
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.SessionReadRequest(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SessionReadRequest.Builder) this.getGInstance()).build()");
                com.google.android.gms.fitness.request.SessionReadRequest gReturn = ((com.google.android.gms.fitness.request.SessionReadRequest.Builder) this.getGInstance()).build();
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.SessionReadRequest(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.SessionReadRequest.Builder enableServerQueries() {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ActivityRecordReadOptions.Builder) this.getHInstance()).allowRemoteInquiry()");
                com.huawei.hms.hihealth.options.ActivityRecordReadOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.ActivityRecordReadOptions.Builder) this.getHInstance()).allowRemoteInquiry();
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.SessionReadRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SessionReadRequest.Builder) this.getGInstance()).enableServerQueries()");
                com.google.android.gms.fitness.request.SessionReadRequest.Builder gReturn = ((com.google.android.gms.fitness.request.SessionReadRequest.Builder) this.getGInstance()).enableServerQueries();
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.SessionReadRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.SessionReadRequest.Builder excludePackage(java.lang.String param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ActivityRecordReadOptions.Builder) this.getHInstance()).removeApplication(param0)");
                com.huawei.hms.hihealth.options.ActivityRecordReadOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.ActivityRecordReadOptions.Builder) this.getHInstance()).removeApplication(param0);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.SessionReadRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SessionReadRequest.Builder) this.getGInstance()).excludePackage(param0)");
                com.google.android.gms.fitness.request.SessionReadRequest.Builder gReturn = ((com.google.android.gms.fitness.request.SessionReadRequest.Builder) this.getGInstance()).excludePackage(param0);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.SessionReadRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.SessionReadRequest.Builder read(org.xms.g.fitness.data.DataSource param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ActivityRecordReadOptions.Builder) this.getHInstance()).read(((com.huawei.hms.hihealth.data.DataCollector) ((param0) == null ? null : (param0.getHInstance()))))");
                com.huawei.hms.hihealth.options.ActivityRecordReadOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.ActivityRecordReadOptions.Builder) this.getHInstance()).read(((com.huawei.hms.hihealth.data.DataCollector) ((param0) == null ? null : (param0.getHInstance()))));
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.SessionReadRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SessionReadRequest.Builder) this.getGInstance()).read(((com.google.android.gms.fitness.data.DataSource) ((param0) == null ? null : (param0.getGInstance()))))");
                com.google.android.gms.fitness.request.SessionReadRequest.Builder gReturn = ((com.google.android.gms.fitness.request.SessionReadRequest.Builder) this.getGInstance()).read(((com.google.android.gms.fitness.data.DataSource) ((param0) == null ? null : (param0.getGInstance()))));
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.SessionReadRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.SessionReadRequest.Builder read(org.xms.g.fitness.data.DataType param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ActivityRecordReadOptions.Builder) this.getHInstance()).read(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))))");
                com.huawei.hms.hihealth.options.ActivityRecordReadOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.ActivityRecordReadOptions.Builder) this.getHInstance()).read(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))));
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.SessionReadRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SessionReadRequest.Builder) this.getGInstance()).read(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))))");
                com.google.android.gms.fitness.request.SessionReadRequest.Builder gReturn = ((com.google.android.gms.fitness.request.SessionReadRequest.Builder) this.getGInstance()).read(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))));
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.SessionReadRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.SessionReadRequest.Builder readSessionsFromAllApps() {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ActivityRecordReadOptions.Builder) this.getHInstance()).readActivityRecordsFromAllApps()");
                com.huawei.hms.hihealth.options.ActivityRecordReadOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.ActivityRecordReadOptions.Builder) this.getHInstance()).readActivityRecordsFromAllApps();
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.SessionReadRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SessionReadRequest.Builder) this.getGInstance()).readSessionsFromAllApps()");
                com.google.android.gms.fitness.request.SessionReadRequest.Builder gReturn = ((com.google.android.gms.fitness.request.SessionReadRequest.Builder) this.getGInstance()).readSessionsFromAllApps();
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.SessionReadRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.SessionReadRequest.Builder setSessionId(java.lang.String param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ActivityRecordReadOptions.Builder) this.getHInstance()).setActivityRecordId(param0)");
                com.huawei.hms.hihealth.options.ActivityRecordReadOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.ActivityRecordReadOptions.Builder) this.getHInstance()).setActivityRecordId(param0);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.SessionReadRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SessionReadRequest.Builder) this.getGInstance()).setSessionId(param0)");
                com.google.android.gms.fitness.request.SessionReadRequest.Builder gReturn = ((com.google.android.gms.fitness.request.SessionReadRequest.Builder) this.getGInstance()).setSessionId(param0);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.SessionReadRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.SessionReadRequest.Builder setSessionName(java.lang.String param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ActivityRecordReadOptions.Builder) this.getHInstance()).setActivityRecordName(param0)");
                com.huawei.hms.hihealth.options.ActivityRecordReadOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.ActivityRecordReadOptions.Builder) this.getHInstance()).setActivityRecordName(param0);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.SessionReadRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SessionReadRequest.Builder) this.getGInstance()).setSessionName(param0)");
                com.google.android.gms.fitness.request.SessionReadRequest.Builder gReturn = ((com.google.android.gms.fitness.request.SessionReadRequest.Builder) this.getGInstance()).setSessionName(param0);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.SessionReadRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.SessionReadRequest.Builder setTimeInterval(long param0, long param1, java.util.concurrent.TimeUnit param2) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ActivityRecordReadOptions.Builder) this.getHInstance()).setTimeInterval(param0, param1, param2)");
                com.huawei.hms.hihealth.options.ActivityRecordReadOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.ActivityRecordReadOptions.Builder) this.getHInstance()).setTimeInterval(param0, param1, param2);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.SessionReadRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.SessionReadRequest.Builder) this.getGInstance()).setTimeInterval(param0, param1, param2)");
                com.google.android.gms.fitness.request.SessionReadRequest.Builder gReturn = ((com.google.android.gms.fitness.request.SessionReadRequest.Builder) this.getGInstance()).setTimeInterval(param0, param1, param2);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.SessionReadRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public static org.xms.g.fitness.request.SessionReadRequest.Builder dynamicCast(java.lang.Object param0) {
            return ((org.xms.g.fitness.request.SessionReadRequest.Builder) param0);
        }
        
        public static boolean isInstance(java.lang.Object param0) {
            if (!(param0 instanceof org.xms.g.utils.XGettable)) {
                return false;
            }
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.options.ActivityRecordReadOptions.Builder;
            } else {
                return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.request.SessionReadRequest.Builder;
            }
        }
    }
}