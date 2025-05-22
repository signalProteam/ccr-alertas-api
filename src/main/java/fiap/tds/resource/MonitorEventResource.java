package fiap.tds.resource;

import fiap.tds.services.EventService;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/monitorar-eventos")
public class MonitorEventResource {
    @Inject
    EventService eventService;


    @GET
    @Path("/{position}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation( summary = "Lista todos os eventos por cargo", description = "Retorna uma lista com todos os eventos por cargo")
    @APIResponses(value = {
            @APIResponse( responseCode = "200", description = "Lista de eventos retornada com sucesso!"),
            @APIResponse( responseCode = "500", description = "Erro ao listar eventos")
    })
    public Response getAllEventsByPosition(@PathParam("position") String position){
        var events = eventService.getAllEventsByPosition(position);
        return Response.ok(events).build();
    }


    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @Operation( summary = "Atualiza o status do evento", description = "Atualiza o status do evento de acordo com o tipo do STATUS")
    @APIResponses(value = {
            @APIResponse( responseCode = "200", description = "Status do evento atualizado com sucesso!"),
            @APIResponse( responseCode = "500", description = "Erro ao atualizar o status do evento")
    })
    public Response reportEvent(@PathParam("id") Long id){
        eventService.resolveEvent(id);
        return Response.ok("Status do evento foi atualizado com sucesso!").build();
    }




    @GET
    @Path("/admin")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation( summary = "Lista todos os eventos", description = "Retorna uma lista com todos os eventos")
    @APIResponses(value = {
            @APIResponse( responseCode = "200", description = "Lista de eventos retornada com sucesso!"),
            @APIResponse( responseCode = "500", description = "Erro ao listar eventos")
    })
    public Response getAllEvents(){
        var events = eventService.getAllEvents();
        return Response.ok(events).build();
    }



}
