package edu.uark.uarkregisterapp.models.api.fields;


import edu.uark.uarkregisterapp.models.api.interfaces.FieldNameInterface;

/**
 * Created by Quinn on 3/10/17.
 */

public enum EmployeeFieldName implements FieldNameInterface {
    ID("id"),
    EMPLOYEE_ID("employeeid"),
    FIRST_NAME("firstname"),
    LAST_NAME("lastname"),
    PASSWORD("password"),
    ACTIVE("active"),
    CLASSIFICATION("classification"),
    MANAGER_ID("managerid"),
    API_REQUEST_STATUS("apiRequestStatus"),
    API_REQUEST_MESSAGE("apiRequestMessage"),
    CREATED_ON("createdOn");

    private String fieldName;
    public String getFieldName() {

        return this.fieldName;
    }

    EmployeeFieldName(String fieldName) {

        this.fieldName = fieldName;
    }

}
