<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login page</title>
		<link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet"></link>
		<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
		<link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
	</head>

	<body>
		<div id="mainWrapper">
			<div class="login-container">
				<div class="login-card">
					<div class="login-form">
						<c:url var="loginUrl" value="/logon" />
						<form action="${loginUrl}" method="post" class="form-horizontal">
<%-- 							<c:if test="${param.error != null}"> --%>
<!-- 								<div class="alert alert-danger"> -->
<!-- 									<p>Invalid username and password.</p> -->
<!-- 								</div> -->
<%-- 							</c:if> --%>
<%-- 							<c:if test="${param.logout != null}"> --%>
<!-- 								<div class="alert alert-success"> -->
<!-- 									<p>You have been logged out successfully.</p> -->
<!-- 								</div> -->
<%-- 							</c:if> --%>
							<div class="input-group input-sm">
								<label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
								<input type="text" class="form-control" id="Email" name="email" placeholder="Enter Email" required>
							</div>
							<div class="input-group input-sm">
								<label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label> 
								<input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
							</div>
							<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
								
							<div class="form-actions">
								<input type="submit"
									class="btn btn-block btn-primary btn-default" value="Log in">
							</div>
							<sec:authorize access="hasRole('ROLE_Admin')">

								This content will only be visible to users who have the "supervisor" authority in their list of <tt>GrantedAuthority</tt>s.

							</sec:authorize>		
						</form>
					</div>
				</div>
			</div>
		</div>

	</body>
</html>