/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ivantactukmercado
 */
@ManagedBean
@RequestScoped
public class MbSession {
    
    private String user;

    private final HttpServletRequest httpServletRequest ;
    private final FacesContext faceContext;
    private FacesMessage facesMessage;
    
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    /**
     * Creates a new instance of MbSession
     */
    public MbSession() {
        faceContext=FacesContext.getCurrentInstance();        
        httpServletRequest=(HttpServletRequest)faceContext.getExternalContext().getRequest();
        if (httpServletRequest.getSession().getAttribute("sessionUser")!=null){
            user=httpServletRequest.getSession().getAttribute("sessionUser").toString();
        }
    }
    
    public String closeSession(){
        httpServletRequest.getSession().removeAttribute("sessionUser");
        httpServletRequest.getSession().removeAttribute("userRole");
        facesMessage=new FacesMessage(FacesMessage.SEVERITY_INFO,"SESSION CLOSED SUCCESSFULLY",null);
        faceContext.addMessage(null, facesMessage);
        return "index";
    }
    
}
