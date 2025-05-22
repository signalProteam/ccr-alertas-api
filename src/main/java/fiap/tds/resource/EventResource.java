package fiap.tds.resource;

import fiap.tds.dtos.EventDTO;
import fiap.tds.dtos.TypeEventDTO;
import fiap.tds.models.TypeEvent;
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

@Path("/reportar-evento")
public class EventResource {

    @Inject
    EventService eventService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Reporta Evento",description = "Reportar um evento com status SEM_RESPOSTA")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Evento reportado com sucesso!"),
            @APIResponse(responseCode = "400", description = "Erro ao reportar evento")
    })
    public Response reportEvent(EventDTO eventDTO){
        eventService.reportEvent(eventDTO);
        return Response.status(Response.Status.CREATED).entity("Evento reportado com sucesso!").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Lista todos os tipos de evento", description = "Retorna uma lista com todos os tipos de evento disponíveis")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Lista de tipos de evento retornada com sucesso!"),
            @APIResponse(responseCode = "500", description = "Erro ao listar tipos de evento")
    })
    public List<TypeEventDTO> listarTiposEvento() {
        return Arrays.stream(TypeEvent.values())
                .map(t -> new TypeEventDTO(t.name(), t.getCargoResponsavel()))
                .collect(Collectors.toList());
    }


}
