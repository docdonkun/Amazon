<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../templates/header.jsp" />
<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Filtre :</h3>
	</div>
	<div class="panel-body">

		<form action="/liste-tous-les-produits" class="formAll" method="get">

			<div class="blocFiltre">
				Années :
				<select id="years" name="years">
					<option value="AllYears">Toutes les années</option>
					<option value="2010" <c:if test="${years == '2010' }">selected</c:if>>Années 2010 et +</option>
					<option value="2000" <c:if test="${years == '2000' }">selected</c:if>>Années 2000 - 2009</option>
					<option value="1990" <c:if test="${years == '1990' }">selected</c:if>>Années 1990 - 1999</option>
					<option value="1980" <c:if test="${years == '1980' }">selected</c:if>>Années 1980 - 1989</option>
					<option value="1970" <c:if test="${years == '1970' }">selected</c:if>>Années 1970 - 1979</option>
					<option value="1960" <c:if test="${years == '1960' }">selected</c:if>>Années 1960 - 1969</option>
					<option value="1950" <c:if test="${years == '1950' }">selected</c:if>>Années 1950 - 1959</option>
					<option value="1940" <c:if test="${years == '1940' }">selected</c:if>>Années 1940 - 1949</option>
					<option value="1939" <c:if test="${years == '1939' }">selected</c:if>>Années antérieur</option>
				</select>
			</div>

			<div class="blocFiltre">
				<input type="submit" class="btn btn-primary" value="Filtrer" />
			</div>
		</form>
	</div>
</div>

<div class="clearBoth"></div>



	<jsp:include page="listProduct.jsp" />



<div class="clearBoth"></div>
<div class="paginationAll center100"></div>

<script>
	var varNumberPage = ${numberPage};
	var varStartPage = ${startPage};
</script>

<script src="/resources/js/jquery.twbsPagination.min.js"></script>
<script src="/resources/js/listeProduct.js"></script>


<jsp:include page="../templates/footer.jsp" />