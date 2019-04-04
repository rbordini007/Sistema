package pojo;

public class Compras {

    private int idCompras;
    private Produtos produtosCompra = new Produtos();
    private double preco;
    private int quantidade;

    public int getIdCompras() {
        return idCompras;
    }

    public void setIdCompras(int idCompras) {
        this.idCompras = idCompras;
    }

    public Produtos getProdutosCompra() {
        return produtosCompra;
    }

    public void setProdutosCompra(Produtos produtosCompra) {
        this.produtosCompra = produtosCompra;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}
