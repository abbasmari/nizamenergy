/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.Date;

/**
 *
 * @author waseem
 */
public class SalesManResponse {
    private int responseId;
    private int salesManId;
    private int customerId;
    private String message;
    private Date resposeDate;

    public SalesManResponse() {}

    public SalesManResponse(int responseId, int salesManId, int customerId, String message, Date resposeDate) {
        this.responseId = responseId;
        this.salesManId = salesManId;
        this.customerId = customerId;
        this.message = message;
        this.resposeDate = resposeDate;
    }

    public int getResponseId() {
        return responseId;
    }

    public void setResponseId(int responseId) {
        this.responseId = responseId;
    }

    public int getSalesManId() {
        return salesManId;
    }

    public void setSalesManId(int salesManId) {
        this.salesManId = salesManId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getResposeDate() {
        return resposeDate;
    }

    public void setResposeDate(Date resposeDate) {
        this.resposeDate = resposeDate;
    }

    @Override
    public String toString() {
        return "SalesManResponse{" + "responseId=" + responseId + ", salesManId=" + salesManId + ", customerId=" + customerId + ", message=" + message + ", resposeDate=" + resposeDate + '}';
    }
    
    
    
    
    
}
