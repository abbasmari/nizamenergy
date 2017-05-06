
package bean;

import java.util.Date;


public class CustomerMessageBean {

    private int messageId;
    private int customerId;
    private int status;
    private String message;
    private String gsmNumber;
    private Date msgDate;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getGsmNumber() {
        return gsmNumber;
    }

    public void setGsmNumber(String gsmNumber) {
        this.gsmNumber = gsmNumber;
    }

    public Date getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(Date msgDate) {
        this.msgDate = msgDate;
    }

    @Override
    public String toString() {
        return "CustomerMessageBean{" + "messageId=" + messageId + ", customerId=" + customerId + ", status=" + status + ", message=" + message + ", gsmNumber=" + gsmNumber + ", msgDate=" + msgDate + '}';
    }
    

}
