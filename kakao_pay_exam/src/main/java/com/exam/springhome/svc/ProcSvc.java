package com.exam.springhome.svc;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.springhome.dao.AccountInfoDAO;
import com.exam.springhome.dao.TransactionHistoryDAO;
import com.exam.springhome.vo.AccountInfoVO;

/**
 * 처리 Service
 * @author SIDeok
 */


@Service
public class ProcSvc {
	
	// 데이터_거래이력 DAO
	@Autowired
	TransactionHistoryDAO daoTransactionHistory;
	
	// 데이터_계좌정보 DAO
	@Autowired
	AccountInfoDAO daoAccountInfo;
	
	public Map[] getMaxSumAmoutByYear(String year) {
		String[] arrYear = year.split("-");
		Map[] output = new Map[arrYear.length];
		
		for(int i = 0; i < arrYear.length; i++) {
			// 해당년도 최대거래금액 조회
			Map<String, Object> tmpOutput = daoTransactionHistory.getMaxAmoutCustByYear(arrYear[i]);

			// 계좌명 조회 및 세팅
			List<AccountInfoVO> lvAccountInfo = daoAccountInfo.getData(tmpOutput.get("acctNo").toString());
			if(!lvAccountInfo.isEmpty()) {
				tmpOutput.put("name", lvAccountInfo.get(0).getACCOUNT_NAME());
			} else {
				tmpOutput.put("name", "계좌정보 미존재");
			}
			
			// output변수 세팅
			output[i] = tmpOutput;
		}
		
		return output;
	}
}
