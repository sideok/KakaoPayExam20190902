package com.exam.springhome.vo;

import org.springframework.stereotype.Repository;

/**
 * 부점정보 VO
 * @author SIDeok
 */

@Repository("BranchInfoVO")
public class BranchInfoVO {
	// 관리점코드
	private String BRANCH_CODE;
	// 관리점명
	private String BRANCH_NAME;
	
	public BranchInfoVO() {};
	public BranchInfoVO(String bRANCH_CODE, String bRANCH_NAME) {
		BRANCH_CODE = bRANCH_CODE;
		BRANCH_NAME = bRANCH_NAME;
	}
	public String getBRANCH_CODE() {
		return BRANCH_CODE;
	}
	public void setBRANCH_CODE(String bRANCH_CODE) {
		BRANCH_CODE = bRANCH_CODE;
	}
	public String getBRANCH_NAME() {
		return BRANCH_NAME;
	}
	public void setBRANCH_NAME(String bRANCH_NAME) {
		BRANCH_NAME = bRANCH_NAME;
	}
	
}
