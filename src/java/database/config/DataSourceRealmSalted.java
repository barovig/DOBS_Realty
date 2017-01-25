package database.config;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.catalina.realm.DataSourceRealm;

/**
 *
 * @author semargl
 */
public class DataSourceRealmSalted extends DataSourceRealm{
    
    final String saltTableName = "salts";
    final String saltColumnName = "salt";
    final String userNameColumn = "username";
    
    // override method with Connection param,
    // so we get a hold on database connection for free =)
    @Override
    protected java.security.Principal authenticate(java.sql.Connection dbConnection, 
            String username, String credentials) {
        
        try{
            // get salt for that user
            PreparedStatement ps = dbConnection.prepareStatement("SELECT salt FROM salts WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
			rs.next();
            String salt = rs.getString("salt");
            // update password
            credentials= credentials+salt;
            
        }catch(SQLException se){
            // database error: don't authenticate (return null)
            // http://tomcat.apache.org/tomcat-8.0-doc/api/org/apache/catalina/realm/DataSourceRealm.html
			return null;
        }
        // call parent with updated password
        return super.authenticate(dbConnection, username, credentials);
    }
}
