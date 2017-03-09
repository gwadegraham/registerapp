package edu.uark.uarkregisterapp.models.api;

/**
 * Created by GANGSTATOP on 3/9/17.
 */

public class Employee {
/*
    private UUID id;
    public UUID getId() { return this.id; }
    public Employee setId(UUID id) {

        this.id = id;
        return this;
    }

    private String firstName;
    public String getFirstName() { return this.firstName; }
    public Employee setFirstName(String fn) {

        this.firstName = fn;
        return this;
    }

    private String lastName;
    public String getLastName() { return this.lastName; }
    public Employee setLastName(String ln) {

        this.lastName = ln;
        return this;
    }

    private String employeeId;
    public String getEmployeeId() { return this.employeeId; }
    public Employee setEmployeeId(String id) {

        this.employeeId = id;
        return this;
    }

    private Boolean active;
    public Boolean getActive() { return this.active; }
    public Employee setActive(Boolean active) {

        this.active = active;
        return this;
    }

    private String role;
    public String getRole() { return this.role; }
    public Employee setRole(String role) {

        this.role = role;
        return this;
    }

    private UUID managerId;
    public UUID getManagerId() { return this.managerId; }
    public Employee setManagerId(UUID managerId) {

        this.managerId = managerId;
        return this;
    }

    private String password;
    public String getPassword() { return this.password; }
    public Employee setPassword(String password) {

        this.password = password;
        return this;
    }

    private LocalDateTime createdOn;
    public LocalDateTime getCreatedOn() { return this.createdOn; }
    public Employee setCreatedOn(LocalDateTime createdOn) {

        this.createdOn = createdOn;
        return this;
    }

    private EmployeeApiRequestStatus apiRequestStatus;
    public EmployeeApiRequestStatus getApiRequestStatus() {

        return apiRequestStatus;
    }
    public Employee setApiRequestStatus(EmployeeApiRequestStatus apiRequestStatus) {

        if (this.apiRequestStatus != apiRequestStatus) {

            this.apiRequestStatus = apiRequestStatus;
        }

        return this;
    }

    private String apiRequestMessage;
    public String getApiRequestMessage() {

        return this.apiRequestMessage;
    }
    public Employee setApiRequestMessage(String apiRequestMessage) {

        if (!StringUtils.equalsIgnoreCase(this.apiRequestMessage, apiRequestMessage)) {

            this.apiRequestMessage = apiRequestMessage;
        }

        return this;
    }

    public Employee() {

        this.id = new UUID(0, 0);
        this.managerId = new UUID(0, 0);
        this.firstName = "";
        this.lastName = "";
        this.employeeId = "";
        this.role = "";
        this.password = "";
        this.active = false;
        this.createdOn = LocalDateTime.now();

        this.apiRequestMessage = StringUtils.EMPTY;
        this.apiRequestStatus = EmployeeApiRequestStatus.OK;
    }



    public Employee(EmployeeEntity employeeEntity) {

        this.id = employeeEntity.getId();
        this.managerId = employeeEntity.getManagerID();
        this.firstName = employeeEntity.getFirstName();
        this.lastName = employeeEntity.getLastName();
        this.employeeId = employeeEntity.getEmployeeID();
        this.role = employeeEntity.getCurrentRole();
        this.password = employeeEntity.getPassword();
        this.active = employeeEntity.getActive();
        this.createdOn = employeeEntity.getCreatedOn();

        this.apiRequestMessage = StringUtils.EMPTY;
        this.apiRequestStatus = EmployeeApiRequestStatus.OK;
    } */
}
