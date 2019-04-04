package dao;

import bd.BancoDados;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import pojo.Cidade;

public class CidadeDao {

    private final String SQL_INCLUIR = "INSERT INTO CIDADE VALUES (?, ?, ?, ?)";
    private final String SQL_ALTERAR = "UPDATE CIDADE SET NOME = ?, ATIVO = ?, ESTADO = ? WHERE ID = ?";
    private final String SQL_EXCLUIR = "DELETE FROM CIDADE WHERE ID = ?";
    private final String SQL_CONSULTAR = "SELECT * FROM CIDADE WHERE ID = ?";
    public static final String SQL_PESQUISAR = "SELECT CIDADE.ID, CIDADE.NOME, ESTADO.SIGLA, CIDADE.ATIVO FROM CIDADE, ESTADO WHERE CIDADE.IDESTADO = ESTADO.ID ORDER BY CIDADE.NOME";
    public static final String SQL_COMBOBOX = "SELECT ID, NOME FROM CIDADE ORDER BY NOME";
    private Cidade cidade;

    public CidadeDao(Cidade cidade) {
        this.cidade = cidade;
    }

    public boolean inserir() {
        try {
            PreparedStatement ps = BancoDados.getConexao().prepareStatement(SQL_INCLUIR);
            ps.setInt(1, cidade.getId());
            ps.setString(2, cidade.getNome());
            ps.setString(3, cidade.getAtivo());
            ps.setInt(4, cidade.getEstado().getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nao foi possivel incluir o Cidade");
            return false;
        }
    }

    public boolean alterar() {
        try {
            PreparedStatement ps = BancoDados.getConexao().prepareStatement(SQL_ALTERAR);

            ps.setString(1, cidade.getNome());
            ps.setString(2, cidade.getAtivo());
            ps.setInt(3, cidade.getEstado().getId());
            ps.setInt(4, cidade.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nao foi possivel alterar o Cidade");
            return false;
        }
    }

    public boolean excluir() {
        try {
            PreparedStatement ps = BancoDados.getConexao().prepareStatement(SQL_EXCLUIR);
            ps.setInt(1, cidade.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nao foi possivel excluir o Cidade");
            return false;
        }
    }

    public boolean consultar() {
        try {
            PreparedStatement ps = BancoDados.getConexao().prepareStatement(SQL_CONSULTAR);
            ps.setInt(1, cidade.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cidade.setNome(rs.getString("NOME"));
                cidade.setAtivo(rs.getString("ATIVO"));
                cidade.getEstado().setId(rs.getInt("IDESTADO"));
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Cidade não encontrado(" + cidade.getId() + ")");
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nao foi possivel incluir o Cidade");
            return false;
        }
    }

}
