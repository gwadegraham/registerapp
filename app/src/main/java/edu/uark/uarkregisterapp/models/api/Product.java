package edu.uark.uarkregisterapp.models.api;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import edu.uark.uarkregisterapp.models.api.enums.ProductApiRequestStatus;
import edu.uark.uarkregisterapp.models.api.fields.ProductFieldName;
import edu.uark.uarkregisterapp.models.api.interfaces.ConvertToJsonInterface;
import edu.uark.uarkregisterapp.models.api.interfaces.LoadFromJsonInterface;
import edu.uark.uarkregisterapp.models.transition.ProductTransition;

public class Product implements ConvertToJsonInterface, LoadFromJsonInterface<Product> {
	private UUID id;
	public UUID getId() {
		return this.id;
	}
	public Product setId(UUID id) {
		this.id = id;
		return this;
	}

	private String lookupCode;
	public String getLookupCode() {
		return this.lookupCode;
	}
	public Product setLookupCode(String lookupCode) {
		this.lookupCode = lookupCode;
		return this;
	}

	private int quantity;
	public int getQuantity() {
		return this.quantity;
	}
	public Product setQuantity(int quantity) {
		this.quantity = quantity;
		return this;
	}

	private Date createdOn;
	public Date getCreatedOn() {
		return this.createdOn;
	}
	public Product setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
		return this;
	}

	private int cost;
	public int getCost() {
		return this.cost;
	}
	public Product setCost(int cost) {
		this.cost = cost;
		return this;
	}

	private ProductApiRequestStatus apiRequestStatus;
	public ProductApiRequestStatus getApiRequestStatus() {
		return this.apiRequestStatus;
	}
	public Product setApiRequestStatus(ProductApiRequestStatus apiRequestStatus) {
		if (this.apiRequestStatus != apiRequestStatus) {
			this.apiRequestStatus = apiRequestStatus;
		}

		return this;
	}

	private String apiRequestMessage;
	public String getApiRequestMessage() {
		return this.apiRequestMessage;
	}
	public Product setApiRequestMessage(String apiRequestMessage) {
		if (!StringUtils.equalsIgnoreCase(this.apiRequestMessage, apiRequestMessage)) {
			this.apiRequestMessage = apiRequestMessage;
		}

		return this;
	}

	@Override
	public Product loadFromJson(JSONObject rawJsonObject) {
		String value = rawJsonObject.optString(ProductFieldName.ID.getFieldName());
		if (!StringUtils.isBlank(value)) {
			this.id = UUID.fromString(value);
		}

		this.lookupCode = rawJsonObject.optString(ProductFieldName.LOOKUP_CODE.getFieldName());
		this.quantity = rawJsonObject.optInt(ProductFieldName.QUANTITY.getFieldName());
		this.cost = rawJsonObject.optInt(ProductFieldName.COST.getFieldName());


		value = rawJsonObject.optString(ProductFieldName.CREATED_ON.getFieldName());
		if (!StringUtils.isBlank(value)) {
			try {
				this.createdOn = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US).parse(value);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		this.apiRequestMessage = rawJsonObject.optString(ProductFieldName.API_REQUEST_MESSAGE.getFieldName());

		value = rawJsonObject.optString(ProductFieldName.API_REQUEST_STATUS.getFieldName());
		if (!StringUtils.isBlank(value)) {
			this.apiRequestStatus = ProductApiRequestStatus.mapName(value);
		}

		return this;
	}

	@Override
	public JSONObject convertToJson() {
		JSONObject jsonObject = new JSONObject();

		try {
			jsonObject.put(ProductFieldName.ID.getFieldName(), this.id.toString());
			jsonObject.put(ProductFieldName.LOOKUP_CODE.getFieldName(), this.lookupCode);
			jsonObject.put(ProductFieldName.QUANTITY.getFieldName(), this.quantity);
			jsonObject.put(ProductFieldName.COST.getFieldName(), this.cost);
			jsonObject.put(ProductFieldName.CREATED_ON.getFieldName(), (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US)).format(this.createdOn));
			jsonObject.put(ProductFieldName.API_REQUEST_MESSAGE.getFieldName(), this.apiRequestMessage);
			jsonObject.put(ProductFieldName.API_REQUEST_STATUS.getFieldName(), this.apiRequestStatus.name());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jsonObject;
	}

	public Product() {
		this.quantity = -1;
		this.lookupCode = "";
		this.id = new UUID(0, 0);
		this.createdOn = new Date();
		this.apiRequestMessage = StringUtils.EMPTY;
		this.apiRequestStatus = ProductApiRequestStatus.OK;
		this.cost = 0;
	}

	public Product(ProductTransition productTransition) {
		this.id = productTransition.getId();
		this.quantity = productTransition.getQuantity();
		this.apiRequestMessage = StringUtils.EMPTY;
		this.createdOn = productTransition.getCreatedOn();
		this.apiRequestStatus = ProductApiRequestStatus.OK;
		this.lookupCode = productTransition.getLookupCode();
		this.cost = productTransition.getCost();
	}
}
