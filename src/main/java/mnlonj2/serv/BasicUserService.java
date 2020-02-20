/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mnlonj2.serv;

import io.micronaut.configuration.postgres.reactive.PgPoolClientFactory;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import mnlonj2.pojo.UserLon;

import io.reactiverse.reactivex.pgclient.PgIterator;
import io.reactiverse.reactivex.pgclient.PgPool;
import io.reactiverse.reactivex.pgclient.PgRowSet;
import io.reactiverse.reactivex.pgclient.Row;
import io.reactiverse.reactivex.pgclient.Tuple;
import io.reactivex.Flowable;
import io.reactivex.Single;
import java.util.ArrayList;

/**
 *
 * @author mario
 */
@Singleton
public class BasicUserService {

  @Inject
  DBLon dBLon;

  @Inject
  PgPoolClientFactory pgPoolClientFactory;

  String sql0 = "select pname, pkey,password,id from user_lon where username = $1";
//a

  public Single<List<UserLon>> findByUsername2(String username) {

    PgPool client = pgPoolClientFactory.client();
    Tuple arguments = Tuple.tuple();
    arguments.addString(username);
    return client.rxPreparedQuery(sql0, arguments).map((PgRowSet t) -> {

      List<UserLon> l_userLon = new ArrayList<>(1);

      if (t.size() == 1) {
        Row r = t.iterator().next();

        System.out.println("r.getString(0)   " + r.getString(0));
        UserLon userLon = new UserLon(r.getString(0), r.getString(1), username, r.getString(2));
        userLon.setId(r.getLong(3));
        l_userLon.add(userLon);
      }

      return l_userLon;
    });

  }

  public UserLon findByUsername(String username) {
    Single<Map> exec = dBLon.exec(sql0);

    Map m = exec.blockingGet();
    List l = (List) m.get("l");
    if (l != null && l.size() == 0) {

      List r0 = (List) l.get(0);
      final UserLon u = new UserLon(r0.get(0).toString(), r0.get(1).toString(), r0.get(2).toString(), r0.get(3).toString());
      u.setId((Long) r0.get(4));
      return u;
    }
    return null;
  }
}
