package componente;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

public class MeuCampoTexto extends JTextField implements FocusListener, MeuComponente{
    private boolean obrigatorio;
    private String dica;

    public MeuCampoTexto(int colunas, boolean obrigatorio ,String dica) {
        this.obrigatorio = obrigatorio;
        this.dica = dica;
        setColumns(colunas);
        addFocusListener(this);
    }
    

    @Override
    public void focusGained(FocusEvent fe) {
        setBackground(Color.yellow);
        
    }

    @Override
    public void focusLost(FocusEvent e) {
        setBackground(Color.WHITE);
    }

    @Override
    public boolean eVazio() {
       /* String conteudo = getText();
        String conteudoSemEspacos = conteudo.trim();
        return conteudoSemEspacos.isEmpty();*/        
        return getText().trim().isEmpty();
    }

    @Override
    public void limpar() {
        setText("");
    }

    @Override
    public void habilitar(boolean status) {
        setEnabled(status);
    }
    
   
   
    @Override
    public String getDica(){
        return dica;
    }

    @Override
    public boolean eObrigatorio() {
        return obrigatorio;
    }
    
    
    @Override
    public boolean eValido(){
        return true;
    }
    
}
