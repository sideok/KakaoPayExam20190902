package com.exam.springhome.svc;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.springhome.dao.AccountInfoDAO;
import com.exam.springhome.dao.BranchInfoDAO;
import com.exam.springhome.dao.TransactionHistoryDAO;
import com.exam.springhome.vo.AccountInfoVO;
import com.exam.springhome.vo.BranchInfoVO;

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
	
	// 데이터_부점정보 DAO
	@Autowired
	BranchInfoDAO daoBranchInfo	;
	
	/**
	 * 입력년도에서 거래금액합계가 가장 큰 고객을 추출하는 Service
	 * -입력년도는 구분자로 '-' 를 사용하여 다건으로 입력할 수 있다.
	 * @param year
	 * @return
	 */
	public List<Map<String, Object>> getMaxSumAmoutByYear(String year) {
		
		// parameter 처리
		List<String> arrYear = new ArrayList<String>();
		if(year == null || year.isEmpty()) {
			// 입력 년도가 존재하지 않을 경우 처리
			arrYear = daoTransactionHistory.getData().stream()
											.map(vo -> vo.getTRANSATION_DATE().substring(0, 4))
											.distinct()
											.collect(Collectors.toList());
		} else {
			// 입력 년도가 존재할 경우 처리
			String[] tmpYear = year.split("-");
			for(String s : tmpYear) {
				arrYear.add(s);
			}
		}
		
		// 입력년도 Loop
		List<Map<String, Object>> output = new ArrayList<Map<String, Object>>();
		for(int i = 0; i < arrYear.size(); i++) {
			// 해당년도 최대거래금액 조회
			Map<String, Object> tmpOutput = daoTransactionHistory.getMaxAmoutCustByYear(arrYear.get(i));

			// 계좌명 조회 및 세팅
			List<AccountInfoVO> lvAccountInfo = daoAccountInfo.getData(tmpOutput.get("acctNo").toString());
			if(!lvAccountInfo.isEmpty()) {
				tmpOutput.put("name", lvAccountInfo.get(0).getACCOUNT_NAME());
			} else {
				tmpOutput.put("name", "계좌정보 미존재");
			}
			
			// output변수 세팅
			output.add(tmpOutput);
		}
		
		return output;
	}
	
	/**
	 * 입력년도에서 거래가 없는 고객을 추출하는 Service
	 * -입력년도는 구분자로 '-' 를 사용하여 다건으로 입력할 수 있다.
	 * @param year
	 * @return
	 */
	public List<Map<String, Object>> getAccountNoHistory(String year) {
		
		// parameter 처리
		List<String> arrYear = new ArrayList<String>();
		if(year == null || year.isEmpty()) {
			// 입력 년도가 존재하지 않을 경우 처리
			arrYear = daoTransactionHistory.getData().stream()
											.map(vo -> vo.getTRANSATION_DATE().substring(0, 4))
											.distinct()
											.collect(Collectors.toList());
		} else {
			// 입력 년도가 존재할 경우 처리
			String[] tmpYear = year.split("-");
			for(String s : tmpYear) {
				arrYear.add(s);
			}
		}
		
		// 고객 리스트 조회
		List<AccountInfoVO> accountList = daoAccountInfo.getData();
		
		// 입력년도 Loop
		List<Map<String, Object>> output = new ArrayList<Map<String, Object>>();
		for(String y : arrYear) {
			// 해당년도 거래고객번호 추출
			String strAccount = daoTransactionHistory.getDataByDate(y).stream()
																		.map(vo -> vo.getACCOUNT_ID())
																		.collect(Collectors.joining(","));
			
			// 반환값 세팅
			for(AccountInfoVO vo : accountList) {
				if(!strAccount.contains(vo.getACCOUNT_ID())) {
					Map<String, Object> tmpOutput = new HashMap<String, Object>();
					tmpOutput.put("year", y);
					tmpOutput.put("acctNo", vo.getACCOUNT_ID());
					tmpOutput.put("name", vo.getACCOUNT_NAME());
					output.add(tmpOutput);
				} 
			}
		}
		
		return output;
	}
	
	/**
	 * 연도별 관리점별 거래금액 합계를 계산하고 금액크기가 큰 순서로 정렬하는 Service
	 * -입력년도는 구분자로 '-' 를 사용하여 다건으로 입력할 수 있다.
	 * -입력년도가 없을 경우 거래내역의 모든 년도를 추출하여 처리
	 * @param year
	 * @return
	 */
	public List<Map<String, Object>> getAmountByYearByBranch(String year) {
		
		// parameter 처리
		List<String> arrYear = new ArrayList<String>();
		List<Map<String, Object>> output = new ArrayList<Map<String, Object>>();
		if(year == null || year.isEmpty()) {
			// 입력 년도가 존재하지 않을 경우 처리
			arrYear = daoTransactionHistory.getData().stream()
											.map(vo -> vo.getTRANSATION_DATE().substring(0, 4))
											.distinct()
											.collect(Collectors.toList());
		} else {
			// 입력 년도가 존재할 경우 처리
			String[] tmpYear = year.split("-");
			for(String s : tmpYear) {
				arrYear.add(s);
			}
		}
		
		// 입력년도 Loop
		for(String y : arrYear) {
			// 입력년도의 관리점별 거래금액 SUM 추출
			Map<String, Long> sumByCust = daoTransactionHistory.getDataByDate(y).stream()
																				.map((vo) -> { 
																					Map<String, Object> rtnMap = new HashMap<>(); // return Map
																					List<AccountInfoVO> lvAccountInfo = daoAccountInfo.getData(vo.getACCOUNT_ID()); // 계좌정보
																					rtnMap.put("BRANCH_CODE", lvAccountInfo.get(0).getBRANCH_CODE()); // 관리점 코드
																					rtnMap.put("AMOUNT", vo.getAMOUNT() - vo.getFEE()); // 거래금액
																					return rtnMap;
																				})
																				.collect(Collectors.groupingBy((map) -> Optional.ofNullable((String)map.get("BRANCH_CODE")).orElse("X"), Collectors.summingLong((map) -> Optional.ofNullable((Long)map.get("AMOUNT")).orElse(Long.valueOf(0)))));
			
			// 반환값 세팅
			Map<String, Object> outputMap = new HashMap<String, Object>();
			outputMap.put("year", y);
			
			List<Map<String, Object>> dataListMap = new ArrayList<Map<String, Object>>();
			for(Entry<String, Long> entry : sumByCust.entrySet()) {
				Map<String, Object> tmpMap = new HashMap<>();
				tmpMap.put("brName", daoBranchInfo.getData(entry.getKey()).get(0).getBRANCH_NAME()); // 관리점명
				tmpMap.put("brCode", entry.getKey()); // 관리점코드
				tmpMap.put("sumAmt", entry.getValue()); // 거래금액 합계
				
				dataListMap.add(tmpMap);
			}
			
			// 정렬하여 금액데이터 세팅
			outputMap.put("dataList", dataListMap.stream()
													.sorted((p1, p2) -> Long.compare( (Long)p2.get("sumAmt"), (Long)p1.get("sumAmt")))
													.collect(Collectors.toList())      );

			output.add(outputMap);
		}
		
		return output;
	}
	
	
	/**
	 * 입력 관리점명에 해당하는 금액합계를 반환하는 Service
	 * -입력된 관리점 데이터가 없을경우 null 로 return 한다.
	 * -판교점 입력시 분당점 데이터도 합산처리
	 * @param brName
	 * @return
	 */
	public Map<String, Object> getSumOfBranchAmt(String brName) {
		
		// 통폐합된 분당점 입력시 return null
		if("분당점".equals(brName)) {
			return new HashMap<>();
		}
		
		// 부점별 거래 합계금액 계산
		Map<String, Long> sumByBranch = daoTransactionHistory.getData().stream()
																		.filter(vo -> "N".equals(vo.getCANCEL_YN()))
																		.map(vo -> { 
																			Map<String, Object> rtnMap = new HashMap<>(); // return Map
																			List<AccountInfoVO> lvAccountInfo = daoAccountInfo.getData(vo.getACCOUNT_ID()); // 계좌정보
																			List<BranchInfoVO> lvBranchInfo = daoBranchInfo.getData(lvAccountInfo.get(0).getBRANCH_CODE()); // 계좌정보
																			if("분당점".equals(lvBranchInfo.get(0).getBRANCH_NAME())) {
																				rtnMap.put("BRANCH_INFO", "A,판교점"); // 관리점
																			} else {
																				rtnMap.put("BRANCH_INFO", lvBranchInfo.get(0).getBRANCH_CODE()+","+lvBranchInfo.get(0).getBRANCH_NAME()); // 관리점
																			}
																			rtnMap.put("AMOUNT", vo.getAMOUNT() - vo.getFEE()); // 거래금액
																			return rtnMap;
																		})
																		.collect(Collectors.groupingBy((map) -> Optional.ofNullable((String)map.get("BRANCH_INFO")).orElse("X"), Collectors.summingLong((map) -> Optional.ofNullable((Long)map.get("AMOUNT")).orElse(Long.valueOf(0)))));
		
		// 조회결과 세팅
		Map<String, Object> outputMap = new HashMap<String, Object>();
		for(Entry<String, Long> entry : sumByBranch.entrySet()) {
			String[] brInfo = entry.getKey().split(",");
			if(brName.equals(brInfo[1])) {
				outputMap.put("brName", brInfo[1]); // 관리점명
				outputMap.put("brCode", brInfo[0]); // 관리점코드
				outputMap.put("sumAmt", entry.getValue()); // 거래금액 합계
			}
		}
		
		return outputMap;
	}
}
