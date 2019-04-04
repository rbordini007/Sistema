package tela;

import componente.MeuCampoTexto;
import componente.MeuDBComboBox;
import dao.ProdutosDao;
import dao.VendasDao;
import pojo.Produtos;
import pojo.Vendas;

public class TelaDeVendas extends TelaCadastro {

    public Vendas vendas = new Vendas();
    public VendasDao vendasDao = new VendasDao(vendas);
    public Produtos produtos = new Produtos();
    public ProdutosDao produtosDao = new ProdutosDao(produtos);
    private MeuCampoTexto campoId = new MeuCampoTexto(10, true, "Codigo");
    private MeuCampoTexto campoPreco = new MeuCampoTexto(50, true, "Nome");
    private MeuCampoTexto campoQuantidade = new MeuCampoTexto(18, true, "CNPJ");
    private MeuDBComboBox campoProduto = new MeuDBComboBox(ProdutosDao.SQL_COMBOBOX, true, "Cidade");

    public TelaDeVendas() {
        super("Cadastro vendas");
        adicionaComponentes(1, 1, 1, 1, campoId);
        adicionaComponentes(3, 1, 1, 1, campoPreco);
        adicionaComponentes(4, 1, 1, 1, campoQuantidade);
        adicionaComponentes(2, 1, 1, 1, campoProduto);

        pack();
        habilitaComponentes(false);
    }

    public void setPersistencia() {

        vendas.setId(Integer.parseInt(campoId.getText()));
        vendas.setPreco(Double.parseDouble(campoPreco.getText()));
        vendas.setQuantidadeVendida(Integer.parseInt(campoQuantidade.getText()));
        vendas.getProdutosVendas().setId(campoProduto.getValor());
    }

    @Override
    public void incluirBD() {
        setPersistencia();
        vendasDao.inserir();

    }

    @Override
    public void alterarBD() {
        setPersistencia();
        vendasDao.alterar();
    }

    @Override
    public void excluirBD() {
        vendasDao.excluir();
        super.excluirBD();
    }

    @Override
    public void consultar() {
        super.consultar();
        new TelaConsulta(this, "Consulta de Vendas",
                new String[]{"Código", "preco", "quantidade", "Produto"},
                vendasDao.SQL_PESQUISAR);
    }

    @Override
    public void preencherDados(int pk) {
        vendas.setId(pk);
        vendasDao.consultar();
        campoId.setText("" + vendas.getId());
        campoPreco.setText("" + vendas.getPreco());
        campoQuantidade.setText("" + vendas.getQuantidadeVendida());
        campoProduto.setValor(vendas.getProdutosVendas().getId());

        super.preencherDados(pk);
    }

}
