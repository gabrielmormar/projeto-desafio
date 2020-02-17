package desafio.empresas.enums;

public enum TipoEmpresa {
    EMPRESAMATRIZ(1, "Matriz"),
    EMPRESAFILIAL(2, "Filial");

    private Integer cod;
    private String nome;

    TipoEmpresa() {
    }

    TipoEmpresa(Integer cod, String nome) {
        this.cod = cod;
        this.nome = nome;
    }

    public static TipoEmpresa toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (TipoEmpresa te : TipoEmpresa.values()) {
            if (cod.equals(te.getCod())) {
                return te;
            }
        }
        throw new IllegalArgumentException("Código inválido");
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
