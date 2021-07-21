package models;

public class Transaction {
	private int id;
	private int sendingAccId;
	private int recievingAccId;
	private double transferAmount;
	public Transaction() {
		
	}
	
	public Transaction(int id, int sendingAccId, int recievingAccId, double transferAmount) {
		super();
		this.id = id;
		this.sendingAccId = sendingAccId;
		this.recievingAccId = recievingAccId;
		this.transferAmount = transferAmount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSendingAccId() {
		return sendingAccId;
	}
	public void setSendingAccId(int sendingAccId) {
		this.sendingAccId = sendingAccId;
	}
	public int getRecievingAccId() {
		return recievingAccId;
	}
	public void setRecievingAccId(int recievingAccId) {
		this.recievingAccId = recievingAccId;
	}
	public double getTransferAmount() {
		return transferAmount;
	}
	public void setTransferAmount(double transferAmount) {
		this.transferAmount = transferAmount;
	}
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", sendingAccId=" + sendingAccId + ", recievingAccId=" + recievingAccId
				+ ", transferAmount=" + transferAmount + "]";
	}
	 
}
