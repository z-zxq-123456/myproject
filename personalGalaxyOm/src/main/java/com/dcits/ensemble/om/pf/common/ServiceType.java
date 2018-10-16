package com.dcits.ensemble.om.pf.common;

public enum ServiceType {
FINANCIAL, NON_FINANCIAL, INQUIRY, REVERSAL, FILE, SYS, Li01, Li02, Li03, Li04, Li05, IFP, ;

    public static ServiceType getServiceType(String svr) {
        ServiceType type = null;
        switch (svr) {
            case "SVR_FINANCIAL":
            case "Financial":
                type = ServiceType.FINANCIAL;
                break;
            case "SVR_NONFINANCIAL":
            case "Non-Financial":
                type = ServiceType.NON_FINANCIAL;
                break;
            case "SVR_INQUIRY":
            case "Inquiry":
                type = ServiceType.INQUIRY;
                break;
            case "SVR_REVERSAL":
            case "Reversal":
                type = ServiceType.REVERSAL;
                break;
            case "SVR_FILE":
            case "File":
                type = ServiceType.FILE;
                break;
            case "1000":
            case "1220":
            case "1300":
            case "1400":
            case "1200":
                type = ServiceType.IFP;
                break;
            case "APP_HEAD":
            case "LOCAL_HEAD":
            case "SYS_HEAD":
            case "Head":
            case "App":
            case "Local":
                type = ServiceType.SYS;
                break;
            /*default:
                type = ServiceType.valueOf(svr);*/

        }
        return type;
    }
}
