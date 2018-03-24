/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc192;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "FrutasServlet", urlPatterns = {"/frutas.html"})
public class FrutasServlet extends HttpServlet implements Comparator<String> {

    @Override
    public int compare(String fruta1, String fruta2){
    return fruta1.length() - fruta2.length();
  }
    private List<String> frutas;
    
    public FrutasServlet(){
        frutas = new ArrayList<String>();
        frutas.add("Banana");
        frutas.add("Morango");
        frutas.add("Melão");
        frutas.add("Maça");
        frutas.add("Uva");
    }
        
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String comando = request.getParameter("comando");
        try (PrintWriter out = response.getWriter()) {
            if("ordenaNome".equals(comando)){
                Collections.sort(frutas);
            }
            if("ordenaTamanho".equals("comando")){
                Collections.sort(frutas, this);
                //Collections.sort(frutas, (a,b)->a.length()-b.length());
            }
            if(!("ordenaNome".equals(comando)) && !("ordenaTamanho".equals(comando))
                    || ("ordenaRandom".equals(comando))){
                Collections.shuffle(frutas);
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Frutas</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Frutas</h1>");
            out.println("<ul>");
            for (String fruta: frutas){
                out.println("<li>" + fruta + "</li>");
            }
            out.println("</ul>");
            out.println("<p> <a href='?comando=ordenaNome'> Ordenar por nome </a> </p>");
            out.println("<p> <a href='?comando=ordenaTamanho'> Ordenar por tamanho </a> </p>");
            out.println("<p> <a href='?comando=ordenaRandom'> Ordenar Padrão </a> </p>");
            out.println("</body>");
            out.println("</html>");
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
        processRequest(request, response);
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
        processRequest(request, response);
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
