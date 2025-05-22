package fiap.tds.dtos;

import fiap.tds.models.Event;
import fiap.tds.models.TypeEvent;

//This class will be used for the client write the event
public class EventDTO {
    private TypeEvent typeEvent;
    private String local_event;
    private String descricao;


    public EventDTO() {
    }

    public EventDTO(TypeEvent typeEvent, String local_event, String descricao) {
        this.typeEvent = typeEvent;
        this.local_event = local_event;
        this.descricao = descricao;
    }

    public TypeEvent getTypeEvent() {
        return typeEvent;
    }

    public void setTypeEvent(TypeEvent typeEvent) {
        this.typeEvent = typeEvent;
    }

    public String getLocal_event() {
        return local_event;
    }

    public void setLocal_event(String local_event) {
        this.local_event = local_event;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}



