package org.xms.g.fitness;

public final class FitnessOptions extends org.xms.g.utils.XObject implements org.xms.g.auth.api.signin.ExtensionSignInOptionsExtension {
    
    public FitnessOptions(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public static int getACCESS_READ() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthOptions.ACCESS_READ");
            return com.huawei.hms.hihealth.HiHealthOptions.ACCESS_READ;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessOptions.ACCESS_READ");
            return com.google.android.gms.fitness.FitnessOptions.ACCESS_READ;
        }
    }
    
    public static int getACCESS_WRITE() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthOptions.ACCESS_WRITE");
            return com.huawei.hms.hihealth.HiHealthOptions.ACCESS_WRITE;
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessOptions.ACCESS_WRITE");
            return com.google.android.gms.fitness.FitnessOptions.ACCESS_WRITE;
        }
    }
    
    public static org.xms.g.fitness.FitnessOptions.Builder builder() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.huawei.hms.hihealth.HiHealthOptions.builder()");
            com.huawei.hms.hihealth.HiHealthOptions.Builder hReturn = com.huawei.hms.hihealth.HiHealthOptions.builder();
            return ((hReturn) == null ? null : (new org.xms.g.fitness.FitnessOptions.Builder(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "com.google.android.gms.fitness.FitnessOptions.builder()");
            com.google.android.gms.fitness.FitnessOptions.Builder gReturn = com.google.android.gms.fitness.FitnessOptions.builder();
            return ((gReturn) == null ? null : (new org.xms.g.fitness.FitnessOptions.Builder(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public final boolean equals(java.lang.Object param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.HiHealthOptions) this.getHInstance()).equals(param0)");
            return ((com.huawei.hms.hihealth.HiHealthOptions) this.getHInstance()).equals(param0);
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.FitnessOptions) this.getGInstance()).equals(param0)");
            return ((com.google.android.gms.fitness.FitnessOptions) this.getGInstance()).equals(param0);
        }
    }
    
    public final int getExtensionType() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.HiHealthOptions) this.getHInstance()).getExtendedParamType()");
            return ((com.huawei.hms.hihealth.HiHealthOptions) this.getHInstance()).getExtendedParamType();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.FitnessOptions) this.getGInstance()).getExtensionType()");
            return ((com.google.android.gms.fitness.FitnessOptions) this.getGInstance()).getExtensionType();
        }
    }
    
    public java.util.List<org.xms.g.common.api.Scope> getImpliedScopes() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.HiHealthOptions) this.getHInstance()).getExtendedScopes()");
            java.util.List hReturn = ((com.huawei.hms.hihealth.HiHealthOptions) this.getHInstance()).getExtendedScopes();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(hReturn, new org.xms.g.utils.Function<com.huawei.hms.support.api.entity.auth.Scope, org.xms.g.common.api.Scope>() {
                
                public org.xms.g.common.api.Scope apply(com.huawei.hms.support.api.entity.auth.Scope param0) {
                    return new org.xms.g.common.api.Scope(new org.xms.g.utils.XBox(null, param0));
                }
            }));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.FitnessOptions) this.getGInstance()).getImpliedScopes()");
            java.util.List gReturn = ((com.google.android.gms.fitness.FitnessOptions) this.getGInstance()).getImpliedScopes();
            return ((java.util.List) org.xms.g.utils.Utils.mapCollection(gReturn, new org.xms.g.utils.Function<com.google.android.gms.common.api.Scope, org.xms.g.common.api.Scope>() {
                
                public org.xms.g.common.api.Scope apply(com.google.android.gms.common.api.Scope param0) {
                    return new org.xms.g.common.api.Scope(new org.xms.g.utils.XBox(param0, null));
                }
            }));
        }
    }
    
    public final int hashCode() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.HiHealthOptions) this.getHInstance()).hashCode()");
            return ((com.huawei.hms.hihealth.HiHealthOptions) this.getHInstance()).hashCode();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.FitnessOptions) this.getGInstance()).hashCode()");
            return ((com.google.android.gms.fitness.FitnessOptions) this.getGInstance()).hashCode();
        }
    }
    
    public final android.os.Bundle toBundle() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.HiHealthOptions) this.getHInstance()).getExtendedBundle()");
            return ((com.huawei.hms.hihealth.HiHealthOptions) this.getHInstance()).getExtendedBundle();
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.FitnessOptions) this.getGInstance()).toBundle()");
            return ((com.google.android.gms.fitness.FitnessOptions) this.getGInstance()).toBundle();
        }
    }
    
    public static org.xms.g.fitness.FitnessOptions dynamicCast(java.lang.Object param0) {
        if (param0 instanceof org.xms.g.fitness.FitnessOptions) {
            return ((org.xms.g.fitness.FitnessOptions) param0);
        }
        if (param0 instanceof org.xms.g.utils.XGettable) {
            com.google.android.gms.fitness.FitnessOptions gReturn = ((com.google.android.gms.fitness.FitnessOptions) ((org.xms.g.utils.XGettable) param0).getGInstance());
            com.huawei.hms.hihealth.HiHealthOptions hReturn = ((com.huawei.hms.hihealth.HiHealthOptions) ((org.xms.g.utils.XGettable) param0).getHInstance());
            return new org.xms.g.fitness.FitnessOptions(new org.xms.g.utils.XBox(gReturn, hReturn));
        }
        return ((org.xms.g.fitness.FitnessOptions) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.HiHealthOptions;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.FitnessOptions;
        }
    }
    
    public static final class Builder extends org.xms.g.utils.XObject {
        
        public Builder(org.xms.g.utils.XBox param0) {
            super(param0);
        }
        
        public final org.xms.g.fitness.FitnessOptions.Builder addDataType(org.xms.g.fitness.data.DataType param0, int param1) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.HiHealthOptions.Builder) this.getHInstance()).addDataType(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))), param1)");
                com.huawei.hms.hihealth.HiHealthOptions.Builder hReturn = ((com.huawei.hms.hihealth.HiHealthOptions.Builder) this.getHInstance()).addDataType(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))), param1);
                return ((hReturn) == null ? null : (new org.xms.g.fitness.FitnessOptions.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.FitnessOptions.Builder) this.getGInstance()).addDataType(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))), param1)");
                com.google.android.gms.fitness.FitnessOptions.Builder gReturn = ((com.google.android.gms.fitness.FitnessOptions.Builder) this.getGInstance()).addDataType(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))), param1);
                return ((gReturn) == null ? null : (new org.xms.g.fitness.FitnessOptions.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public final org.xms.g.fitness.FitnessOptions.Builder addDataType(org.xms.g.fitness.data.DataType param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.HiHealthOptions.Builder) this.getHInstance()).addDataType(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))))");
                com.huawei.hms.hihealth.HiHealthOptions.Builder hReturn = ((com.huawei.hms.hihealth.HiHealthOptions.Builder) this.getHInstance()).addDataType(((com.huawei.hms.hihealth.data.DataType) ((param0) == null ? null : (param0.getHInstance()))));
                return ((hReturn) == null ? null : (new org.xms.g.fitness.FitnessOptions.Builder(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.FitnessOptions.Builder) this.getGInstance()).addDataType(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))))");
                com.google.android.gms.fitness.FitnessOptions.Builder gReturn = ((com.google.android.gms.fitness.FitnessOptions.Builder) this.getGInstance()).addDataType(((com.google.android.gms.fitness.data.DataType) ((param0) == null ? null : (param0.getGInstance()))));
                return ((gReturn) == null ? null : (new org.xms.g.fitness.FitnessOptions.Builder(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public final org.xms.g.fitness.FitnessOptions build() {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.HiHealthOptions.Builder) this.getHInstance()).build()");
                com.huawei.hms.hihealth.HiHealthOptions hReturn = ((com.huawei.hms.hihealth.HiHealthOptions.Builder) this.getHInstance()).build();
                return ((hReturn) == null ? null : (new org.xms.g.fitness.FitnessOptions(new org.xms.g.utils.XBox(null, hReturn))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.FitnessOptions.Builder) this.getGInstance()).build()");
                com.google.android.gms.fitness.FitnessOptions gReturn = ((com.google.android.gms.fitness.FitnessOptions.Builder) this.getGInstance()).build();
                return ((gReturn) == null ? null : (new org.xms.g.fitness.FitnessOptions(new org.xms.g.utils.XBox(gReturn, null))));
            }
        }
        
        public static org.xms.g.fitness.FitnessOptions.Builder dynamicCast(java.lang.Object param0) {
            return ((org.xms.g.fitness.FitnessOptions.Builder) param0);
        }
        
        public static boolean isInstance(java.lang.Object param0) {
            if (!(param0 instanceof org.xms.g.utils.XGettable)) {
                return false;
            }
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.HiHealthOptions.Builder;
            } else {
                return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.FitnessOptions.Builder;
            }
        }
    }
}