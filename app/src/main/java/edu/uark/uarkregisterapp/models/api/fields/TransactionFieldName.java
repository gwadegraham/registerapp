package edu.uark.uarkregisterapp.models.api.fields;

import edu.uark.uarkregisterapp.models.api.interfaces.FieldNameInterface;

public enum TransactionFieldName implements FieldNameInterface {
    ID("id"),
    RECORD_ID("recordID"),
    CASHIER_ID("cashierID"),
    TOTAL_PRICE("totalPrice"),
    TRANSACTION_TYPE("transactionType"),
    REFERENCE_ID("referenceID"),
    API_REQUEST_STATUS("apiRequestStatus"),
    API_REQUEST_MESSAGE("apiRequestMessage"),
    CREATED_ON("createdOn");

    private String fieldName;
    public String getFieldName() {
        return this.fieldName;
    }

    TransactionFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
