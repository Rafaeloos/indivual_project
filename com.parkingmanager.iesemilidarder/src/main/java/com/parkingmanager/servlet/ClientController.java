package com.parkingmanager.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.parkingmanager.dao.ClientDao;
import com.parkingmanager.dto.Clientes;

@WebServlet("/product")
public class ClientController extends HttpServlet {
	
private static final long serialVersionUID = -7558166536789234332L;
	   
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recuperam l'acció a realitzar i es crida a la funció corresponent
		String action = request.getParameter("action");
		if (action != null) {
			switch (action) {
			case "edit":
				this.deleteClient(request, response);
				break;
			default:
				this.showListClient(request, response);
			}
		} else {
			this.showListClient(request, response);
		}
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperam l'acció a realitzar i es crida a la funció corresponent
		String action = request.getParameter("action");
		if (action != null) {
			switch (action) {
			case "delete":
				this.deleteClient(request, response);
				break;
			case "insert":
				this.insertClient(request, response);
				break;
			case "update":
				this.updateClient(request, response);
				break;
			default:
				this.showListClient(request, response);
			}
		} else {
			this.showListClient(request, response);
		}
	}



	private void showListClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Clientes> client = new ClientDao().listar();

		System.out.println("clients = " + client);
		
		// Dades a desar a la sessió de la classe
		HttpSession session = request.getSession();
		session.setAttribute("clients", client);
		session.setAttribute("totalClients", client.size());

		// request.getRequestDispatcher("frmProduct.jsp").forward(request, response);
		response.sendRedirect("frmProduct.jsp");
	}

	private void editClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// recuperamos el idProduct
		int idClient = Integer.parseInt(request.getParameter("idClient"));
		Clientes client = new ClientDao().findById(new Clientes(idClient));
		request.setAttribute("client", client);
		String jspEditar = "/editClient.jsp";
		request.getRequestDispatcher(jspEditar).forward(request, response);

	}

	private void insertClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
		
		// recuperamos los valores del formulario agregarProducto
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		System.out.println("name:"+ name + surname);
		String email = request.getParameter("email");
		String user = request.getParameter("user");
		
		/*
		 * String saldoString = request.getParameter("saldo"); if (saldoString != null
		 * && !"".equals(saldoString)) { saldo = Float.parseFloat(saldoString); }
		 */

		// Creamos el objeto de producto (modelo)
		Clientes client = new Clientes(name, surname, email, user, null);

		// Insertamos el nuevo objeto en la base de datos
		int registrosModificados = new ClientDao().create(client);
		System.out.println("Registres modificats:" + registrosModificados);

		// Redirigimos hacia accion por default
		this.showListClient(request, response);
	}

	private void updateClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
		System.out.println("Modificar cliente");
		
		// Recuperam els valors del formulari editProduct
		int idClient = Integer.parseInt(request.getParameter("idClient"));
		String name = request.getParameter("nombre");
		System.out.println("Nombre:" + name);
		
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		
		// Creamos el objeto de cliente (modelo)
		Clientes client = new Clientes(idClient);

		// Modificar el objeto en la base de datos
		int registrosModificados = new ClientDao().update(client);
		System.out.println("Registres modificats:" + registrosModificados);

		// Redirigimos hacia accion por default
		this.showListClient(request, response);
	}

	private void deleteClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// recuperamos los valores del formulario editarCliente
		int idCliente = Integer.parseInt(request.getParameter("idCliente"));

		// Creamos el objeto de producto (modelo)
		Clientes client = new Clientes(idCliente);

		// Eliminamos el objeto en la base de datos
		int registrosModificados = new ClientDao().delete(client);
		System.out.println("Registres modificats:" + registrosModificados);

		// Redirigimos hacia accion por default
		this.showListClient(request, response);
	}

}
