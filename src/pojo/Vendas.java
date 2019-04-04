package pojo;

public class Vendas {

    private int id;
    private Produtos produtosVendas = new Produtos();
    private double preco;
    private int quantidadeVendida;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produtos getProdutosVendas() {
        return produtosVendas;
    }

    public void setProdutosVendas(Produtos produtosVendas) {
        this.produtosVendas = produtosVendas;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

}
