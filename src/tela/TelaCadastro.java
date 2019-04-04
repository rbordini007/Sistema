package tela;

import componente.MeuComponente;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TelaCadastro extends JInternalFrame implements ActionListener {
    //componentes

    public List<MeuComponente> componentes = new ArrayList();
    public JPanel jpComponentes = new JPanel();

    // criando jogo de botoes
    public JPanel jpBotoes = new JPanel();
    public JButton jbIncluir = new JButton("Incluir");
    public JButton jbAlterar = new JButton("Alterar");
    public JButton jbExcluir = new JButton("Excluir");
    public JButton jbConsultar = new JButton("Consultar");
    public JButton jbConfirmar = new JButton("Confirmar");
    public JButton jbCancelar = new JButton("Cancelar");

    public final int PADRAO = 0;
    public final int INCLUINDO = 1;
    public final int ALTERANDO = 2;
    public final int EXCLUINDO = 3;
    public final int CONSULTANDO = 4;
    public int estadoTela = PADRAO;
    public boolean temDadosNaTela = false;

    public TelaCadastro(String titulo) {
        //criando a ela de cadastro de estado
        super(titulo, true, true, true, true);
        // setTitle();
        // setSize(400, 300);  -- substituido pelo pack();
        setVisible(true);

        // ------------- components ---------------//
        getContentPane().add(BorderLayout.CENTER, jpComponentes);
        jpComponentes.setBackground(Color.CYAN);
        jpComponentes.setLayout(new GridBagLayout());

        //----------- botoes ------------------ //
        getContentPane().add(BorderLayout.PAGE_END, jpBotoes);
        //jpBotoes.setSize(50,30);
        //jpBotoes.setBackground(Color.yellow);
        jpBotoes.setLayout(new GridLayout(1, 6));
        jpBotoes.add(jbIncluir);
        jpBotoes.add(jbAlterar);
        jpBotoes.add(jbExcluir);
        jpBotoes.add(jbConsultar);
        jpBotoes.add(jbConfirmar);
        jpBotoes.add(jbCancelar);
        //fazer a propria class ouvir
        jbIncluir.addActionListener(this);
        jbAlterar.addActionListener(this);
        jbExcluir.addActionListener(this);
        jbConsultar.addActionListener(this);
        jbConfirmar.addActionListener(this);
        jbCancelar.addActionListener(this);

        pack();
        setVisible(true);
        habilitaBotoes();
    }

    // definindo o posicionamento dos componentes na tela
    public void adicionaComponentes(int linha, int coluna, int linhas, int colunas, JComponent componente) {

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = linha;
        gbc.gridx = coluna;
        if (componente instanceof MeuComponente) {
            gbc.gridheight = 1;
            gbc.gridwidth = 1;
            String texto = ((MeuComponente) componente).getDica() + ": ";
            if (((MeuComponente) componente).eObrigatorio()) {
                texto = texto + "*";
            } else {
                texto = texto + " ";
            }
            JLabel jl = new JLabel(texto);
            gbc.anchor = GridBagConstraints.EAST;
            jpComponentes.add(jl, gbc);
            gbc.gridx++;
        }
        gbc.gridheight = linhas;
        gbc.gridwidth = colunas;
        gbc.anchor = GridBagConstraints.WEST;
        jpComponentes.add(componente, gbc);
        if (componente instanceof MeuComponente) {
            componentes.add((MeuComponente) componente);
        }
    }

    public void habilitaComponentes(boolean status) {
        /*for (int i = 0; i < componentes.size(); i++) {
            componentes.get(i).habilitar(status);
        }*/
        for (MeuComponente componente : componentes) {
            componente.habilitar(status);

        }
    }

    public void limpaComponentes() {
        for (MeuComponente componente : componentes) {
            componente.limpar();
        }
    }

    // habilitando botoes
    public void habilitaBotoes() {
        jbIncluir.setEnabled(estadoTela == PADRAO);
        jbAlterar.setEnabled(estadoTela == PADRAO && temDadosNaTela);
        jbExcluir.setEnabled(estadoTela == PADRAO && temDadosNaTela);
        jbConsultar.setEnabled(estadoTela == PADRAO);
        jbConfirmar.setEnabled(estadoTela != PADRAO);
        jbCancelar.setEnabled(estadoTela != PADRAO);
    }

    @Override //aqui cria o metodo para escrever oque ira acontecer nos botoes
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == jbIncluir) {

            incluir();
        } else if (ae.getSource() == jbAlterar) {
            alterar();
        } else if (ae.getSource() == jbExcluir) {
            excluir();
        } else if (ae.getSource() == jbConsultar) {
            consultar();
        } else if (ae.getSource() == jbConfirmar) {
            confirmar();
        } else if (ae.getSource() == jbCancelar) {
            cancelar();
        }
        habilitaBotoes();

    }

    public void incluir() {
        estadoTela = INCLUINDO;
        limpaComponentes();
        habilitaComponentes(true);
    }

    public void alterar() {
        estadoTela = ALTERANDO;
        habilitaComponentes(true);
    }

    public void excluir() {
        estadoTela = EXCLUINDO;
    }

    public void consultar() {
        estadoTela = CONSULTANDO;
        habilitaBotoes();
    }

    public void confirmar() {
        if (estadoTela == INCLUINDO) {
            if (validaComponentes()) {
                incluirBD();
            } else {
                return;
            }
        } else if (estadoTela == ALTERANDO) {
            if (validaComponentes()) {
                alterarBD();
            } else {
                return;
            }

        } else if (estadoTela == EXCLUINDO) {
            excluirBD();
        }
        estadoTela = PADRAO;
        habilitaComponentes(false);
        habilitaBotoes();
    }

    public void cancelar() {
        estadoTela = PADRAO;
    }

    public void incluirBD() {
        // ESte metodo sera redefinido nas subclasses
    }

    public void alterarBD() {
        limpaComponentes();
        // ESte metodo sera redefinido nas subclasses
    }

    public void excluirBD() {
        // ESte metodo sera redefinido nas subclasses
        limpaComponentes();
    }

    public void preencherDados(int pk) {
        estadoTela = PADRAO;
        temDadosNaTela = true;
        habilitaBotoes();
    }

    public boolean validaComponentes() {
        String erroObrigatorios = "";
        for (int i = 0; i < componentes.size(); i++) {
            if (componentes.get(i).eObrigatorio() && componentes.get(i).eVazio()) {
                erroObrigatorios = erroObrigatorios + componentes.get(i).getDica() + "\n";
            }
        }
        if (erroObrigatorios.isEmpty()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Os campos abaixo são obrigatórios e não foram preenchidos:\n\n" + erroObrigatorios);
            return false;
        }
    }

}
