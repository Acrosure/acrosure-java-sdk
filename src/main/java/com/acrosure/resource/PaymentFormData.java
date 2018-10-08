package com.acrosure.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentFormData {
    private String username;
    private String password;
    private String paymentURL;
    private String version;
    private String merchantID;
    private String paymentDescription;
    private String orderID;
    private String userDefined1;
    private String userDefined2;
    private String userDefined3;
    private String userDefined4;
    private String userDefined5;
    private String amount;
    private String currency;
    private String promotion;
    private String customerEmail;
    private String payCategoryID;
    private String resultURL1;
    private String resultURL2;
    private String paymentOption;
    private String ippInterestType;
    private String paymentExpiry;
    private String defaultLang;
    private String enableStoreCard;
    private String storedCardUniqueID;
    private String request3ds;
    private String recurring;
    private String orderPrefix;
    private String recurringAmount;
    private String allowAccumulate;
    private String maxAccumulateAmount;
    private String recurringInterval;
    private String recurringCount;
    private String chargeNextDate;
    private String chargeOnDate;
    private String statementDescriptor;
    private String useStoredcardOnly;
    private String tokenizeWithoutAuthorization;
    private String productCode;
    private String ippPeriodFilter;
    private String hashValue;

    @JsonCreator
    public PaymentFormData(
            @JsonProperty("username") String username,
            @JsonProperty("password") String password,
            @JsonProperty("payment_url") String paymentURL,
            @JsonProperty("version") String version,
            @JsonProperty("merchant_id") String merchantID,
            @JsonProperty("payment_description") String paymentDescription,
            @JsonProperty("order_id") String orderID,
            @JsonProperty("user_defined_1") String userDefined1,
            @JsonProperty("user_defined_2") String userDefined2,
            @JsonProperty("user_defined_3") String userDefined3,
            @JsonProperty("user_defined_4") String userDefined4,
            @JsonProperty("user_defined_5") String userDefined5,
            @JsonProperty("amount") String amount,
            @JsonProperty("currency") String currency,
            @JsonProperty("promotion") String promotion,
            @JsonProperty("customer_email") String customerEmail,
            @JsonProperty("pay_category_id") String payCategoryID,
            @JsonProperty("result_url_1") String resultURL1,
            @JsonProperty("result_url_2") String resultURL2,
            @JsonProperty("payment_option") String paymentOption,
            @JsonProperty("ipp_interest_type") String ippInterestType,
            @JsonProperty("payment_expiry") String paymentExpiry,
            @JsonProperty("default_lang") String defaultLang,
            @JsonProperty("enable_store_card") String enableStoreCard,
            @JsonProperty("stored_card_unique_id") String storedCardUniqueID,
            @JsonProperty("request_3ds") String request3ds,
            @JsonProperty("recurring") String recurring,
            @JsonProperty("order_prefix") String orderPrefix,
            @JsonProperty("recurring_amount") String recurringAmount,
            @JsonProperty("allow_accumulate") String allowAccumulate,
            @JsonProperty("max_accumulate_amount") String maxAccumulateAmount,
            @JsonProperty("recurring_interval") String recurringInterval,
            @JsonProperty("recurring_count") String recurringCount,
            @JsonProperty("charge_next_date") String chargeNextDate,
            @JsonProperty("charge_on_date") String chargeOnDate,
            @JsonProperty("statement_descriptor") String statementDescriptor,
            @JsonProperty("use_storedcard_only") String useStoredcardOnly,
            @JsonProperty("tokenize_without_authorization") String tokenizeWithoutAuthorization,
            @JsonProperty("product_code") String productCode,
            @JsonProperty("ipp_period_filter") String ippPeriodFilter,
            @JsonProperty("hash_value") String hashValue) {
        this.username = username;
        this.password = password;
        this.paymentURL = paymentURL;
        this.version = version;
        this.merchantID = merchantID;
        this.paymentDescription = paymentDescription;
        this.orderID = orderID;
        this.userDefined1 = userDefined1;
        this.userDefined2 = userDefined2;
        this.userDefined3 = userDefined3;
        this.userDefined4 = userDefined4;
        this.userDefined5 = userDefined5;
        this.amount = amount;
        this.currency = currency;
        this.promotion = promotion;
        this.customerEmail = customerEmail;
        this.payCategoryID = payCategoryID;
        this.resultURL1 = resultURL1;
        this.resultURL2 = resultURL2;
        this.paymentOption = paymentOption;
        this.ippInterestType = ippInterestType;
        this.paymentExpiry = paymentExpiry;
        this.defaultLang = defaultLang;
        this.enableStoreCard = enableStoreCard;
        this.storedCardUniqueID = storedCardUniqueID;
        this.request3ds = request3ds;
        this.recurring = recurring;
        this.orderPrefix = orderPrefix;
        this.recurringAmount = recurringAmount;
        this.allowAccumulate = allowAccumulate;
        this.maxAccumulateAmount = maxAccumulateAmount;
        this.recurringInterval = recurringInterval;
        this.recurringCount = recurringCount;
        this.chargeNextDate = chargeNextDate;
        this.chargeOnDate = chargeOnDate;
        this.statementDescriptor = statementDescriptor;
        this.useStoredcardOnly = useStoredcardOnly;
        this.tokenizeWithoutAuthorization = tokenizeWithoutAuthorization;
        this.productCode = productCode;
        this.ippPeriodFilter = ippPeriodFilter;
        this.hashValue = hashValue;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPaymentURL() {
        return paymentURL;
    }

    public String getVersion() {
        return version;
    }

    public String getMerchantID() {
        return merchantID;
    }

    public String getPaymentDescription() {
        return paymentDescription;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getUserDefined1() {
        return userDefined1;
    }

    public String getUserDefined2() {
        return userDefined2;
    }

    public String getUserDefined3() {
        return userDefined3;
    }

    public String getUserDefined4() {
        return userDefined4;
    }

    public String getUserDefined5() {
        return userDefined5;
    }

    public String getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getPromotion() {
        return promotion;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getPayCategoryID() {
        return payCategoryID;
    }

    public String getResultURL1() {
        return resultURL1;
    }

    public String getResultURL2() {
        return resultURL2;
    }

    public String getPaymentOption() {
        return paymentOption;
    }

    public String getIppInterestType() {
        return ippInterestType;
    }

    public String getPaymentExpiry() {
        return paymentExpiry;
    }

    public String getDefaultLang() {
        return defaultLang;
    }

    public String getEnableStoreCard() {
        return enableStoreCard;
    }

    public String getStoredCardUniqueID() {
        return storedCardUniqueID;
    }

    public String getRequest3ds() {
        return request3ds;
    }

    public String getRecurring() {
        return recurring;
    }

    public String getOrderPrefix() {
        return orderPrefix;
    }

    public String getRecurringAmount() {
        return recurringAmount;
    }

    public String getAllowAccumulate() {
        return allowAccumulate;
    }

    public String getMaxAccumulateAmount() {
        return maxAccumulateAmount;
    }

    public String getRecurringInterval() {
        return recurringInterval;
    }

    public String getRecurringCount() {
        return recurringCount;
    }

    public String getChargeNextDate() {
        return chargeNextDate;
    }

    public String getChargeOnDate() {
        return chargeOnDate;
    }

    public String getStatementDescriptor() {
        return statementDescriptor;
    }

    public String getUseStoredcardOnly() {
        return useStoredcardOnly;
    }

    public String getTokenizeWithoutAuthorization() {
        return tokenizeWithoutAuthorization;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getIppPeriodFilter() {
        return ippPeriodFilter;
    }

    public String getHashValue() {
        return hashValue;
    }

    @Override
    public String toString() {
        return "PaymentFormData{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", paymentURL='" + paymentURL + '\'' +
                ", version='" + version + '\'' +
                ", merchantID='" + merchantID + '\'' +
                ", paymentDescription='" + paymentDescription + '\'' +
                ", orderID='" + orderID + '\'' +
                ", userDefined1='" + userDefined1 + '\'' +
                ", userDefined2='" + userDefined2 + '\'' +
                ", userDefined3='" + userDefined3 + '\'' +
                ", userDefined4='" + userDefined4 + '\'' +
                ", userDefined5='" + userDefined5 + '\'' +
                ", amount='" + amount + '\'' +
                ", currency='" + currency + '\'' +
                ", promotion='" + promotion + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", payCategoryID='" + payCategoryID + '\'' +
                ", resultURL1='" + resultURL1 + '\'' +
                ", resultURL2='" + resultURL2 + '\'' +
                ", paymentOption='" + paymentOption + '\'' +
                ", ippInterestType='" + ippInterestType + '\'' +
                ", paymentExpiry='" + paymentExpiry + '\'' +
                ", defaultLang='" + defaultLang + '\'' +
                ", enableStoreCard='" + enableStoreCard + '\'' +
                ", storedCardUniqueID='" + storedCardUniqueID + '\'' +
                ", request3ds='" + request3ds + '\'' +
                ", recurring='" + recurring + '\'' +
                ", orderPrefix='" + orderPrefix + '\'' +
                ", recurringAmount='" + recurringAmount + '\'' +
                ", allowAccumulate='" + allowAccumulate + '\'' +
                ", maxAccumulateAmount='" + maxAccumulateAmount + '\'' +
                ", recurringInterval='" + recurringInterval + '\'' +
                ", recurringCount='" + recurringCount + '\'' +
                ", chargeNextDate='" + chargeNextDate + '\'' +
                ", chargeOnDate='" + chargeOnDate + '\'' +
                ", statementDescriptor='" + statementDescriptor + '\'' +
                ", useStoredcardOnly='" + useStoredcardOnly + '\'' +
                ", tokenizeWithoutAuthorization='" + tokenizeWithoutAuthorization + '\'' +
                ", productCode='" + productCode + '\'' +
                ", ippPeriodFilter='" + ippPeriodFilter + '\'' +
                ", hashValue='" + hashValue + '\'' +
                '}';
    }
}
