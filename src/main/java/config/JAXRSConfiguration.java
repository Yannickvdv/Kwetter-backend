package config;

//import auth.CrossOriginResourceSharingFilter;
//import io.swagger.v3.jaxrs2.integration.resources.AcceptHeaderOpenApiResource;
//import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
//import java.util.Set;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Configures a JAX-RS endpoint. Delete this class, if you are not exposing
 * JAX-RS resources in your application."
 *
 * @author airhacks.com
 */

@ApplicationPath("api")
public class JAXRSConfiguration extends Application {
//      
//    @Override
//    public Set<Class<?>> getClasses() {
//        return Stream.of(
//                // Filters
//                CrossOriginResourceSharingFilter.class,
//                // Swagger
//                OpenApiResource.class,
//                AcceptHeaderOpenApiResource.class
//        ).collect(Collectors.toSet());
//    }
    
//    public JAXRSConfiguration(){
//        BeanConfig beanConfig = new BeanConfig();
//        beanConfig.setVersion("2.0.0");
//        beanConfig.setSchemes(new String[]{"http"});
//        beanConfig.setHost("localhost:8080");
//        beanConfig.setBasePath("/kwetter/api");
//        beanConfig.setVersion("1");
//        beanConfig.setResourcePackage("rest");
//        beanConfig.setPrettyPrint(true);
//        beanConfig.setScan();
//    }
}
