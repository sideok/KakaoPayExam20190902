package com.exam.springhome.vo;

import org.springframework.stereotype.Repository;

/**
 * 계좌정보 VO
 * @author SIDeok
 */

@Repository("AccountInfoVO")
public class AccountInfoVO {
	// 계좌번호
	private String ACCOUNT_ID;
	// 계좌명
	private String ACCOUNT_NAME;
	// 관리점코드
	private String BRANCH_CODE;

	public AccountInfoVO() {}
	public AccountInfoVO(String aCCOUNT_ID, String aCCOUNT_NAME, String bRANCH_CODE) {
		ACCOUNT_ID = aCCOUNT_ID;
		ACCOUNT_NAME = aCCOUNT_NAME;
		BRANCH_CODE = bRANCH_CODE;
	}
	public String getACCOUNT_ID() {
		return ACCOUNT_ID;
	}
	public void setACCOUNT_ID(String aCCOUNT_ID) {
		ACCOUNT_ID = aCCOUNT_ID;
	}
	public String getACCOUNT_NAME() {
		return ACCOUNT_NAME;
	}
	public void setACCOUNT_NAME(String aCCOUNT_NAME) {
		ACCOUNT_NAME = aCCOUNT_NAME;
	}
	public String getBRANCH_CODE() {
		return BRANCH_CODE;
	}
	public void setBRANCH_CODE(String bRANCH_CODE) {
		BRANCH_CODE = bRANCH_CODE;
	}


}
