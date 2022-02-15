package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import model.Cliente;

public class ClienteDAO {
	
	private static final String USERNAME = "root";
	
	private static final String PASSWORD = "HotelC@lifornia2022";
	
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/maisblack";
	
	public static Connection createConnectionToMySQL() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		
		return connection; }
	
	public static void main(String[] args) throws Exception {
		
		Connection con = createConnectionToMySQL();
		
		if (con != null) {
			System.out.println("Conexão obtida com sucesso!" + con);
			
			con.close(); 
			
		}
	}

	public void cadastrar(Cliente cliente) {
		String sql = "INSERT INTO cliente (nome, email, mensagem)" + "VALUES (?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ClienteDAO.createConnectionToMySQL();
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, cliente.getNome());
			pstm.setString(2, cliente.getEmail());
			pstm.setString(3, cliente.getMensagem());
			
			pstm.execute();
		} 
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			
			try {
				
				if (pstm != null) {
					pstm.close();
				}
				
					if (conn != null) {
						conn.close();
					}
			}
			
			catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
