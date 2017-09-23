package org.app.example.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.app.example.bean.Categoria;
import org.app.example.dao.impl.CategoriaDaoImpl;

/**
 * @author roberto huangal diaz
 * @web https://github.com/rhuangal/
 * @version 2.0
 */
@WebServlet(name = "CategoriaServlet", urlPatterns = {"/categoria/listado", "/categoria/nuevo", "/categoria/submit", "/categoria/editar", "/categoria/eliminar"})
public class CategoriaServlet extends HttpServlet {

    private static Logger log = Logger.getLogger(CategoriaServlet.class);

    private static CategoriaDaoImpl categoriaDao = new CategoriaDaoImpl();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, IllegalAccessException, InvocationTargetException {

        String url = request.getServletPath();
        log.info("CONTEXT PATH: " + url);

        switch (url) {
            case "/categoria/listado":
                this.listado(request, response);
                break;
            case "/categoria/nuevo":
                this.nuevo(request, response);
                break;
            case "/categoria/editar":
                this.editar(request, response);
                break;
            case "/categoria/submit":
                this.submit(request, response);
                break;
            case "/categoria/eliminar":
                this.eliminar(request, response);
                break;
        }
    }

    protected void listado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Categoria> lista = categoriaDao.readAll();
        request.setAttribute("lista", lista);
        request.setAttribute("totalRec", lista.size());
        String path = "/WEB-INF/jsp/categoria_list.jsp";
        processUrl(path, request, response);

    }
    
    protected void nuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
        request.setAttribute("operacion", "Nuevo");    
        String path = "/WEB-INF/jsp/categoria_form.jsp";
        processUrl(path, request, response);

    }
    
    protected void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int cod = Integer.parseInt(request.getParameter("codigo"));
        Categoria bean = categoriaDao.read(cod);
        request.setAttribute("categoria", bean);
        request.setAttribute("operacion", "Editar");    
        String path = "/WEB-INF/jsp/categoria_form.jsp";
        processUrl(path, request, response);

    }
    
    protected void submit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
        
        Categoria bean = new Categoria();
        BeanUtils.populate(bean, request.getParameterMap());
        String strategy = request.getParameter("strategy");
        int res = 0;
        log.info("nombre: "+bean.getNom_categoria());
        switch(strategy){
            case "Nuevo":
                res = categoriaDao.create(bean);
                request.getSession().setAttribute("resultado", res);
                response.sendRedirect(request.getContextPath()+"/categoria/nuevo");
                break;
            case "Editar":
                res = categoriaDao.update(bean);
                request.getSession().setAttribute("resultado", res);
                response.sendRedirect(request.getContextPath()+"/categoria/editar?codigo="+bean.getCod_categoria());
                break;
        }     

    }
    
    protected void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
        
        int cod = Integer.parseInt(request.getParameter("codigo"));
        categoriaDao.delete(cod);
        response.sendRedirect(request.getContextPath()+"/categoria/listado");
        
    }
    
    protected void processUrl(String path, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ServletContext ctx = getServletContext();
        RequestDispatcher rd = ctx.getRequestDispatcher(path);
        
        if (rd != null) {
            rd.forward(request, response);
        } else{
            log.error("LA RUTA NO ES VALIDA.");
        }
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (IllegalAccessException ex) {
            log.error(ex);
        } catch (InvocationTargetException ex) {
            log.error(ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (IllegalAccessException ex) {
             log.error(ex);
        } catch (InvocationTargetException ex) {
             log.error(ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
