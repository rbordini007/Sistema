package tela;

import componente.MeuCampoTexto;
import componente.MeuDBComboBox;
import dao.ComprasDao;
import dao.ProdutosDao;

import pojo.Compras;
import pojo.Produtos;

public class TelaDeCompras extends TelaCadastro {

    public Compras compras = new Compras();
    public ComprasDao comprasDao = new ComprasDao(compras);
    public Produtos produtos = new Produtos();
    public ProdutosDao produtosDao = new ProdutosDao(produtos);
    private MeuCampoTexto campoId = new MeuCampoTexto(10, true, "Codigo");
    private MeuCampoTexto campoPreco = new MeuCampoTexto(50, true, "Nome");
    private MeuCampoTexto campoQuantidade = new MeuCampoTexto(18, true, "CNPJ");
    private MeuDBComboBox campoProduto = new MeuDBComboBox(ProdutosDao.SQL_COMBOBOX, true, "Cidade");

    public TelaDeCompras() {
        super("Cadastro compras");
        adicionaComponentes(1, 1, 1, 1, campoId);
        adicionaComponentes(3, 1, 1, 1, campoPreco);
        adicionaComponentes(4, 1, 1, 1, campoQuantidade);
        adicionaComponentes(2, 1, 1, 1, campoProduto);

        pack();
        habilitaComponentes(false);
    }

    public void setPersistencia() {

        compras.setIdCompras(Integer.parseInt(campoId.getText()));
        compras.setPreco(Double.parseDouble(campoPreco.getText()));
        compras.setQuantidade(Integer.parseInt(campoQuantidade.getText()));
        compras.getProdutosCompra().setId(campoProduto.getValor());
    }

    @Override
    public void incluirBD() {
        setPersistencia();
        comprasDao.inserir();

    }

    @Override
    public void alterarBD() {
        setPersistencia();
        comprasDao.alterar();
    }

    @Override
    public void excluirBD() {
        comprasDao.excluir();
        super.excluirBD();
    }

    @Override
    public void consultar() {
        super.consultar();
        new TelaConsulta(this, "Consulta de Compras",
                new String[]{"Código", "Nome", "estoque", "Fornecedor"},
                comprasDao.SQL_PESQUISAR);
    }

    @Override
    public void preencherDados(int pk) {
        compras.setIdCompras(pk);
        comprasDao.consultar();
        campoId.setText("" + compras.getIdCompras());
        campoPreco.setText("" + compras.getPreco());
        campoQuantidade.setText("" + compras.getQuantidade());
        campoProduto.setValor(compras.getProdutosCompra().getId());

        super.preencherDados(pk);
    }

}
