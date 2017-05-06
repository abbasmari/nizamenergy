/**
 * IPartnerBusinessService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.systems.pg.partner.transaction;

public interface IPartnerBusinessService extends java.rmi.Remote {
    public com.systems.pg.partner.transaction.dto.InitiateTransactionResponseType initiateTransaction(com.systems.pg.partner.transaction.dto.InitiateTransactionRequestType parameters1) throws java.rmi.RemoteException;
    public com.systems.pg.partner.transaction.dto.InquireTransactionResponseType inquireTransaction(com.systems.pg.partner.transaction.dto.InquireTransactionRequestType parameters1) throws java.rmi.RemoteException;
    public com.systems.pg.partner.transaction.dto.InitiateCCTransactionResponseType initiateCCTransaction(com.systems.pg.partner.transaction.dto.InitiateCCTransactionRequestType parameters1) throws java.rmi.RemoteException;
}
