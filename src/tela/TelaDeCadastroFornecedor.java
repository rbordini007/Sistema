package tela;

import componente.MeuCampoTexto;
import componente.MeuDBComboBox;
import dao.CidadeDao;
import dao.EstadoDao;
import dao.FornecedorDao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import pojo.Cidade;
import pojo.Estado;
import pojo.Fornecedor;

public class TelaDeCadastroFornecedor extends TelaCadastro {

    SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
    public Fornecedor fornecedor = new Fornecedor();
    public FornecedorDao fornecedorDao = new FornecedorDao(fornecedor);

    public Cidade cidade = new Cidade();
    public CidadeDao cidadeDao = new CidadeDao(cidade);
    public Estado estado = new Estado();
    public EstadoDao estadoDao = new EstadoDao(estado);
    private MeuCampoTexto campoId = new MeuCampoTexto(10, true, "Codigo");
    private MeuCampoTexto campoNome = new MeuCampoTexto(50, true, "Nome");
    private MeuCampoTexto campoCnpj = new MeuCampoTexto(18, true, "CNPJ");
    private MeuCampoTexto campoEndereco = new MeuCampoTexto(50, true, "Endereço");
    private MeuCampoTexto campoBairro = new MeuCampoTexto(50, true, "Bairro");
    private MeuCampoTexto campoCep = new MeuCampoTexto(9, true, "Cep");
    private MeuCampoTexto campoCadastroData = new MeuCampoTexto(10, true, "Cadastro");
    public MeuCampoTexto campoAtivo = new MeuCampoTexto(2, true, "Ativo");
    private MeuDBComboBox campoCidade = new MeuDBComboBox(CidadeDao.SQL_COMBOBOX, true, "Cidade");

    public TelaDeCadastroFornecedor() {
        super("Cadastro Fornecedor");
        adicionaComponentes(1, 1, 1, 1, campoId);
        adicionaComponentes(2, 1, 1, 1, campoNome);
        adicionaComponentes(3, 1, 1, 1, campoCnpj);
        adicionaComponentes(4, 1, 1, 1, campoEndereco);
        adicionaComponentes(5, 1, 1, 1, campoBairro);
        adicionaComponentes(6, 1, 1, 1, campoCep);
        adicionaComponentes(7, 1, 1, 1, campoCadastroData);
        adicionaComponentes(8, 1, 1, 1, campoAtivo);
        adicionaComponentes(9, 1, 1, 1, campoCidade);
        pack();
        habilitaComponentes(false);
    }

    //SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
    public void setPersistencia() {

        fornecedor.setId(Integer.parseInt(campoId.getText()));
        fornecedor.setNome(campoNome.getText());
        fornecedor.setCnpj(campoCnpj.getText());
        fornecedor.setEndereco(campoEndereco.getText());
        fornecedor.setBairro(campoBairro.getText());
        fornecedor.setCep(campoCep.getText());
        try {
            fornecedor.setCadastro(dataFormatada.parse(campoCadastroData.getText()));
        } catch (ParseException pe) {
            pe.printStackTrace();
            //JOptionPane.showMessageDialog(null, "não foi feita conversão data");
        }
        fornecedor.setAtivo(campoAtivo.getText());
        fornecedor.getCidade().setId(campoCidade.getValor());
    }

    @Override
    public void incluirBD() {
        setPersistencia();
        fornecedorDao.inserir();

    }

    @Override
    public void alterarBD() {
        setPersistencia();
        fornecedorDao.alterar();
    }

    @Override
    public void excluirBD() {
        fornecedorDao.excluir();
        super.excluirBD();
    }

    @Override
    public void consultar() {
        super.consultar();
        new TelaConsulta(this, "Consulta de Fornecedor",
                new String[]{"Código", "Nome", "Cnpj", "Endereco", "Bairro", "Cep", "Data", "Cidade", "Ativo"},
                fornecedorDao.SQL_PESQUISAR);
    }

    @Override
    public void preencherDados(int pk) {
        fornecedor.setId(pk);
        fornecedorDao.consultar();
        campoId.setText("" + fornecedor.getId());
        campoNome.setText(fornecedor.getNome());
        campoCnpj.setText(fornecedor.getCnpj());
        campoEndereco.setText(fornecedor.getEndereco());
        campoBairro.setText(fornecedor.getBairro());
        campoCep.setText(fornecedor.getCep());
        campoCadastroData.setText("" + fornecedor.getCadastro());
        campoAtivo.setText(fornecedor.getAtivo());
        campoCidade.setValor(fornecedor.getCidade().getId());

        super.preencherDados(pk);
    }

}
