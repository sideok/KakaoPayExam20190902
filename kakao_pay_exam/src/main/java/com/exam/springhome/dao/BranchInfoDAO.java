package com.exam.springhome.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.exam.springhome.vo.BranchInfoVO;

/**
 * 부점정보 DAO
 * @author SIDeok
 */

@Repository("BranchInfoDAO")
public class BranchInfoDAO {
	
	@Value("${data.path}") 
	private String file_Path;
	
	// 데이터리스트
	private List<BranchInfoVO> mlvData = new ArrayList<>();
	
	/***
	 * 전체 데이터 조회
	 * query :
	 * SELECT *
	 *   FROM 데이터_지점정보
	 *   
	 * @return List<AccountInfoVO>
	 */
	public List<BranchInfoVO> getData() {
		if(mlvData == null || mlvData.isEmpty()) {
			/* CSV파일을 데이터베이스로 간주하기 떄문에 파일IO를 통해 DAO를 처리한다. */
			try {
				Files.lines(Paths.get(System.getProperty("user.dir") + file_Path + "/data_branch_info.csv"))
				     .skip(1)
				     .forEach(line -> { String[] arrBranchInfo = line.split(",");
						            	// 데이터 갯수가 잘못 조회되면 continue
						            	if(arrBranchInfo.length != 2) return;
						            	
						            	BranchInfoVO lvBranchInfoVO = new BranchInfoVO(arrBranchInfo[0], arrBranchInfo[1]);
						            	mlvData.add(lvBranchInfoVO);
				     			}
						);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return mlvData;
	}
	
}
