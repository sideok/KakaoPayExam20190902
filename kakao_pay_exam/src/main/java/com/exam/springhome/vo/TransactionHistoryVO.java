package com.exam.springhome.vo;

import org.springframework.stereotype.Repository;

/**
 * 거래이력 VO
 * @author SIDeok
 */

@Repository("TransactionHistoryVO")
public class TransactionHistoryVO {
	// 거래일자
	private String TRANSATION_DATE;
	// 계좌번호
	private String ACCOUNT_ID;
	// 거래번호
	private Integer TRANSACTION_NUMBER;
	// 거래금액
	private Long AMOUNT;
	// 수수료
	private Long FEE;
	// 취소여부
	private String CANCEL_YN;
	
	public TransactionHistoryVO() {};
	public TransactionHistoryVO(String tRANSATION_DATE, String aCCOUNT_ID, Integer tRANSACTION_NUMBER,
			Long aMOUNT, Long fEE, String cANCEL_YN) {
		TRANSATION_DATE = tRANSATION_DATE;
		ACCOUNT_ID = aCCOUNT_ID;
		TRANSACTION_NUMBER = tRANSACTION_NUMBER;
		AMOUNT = aMOUNT;
		FEE = fEE;
		CANCEL_YN = cANCEL_YN;
	}
	public String getTRANSATION_DATE() {
		return TRANSATION_DATE;
	}
	public void setTRANSATION_DATE(String tRANSATION_DATE) {
		TRANSATION_DATE = tRANSATION_DATE;
	}
	public String getACCOUNT_ID() {
		return ACCOUNT_ID;
	}
	public void setACCOUNT_ID(String aCCOUNT_ID) {
		ACCOUNT_ID = aCCOUNT_ID;
	}
	public Integer getTRANSACTION_NUMBER() {
		return TRANSACTION_NUMBER;
	}
	public void setTRANSACTION_NUMBER(Integer tRANSACTION_NUMBER) {
		TRANSACTION_NUMBER = tRANSACTION_NUMBER;
	}
	public Long getAMOUNT() {
		return AMOUNT;
	}
	public void setAMOUNT(Long aMOUNT) {
		AMOUNT = aMOUNT;
	}
	public Long getFEE() {
		return FEE;
	}
	public void setFEE(Long fEE) {
		FEE = fEE;
	}
	public String getCANCEL_YN() {
		return CANCEL_YN;
	}
	public void setCANCEL_YN(String cANCEL_YN) {
		CANCEL_YN = cANCEL_YN;
	}
	
}
