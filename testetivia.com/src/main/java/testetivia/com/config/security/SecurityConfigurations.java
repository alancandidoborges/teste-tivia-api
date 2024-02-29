package testetivia.com.config.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import testetivia.com.helpers.SecurityHelper;



@Configuration
@EnableWebSecurity
public class SecurityConfigurations implements WebMvcConfigurer{
	
    @Autowired
    SecurityFilter securityFilter;

    @Autowired
    SecurityHelper securityHelper;    

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    	
        return httpSecurity
        		.cors(cors -> cors.configurationSource(corsConfigurationSource()))
        		.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST  , securityHelper.permitAllPost).permitAll()
                        .requestMatchers(HttpMethod.GET   , securityHelper.permitAllGet).permitAll()

                        .requestMatchers(HttpMethod.POST  , securityHelper.hasRoleAdminPost).hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET   , securityHelper.hasRoleAdminGet).hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT   , securityHelper.hasRoleAdminPut).hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, securityHelper.hasRoleAdminDelete).hasAnyRole("ADMIN")

                        .requestMatchers(HttpMethod.GET   , securityHelper.hasRoleUserGet).hasAnyRole("USER")
                        .requestMatchers(HttpMethod.POST  , securityHelper.hasRoleUserPost).hasAnyRole("USER")
                        .requestMatchers(HttpMethod.PUT   , securityHelper.hasRoleUserPut).hasAnyRole("USER")
                        .requestMatchers(HttpMethod.DELETE, securityHelper.hasRoleUserDelete).hasAnyRole("USER")
                        
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilter(securityFilter)
                .build();
    }
    
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
    	CorsConfiguration configuration = new CorsConfiguration();
    	//configuration.setAllowedHeaders(Arrays.asList("*", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Access-Control-Allow-Methods", "Access-Control-Allow-Headers"));
        configuration.setAllowCredentials(false);
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
    	configuration.setAllowedMethods(Arrays.asList("*"));
    	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    	source.registerCorsConfiguration("/**", configuration);
    	return source;
    }    
   
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	

}
