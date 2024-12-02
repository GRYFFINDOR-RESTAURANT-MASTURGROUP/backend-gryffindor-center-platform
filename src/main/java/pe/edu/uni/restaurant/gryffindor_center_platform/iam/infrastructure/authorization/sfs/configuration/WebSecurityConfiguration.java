package pe.edu.uni.restaurant.gryffindor_center_platform.iam.infrastructure.authorization.sfs.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.infrastructure.authorization.sfs.pipeline.BearerAuthorizationRequestFilter;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.infrastructure.hashing.bcrypt.BCryptHashingService;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.infrastructure.tokens.jwt.BearerTokenService;

import java.util.List;

/**
 * Configuración de Seguridad Web.
 * <p>
 * Esta clase es responsable de configurar la seguridad web.
 * Activa la seguridad de los métodos y configura la cadena de filtros de seguridad.
 * Incluye el gestor de autenticación, el proveedor de autenticación,
 * el codificador de contraseñas y el punto de entrada de autenticación.
 * </p>
 */
@Configuration
@EnableMethodSecurity
public class WebSecurityConfiguration {

  private final UserDetailsService userDetailsService;
  private final BearerTokenService tokenService;
  private final BCryptHashingService hashingService;

  private final AuthenticationEntryPoint unauthorizedRequestHandler;

  /**
   * Este método crea el filtro de autorización con token Bearer.
   * @return El filtro de autorización con token Bearer
   */
  @Bean
  public BearerAuthorizationRequestFilter authorizationRequestFilter() {
    return new BearerAuthorizationRequestFilter(tokenService, userDetailsService);
  }

  /**
   * Este método crea el gestor de autenticación.
   * @param authenticationConfiguration La configuración de autenticación
   * @return El gestor de autenticación
   * @throws Exception si ocurre algún error en la configuración
   */
  @Bean
  public AuthenticationManager authenticationManager(
          AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  /**
   * Este método crea el proveedor de autenticación.
   * @return El proveedor de autenticación
   */
  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    var authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService);
    authenticationProvider.setPasswordEncoder(hashingService);
    return authenticationProvider;
  }

  /**
   * Este método crea el codificador de contraseñas.
   * @return El codificador de contraseñas
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return hashingService;
  }

  /**
   * Este método crea la cadena de filtros de seguridad.
   * También configura la seguridad HTTP.
   *
   * @param http La seguridad HTTP
   * @return La cadena de filtros de seguridad
   * @throws Exception si ocurre algún error en la configuración
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // Configura CORS (Cross-Origin Resource Sharing)
    http.cors(corsConfigurer -> corsConfigurer.configurationSource(request -> {
      var cors = new CorsConfiguration();
      cors.setAllowedOrigins(List.of("*"));
      cors.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE"));
      cors.setAllowedHeaders(List.of("*"));
      return cors;
    }));

    // Configura CSRF, manejo de excepciones y autenticación sin estado
    http.csrf(csrfConfigurer -> csrfConfigurer.disable())
            .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(unauthorizedRequestHandler))
            .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(
                    authorizeRequests -> authorizeRequests.requestMatchers(
                                    "/api/v1/autenticacion/**", "/v3/api-docs/**", "/swagger-ui.html",
                                    "/swagger-ui/**", "/swagger-resources/**", "/webjars/**")
                            .permitAll()  // Permitir todas las solicitudes a las rutas especificadas
                            .anyRequest()
                            .authenticated());  // Requiere autenticación para cualquier otra solicitud

    // Añade el proveedor de autenticación y el filtro de autorización
    http.authenticationProvider(authenticationProvider());
    http.addFilterBefore(authorizationRequestFilter(), UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  /**
   * Constructor de la clase.
   * @param userDetailsService El servicio de detalles del usuario
   * @param tokenService El servicio de token
   * @param hashingService El servicio de hashing
   * @param authenticationEntryPoint El punto de entrada para solicitudes no autorizadas
   */
  public WebSecurityConfiguration(
          @Qualifier("defaultUserDetailsService") UserDetailsService userDetailsService,
          BearerTokenService tokenService, BCryptHashingService hashingService,
          AuthenticationEntryPoint authenticationEntryPoint) {

    this.userDetailsService = userDetailsService;
    this.tokenService = tokenService;
    this.hashingService = hashingService;
    this.unauthorizedRequestHandler = authenticationEntryPoint;
  }
}
