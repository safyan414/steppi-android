package org.xms.g.fitness.data;

public final class DataSet extends org.xms.g.utils.XObject implements android.os.Parcelable {
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {
        
        public org.xms.g.fitness.data.DataSet createFromParcel(android.os.Parcel param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                com.huawei.hms.hihealth.data.SampleSet hReturn = com.huawei.hms.hihealth.data.SampleSet.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.data.DataSet(new org.xms.g.utils.XBox(null, hReturn));
            } else {
                com.google.android.gms.fitness.data.DataSet gReturn = com.google.android.gms.fitness.data.DataSet.CREATOR.createFromParcel(param0);
                return new org.xms.g.fitness.data.DataSet(new org.xms.g.utils.XBox(gReturn, null));
            }
        }
        
        public org.xms.g.fitness.data.DataSet[] newArray(int param0) {
            return new org.xms.g.fitness.data.DataSet[param0];
        }
    };
    
    public DataSet(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public final void add(org.xms.g.fitness.data.DataPoint param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SampleSet) this.getHInstance()).addSample(((com.huawei.hms.hihealth.data.SamplePoint) ((param0) == null ? null : (param0.getHInstance()))))");
            ((com.huawei.hms.hihealth.data.SampleSet) this.getHInstance()).addSample(((com.huawei.hms.hihealth.data.SamplePoint) ((param0) == null ? null : (param0.getHInstance()))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataSet) this.getGInstance()).add(((com.google.android.gms.fitness.data.DataPoint) ((param0) == null ? null : (param0.getGInstance()))))");
            ((com.google.android.gms.fitness.data.DataSet) this.getGInstance()).add(((com.google.android.gms.fitness.data.DataPoint) ((param0) == null ? null : (param0.getGInstance()))));
        }
    }
    
    public void addAll(java.lang.Iterable<org.xms.g.fitness.data.DataPoint> param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SampleSet) this.getHInstance()).addSampleList(org.xms.g.utils.Utils.transformIterable(param0, e -> org.xms.g.utils.Utils.getInstanceInInterface(e, true)))");
            ((com.huawei.hms.hihealth.data.SampleSet) this.getHInstance()).addSampleList(org.xms.g.utils.Utils.transformIterable(param0, e -> org.xms.g.utils.Utils.getInstanceInInterface(e, true)));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataSet) this.getGInstance()).addAll(org.xms.g.utils.Utils.transformIterable(param0, e -> org.xms.g.utils.Utils.getInstanceInInterface(e, false)))");
            ((com.google.android.gms.fitness.data.DataSet) this.getGInstance()).addAll(org.xms.g.utils.Utils.transformIterable(param0, e -> org.xms.g.utils.Utils.getInstanceInInterface(e, false)));
        }
    }
    
    public static org.xms.g.fitness.data.DataSet.Builder builder(org.xms.g.fitness.data.DataSource param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.SampleSet.builder(((com.huawei.hms.hihealth.data.DataCollector) ((param0) == null ? null : (param0.getHInstance()))))");
            com.huawei.hms.hihealth.data.SampleSet.Builder hReturn = com.huawei.hms.hihealth.data.SampleSet.builder(((com.huawei.hms.hihealth.data.DataCollector) ((param0) == null ? null : (param0.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataSet.Builder(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataSet.builder(((com.google.android.gms.fitness.data.DataSource) ((param0) == null ? null : (param0.getGInstance()))))");
            com.google.android.gms.fitness.data.DataSet.Builder gReturn = com.google.android.gms.fitness.data.DataSet.builder(((com.google.android.gms.fitness.data.DataSource) ((param0) == null ? null : (param0.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataSet.Builder(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public static org.xms.g.fitness.data.DataSet create(org.xms.g.fitness.data.DataSource param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.data.SampleSet.create(((com.huawei.hms.hihealth.data.DataCollector) ((param0) == null ? null : (param0.getHInstance()))))");
            com.huawei.hms.hihealth.data.SampleSet hReturn = com.huawei.hms.hihealth.data.SampleSet.create(((com.huawei.hms.hihealth.data.DataCollector) ((param0) == null ? null : (param0.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataSet(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.data.DataSet.create(((com.google.android.gms.fitness.data.DataSource) ((param0) == null ? null : (param0.getGInstance()))))");
            com.google.android.gms.fitness.data.DataSet gReturn = com.google.android.gms.fitness.data.DataSet.create(((com.google.android.gms.fitness.data.DataSource) ((param0) == null ? null : (param0.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataSet(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public final org.xms.g.fitness.data.DataPoint createDataPoint() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SampleSet) this.getHInstance()).createSamplePoint()");
            com.huawei.hms.hihealth.data.SamplePoint hReturn = ((com.huawei.hms.hihealth.data.SampleSet) this.getHInstance()).createSamplePoint();
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataPoint(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataSet) this.getGInstance()).createDataPoint()");
            com.google.android.gms.fitness.data.DataPoint gReturn = ((com.google.android.gms.fitness.data.DataSet) this.getGInstance()).createDataPoint();
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataPoint(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public final boolean equals(java.lang.Object param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SampleSet) this.getHInstance()).equals(param0)");
            return ((com.huawei.hms.hihealth.data.SampleSet) this.getHInstance()).equals(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataSet) this.getGInstance()).equals(param0)");
            return ((com.google.android.gms.fitness.data.DataSet) this.getGInstance()).equals(param0);
        }
    }
    
    public java.util.List<org.xms.g.fitness.data.DataPoint> getDataPoints() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SampleSet) this.getHInstance()).getSamplePoints()");
            java.util.List hReturn = ((com.huawei.hms.hihealth.data.SampleSet) this.getHInstance()).getSamplePoints();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(hReturn, new org.xms.g.utils.Function<com.huawei.hms.hihealth.data.SamplePoint, org.xms.g.fitness.data.DataPoint>() {
                
                public org.xms.g.fitness.data.DataPoint apply(com.huawei.hms.hihealth.data.SamplePoint param0) {
                    return new org.xms.g.fitness.data.DataPoint(new org.xms.g.utils.XBox(null, param0));
                }
            }));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataSet) this.getGInstance()).getDataPoints()");
            java.util.List gReturn = ((com.google.android.gms.fitness.data.DataSet) this.getGInstance()).getDataPoints();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(gReturn, new org.xms.g.utils.Function<com.google.android.gms.fitness.data.DataPoint, org.xms.g.fitness.data.DataPoint>() {
                
                public org.xms.g.fitness.data.DataPoint apply(com.google.android.gms.fitness.data.DataPoint param0) {
                    return new org.xms.g.fitness.data.DataPoint(new org.xms.g.utils.XBox(param0, null));
                }
            }));
        }
    }
    
    public final org.xms.g.fitness.data.DataSource getDataSource() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SampleSet) this.getHInstance()).getDataCollector()");
            com.huawei.hms.hihealth.data.DataCollector hReturn = ((com.huawei.hms.hihealth.data.SampleSet) this.getHInstance()).getDataCollector();
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataSource(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataSet) this.getGInstance()).getDataSource()");
            com.google.android.gms.fitness.data.DataSource gReturn = ((com.google.android.gms.fitness.data.DataSet) this.getGInstance()).getDataSource();
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataSource(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public final org.xms.g.fitness.data.DataType getDataType() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SampleSet) this.getHInstance()).getDataType()");
            com.huawei.hms.hihealth.data.DataType hReturn = ((com.huawei.hms.hihealth.data.SampleSet) this.getHInstance()).getDataType();
            return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataSet) this.getGInstance()).getDataType()");
            com.google.android.gms.fitness.data.DataType gReturn = ((com.google.android.gms.fitness.data.DataSet) this.getGInstance()).getDataType();
            return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataType(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public final int hashCode() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SampleSet) this.getHInstance()).hashCode()");
            return ((com.huawei.hms.hihealth.data.SampleSet) this.getHInstance()).hashCode();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataSet) this.getGInstance()).hashCode()");
            return ((com.google.android.gms.fitness.data.DataSet) this.getGInstance()).hashCode();
        }
    }
    
    public final boolean isEmpty() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SampleSet) this.getHInstance()).isEmpty()");
            return ((com.huawei.hms.hihealth.data.SampleSet) this.getHInstance()).isEmpty();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataSet) this.getGInstance()).isEmpty()");
            return ((com.google.android.gms.fitness.data.DataSet) this.getGInstance()).isEmpty();
        }
    }
    
    public final java.lang.String toString() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SampleSet) this.getHInstance()).toString()");
            return ((com.huawei.hms.hihealth.data.SampleSet) this.getHInstance()).toString();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataSet) this.getGInstance()).toString()");
            return ((com.google.android.gms.fitness.data.DataSet) this.getGInstance()).toString();
        }
    }
    
    public final void writeToParcel(android.os.Parcel param0, int param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SampleSet) this.getHInstance()).writeToParcel(param0, param1)");
            ((com.huawei.hms.hihealth.data.SampleSet) this.getHInstance()).writeToParcel(param0, param1);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataSet) this.getGInstance()).writeToParcel(param0, param1)");
            ((com.google.android.gms.fitness.data.DataSet) this.getGInstance()).writeToParcel(param0, param1);
        }
    }
    
    public int describeContents() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.data.DataSet dynamicCast(java.lang.Object param0) {
        return ((org.xms.g.fitness.data.DataSet) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.data.SampleSet;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.data.DataSet;
        }
    }
    
    public static class Builder extends org.xms.g.utils.XObject {
        
        public Builder(org.xms.g.utils.XBox param0) {
            super(param0);
        }
        
        public org.xms.g.fitness.data.DataSet.Builder add(org.xms.g.fitness.data.DataPoint param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SampleSet.Builder) this.getHInstance()).addSample(((com.huawei.hms.hihealth.data.SamplePoint) ((param0) == null ? null : (param0.getHInstance()))))");
                com.huawei.hms.hihealth.data.SampleSet.Builder hReturn = ((com.huawei.hms.hihealth.data.SampleSet.Builder) this.getHInstance()).addSample(((com.huawei.hms.hihealth.data.SamplePoint) ((param0) == null ? null : (param0.getHInstance()))));
                return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataSet.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataSet.Builder) this.getGInstance()).add(((com.google.android.gms.fitness.data.DataPoint) ((param0) == null ? null : (param0.getGInstance()))))");
                com.google.android.gms.fitness.data.DataSet.Builder gReturn = ((com.google.android.gms.fitness.data.DataSet.Builder) this.getGInstance()).add(((com.google.android.gms.fitness.data.DataPoint) ((param0) == null ? null : (param0.getGInstance()))));
                return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataSet.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.data.DataSet.Builder addAll(java.lang.Iterable<org.xms.g.fitness.data.DataPoint> param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SampleSet.Builder) this.getHInstance()).addSampleList(org.xms.g.utils.Utils.transformIterable(param0, e -> org.xms.g.utils.Utils.getInstanceInInterface(e, true)))");
                com.huawei.hms.hihealth.data.SampleSet.Builder hReturn = ((com.huawei.hms.hihealth.data.SampleSet.Builder) this.getHInstance()).addSampleList(org.xms.g.utils.Utils.transformIterable(param0, e -> org.xms.g.utils.Utils.getInstanceInInterface(e, true)));
                return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataSet.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataSet.Builder) this.getGInstance()).addAll(org.xms.g.utils.Utils.transformIterable(param0, e -> org.xms.g.utils.Utils.getInstanceInInterface(e, false)))");
                com.google.android.gms.fitness.data.DataSet.Builder gReturn = ((com.google.android.gms.fitness.data.DataSet.Builder) this.getGInstance()).addAll(org.xms.g.utils.Utils.transformIterable(param0, e -> org.xms.g.utils.Utils.getInstanceInInterface(e, false)));
                return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataSet.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public org.xms.g.fitness.data.DataSet build() {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.data.SampleSet.Builder) this.getHInstance()).build()");
                com.huawei.hms.hihealth.data.SampleSet hReturn = ((com.huawei.hms.hihealth.data.SampleSet.Builder) this.getHInstance()).build();
                return ((hReturn) == null ? null : (new org.xms.g.fitness.data.DataSet(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.data.DataSet.Builder) this.getGInstance()).build()");
                com.google.android.gms.fitness.data.DataSet gReturn = ((com.google.android.gms.fitness.data.DataSet.Builder) this.getGInstance()).build();
                return ((gReturn) == null ? null : (new org.xms.g.fitness.data.DataSet(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public static org.xms.g.fitness.data.DataSet.Builder dynamicCast(java.lang.Object param0) {
            return ((org.xms.g.fitness.data.DataSet.Builder) param0);
        }
        
        public static boolean isInstance(java.lang.Object param0) {
            if (!(param0 instanceof org.xms.g.utils.XGettable)) {
                return false;
            }
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.data.SampleSet.Builder;
            } else {
                return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.data.DataSet.Builder;
            }
        }
    }
}