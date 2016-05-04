package it.polito.tdp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParolaDAO {
	
public List<String> trovaParole(int dim){
		List<String> lista = new ArrayList<String>();
		
        Connection conn = DBConnect.getConnection();
		
		try { 
			String sql = "SELECT nome from parola";
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			
			while( res.next() ){
				if(res.getString("nome").length() == dim )
					lista.add(res.getString("nome"));
			}
			res.close();
			conn.close();
			return lista;
			
		}catch (SQLException e) {
		
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	

}

