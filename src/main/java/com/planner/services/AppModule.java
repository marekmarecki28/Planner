package com.planner.services;

import java.io.IOException;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.Local;
import org.apache.tapestry5.ioc.annotations.Marker;
import org.apache.tapestry5.ioc.services.ApplicationDefaults;
import org.apache.tapestry5.ioc.services.SymbolProvider;
import org.apache.tapestry5.services.HttpServletRequestFilter;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.RequestFilter;
import org.apache.tapestry5.services.RequestHandler;
import org.apache.tapestry5.services.Response;
import org.slf4j.Logger;
import org.tynamo.security.Security;
import org.tynamo.security.SecuritySymbols;
import org.tynamo.security.services.SecurityFilterChainFactory;
import org.tynamo.security.services.impl.SecurityFilterChain;

import com.planner.auth.MyCustomRealm;
import com.planner.dao.CalendarDAO;
import com.planner.dao.EmployeeDAO;
import com.planner.dao.HotelCalendarDAO;
import com.planner.dao.HotelDAO;
import com.planner.dao.PositionsDAO;
import com.planner.dao.UserCalendarDAO;
import com.planner.dao.UserDAO;
import com.planner.dao.impl.CalendarDAOImpl;
import com.planner.dao.impl.EmployeeDAOImpl;
import com.planner.dao.impl.HotelCalendarDAOImpl;
import com.planner.dao.impl.HotelDAOImpl;
import com.planner.dao.impl.PositionsDAOImpl;
import com.planner.dao.impl.UserCalendarDAOImpl;
import com.planner.dao.impl.UserDAOImpl;

/**
 * This module is automatically included as part of the Tapestry IoC Registry, it's a good place to
 * configure and extend Tapestry, or to place your own service definitions.
 */
public class AppModule
{
    public static void bind(ServiceBinder binder)
    {
        // binder.bind(MyServiceInterface.class, MyServiceImpl.class);
    	binder.bind(HotelDAO.class, HotelDAOImpl.class);
    	binder.bind(EmployeeDAO.class, EmployeeDAOImpl.class);
    	binder.bind(CalendarDAO.class, CalendarDAOImpl.class);
    	binder.bind(UserCalendarDAO.class, UserCalendarDAOImpl.class);
    	binder.bind(HotelCalendarDAO.class, HotelCalendarDAOImpl.class);
    	binder.bind(PositionsDAO.class, PositionsDAOImpl.class);
    	binder.bind(UserDAO.class, UserDAOImpl.class);

        // Make bind() calls on the binder object to define most IoC services.
        // Use service builder methods (example below) when the implementation
        // is provided inline, or requires more initialization than simply
        // invoking the constructor.
    }
    
    public static void contributeWebSecurityManager(Configuration<Realm> configuration) {
    	//ExtendedPropertiesRealm realm = new ExtendedPropertiesRealm("classpath:shiro-users.properties");
    	MyCustomRealm realm = new MyCustomRealm();
    	HashedCredentialsMatcher hm = new HashedCredentialsMatcher();
		hm.setHashAlgorithmName("SHA-256");
		hm.setHashIterations(1024);
		hm.setStoredCredentialsHexEncoded(false);
    	realm.setCredentialsMatcher(hm);
    	configuration.add(realm);
    }
    
	@Contribute(HttpServletRequestFilter.class)
	@Marker(Security.class)
	public static void setupSecurity(Configuration<SecurityFilterChain> configuration,
			SecurityFilterChainFactory factory, WebSecurityManager securityManager) {

		configuration.add(factory.createChain("/positions/**").add(factory.authc()).build());
		configuration.add(factory.createChain("/plan/**").add(factory.authc()).build());
		configuration.add(factory.createChain("/createhotel/**").add(factory.authc()).build());
		configuration.add(factory.createChain("/hotelschedule/**").add(factory.authc()).build());
	}

    public static void contributeFactoryDefaults(
        MappedConfiguration<String, Object> configuration)
    {
        // The values defined here (as factory default overrides) are themselves
        // overridden with application defaults by DevelopmentModule and QaModule.

        // The application version is primarily useful as it appears in
        // any exception reports (HTML or textual).
        configuration.override(SymbolConstants.APPLICATION_VERSION, "0.0.1-SNAPSHOT");

        // This is something that should be removed when going to production, but is useful
        // in the early stages of development.
        configuration.override(SymbolConstants.PRODUCTION_MODE, false);
    }

    public static void contributeApplicationDefaults(
        MappedConfiguration<String, Object> configuration)
    {
        // Contributions to ApplicationDefaults will override any contributions to
        // FactoryDefaults (with the same key). Here we're restricting the supported
        // locales to just "en" (English). As you add localised message catalogs and other assets,
        // you can extend this list of locales (it's a comma separated series of locale names;
        // the first locale name is the default when there's no reasonable match).
        configuration.add(SymbolConstants.SUPPORTED_LOCALES, "en");

              // You should change the passphrase immediately; the HMAC passphrase is used to secure
        // the hidden field data stored in forms to encrypt and digitally sign client-side data.
        configuration.add(SymbolConstants.HMAC_PASSPHRASE, "change this immediately");
        
        configuration.add(SecuritySymbols.LOGIN_URL, "/signin");
    }

	/**
	 * Use annotation or method naming convention: <code>contributeApplicationDefaults</code>
	 */
	@Contribute(SymbolProvider.class)
	@ApplicationDefaults
	public static void setupEnvironment(MappedConfiguration<String, Object> configuration)
	{
        // Support for jQuery is new in Tapestry 5.4 and will become the only supported
        // option in 5.5.
		configuration.add(SymbolConstants.JAVASCRIPT_INFRASTRUCTURE_PROVIDER, "jquery");
		configuration.add(SymbolConstants.BOOTSTRAP_ROOT, "context:mybootstrap");
		configuration.add(SymbolConstants.MINIFICATION_ENABLED, true);
	}


    /**
     * This is a service definition, the service will be named "TimingFilter". The interface,
     * RequestFilter, is used within the RequestHandler service pipeline, which is built from the
     * RequestHandler service configuration. Tapestry IoC is responsible for passing in an
     * appropriate Logger instance. Requests for static resources are handled at a higher level, so
     * this filter will only be invoked for Tapestry related requests.
     *
     *
     * Service builder methods are useful when the implementation is inline as an inner class
     * (as here) or require some other kind of special initialization. In most cases,
     * use the static bind() method instead.
     *
     *
     * If this method was named "build", then the service id would be taken from the
     * service interface and would be "RequestFilter".  Since Tapestry already defines
     * a service named "RequestFilter" we use an explicit service id that we can reference
     * inside the contribution method.
     */
    public RequestFilter buildTimingFilter(final Logger log)
    {
        return new RequestFilter()
        {
            public boolean service(Request request, Response response, RequestHandler handler)
            throws IOException
            {
                long startTime = System.currentTimeMillis();

                try
                {
                    // The responsibility of a filter is to invoke the corresponding method
                    // in the handler. When you chain multiple filters together, each filter
                    // received a handler that is a bridge to the next filter.

                    return handler.service(request, response);
                } finally
                {
                    long elapsed = System.currentTimeMillis() - startTime;

                    log.info("Request time: {} ms", elapsed);
                }
            }
        };
    }

    /**
     * This is a contribution to the RequestHandler service configuration. This is how we extend
     * Tapestry using the timing filter. A common use for this kind of filter is transaction
     * management or security. The @Local annotation selects the desired service by type, but only
     * from the same module.  Without @Local, there would be an error due to the other service(s)
     * that implement RequestFilter (defined in other modules).
     */
    @Contribute(RequestHandler.class)
    public void addTimingFilter(OrderedConfiguration<RequestFilter> configuration,
     @Local
     RequestFilter filter)
    {
        // Each contribution to an ordered configuration has a name, When necessary, you may
        // set constraints to precisely control the invocation order of the contributed filter
        // within the pipeline.

        configuration.add("Timing", filter);
    }
}
