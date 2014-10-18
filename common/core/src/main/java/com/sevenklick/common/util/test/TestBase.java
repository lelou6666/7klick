package com.sevenklick.common.util.test;

/**
 * Created by pierre.petersson on 24/06/2014.
 */
public abstract class TestBase {

    public static Long getTenantId() {
        return tenantId;
    }

    public static Long getLogisticsServiceProviderId() {
        return logisticsServiceProviderId;
    }

    public static Long getLoadPlanningNumberSerie(){
        return loadPlanningNumberSerie;
    }

    public static Long getCommonNumberSerie() {
        return commonNumberSerie;
    }

    public static Long getCostAllocationNumberSerie() {
        return costAllocationNumberSerie;
    }

    public static Long getRatesAndTariffsNumberSerie() {
        return ratesAndTariffsNumberSerie;
    }

    public static Long getFileRoutingNumberSerie() {
        return fileRoutingNumberSerie;
    }

    private static final Long commonNumberSerie = 9988888L;
    private static final Long loadPlanningNumberSerie = -7777777L;
    private static final Long costAllocationNumberSerie = -6666666L;
    private static final Long ratesAndTariffsNumberSerie = -5555555L;
    private static final Long fileRoutingNumberSerie = -4444444L;
    private static final Long tenantId = -10L;
    private static final Long logisticsServiceProviderId = -20L;

}
