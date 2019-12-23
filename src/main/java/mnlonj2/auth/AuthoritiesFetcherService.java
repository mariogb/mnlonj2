/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mnlonj2.auth;

import io.micronaut.security.authentication.providers.AuthoritiesFetcher;
import io.reactivex.Flowable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;
import org.reactivestreams.Publisher;

/**
 *
 * @author mario
 */
@Singleton 
public class AuthoritiesFetcherService implements AuthoritiesFetcher {

  @Override
  public Publisher<List<String>> findAuthoritiesByUsername(String username) {
      List<String> l = new ArrayList<>();
      l.add("AAAA");
      return Flowable.just(l);
  }
  
}
