
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="templates/header.jsp" />

<div class="panel panel-default">
	<div class="panel-heading">Mon Panier</div>
	<div class="panel-body">
		<div class="table-responsive">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th class="center centerVerticale">
							<strong>Produit</strong>
						</th>
						<th class="centerVerticale">Nom</th>
						<th class="centerVerticale">Type</th>
						<th class="center centerVerticale">Support</th>
						<th class="centerVerticale">Prix unitaire</th>
						<th class="center centerVerticale">Quantité</th>
						<th class="centerVerticale">
							<strong>Total</strong>
						</th>
						<th class="center">
							<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#popUpDeletePanier">Supprimer tout le panier</button>
						</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="listProducts" value="${products}" />
					<c:forEach var="product" items="${listProducts}" varStatus="counter">
						<tr class="productCart">
							<td class="center">
								<a href="/product/${product.type}/${product.nameTagDateReleased}">
									<img src="/resources/img/product/<c:out value="${product.urlPicture}" />" class="imageCart">
								</a>
							</td>
							<td class="centerVerticale">
								<a href="/product/${product.type}/${product.nameTagDateReleased}"> ${product.name} </a>
							</td>
							<td class="centerVerticale">
								<a href="${product.urlType}">${product.typeHtml}</a>
							</td>
							<td class="centerVerticale center">
								<img data-toggle="tooltip" data-placement="left" title="Format : ${product.support}" src="/resources/img/${product.support}.png" />
							</td>
							<td class="centerVerticale">
								<span class="priceProduct${product.id}${product.type}" data-price="<c:out value="${product.price}" />">
									<fmt:formatNumber value="${product.price}" type="currency" />
								</span>
							</td>
							<td class="centerVerticale center">
								<div class="input-number-cart">
									<input type="text" class="form-control numberProduct numberProduct${product.id}${product.type}" data-type="${product.type}" data-id="${product.id}" data-idtype="${product.id}${product.type}" value="${product.numberProduct}" />
								</div>
								<div class="">
									<button class="btn btn-default minus" data-type="${product.type}" data-id="${product.id}" data-idtype="${product.id}${product.type}" type="button">-</button>
									<button class="btn btn-default plus" data-type="${product.type}" data-id="${product.id}" data-idtype="${product.id}${product.type}" type="button">+</button>
								</div>
							</td>
							<td class="centerVerticale">
								<span data-price="${product.totalProduct}" class="totalProduct${product.id}${product.type} totalProduct">${product.totalProduct}</span>
								€
							</td>
							<td class="centerVerticale center">
								<button class="btn btn-danger center deleteProduct" data-type="${product.type}" data-id="${product.id}" type="button">x</button>
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="5"></td>
						<td class="totalCart centerVerticale">Total :</td>
						<td colspan="2">
							<span class="priceTotal">${priceTotal}</span>
							€
						</td>
					</tr>

				</tbody>
			</table>
			<input type="submit" class="btn btn-primary input-cart" value="Valider mon panier" />

		</div>

	</div>
</div>
<div class="modal fade" id="popUpDeletePanier" tabindex="-1" role="dialog" aria-labelledby="popUpDeletePanier">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Suppression du panier</h4>
      </div>
      <div class="modal-body">
        Voulez-vous réellement supprimer le panier ?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger suppressionPanier" data-dismiss="modal">Oui</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal">Non</button>
      </div>
    </div>
  </div>
</div>
<script src="/resources/js/cart.js"></script>

<jsp:include page="templates/footer.jsp" />