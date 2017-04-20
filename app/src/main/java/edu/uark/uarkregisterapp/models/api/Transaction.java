package edu.uark.uarkregisterapp.models.api;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import edu.uark.uarkregisterapp.models.api.enums.TransactionApiRequestStatus;
import edu.uark.uarkregisterapp.models.api.fields.TransactionFieldName;
import edu.uark.uarkregisterapp.models.api.interfaces.ConvertToJsonInterface;
import edu.uark.uarkregisterapp.models.api.interfaces.LoadFromJsonInterface;
//import edu.uark.uarkregisterapp.models.transition.transactionTransition;
import edu.uark.uarkregisterapp.models.transition.TransactionTransition;

public class Transaction implements ConvertToJsonInterface, LoadFromJsonInterface<Transaction> {
    private UUID id;
    public UUID getId() {
        return this.id;
    }
    public Transaction setId(UUID id) {
        this.id = id;
        return this;
    }

    private String recordID;
    public String getRecordID() {
        return this.recordID;
    }
    public Transaction setRecordID(String recordID) {
        this.recordID = recordID;
        return this;
    }

    private String cashierID;
    public String getCashierID() {
        return this.cashierID;
    }
    public Transaction setCashierID(String cashierID) {
        this.cashierID = cashierID;
        return this;
    }

    private int totalPrice;
    public int getTotalPrice() {
        return this.totalPrice;
    }
    public Transaction setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    private String transactionType;
    public String getTransactionType() {
        return this.transactionType;
    }
    public Transaction setTransactionType(String transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    private String referenceID;
    public String getReferenceID() {
        return this.referenceID;
    }
    public Transaction setReferenceID(String referenceID) {
        this.referenceID = referenceID;
        return this;
    }

    private TransactionApiRequestStatus apiRequestStatus;
    public TransactionApiRequestStatus getApiRequestStatus() {
        return this.apiRequestStatus;
    }
    public Transaction setApiRequestStatus(TransactionApiRequestStatus apiRequestStatus) {
        if (this.apiRequestStatus != apiRequestStatus) {
            this.apiRequestStatus = apiRequestStatus;
        }

        return this;
    }

    private String apiRequestMessage;
    public String getApiRequestMessage() {
        return this.apiRequestMessage;
    }
    public Transaction setApiRequestMessage(String apiRequestMessage) {
        if (!StringUtils.equalsIgnoreCase(this.apiRequestMessage, apiRequestMessage)) {
            this.apiRequestMessage = apiRequestMessage;
        }

        return this;
    }

    private Date createdOn;
    public Date getCreatedOn() {
        return this.createdOn;
    }
    public Transaction setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    @Override
    public Transaction loadFromJson(JSONObject rawJsonObject) {
        String value = rawJsonObject.optString(TransactionFieldName.ID.getFieldName());
        if (!StringUtils.isBlank(value)) {
            this.id = UUID.fromString(value);
        }

        this.recordID = rawJsonObject.optString(TransactionFieldName.RECORD_ID.getFieldName());
        this.totalPrice = rawJsonObject.optInt(TransactionFieldName.TOTAL_PRICE.getFieldName());

        value = rawJsonObject.optString(TransactionFieldName.CREATED_ON.getFieldName());
        if (!StringUtils.isBlank(value)) {
            try {
                this.createdOn = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US).parse(value);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        this.apiRequestMessage = rawJsonObject.optString(TransactionFieldName.API_REQUEST_MESSAGE.getFieldName());

        value = rawJsonObject.optString(TransactionFieldName.API_REQUEST_STATUS.getFieldName());
        if (!StringUtils.isBlank(value)) {
            this.apiRequestStatus = TransactionApiRequestStatus.mapName(value);
        }

        return this;
    }

    @Override
    public JSONObject convertToJson() {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put(TransactionFieldName.ID.getFieldName(), this.id.toString());
            jsonObject.put(TransactionFieldName.RECORD_ID.getFieldName(), this.recordID);
            jsonObject.put(TransactionFieldName.CASHIER_ID.getFieldName(), this.cashierID);
            jsonObject.put(TransactionFieldName.TOTAL_PRICE.getFieldName(), this.totalPrice);
            jsonObject.put(TransactionFieldName.TRANSACTION_TYPE.getFieldName(), this.transactionType);
            jsonObject.put(TransactionFieldName.REFERENCE_ID.getFieldName(), this.referenceID);
            jsonObject.put(TransactionFieldName.API_REQUEST_MESSAGE.getFieldName(), this.apiRequestMessage);
            jsonObject.put(TransactionFieldName.API_REQUEST_STATUS.getFieldName(), this.apiRequestStatus.name());
            jsonObject.put(TransactionFieldName.CREATED_ON.getFieldName(), (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US)).format(this.createdOn));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public Transaction() {
        this.totalPrice = 0;
        this.id = new UUID(0, 0);
        this.createdOn = new Date();
        this.recordID = StringUtils.EMPTY;
        this.cashierID = StringUtils.EMPTY;
        this.referenceID = StringUtils.EMPTY;
        this.transactionType = StringUtils.EMPTY;
        this.apiRequestMessage = StringUtils.EMPTY;
        this.apiRequestStatus = TransactionApiRequestStatus.OK;
    }

    public Transaction(TransactionTransition transactionTransition) {
        this.totalPrice = transactionTransition.getTotalPrice();
        this.id = transactionTransition.getId();
        this.createdOn = transactionTransition.getCreatedOn();
        this.recordID = transactionTransition.getRecordID();
        this.cashierID = transactionTransition.getCashierID();
        this.referenceID = transactionTransition.getReferenceID();
        this.transactionType = transactionTransition.getTransactionType();
        this.apiRequestMessage = StringUtils.EMPTY;
        this.apiRequestStatus = TransactionApiRequestStatus.OK;
    }
}
