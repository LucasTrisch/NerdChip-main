package comNerdChip.NerdChip.models;

import jakarta.persistence.*;
import lombok.extern.java.Log;

@Entity
@Table (name ="Pedidos") 
public class Pedido {
    @Id
    private Long id;
    private int numero;
    private float preco;
    private String lista;
    private String pagamento;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    
    public Pedido(){}

    public Pedido(Long id,int numero,Double preco,String lista,String pagamento){
    this.id = id;
    this.numero = numero;
    this.preco = preco;
    this.lista = lista;
    this.pagamento = pagamento;
}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getLista() {
        return lista;
    }

    public void setLista(String lista) {
        this.lista = lista;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }


}
