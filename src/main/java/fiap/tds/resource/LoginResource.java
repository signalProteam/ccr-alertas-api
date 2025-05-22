package fiap.tds.resource;

import fiap.tds.dtos.LoginDTO;
import fiap.tds.services.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import java.util.HashMap;
import java.util.Map;

@Path("/login")
public class LoginResource {

    @Inject
    UserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation( summary = "Realiza o login do usuário", description = "Retorna o cargo do usuário logado")
    @APIResponses( value={
            @APIResponse (responseCode = "200", description = "Login realizado com sucesso!"),
            @APIResponse (responseCode = "401", description = "Credenciais inválidas!"),
    })
    public Response login(LoginDTO loginDTO) {
        boolean success = userService.login(loginDTO.getUsername(), loginDTO.getPassword());
        if (success) {
            String cargo = userService.getCargo(loginDTO.getUsername());

            Map<String, String> resposta = new HashMap<>();
            resposta.put("cargo", cargo);

            return Response.ok(resposta).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Credenciais inválidas!").build();
        }
    }

}


