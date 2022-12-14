<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Mem: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM Mem: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Mem: Home</p>

<h3>資料查詢:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message.value}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllMem.jsp'>List</a> all Mems.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="mem.do" >
        <b>輸入員工編號 (如7001):</b>
        <input type="text" name="mem_no" value="${param.mem_no}"><font color=red>${errorMsgs.mem_no}</font>
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="MemSvc" scope="page" class="com.mem.model.MemService" />
   
  <li>
     <FORM METHOD="post" ACTION="mem.do" >
       <b>選擇員工編號:</b>
       <select size="1" name="mem_no">
         <c:forEach var="memVO" items="${MemSvc.all}" > 
          <option value="${memVO.mem_no}">${memVO.mem_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="mem.do" >
       <b>選擇員工姓名:</b>
       <select size="1" name="mem_no">
         <c:forEach var="memVO" items="${MemSvc.all}" > 
          <option value="${memVO.mem_no}">${memVO.mem_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>員工管理</h3>

<ul>
  <li><a href='addMem.jsp'>Add</a> a new Mem.</li>
</ul>

</body>
</html>