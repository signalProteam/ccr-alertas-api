package fiap.tds.models;

public enum TypeEvent {
    CONFLITO_ENTRE_PASSAGEIROS("Seguranca"),
    FUSIVEL_QUEBRADO("Tecnico"),
    ESCADA_ROLANTE_QUEBRADA("Tecnico"),
    PORTA_DE_EMERGENCIA_QUEBRADA("Tecnico"),
    VIDRO_QUEBRADO("Limpeza"),
    LIXEIRA_VAZANDO("Limpeza");


    private final String cargoResponsavel;

    TypeEvent(String cargoResponsavel) {
        this.cargoResponsavel = cargoResponsavel;
    }

    public String getCargoResponsavel() {
        return cargoResponsavel;
    }


}
