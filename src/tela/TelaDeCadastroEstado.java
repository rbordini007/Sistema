package tela;

import componente.MeuCampoTexto;
import dao.EstadoDao;
import pojo.Estado;

public class TelaDeCadastroEstado extends TelaCadastro {

    public Estado estado = new Estado();
    public EstadoDao estadoDao = new EstadoDao(estado);
    public MeuCampoTexto campoCodigo = new MeuCampoTexto(10, true, "Codigo");
    public MeuCampoTexto campoNome = new MeuCampoTexto(50, true, "Nome");
    public MeuCampoTexto campoSigla = new MeuCampoTexto(2, true, "Sigla");
    public MeuCampoTexto campoAtivo = new MeuCampoTexto(1, true, "Ativo");

    public TelaDeCadastroEstado() {
        super("Cadastro Estado");

        adicionaComponentes(1, 1, 1, 1, campoCodigo);
        adicionaComponentes(2, 1, 1, 2, campoNome);
        adicionaComponentes(3, 1, 1, 2, campoSigla);
        adicionaComponentes(4, 1, 1, 2, campoAtivo);
        //adicionaComponentes(5, 3, 1, 3, new JLabel("teste"));
        pack();
        habilitaComponentes(false);

    }

    public void setPersistencia() {
        estado.setId(Integer.parseInt(campoCodigo.getText()));
        estado.setNome(campoNome.getText());
        estado.setSigla(campoSigla.getText());
        estado.setAtivo(campoAtivo.getText());
    }

    @Override
    public void incluirBD() {
        setPersistencia();
        estadoDao.inserir();
    }

    @Override
    public void alterarBD() {
        setPersistencia();
        estadoDao.alterar();
    }

    @Override
    public void excluirBD() {
        estadoDao.excluir();
        super.excluirBD();
    }

    @Override
    public void consultar() {
        super.consultar();
        new TelaConsulta(this, "Consulta de Estado",
                new String[]{"Código", "Nome", "Sigla", "Ativo"},
                EstadoDao.SQL_PESQUISAR);
    }

    @Override
    public void preencherDados(int pk) {
        estado.setId(pk);
        estadoDao.consultar();
        campoCodigo.setText("" + estado.getId());
        campoNome.setText(estado.getNome());
        campoSigla.setText(estado.getSigla());
        campoAtivo.setText(estado.getAtivo());
        super.preencherDados(pk);
    }

}
