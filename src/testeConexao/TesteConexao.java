/*
package testeConexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class TesteConexao {
    public static void main(String[] args) {
        
        try {
            Connection conexao;
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            conexao = DriverManager.getConnection(
                    "jdbc:firebirdsql://localhost:3050/D:\\BancoDados\\TESTEJDBC.FDB", "SYSDBA", "masterkey");
            
            
            Statement st = conexao.createStatement();
             //---------- grava no banco ----------//
           // st.executeUpdate("INSERT INTO CLIENTE VALUES (2, 'DAIANE', 'SAO PAULO')");
            
            //-----------MUDAR ALGO DENTRO SETANDO OQUE MUDAR DO BANCO -----------//
          //  st.executeUpdate("UPDATE CLIENTE SET CIDADE = 'SANTO ANDRE'");
            
          //------------- consultar banco de dados -------//
              ResultSet rs = st.executeQuery("SELECT * FROM CLIENTE");
              while (rs.next()) {
                      System.out.println(rs.getInt(1)+ " = " + rs.getString("nome") + " - " + rs.getString("cidade"));
                      
                  }
              
              
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "erro no driver");
            
        } catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "erro ao conectar o banco de dados");
        }   
        
    }
    
}
*/