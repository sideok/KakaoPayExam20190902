package com.exam.springhome.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.exam.springhome.vo.TransactionHistoryVO;

/**
 * 거래이력 DAO
 * @author SIDeok
 */

@Repository("TransactionHistoryDAO")
public class TransactionHistoryDAO {
	
	@Value("${data.path}") 
	private String file_Path;
	
	// 데이터리스트
	private List<TransactionHistoryVO> mlvData = new ArrayList<>();
	
	/***
	 * 전체 데이터 조회
	 * query :
	 * SELECT *
	 *   FROM 데이터_거래내역
	 *   
	 * @return List<BranchInfoVO>
	 */
	public List<TransactionHistoryVO> getData() {
		if(mlvData == null || mlvData.isEmpty()) {
			/* CSV파일을 데이터베이스로 간주하기 떄문에 파일IO를 통해 DAO를 처리한다. */
			try {
				Files.lines(Paths.get(System.getProperty("user.dir") + file_Path + "/data_transaction_history.csv"))
				     .skip(1)
				     .forEach(line -> { 
										String[] arrTransactionHistory= line.split(",");
										// 데이터 갯수가 잘못 조회되면 continue
										if(arrTransactionHistory.length != 6) return;
										
										TransactionHistoryVO lvTransactionHistoryVO = new TransactionHistoryVO(arrTransactionHistory[0]
												                                                             , arrTransactionHistory[1]
										    			                                                     , Integer.parseInt(arrTransactionHistory[2])
										            			                                             , Long.parseLong(arrTransactionHistory[3])
										            			                                             , Long.parseLong(arrTransactionHistory[4])
										            			                                             , arrTransactionHistory[5] );
										mlvData.add(lvTransactionHistoryVO);
				     			}
						);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		return mlvData;
	}
	
	/***
	 * 입력 연도별 합계 금액이 가장 많은 고객을 추출하는 함수
	 * query :
	 *  
	 *  SELECT Y.계좌번호, Y.합계금액
     *    FROM ( SELECT 계좌번호, SUM(거래금액) AS 합계금액
     *             FROM 데이터_거래내역
     *            WHERE 거래일자 LIKE ${year} || '%'
     *              AND 취소여부 = 'N'
     *            GROUP BY 계좌번호  ) X
     *    JOIN ( SELECT T.계좌번호, MAX(T.합계금액) AS 합계금액 
     *             FROM ( SELECT 계좌번호, SUM(거래금액) AS 합계금액
     *                      FROM 데이터_거래내역 
     *                     WHERE 거래일자 LIKE ${year} || '%'
     *                       AND 취소여부 = 'N'
     *                     GROUP BY 계좌번호 ) T  ) Y
     *      ON X.계좌번호 = Y.계좌번호
     *     AND X.합계금액 = Y.합계금액
	 *  
	 * @return List<BranchInfoVO>
	 */
	public Map<String, Object> getMaxAmoutCustByYear(String pYear) {
		/* 데이터베이스를 파일의 내용으로 대체하였기 때문에 sql이 아닌 코딩을 통해서 데이터를 추출처리 함 */
		
		// 입력 년도의 데이터 추출
		List<TransactionHistoryVO> yearData = getData().stream().filter(TransactionHistoryVO -> TransactionHistoryVO.getTRANSATION_DATE().startsWith(pYear)).collect(Collectors.toList());
		
		// 해당년도 고객 추출
		List<String> llAccountId = yearData.stream().map(TransactionHistoryVO::getACCOUNT_ID)
				                          .distinct()
				                          .collect(Collectors.toList());
		
		// 고객별 SUM
		Long maxAmt = Long.valueOf(0);
		String maxId = "";
		for(String id : llAccountId) {
			Long tmpLong = yearData.stream().filter(TransactionHistoryVO -> TransactionHistoryVO.getACCOUNT_ID().equals(id) && TransactionHistoryVO.getCANCEL_YN().equals("N"))
					                        .mapToLong(TransactionHistoryVO -> TransactionHistoryVO.getAMOUNT() - TransactionHistoryVO.getFEE()).sum();
		
			if(tmpLong > maxAmt) {
				maxAmt = tmpLong;
				maxId = id;
			}
		}
		Map<String, Object> output = new HashMap<String, Object>();
		output.put("year", pYear);
		output.put("acctNo", maxId);
		output.put("sumAmt", maxAmt);
		
		return output;
	}
}
