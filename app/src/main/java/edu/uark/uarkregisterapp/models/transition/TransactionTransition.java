package edu.uark.uarkregisterapp.models.transition;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.LinkedList;
import java.util.UUID;

import edu.uark.uarkregisterapp.commands.converters.ByteToUUIDConverterCommand;
import edu.uark.uarkregisterapp.commands.converters.UUIDToByteConverterCommand;
//import edu.uark.uarkregisterapp.models.api.transaction;
import edu.uark.uarkregisterapp.models.api.Transaction;

public class TransactionTransition implements Parcelable {
    private UUID id;
    public UUID getId() {
        return this.id;
    }
    public TransactionTransition setId(UUID id) {
        this.id = id;
        return this;
    }

    private String recordID;
    public String getRecordID() {
        return this.recordID;
    }
    public TransactionTransition setRecordID(String recordID) {
        this.recordID = recordID;
        return this;
    }

    private String cashierID;
    public String getCashierID() {
        return this.cashierID;
    }
    public TransactionTransition setCashierID(String cashierID) {
        this.cashierID = cashierID;
        return this;
    }

    private int totalPrice;
    public int getTotalPrice() {
        return this.totalPrice;
    }
    public TransactionTransition setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    private String transactionType;
    public String getTransactionType() {
        return this.transactionType;
    }
    public TransactionTransition setTransactionType(String transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    private String referenceID;
    public String getReferenceID() {
        return this.referenceID;
    }
    public TransactionTransition setReferenceID(String referenceID) {
        this.referenceID = referenceID;
        return this;
    }

    private Date createdOn;
    public Date getCreatedOn() {
        return this.createdOn;
    }
    public TransactionTransition setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    //help from Dr. Phillips
    private LinkedList<ProductTransition> products;
    public LinkedList<ProductTransition> getProducts() {
        return this.products;
    }
    public TransactionTransition setProducts(LinkedList<ProductTransition> products) {
        this.products = products;
        return this;
    }
    public TransactionTransition addProduct(ProductTransition product) {
        if (this.products == null) {
            this.products = new LinkedList<>();
        }

        this.products.addLast(product);
        return this;
    }





    @Override
    public void writeToParcel(Parcel destination, int flags) {
        destination.writeByteArray((new UUIDToByteConverterCommand()).setValueToConvert(this.id).execute());
        destination.writeString(this.recordID);
        destination.writeString(this.cashierID);
        destination.writeInt(this.totalPrice);
        destination.writeString(this.transactionType);
        destination.writeString(this.referenceID);
        destination.writeLong(this.createdOn.getTime());

        //help from Dr. Phillips
        destination.writeInt((this.products != null) ? this.products.size() : 0);
        for (ProductTransition productTransition : this.products) {
            destination.writeParcelable(productTransition, flags);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<TransactionTransition> CREATOR = new Parcelable.Creator<TransactionTransition>() {
        public TransactionTransition createFromParcel(Parcel TransactionTransitionParcel) {
            return new TransactionTransition(TransactionTransitionParcel);
        }

        public TransactionTransition[] newArray(int size) {
            return new TransactionTransition[size];
        }
    };

    public TransactionTransition() {
        this.totalPrice = 0;
        this.id = new UUID(0, 0);
        this.createdOn = new Date();
        this.recordID = StringUtils.EMPTY;
        this.cashierID = StringUtils.EMPTY;
        this.referenceID = StringUtils.EMPTY;
        this.transactionType = StringUtils.EMPTY;
        this.products = new LinkedList<>();
    }

    public TransactionTransition(Transaction transaction) {

        this.id = transaction.getId();
        this.createdOn = transaction.getCreatedOn();
        this.recordID = transaction.getRecordID();
        this.cashierID = transaction.getCashierID();
        this.totalPrice = transaction.getTotalPrice();
        this.transactionType = transaction.getTransactionType();
        this.referenceID = transaction.getReferenceID();
        this.products = new LinkedList<>();
    }

    public TransactionTransition(Parcel TransactionTransitionParcel) {

        this.id = (new ByteToUUIDConverterCommand()).setValueToConvert(TransactionTransitionParcel.createByteArray()).execute();
        this.recordID = TransactionTransitionParcel.readString();
        this.cashierID = TransactionTransitionParcel.readString();
        this.totalPrice = TransactionTransitionParcel.readInt();

        this.transactionType = TransactionTransitionParcel.readString();
        this.referenceID = TransactionTransitionParcel.readString();
        this.createdOn = new Date();
        this.createdOn.setTime(TransactionTransitionParcel.readLong());

        //help from Dr. Phillips
        int linkedListSize = TransactionTransitionParcel.readInt();
        this.products = new LinkedList<>();
        for (int i = 0; i < linkedListSize; i++) {
            ProductTransition pt = TransactionTransitionParcel.readParcelable(ProductTransition.class.getClassLoader());
            this.products.addLast(pt);
        }
    }
}
