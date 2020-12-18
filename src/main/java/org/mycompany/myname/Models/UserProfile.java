package org.mycompany.myname.Models;

import javax.persistence.*;

@Entity
@Table(name="users")
public class UserProfile {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_login", unique = true)
    private String login;
    @Column(name = "user_pass")
    private String pass;
    @Column(name = "user_mail")
    private String email;

    public UserProfile() {}

    public UserProfile(String login, String pass, String email) {
        this.login = login;
        this.pass = pass;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getEmail() {
        return email;
    }
}
