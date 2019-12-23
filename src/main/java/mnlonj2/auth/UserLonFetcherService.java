/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mnlonj2.auth;

import io.micronaut.security.authentication.providers.UserFetcher;
import io.micronaut.security.authentication.providers.UserState;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import javax.inject.Inject;
import javax.inject.Singleton;
import mnlonj2.pojo.UserLon;
import mnlonj2.serv.BasicUserService;
import org.reactivestreams.Publisher;

/**
 *
 * @author mario
 */
@Singleton
public class UserLonFetcherService implements UserFetcher {

  @Inject
  BasicUserService basicUserService;

  @Override
  public Publisher<UserState> findByUsername(String username) {
    final Single<UserLon> r1 = basicUserService.findByUsername2(username);
    final UserLon u = r1.blockingGet();
    if(u==null){
      return Flowable.empty();
    }
   return (Publisher<UserState>)Flowable.just((UserState)u);
  }

}
