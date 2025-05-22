package fiap.tds.repositores;

import fiap.tds.models.Event;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class EventRepository implements PanacheRepository<Event> {


    public Event findByName(String name) {
        return find("name", name).firstResult();
    }

    public void persistEvent(Event event) {
        event.persist();
    }


   //this method will return all the events from database
    public List<Event> getAllEvents(){
        return listAll();
    }
}
