package org.xms.g.fitness;

public class SensorsClient extends org.xms.g.common.api.ExtensionApi {
    
    public SensorsClient(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public org.xms.g.tasks.Task<java.lang.Void> add(org.xms.g.fitness.request.SensorRequest param0, org.xms.g.fitness.request.OnDataPointListener param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.SensorsController) this.getHInstance()).register(((com.huawei.hms.hihealth.options.SensorOptions) ((param0) == null ? null : (param0.getHInstance()))), ((param1) == null ? null : (param1.getHInstanceOnDataPointListener())))");
            com.huawei.hmf.tasks.Task hReturn = ((com.huawei.hms.hihealth.SensorsController) this.getHInstance()).register(((com.huawei.hms.hihealth.options.SensorOptions) ((param0) == null ? null : (param0.getHInstance()))), ((param1) == null ? null : (param1.getHInstanceOnDataPointListener())));
            return ((hReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.SensorsClient) this.getGInstance()).add(((com.google.android.gms.fitness.request.SensorRequest) ((param0) == null ? null : (param0.getGInstance()))), ((param1) == null ? null : (param1.getGInstanceOnDataPointListener())))");
            com.google.android.gms.tasks.Task gReturn = ((com.google.android.gms.fitness.SensorsClient) this.getGInstance()).add(((com.google.android.gms.fitness.request.SensorRequest) ((param0) == null ? null : (param0.getGInstance()))), ((param1) == null ? null : (param1.getGInstanceOnDataPointListener())));
            return ((gReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.tasks.Task<java.lang.Void> add(org.xms.g.fitness.request.SensorRequest param0, android.app.PendingIntent param1) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.SensorsController) this.getHInstance()).register(((com.huawei.hms.hihealth.options.SensorOptions) ((param0) == null ? null : (param0.getHInstance()))), param1)");
            com.huawei.hmf.tasks.Task hReturn = ((com.huawei.hms.hihealth.SensorsController) this.getHInstance()).register(((com.huawei.hms.hihealth.options.SensorOptions) ((param0) == null ? null : (param0.getHInstance()))), param1);
            return ((hReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.SensorsClient) this.getGInstance()).add(((com.google.android.gms.fitness.request.SensorRequest) ((param0) == null ? null : (param0.getGInstance()))), param1)");
            com.google.android.gms.tasks.Task gReturn = ((com.google.android.gms.fitness.SensorsClient) this.getGInstance()).add(((com.google.android.gms.fitness.request.SensorRequest) ((param0) == null ? null : (param0.getGInstance()))), param1);
            return ((gReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.tasks.Task<java.util.List<org.xms.g.fitness.data.DataSource>> findDataSources(org.xms.g.fitness.request.DataSourcesRequest param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.SensorsController) this.getHInstance()).getDataCollectors(((com.huawei.hms.hihealth.options.DataCollectorsOptions) ((param0) == null ? null : (param0.getHInstance()))))");
            com.huawei.hmf.tasks.Task hReturn = ((com.huawei.hms.hihealth.SensorsController) this.getHInstance()).getDataCollectors(((com.huawei.hms.hihealth.options.DataCollectorsOptions) ((param0) == null ? null : (param0.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.SensorsClient) this.getGInstance()).findDataSources(((com.google.android.gms.fitness.request.DataSourcesRequest) ((param0) == null ? null : (param0.getGInstance()))))");
            com.google.android.gms.tasks.Task gReturn = ((com.google.android.gms.fitness.SensorsClient) this.getGInstance()).findDataSources(((com.google.android.gms.fitness.request.DataSourcesRequest) ((param0) == null ? null : (param0.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.tasks.Task<java.lang.Void> remove(android.app.PendingIntent param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.SensorsController) this.getHInstance()).unregister(param0)");
            com.huawei.hmf.tasks.Task hReturn = ((com.huawei.hms.hihealth.SensorsController) this.getHInstance()).unregister(param0);
            return ((hReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.SensorsClient) this.getGInstance()).remove(param0)");
            com.google.android.gms.tasks.Task gReturn = ((com.google.android.gms.fitness.SensorsClient) this.getGInstance()).remove(param0);
            return ((gReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.tasks.Task<java.lang.Boolean> remove(org.xms.g.fitness.request.OnDataPointListener param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.SensorsController) this.getHInstance()).unregister(((param0) == null ? null : (param0.getHInstanceOnDataPointListener())))");
            com.huawei.hmf.tasks.Task hReturn = ((com.huawei.hms.hihealth.SensorsController) this.getHInstance()).unregister(((param0) == null ? null : (param0.getHInstanceOnDataPointListener())));
            return ((hReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.SensorsClient) this.getGInstance()).remove(((param0) == null ? null : (param0.getGInstanceOnDataPointListener())))");
            com.google.android.gms.tasks.Task gReturn = ((com.google.android.gms.fitness.SensorsClient) this.getGInstance()).remove(((param0) == null ? null : (param0.getGInstanceOnDataPointListener())));
            return ((gReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public java.lang.Object getApiKey() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.SensorsClient dynamicCast(java.lang.Object param0) {
        if (param0 instanceof org.xms.g.fitness.SensorsClient) {
            return ((org.xms.g.fitness.SensorsClient) param0);
        }
        if (param0 instanceof org.xms.g.utils.XGettable) {
            com.google.android.gms.fitness.SensorsClient gReturn = ((com.google.android.gms.fitness.SensorsClient) ((org.xms.g.utils.XGettable) param0).getGInstance());
            com.huawei.hms.hihealth.SensorsController hReturn = ((com.huawei.hms.hihealth.SensorsController) ((org.xms.g.utils.XGettable) param0).getHInstance());
            return new org.xms.g.fitness.SensorsClient(new org.xms.g.utils.XBox(gReturn, hReturn));
        }
        return ((org.xms.g.fitness.SensorsClient) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.SensorsController;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.SensorsClient;
        }
    }
}