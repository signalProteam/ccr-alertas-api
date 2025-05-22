package fiap.tds.resource;

import fiap.tds.dtos.UserDTO;
import fiap.tds.dtos.UserReponseDTO;
import fiap.tds.services.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/usuarios")
public class UserRegisterResource {

    @Inject
    UserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Cadastra um novo usuário", description = "Cadastra um novo usuário")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Usuário cadastrado com sucesso!"),
            @APIResponse(responseCode = "400", description = "Erro ao cadastrar usuário"),
    })
    public Response register(UserDTO userDTO) {
        userService.register(userDTO);
        return Response.status(Response.Status.CREATED).entity("Usuário cadastrado com sucesso!").build();
    }


    //I'll test this another variation of the update method
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Atualiza um usuário", description = "Atualiza um usuário")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Usuário atualizado com sucesso!"),
            @APIResponse(responseCode = "400", description = "Erro ao atualizar usuário"),
            @APIResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public Response updateUser(@PathParam("id") Long id, UserDTO userDTO) {
        userService.updateUser(id, userDTO);
        return Response.ok("Usuário foi atualizado com sucesso!").build();
    }



    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Busca um usuário por ID", description = "Busca um usuário por ID")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Usuário encontrado com sucesso!"),
            @APIResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public Response findById(@PathParam("id") Long id) {
        var user = userService.findById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        var response = new UserReponseDTO(user.getUsername(), user.getPosition());
        return Response.ok(response).build();
    }

    @DELETE
    @Operation(summary = "Deleta um usuário por ID", description = "Deleta um usuário por ID")
    @APIResponses(value = {
            @APIResponse(responseCode = "204", description = "Usuário deletado com sucesso!"),
            @APIResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Long id) {
        var user = userService.deleteById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        var response = new UserReponseDTO(user.getUsername(), user.getPosition());
        return Response.noContent().entity(response).build();
    }
}
