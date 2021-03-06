package model;

public class Agent {
	private int agentId;
	private int agencyId;
	private String agtBusPhone;
	private String agtEmail;
	private String agtFirstName;
	private String agtLastName;
	private String agtMiddleInitial;
	private String agtPosition;
	private Agency agncy;

	public Agency getAgncy() {
		return agncy;
	}

	public void setAgncy(Agency agncy) {
		this.agncy = agncy;
	}

	public Agent() {
	}

	public int getAgentId() {
		return this.agentId;
	}

	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}

	public int getAgencyId() {
		return this.agencyId;
	}

	public void setAgencyId(int agencyId) {
		this.agencyId = agencyId;
	}

	public String getAgtBusPhone() {
		return this.agtBusPhone;
	}

	public void setAgtBusPhone(String agtBusPhone) {
		this.agtBusPhone = agtBusPhone;
	}

	public String getAgtEmail() {
		return this.agtEmail;
	}

	public void setAgtEmail(String agtEmail) {
		this.agtEmail = agtEmail;
	}

	public String getAgtFirstName() {
		return this.agtFirstName;
	}

	public void setAgtFirstName(String agtFirstName) {
		this.agtFirstName = agtFirstName;
	}

	public String getAgtLastName() {
		return this.agtLastName;
	}

	public void setAgtLastName(String agtLastName) {
		this.agtLastName = agtLastName;
	}

	public String getAgtMiddleInitial() {
		return this.agtMiddleInitial;
	}

	public void setAgtMiddleInitial(String agtMiddleInitial) {
		this.agtMiddleInitial = agtMiddleInitial;
	}

	public String getAgtPosition() {
		return this.agtPosition;
	}

	public void setAgtPosition(String agtPosition) {
		this.agtPosition = agtPosition;
	}

	
}
