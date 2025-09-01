package comNerdChip.NerdChip.dtos;

public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private String senha;

    public UsuarioDTO(Long id1, String nome1, String email1, String senha1, String telefone, String endereco) {
    }

    public UsuarioDTO(Long id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        throw new UnsupportedOperationException("Unimplemented method 'getTelefone'");
    }

    public String getEndereco() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}