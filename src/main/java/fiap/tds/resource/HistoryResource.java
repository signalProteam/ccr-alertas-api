package fiap.tds.resource;


import fiap.tds.services.EventService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/historico")
public class HistoryResource {

    @Inject
    EventService eventService;
    //This get will be used to get the history of resolved events by position and status
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation( summary = "Lista todos os eventos resolvidos por cargo", description = "Retorna uma lista com todos os eventos resolvidos por cargo")
    @APIResponses(value = {
            @APIResponse (responseCode = "200", description = "Lista de eventos resolvidos retornada com sucesso!"),
            @APIResponse (responseCode = "500", description = "Erro ao listar eventos resolvidos")
    })
    @Path("/{position}")
    public Response getAllResolvedEventsByPosition(@PathParam("position") String position){
        var events = eventService.getAllResolvedEventsByPosition(position);
        return Response.ok(events).build();
    }



    //This get will be used to get the history of all events only for ADMIN
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation( summary = "Lista todos os eventos resolvidos", description = "Retorna uma lista com todos os eventos resolvidos")
    @APIResponses(value = {
            @APIResponse (responseCode = "200", description = "Lista de eventos resolvidos retornada com sucesso!"),
            @APIResponse (responseCode = "500", description = "Erro ao listar eventos resolvidos")
    })
    @Path("/admin")
    public Response getAllEvents(){
        var events = eventService.getAllEvents();
        return Response.ok(events).build();
    }
}
