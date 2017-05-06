/**
 * PartnerBusinessServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.systems.pg.partner.transaction;

public class PartnerBusinessServiceLocator extends org.apache.axis.client.Service implements com.systems.pg.partner.transaction.PartnerBusinessService {

    public PartnerBusinessServiceLocator() {
    }


    public PartnerBusinessServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public PartnerBusinessServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for PartnerBusinessServicePort
    private java.lang.String PartnerBusinessServicePort_address = "http://202.69.8.50:9080/easypay-service/PartnerBusinessService";

    public java.lang.String getPartnerBusinessServicePortAddress() {
        return PartnerBusinessServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String PartnerBusinessServicePortWSDDServiceName = "PartnerBusinessServicePort";

    public java.lang.String getPartnerBusinessServicePortWSDDServiceName() {
        return PartnerBusinessServicePortWSDDServiceName;
    }

    public void setPartnerBusinessServicePortWSDDServiceName(java.lang.String name) {
        PartnerBusinessServicePortWSDDServiceName = name;
    }

    public com.systems.pg.partner.transaction.IPartnerBusinessService getPartnerBusinessServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(PartnerBusinessServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPartnerBusinessServicePort(endpoint);
    }

    public com.systems.pg.partner.transaction.IPartnerBusinessService getPartnerBusinessServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.systems.pg.partner.transaction.PartnerBusinessServicePortBindingStub _stub = new com.systems.pg.partner.transaction.PartnerBusinessServicePortBindingStub(portAddress, this);
            _stub.setPortName(getPartnerBusinessServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPartnerBusinessServicePortEndpointAddress(java.lang.String address) {
        PartnerBusinessServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.systems.pg.partner.transaction.IPartnerBusinessService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.systems.pg.partner.transaction.PartnerBusinessServicePortBindingStub _stub = new com.systems.pg.partner.transaction.PartnerBusinessServicePortBindingStub(new java.net.URL(PartnerBusinessServicePort_address), this);
                _stub.setPortName(getPartnerBusinessServicePortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("PartnerBusinessServicePort".equals(inputPortName)) {
            return getPartnerBusinessServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://transaction.partner.pg.systems.com/", "PartnerBusinessService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://transaction.partner.pg.systems.com/", "PartnerBusinessServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("PartnerBusinessServicePort".equals(portName)) {
            setPartnerBusinessServicePortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
