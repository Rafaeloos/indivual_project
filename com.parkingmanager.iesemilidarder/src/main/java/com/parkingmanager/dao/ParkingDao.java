package com.parkingmanager.dao;
import java.sql.*;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import com.parkingmanager.dto.Clientes;
import com.parkingmanager.dto.Parking;
import com.parkingmanager.dao.DBConnection;

public class ParkingDao {

	public List<Parking> listar() {
		String SQL_SELECT = "SELECT id_prk, mun_prk, plazas_prk, dir_prk, horario_prk, precio_prk " + " FROM plazas_parking";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Parking parking = null;
		List<Parking> parkings = new ArrayList<>();try {
			conn = DBConnection.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT);
			rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id_prk");
				String municipio = rs.getString("mun_prk");
				Integer plazas = rs.getInt("plazas_prk");
				String direccion = rs.getString("dir_prk");
				LocalDateTime horario = (rs.getTimestamp("horario_prk")).toLocalDateTime();
				Double precio = rs.getDouble("precio_prk");

				parking = new Parking(id, municipio, plazas, direccion, horario, precio);
				parkings.add(parking);
			}
			}catch (SQLException ex) {
				ex.printStackTrace(System.out);
			} finally {
				DBConnection.close(rs);
				DBConnection.close(stmt);
				DBConnection.close(conn);
			}
			return parkings;
	}
	
	//recuperar un parking según id
	
	public Parking findById(Parking parking) {
		String SQL_SELECT_BY_ID = "SELECT id_prk, mun_prk, plazas_prk, dir_prk, horario_prk, precio_prk  "
				+ " FROM plazas_parking WHERE id_prk = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
			stmt.setInt(1, parking.getId());
			rs = stmt.executeQuery();
			rs.absolute(1); // nos posicionamos en el primer registro devuelto

			String municipio = rs.getString("mun_prk");
			Integer plazas = rs.getInt("plazas_prk");
			String direccion = rs.getString("dir_prk");
			Date horario = rs.getDate("horario_prk");
			Double precio = rs.getDouble("precio_prk");

			parking.setMunicipio(municipio);
			parking.setPlazas(plazas);
			parking.setDireccion(direccion);
			LocalDateTime localDateTime = horario.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			parking.setHorario(localDateTime);
			parking.setPrecio(precio);

		}catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {
			DBConnection.close(rs);
			DBConnection.close(stmt);
			DBConnection.close(conn);
		}
		return parking;
	}
	
	//crear parkings en la bd
	
	public int create(Parking parking) {
		String SQL_INSERT = "INSERT INTO plazas_parking(id_prk, mun_prk, plazas_prk, dir_prk, horario_prk, precio_prk) "
				+ " VALUES(?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;
		try {
			conn = DBConnection.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);
			stmt.setInt(1, parking.getId());
			stmt.setString(2, parking.getMunicipio());
			stmt.setInt(3, parking.getPlazas());
			stmt.setString(4, parking.getDireccion());
			stmt.setTimestamp(5, Timestamp.valueOf(parking.getHorario()));
			stmt.setDouble(6, parking.getPrecio());
		
System.out.println(parking.toString());
			rows = stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {
			DBConnection.close(stmt);
			DBConnection.close(conn);
		}
		return rows;
	}
	
	//Modificar un parking de la base de datos
	
		public int update(Parking parking) {
			String SQL_UPDATE = "UPDATE parking "
					+ " SET mun_prk=?, plazas_prk=?, dir_prk=?, horario_prk=?, precio_prk=? WHERE id_prk=?";
			Connection conn = null;
			PreparedStatement stmt = null;
			int rows = 0;
			try {
				conn = DBConnection.getConnection();
				stmt = conn.prepareStatement(SQL_UPDATE);
				int i = 1;
				stmt.setString(i++, parking.getMunicipio());
				stmt.setInt(i++, parking.getPlazas());
				stmt.setString(i++, parking.getDireccion());
				stmt.setTimestamp(5, Timestamp.valueOf(parking.getHorario()));
				stmt.setDouble(i++, parking.getPrecio());

				rows = stmt.executeUpdate();
			} catch (SQLException ex) {
				ex.printStackTrace(System.out);
			} finally {
				DBConnection.close(stmt);
				DBConnection.close(conn);
			}
			return rows;
		}
		
		//Borrar un parking de la base de datos
		
		public int delete(Parking parking) {
			String SQL_DELETE = "DELETE FROM plazas_parking WHERE id_prk = ?";
			Connection conn = null;
			PreparedStatement stmt = null;
			int rows = 0;
			try {
				conn = DBConnection.getConnection();
				stmt = conn.prepareStatement(SQL_DELETE);
				stmt.setInt(1, parking.getId());
				rows = stmt.executeUpdate();
			} catch (SQLException ex) {
				ex.printStackTrace(System.out);
			} finally {
				DBConnection.close(stmt);
				DBConnection.close(conn);
			}
			return rows;
		}

		public Parking findByCode(Parking parking) {
			// TODO Auto-generated method stub
			return null;
		}
}