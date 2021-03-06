package edu.uark.uarkregisterapp.models.transition;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.UUID;

import edu.uark.uarkregisterapp.commands.converters.ByteToUUIDConverterCommand;
import edu.uark.uarkregisterapp.commands.converters.UUIDToByteConverterCommand;
import edu.uark.uarkregisterapp.models.api.Product;

public class ProductTransition implements Parcelable {
	private UUID id;
	public UUID getId() {
		return this.id;
	}
	public ProductTransition setId(UUID id) {
		this.id = id;
		return this;
	}

	private String lookupCode;
	public String getLookupCode() {
		return this.lookupCode;
	}
	public ProductTransition setLookupCode(String lookupCode) {
		this.lookupCode = lookupCode;
		return this;
	}

	private int quantity;
	public int getQuantity() {
		return this.quantity;
	}
	public ProductTransition setQuantity(int quantity) {
		this.quantity = quantity;
		return this;
	}

	private int cost;
	public int getCost() {
		return this.cost;
	}
	public ProductTransition setCost(int cost) {
		this.cost = cost;
		return this;
	}

	private Date createdOn;
	public Date getCreatedOn() {
		return this.createdOn;
	}
	public ProductTransition setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
		return this;
	}

	@Override
	public void writeToParcel(Parcel destination, int flags) {
		destination.writeByteArray((new UUIDToByteConverterCommand()).setValueToConvert(this.id).execute());
		destination.writeString(this.lookupCode);
		destination.writeInt(this.quantity);
		destination.writeLong(this.createdOn.getTime());
		destination.writeInt(this.cost);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Parcelable.Creator<ProductTransition> CREATOR = new Parcelable.Creator<ProductTransition>() {
		public ProductTransition createFromParcel(Parcel productTransitionParcel) {
			return new ProductTransition(productTransitionParcel);
		}

		public ProductTransition[] newArray(int size) {
			return new ProductTransition[size];
		}
	};

	public ProductTransition() {
		this.quantity = -1;
		this.id = new UUID(0, 0);
		this.createdOn = new Date();
		this.lookupCode = StringUtils.EMPTY;
		this.cost = 0;
	}

	public ProductTransition(Product product) {
		this.id = product.getId();
		this.quantity = product.getQuantity();
		this.createdOn = product.getCreatedOn();
		this.lookupCode = product.getLookupCode();
		this.cost = product.getCost();
	}

	public ProductTransition(Parcel productTransitionParcel) {
		this.id = (new ByteToUUIDConverterCommand()).setValueToConvert(productTransitionParcel.createByteArray()).execute();
		this.lookupCode = productTransitionParcel.readString();
		this.quantity = productTransitionParcel.readInt();

		this.createdOn = new Date();
		this.createdOn.setTime(productTransitionParcel.readLong());

		this.cost = productTransitionParcel.readInt();
	}
}
