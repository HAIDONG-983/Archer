<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <link rel="stylesheet" href="${ctx}/css/github.css">
    <script src="${ctx}/js/highlight.pack.js"></script>
    <script>
        hljs.initHighlightingOnLoad();
    </script>

</head>
<body>
<pre>
		<code class="xml" style="font-size: 16px">
            ${xmlResource }
        </code>
	</pre >
</body>
</html>
