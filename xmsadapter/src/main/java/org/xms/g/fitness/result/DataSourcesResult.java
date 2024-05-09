package org.xms.g.fitness.result;

public class DataSourcesResult extends org.xms.g.utils.XObject implements android.os.Parcelable, org.xms.g.common.api.Result {
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {
        
        public org.xms.g.fitness.result.DataSourcesResult createFromParcel(android.os.Parcel param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                com.huawei.hms.hihealth.result.DataCollectorsResult hReturn = com.huawei.hms.hihealth.result.DataCollectorsResult.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.result.DataSourcesResult(new org.xms.g.utils.XBox(null, hReturn));
            } else {
                com.google.android.gms.fitness.result.DataSourcesResult gReturn = com.google.android.gms.fitness.result.DataSourcesResult.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.result.DataSourcesResult(new org.xms.g.utils.XBox(gReturn, null));
            }
        }
        
        public org.xms.g.fitness.result.DataSourcesResult[] newArray(int param0) {
            return new org.xms.g.fitness.result.DataSourcesResult[param0];
        }
    };
    
    public DataSourcesResult(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public boolean equals(java.lang.Object param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.result.DataCollectorsResult) this.getHInstance()).equals(param0)");
            return ((com.huawei.hms.hihealth.result.DataCollectorsResult) this.getHInstance()).equals(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.result.DataSourcesResult) this.getGInstance()).equals(param0)");
            return ((com.google.android.gms.fitness.result.DataSourcesResult) this.getGInstance()).equals(param0);
        }
    }
    
    public java.util.List<org.xms.g.fitness.data.DataSource> getDataSources() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.result.DataCollectorsResult) this.getHInstance()).getDataCollectors()");
            java.util.List hReturn = ((com.huawei.hms.hihealth.result.DataCollectorsResult) this.getHInstance()).getDataCollectors();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(hReturn, new org.xms.g.utils.Function<com.huawei.hms.hihealth.data.DataCollector, org.xms.g.fitness.data.DataSource>() {
                
                public org.xms.g.fitness.data.DataSource apply(com.huawei.hms.hihealth.data.DataCollector param0) {
                    return new org.xms.g.fitness.data.DataSource(new org.xms.g.utils.XBox(null, param0));
                }
            }));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.result.DataSourcesResult) this.getGInstance()).getDataSources()");
            java.util.List gReturn = ((com.google.android.gms.fitness.result.DataSourcesResult) this.getGInstance()).getDataSources();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(gReturn, new org.xms.g.utils.Function<com.google.android.gms.fitness.data.DataSource, org.xms.g.fitness.data.DataSource>() {
                
                public org.xms.g.fitness.data.DataSource apply(com.google.android.gms.fitness.data.DataSource param0) {
                    return new org.xms.g.fitness.data.DataSource(new org.xms.g.utils.XBox(param0, null));
                }
            }));
        }
    }
    
    public java.util.List<org.xms.g.fitness.data.DataSource> getDataSources(org.xms.g.fitness.data.DataType param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.result.DataCollectorsResult) this.getHInstance()).getDataCollectors(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))))");
            java.util.List hReturn = ((com.huawei.hms.hihealth.result.DataCollectorsResult) this.getHInstance()).getDataCollectors(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))));
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(hReturn, new org.xms.g.utils.Function<com.huawei.hms.hihealth.data.DataCollector, org.xms.g.fitness.data.DataSource>() {
                
                public org.xms.g.fitness.data.DataSource apply(com.huawei.hms.hihealth.data.DataCollector param0) {
                    return new org.xms.g.fitness.data.DataSource(new org.xms.g.utils.XBox(null, param0));
                }
            }));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.result.DataSourcesResult) this.getGInstance()).getDataSources(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))))");
            java.util.List gReturn = ((com.google.android.gms.fitness.result.DataSourcesResult) this.getGInstance()).getDataSources(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))));
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(gReturn, new org.xms.g.utils.Function<com.google.android.gms.fitness.data.DataSource, org.xms.g.fitness.data.DataSource>() {
                
                public org.xms.g.fitness.data.DataSource apply(com.google.android.gms.fitness.data.DataSource param0) {
                    return new org.xms.g.fitness.data.DataSource(new org.xms.g.utils.XBox(param0, null));
                }
            }));
        }
    }
    
    public org.xms.g.common.api.Status getStatus() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.result.DataCollectorsResult) this.getHInstance()).getStatus()");
            com.huawei.hms.support.api.client.Status hReturn = ((com.huawei.hms.hihealth.result.DataCollectorsResult) this.getHInstance()).getStatus();
            return ((hReturn) == null ? null : (new org.xms.g.common.api.Status(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.result.DataSourcesResult) this.getGInstance()).getStatus()");
            com.google.android.gms.common.api.Status gReturn = ((com.google.android.gms.fitness.result.DataSourcesResult) this.getGInstance()).getStatus();
            return ((gReturn) == null ? null : (new org.xms.g.common.api.Status(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public int hashCode() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.result.DataCollectorsResult) this.getHInstance()).hashCode()");
            return ((com.huawei.hms.hihealth.result.DataCollectorsResult) this.getHInstance()).hashCode();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.result.DataSourcesResult) this.getGInstance()).hashCode()");
            return ((com.google.android.gms.fitness.result.DataSourcesResult) this.getGInstance()).hashCode();
        }
    }
    
    public java.lang.String toString() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.result.DataCollectorsResult) this.getHInstance()).toString()");
            return ((com.huawei.hms.hihealth.result.DataCollectorsResult) this.getHInstance()).toString();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.result.DataSourcesResult) this.getGInstance()).toString()");
            return ((com.google.android.gms.fitness.result.DataSourcesResult) this.getGInstance()).toString();
        }
    }
    
    public void writeToParcel(android.os.Parcel param0, int param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.result.DataCollectorsResult) this.getHInstance()).writeToParcel(param0, param1)");
            ((com.huawei.hms.hihealth.result.DataCollectorsResult) this.getHInstance()).writeToParcel(param0, param1);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.result.DataSourcesResult) this.getGInstance()).writeToParcel(param0, param1)");
            ((com.google.android.gms.fitness.result.DataSourcesResult) this.getGInstance()).writeToParcel(param0, param1);
        }
    }
    
    public int describeContents() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.result.DataSourcesResult dynamicCast(java.lang.Object param0) {
        if (param0 instanceof org.xms.g.fitness.result.DataSourcesResult) {
            return ((org.xms.g.fitness.result.DataSourcesResult) param0);
        }
        if (param0 instanceof org.xms.g.utils.XGettable) {
            com.google.android.gms.fitness.result.DataSourcesResult gReturn = ((com.google.android.gms.fitness.result.DataSourcesResult) ((org.xms.g.utils.XGettable) param0).getGInstance());
            com.huawei.hms.hihealth.result.DataCollectorsResult hReturn = ((com.huawei.hms.hihealth.result.DataCollectorsResult) ((org.xms.g.utils.XGettable) param0).getHInstance());
            return new org.xms.g.fitness.result.DataSourcesResult(new org.xms.g.utils.XBox(gReturn, hReturn));
        }
        return ((org.xms.g.fitness.result.DataSourcesResult) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.result.DataCollectorsResult;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.result.DataSourcesResult;
        }
    }
}