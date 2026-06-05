
package models;

public class ItemVenda {

    private Produto produto;
    private int quantidade;
    private double subtotal;
    private String variedade;

    public ItemVenda(Produto produto, int quantidade, String variedade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.subtotal = produto.getPreco() * quantidade;
        this.variedade = variedade;
    }

    // Getters
    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public String getVariedade() {
        return variedade;
    }
}
