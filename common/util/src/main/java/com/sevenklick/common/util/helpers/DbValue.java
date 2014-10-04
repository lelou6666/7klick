package com.sevenklick.common.util.helpers;

/**
 * Created by lars.vateman on 2014-06-18.
 */
public class DbValue {
    /*
     * YES and NO are stored as Y and N
     */
    public enum YesNo {
        YES("Y"),
        NO("N");

        private String value;

        YesNo(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
    public static final String NO = YesNo.NO.getValue();
    public static final String YES = YesNo.YES.getValue();

    public enum ImportExport {
        IMPORT("IMPORT"),
        EXPORT("EXPORT");

        private String value;

        ImportExport(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
    public static final String EXPORT = ImportExport.EXPORT.getValue();
    public static final String IMPORT = ImportExport.IMPORT.getValue();

    public enum ShipmentLegType {
        PRELIMINARY("PRELIMINARY"),
        DEFINITE("DEFINITE");

        private String value;

        ShipmentLegType(String value) {

            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
    public static final String PRELIMINARY = ShipmentLegType.PRELIMINARY.getValue();
    public static final String DEFINITE = ShipmentLegType.DEFINITE.getValue();

    public enum ConsignmentType {
        PURCHASED_ACTIVITY("SUPPLIER"),
        CUSTOMER_SHIPMENT("CUSTOMER");

        private String value;

        ConsignmentType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
    public static final String PURCHASED_ACTIVITY = ConsignmentType.PURCHASED_ACTIVITY.getValue();
    public static final String CUSTOMER_SHIPMENT = ConsignmentType.CUSTOMER_SHIPMENT.getValue();

    public enum AddressType {
        DELIVERY("DELIVERY"),
        PICKUP("PICKUP"),
        PAYER("PAYER"),
        CONSIGNOR("CONSIGNER"),
        CONSIGNEE("CONSIGNEE");

        private String value;

        AddressType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
    public static final String DELIVERY = AddressType.DELIVERY.getValue();
    public static final String PICKUP = AddressType.PICKUP.getValue();

    public enum PartyQualifier {
        SENDER("SENDER"),
        RECEIVER("RECEIVER");

        private String value;

        PartyQualifier(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
    public static final String SENDER = PartyQualifier.SENDER.getValue();
}
