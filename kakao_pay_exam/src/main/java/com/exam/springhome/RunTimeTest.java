package com.exam.springhome;

import com.exam.springhome.dao.AccountInfoDAO;
import com.exam.springhome.dao.BranchInfoDAO;
import com.exam.springhome.dao.TransactionHistoryDAO;
import com.exam.springhome.vo.TransactionHistoryVO;

public class RunTimeTest {
	public static void main(String[] args) {
//		test1(); // 데이터 엑세스 테스트
		
		// 데이터 조회 테스트
//		AccountInfoDAO a = new AccountInfoDAO();
//		System.out.println(a.getData("11111111").get(0).getACCOUNT_NAME());
		
		TransactionHistoryDAO b = new TransactionHistoryDAO();
		TransactionHistoryVO data = b.getMaxAmoutCustByYear("2019");
		System.out.println(data.getACCOUNT_ID() + " " + data.getAMOUNT() + " " + data.getTRANSATION_DATE());
	}
	
	// 데이터 엑세스 테스트
	static void test1() {
		System.out.println("=================계좌 데이터===================================");
		AccountInfoDAO a = new AccountInfoDAO();
		a.getData().forEach(AccountInfoDAO -> System.out.println(AccountInfoDAO.getACCOUNT_ID() 
				                                        + ", " + AccountInfoDAO.getACCOUNT_NAME() 
				                                        + ", " + AccountInfoDAO.getBRANCH_CODE())   );	
		
		System.out.println("\n\n=================부점 데이터===================================");
		BranchInfoDAO b = new BranchInfoDAO();
		b.getData().forEach(BranchInfoDAO -> System.out.println(BranchInfoDAO.getBRANCH_CODE()
			                                         	+ ", " + BranchInfoDAO.getBRANCH_NAME())   );	
		
		System.out.println("\n\n=================거래이력 데이터===================================");
		TransactionHistoryDAO c = new TransactionHistoryDAO();
		c.getData().forEach(TransactionHistoryDAO -> System.out.println(TransactionHistoryDAO.getTRANSATION_DATE()
																+ ", " + TransactionHistoryDAO.getACCOUNT_ID()
																+ ", " + TransactionHistoryDAO.getTRANSACTION_NUMBER()
																+ ", " + TransactionHistoryDAO.getAMOUNT()
																+ ", " + TransactionHistoryDAO.getFEE()
																+ ", " + TransactionHistoryDAO.getCANCEL_YN() )
																);	
	}
	
	
}
