package fiap.tds.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import java.time.LocalDateTime;


@Entity
@Table(name = "Event")
public class Event extends PanacheEntity {
        @Enumerated(EnumType.STRING)
        private TypeEvent typeEvent;
        private String local_event;
        private String description;
        private String position;
        private LocalDateTime date_event;
        @Enumerated(EnumType.STRING)
        private Status status;


    public Event() {
    }

    public Event(TypeEvent typeEvent, String local_event, String description, String position, LocalDateTime date_event, Status status) {
        this.typeEvent = typeEvent;
        this.local_event = local_event;
        this.description = description;
        this.position = position;
        this.date_event = date_event;
        this.status = status;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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


