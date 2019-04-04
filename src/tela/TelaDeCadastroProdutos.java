package tela;

import componente.MeuCampoTexto;
import componente.MeuDBComboBox;
import dao.CidadeDao;
import dao.FornecedorDao;
import dao.ProdutosDao;
import java.text.SimpleDateFormat;
import pojo.Cidade;
import pojo.Produtos;

/**
 *
 * @author rbord
 */
public class TelaDeCadastroProdutos extends TelaCadastro {

    SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
    public Produtos produtos = new Produtos();
    public ProdutosDao produtosDao = new ProdutosDao(produtos);
    public Cidade cidade = new Cidade();
    public CidadeDao cidadeDao = new CidadeDao(cidade);
    private MeuCampoTexto campoId = new MeuCampoTexto(10, true, "Codigo");
    private MeuCampoTexto campoNomeProduto = new MeuCampoTexto(50, true, "Nome");
    private MeuCampoTexto campoEstoque = new MeuCampoTexto(18, true, "CNPJ");
    private MeuDBComboBox campoFornecedor = new MeuDBComboBox(FornecedorDao.SQL_COMBOBOX, true, "Cidade");

    public TelaDeCadastroProdutos() {
        super("Cadastro Produtos");
        adicionaComponentes(1, 1, 1, 1, campoId);
        adicionaComponentes(3, 1, 1, 1, campoNomeProduto);
        adicionaComponentes(4, 1, 1, 1, campoEstoque);
        adicionaComponentes(2, 1, 1, 1, campoFornecedor);

        pack();
        habilitaComponentes(false);
    }

    //SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
    public void setPersistencia() {

        produtos.setId(Integer.parseInt(campoId.getText()));
        produtos.setNomeProduto(campoNomeProduto.getText());
        produtos.setEstoque(Integer.parseInt(campoEstoque.getText()));
        produtos.getFornecedor().setId(campoFornecedor.getValor());
    }

    @Override
    public void incluirBD() {
        setPersistencia();
        produtosDao.inserir();

    }

    @Override
    public void alterarBD() {
        setPersistencia();
        produtosDao.alterar();
    }

    @Override
    public void excluirBD() {
        produtosDao.excluir();
        super.excluirBD();
    }

    @Override
    public void consultar() {
        super.consultar();
        new TelaConsulta(this, "Consulta de Produtos",
                new String[]{"Código", "Nome", "estoque", "Fornecedor"},
                produtosDao.SQL_PESQUISAR);
    }

    @Override
    public void preencherDados(int pk) {
        produtos.setId(pk);
        produtosDao.consultar();
        campoId.setText("" + produtos.getId());
        campoNomeProduto.setText(produtos.getNomeProduto());
        campoEstoque.setText("" + produtos.getEstoque());
        campoFornecedor.setValor(produtos.getFornecedor().getId());

        super.preencherDados(pk);
    }

}
