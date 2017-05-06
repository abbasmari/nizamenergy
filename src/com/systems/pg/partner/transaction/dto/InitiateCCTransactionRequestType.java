/**
 * InitiateCCTransactionRequestType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.systems.pg.partner.transaction.dto;

public class InitiateCCTransactionRequestType  extends com.systems.pg.common.dto.BaseRequestType  implements java.io.Serializable {
    private java.lang.String orderId;

    private long storeId;

    private double transactionAmount;

    private com.systems.pg.partner.transaction.dto.TransactionType transactionType;

    private java.lang.String msisdn;

    private java.lang.String emailAddress;

    private java.lang.String cardType;

    private java.lang.String pan;

    private java.lang.String expiryYear;

    private java.lang.String expiryMonth;

    private java.lang.String cvv2;

    public InitiateCCTransactionRequestType() {
    }

    public InitiateCCTransactionRequestType(
           java.lang.String username,
           java.lang.String password,
           java.lang.String orderId,
           long storeId,
           double transactionAmount,
           com.systems.pg.partner.transaction.dto.TransactionType transactionType,
           java.lang.String msisdn,
           java.lang.String emailAddress,
           java.lang.String cardType,
           java.lang.String pan,
           java.lang.String expiryYear,
           java.lang.String expiryMonth,
           java.lang.String cvv2) {
        super(
            username,
            password);
        this.orderId = orderId;
        this.storeId = storeId;
        this.transactionAmount = transactionAmount;
        this.transactionType = transactionType;
        this.msisdn = msisdn;
        this.emailAddress = emailAddress;
        this.cardType = cardType;
        this.pan = pan;
        this.expiryYear = expiryYear;
        this.expiryMonth = expiryMonth;
        this.cvv2 = cvv2;
    }


    /**
     * Gets the orderId value for this InitiateCCTransactionRequestType.
     * 
     * @return orderId
     */
    public java.lang.String getOrderId() {
        return orderId;
    }


    /**
     * Sets the orderId value for this InitiateCCTransactionRequestType.
     * 
     * @param orderId
     */
    public void setOrderId(java.lang.String orderId) {
        this.orderId = orderId;
    }


    /**
     * Gets the storeId value for this InitiateCCTransactionRequestType.
     * 
     * @return storeId
     */
    public long getStoreId() {
        return storeId;
    }


    /**
     * Sets the storeId value for this InitiateCCTransactionRequestType.
     * 
     * @param storeId
     */
    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }


    /**
     * Gets the transactionAmount value for this InitiateCCTransactionRequestType.
     * 
     * @return transactionAmount
     */
    public double getTransactionAmount() {
        return transactionAmount;
    }


    /**
     * Sets the transactionAmount value for this InitiateCCTransactionRequestType.
     * 
     * @param transactionAmount
     */
    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }


    /**
     * Gets the transactionType value for this InitiateCCTransactionRequestType.
     * 
     * @return transactionType
     */
    public com.systems.pg.partner.transaction.dto.TransactionType getTransactionType() {
        return transactionType;
    }


    /**
     * Sets the transactionType value for this InitiateCCTransactionRequestType.
     * 
     * @param transactionType
     */
    public void setTransactionType(com.systems.pg.partner.transaction.dto.TransactionType transactionType) {
        this.transactionType = transactionType;
    }


    /**
     * Gets the msisdn value for this InitiateCCTransactionRequestType.
     * 
     * @return msisdn
     */
    public java.lang.String getMsisdn() {
        return msisdn;
    }


    /**
     * Sets the msisdn value for this InitiateCCTransactionRequestType.
     * 
     * @param msisdn
     */
    public void setMsisdn(java.lang.String msisdn) {
        this.msisdn = msisdn;
    }


    /**
     * Gets the emailAddress value for this InitiateCCTransactionRequestType.
     * 
     * @return emailAddress
     */
    public java.lang.String getEmailAddress() {
        return emailAddress;
    }


    /**
     * Sets the emailAddress value for this InitiateCCTransactionRequestType.
     * 
     * @param emailAddress
     */
    public void setEmailAddress(java.lang.String emailAddress) {
        this.emailAddress = emailAddress;
    }


    /**
     * Gets the cardType value for this InitiateCCTransactionRequestType.
     * 
     * @return cardType
     */
    public java.lang.String getCardType() {
        return cardType;
    }


    /**
     * Sets the cardType value for this InitiateCCTransactionRequestType.
     * 
     * @param cardType
     */
    public void setCardType(java.lang.String cardType) {
        this.cardType = cardType;
    }


    /**
     * Gets the pan value for this InitiateCCTransactionRequestType.
     * 
     * @return pan
     */
    public java.lang.String getPan() {
        return pan;
    }


    /**
     * Sets the pan value for this InitiateCCTransactionRequestType.
     * 
     * @param pan
     */
    public void setPan(java.lang.String pan) {
        this.pan = pan;
    }


    /**
     * Gets the expiryYear value for this InitiateCCTransactionRequestType.
     * 
     * @return expiryYear
     */
    public java.lang.String getExpiryYear() {
        return expiryYear;
    }


    /**
     * Sets the expiryYear value for this InitiateCCTransactionRequestType.
     * 
     * @param expiryYear
     */
    public void setExpiryYear(java.lang.String expiryYear) {
        this.expiryYear = expiryYear;
    }


    /**
     * Gets the expiryMonth value for this InitiateCCTransactionRequestType.
     * 
     * @return expiryMonth
     */
    public java.lang.String getExpiryMonth() {
        return expiryMonth;
    }


    /**
     * Sets the expiryMonth value for this InitiateCCTransactionRequestType.
     * 
     * @param expiryMonth
     */
    public void setExpiryMonth(java.lang.String expiryMonth) {
        this.expiryMonth = expiryMonth;
    }


    /**
     * Gets the cvv2 value for this InitiateCCTransactionRequestType.
     * 
     * @return cvv2
     */
    public java.lang.String getCvv2() {
        return cvv2;
    }


    /**
     * Sets the cvv2 value for this InitiateCCTransactionRequestType.
     * 
     * @param cvv2
     */
    public void setCvv2(java.lang.String cvv2) {
        this.cvv2 = cvv2;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InitiateCCTransactionRequestType)) return false;
        InitiateCCTransactionRequestType other = (InitiateCCTransactionRequestType) obj;
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
            ((this.emailAddress==null && other.getEmailAddress()==null) || 
             (this.emailAddress!=null &&
              this.emailAddress.equals(other.getEmailAddress()))) &&
            ((this.cardType==null && other.getCardType()==null) || 
             (this.cardType!=null &&
              this.cardType.equals(other.getCardType()))) &&
            ((this.pan==null && other.getPan()==null) || 
             (this.pan!=null &&
              this.pan.equals(other.getPan()))) &&
            ((this.expiryYear==null && other.getExpiryYear()==null) || 
             (this.expiryYear!=null &&
              this.expiryYear.equals(other.getExpiryYear()))) &&
            ((this.expiryMonth==null && other.getExpiryMonth()==null) || 
             (this.expiryMonth!=null &&
              this.expiryMonth.equals(other.getExpiryMonth()))) &&
            ((this.cvv2==null && other.getCvv2()==null) || 
             (this.cvv2!=null &&
              this.cvv2.equals(other.getCvv2())));
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
        if (getEmailAddress() != null) {
            _hashCode += getEmailAddress().hashCode();
        }
        if (getCardType() != null) {
            _hashCode += getCardType().hashCode();
        }
        if (getPan() != null) {
            _hashCode += getPan().hashCode();
        }
        if (getExpiryYear() != null) {
            _hashCode += getExpiryYear().hashCode();
        }
        if (getExpiryMonth() != null) {
            _hashCode += getExpiryMonth().hashCode();
        }
        if (getCvv2() != null) {
            _hashCode += getCvv2().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InitiateCCTransactionRequestType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dto.transaction.partner.pg.systems.com/", ">initiateCCTransactionRequestType"));
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
        elemField.setFieldName("emailAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "emailAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cardType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cardType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pan");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pan"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expiryYear");
        elemField.setXmlName(new javax.xml.namespace.QName("", "expiryYear"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expiryMonth");
        elemField.setXmlName(new javax.xml.namespace.QName("", "expiryMonth"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cvv2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cvv2"));
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
