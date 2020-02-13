package com.parkingmanager.servlet.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.parkingmanager.dao.ClientDao;
import com.parkingmanager.dto.Clientes;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet({ "/client.json", "/listClients.json" })
public class ClientJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ClientJsonServlet() {
        super(); 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String sID = request.getParameter("id");
		String servletPath = request.getServletPath();
		System.out.println(servletPath);
		
		if (servletPath.equals("/product.json")) {
			if (sID!=null) {
				int id = Integer.parseInt(sID);
				Clientes client = new ClientDao().findByCode(new Clientes(id));;
				
				ObjectMapper mapper = new ObjectMapper(); 
				String jsonResult = mapper.writeValueAsString(client);
				
				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.print(jsonResult);
				out.flush();
			}else {
				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.print("{error:'id no trobat'}");
				out.flush();
			}

		}else if (servletPath.equals("/listClients.json")){
			List<Clientes> products = new ClientDao().listar();
			
			ObjectMapper mapper = new ObjectMapper(); 
			String jsonResult = mapper.writeValueAsString(products);
			
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.print(jsonResult);
			out.flush();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}