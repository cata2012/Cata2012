package br.usp.cata.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.util.hibernate.SessionCreator;

import br.usp.cata.model.User;


@Component
@RequestScoped
public class UserDAO extends AbstractDAO<Long,User> {

	public UserDAO(SessionCreator sessionCreator) {
		super(sessionCreator);
	}

    public User findByEmailAndStatus(final String email, final boolean active) {
        final List<User> users = findByCriteria(Restrictions.and(
        		Restrictions.eq("email", email), Restrictions.eq("active", active)));

        if(users.size() > 1)
            throw new IllegalStateException(
                "There is more than one user with the same e-mail address: " + email + ".");
        
        return (users.isEmpty() ? null : users.get(0));
    }

    public User findActiveUsersByEmailAndPassword(final String email, final String password) {
        final List<User> users = findByCriteria(Restrictions.and(
        		Restrictions.and(Restrictions.eq("email", email), Restrictions.eq("password", password)),
        		Restrictions.eq("active", true)));

        if(users.size() > 1)
            throw new IllegalStateException(
                "There is more than one user with the same e-mail and password."
                		+ email + " " + password);

        return (users.isEmpty() ? null : users.get(0));
    }
    
    public User findByEmail(final String email) {
    	final List<User> users = findByCriteria(Restrictions.eq("email", email));
    	
    	if(users.size() > 1)
    		throw new IllegalStateException(
    				"There is more than one user with the same e-mail: " + email);
    	
    	return (users.isEmpty() ? null : users.get(0)); 
    }
    
    public User findByName(final String name) {
    	final List<User> users = findByCriteria(Restrictions.eq("name", name));
    	
    	if(users.size() > 1)
    		throw new IllegalStateException(
    				"There is more than one user with the same name: " + name);
    	
    	return (users.isEmpty() ? null : users.get(0));
    }
    
    public User findByActivationKey(final String activationKey) {
        final List<User> users = findByCriteria(
        		 Restrictions.eq("activationKey", activationKey));

        if(users.size() > 1)
            throw new IllegalStateException(
                "There is more than one user with the same activation key: " + activationKey + ".");

        return (users.isEmpty() ? null : users.get(0));
    }
    
    public User findByNewPasswordKey(final String newPasswordKey) {
    	final List<User> users = findByCriteria(Restrictions.eq("newPasswordKey", newPasswordKey));
    	
    	if(users.size() > 1)
            throw new IllegalStateException(
                "There is more than one user with the same new password key: " + newPasswordKey + ".");
    	
    	return (users.isEmpty() ? null : users.get(0));
    }
    
}
