package com.parkingmanager.dao;
import java.sql.*;
import java.util.*;

import com.parkingmanager.dto.Clientes;
import com.parkingmanager.dao.DBConnection;

public class ClientDao {

	public List<Clientes> listar() {
		String SQL_SELECT = "SELECT idclients, name_cli, surname_cli, email_cli, phone_cli, user_cli " + " FROM clients";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Clientes clientes = null;
		List<Clientes> cliente = new ArrayList<>();try {
			conn = DBConnection.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT);
			rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("idclients");
				String nombre = rs.getString("name_cli");
				String apellidos = rs.getString("surname_cli");
				String email = rs.getString("email_cli");
				Integer telefono = rs.getInt("phone_cli");
				String usuario = rs.getString("user_cli");

				clientes = new Clientes(id, nombre, apellidos, email, telefono, usuario);
				clientes.add(cliente);
			}
			}catch (SQLException ex) {
				ex.printStackTrace(System.out);
			} finally {
				DBConnection.close(rs);
				DBConnection.close(stmt);
				DBConnection.close(conn);
			}
			return cliente;
	}
	
	//recuperar un cliente según id
	
	public Clientes findById(Clientes client) {
		String SQL_SELECT_BY_ID = "SELECT idclients, name_cli, surname_cli, email_cli, phone_cli, user_cli "
				+ " FROM products WHERE pro_code = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
			stmt.setInt(1, client.getId());
			rs = stmt.executeQuery();
			rs.absolute(1); // nos posicionamos en el primer registro devuelto

			String name = rs.getString("name_cli");
			String surname = rs.getString("surname_cli");
			String email = rs.getString("email_cli");
			Integer phone = rs.getInt("phone_cli");
			String user = rs.getString("user_cli");

			client.setNombre(name);
			client.setApellidos(surname);
			client.setEmail(email);
			client.setTelefono(phone);
			client.setUsuario(user);

		}catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {
			DBConnection.close(rs);
			DBConnection.close(stmt);
			DBConnection.close(conn);
		}
		return client;
	}
	
	//crear clientes en la bd
	
	public int create(Clientes client) {
		String SQL_INSERT = "INSERT INTO clients(idclients, name_cli, surname_cli, email_cli, phone_cli, user_cli) "
				+ " VALUES(?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;
		try {
			conn = DBConnection.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);
			stmt.setInt(1, client.getId());
			stmt.setString(2, client.getNombre());
			stmt.setString(3, client.getApellidos());
			stmt.setString(4, client.getEmail());
			stmt.setInt(5,  client.getTelefono());
			stmt.setString(6, client.getUsuario());
			
	
System.out.println(client.toString());
			rows = stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {
			DBConnection.close(stmt);
			DBConnection.close(conn);
		}
		return rows;
	}
	
	//Modificar un cliente de la base de datos
	
	public int update(Clientes client) {
		String SQL_UPDATE = "UPDATE client "
				+ " SET name_cli=?, surname_cli=?, email_cli=?, phone_cli=?, user_cli=? WHERE idclients=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;
		try {
			conn = DBConnection.getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE);
			int i = 1;
			stmt.setString(i++, client.getNombre());
			stmt.setString(i++, client.getApellidos());
			stmt.setString(i++, client.getEmail());
			stmt.setInt(i++, client.getTelefono());
			stmt.setString(i++, client.getUsuario());

			rows = stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {
			DBConnection.close(stmt);
			DBConnection.close(conn);
		}
		return rows;
	}
	
	//Borrar un cliente de la base de datos
	
	public int delete(Clientes client) {
		String SQL_DELETE = "DELETE FROM clients WHERE idclients = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;
		try {
			conn = DBConnection.getConnection();
			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setInt(1, client.getId());
			rows = stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {
			DBConnection.close(stmt);
			DBConnection.close(conn);
		}
		return rows;
	}

	public Clientes findByCode(Clientes clients) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
