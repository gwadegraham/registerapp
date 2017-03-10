package edu.uark.uarkregisterapp.models.api.fields;


import edu.uark.uarkregisterapp.models.api.interfaces.FieldNameInterface;

/**
 * Created by Quinn on 3/10/17.
 */

public enum EmployeeFieldName implements FieldNameInterface {
    ID("id"),
    FIRSTNAME("firstname"),
    LASTNAME("lastname"),
    EMPLOYEE_ID("employeeid"),
    ACTIVE("active"),
    CURRENT_ROLE("currentrole"),
    MANAGER_ID("managerid"),
    PASSWORD("password"),
    CREATED_ON("createdOn");

    private String fieldName;
    public String getFieldName() {
        return this.fieldName;
    }

    EmployeeFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

}
