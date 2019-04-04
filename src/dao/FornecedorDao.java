package dao;

import bd.BancoDados;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import pojo.Fornecedor;

public class FornecedorDao {

    private final String SQL_INCLUIR = "INSERT INTO FORNECEDOR VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String SQL_ALTERAR = "UPDATE FORNECEDOR "
            + "SET NOME = ?, CNPJ = ?, ENDERECO = ?, BAIRRO = ?, CEP = ?, CADASTRO = ?, ATIVO = ?, IDCIDADE = ? WHERE ID = ?";
    private final String SQL_EXCLUIR = "DELETE FROM FORNECEDOR WHERE ID = ?";
    private final String SQL_CONSULTAR = "SELECT * FROM FORNECEDOR WHERE ID = ?";
    public static final String SQL_PESQUISAR = "SELECT FORNECEDOR.ID, FORNECEDOR.NOME, FORNECEDOR.CNPJ,"
            + " FORNECEDOR.ENDERECO, FORNECEDOR.BAIRRO, FORNECEDOR.CEP, FORNECEDOR.CADASTRO , FORNECEDOR.IDCIDADE,"
            + " FORNECEDOR.ATIVO FROM FORNECEDOR, CIDADE WHERE FORNECEDOR.IDCIDADE = CIDADE.ID ORDER BY FORNECEDOR.NOME";
    //public static final String SQL_PESQUISAR = "SELECT * FROM FORNECEDOR ORDER BY NOME";
    public static final String SQL_COMBOBOX = "SELECT ID, NOME FROM FORNECEDOR ORDER BY NOME";
    private Fornecedor fornecedor;

    public FornecedorDao(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public boolean inserir() {
        try {
            PreparedStatement ps = BancoDados.getConexao().prepareStatement(SQL_INCLUIR);
            ps.setInt(1, fornecedor.getId());
            ps.setString(2, fornecedor.getNome());
            ps.setString(3, fornecedor.getCnpj());
            ps.setString(4, fornecedor.getEndereco());
            ps.setString(5, fornecedor.getBairro());
            ps.setString(6, fornecedor.getCep());
            ps.setDate(7, new java.sql.Date(fornecedor.getCadastro().getTime()));
            ps.setString(8, fornecedor.getAtivo());
            ps.setInt(9, fornecedor.getCidade().getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nao foi possivel incluir o Fornecedor");
            return false;
        }
    }

    public boolean alterar() {
        try {
            PreparedStatement ps = BancoDados.getConexao().prepareStatement(SQL_ALTERAR);

            ps.setString(1, fornecedor.getNome());
            ps.setString(2, fornecedor.getCnpj());
            ps.setString(3, fornecedor.getEndereco());
            ps.setString(4, fornecedor.getBairro());
            ps.setString(5, fornecedor.getCep());
            ps.setDate(6, new java.sql.Date(fornecedor.getCadastro().getTime()));
            ps.setString(7, fornecedor.getAtivo());
            ps.setInt(8, fornecedor.getCidade().getId());
            ps.setInt(9, fornecedor.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nao foi possivel alterar o Fornecedor");
            return false;
        }
    }

    public boolean excluir() {
        try {
            PreparedStatement ps = BancoDados.getConexao().prepareStatement(SQL_EXCLUIR);
            ps.setInt(1, fornecedor.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nao foi possivel excluir o Fornecedor");
            return false;
        }
    }

    public boolean consultar() {
        try {
            PreparedStatement ps = BancoDados.getConexao().prepareStatement(SQL_CONSULTAR);
            ps.setInt(1, fornecedor.getId());
            ResultSet rs = ps.executeQuery();
            if (rs == null) {
                return false;
            }
            if (rs.next()) {
                fornecedor.setNome(rs.getString("NOME"));
                fornecedor.setCnpj(rs.getString("CNPJ"));
                fornecedor.setEndereco(rs.getString("ENDERECO"));
                fornecedor.setBairro(rs.getString("BAIRRO"));
                fornecedor.setCep(rs.getString("CEP"));
                //new java.sql.Date(fornecedor.getCadastro().getTime()));
                //SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy");
                fornecedor.setCadastro(rs.getDate("CADASTRO"));
                fornecedor.setAtivo(rs.getString("ATIVO"));
                fornecedor.getCidade().setId(rs.getInt("IDCIDADE"));
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Fornecedor não encontrado(" + fornecedor.getId() + ")");
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nao foi possivel incluir o Fornecedor");
            return false;
        }
    }

}
