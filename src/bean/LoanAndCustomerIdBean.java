package bean;

public class LoanAndCustomerIdBean {
	
	int loanId;
    int cucstomerId;

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public int getCucstomerId() {
        return cucstomerId;
    }

    public void setCucstomerId(int cucstomerId) {
        this.cucstomerId = cucstomerId;
    }

    @Override
    public String toString() {
        return "LoandAndCutstomerIdBean{" + "loanId=" + loanId + ", cucstomerId=" + cucstomerId + '}';
    }
}
