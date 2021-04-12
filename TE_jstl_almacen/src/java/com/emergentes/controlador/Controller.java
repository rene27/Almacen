package com.emergentes.controlador;

import com.emergentes.modelo.GestorTareas;
import com.emergentes.modelo.Tarea;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Tarea objTarea = new Tarea();
        int id;
        int pos;
        String op = request.getParameter("op");
        
        if(op.equals("nuevo")){
            HttpSession ses = request.getSession();
            GestorTareas almacen = (GestorTareas) ses.getAttribute("almacen");
            objTarea.setId(almacen.obtieneId());
            
            request.setAttribute("op", op);
            request.setAttribute("miTarea", objTarea);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        }
        if(op.equals("modificar")){
            id = Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            GestorTareas almacen = (GestorTareas) ses.getAttribute("almacen");
            pos = almacen.ubicarTarea(id);
            objTarea = almacen.getLista().get(pos);
            
            request.setAttribute("op", op);
            request.setAttribute("miTarea", objTarea);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        }
        if(op.equals("eliminar")){
            id = Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            GestorTareas almacen = (GestorTareas) ses.getAttribute("almacen");
            pos = almacen.ubicarTarea(id);
            almacen.eliminarTarea(pos);
            ses.setAttribute("almacen", almacen);
            response.sendRedirect("index.jsp");
        }    
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Tarea objTarea = new Tarea();
        int pos;
        String op = request.getParameter("op");
        
        if(op.equals("grabar")){
            objTarea.setId(Integer.parseInt(request.getParameter("id")));
            objTarea.setProducto(request.getParameter("producto"));
            objTarea.setPrecio(Double.parseDouble(request.getParameter("precio")));
            objTarea.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
            
            HttpSession ses = request.getSession();
            GestorTareas almacen = (GestorTareas) ses.getAttribute("almacen");
            
            String opg = request.getParameter("opg");
            if(opg.equals("nuevo")){
                almacen.insertarTarea(objTarea);
            }
            else{
                pos = almacen.ubicarTarea(objTarea.getId());
                almacen.modificarTarea(pos, objTarea);
            }
            ses.setAttribute("almacen", almacen);
            response.sendRedirect("index.jsp");
        }
    }

}
