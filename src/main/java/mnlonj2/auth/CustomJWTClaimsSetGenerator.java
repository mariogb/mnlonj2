/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mnlonj2.auth;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.security.token.config.TokenConfiguration;
import io.micronaut.security.token.jwt.generator.claims.ClaimsAudienceProvider;
import io.micronaut.security.token.jwt.generator.claims.JWTClaimsSetGenerator;
import io.micronaut.security.token.jwt.generator.claims.JwtIdGenerator;
import io.reactivex.annotations.Nullable;
import javax.inject.Singleton;
import com.nimbusds.jwt.JWTClaimsSet;
import io.micronaut.security.authentication.UserDetails;

/**
 *
 * @author mario
 */
@Singleton
@Replaces(JWTClaimsSetGenerator.class)
public class CustomJWTClaimsSetGenerator extends JWTClaimsSetGenerator {

  CustomJWTClaimsSetGenerator(TokenConfiguration tokenConfiguration, @Nullable JwtIdGenerator jwtIdGenerator, @Nullable ClaimsAudienceProvider claimsAudienceProvider) {
    super(tokenConfiguration, jwtIdGenerator, claimsAudienceProvider);
  }

  protected void populateWithUserDetails(JWTClaimsSet.Builder builder, UserDetails userDetails) {
    System.out.println("ddddddddddddddddddddddddddddddd populateWithUserDetails");
    super.populateWithUserDetails(builder, userDetails);

    if (userDetails instanceof UserLonDetails) {
      UserLonDetails uld = (UserLonDetails) userDetails;
      System.out.println("----------------->>>>>> " + uld.getUsername());
      builder.claim("typeLon", "AA")
              .claim("dddd", "ddddd")
              .claim("pname", uld.getUsername())
              .build();
    }

  }
}
