package edu.uark.uarkregisterapp.models.api.enums;

import java.util.HashMap;
import java.util.Map;

import edu.uark.uarkregisterapp.models.api.interfaces.PathElementInterface;

/**
 * Created by Quinn Childress on 3/9/17.
 */

public enum EmployeeApiMethod implements PathElementInterface {
    NONE(""),
    EMPLOYEE_ID("employeeId"),
    PASSWORD("password"),
    PRODUCTS("products"),
    CHECK("check");

    @Override
    public String getPathValue() {
        return value;
    }

    public static EmployeeApiMethod map(String key) {
        if (valueMap == null) {
            valueMap = new HashMap<>();

            for (EmployeeApiMethod value : EmployeeApiMethod.values()) {
                valueMap.put(value.getPathValue(), value);
            }
        }

        return (valueMap.containsKey(key) ? valueMap.get(key) : EmployeeApiMethod.NONE);
    }
    private String value;

    private static Map<String, EmployeeApiMethod> valueMap = null;

    EmployeeApiMethod(String value) {
        this.value = value;
    }
}
