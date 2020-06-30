package com.example.rest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.example.rest.entidades.Docente;
import com.example.rest.util.MySqlDBConexion;

public class DocenteModel {
	
	private static final Log log = LogFactory.getLog(DocenteModel.class);
	
	public List<Docente> listarTodosDocentes() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		List<Docente> lista = new ArrayList<Docente>();
		try {
			String sql = "select * from docente";
			conn = MySqlDBConexion.getConexion();
			pstm = conn.prepareStatement(sql);
			log.info(pstm);
			rs = pstm.executeQuery();
			Docente obj = null;
			while (rs.next()) {
				obj = new Docente();
				obj.setIdDocente(rs.getInt(1));
				obj.setNombre(rs.getString(2));
				obj.setApellido(rs.getString(3));
				obj.setSexo(rs.getString(4));
				obj.setFecha(rs.getString(5));
				obj.setDni(rs.getString(6));
				
				lista.add(obj);
			}
		} catch (Exception e) {
			log.info(e);
		} finally {
			try {
				if (rs != null)rs.close();
				if (pstm != null)pstm.close();
				if (conn != null)conn.close();
			} catch (SQLException e) {}
		}
		return lista;
	}
	
	public int insertaDo(Docente obj) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {
			String sql = "insert into docente values(null,?,?,?,?,?)";
			conn = new MySqlDBConexion().getConexion();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setString(2, obj.getApellido());
			pstm.setString(3, obj.getSexo());
			pstm.setString(4, obj.getFecha());
			pstm.setString(5, obj.getDni());
			log.info(pstm);
			salida = pstm.executeUpdate();
		} catch (Exception e) {
			log.info(e);
		} finally {
			try {
				if (pstm != null)pstm.close();
			} catch (SQLException e1) {}
			try {
				if (conn != null)conn.close();
			} catch (SQLException e) {}
		}
		return salida;
	}

	
	public int actualizaDo(Docente obj) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {
			String sql = "update docente set nombre =?, apellido =?, sexo =?, fechadenacimiento =?, dni =? where iddocente =? ";
			conn = new MySqlDBConexion().getConexion();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setString(2, obj.getApellido());
			pstm.setString(3, obj.getSexo());
			pstm.setString(4, obj.getFecha());
			pstm.setString(5, obj.getDni());
			pstm.setInt(6, obj.getIdDocente());
			log.info(pstm);
			
			salida = pstm.executeUpdate();
		} catch (Exception e) {
			log.info(e);
		} finally {
			try {
				if (pstm != null)pstm.close();
			} catch (SQLException e1) {}
			try {
				if (conn != null)conn.close();
			} catch (SQLException e) {}
		}
		return salida;
	}

	
	public List<Docente> consultaDo(int id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		List<Docente> lista = new ArrayList<Docente>();
		try {
			String sql = "select * from docente where iddocente = ?";
			conn = new MySqlDBConexion().getConexion();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			log.info(pstm);
			rs = pstm.executeQuery();
			Docente bean = null;
			while(rs.next()){
				bean = new Docente();
				bean.setIdDocente(rs.getInt(1));
				bean.setNombre(rs.getString(2));
				bean.setApellido(rs.getString(3));
				bean.setSexo(rs.getString(4));
				bean.setFecha(rs.getString(5));
				bean.setDni(rs.getString(6));
				lista.add(bean);
			}
		} catch (Exception e) {
			log.info(e);
		} finally {
			try {
				if (rs != null)rs.close();
				if (pstm != null)pstm.close();
				if (conn != null)conn.close();
			} catch (SQLException e) {}
		}
		return lista;
	}
	
	public int eliminaDo(int id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {
			String sql = "delete from docente where iddocente =?";
			conn = new MySqlDBConexion().getConexion();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			log.info(pstm);
			salida = pstm.executeUpdate();
		} catch (Exception e) {
			log.info(e);
		} finally {
			try {
				if (pstm != null)pstm.close();
			} catch (SQLException e1) {}
			try {
				if (conn != null)conn.close();
			} catch (SQLException e) {}
		}
		return salida;
	}

}
