package com.exam.springhome.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.exam.springhome.vo.BranchInfoVO;
import com.exam.springhome.vo.TransactionHistoryVO;

/**
 * 부점정보 DAO
 * @author SIDeok
 */

@Repository("BranchInfoDAO")
public class BranchInfoDAO {

	// 데이터리스트
	private List<BranchInfoVO> mlvData = new ArrayList<>();
	
	/***
	 * 전체 데이터 조회
	 * query :
	 * SELECT *
	 *   FROM 데이터_지점정보
	 *   
	 * @return List<BranchInfoVO>
	 */
	public List<BranchInfoVO> getData() {
		if(mlvData == null || mlvData.isEmpty()) {
			/* CSV파일을 데이터베이스로 간주하기 떄문에 파일IO를 통해 DAO를 처리한다. */
			try {
				BufferedReader in=new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream( "data_branch_info.csv"), "UTF-8"));
				
				String line = "";
				int i = 0;
				while ((line = in.readLine()) != null) {
					// 헤더 continue
					if(i++ == 0) continue; 
					
					String[] arrBranchInfo = line.split(",");
	            	// 데이터 갯수가 잘못 조회되면 continue
	            	if(arrBranchInfo.length != 2) continue;
	            	
	            	BranchInfoVO lvBranchInfoVO = new BranchInfoVO(arrBranchInfo[0], arrBranchInfo[1]);
	            	mlvData.add(lvBranchInfoVO);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return mlvData;
	}
	
	/***
	 * 계좌번호 로 계좌정보 조회
	 * query :
	 * SELECT *
	 *   FROM 데이터_관리점정보
	 *  WHERE 관리점코드 = ${관리점코드}
	 * @return List<BranchInfoVO>
	 */
	public List<BranchInfoVO> getData(String key) {
		// 틀정 계좌번호로 데이터 추출
		return getData().stream().filter(vo -> vo.getBRANCH_CODE().equals(key))
									.collect(Collectors.toList());
	}
}
