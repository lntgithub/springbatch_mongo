package com.citigroup.gftp.spring.da.domain;

public class Contract {
 
	/**
	 * @return the lvcode
	 */
	public String getLvcode() {
		return lvcode;
	}
	/**
	 * @param lvcode the lvcode to set
	 */
	public void setLvcode(String lvcode) {
		this.lvcode = lvcode;
	}
	/**
	 * @return the ccycode
	 */
	public String getCcycode() {
		return ccycode;
	}
	/**
	 * @param ccycode the ccycode to set
	 */
	public void setCcycode(String ccycode) {
		this.ccycode = ccycode;
	}
	/**
	 * @return the productcode
	 */
	public String getProductcode() {
		return productcode;
	}
	/**
	 * @param productcode the productcode to set
	 */
	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}
	/**
	 * @return the valuedate
	 */
	public String getValuedate() {
		return valuedate;
	}
	/**
	 * @param valuedate the valuedate to set
	 */
	public void setValuedate(String valuedate) {
		this.valuedate = valuedate;
	}
	/**
	 * @return the maturitydate
	 */
	public String getMaturitydate() {
		return maturitydate;
	}
	/**
	 * @param maturitydate the maturitydate to set
	 */
	public void setMaturitydate(String maturitydate) {
		this.maturitydate = maturitydate;
	}
	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	/**
	 * @return the installmentamt
	 */
	public String getInstallmentamt() {
		return installmentamt;
	}
	/**
	 * @param installmentamt the installmentamt to set
	 */
	public void setInstallmentamt(String installmentamt) {
		this.installmentamt = installmentamt;
	}
	/**
	 * @return the rate
	 */
	public String getRate() {
		return rate;
	}
	/**
	 * @param rate the rate to set
	 */
	public void setRate(String rate) {
		this.rate = rate;
	}

	private int id;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	public String getTprate() {
		return tprate;
	}
	public void setTprate(String tprate) {
		this.tprate = tprate;
	}

	private String lvcode;
	private String ccycode;
	private String productcode;
	private String valuedate;
	private String maturitydate;
	private String amount;
	private String installmentamt;
	private String rate;
	private String tprate;
	
 
	//getter and setter methods
 
 
}