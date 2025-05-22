package fiap.tds.dtos;

public class HelpRequestDTO {
    private String descricao;

    public HelpRequestDTO() {
    }

    public HelpRequestDTO(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}