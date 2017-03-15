package edu.uark.uarkregisterapp.models.transition;

/**
 * Created by garrett on 3/6/17.
 * this class implements the parcelable class, in order to pass information about -
 * - the employee from activity to activity
 */

import android.os.Parcelable;

import android.os.Parcel;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.UUID;

import edu.uark.uarkregisterapp.commands.converters.ByteToUUIDConverterCommand;
import edu.uark.uarkregisterapp.commands.converters.UUIDToByteConverterCommand;
import edu.uark.uarkregisterapp.models.api.Employee;
import edu.uark.uarkregisterapp.models.api.enums.EmployeeClassification;

public class EmployeeTransition implements Parcelable {

    private UUID id;
    public UUID getId() {
        return this.id;
    }

    public EmployeeTransition setid (UUID id) {
        this.id = id;
        return this;
    }

    private String employeeId;
    public String getEmployeeId() {
        return this.employeeId;
    }

    public EmployeeTransition setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    private String firstName;
    public String getFirstName() {
        return this.firstName;
    }

    public EmployeeTransition setFirstName (String firstName) {
        this.firstName = firstName;
        return this;
    }

    private String lastName;
    public String getlastName () {
        return this.lastName;
    }

    public EmployeeTransition setLastName (String lastName) {
        this.lastName = lastName;
        return this;
    }

    private String password;
    //method that returns the Password
    public String getPassword() {
        return this.password;
    }

    public EmployeeTransition setPassword (String password) {
        this.password = password;
        return this;
    }


    private boolean active;
    public boolean getActive() {
        return this.active;
    }

    public EmployeeTransition setActive (boolean active) {
        this.active = active;
        return this;
    }

    private EmployeeClassification classification;
    public EmployeeClassification getClassification () {
        return this.classification;
    }

    public EmployeeTransition setClassification(EmployeeClassification classification) {

        this.classification = classification;
        return this;
    }

    private UUID managerId;
    public UUID getManagerId () {
        return this.managerId;
    }

    public EmployeeTransition setManagerId (UUID managerId) {
        this.managerId = managerId;
        return this;
    }

    private Date createdOn;
    public Date getCreatedOn () {
        return this.createdOn;
    }

    public EmployeeTransition setCreatedOn (Date createdOn) {
        this.createdOn = createdOn;
        return this;
    }


    @Override
    public void writeToParcel(Parcel destination, int flags) {

        destination.writeByteArray((new UUIDToByteConverterCommand()).setValueToConvert(this.id).execute());
        destination.writeString(this.employeeId);
        destination.writeString(this.firstName);
        destination.writeString(this.lastName);
        destination.writeString(this.password);
        destination.writeByteArray((new UUIDToByteConverterCommand()).setValueToConvert(this.managerId).execute());
        destination.writeInt(this.classification.getValue());
        destination.writeLong(this.createdOn.getTime());
        destination.writeInt(this.active ? 1 : 0);
    }


    @Override
    public int describeContents() {

        return 0;
    }



    public static final Parcelable.Creator<EmployeeTransition> CREATOR= new Parcelable.Creator<EmployeeTransition>() {

        @Override
        public EmployeeTransition createFromParcel(Parcel employeeTransitionParcel) {
            return new EmployeeTransition(employeeTransitionParcel);  //using parcelable constructor
        }

        @Override
        public EmployeeTransition[] newArray(int size) {

            return new EmployeeTransition[size];
        }
    };


    public EmployeeTransition () {


        this.id = new UUID(0, 0);
        this.employeeId = StringUtils.EMPTY;
        this.firstName = StringUtils.EMPTY;
        this.lastName = StringUtils.EMPTY;
        this.password = StringUtils.EMPTY;
        this.managerId = new UUID (0, 0);
        this.classification = EmployeeClassification.NOT_DEFINED;
        this.createdOn = new Date();
        this.active = false;
    }

    public EmployeeTransition (Employee employee) {

        this.id = employee.getId();
        this.employeeId = employee.getEmployeeId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.password = employee.getPassword();
        this.managerId = employee.getManagerId();
        this.classification = employee.getClassification();
        this.createdOn = employee.getCreatedOn();
        this.active = employee.getActive();
    }

    public EmployeeTransition (Parcel employeeTransitionParcel) {

        this.id = (new ByteToUUIDConverterCommand()).setValueToConvert(employeeTransitionParcel.createByteArray()).execute();
        this.employeeId = employeeTransitionParcel.readString();
        this.firstName = employeeTransitionParcel.readString();
        this.lastName = employeeTransitionParcel.readString();
        this.password = employeeTransitionParcel.readString();
        this.managerId = (new ByteToUUIDConverterCommand()).setValueToConvert(employeeTransitionParcel.createByteArray()).execute();
        this.classification = EmployeeClassification.mapValue(employeeTransitionParcel.readInt());
        this.createdOn = new Date();
        this.createdOn.setTime(employeeTransitionParcel.readLong());
        this.active = (employeeTransitionParcel.readInt() != 0);
    }

}
