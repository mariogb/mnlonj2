/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mnlonj2.serv;

import io.micronaut.configuration.postgres.reactive.PgPoolClientFactory;
import io.reactiverse.reactivex.pgclient.PgIterator;
import io.reactiverse.reactivex.pgclient.PgPool;
import io.reactiverse.reactivex.pgclient.PgRowSet;
import io.reactiverse.reactivex.pgclient.Row;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 * @author mario
 */
@Singleton
public class DBLon {

  @Inject
  PgPoolClientFactory pgPoolClientFactory;


  
  
  public Single<Map> asMap(String sql) {
    PgPool client = pgPoolClientFactory.client();
    Single<Map> map = client.rxQuery(sql).map(new Function<PgRowSet, Map>() {
      @Override
      public Map apply(PgRowSet pgRowSet) throws Exception {

        Map m = new HashMap();
        PgIterator iterator = pgRowSet.iterator();
        List<String> columnsNames = pgRowSet.columnsNames();
        final int ncols = columnsNames.size();
        while (iterator.hasNext()) {
          final Row row = iterator.next();
          Object key = row.getValue(0);
          final List<Object> r = new ArrayList(ncols - 1);
          for (int i = 1; i < ncols; i++) {
            final Object value = row.getValue(i);
            r.add(value);
          }
          m.put(key, r);
        }
        return m;
      }

    });

    return map;
  }

  public Single<Map> exec(String sql) {
    PgPool client = pgPoolClientFactory.client();

    Single<Map> map = client.rxQuery(sql).map(new Function<PgRowSet, Map>() {
      @Override
      public Map apply(PgRowSet pgRowSet) throws Exception {
        final List l = new ArrayList();
        List<String> columnsNames = pgRowSet.columnsNames();
        final int ncols = columnsNames.size();
        PgIterator iterator = pgRowSet.iterator();
        while (iterator.hasNext()) {
          final List r = new ArrayList(ncols);
          final Row row = iterator.next();
          for (int i = 0; i < ncols; i++) {
            final Object value = row.getValue(i);
            r.add(value);

          }
          l.add(r);

        }
        final Map m = new HashMap();
        m.put("columns", columnsNames);
        m.put("l", l);
        return m;

      }

    });
    return map;
  }

}
