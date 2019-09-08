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

import com.exam.springhome.vo.AccountInfoVO;
import com.exam.springhome.vo.BranchInfoVO;

/**
 * 계좌정보DAO
 * @author SIDeok
 */

@Repository("AccountInfoDAO")
public class AccountInfoDAO {
	
	// 데이터리스트
	private List<AccountInfoVO> mlvData = new ArrayList<>();
	
	/***
	 * 전체 데이터 조회
	 * query :
	 * SELECT *
	 *   FROM 데이터_계좌정보
	 *   
	 * @return List<AccountInfoVO>
	 */
	public List<AccountInfoVO> getData() {
		if(mlvData == null || mlvData.isEmpty()) {
			/* CSV파일을 데이터베이스로 간주하기 떄문에 파일IO를 통해 DAO를 처리한다. */
			try {
				BufferedReader in=new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream( "data_account_info.csv"), "UTF-8"));
				
				String line = "";
				int i = 0;
				while ((line = in.readLine()) != null) {
					// 헤더 continue
					if(i++ == 0) continue; 
					
					String[] arrAccountInfo = line.split(",");
	            	// 데이터 갯수가 잘못 조회되면 continue
	            	if(arrAccountInfo.length != 3) continue;
	            	
	            	AccountInfoVO lvAccountInfoVO = new AccountInfoVO(arrAccountInfo[0], arrAccountInfo[1], arrAccountInfo[2]);
	            	mlvData.add(lvAccountInfoVO);
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
	 *   FROM 데이터_계좌정보
	 *  WHERE 계좌번호 = ${계좌번호}
	 * @return List<AccountInfoVO>
	 */
	public List<AccountInfoVO> getData(String key) {
		// 틀정 계좌번호로 데이터 추출
		return getData().stream().filter(vo -> vo.getACCOUNT_ID().equals(key))
									.collect(Collectors.toList());
	}
	
}
