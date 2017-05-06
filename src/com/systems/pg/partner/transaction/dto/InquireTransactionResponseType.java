/**
 * InquireTransactionResponseType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.systems.pg.partner.transaction.dto;

public class InquireTransactionResponseType  extends com.systems.pg.common.dto.BaseResponseType  implements java.io.Serializable {
    private java.lang.String orderId;

    private java.lang.String accountNum;

    private long storeId;

    private java.lang.String storeName;

    private java.lang.String paymentToken;

    private java.lang.String transactionId;

    private java.lang.String transactionStatus;

    private double transactionAmount;

    private java.util.Calendar transactionDateTime;

    private java.util.Calendar paymentTokenExiryDateTime;

    private java.util.Calendar transactionPaidDateTime;

    private java.lang.String msisdn;

    private com.systems.pg.partner.transaction.dto.TransactionType paymentMode;

    public InquireTransactionResponseType() {
    }

    public InquireTransactionResponseType(
           java.lang.String responseCode,
           java.lang.String orderId,
           java.lang.String accountNum,
           long storeId,
           java.lang.String storeName,
           java.lang.String paymentToken,
           java.lang.String transactionId,
           java.lang.String transactionStatus,
           double transactionAmount,
           java.util.Calendar transactionDateTime,
           java.util.Calendar paymentTokenExiryDateTime,
           java.util.Calendar transactionPaidDateTime,
           java.lang.String msisdn,
           com.systems.pg.partner.transaction.dto.TransactionType paymentMode) {
        super(
            responseCode);
        this.orderId = orderId;
        this.accountNum = accountNum;
        this.storeId = storeId;
        this.storeName = storeName;
        this.paymentToken = paymentToken;
        this.transactionId = transactionId;
        this.transactionStatus = transactionStatus;
        this.transactionAmount = transactionAmount;
        this.transactionDateTime = transactionDateTime;
        this.paymentTokenExiryDateTime = paymentTokenExiryDateTime;
        this.transactionPaidDateTime = transactionPaidDateTime;
        this.msisdn = msisdn;
        this.paymentMode = paymentMode;
    }


    /**
     * Gets the orderId value for this InquireTransactionResponseType.
     * 
     * @return orderId
     */
    public java.lang.String getOrderId() {
        return orderId;
    }


    /**
     * Sets the orderId value for this InquireTransactionResponseType.
     * 
     * @param orderId
     */
    public void setOrderId(java.lang.String orderId) {
        this.orderId = orderId;
    }


    /**
     * Gets the accountNum value for this InquireTransactionResponseType.
     * 
     * @return accountNum
     */
    public java.lang.String getAccountNum() {
        return accountNum;
    }


    /**
     * Sets the accountNum value for this InquireTransactionResponseType.
     * 
     * @param accountNum
     */
    public void setAccountNum(java.lang.String accountNum) {
        this.accountNum = accountNum;
    }


    /**
     * Gets the storeId value for this InquireTransactionResponseType.
     * 
     * @return storeId
     */
    public long getStoreId() {
        return storeId;
    }


    /**
     * Sets the storeId value for this InquireTransactionResponseType.
     * 
     * @param storeId
     */
    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }


    /**
     * Gets the storeName value for this InquireTransactionResponseType.
     * 
     * @return storeName
     */
    public java.lang.String getStoreName() {
        return storeName;
    }


    /**
     * Sets the storeName value for this InquireTransactionResponseType.
     * 
     * @param storeName
     */
    public void setStoreName(java.lang.String storeName) {
        this.storeName = storeName;
    }


    /**
     * Gets the paymentToken value for this InquireTransactionResponseType.
     * 
     * @return paymentToken
     */
    public java.lang.String getPaymentToken() {
        return paymentToken;
    }


    /**
     * Sets the paymentToken value for this InquireTransactionResponseType.
     * 
     * @param paymentToken
     */
    public void setPaymentToken(java.lang.String paymentToken) {
        this.paymentToken = paymentToken;
    }


    /**
     * Gets the transactionId value for this InquireTransactionResponseType.
     * 
     * @return transactionId
     */
    public java.lang.String getTransactionId() {
        return transactionId;
    }


    /**
     * Sets the transactionId value for this InquireTransactionResponseType.
     * 
     * @param transactionId
     */
    public void setTransactionId(java.lang.String transactionId) {
        this.transactionId = transactionId;
    }


    /**
     * Gets the transactionStatus value for this InquireTransactionResponseType.
     * 
     * @return transactionStatus
     */
    public java.lang.String getTransactionStatus() {
        return transactionStatus;
    }


    /**
     * Sets the transactionStatus value for this InquireTransactionResponseType.
     * 
     * @param transactionStatus
     */
    public void setTransactionStatus(java.lang.String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }


    /**
     * Gets the transactionAmount value for this InquireTransactionResponseType.
     * 
     * @return transactionAmount
     */
    public double getTransactionAmount() {
        return transactionAmount;
    }


    /**
     * Sets the transactionAmount value for this InquireTransactionResponseType.
     * 
     * @param transactionAmount
     */
    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }


    /**
     * Gets the transactionDateTime value for this InquireTransactionResponseType.
     * 
     * @return transactionDateTime
     */
    public java.util.Calendar getTransactionDateTime() {
        return transactionDateTime;
    }


    /**
     * Sets the transactionDateTime value for this InquireTransactionResponseType.
     * 
     * @param transactionDateTime
     */
    public void setTransactionDateTime(java.util.Calendar transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }


    /**
     * Gets the paymentTokenExiryDateTime value for this InquireTransactionResponseType.
     * 
     * @return paymentTokenExiryDateTime
     */
    public java.util.Calendar getPaymentTokenExiryDateTime() {
        return paymentTokenExiryDateTime;
    }


    /**
     * Sets the paymentTokenExiryDateTime value for this InquireTransactionResponseType.
     * 
     * @param paymentTokenExiryDateTime
     */
    public void setPaymentTokenExiryDateTime(java.util.Calendar paymentTokenExiryDateTime) {
        this.paymentTokenExiryDateTime = paymentTokenExiryDateTime;
    }


    /**
     * Gets the transactionPaidDateTime value for this InquireTransactionResponseType.
     * 
     * @return transactionPaidDateTime
     */
    public java.util.Calendar getTransactionPaidDateTime() {
        return transactionPaidDateTime;
    }


    /**
     * Sets the transactionPaidDateTime value for this InquireTransactionResponseType.
     * 
     * @param transactionPaidDateTime
     */
    public void setTransactionPaidDateTime(java.util.Calendar transactionPaidDateTime) {
        this.transactionPaidDateTime = transactionPaidDateTime;
    }


    /**
     * Gets the msisdn value for this InquireTransactionResponseType.
     * 
     * @return msisdn
     */
    public java.lang.String getMsisdn() {
        return msisdn;
    }


    /**
     * Sets the msisdn value for this InquireTransactionResponseType.
     * 
     * @param msisdn
     */
    public void setMsisdn(java.lang.String msisdn) {
        this.msisdn = msisdn;
    }


    /**
     * Gets the paymentMode value for this InquireTransactionResponseType.
     * 
     * @return paymentMode
     */
    public com.systems.pg.partner.transaction.dto.TransactionType getPaymentMode() {
        return paymentMode;
    }


    /**
     * Sets the paymentMode value for this InquireTransactionResponseType.
     * 
     * @param paymentMode
     */
    public void setPaymentMode(com.systems.pg.partner.transaction.dto.TransactionType paymentMode) {
        this.paymentMode = paymentMode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InquireTransactionResponseType)) return false;
        InquireTransactionResponseType other = (InquireTransactionResponseType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.orderId==null && other.getOrderId()==null) || 
             (this.orderId!=null &&
              this.orderId.equals(other.getOrderId()))) &&
            ((this.accountNum==null && other.getAccountNum()==null) || 
             (this.accountNum!=null &&
              this.accountNum.equals(other.getAccountNum()))) &&
            this.storeId == other.getStoreId() &&
            ((this.storeName==null && other.getStoreName()==null) || 
             (this.storeName!=null &&
              this.storeName.equals(other.getStoreName()))) &&
            ((this.paymentToken==null && other.getPaymentToken()==null) || 
             (this.paymentToken!=null &&
              this.paymentToken.equals(other.getPaymentToken()))) &&
            ((this.transactionId==null && other.getTransactionId()==null) || 
             (this.transactionId!=null &&
              this.transactionId.equals(other.getTransactionId()))) &&
            ((this.transactionStatus==null && other.getTransactionStatus()==null) || 
             (this.transactionStatus!=null &&
              this.transactionStatus.equals(other.getTransactionStatus()))) &&
            this.transactionAmount == other.getTransactionAmount() &&
            ((this.transactionDateTime==null && other.getTransactionDateTime()==null) || 
             (this.transactionDateTime!=null &&
              this.transactionDateTime.equals(other.getTransactionDateTime()))) &&
            ((this.paymentTokenExiryDateTime==null && other.getPaymentTokenExiryDateTime()==null) || 
             (this.paymentTokenExiryDateTime!=null &&
              this.paymentTokenExiryDateTime.equals(other.getPaymentTokenExiryDateTime()))) &&
            ((this.transactionPaidDateTime==null && other.getTransactionPaidDateTime()==null) || 
             (this.transactionPaidDateTime!=null &&
              this.transactionPaidDateTime.equals(other.getTransactionPaidDateTime()))) &&
            ((this.msisdn==null && other.getMsisdn()==null) || 
             (this.msisdn!=null &&
              this.msisdn.equals(other.getMsisdn()))) &&
            ((this.paymentMode==null && other.getPaymentMode()==null) || 
             (this.paymentMode!=null &&
              this.paymentMode.equals(other.getPaymentMode())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getOrderId() != null) {
            _hashCode += getOrderId().hashCode();
        }
        if (getAccountNum() != null) {
            _hashCode += getAccountNum().hashCode();
        }
        _hashCode += new Long(getStoreId()).hashCode();
        if (getStoreName() != null) {
            _hashCode += getStoreName().hashCode();
        }
        if (getPaymentToken() != null) {
            _hashCode += getPaymentToken().hashCode();
        }
        if (getTransactionId() != null) {
            _hashCode += getTransactionId().hashCode();
        }
        if (getTransactionStatus() != null) {
            _hashCode += getTransactionStatus().hashCode();
        }
        _hashCode += new Double(getTransactionAmount()).hashCode();
        if (getTransactionDateTime() != null) {
            _hashCode += getTransactionDateTime().hashCode();
        }
        if (getPaymentTokenExiryDateTime() != null) {
            _hashCode += getPaymentTokenExiryDateTime().hashCode();
        }
        if (getTransactionPaidDateTime() != null) {
            _hashCode += getTransactionPaidDateTime().hashCode();
        }
        if (getMsisdn() != null) {
            _hashCode += getMsisdn().hashCode();
        }
        if (getPaymentMode() != null) {
            _hashCode += getPaymentMode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InquireTransactionResponseType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dto.transaction.partner.pg.systems.com/", ">inquireTransactionResponseType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orderId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "orderId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountNum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("storeId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "storeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("storeName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "storeName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentToken");
        elemField.setXmlName(new javax.xml.namespace.QName("", "paymentToken"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionDateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionDateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentTokenExiryDateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "paymentTokenExiryDateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionPaidDateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionPaidDateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msisdn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "msisdn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentMode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "paymentMode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dto.transaction.partner.pg.systems.com/", "TransactionType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
