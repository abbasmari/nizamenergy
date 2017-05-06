/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

public class Message {
    
    private int id;
    private int customerId;
    private int doId;
    private String message;

    public Message() {}

    public Message(int id, int customerId, int doId, String message) {
        this.id = id;
        this.customerId = customerId;
        this.doId = doId;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDoId() {
        return doId;
    }

    public void setDoId(int doId) {
        this.doId = doId;
    }
    
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    
    @Override
    public String toString() {
        return "Message{" + "id=" + id + ", doId=" + doId + ", message=" + message + '}';
    }

}
