/**
 * InitiateCCTransactionResponseType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.systems.pg.partner.transaction.dto;

public class InitiateCCTransactionResponseType  extends com.systems.pg.common.dto.BaseResponseType  implements java.io.Serializable {
    private java.lang.String orderId;

    private long storeId;

    private java.lang.String paymentToken;

    private java.lang.String transactionId;

    private java.util.Calendar transactionDateTime;

    private java.util.Calendar paymentTokenExiryDateTime;

    private java.lang.String transactionRefNumber;

    private java.lang.String authorizationId;

    private java.lang.String batchNumber;

    public InitiateCCTransactionResponseType() {
    }

    public InitiateCCTransactionResponseType(
           java.lang.String responseCode,
           java.lang.String orderId,
           long storeId,
           java.lang.String paymentToken,
           java.lang.String transactionId,
           java.util.Calendar transactionDateTime,
           java.util.Calendar paymentTokenExiryDateTime,
           java.lang.String transactionRefNumber,
           java.lang.String authorizationId,
           java.lang.String batchNumber) {
        super(
            responseCode);
        this.orderId = orderId;
        this.storeId = storeId;
        this.paymentToken = paymentToken;
        this.transactionId = transactionId;
        this.transactionDateTime = transactionDateTime;
        this.paymentTokenExiryDateTime = paymentTokenExiryDateTime;
        this.transactionRefNumber = transactionRefNumber;
        this.authorizationId = authorizationId;
        this.batchNumber = batchNumber;
    }


    /**
     * Gets the orderId value for this InitiateCCTransactionResponseType.
     * 
     * @return orderId
     */
    public java.lang.String getOrderId() {
        return orderId;
    }


    /**
     * Sets the orderId value for this InitiateCCTransactionResponseType.
     * 
     * @param orderId
     */
    public void setOrderId(java.lang.String orderId) {
        this.orderId = orderId;
    }


    /**
     * Gets the storeId value for this InitiateCCTransactionResponseType.
     * 
     * @return storeId
     */
    public long getStoreId() {
        return storeId;
    }


    /**
     * Sets the storeId value for this InitiateCCTransactionResponseType.
     * 
     * @param storeId
     */
    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }


    /**
     * Gets the paymentToken value for this InitiateCCTransactionResponseType.
     * 
     * @return paymentToken
     */
    public java.lang.String getPaymentToken() {
        return paymentToken;
    }


    /**
     * Sets the paymentToken value for this InitiateCCTransactionResponseType.
     * 
     * @param paymentToken
     */
    public void setPaymentToken(java.lang.String paymentToken) {
        this.paymentToken = paymentToken;
    }


    /**
     * Gets the transactionId value for this InitiateCCTransactionResponseType.
     * 
     * @return transactionId
     */
    public java.lang.String getTransactionId() {
        return transactionId;
    }


    /**
     * Sets the transactionId value for this InitiateCCTransactionResponseType.
     * 
     * @param transactionId
     */
    public void setTransactionId(java.lang.String transactionId) {
        this.transactionId = transactionId;
    }


    /**
     * Gets the transactionDateTime value for this InitiateCCTransactionResponseType.
     * 
     * @return transactionDateTime
     */
    public java.util.Calendar getTransactionDateTime() {
        return transactionDateTime;
    }


    /**
     * Sets the transactionDateTime value for this InitiateCCTransactionResponseType.
     * 
     * @param transactionDateTime
     */
    public void setTransactionDateTime(java.util.Calendar transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }


    /**
     * Gets the paymentTokenExiryDateTime value for this InitiateCCTransactionResponseType.
     * 
     * @return paymentTokenExiryDateTime
     */
    public java.util.Calendar getPaymentTokenExiryDateTime() {
        return paymentTokenExiryDateTime;
    }


    /**
     * Sets the paymentTokenExiryDateTime value for this InitiateCCTransactionResponseType.
     * 
     * @param paymentTokenExiryDateTime
     */
    public void setPaymentTokenExiryDateTime(java.util.Calendar paymentTokenExiryDateTime) {
        this.paymentTokenExiryDateTime = paymentTokenExiryDateTime;
    }


    /**
     * Gets the transactionRefNumber value for this InitiateCCTransactionResponseType.
     * 
     * @return transactionRefNumber
     */
    public java.lang.String getTransactionRefNumber() {
        return transactionRefNumber;
    }


    /**
     * Sets the transactionRefNumber value for this InitiateCCTransactionResponseType.
     * 
     * @param transactionRefNumber
     */
    public void setTransactionRefNumber(java.lang.String transactionRefNumber) {
        this.transactionRefNumber = transactionRefNumber;
    }


    /**
     * Gets the authorizationId value for this InitiateCCTransactionResponseType.
     * 
     * @return authorizationId
     */
    public java.lang.String getAuthorizationId() {
        return authorizationId;
    }


    /**
     * Sets the authorizationId value for this InitiateCCTransactionResponseType.
     * 
     * @param authorizationId
     */
    public void setAuthorizationId(java.lang.String authorizationId) {
        this.authorizationId = authorizationId;
    }


    /**
     * Gets the batchNumber value for this InitiateCCTransactionResponseType.
     * 
     * @return batchNumber
     */
    public java.lang.String getBatchNumber() {
        return batchNumber;
    }


    /**
     * Sets the batchNumber value for this InitiateCCTransactionResponseType.
     * 
     * @param batchNumber
     */
    public void setBatchNumber(java.lang.String batchNumber) {
        this.batchNumber = batchNumber;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InitiateCCTransactionResponseType)) return false;
        InitiateCCTransactionResponseType other = (InitiateCCTransactionResponseType) obj;
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
            ((this.paymentToken==null && other.getPaymentToken()==null) || 
             (this.paymentToken!=null &&
              this.paymentToken.equals(other.getPaymentToken()))) &&
            ((this.transactionId==null && other.getTransactionId()==null) || 
             (this.transactionId!=null &&
              this.transactionId.equals(other.getTransactionId()))) &&
            ((this.transactionDateTime==null && other.getTransactionDateTime()==null) || 
             (this.transactionDateTime!=null &&
              this.transactionDateTime.equals(other.getTransactionDateTime()))) &&
            ((this.paymentTokenExiryDateTime==null && other.getPaymentTokenExiryDateTime()==null) || 
             (this.paymentTokenExiryDateTime!=null &&
              this.paymentTokenExiryDateTime.equals(other.getPaymentTokenExiryDateTime()))) &&
            ((this.transactionRefNumber==null && other.getTransactionRefNumber()==null) || 
             (this.transactionRefNumber!=null &&
              this.transactionRefNumber.equals(other.getTransactionRefNumber()))) &&
            ((this.authorizationId==null && other.getAuthorizationId()==null) || 
             (this.authorizationId!=null &&
              this.authorizationId.equals(other.getAuthorizationId()))) &&
            ((this.batchNumber==null && other.getBatchNumber()==null) || 
             (this.batchNumber!=null &&
              this.batchNumber.equals(other.getBatchNumber())));
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
        if (getPaymentToken() != null) {
            _hashCode += getPaymentToken().hashCode();
        }
        if (getTransactionId() != null) {
            _hashCode += getTransactionId().hashCode();
        }
        if (getTransactionDateTime() != null) {
            _hashCode += getTransactionDateTime().hashCode();
        }
        if (getPaymentTokenExiryDateTime() != null) {
            _hashCode += getPaymentTokenExiryDateTime().hashCode();
        }
        if (getTransactionRefNumber() != null) {
            _hashCode += getTransactionRefNumber().hashCode();
        }
        if (getAuthorizationId() != null) {
            _hashCode += getAuthorizationId().hashCode();
        }
        if (getBatchNumber() != null) {
            _hashCode += getBatchNumber().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InitiateCCTransactionResponseType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dto.transaction.partner.pg.systems.com/", ">initiateCCTransactionResponseType"));
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
        elemField.setFieldName("transactionRefNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "transactionRefNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authorizationId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "authorizationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("batchNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "batchNumber"));
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
