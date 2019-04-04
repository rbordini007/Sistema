package tela;

import componente.MeuCampoTexto;
import componente.MeuDBComboBox;
import dao.CidadeDao;
import dao.EstadoDao;
import pojo.Cidade;



public class TelaDeCadastroCidade extends TelaCadastro{
    public Cidade cidade = new Cidade();
    public CidadeDao cidadeDao = new CidadeDao(cidade);
    private MeuCampoTexto campoId = new MeuCampoTexto(10, true, "Codigo");
    private MeuCampoTexto campoNome = new MeuCampoTexto(50, true, "Nome");
    public MeuCampoTexto campoAtivo = new MeuCampoTexto(1, true, "Ativo");
    private MeuDBComboBox campoEstado = new MeuDBComboBox(EstadoDao.SQL_COMBOBOX, true, "Estado");
    

    public TelaDeCadastroCidade() {
        super("Cadastro Cidade");
        adicionaComponentes(1, 1, 1, 1, campoId);
        adicionaComponentes(2, 1, 1, 1, campoNome);
        adicionaComponentes(3, 1, 1, 1, campoAtivo);
        adicionaComponentes(4, 1, 1, 1, campoEstado);
        pack();
    }
    
    public void setPersistencia(){
        cidade.setId(Integer.parseInt(campoId.getText()));
        cidade.setNome(campoNome.getText());
        cidade.setAtivo(campoAtivo.getText());
        cidade.getEstado().setId(campoEstado.getValor());
    }
    
    @Override
     public void incluirBD(){
         setPersistencia();
         cidadeDao.inserir();
     }  
     
    @Override
     public void alterarBD(){
         setPersistencia();
         cidadeDao.alterar();
     }
     
      @Override
    public void excluirBD(){        
        cidadeDao.excluir();
        super.excluirBD();
    }
    
    @Override
    public void consultar(){
        super.consultar();
        new TelaConsulta(this, "Consulta de Cidade",
                new String[] {"Código", "Nome", "Estado", "Ativo"},
                CidadeDao.SQL_PESQUISAR);
    }
    
    @Override
    public void preencherDados(int pk){
        cidade.setId(pk);
        cidadeDao.consultar();
        campoId.setText("" + cidade.getId());
        campoNome.setText(cidade.getNome());
        campoAtivo.setText(cidade.getAtivo());
        campoEstado.setValor(cidade.getEstado().getId());
        
        super.preencherDados(pk);
    }
    
    
   
    
}
