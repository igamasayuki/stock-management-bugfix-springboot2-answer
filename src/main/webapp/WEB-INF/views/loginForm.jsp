<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="common/common.jsp"  %>
<body>
<div class="container">
	<div class="span8">
		<img src="img/logo.png" >
		<div class="row">
		<div class="error"><c:out value="${loginError}" /></div>
		<form:form  action="${pageContext.request.contextPath}/login">
			<table class="table table-striped">
			  <tr>
			    <th>
			    	 メールアドレス
			    </th>
			    <td>
			    	<input type="text" name="mailAddress" placeholder="Email"/>
			    </td>
			  </tr>
			  <tr>
			    <th>
			      	パスワード
			    </th>
			    <td>
			    	<input type="password" name="password" placeholder="Password"/>
			    </td>
			  </tr>
			  <tr>
			  	<td></td>
			    <td>
					<input class="btn" type="submit" value="ログイン">
			    </td>
			  </tr>
			</table>
		  </form:form>
		  <a href="/member/form" id="toInsertMember">メンバー登録はこちらから</a>
		</div>
	</div>
</div>
</body>
</html>
