﻿<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es-ES" variant="euro"/>

<section id="clientes">
    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado de Clientes</h4>
                    </div>
                    <table class="table table-hover">
                        <thead class="thead-green">
                            <tr>
                                <th>#</th>
                                <th>Nombre</th>
                              	<th>Apellidos</th>
                                <th>email</th>
                                 <th>---</th>
                                
                              
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Iteramos cada elemento de la lista de clientes -->
                            <c:forEach var="client" items="${client}" varStatus="status" >
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${product.name}</td>
                                    <td>${product.surname}</td>
                                    <td>${product.email}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/product?action=edit&codeProduct=${client.id}"
                                           class="btn btn-secondary">
                                            <i class="fas fa-edit"></i> Borrar
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <!--Inicio Tarjetas para los totales-->
            <div class="col-md-3">
                <div class="card text-center bg-success text-white mb-3">
                <div class="card text-center bg-success text-white mb-3">
                    <div class="card-body">
                        <h3>Total Clientes</h3>
                        <h4 class="display-4">
                            <i class="fas fa-users"></i> ${totalClientes}
                        </h4>
                    </div>
                </div>        
            </div>
            <!--Fin Tarjetas para los totales-->
            
        </div>
    </div>
</section>
                        