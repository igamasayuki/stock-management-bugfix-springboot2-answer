<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/common.jsp"  %>
<body>
<div class="container">
	<sec:authorize access="hasRole('ROLE_MEMBER') and isAuthenticated()">
							<sec:authentication var="memberName" property="principal.member.name" />
								<c:out value="${memberName}" />&nbsp;さん
						</sec:authorize>
	　こんにちは！<br>
	<a href="${pageContext.request.contextPath}/logout">ログアウト</a>
	<a href="${pageContext.request.contextPath}/book/form">書籍情報追加はこちら</a>
	<h3>書籍在庫数変更画面</h3>
	<div class="span8">
		<div class="row">
			<form:errors path="bookForm.*" />
			<table class="table table-striped">
			  <tr>
			    <th>
			      書籍名
			    </th>
			    <td>
			      <c:out value= "${book.name}"/>
			    </td>
			  </tr>
			  <tr>
			    <th>
			      著者
			    </th>
			    <td>
			      <c:out value="${book.author}" />
			    </td>
			  </tr>
			  <tr>
			    <th>
			      出版社
			    </th>
			    <td>
			      <c:out value="${book.publisher}" />
			    </td>
			  </tr>
			  <tr>
			    <th>
			      価格
			    </th>
			    <td>
			      <fmt:formatNumber pattern="###,###" value="${book.price}"/>円
			    </td>
			  </tr>
			  <tr>
			    <th>
			      ISBNコード
			    </th>
			    <td>
			      <c:out value="${book.isbncode}"/>
			    </td>
			  </tr>
			  <tr>
			    <th>
			      発売日
			    </th>
			    <td>
			      <fmt:formatDate pattern="yyyy年MM月dd日" value="${book.saledate}"/>
			    </td>
			  </tr>
			  <tr>
			    <th>
			      説明
			    </th>
			    <td>
			      <c:out value="${book.explanation}"/>
			    </td>
			  </tr>
			  <tr>
			    <th>
			      画像
			    </th>
			    <td>
			      <img src="img/<c:out value="${book.image}"/>"/>
			    </td>
			  </tr>
			  <tr>
			    <th>
			      在庫数
			    </th>
			    <td>
			    	<form:errors path="bookForm.*"/>
					<form:form action="${pageContext.request.contextPath}/book/update" method="post" modelAttribute="bookForm">
						<input type="text" name="stock"  value="<c:out value="${book.stock}"/>">
						<input type="hidden" name="id" value="<c:out value="${book.id}"/>">
						<input class="btn" type="submit" value="更新">
					</form:form>
			    </td>
			  </tr>
			</table>
		</div>
	</div>
</div>
</body>
</html>
