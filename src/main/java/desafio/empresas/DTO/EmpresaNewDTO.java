package desafio.empresas.DTO;

import desafio.empresas.enums.TipoEmpresa;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class EmpresaNewDTO {
    private Long id;
    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 3, max = 120, message = "Nome inválido")
    private String nome;

    @NotNull(message = "Preenchimento obrigatório")
    private Integer tipoEmpresa;

    @NotEmpty(message = "Preenchimento obrigatório")
    @CNPJ
    private String cnpj;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String razaoSocial;

    @NotEmpty(message = "Email obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String logradouro;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String numero;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String complemento;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String bairro;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String estado;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String cidade;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 5, max = 9, message = "Nome inválido")
    private String cep;

}
