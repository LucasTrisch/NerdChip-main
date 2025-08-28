package comNerdChip.NerdChip.dtos;

import comNerdChip.NerdChip.models.Pedido;
import comNerdChip.NerdChip.models.Produto;

public class PedidoDTO {
    private long id;
    private int numero;
    private float preco;
    private String lista;
    private String pagamento;

    public PedidoDTO(Long long1, int i, float f, String string, String string2) {
    }

    public void ProdutoDTO(Long id, int numero,float preco, String lista, String pagamento) {
        this.id = id;
        this.numero = numero;
        this.lista = lista;
        this.preco = preco;
        this.pagamento = pagamento;
    }

    public PedidoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.numero = pedido.getNumero();
        this.lista = pedido.getLista();
        this.preco = pedido.getPreco();
        this.pagamento = pedido.getPagamento();
    }
    public Pedido toModel() {
        return new Pedido();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNome() {
        return numero;
    }

    public void setNome(int numero) {
        this.numero = numero;
    }

    public String getDescricao() {
        return lista;
    }

    public void setDescricao(String lista) {
        this.lista = lista;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento( String pagamento) {
            this.pagamento = pagamento;
    }

    public String getLista() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLista'");
    }

    public int getNumero() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNumero'");
    }
}
    

