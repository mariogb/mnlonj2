/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mnlonj2.auth;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.providers.AuthoritiesFetcher;
import io.micronaut.security.authentication.providers.DelegatingAuthenticationProvider;
import javax.inject.Singleton;
import io.micronaut.security.authentication.providers.DelegatingAuthenticationProvider;
import io.micronaut.security.authentication.providers.PasswordEncoder;
import io.micronaut.security.authentication.providers.UserFetcher;
import io.micronaut.security.authentication.providers.UserState;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mnlonj2.pojo.UserLon;
import org.reactivestreams.Publisher;

/**
 *
 * @author mario
 */
@Singleton
@Replaces(DelegatingAuthenticationProvider.class)
public class CustomDelegatingAuthenticationProvider extends DelegatingAuthenticationProvider {

  public CustomDelegatingAuthenticationProvider(UserFetcher userFetcher, PasswordEncoder passwordEncoder, AuthoritiesFetcher authoritiesFetcher) {
    super(userFetcher, passwordEncoder, authoritiesFetcher);
  }

  @Override
  protected Publisher<AuthenticationResponse> createSuccessfulAuthenticationResponse(AuthenticationRequest authenticationRequest, UserState userState) {
    if (userState instanceof UserLon) {
      UserLon user = (UserLon) userState;

      List<Long> deptos = new ArrayList<>(20);//dBService.findDepartaments(user.id)
      Publisher<List<String>> findAuthoritiesByUsername = authoritiesFetcher.findAuthoritiesByUsername(user.getUsername());
      
      Flowable.fromPublisher(findAuthoritiesByUsername).map((List<String> authorities) -> {
        Map<String, Object> map = new HashMap<>();
        
        //  public UserLonDetails(String pname,String pkey,Long id,String username, Collection<String> roles, Map<String, Object> map) {

        return new UserLonDetails(user.getPname(), user.getPkey(), user.getId(),user.getUsername(),authorities, map);
      });
      
    }
    return super.createSuccessfulAuthenticationResponse(authenticationRequest, userState);
  }
}
