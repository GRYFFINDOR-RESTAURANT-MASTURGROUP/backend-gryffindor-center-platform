package pe.edu.uni.restaurant.gryffindor_center_platform.iam.application.internal.outboundservices.tokens;

/**
 * TokenService interface
 * This interface is used to generate and validate tokens
 */
public interface TokenService {

  /**
   * Generate a token for a given username
   * @param userName the username
   * @return String the token
   */
  String generateToken(String userName);

  /**
   * Extract the username from a token
   * @param token the token
   * @return String the userName
   */
  String getUserNameFromToken(String token);

  /**
   * Validate a token
   * @param token the token
   * @return boolean true if the token is valid, false otherwise
   */
  boolean validateToken(String token);
}
