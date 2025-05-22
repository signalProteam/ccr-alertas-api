package fiap.tds.services;

import fiap.tds.dtos.EventDTO;
import fiap.tds.models.Event;
import fiap.tds.models.Status;
import fiap.tds.repositores.EventRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class EventService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(EventService.class);

    @Inject
    EventRepository eventRepository;

    @Transactional
    //This method will be used to register the event in the database, POST request
    public void reportEvent(EventDTO eventDTO){
        var event = new Event();
        event.setTypeEvent(eventDTO.getTypeEvent());
        event.setLocal_event(eventDTO.getLocal_event());
        event.setDescription(eventDTO.getDescricao());
        event.setPosition(eventDTO.getTypeEvent().getCargoResponsavel());
        event.setDate_event(LocalDateTime.now());
        event.setStatus(Status.SEM_RESPOSTA);
        eventRepository.persistEvent(event);
        log.info("Evento reportado com sucesso!");
    }

    //This method will be used to get all events for position, GET request
    public List<Event> getAllEventsByPosition(String position){
        var events = eventRepository.getAllEvents();
        return events.stream()
                .filter(e -> e.getPosition().equals(position))
                .filter(e -> e.getStatus().equals(Status.SEM_RESPOSTA))
                .toList();
    }

    //This method will be used to get events by position and methods that has the status FINALIZADO, GET request
    public List<Event> getAllResolvedEventsByPosition(String position){
        var events = eventRepository.getAllEvents();
        return events.stream()
                .filter(e -> e.getPosition().equals(position)
                        && e.getStatus().equals(Status.FINALIZADO))
                .toList();
    }



    //This method will resolve the event, PUT request, by the order of the status
    @Transactional
    public void resolveEvent(Long id){
        var event = eventRepository.findById(id);
        if(event != null){
            if(event.getStatus().equals(Status.SEM_RESPOSTA)){
                event.setStatus(Status.EM_ANDAMENTO);
            } else if (event.getStatus().equals(Status.EM_ANDAMENTO)){
                event.setStatus(Status.FINALIZADO);
            } else if (event.getStatus().equals(Status.AJUDA_SOLICITADA)){
                event.setStatus(Status.EM_ANDAMENTO);
            }
            eventRepository.persistEvent(event);
            log.info("Evento foi resolvido e atualizado com sucesso!");
        }
    }

    //This method will be used to get all events only for ADMIN, GET request
    //Rangel will implement this method, yet
    public List<Event> getAllEvents(){
        var events = eventRepository.getAllEvents();
        return events.stream()
                .toList();
    }

    //This method will be used to update an event to AJUDA_SOLICITADA in the database and also the path
    // MonitorEventResource
    @Transactional
    public void requestHelp(Long id, String descricao){
        var event = eventRepository.findById(id);
        if(event == null){
            log.info("Evento não encontrado");
            return;
        }
        if(event.getStatus().equals(Status.FINALIZADO)){
            log.info("Este evento já foi finalizado");
        }
        else if(event.getStatus().equals(Status.EM_ANDAMENTO)){
            event.setStatus(Status.AJUDA_SOLICITADA);
            event.setDescription(descricao);
            eventRepository.persist(event);
            log.info("Ajuda solicitada com sucesso!");
        }
    }





}
