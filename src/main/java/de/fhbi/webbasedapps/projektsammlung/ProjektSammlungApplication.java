package de.fhbi.webbasedapps.projektsammlung;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class ProjektSammlungApplication extends Application {

    public ProjektSammlungApplication() throws ClassNotFoundException {
        super();
        Class.forName("org.postgresql.Driver");
        Class.forName("org.postgresql.ds.PGConnectionPoolDataSource");
    }

}