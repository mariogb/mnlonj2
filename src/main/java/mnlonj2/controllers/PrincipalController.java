package mnlonj2.controllers;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.HttpStatus;
import javax.annotation.security.PermitAll;

@Controller("/principal")
public class PrincipalController {

    @Get("/")
    @PermitAll
    public HttpStatus index() {
        return HttpStatus.OK;
    }
}