<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.emergentes.modelo.Tarea" %>
<%@page import="com.emergentes.modelo.GestorTareas" %>
<%
    if(session.getAttribute("almacen") == null){
        GestorTareas objeto1 = new GestorTareas();
        
        objeto1.insertarTarea(new Tarea(1,"Coca Cola",8.5,100));
        objeto1.insertarTarea(new Tarea(2,"Pepsi",11,50));
        objeto1.insertarTarea(new Tarea(3,"Frack",6,100));
        objeto1.insertarTarea(new Tarea(4,"Serranitas",2.5,80));
        
        session.setAttribute("almacen", objeto1);
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSTL - Almacen</title>
    </head>
    <body>
        <h1>Lista de Almacen</h1>
        <a href="Controller?op=nuevo">Nuevo Producto</a>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Producto</th>
                <th>Precio</th>
                <th>Cantidad</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="item" items="${sessionScope.almacen.getLista()}">
            <tr>
                <td>${item.id}</td>
                <td>${item.producto}</td>
                <td>${item.precio}</td>
                <td>${item.cantidad}</td>
                <td><a href="Controller?op=modificar&id=${item.id}">Modificar</a></td>
                <td><a href="Controller?op=eliminar&id=${item.id}">Eliminar</a></td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>
