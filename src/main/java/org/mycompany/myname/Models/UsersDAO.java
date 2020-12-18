package org.mycompany.myname.Models;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class UsersDAO {
    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public UserProfile getUser(String login) throws HibernateException {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<UserProfile> cr = cb.createQuery(UserProfile.class);
        Root<UserProfile> root = cr.from(UserProfile.class);
        cr.select(root).where(cb.equal(root.get("login"), login));

        return  session.createQuery(cr).getSingleResult();
    }

    public void insertUser(String login, String pass, String mail) throws HibernateException {
        session.save(new UserProfile(login, pass, mail));
    }
}
