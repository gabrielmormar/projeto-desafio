package desafio.empresas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String estado;
    private String cidade;
    private String cep;

    @JsonIgnore
    @ManyToOne()
    private Empresa empresa;

    public Endereco() {
    }

    public Endereco(Long id, String logradouro, String numero, String complemento, String bairro, String estado, String cidade, String cep, Empresa empresa) {
        this.id = id;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.estado = estado;
        this.cep = cep;
        this.cidade = cidade;
        this.empresa = empresa;
    }
}