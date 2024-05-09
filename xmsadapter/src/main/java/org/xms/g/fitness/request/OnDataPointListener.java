package org.xms.g.fitness.request;

public interface OnDataPointListener extends org.xms.g.utils.XInterface {
    
    public void onDataPoint(org.xms.g.fitness.data.DataPoint param0);
    
    default java.lang.Object getZInstanceOnDataPointListener() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return getHInstanceOnDataPointListener();
        } else {
            return getGInstanceOnDataPointListener();
        }
    }
    
    default com.google.android.gms.fitness.request.OnDataPointListener getGInstanceOnDataPointListener() {
        if (this instanceof org.xms.g.utils.XGettable) {
            return ((com.google.android.gms.fitness.request.OnDataPointListener) ((org.xms.g.utils.XGettable) this).getGInstance());
        }
        return new com.google.android.gms.fitness.request.OnDataPointListener() {
            
            public void onDataPoint(com.google.android.gms.fitness.data.DataPoint param0) {
                org.xms.g.fitness.request.OnDataPointListener.this.onDataPoint(((param0) == null ? null : (new org.xms.g.fitness.data.DataPoint(new org.xms.g.utils.XBox(param0, null)))));
            }
        };
    }
    
    default com.huawei.hms.hihealth.options.OnSamplePointListener getHInstanceOnDataPointListener() {
        if (this instanceof org.xms.g.utils.XGettable) {
            return ((com.huawei.hms.hihealth.options.OnSamplePointListener) ((org.xms.g.utils.XGettable) this).getHInstance());
        }
        return new com.huawei.hms.hihealth.options.OnSamplePointListener() {
            
            public void onSamplePoint(com.huawei.hms.hihealth.data.SamplePoint param0) {
                org.xms.g.fitness.request.OnDataPointListener.this.onDataPoint(((param0) == null ? null : (new org.xms.g.fitness.data.DataPoint(new org.xms.g.utils.XBox(null, param0)))));
            }
        };
    }
    
    public static org.xms.g.fitness.request.OnDataPointListener dynamicCast(java.lang.Object param0) {
        return ((org.xms.g.fitness.request.OnDataPointListener) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XInterface)) {
            return false;
        }
        if (param0 instanceof org.xms.g.utils.XGettable) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.options.OnSamplePointListener;
            } else {
                return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.request.OnDataPointListener;
            }
        }
        return param0 instanceof org.xms.g.fitness.request.OnDataPointListener;
    }
    
    public static class XImpl extends org.xms.g.utils.XObject implements org.xms.g.fitness.request.OnDataPointListener {
        
        public XImpl(org.xms.g.utils.XBox param0) {
            super(param0);
        }
        
        public void onDataPoint(org.xms.g.fitness.data.DataPoint param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.OnSamplePointListener) this.getHInstance()).onSamplePoint(((com.huawei.hms.hihealth.data.SamplePoint) ((param0) == null ? null : (param0.getHInstance()))))");
                ((com.huawei.hms.hihealth.options.OnSamplePointListener) this.getHInstance()).onSamplePoint(((com.huawei.hms.hihealth.data.SamplePoint) ((param0) == null ? null : (param0.getHInstance()))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.OnDataPointListener) this.getGInstance()).onDataPoint(((com.google.android.gms.fitness.data.DataPoint) ((param0) == null ? null : (param0.getGInstance()))))");
                ((com.google.android.gms.fitness.request.OnDataPointListener) this.getGInstance()).onDataPoint(((com.google.android.gms.fitness.data.DataPoint) ((param0) == null ? null : (param0.getGInstance()))));
            }
        }
    }
}