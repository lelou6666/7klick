package com.sevenklick.common.core.domain;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.sevenklick.common.core.helpers.EncryptionUtil;
import com.sevenklick.common.core.exception.TicketNotValidException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Context data holder to User context information, created when user has a valid ticket.
 * Se TicketService for more information.
 * * Created by pierre.petersson on 4/3/2014.
 */
@Component
public class Context {
    private static final Logger logger = LoggerFactory.getLogger(Context.class);
    /**
     * An encrypted ticket string provided by the TicketService
     * @param ticket - An base64encoded and encrypted json string representing the user entity.
     * @throws TicketNotValidException - If ticket has been manipulated
     */
    public Context(String ticket) throws TicketNotValidException {
        try{
            UserContext user = new Gson().fromJson(EncryptionUtil.getInstance().decrypt(ticket), UserContext.class);
            addContextValue(CONTEXT_TICKET, ticket);
            addContextValue(CONTEXT_USERNAME, user.getUserName());

        }catch(NullPointerException ne){
            logger.error("Not valid ticket:"+ ticket, ne);
            throw new TicketNotValidException(TicketNotValidException.NOT_VALID, "Not valid ticket:"+ ticket);
        }
        catch(TicketNotValidException te){
            logger.error("Not valid ticket:"+ ticket, te);
            throw new TicketNotValidException(TicketNotValidException.NOT_AUHTHENTICATED, "Not valid ticket:"+ ticket);
        }
        catch(JsonSyntaxException e){
            logger.error("Invalid json format of ticket:"+ ticket, e);
            throw new TicketNotValidException(TicketNotValidException.NOT_VALID, "Invalid json format of ticket:"+ ticket);
        }
    }
    public Map<String, Object> getContextAsMap(){
        return this.contextValues;
    }
    public void addContextValue(String key, Object value){
        if(value!=null) {
            contextValues.put(key, value);
        }
    }

    public String getTicket(){
        return this.contextValues.get(CONTEXT_TICKET).toString();
    }
    public Long getTenantId(){
        return (Long)this.contextValues.get(CONTEXT_PLCID);
    }
    public String getUsername(){
        return this.contextValues.get(CONTEXT_USERNAME).toString();
    }
    public String getLangaugeCode(){
        return this.contextValues.get(CONTEXT_LANGCODE).toString();
    }
    public String getCountryCode(){
        return this.contextValues.get(CONTEXT_COUNTRYCODE).toString();
    }
    public String getRole(){
        return this.contextValues.get(CONTEXT_ROLE).toString();
    }
    public Boolean isAdminEnabled(){
        return (Boolean)this.contextValues.get(CONTEXT_ADMINENABLED);
    }
    public Object getContextValue(String key){
        return this.contextValues.get(key);
    }

    /**
     * To make sure that empty constructor will not be use to initialiaze contstructor.
     */
    private Context(){
    }
    private Map<String, Object> contextValues = new ConcurrentHashMap<String, Object>();
    private final String CONTEXT_TICKET="contextTicket";
    private final String CONTEXT_PLCID="contextPlcId";
    private final String CONTEXT_CARID="contextCarId";
    private final String CONTEXT_USERNAME="contextUsername";
    private final String CONTEXT_LANGCODE="contextLangCode";
    private final String CONTEXT_COUNTRYCODE="contextCountryCode";
    private final String CONTEXT_ROLE="contextRole";
    private final String CONTEXT_ADMINENABLED="contextAdminEnabled";
    private final String CONTEXT_TRBID="contextTrbId";

}
