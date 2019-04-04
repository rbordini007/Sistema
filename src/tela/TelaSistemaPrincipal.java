package tela;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class TelaSistemaPrincipal extends JFrame implements ActionListener{
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
        
        // direcionar cada menu 
        jmiEstado.addActionListener(this);
        jmiCidade.addActionListener(this);
        jmiFornecedor.addActionListener(this);
        
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        // getSource == quem foi que gerou o listerne
        if (ae.getSource() == jmiEstado ) {
           TelaDeCadastroEstado telaDeCadastroEstado = new TelaDeCadastroEstado();
           jdp.add(telaDeCadastroEstado);
        }
        else if(ae.getSource() == jmiCidade){
            TelaDeCadastroCidade telaDeCadastroCidade = new TelaDeCadastroCidade();
            jdp.add(telaDeCadastroCidade);
            
        }
        else if(ae.getSource() == jmiFornecedor){
             TelaDeCadastroFornecedor telaDeCadastroFornecedor = new TelaDeCadastroFornecedor();
             jdp.add(telaDeCadastroFornecedor);
             
        }
        
    
    }
    
    
    
}
