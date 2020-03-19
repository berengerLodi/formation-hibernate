package fr.insee.tp.dao;

import java.sql.Types;

import org.hibernate.dialect.H2Dialect;

public class H2DialectExtended extends H2Dialect{

	   public H2DialectExtended() {
	        super();
	        registerColumnType(Types.FLOAT, "double");
	    }
}
