package edu.uark.uarkregisterapp.models.api.services;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.uark.uarkregisterapp.models.api.Transaction;
import edu.uark.uarkregisterapp.models.api.ProductListing;
import edu.uark.uarkregisterapp.models.api.enums.ApiLevel;
import edu.uark.uarkregisterapp.models.api.enums.TransactionApiMethod;
import edu.uark.uarkregisterapp.models.api.enums.TransactionApiRequestStatus;
import edu.uark.uarkregisterapp.models.api.interfaces.PathElementInterface;

public class TransactionService extends BaseRemoteService {
    public Transaction getProduct(UUID productId) {
        JSONObject rawJsonObject = this.requestSingle(
                (new PathElementInterface[] { TransactionApiMethod.TRANSACTION, ApiLevel.ONE }), productId
        );

        if (rawJsonObject != null) {
            return (new Transaction()).loadFromJson(rawJsonObject);
        } else {
            return new Transaction().setApiRequestStatus(TransactionApiRequestStatus.UNKNOWN_ERROR);
        }
    }

    public Transaction getProductByLookupCode(String transactionRecordID) {
        JSONObject rawJsonObject = this.requestSingle(
                (new PathElementInterface[] { TransactionApiMethod.TRANSACTION, ApiLevel.ONE, TransactionApiMethod.BY_RECORD_ID }), transactionRecordID
        );

        if (rawJsonObject != null) {
            return (new Transaction()).loadFromJson(rawJsonObject);
        } else {
            return new Transaction().setApiRequestStatus(TransactionApiRequestStatus.UNKNOWN_ERROR);
        }
    }

//    public List<Transaction> getProducts() {
//        List<Transaction> activities;
//        JSONObject rawJsonObject = this.requestSingle(
//                (new PathElementInterface[] { TransactionApiMethod.TRANSACTION, ApiLevel.ONE, TransactionApiMethod.TRANSACTIONS })
//        );
//
//        if (rawJsonObject != null) {
//            activities = (new ProductListing()).loadFromJson(rawJsonObject).getProducts();
//        } else {
//            activities = new ArrayList<>(0);
//        }
//
//        return activities;
//    }

    public Transaction putProduct(Transaction product) {
        JSONObject rawJsonObject = this.putSingle(
                (new PathElementInterface[]{ TransactionApiMethod.TRANSACTION, ApiLevel.ONE }), product.convertToJson()
        );

        if (rawJsonObject != null) {
            return (new Transaction()).loadFromJson(rawJsonObject);
        } else {
            return new Transaction().setApiRequestStatus(TransactionApiRequestStatus.UNKNOWN_ERROR);
        }
    }
}