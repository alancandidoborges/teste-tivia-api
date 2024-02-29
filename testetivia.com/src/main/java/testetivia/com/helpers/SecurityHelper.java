package testetivia.com.helpers;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityHelper {
	
	  public String[] permitAllGet      = {
			  "/usuario/**"               ,
			  "/h2-console/**"            ,
              "/swagger-ui/**"            , 
              "/v3/api-docs/**"           };

	  public String[] permitAllPost      = {
			  "/usuario/post"               ,
			  "/h2-console", 
			  "/auth/login"               ,
              "/auth/register"            };
	  
	  public String[] permitAllPut       = {
			  "/h2-console/**",
              "/swagger-ui/**"            , 
              "/v3/api-docs/**"           };

	  public String[] permitAllDelete   =  {
			  "/h2-console/**"             , 
			  "/auth/login"                ,
              "/auth/register"             };

	  public String[] hasRoleAdminGet    = {
			 "/beneficiario/**"     	   ,
             "/documento/**"       	       };

	  public String[] hasRoleAdminPost   = {
		      "/beneficiario/post"         ,
              "/documento/post"            };

	  public String[] hasRoleAdminPut    = {
			    "/usuario/put/**"          ,
				"/beneficiario/put/**"     ,
                "/documento/put/**"        };

	  public String[] hasRoleAdminDelete = {
			  "/usuario/delete/**"         ,
			  "/beneficiario/delete/**"    ,
              "/documento/delete/**"       };

	  public String[] hasRoleUserGet     = {};
	  public String[] hasRoleUserPost    = {};
	  public String[] hasRoleUserPut     = {};
	  public String[] hasRoleUserDelete  = {};

}
