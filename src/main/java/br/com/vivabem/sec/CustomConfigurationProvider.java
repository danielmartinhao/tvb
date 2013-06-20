//package br.com.vivabem.sec;
//
//import javax.servlet.ServletContext;
//
//import org.picketbox.http.config.ConfigurationBuilderProvider;
//import org.picketbox.http.config.HTTPConfigurationBuilder;
//import org.picketbox.http.resource.ProtectedResourceConstraint;
//
//public class CustomConfigurationProvider implements ConfigurationBuilderProvider {
//
//	@Override
//	public HTTPConfigurationBuilder getBuilder(ServletContext servletcontext) {
//		HTTPConfigurationBuilder configurationBuilder = new HTTPConfigurationBuilder();
//		
//		configurationBuilder.identityManager().jpaStore();
//		
//        // protected resources configuration
//        configurationBuilder.protectedResource()
//                // unprotected resource. Usually this will be your application's static resources like CSS, JS, etc.
//                .resource("/faces/resources/*", ProtectedResourceConstraint.AUTHENTICATION)
// 
//                // the login page is marked as not protected.
//                .resource("/faces/login.xhtml", ProtectedResourceConstraint.NOT_PROTECTED)
// 
//                // the register page is marked as not protected.
//                //.resource("/signup.jsp", ProtectedResourceConstraint.NOT_PROTECTED)
// 
//                // the register page is marked as not protected.
//                //.resource("/signup", ProtectedResourceConstraint.NOT_PROTECTED)
// 
//                // the error page is marked as not protected.
//                .resource("/faces/error.xhtml", ProtectedResourceConstraint.NOT_PROTECTED)
// 
//                // protected all resources. They will be available only for users with a role named 'guest'.
//                .resource("/faces/sistema/*", "sistema", "administracao", "atendente", "vendedor");
// 
//        return configurationBuilder;
//	}
//
//}
