/**
 * ServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public interface ServiceSoap extends java.rmi.Remote {
    public java.lang.String sendSMS(java.lang.String sAccessKey, java.lang.String sMobile, java.lang.String sMsgId, java.lang.String sMsg) throws java.rmi.RemoteException;
    public java.lang.String getLocation(java.lang.String sAccessKey, java.lang.String sMobile) throws java.rmi.RemoteException;
    public org.tempuri.ReceiveSMSResponseReceiveSMSResult receiveSMS(java.lang.String sAccessKey, java.lang.String sRequestId) throws java.rmi.RemoteException;
}
