package fiap.tds.resource;

import fiap.tds.dtos.HelpEventDTO;
import fiap.tds.dtos.HelpRequestDTO;
import fiap.tds.dtos.TypeEventDTO;
import fiap.tds.models.Status;
import fiap.tds.models.TypeEvent;
import fiap.tds.repositores.EventRepository;
import fiap.tds.services.EventService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Path("/solicitar-ajuda")
public class SolicitHelpResource {
    @Inject
    EventService eventService;



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation( summary = "Lista todos os eventos em andamento", description = "Retorna uma lista com todos os eventos em andamento")
    @APIResponses( value={
            @APIResponse (responseCode = "200", description = "Lista de eventos em andamento retornada com sucesso!"),
            @APIResponse (responseCode = "500", description = "Erro ao listar eventos em andamento")
    })
    public List<HelpEventDTO> listEvents(){
        var events = eventService.getAllEvents();
        var eventsFiltered = events.stream()
                .filter(e-> e.getStatus().equals(Status.EM_ANDAMENTO))
                .toList();
        return eventsFiltered.stream()
                .map(e -> new HelpEventDTO(e.id ,e.getTypeEvent().name(), e.getPosition(), e.getStatus()))
                .collect(Collectors.toList());
    }


    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation( summary = "Solicita ajuda para um evento", description = "Solicita ajuda para um evento")
    @APIResponses( value={
            @APIResponse (responseCode = "200", description = "Solicitação de ajuda realizada com sucesso!"),
            @APIResponse (responseCode = "500", description = "Erro ao solicitar ajuda")
    })
    @Path("/{id}")
    public Response requestHelp(@PathParam("id") Long id, HelpRequestDTO helpRequestDTO) {
        eventService.requestHelp(id, helpRequestDTO.getDescricao());
        return Response.ok("Status do evento foi atualizado com sucesso!").build();
    }

}
