package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class BancoDados {
     private static Connection conexao;
    
    public static Connection getConexao(){
     try {
         if(conexao == null){
            
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            conexao = DriverManager.getConnection(
                    "jdbc:firebirdsql://localhost/"
                            + "D:\\BancoDados\\SITEMACADASTRO.FDB", "SYSDBA", "masterkey");
         }
         return conexao;
            
        } 
     catch (ClassNotFoundException e) {
            
            JOptionPane.showMessageDialog(null, "erro no driver de acesso");
            return null;
        }
     catch(SQLException e){
            
            JOptionPane.showMessageDialog(null, "erro ao conectar o banco de dados");
            return null;
        } 
        
    
    }
    
    public static List<String[]> executaQuery(String sql){
            try {
                List<String[]> dados = new ArrayList();
                Statement st = getConexao().createStatement();
                ResultSet rs = st.executeQuery(sql);
                int numeroColunas = rs.getMetaData().getColumnCount();
                while (rs.next()) {
                    String[] linha = new String[numeroColunas];
                    for (int i = 1; i <= numeroColunas; i++) {
                        linha[i -1] = rs.getString(i);
                    }
                    dados.add(linha);
                    
                }
                return dados;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Nao foi possivel consultar o banco de dados.");
                return new ArrayList();
            }

}
    
}
