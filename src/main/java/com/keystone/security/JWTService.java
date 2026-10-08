	package com.keystone.security;
	
	import java.nio.charset.StandardCharsets;
	import java.time.LocalDateTime;
	import java.time.ZoneId;
	import java.util.Date;
	
	import javax.crypto.SecretKey;
	
	import org.springframework.stereotype.Service;
	
	import io.jsonwebtoken.Claims;
	import io.jsonwebtoken.Jwts;
	import io.jsonwebtoken.security.Keys;
	
	@Service
	public class JWTService {
	
		private static final String SECRET_KEY="KeystoneProjectSecretKeyForJwtAuthentication2026";
		
		private static final long EXPIRATION_TIME=1000*60*60;   // 1 hrs
		
		
		//Create Signing Key
		private SecretKey getSigningKey() {
			
			return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)
		    );
		}
		
		
		//Generate JWT token 
		public String generateToken(String userEmail,String role) {
			
			return Jwts.builder()
					.subject(userEmail)
				   .claim("role", role)
				   .issuedAt(new Date())
				   .expiration(
						   new Date(System.currentTimeMillis()+EXPIRATION_TIME)
						   )
				   .signWith(getSigningKey())
				   .compact();
		}
		
		
		//Extract userName/email
		public String extractUserName(String token) {
			
			return getClaims(token)
					.getSubject();
		}
	
		
		//Extract Role
		public String extractRole(String token) {
			
			return getClaims(token)
				   .get("role", String.class);
		}
		
		
	    //Get All Claims
		private Claims getClaims(String token) {
			
			return Jwts.parser()
				       .verifyWith(getSigningKey())
				       .build()
				       .parseSignedClaims(token)
				       .getPayload();
		}
		
		
		//Validate Token
		public boolean isTokenValid(String token) {
			
			try {
				Claims claims=getClaims(token);
				
				return claims.getExpiration()
						      .after(new Date());
			}
			catch(Exception e) {
				
				return false;
			}
		}
		

		// Extract expiration date from token
		public LocalDateTime extractExpiration(String token) {

		    Date expiration = getClaims(token).getExpiration();

		    return expiration.toInstant()
		            .atZone(ZoneId.systemDefault())
		            .toLocalDateTime();
		}
}
