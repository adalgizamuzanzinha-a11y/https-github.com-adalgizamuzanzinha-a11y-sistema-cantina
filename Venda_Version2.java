package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Venda {
    private int id;
    private Date data;
    private List<ItemVenda> itens;
    private double total;
    private String cliente;
    private String vendedor;
    
    public Venda(int id, String cliente, String vendedor) {
        this.id = id;
        this.data = new Date();
        this.itens = new ArrayList<>();
        this.total = 0;
        this.cliente = cliente;
        this.vendedor = vendedor;
    }
    
    public void adicionarItem(Produto produto, int quantidade, String variedade) {
        if (produto.getQuantidade() >= quantidade) {
            ItemVenda item = new ItemVenda(produto, quantidade, variedade);
            itens.add(item);
            total += item.getSubtotal();
            produto.setQuantidade(produto.getQuantidade() - quantidade);
        }
    }
    
    // Getters
    public int getId() { return id; }
    public Date getData() { return data; }
    public List<ItemVenda> getItens() { return itens; }
    public double getTotal() { return total; }
    public String getCliente() { return cliente; }
    public String getVendedor() { return vendedor; }
}