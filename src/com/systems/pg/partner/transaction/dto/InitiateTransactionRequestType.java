/**
 * InitiateTransactionRequestType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.systems.pg.partner.transaction.dto;

public class InitiateTransactionRequestType  extends com.systems.pg.common.dto.BaseRequestType  implements java.io.Serializable {
    private java.lang.String orderId;

    private long storeId;

    private double transactionAmount;

    private com.systems.pg.partner.transaction.dto.TransactionType transactionType;

    private java.lang.String msisdn;

    private java.lang.String mobileAccountNo;

    private java.lang.String emailAddress;

    public InitiateTransactionRequestType() {
    }

    public InitiateTransactionRequestType(
           java.lang.String username,
           java.lang.String password,
           java.lang.String orderId,
           long storeId,
           double transactionAmount,
           com.systems.pg.partner.transaction.dto.TransactionType transactionType,
           java.lang.String msisdn,
           java.lang.String mobileAccountNo,
           java.lang.String emailAddress) {
        super(
            username,
            password);
        this.orderId = orderId;
        this.storeId = storeId;
        this.transactionAmount = transactionAmount;
        this.transactionType = transactionType;
        this.msisdn = msisdn;
        this.mobileAccountNo = mobileAccountNo;
        this.emailAddress = emailAddress;
    }


    /**
     * Gets the orderId value for this InitiateTransactionRequestType.
     * 
     * @return orderId
     */
    public java.lang.String getOrderId() {
        return orderId;
    }


    /**
     * Sets the orderId value for this InitiateTransactionRequestType.
     * 
     * @param orderId
     */
    public void setOrderId(java.lang.String orderId) {
        this.orderId = orderId;
    }


    /**
     * Gets the storeId value for this InitiateTransactionRequestType.
     * 
     * @return storeId
     */
    public long getStoreId() {
        return storeId;
    }


    /**
     * Sets the storeId value for this InitiateTransactionRequestType.
     * 
     * @param storeId
     */
    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }


    /**
     * Gets the transactionAmount value for this InitiateTransactionRequestType.
     * 
     * @return transactionAmount
     */
    public double getTransactionAmount() {
        return transactionAmount;
    }


    /**
     * Sets the transactionAmount value for this InitiateTransactionRequestType.
     * 
     * @param transactionAmount
     */
    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }


    /**
     * Gets the transactionType value for this InitiateTransactionRequestType.
     * 
     * @return transactionType
     */
    public com.systems.pg.partner.transaction.dto.TransactionType getTransactionType() {
        return transactionType;
    }


    /**
     * Sets the transactionType value for this InitiateTransactionRequestType.
     * 
     * @param transactionType
     */
    public void setTransactionType(com.systems.pg.partner.transaction.dto.TransactionType transactionType) {
        this.transactionType = transactionType;
    }


    /**
     * Gets the msisdn value for this InitiateTransactionRequestType.
     * 
     * @return msisdn
     */
    public java.lang.String getMsisdn() {
        return msisdn;
    }


    /**
     * Sets the msisdn value for this InitiateTransactionRequestType.
     * 
     * @param msisdn
     */
    public void setMsisdn(java.lang.String msisdn) {
        this.msisdn = msisdn;
    }


    /**
     * Gets the mobileAccountNo value for this InitiateTransactionRequestType.
     * 
     * @return mobileAccountNo
     */
    public java.lang.String getMobileAccountNo() {
        return mobileAccountNo;
    }


    /**
     * Sets the mobileAccountNo value for this InitiateTransactionRequestType.
     * 
     * @param mobileAccountNo
     */
    public void setMobileAccountNo(java.lang.String mobileAccountNo) {
        this.mobileAccountNo = mobileAccountNo;
    }


    /**
     * Gets the emailAddress value for this InitiateTransactionRequestType.
     * 
     * @return emailAddress
     */
    public java.lang.String getEmailAddress() {
        return emailAddress;
    }


    /**
     * Sets the emailAddress value for this InitiateTransactionRequestType.
     * 
     * @param emailAddress
     */
    public void setEmailAddress(java.lang.String emailAddress) {
        this.emailAddress = emailAddress;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InitiateTransactionRequestType)) return false;
        InitiateTransactionRequestType other = (InitiateTransactionRequestType) obj;
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
            this.storeId == other.getStoreId() &&
            this.transactionAmount == other.getTransactionAmount() &&
            ((this.transactionType==null && other.getTransactionType()==null) || 
             (this.transactionType!=null &&
              this.transactionType.equals(other.getTransactionType()))) &&
            ((this.msisdn==null && other.getMsisdn()==null) || 
             (this.msisdn!=null &&
              this.msisdn.equals(other.getMsisdn()))) &&
            ((this.mobileAccountNo==null && other.getMobileAccountNo()==null) || 
             (this.mobileAccountNo!=null &&
              this.mobileAccountNo.equals(other.getMobileAccountNo()))) &&
            ((this.emailAddress==null && other.getEmailAddress()==null) || 
             (this.emailAddress!=null &&
              this.emailAddress.equals(other.getEmailAddress())));
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
        _hashCode += new Long(getStoreId()).hashCode();
        _hashCode += new Double(getTransactionAmount()).hashCode();
        if (getTransactionType() != null) {
            _hashCode += getTransactionType().hashCode();
        }
        if (getMsisdn() != null) {
            _hashCode += getMsisdn().hashCode();
        }
        if (getMobileAccountNo() != null) {
            _hashCode += getMobileAccountNo().hashCode();
        }
        if (getEmailAddress() != null) {
            _hashCode += getEmailAddress().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InitiateTransactionRequestType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dto.transaction.partner.pg.systems.com/", ">initiateTransactionRequestType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orderId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "orderId"));
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
        elemField.setFieldName("transactionAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dto.transaction.partner.pg.systems.com/", "TransactionType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msisdn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "msisdn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mobileAccountNo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mobileAccountNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emailAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "emailAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
