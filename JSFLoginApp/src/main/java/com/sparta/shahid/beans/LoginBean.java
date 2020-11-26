package com.sparta.shahid.beans;

import com.sparta.shahid.entities.User;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Named
@RequestScoped
public class LoginBean {
    @Inject
    User user;

    @Inject
    SecurityContext securityContext;

    @Inject
    ExternalContext externalContext;

    @Inject
    FacesContext facesContext;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void submit() throws IOException {
        switch (continueAuthentication()){
            case SEND_CONTINUE:
                facesContext.responseComplete();
                break;
            case SEND_FAILURE:
                facesContext.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Login unsuccessful",null));
                break;
            case SUCCESS:
                externalContext.redirect(externalContext.getRequestContextPath() + "/view/welcome.xhtml");
        }
    }

    private AuthenticationStatus continueAuthentication() {
        return securityContext.authenticate(
                (HttpServletRequest) externalContext.getRequest(),
                (HttpServletResponse) externalContext.getResponse(),
                AuthenticationParameters.withParams().credential(new UsernamePasswordCredential(user.getUserName(),user.getPassword()))
        );
    }

    public String logout() throws ServletException {
        ExternalContext externalContext = facesContext.getExternalContext();
        ((HttpServletRequest)externalContext.getRequest()).logout();
        return "/login.xhtml?faces-redirect=true";
    }

}
