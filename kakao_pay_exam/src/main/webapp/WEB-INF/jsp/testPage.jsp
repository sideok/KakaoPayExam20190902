<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>TEST</title>
<script type="text/javascript">
function btnService1_onClick(obj, e) {
 	var iptYear = document.getElementById("iptYearService1");

	var xhr = new XMLHttpRequest();
	var data = {};
	xhr.onload = function() {
		document.getElementById("ifrmResult1").contentDocument.body.innerHTML = '<pre style="word-wrap: break-word; white-space: pre-wrap">' + xhr.responseText + '</pre>';
	};
	if(iptYear.value != "") {
		xhr.open("GET", "/cust/get-max-amt/" + iptYear.value);
	} else {
		xhr.open("GET", "/cust/get-max-amt");
	}
	xhr.setRequestHeader("Content-Type", "application/json"); 
	xhr.send(JSON.stringify(data));

}
function btnService2_onClick(obj, e) {
 	var iptYear = document.getElementById("iptYearService2");

	var xhr = new XMLHttpRequest();
	var data = {};
	xhr.onload = function() {
		document.getElementById("ifrmResult2").contentDocument.body.innerHTML = '<pre style="word-wrap: break-word; white-space: pre-wrap">' + xhr.responseText + '</pre>';
	};
	if(iptYear.value != "") {
		xhr.open("GET", "/cust/get-no-hist-cust/" + iptYear.value);
	} else {
		xhr.open("GET", "/cust/get-no-hist-cust");
	}
	xhr.setRequestHeader("Content-Type", "application/json"); 
	xhr.send(JSON.stringify(data));
}
function btnService3_onClick(obj, e) {
 	var iptYear = document.getElementById("iptYearService3");

	var xhr = new XMLHttpRequest();
	var data = {};
	xhr.onload = function() {
		document.getElementById("ifrmResult3").contentDocument.body.innerHTML = '<pre style="word-wrap: break-word; white-space: pre-wrap">' + xhr.responseText + '</pre>';
	};
	if(iptYear.value != "") {
		xhr.open("GET", "/branch/order-by-samt/" + iptYear.value);
	} else {
		xhr.open("GET", "/branch/order-by-samt");
	}
	xhr.setRequestHeader("Content-Type", "application/json"); 
	xhr.send(JSON.stringify(data));
}
function btnService4_onClick(obj, e) {
	var xhr = new XMLHttpRequest();
	var data = {
		brName: document.getElementById("iptYearService4").value,
	};
	xhr.onload = function() {
		document.getElementById("ifrmResult4").contentDocument.body.innerHTML = '<pre style="word-wrap: break-word; white-space: pre-wrap">' + xhr.responseText + '</pre>';
	};
	xhr.open("POST", "/branch/samt-by-branch");
	xhr.setRequestHeader("Content-Type", "application/json"); 
	xhr.send(JSON.stringify(data));

}
</script>
</head>
<body>
<div style="border-bottom: 1px solid;padding-bottom: 20px;">
	<h1>개발요건 1</h1>
	<h3>2018년, 2019년 각 연도별 합계 금액이 가장 많은 고객을 추출하는 API</h3>
	<ul>
		<li>하단 박스에 추출하고 싶은 대상 년도를 입력하면 해당 년도가 응답결과로 출력</li>
		<li>구분자로 dash(-)를 사용하여 추출하고 싶은 년도를 다건으로 입력 가능</li>
		<li>입력값이 없을 경우 거래이력에 존재하는 모든 년도(2018년, 2019년)를 대상이 응답결과로 출력</li>
		<li>호출 URL : /cust/get-max-amt</li>
	</ul>
	<form id="frmService1" action="" target="ifrmResult1">
	</form>
	<input name="year" id="iptYearService1" placeholder="구분자로 '-' 사용 ex)2018-2019" style="width:500px"> &nbsp;<input id="btnService1" type="button" value="요청" onclick="btnService1_onClick(this, event)">
	<h5>요청결과</h5>
	<iframe name="ifrmResult1" id="ifrmResult1" style="width:100%;height:100px">
	</iframe>
</div>
<div style="border-bottom: 1px solid;padding-bottom: 20px;">
	<h1>개발요건 2</h1>
	<h3>2018년 또는 2019년에 거래가 없는 고객을 추출하는 API</h3>
	<ul>
		<li>하단 박스에 추출하고 싶은 대상 년도를 입력하면 해당 년도가 응답결과로 출력</li>
		<li>구분자로 dash(-)를 사용하여 추출하고 싶은 년도를 다건으로 입력 가능</li>
		<li>입력값이 없을 경우 거래이력에 존재하는 모든 년도(2018년, 2019년)를 대상이 응답결과로 출력</li>
		<li>호출 URL : /get-no-hist-cust</li>
	</ul>
	<form id="frmService2" action="" target="ifrmResult2">
	</form>
	<input name="year" id="iptYearService2" placeholder="구분자로 '-' 사용 ex)2018-2019" style="width:500px"> &nbsp;<input id="btnService2" type="button" value="요청" onclick="btnService2_onClick(this, event)">
	<h5>요청결과</h5>
	<iframe name="ifrmResult2" id="ifrmResult2" style="width:100%;height:100px">
	</iframe>
</div>
<div style="border-bottom: 1px solid;padding-bottom: 20px;">
	<h1>개발요건 3</h1>
	<h3>연도별 관리점별 거래금액 합계를 구하고 합계금액이 큰 순서로 출력하는 API</h3>
	<ul>
		<li>하단 박스에 추출하고 싶은 대상 년도를 입력하면 해당 년도가 응답결과로 출력</li>
		<li>구분자로 dash(-)를 사용하여 추출하고 싶은 년도를 다건으로 입력 가능</li>
		<li>입력값이 없을 경우 거래이력에 존재하는 모든 년도(2018년, 2019년)를 대상이 응답결과로 출력</li>
		<li>호출 URL : /branch/order-by-samt</li>
	</ul>
	<form id="frmService3" action="" target="ifrmResult3">
	</form>
	<input name="year" id="iptYearService3" placeholder="구분자로 '-' 사용 ex)2018-2019" style="width:500px"> &nbsp;<input id="btnService3" type="button" value="요청" onclick="btnService3_onClick(this, event)">
	<h5>요청결과</h5>
	<iframe name="ifrmResult3" id="ifrmResult3" style="width:100%;height:100px">
	</iframe>
</div>
<div style="border-bottom: 1px solid;padding-bottom: 20px;">
	<h1>개발요건 4</h1>
	<h3>분당점과 판교점을 통폐합하여 판교점으로 관리점을 이관하였을 때, 지점명을 입력하면 해당지점의 거래금액 합계를 출력하는 API</h3>	
	<ul>
		<li>분당점 및 미존재 지점명을 입력하면 httpstatus를 404로 응답</li>
		<li>지점명이 정확하게 일치하여야 데이터 출력</li>
		<li>호출 URL : /branch/samt-by-branch</li>
	</ul>
	<form id="frmService4" action="/branch/samt-by-branch" target="ifrmResult4" method="post" enctype="application/json">
	</form>
	<input name="brName" id="iptYearService4" placeholder="지점명 입력  ex)판교점" style="width:500px"> &nbsp;<input type="button" id="btnService4" value="요청" onclick="btnService4_onClick(this, event)">
	<h5>요청결과</h5>
	<iframe name="ifrmResult4" id="ifrmResult4" style="width:100%;height:100px">
	</iframe>
</div>
</body>
</html>