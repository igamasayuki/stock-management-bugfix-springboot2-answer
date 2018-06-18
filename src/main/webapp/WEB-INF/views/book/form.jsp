<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/common.jsp"%>
<body>
    <div class="container">
	<sec:authorize access="hasRole('ROLE_MEMBER') and isAuthenticated()">
		<sec:authentication property="principal.member.name" />&nbsp;さん
	</sec:authorize>
	　こんにちは！<br>
	<a href="${pageContext.request.contextPath}/logout">ログアウト</a>
	<a href="${pageContext.request.contextPath}/book/form">書籍情報追加はこちら</a>
	                                        
        <h3>書籍情報登録画面</h3>
        <div class="span8">
            <div class="row">
                <form:form modelAttribute="registBookForm" action="${pageContext.request.contextPath}/book/regist" enctype="multipart/form-data" method="POST">
                    <table class="table table-striped">
                        <tr>
                            <th>書籍名</th>
                            <td><form:input path="name" placeholder="Name" />
                            <form:errors path="name" cssStyle="color:red" /></td>
                        </tr>
                        <tr>
                            <th>著者</th>
                            <td><form:input path="author" placeholder="Author" /> <form:errors path="author" cssStyle="color:red" /></td>
                        </tr>
                        <tr>
                            <th>出版社</th>
                            <td><form:input path="publisher" placeholder="Publisher" /> <form:errors path="publisher" cssStyle="color:red" /></td>
                        </tr>
                        <tr>
                            <th>価格</th>
                            <td><form:input path="price" placeholder="Price" /> <form:errors path="price" cssStyle="color:red" /></td>
                        </tr>
                        <tr>
                            <th>ISBNコード</th>
                            <td><form:input path="isbncode" placeholder="X-XXXX-XXXX-X" /> <form:errors path="isbncode" cssStyle="color:red" /></td>
                        </tr>
                        <tr>
                            <th>発売日</th>
                            <td><form:input path="saledate" placeholder="Saledate" /> <form:errors path="saledate" cssStyle="color:red" /></td>
                        </tr>                        
                        <tr>
                            <th>説明</th>
                            <td><form:input path="explanation" placeholder="Explanation" /> <form:errors path="explanation" cssStyle="color:red" /></td>
                        </tr>                        
                        <tr>
                            <th>画像</th>
                            <td> <input name="imageFile" type="file" accept="image/*" required/></td>
                        </tr>                        
                        <tr>
                            <th>在庫数</th>
                            <td><form:input path="stock" placeholder="Stock" /> <form:errors path="stock" cssStyle="color:red" /></td>
                        </tr>                        
                        <tr>
                            <td></td>
                            <td><input class="btn" type="submit" value="登録"></td>
                        </tr>
                    </table>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>