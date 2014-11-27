package com.cummins.mowo;


import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.MediaType;
import org.restlet.data.Protocol;
import org.restlet.representation.StringRepresentation;
import org.restlet.routing.Router;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.security.MemoryRealm;
import org.restlet.security.Role;
import org.restlet.security.User;

import com.cummins.mowo.persistence.PersistenceService;
import com.cummins.mowo.resource.server.PingServerResource;
import com.cummins.mowo.resource.server.TimecardListServerResource;
import com.cummins.mowo.resource.server.TimecardServerResource;

import server.HelloResource;

public class MowoAPIApplication extends Application {
	
	public static final String PING = "MOWO API is runnis";
	
    /*
     * Define role names
     */
    public static final String ROLE_ADMIN = "admin";

    public static final String ROLE_OWNER = "owner";

    public static final String ROLE_USER = "user";

    /*
     * Define route constants
     */
    public static final String ROUTE_TIMECARDS = "/timecards";

    public static final String ROUTE_CONTACTS = "/contacts";

    /*
     * Define SQL State code constants
     */
    public static final String SQL_STATE_23000_DUPLICATE = "23000";

    static {
        try {
            Class.forName("org.restlet.ext.slf4j.Slf4jLoggerFacade");
            Class.forName("org.slf4j.Logger");
            System.setProperty("org.restlet.engine.loggerFacadeClass", "org.restlet.ext.slf4j.Slf4jLoggerFacade");
        } catch (ClassNotFoundException ex) {
            // slf4j or restlet slf4j extension not found
        }
    }
    /*
     * initialize database driver , we will use it later to connect to the database. 
     */
	public MowoAPIApplication() {
		super();

        
        /*
         * Attach application to http://localhost:9000/v1
         */
        Component c = new Component();
        //c.getServers().add(Protocol.HTTP, 9000);

        /*
         * Declare logger
         */
        // Declare CLAP client connector
        c.getClients().add(Protocol.CLAP);
        // Look for the log configuration file in the current classloader
        c.getLogService().setLogPropertiesRef("clap:///logging.properties");

        Context.getCurrentLogger().info("Restlet application started. URL: http://localhost:8080" );
		
	}

	public MowoAPIApplication(Context parentContext) {
		super(parentContext);
	}
 
	public Restlet createInboundRoot() {
		
        PersistenceService.initialize();
		
		Router router = new Router(getContext());

		router.attach("/ping", PingServerResource.class);
		
        /*
         * Create the api router, protected by a guard
         */
        ChallengeAuthenticator apiGuard = createApiGuard();
        Router apiRouter = createApiRouter();
        apiGuard.setNext(apiRouter);

        router.attachDefault(apiGuard);
        
		router.attach("/hello", TimecardListServerResource.class);
		

		Restlet mainpage = new Restlet() {
			@Override
			public void handle(Request request, Response response) {
				StringBuilder stringBuilder = new StringBuilder();

				stringBuilder.append("<html>");
				stringBuilder.append("<head><title>Hello MOWO API  "
						+ "Servlet Page</title></head>");
				stringBuilder.append("<body bgcolor=white>");
				stringBuilder.append("<table border=\"0\">");
				stringBuilder.append("<tr>");
				stringBuilder.append("<td>");
				stringBuilder.append("<h3>available MOWO REST calls</h3>");
				stringBuilder
						.append("<ol><li><a href=\"app/hello\">hello</a> --> returns hello world message "
								+ "and date string</li>");

				stringBuilder.append("</ol>");
				stringBuilder.append("</td>");
				stringBuilder.append("</tr>");
				stringBuilder.append("</table>");
				stringBuilder.append("</body>");
				stringBuilder.append("</html>");

				response.setEntity(new StringRepresentation(stringBuilder
						.toString(), MediaType.TEXT_HTML));
			}
		};
		router.attach("", mainpage);

		return router;
	}

	private ChallengeAuthenticator createApiGuard() {

        ChallengeAuthenticator apiGuard = new ChallengeAuthenticator(
                getContext(), ChallengeScheme.HTTP_BASIC, "realm");

        /*
         * Create in-memory users and roles.
         */
        MemoryRealm realm = new MemoryRealm();
        User owner = new User("owner", "owner");
        realm.getUsers().add(owner);
        realm.map(owner, Role.get(this, ROLE_OWNER));
        realm.map(owner, Role.get(this, ROLE_USER));
        User admin = new User("admin", "admin");
        realm.getUsers().add(admin);
        realm.map(admin, Role.get(this, ROLE_ADMIN));
        realm.map(admin, Role.get(this, ROLE_OWNER));
        realm.map(admin, Role.get(this, ROLE_USER));
        User user = new User("user", "user");
        realm.getUsers().add(user);
        realm.map(user, Role.get(this, ROLE_USER));

        /*
         * Attach - Verifier : to check authentication - Enroler : to check
         * authorization (roles)
         */
        apiGuard.setVerifier(realm.getVerifier());
        apiGuard.setEnroler(realm.getEnroler());

        /*
         * You can also create your own authentication/authorization system by
         * creating classes extending SecretVerifier or LocalVerifier (for
         * authentication) and Enroler (for authorization) and set these to the
         * guard.
         */

        return apiGuard;
    }

    private Router createApiRouter() {

        /*
         * Attach Server Resources to given URL. For instance,
         * CompanyListServerResource is attached to
         * http://localhost:9000/v1/companies/
         */
        Router apiRouter = new Router(getContext());
        apiRouter.attach(ROUTE_TIMECARDS, TimecardListServerResource.class);
        apiRouter.attach(ROUTE_TIMECARDS + "/", TimecardListServerResource.class);
        apiRouter.attach(ROUTE_TIMECARDS + "/{id}", TimecardServerResource.class);

        return apiRouter;

    }
}