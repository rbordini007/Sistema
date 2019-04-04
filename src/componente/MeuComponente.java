
package componente;

public interface MeuComponente {
    public boolean eObrigatorio();
    public boolean eVazio();
    public boolean eValido();
    public String getDica();
    public void limpar();
    public void habilitar(boolean status);    
}
