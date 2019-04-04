/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componente;

import bd.BancoDados;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author rbord
 */
public class MeuDBComboBox extends JComboBox implements MeuComponente{
    private String sql;
    private boolean obrigatorio;
    private String dica;
    private List<Integer> pks;
    
    public MeuDBComboBox(String sql, boolean obrigatorio, String dica){
        this.obrigatorio = obrigatorio;
        this.dica = dica;
        preencher(sql);
    }
    
    public void preencher(String sql){
        this.sql = sql;
        pks = new ArrayList();
        removeAllItems();
        pks.add(-1);
        addItem("Selecione...");
        List<String[]>dados = BancoDados.executaQuery(sql);
        for (int i = 0; i < dados.size(); i++) {
            pks.add(Integer.parseInt(dados.get(i)[0]));
            addItem(dados.get(i)[1]);
        }
        
    }

    @Override
    public boolean eObrigatorio() {
       return obrigatorio;
    }

    @Override
    public boolean eVazio() {
       return  getSelectedIndex() <= 0;
    }

    @Override
    public boolean eValido() {
       return true;
    }

    @Override
    public String getDica() {
       return dica;
    }

    @Override
    public void limpar() {
        setSelectedIndex(0);
    }

    @Override
    public void habilitar(boolean status) {
       setEnabled(status);
    }
    
    public Integer getValor(){
        return pks.get(getSelectedIndex());
    }
    
    public void setValor(int pk){
        for (int i = 0; i < pks.size(); i++) {
            if (pks.get(i) == pk) {
                setSelectedIndex(i);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, getDica() + " Não encontrado(a).");
    }    
}
