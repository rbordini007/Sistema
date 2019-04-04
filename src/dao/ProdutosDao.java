package dao;

import bd.BancoDados;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import pojo.Fornecedor;
import pojo.Produtos;

public class ProdutosDao {

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
    private Produtos produtos;

    public ProdutosDao(Produtos produtos) {
        this.produtos = produtos;
    }

    public boolean inserir() {
        try {
            PreparedStatement ps = BancoDados.getConexao().prepareStatement(SQL_INCLUIR);
            ps.setInt(1, produtos.getId());
            ps.setString(2, produtos.getNomeProduto());
            ps.setInt(3, produtos.getEstoque());
            ps.setInt(4, produtos.getFornecedor().getId());
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

            ps.setString(1, produtos.getNomeProduto());
            ps.setInt(2, produtos.getEstoque());
            ps.setInt(3, produtos.getFornecedor().getId());
            ps.setInt(4, produtos.getId());
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
            ps.setInt(1, produtos.getId());
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
            ps.setInt(1, produtos.getId());
            ResultSet rs = ps.executeQuery();
            if (rs == null) {
                return false;
            }
            if (rs.next()) {
                produtos.setNomeProduto(rs.getString("NOME"));
                produtos.setEstoque(rs.getInt("CNPJ"));
                // produtos.setEndereco(rs.getString("ENDERECO"));
                // produtos.setBairro(rs.getString("BAIRRO"));
                //produtos.setCep(rs.getString("CEP"));
                //new java.sql.Date(produtos.getCadastro().getTime()));
                //SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy");
                //produtos.setCadastro(rs.getDate("CADASTRO"));
                //produtos.setAtivo(rs.getString("ATIVO"));
                produtos.getFornecedor().setId(rs.getInt("IDFORNECEDOR"));
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Fornecedor não encontrado(" + produtos.getId() + ")");
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nao foi possivel incluir o Fornecedor");
            return false;
        }
    }

}
