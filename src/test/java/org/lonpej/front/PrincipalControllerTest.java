package org.lonpej.front;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;
import io.micronaut.test.annotation.MicronautTest;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class PrincipalControllerTest {

  @Inject
  EmbeddedServer embeddedServer;

  @Test
  public void testIndex() throws Exception {

    System.out.println("embeddedServer.getURL()" + embeddedServer.getURL());

    try (RxHttpClient client = embeddedServer.getApplicationContext().createBean(RxHttpClient.class, embeddedServer.getURL())) {

      assertEquals(HttpStatus.OK, client.toBlocking().exchange("/principal").status());
    }
  }

  @Test
  public void testLoginGood() throws Exception{
    testLogin0("admin","1234");
  }
  
  @Test
  public void testLoginBad(){
    try {
      testLogin0("admin","12345");
    } catch (Exception ex) {
      
      if(ex instanceof HttpClientResponseException){
       HttpClientResponseException e2 =  ((HttpClientResponseException)ex);
       
        System.out.println("resultado" + e2.getStatus()+ " mesg"+e2.getMessage());
        HttpResponse<?> response = e2.getResponse();
        System.out.println("status  --> "+response.getStatus() + " "+response.code());
        
        assertEquals(response.getStatus(), HttpStatus.UNAUTHORIZED);
      
      }
      

      
    }
  }
  
  
  protected void testLogin0(String username, String password) throws Exception {
    UsernamePasswordCredentials creds = new UsernamePasswordCredentials(username, password);

    RxHttpClient client0 = embeddedServer.getApplicationContext().createBean(RxHttpClient.class, embeddedServer.getURL());

    System.out.println("embeddedServer.getURL()" + embeddedServer.getURL());

    MutableHttpRequest<UsernamePasswordCredentials> requestLogin = HttpRequest.POST("/login", creds);

    HttpResponse<BearerAccessRefreshToken> rsp = client0.toBlocking().exchange(requestLogin, BearerAccessRefreshToken.class);
    String accessToken = rsp.body().getAccessToken();
    System.out.println("accessToken = " + accessToken);


  }

}
