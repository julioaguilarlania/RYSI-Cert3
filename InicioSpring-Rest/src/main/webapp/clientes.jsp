<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
        String contexto = request.getContextPath();
        %>
    <body>
        <h1>Clientes</h1>
        
        <div>
            <form method="POST" action="<%=contexto%>/altaclientes">
                <table><tbody>
                        <tr>
                            <td>Nombre:</td>
                            <td><input type="text" name="nombre" id="nombre"></td>
                        </tr>
                        <tr>
                            <td>Email:</td>
                            <td><input type="text" name="email" id="email"></td>
                        </tr>
                        <tr><td></td>
                            <td><input type="submit" value="Guardar"></td></tr>
                    </tbody></table>
            </form>
        </div>
        <table>
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>E-mail</th>
                    <th>Fecha Registro</th>
                </tr>
            </thead>
            <tbody>
        <c:forEach items="${listaClientes}" var="cliente">
            <tr>
                <td>${cliente.nombre}</td>
                <td>${cliente.email}</td>
                <td>${cliente.fechaRegistro}</td>
            </tr>
        </c:forEach>
            </tbody>
        </table>
    </body>
</html>
