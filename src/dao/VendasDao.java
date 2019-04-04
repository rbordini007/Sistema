package dao;

import bd.BancoDados;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import pojo.Vendas;

public class VendasDao {

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
    private Vendas vendas;

    public VendasDao(Vendas vendas) {
        this.vendas = vendas;
    }

    public boolean inserir() {
        try {
            PreparedStatement ps = BancoDados.getConexao().prepareStatement(SQL_INCLUIR);
            ps.setInt(1, vendas.getId());
            ps.setDouble(2, vendas.getPreco());
            ps.setInt(3, vendas.getQuantidadeVendida());
            ps.setInt(4, vendas.getProdutosVendas().getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nao foi possivel incluir o Produtos");
            return false;
        }
    }

    public boolean alterar() {
        try {
            PreparedStatement ps = BancoDados.getConexao().prepareStatement(SQL_ALTERAR);

            ps.setDouble(1, vendas.getPreco());
            ps.setInt(2, vendas.getQuantidadeVendida());
            ps.setInt(3, vendas.getProdutosVendas().getId());
            ps.setInt(4, vendas.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nao foi possivel alterar o Produtos");
            return false;
        }
    }

    public boolean excluir() {
        try {
            PreparedStatement ps = BancoDados.getConexao().prepareStatement(SQL_EXCLUIR);
            ps.setInt(1, vendas.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nao foi possivel excluir o Produtos");
            return false;
        }
    }

    public boolean consultar() {
        try {
            PreparedStatement ps = BancoDados.getConexao().prepareStatement(SQL_CONSULTAR);
            ps.setInt(1, vendas.getId());
            ResultSet rs = ps.executeQuery();
            if (rs == null) {
                return false;
            }
            if (rs.next()) {
                vendas.setPreco(rs.getInt("NOME"));
                vendas.setQuantidadeVendida(rs.getInt("CNPJ"));
                vendas.getProdutosVendas().setId(rs.getInt("IDFORNECEDOR"));
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Fornecedor não encontrado(" + vendas.getId() + ")");
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nao foi possivel incluir o Fornecedor");
            return false;
        }
    }

}
