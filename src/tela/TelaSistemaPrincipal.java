package tela;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class TelaSistemaPrincipal extends JFrame implements ActionListener {

    //permite jinternalframe aparecer. controle das telas
    public static JDesktopPane jdp = new JDesktopPane();

    //------ criando menus --------- //
    public JMenuBar jmb = new JMenuBar();
    public JMenu jmCadastros = new JMenu("Cadastros");
    public JMenu jmMovimentos = new JMenu("Movimentos");
    public JMenu jmRelatorios = new JMenu("Relatorios");

    // ---- colocando os itens de menus ---------//
    public JMenuItem jmiEstado = new JMenuItem("Estado");
    public JMenuItem jmiCidade = new JMenuItem("Cidade");
    public JMenuItem jmiFornecedor = new JMenuItem("Fornecedor");

    // ----- criando os itens de menus movimentos e relatorios -------//
    public JMenuItem jmiProdutoCadas = new JMenuItem("Cadastro de Produtos");
    public JMenuItem jmiCompras = new JMenuItem("Entrada de Produtos");
    public JMenuItem jmiVendas = new JMenuItem("Saida de Produtos");

    public JMenuItem jmiBalanco = new JMenuItem("Balanço");

    public TelaSistemaPrincipal() {
        //----------tela do sistema ----------//
        getContentPane().add(jdp);
        //jdp.setBackground(Color.LIGHT_GRAY);

        setTitle("Sistema de Cadastro");
        //setSize(800,600);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setJMenuBar(jmb);
        jmb.add(jmCadastros);
        jmb.add(jmMovimentos);
        jmb.add(jmRelatorios);

        //adicionar dentro do menu cadastro
        jmCadastros.add(jmiEstado);
        jmCadastros.add(jmiCidade);
        jmCadastros.add(jmiFornecedor);

        //adicionar dentro do menu movimentos
        jmMovimentos.add(jmiProdutoCadas);
        jmMovimentos.add(jmiCompras);
        jmMovimentos.add(jmiVendas);

        //adicionar dentro do menu relatórios
        jmRelatorios.add(jmiBalanco);

        // direcionar cada menu
        jmiEstado.addActionListener(this);
        jmiCidade.addActionListener(this);
        jmiFornecedor.addActionListener(this);

        jmiProdutoCadas.addActionListener(this);
        jmiCompras.addActionListener(this);
        jmiVendas.addActionListener(this);

        jmiBalanco.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        // getSource == quem foi que gerou o listerne
        if (ae.getSource() == jmiEstado) {
            TelaDeCadastroEstado telaDeCadastroEstado = new TelaDeCadastroEstado();
            jdp.add(telaDeCadastroEstado);

        } else if (ae.getSource() == jmiCidade) {
            TelaDeCadastroCidade telaDeCadastroCidade = new TelaDeCadastroCidade();
            jdp.add(telaDeCadastroCidade);

        } else if (ae.getSource() == jmiFornecedor) {
            TelaDeCadastroFornecedor telaDeCadastroFornecedor = new TelaDeCadastroFornecedor();
            jdp.add(telaDeCadastroFornecedor);

        } else if (ae.getSource() == jmiProdutoCadas) {
            TelaDeCadastroProdutos telaDeCadastroProdutos = new TelaDeCadastroProdutos();
            jdp.add(telaDeCadastroProdutos);

        } else if (ae.getSource() == jmiCompras) {
            TelaDeCompras telaDeCompras = new TelaDeCompras();
            jdp.add(telaDeCompras);

        } else if (ae.getSource() == jmiVendas) {
            TelaDeVendas telaDeVendas = new TelaDeVendas();
            jdp.add(telaDeVendas);
        }

    }

}
