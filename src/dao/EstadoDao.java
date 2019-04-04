package dao;

import bd.BancoDados;
import pojo.Estado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class EstadoDao {
    private final String SQL_INCLUIR = "INSERT INTO ESTADO VALUES (?, ?, ?, ?)";
    private final String SQL_ALTERAR = "UPDATE ESTADO SET NOME = ?, SIGLA = ?, ATIVO = ? WHERE ID = ?";
    private final String SQL_EXCLUIR = "DELETE FROM ESTADO WHERE ID = ?";
    private final String SQL_CONSULTAR = "SELECT * FROM ESTADO WHERE ID = ?";
    public static final String SQL_PESQUISAR  = "SELECT * FROM ESTADO ORDER BY NOME";
    public static final String SQL_COMBOBOX   = "SELECT ID, NOME FROM ESTADO ORDER BY NOME";    
    private Estado estado; 

    public EstadoDao(Estado estado) {
        this.estado = estado;
    }
    
    public boolean inserir() {
        try{
        PreparedStatement ps = BancoDados.getConexao().prepareStatement(SQL_INCLUIR);
        ps.setInt(1, estado.getId());
        ps.setString(2, estado.getNome());
        ps.setString(3, estado.getSigla());
        ps.setString(4, estado.getAtivo());
        ps.executeUpdate();        
        return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Nao foi possivel incluir o Estado");
            return false;
        }     
    }
    
    public boolean alterar() {
        try{
        PreparedStatement ps = BancoDados.getConexao().prepareStatement(SQL_ALTERAR);        
        ps.setString(1, estado.getNome());
        ps.setString(2, estado.getSigla());
        ps.setString(3, estado.getAtivo());
        ps.setInt(4, estado.getId());
        ps.executeUpdate();        
        return true;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Nao foi possivel alterar o Estado");
            return false;
        }     
    }
    
    public boolean excluir() {
        try{
        PreparedStatement ps = BancoDados.getConexao().prepareStatement(SQL_EXCLUIR);
        ps.setInt(1, estado.getId());
        ps.executeUpdate();        
        return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Nao foi possivel excluir o Estado");
            return false;
        }     
    }
    
    public boolean consultar() {
        try{
        PreparedStatement ps = BancoDados.getConexao().prepareStatement(SQL_CONSULTAR);
        ps.setInt(1, estado.getId());
        ResultSet rs = ps.executeQuery();
        if(rs.next()){       
        estado.setNome(rs.getString("NOME"));
        estado.setSigla(rs.getString("SIGLA"));
        estado.setAtivo(rs.getString("ATIVO"));
        return true;
        }
        else{ 
        JOptionPane.showMessageDialog(null, "Estado não encontrado(" + estado.getId() + ")" );
        return false;
        }      
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Nao foi possivel incluir o Estado");
            return false;
        }     
    }
    
    
    
}
