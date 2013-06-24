//package br.com.vivabem.sec;
//
//import javax.enterprise.context.ApplicationScoped;
//import javax.enterprise.inject.Produces;
//import javax.inject.Inject;
//
//import org.picketbox.core.config.ConfigurationBuilder;
//import org.picketlink.extensions.core.pbox.idm.DefaultEntityManagerLookupStrategy;
//
//@ApplicationScoped
//public class SecurityConfigurationProducer {
//
//    @Inject
//    private DefaultEntityManagerLookupStrategy entityManagerLookupStrategy;
//
//    @Produces
//    public ConfigurationBuilder configure() {
//        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
//
//        // configures a JPA-based identity store.
//        configurationBuilder.identityManager().jpaStore().entityManagerLookupStrategy(this.entityManagerLookupStrategy);
//
//        return configurationBuilder;
//    }
//
//}