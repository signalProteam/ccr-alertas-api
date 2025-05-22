package fiap.tds.dtos;

import fiap.tds.models.Event;
import fiap.tds.models.Status;
import fiap.tds.models.TypeEvent;

import java.time.LocalDateTime;

//This class will be used to send the response to frontend, for the GET request in "monitorar-evento" endpoint
public class EventResponseDTO  {
    private TypeEvent typeEvent;
    private String position;
    private String local_event;
    private String description;
    private LocalDateTime date_event;
    private Status status;

    public EventResponseDTO(TypeEvent typeEvent, String position, String local_event, String description, LocalDateTime date_event, Status status) {
        this.typeEvent = typeEvent;
        this.position = position;
        this.local_event = local_event;
        this.description = description;
        this.date_event = date_event;
        this.status = status;
    }

    public TypeEvent getTypeEvent() {
        return typeEvent;
    }

    public void setTypeEvent(TypeEvent typeEvent) {
        this.typeEvent = typeEvent;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLocal_event() {
        return local_event;
    }

    public void setLocal_event(String local_event) {
        this.local_event = local_event;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate_event() {
        return date_event;
    }

    public void setDate_event(LocalDateTime date_event) {
        this.date_event = date_event;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
