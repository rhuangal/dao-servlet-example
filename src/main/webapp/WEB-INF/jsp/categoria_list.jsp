<%-- 
    Document   : index
    Created on : Sep 15, 2017, 8:36:41 AM
    Author     : rober
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Ejemplo de Biblioteca</title>
        <!-- Bootstrap core CSS -->
        <%@include file="template/inc_css.jsp" %>
    </head>

    <body>

        <%@include file="template/header.jsp" %>

        <div class="container-header">
            <section class="content-header">
                <h1>
                    Opción Categoria
                    <small>administración de categorias</small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="#"><i class="fa fa-dashboard"></i> Inicio</a></li>
                    <li><a href="#">Categoria</a></li>
                    <li class="active">Listado</li>
                </ol>
            </section>
        </div>

        <div class="container">

            <div class="row">
                <div class="col-md-12">
                    <h2>Mis Categorias</h2>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Codigo</th>
                                <th>Nombre</th>
                                <th>Operaciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${empty lista}">
                                <tr>
                                    <th colspan="3">No hay categorias registradas.</th>   
                                </tr>                               
                            </c:if>
                            <c:if test="${not empty lista}">
                                <c:forEach items="${lista}" var="item">
                                    <tr>
                                        <td>${item.cod_categoria}</td>
                                        <td>${item.nom_categoria}</td>
                                        <td>
                                            <a href="#" class="btn btn-sm btn-info">
                                                Actualizar
                                            </a>
                                            <a href="#" class="btn btn-sm btn-danger">
                                                Eliminar
                                            </a> 
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </tbody>
                        <tfoot>
                            <tr>
                                <th colspan="3">Total de registros: <c:out value="${totalRec}" /></th>   
                            </tr>
                        </tfoot>
                    </table>        
                </div>
            </div>
            <hr>

            <%@include file="template/footer.jsp" %>
        </div> <!-- /container -->

        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <%@include file="template/inc_js.jsp" %>
    </body>
</html>

