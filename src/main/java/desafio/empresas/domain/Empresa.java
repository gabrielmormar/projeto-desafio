package desafio.empresas.domain;

import desafio.empresas.enums.TipoEmpresa;
import io.swagger.annotations.ApiModel;
import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ApiModel(description = "Modelo de empresa")
public class Empresa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer tipoEmpresa;
    private String cnpj;
    private String razaoSocial;
    private String email;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();

    public Empresa() {
    }

    public Empresa(Long id, String nome, TipoEmpresa tipoEmpresa, String cnpj, String razaoSocial, String email) {
        super();
        this.id = id;
        this.nome = nome;
        this.tipoEmpresa = (tipoEmpresa == null) ? null : tipoEmpresa.getCod();
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.email = email;
    }
}
