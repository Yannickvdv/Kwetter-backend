/* 
 * Copyright (C) 2019 Yannick
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
