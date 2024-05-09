package org.xms.g.fitness.request;

public class DataReadRequest extends org.xms.g.utils.XObject implements android.os.Parcelable {
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {
        
        public org.xms.g.fitness.request.DataReadRequest createFromParcel(android.os.Parcel param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                com.huawei.hms.hihealth.options.ReadOptions hReturn = com.huawei.hms.hihealth.options.ReadOptions.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.request.DataReadRequest(new org.xms.g.utils.XBox(null, hReturn));
            } else {
                com.google.android.gms.fitness.request.DataReadRequest gReturn = com.google.android.gms.fitness.request.DataReadRequest.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.request.DataReadRequest(new org.xms.g.utils.XBox(gReturn, null));
            }
        }
        
        public org.xms.g.fitness.request.DataReadRequest[] newArray(int param0) {
            return new org.xms.g.fitness.request.DataReadRequest[param0];
        }
    };
    
    public DataReadRequest(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public static int getNO_LIMIT() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.options.ReadOptions.NO_LIMIT");
            return com.huawei.hms.hihealth.options.ReadOptions.NO_LIMIT;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.request.DataReadRequest.NO_LIMIT");
            return com.google.android.gms.fitness.request.DataReadRequest.NO_LIMIT;
        }
    }
    
    public boolean equals(java.lang.Object param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ReadOptions) this.getHInstance()).equals(param0)");
            return ((com.huawei.hms.hihealth.options.ReadOptions) this.getHInstance()).equals(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataReadRequest) this.getGInstance()).equals(param0)");
            return ((com.google.android.gms.fitness.request.DataReadRequest) this.getGInstance()).equals(param0);
        }
    }
    
    public org.xms.g.fitness.data.DataSource getActivityDataSource() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ReadOptions) this.getHInstance()).getActivityDataCollector()");
            com.huawei.hms.hihealth.data.DataCollector hReturn = ((com.huawei.hms.hihealth.options.ReadOptions) this.getHInstance()).getActivityDataCollector();
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataSource(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataReadRequest) this.getGInstance()).getActivityDataSource()");
            com.google.android.gms.fitness.data.DataSource gReturn = ((com.google.android.gms.fitness.request.DataReadRequest) this.getGInstance()).getActivityDataSource();
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataSource(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public java.util.List<org.xms.g.fitness.data.DataSource> getAggregatedDataSources() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ReadOptions) this.getHInstance()).getPolymerizedDataCollectors()");
            java.util.List hReturn = ((com.huawei.hms.hihealth.options.ReadOptions) this.getHInstance()).getPolymerizedDataCollectors();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(hReturn, new org.xms.g.utils.Function<com.huawei.hms.hihealth.data.DataCollector, org.xms.g.fitness.data.DataSource>() {
                
                public org.xms.g.fitness.data.DataSource apply(com.huawei.hms.hihealth.data.DataCollector param0) {
                    return new org.xms.g.fitness.data.DataSource(new org.xms.g.utils.XBox(null, param0));
                }
            }));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataReadRequest) this.getGInstance()).getAggregatedDataSources()");
            java.util.List gReturn = ((com.google.android.gms.fitness.request.DataReadRequest) this.getGInstance()).getAggregatedDataSources();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(gReturn, new org.xms.g.utils.Function<com.google.android.gms.fitness.data.DataSource, org.xms.g.fitness.data.DataSource>() {
                
                public org.xms.g.fitness.data.DataSource apply(com.google.android.gms.fitness.data.DataSource param0) {
                    return new org.xms.g.fitness.data.DataSource(new org.xms.g.utils.XBox(param0, null));
                }
            }));
        }
    }
    
    public java.util.List<org.xms.g.fitness.data.DataType> getAggregatedDataTypes() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ReadOptions) this.getHInstance()).getPolymerizedDataTypes()");
            java.util.List hReturn = ((com.huawei.hms.hihealth.options.ReadOptions) this.getHInstance()).getPolymerizedDataTypes();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(hReturn, new org.xms.g.utils.Function<com.huawei.hms.hihealth.data.DataType, org.xms.g.fitness.data.DataType>() {
                
                public org.xms.g.fitness.data.DataType apply(com.huawei.hms.hihealth.data.DataType param0) {
                    return new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, param0));
                }
            }));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataReadRequest) this.getGInstance()).getAggregatedDataTypes()");
            java.util.List gReturn = ((com.google.android.gms.fitness.request.DataReadRequest) this.getGInstance()).getAggregatedDataTypes();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(gReturn, new org.xms.g.utils.Function<com.google.android.gms.fitness.data.DataType, org.xms.g.fitness.data.DataType>() {
                
                public org.xms.g.fitness.data.DataType apply(com.google.android.gms.fitness.data.DataType param0) {
                    return new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(param0, null));
                }
            }));
        }
    }
    
    public long getBucketDuration(java.util.concurrent.TimeUnit param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ReadOptions) this.getHInstance()).getGroupPeriod(param0)");
            return ((com.huawei.hms.hihealth.options.ReadOptions) this.getHInstance()).getGroupPeriod(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataReadRequest) this.getGInstance()).getBucketDuration(param0)");
            return ((com.google.android.gms.fitness.request.DataReadRequest) this.getGInstance()).getBucketDuration(param0);
        }
    }
    
    public int getBucketType() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ReadOptions) this.getHInstance()).getGroupType()");
            return ((com.huawei.hms.hihealth.options.ReadOptions) this.getHInstance()).getGroupType();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataReadRequest) this.getGInstance()).getBucketType()");
            return ((com.google.android.gms.fitness.request.DataReadRequest) this.getGInstance()).getBucketType();
        }
    }
    
    public java.util.List<org.xms.g.fitness.data.DataSource> getDataSources() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ReadOptions) this.getHInstance()).getDataCollectors()");
            java.util.List hReturn = ((com.huawei.hms.hihealth.options.ReadOptions) this.getHInstance()).getDataCollectors();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(hReturn, new org.xms.g.utils.Function<com.huawei.hms.hihealth.data.DataCollector, org.xms.g.fitness.data.DataSource>() {
                
                public org.xms.g.fitness.data.DataSource apply(com.huawei.hms.hihealth.data.DataCollector param0) {
                    return new org.xms.g.fitness.data.DataSource(new org.xms.g.utils.XBox(null, param0));
                }
            }));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataReadRequest) this.getGInstance()).getDataSources()");
            java.util.List gReturn = ((com.google.android.gms.fitness.request.DataReadRequest) this.getGInstance()).getDataSources();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(gReturn, new org.xms.g.utils.Function<com.google.android.gms.fitness.data.DataSource, org.xms.g.fitness.data.DataSource>() {
                
                public org.xms.g.fitness.data.DataSource apply(com.google.android.gms.fitness.data.DataSource param0) {
                    return new org.xms.g.fitness.data.DataSource(new org.xms.g.utils.XBox(param0, null));
                }
            }));
        }
    }
    
    public java.util.List<org.xms.g.fitness.data.DataType> getDataTypes() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ReadOptions) this.getHInstance()).getDataTypes()");
            java.util.List hReturn = ((com.huawei.hms.hihealth.options.ReadOptions) this.getHInstance()).getDataTypes();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(hReturn, new org.xms.g.utils.Function<com.huawei.hms.hihealth.data.DataType, org.xms.g.fitness.data.DataType>() {
                
                public org.xms.g.fitness.data.DataType apply(com.huawei.hms.hihealth.data.DataType param0) {
                    return new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, param0));
                }
            }));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataReadRequest) this.getGInstance()).getDataTypes()");
            java.util.List gReturn = ((com.google.android.gms.fitness.request.DataReadRequest) this.getGInstance()).getDataTypes();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(gReturn, new org.xms.g.utils.Function<com.google.android.gms.fitness.data.DataType, org.xms.g.fitness.data.DataType>() {
                
                public org.xms.g.fitness.data.DataType apply(com.google.android.gms.fitness.data.DataType param0) {
                    return new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(param0, null));
                }
            }));
        }
    }
    
    public long getEndTime(java.util.concurrent.TimeUnit param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ReadOptions) this.getHInstance()).getEndTime(param0)");
            return ((com.huawei.hms.hihealth.options.ReadOptions) this.getHInstance()).getEndTime(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataReadRequest) this.getGInstance()).getEndTime(param0)");
            return ((com.google.android.gms.fitness.request.DataReadRequest) this.getGInstance()).getEndTime(param0);
        }
    }
    
    public java.util.List<java.lang.Integer> getFilteredDataQualityStandards() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public int getLimit() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ReadOptions) this.getHInstance()).getPageSize()");
            return ((com.huawei.hms.hihealth.options.ReadOptions) this.getHInstance()).getPageSize();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataReadRequest) this.getGInstance()).getLimit()");
            return ((com.google.android.gms.fitness.request.DataReadRequest) this.getGInstance()).getLimit();
        }
    }
    
    public long getStartTime(java.util.concurrent.TimeUnit param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ReadOptions) this.getHInstance()).getStartTime(param0)");
            return ((com.huawei.hms.hihealth.options.ReadOptions) this.getHInstance()).getStartTime(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataReadRequest) this.getGInstance()).getStartTime(param0)");
            return ((com.google.android.gms.fitness.request.DataReadRequest) this.getGInstance()).getStartTime(param0);
        }
    }
    
    public int hashCode() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ReadOptions) this.getHInstance()).hashCode()");
            return ((com.huawei.hms.hihealth.options.ReadOptions) this.getHInstance()).hashCode();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataReadRequest) this.getGInstance()).hashCode()");
            return ((com.google.android.gms.fitness.request.DataReadRequest) this.getGInstance()).hashCode();
        }
    }
    
    public java.lang.String toString() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ReadOptions) this.getHInstance()).toString()");
            return ((com.huawei.hms.hihealth.options.ReadOptions) this.getHInstance()).toString();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataReadRequest) this.getGInstance()).toString()");
            return ((com.google.android.gms.fitness.request.DataReadRequest) this.getGInstance()).toString();
        }
    }
    
    public void writeToParcel(android.os.Parcel param0, int param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ReadOptions) this.getHInstance()).writeToParcel(param0, param1)");
            ((com.huawei.hms.hihealth.options.ReadOptions) this.getHInstance()).writeToParcel(param0, param1);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataReadRequest) this.getGInstance()).writeToParcel(param0, param1)");
            ((com.google.android.gms.fitness.request.DataReadRequest) this.getGInstance()).writeToParcel(param0, param1);
        }
    }
    
    public int describeContents() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.request.DataReadRequest dynamicCast(java.lang.Object param0) {
        return ((org.xms.g.fitness.request.DataReadRequest) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.options.ReadOptions;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.request.DataReadRequest;
        }
    }
    
    public static class Builder extends org.xms.g.utils.XObject {
        
        
        
        public Builder(org.xms.g.utils.XBox param0) {
            super(param0);
        }
        
        public Builder() {
            super(((org.xms.g.utils.XBox) null));
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                this.setHInstance(new com.huawei.hms.hihealth.options.ReadOptions.Builder());
            } else {
                this.setGInstance(new com.google.android.gms.fitness.request.DataReadRequest.Builder());
            }
        }
        
        public org.xms.g.fitness.request.DataReadRequest.Builder addFilteredDataQualityStandard(int param0) {
            throw new java.lang.RuntimeException("Not Supported");
        }
        
        public org.xms.g.fitness.request.DataReadRequest.Builder aggregate(org.xms.g.fitness.data.DataSource param0, org.xms.g.fitness.data.DataType param1) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ReadOptions.Builder) this.getHInstance()).polymerize(((com.huawei.hms.hihealth.data.DataCollector) ((param0) == null ? null : (param0.getHInstance()))), ((com.huawei.hms.hihealth.data.DataType) ((param1) == null ? null : (param1.getHInstance()))))");
                com.huawei.hms.hihealth.options.ReadOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.ReadOptions.Builder) this.getHInstance()).polymerize(((com.huawei.hms.hihealth.data.DataCollector) ((param0) == null ? null : (param0.getHInstance()))), ((com.huawei.hms.hihealth.data.DataType) ((param1) == null ? null : (param1.getHInstance()))));
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.DataReadRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataReadRequest.Builder) this.getGInstance()).aggregate(((com.google.android.gms.fitness.data.DataSource) ((param0) == null ? null : (param0.getGInstance()))), ((com.google.android.gms.fitness.data.DataType) ((param1) == null ? null : (param1.getGInstance()))))");
                com.google.android.gms.fitness.request.DataReadRequest.Builder gReturn = ((com.google.android.gms.fitness.request.DataReadRequest.Builder) this.getGInstance()).aggregate(((com.google.android.gms.fitness.data.DataSource) ((param0) == null ? null : (param0.getGInstance()))), ((com.google.android.gms.fitness.data.DataType) ((param1) == null ? null : (param1.getGInstance()))));
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.DataReadRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.DataReadRequest.Builder aggregate(org.xms.g.fitness.data.DataType param0, org.xms.g.fitness.data.DataType param1) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ReadOptions.Builder) this.getHInstance()).polymerize(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))), ((com.huawei.hms.hihealth.data.DataType) ((param1) == null ? null : (param1.getHInstance()))))");
                com.huawei.hms.hihealth.options.ReadOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.ReadOptions.Builder) this.getHInstance()).polymerize(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))), ((com.huawei.hms.hihealth.data.DataType) ((param1) == null ? null : (param1.getHInstance()))));
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.DataReadRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataReadRequest.Builder) this.getGInstance()).aggregate(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))), ((com.google.android.gms.fitness.data.DataType) ((param1) == null ? null : (param1.getGInstance()))))");
                com.google.android.gms.fitness.request.DataReadRequest.Builder gReturn = ((com.google.android.gms.fitness.request.DataReadRequest.Builder) this.getGInstance()).aggregate(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))), ((com.google.android.gms.fitness.data.DataType) ((param1) == null ? null : (param1.getGInstance()))));
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.DataReadRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.DataReadRequest.Builder bucketByActivitySegment(int param0, java.util.concurrent.TimeUnit param1, org.xms.g.fitness.data.DataSource param2) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                
                com.huawei.hms.hihealth.options.ReadOptions.Builder hReturn = null;
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ReadOptions.Builder) this.getHInstance()).groupByActivitySampleSet(((com.huawei.hms.hihealth.data.DataCollector) ((param2) == null ? null : (param2.getHInstance())))), param0, param1");
                hReturn = ((com.huawei.hms.hihealth.options.ReadOptions.Builder) this.getHInstance()).groupByActivitySampleSet(((com.huawei.hms.hihealth.data.DataCollector) ((param2) == null ? null : (param2.getHInstance()))), param0, param1);
                if (hReturn == null) {
                    org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ReadOptions.Builder) this.getHInstance()).groupByActivitySampleSet(((com.huawei.hms.hihealth.data.DataCollector) ((param2) == null ? null : (param2.getHInstance()))), param0, param1) is null");
                    return null;
                }
                return new org.xms.g.fitness.request.DataReadRequest.Builder(new org.xms.g.utils.XBox(null, hReturn));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataReadRequest.Builder) this.getGInstance()).bucketByActivitySegment(param0, param1, ((com.google.android.gms.fitness.data.DataSource) ((param2) == null ? null : (param2.getGInstance()))))");
                com.google.android.gms.fitness.request.DataReadRequest.Builder gReturn = ((com.google.android.gms.fitness.request.DataReadRequest.Builder) this.getGInstance()).bucketByActivitySegment(param0, param1, ((com.google.android.gms.fitness.data.DataSource) ((param2) == null ? null : (param2.getGInstance()))));
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.DataReadRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.DataReadRequest.Builder bucketByActivitySegment(int param0, java.util.concurrent.TimeUnit param1) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ReadOptions.Builder) this.getHInstance()).groupByActivitySampleSet(param0, param1)");
                com.huawei.hms.hihealth.options.ReadOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.ReadOptions.Builder) this.getHInstance()).groupByActivitySampleSet(param0, param1);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.DataReadRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataReadRequest.Builder) this.getGInstance()).bucketByActivitySegment(param0, param1)");
                com.google.android.gms.fitness.request.DataReadRequest.Builder gReturn = ((com.google.android.gms.fitness.request.DataReadRequest.Builder) this.getGInstance()).bucketByActivitySegment(param0, param1);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.DataReadRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.DataReadRequest.Builder bucketByActivityType(int param0, java.util.concurrent.TimeUnit param1, org.xms.g.fitness.data.DataSource param2) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ReadOptions.Builder) this.getHInstance()).groupByActivityType(((com.huawei.hms.hihealth.data.DataCollector) ((param2) == null ? null : (param2.getHInstance()))), param0, param1)");
                com.huawei.hms.hihealth.options.ReadOptions.Builder hReturn = null;
                hReturn = ((com.huawei.hms.hihealth.options.ReadOptions.Builder) this.getHInstance()).groupByActivityType(((com.huawei.hms.hihealth.data.DataCollector) ((param2) == null ? null : (param2.getHInstance()))), param0, param1);
                if (hReturn == null) {
                    org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ReadOptions.Builder) this.getHInstance()).groupByActivityType(((com.huawei.hms.hihealth.data.DataCollector) ((param2) == null ? null : (param2.getHInstance()))), param0, param1) is null");
                    return null;
                }
                return new org.xms.g.fitness.request.DataReadRequest.Builder(new org.xms.g.utils.XBox(null, hReturn));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataReadRequest.Builder) this.getGInstance()).bucketByActivityType(param0, param1, ((com.google.android.gms.fitness.data.DataSource) ((param2) == null ? null : (param2.getGInstance()))))");
                com.google.android.gms.fitness.request.DataReadRequest.Builder gReturn = ((com.google.android.gms.fitness.request.DataReadRequest.Builder) this.getGInstance()).bucketByActivityType(param0, param1, ((com.google.android.gms.fitness.data.DataSource) ((param2) == null ? null : (param2.getGInstance()))));
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.DataReadRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.DataReadRequest.Builder bucketByActivityType(int param0, java.util.concurrent.TimeUnit param1) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ReadOptions.Builder) this.getHInstance()).groupByActivityType(param0, param1)");
                com.huawei.hms.hihealth.options.ReadOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.ReadOptions.Builder) this.getHInstance()).groupByActivityType(param0, param1);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.DataReadRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataReadRequest.Builder) this.getGInstance()).bucketByActivityType(param0, param1)");
                com.google.android.gms.fitness.request.DataReadRequest.Builder gReturn = ((com.google.android.gms.fitness.request.DataReadRequest.Builder) this.getGInstance()).bucketByActivityType(param0, param1);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.DataReadRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.DataReadRequest.Builder bucketBySession(int param0, java.util.concurrent.TimeUnit param1) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ReadOptions.Builder) this.getHInstance()).groupByActivityRecord(param0, param1)");
                com.huawei.hms.hihealth.options.ReadOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.ReadOptions.Builder) this.getHInstance()).groupByActivityRecord(param0, param1);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.DataReadRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataReadRequest.Builder) this.getGInstance()).bucketBySession(param0, param1)");
                com.google.android.gms.fitness.request.DataReadRequest.Builder gReturn = ((com.google.android.gms.fitness.request.DataReadRequest.Builder) this.getGInstance()).bucketBySession(param0, param1);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.DataReadRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.DataReadRequest.Builder bucketByTime(int param0, java.util.concurrent.TimeUnit param1) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ReadOptions.Builder) this.getHInstance()).groupByTime(param0, param1)");
                com.huawei.hms.hihealth.options.ReadOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.ReadOptions.Builder) this.getHInstance()).groupByTime(param0, param1);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.DataReadRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataReadRequest.Builder) this.getGInstance()).bucketByTime(param0, param1)");
                com.google.android.gms.fitness.request.DataReadRequest.Builder gReturn = ((com.google.android.gms.fitness.request.DataReadRequest.Builder) this.getGInstance()).bucketByTime(param0, param1);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.DataReadRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.DataReadRequest build() {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ReadOptions.Builder) this.getHInstance()).build()");
                com.huawei.hms.hihealth.options.ReadOptions hReturn = ((com.huawei.hms.hihealth.options.ReadOptions.Builder) this.getHInstance()).build();
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.DataReadRequest(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataReadRequest.Builder) this.getGInstance()).build()");
                com.google.android.gms.fitness.request.DataReadRequest gReturn = ((com.google.android.gms.fitness.request.DataReadRequest.Builder) this.getGInstance()).build();
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.DataReadRequest(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.DataReadRequest.Builder enableServerQueries() {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ReadOptions.Builder) this.getHInstance()).allowRemoteInquiry()");
                com.huawei.hms.hihealth.options.ReadOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.ReadOptions.Builder) this.getHInstance()).allowRemoteInquiry();
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.DataReadRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataReadRequest.Builder) this.getGInstance()).enableServerQueries()");
                com.google.android.gms.fitness.request.DataReadRequest.Builder gReturn = ((com.google.android.gms.fitness.request.DataReadRequest.Builder) this.getGInstance()).enableServerQueries();
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.DataReadRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.DataReadRequest.Builder read(org.xms.g.fitness.data.DataSource param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ReadOptions.Builder) this.getHInstance()).read(((com.huawei.hms.hihealth.data.DataCollector) ((param0) == null ? null : (param0.getHInstance()))))");
                com.huawei.hms.hihealth.options.ReadOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.ReadOptions.Builder) this.getHInstance()).read(((com.huawei.hms.hihealth.data.DataCollector) ((param0) == null ? null : (param0.getHInstance()))));
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.DataReadRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataReadRequest.Builder) this.getGInstance()).read(((com.google.android.gms.fitness.data.DataSource) ((param0) == null ? null : (param0.getGInstance()))))");
                com.google.android.gms.fitness.request.DataReadRequest.Builder gReturn = ((com.google.android.gms.fitness.request.DataReadRequest.Builder) this.getGInstance()).read(((com.google.android.gms.fitness.data.DataSource) ((param0) == null ? null : (param0.getGInstance()))));
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.DataReadRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.DataReadRequest.Builder read(org.xms.g.fitness.data.DataType param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ReadOptions.Builder) this.getHInstance()).read(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))))");
                com.huawei.hms.hihealth.options.ReadOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.ReadOptions.Builder) this.getHInstance()).read(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))));
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.DataReadRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataReadRequest.Builder) this.getGInstance()).read(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))))");
                com.google.android.gms.fitness.request.DataReadRequest.Builder gReturn = ((com.google.android.gms.fitness.request.DataReadRequest.Builder) this.getGInstance()).read(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))));
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.DataReadRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.DataReadRequest.Builder setLimit(int param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ReadOptions.Builder) this.getHInstance()).setPageSize(param0)");
                com.huawei.hms.hihealth.options.ReadOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.ReadOptions.Builder) this.getHInstance()).setPageSize(param0);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.DataReadRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataReadRequest.Builder) this.getGInstance()).setLimit(param0)");
                com.google.android.gms.fitness.request.DataReadRequest.Builder gReturn = ((com.google.android.gms.fitness.request.DataReadRequest.Builder) this.getGInstance()).setLimit(param0);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.DataReadRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.request.DataReadRequest.Builder setTimeRange(long param0, long param1, java.util.concurrent.TimeUnit param2) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.ReadOptions.Builder) this.getHInstance()).setTimeRange(param0, param1, param2)");
                com.huawei.hms.hihealth.options.ReadOptions.Builder hReturn = ((com.huawei.hms.hihealth.options.ReadOptions.Builder) this.getHInstance()).setTimeRange(param0, param1, param2);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.request.DataReadRequest.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.DataReadRequest.Builder) this.getGInstance()).setTimeRange(param0, param1, param2)");
                com.google.android.gms.fitness.request.DataReadRequest.Builder gReturn = ((com.google.android.gms.fitness.request.DataReadRequest.Builder) this.getGInstance()).setTimeRange(param0, param1, param2);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.request.DataReadRequest.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public static org.xms.g.fitness.request.DataReadRequest.Builder dynamicCast(java.lang.Object param0) {
            return ((org.xms.g.fitness.request.DataReadRequest.Builder) param0);
        }
        
        public static boolean isInstance(java.lang.Object param0) {
            if (!(param0 instanceof org.xms.g.utils.XGettable)) {
                return false;
            }
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.options.ReadOptions.Builder;
            } else {
                return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.request.DataReadRequest.Builder;
            }
        }
    }
}