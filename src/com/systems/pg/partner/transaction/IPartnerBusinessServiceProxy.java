package com.systems.pg.partner.transaction;

public class IPartnerBusinessServiceProxy implements com.systems.pg.partner.transaction.IPartnerBusinessService {
  private String _endpoint = null;
  private com.systems.pg.partner.transaction.IPartnerBusinessService iPartnerBusinessService = null;
  
  public IPartnerBusinessServiceProxy() {
    _initIPartnerBusinessServiceProxy();
  }
  
  public IPartnerBusinessServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initIPartnerBusinessServiceProxy();
  }
  
  private void _initIPartnerBusinessServiceProxy() {
    try {
      iPartnerBusinessService = (new com.systems.pg.partner.transaction.PartnerBusinessServiceLocator()).getPartnerBusinessServicePort();
      if (iPartnerBusinessService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iPartnerBusinessService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iPartnerBusinessService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iPartnerBusinessService != null)
      ((javax.xml.rpc.Stub)iPartnerBusinessService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.systems.pg.partner.transaction.IPartnerBusinessService getIPartnerBusinessService() {
    if (iPartnerBusinessService == null)
      _initIPartnerBusinessServiceProxy();
    return iPartnerBusinessService;
  }
  
  public com.systems.pg.partner.transaction.dto.InitiateTransactionResponseType initiateTransaction(com.systems.pg.partner.transaction.dto.InitiateTransactionRequestType parameters1) throws java.rmi.RemoteException{
    if (iPartnerBusinessService == null)
      _initIPartnerBusinessServiceProxy();
    return iPartnerBusinessService.initiateTransaction(parameters1);
  }
  
  public com.systems.pg.partner.transaction.dto.InquireTransactionResponseType inquireTransaction(com.systems.pg.partner.transaction.dto.InquireTransactionRequestType parameters1) throws java.rmi.RemoteException{
    if (iPartnerBusinessService == null)
      _initIPartnerBusinessServiceProxy();
    return iPartnerBusinessService.inquireTransaction(parameters1);
  }
  
  public com.systems.pg.partner.transaction.dto.InitiateCCTransactionResponseType initiateCCTransaction(com.systems.pg.partner.transaction.dto.InitiateCCTransactionRequestType parameters1) throws java.rmi.RemoteException{
    if (iPartnerBusinessService == null)
      _initIPartnerBusinessServiceProxy();
    return iPartnerBusinessService.initiateCCTransaction(parameters1);
  }
  
  
}