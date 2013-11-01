package com.vexdev.controllers;

import com.vexdev.constant.SystemConstants;
import com.vexdev.dao.interfaces.BaseDAO;
import com.vexdev.manager.JSONManager;
import com.vexdev.models.interfaces.BaseEntity;
import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Base controller extended by others.
 */
@Controller
@PreAuthorize("hasRole('admin')")
public abstract class BaseController<T extends BaseEntity> {
    public static final boolean D = SystemConstants.ACTIVE_DEBUG;
    private static final Logger log = Logger.getLogger(BaseController.class);

    /**
     * Shows a list of entities.
     * @param map Map
     * @return View to load
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String entities(ModelMap map) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        map.addAttribute("userDetails", userDetails);
        List<T> entities = getDAO().list();
        map.addAttribute("entities", entities);
        return getViewName();
    }

    /**
     * Updates a parameter of the entity.
     * @param id Entity ID
     * @param field Field to update
     * @param value New value
     * @return JSON Result
     */
    @RequestMapping(value="/storeParameter", method= RequestMethod.GET)
    public @ResponseBody
    String storeParameter(@RequestParam String id, @RequestParam String field ,@RequestParam String value) {
        if(D) log.debug("Called: storeParameter(["+id+", "+field+", "+value+"])");
        try {
            if(id.equalsIgnoreCase("0")) {
                boolean result = getDAO().setField(getMock(), field, value);
                if(result)
                    return JSONManager.fromSimpleResultOK();
            } else {
                boolean result = getDAO().setField(id, field, value);
                if(result)
                    return JSONManager.fromSimpleResultOK();
            }
        } catch (Exception e) {
            return JSONManager.fromError(e.getLocalizedMessage());
        }
        return JSONManager.fromError("Unable to store parameter.");
    }

    /**
     * Stores an element
     * @return JSON Result
     */
    @RequestMapping(value="/storeElement", method=RequestMethod.GET)
    public @ResponseBody String storeElement() {
        if(D) log.debug("Called: storeElement([])");
        try {
            getDAO().persist(getMock());
            return JSONManager.fromSimpleResultOK();
        } catch (Exception e) {
            return JSONManager.fromError(e.getLocalizedMessage());
        }
    }

    /**
     * Drops an Element
     * @param id Element's ID
     * @return JSON Result
     */
    @Transactional
    @RequestMapping(value="/dropElement", method=RequestMethod.GET)
    public @ResponseBody String dropElement(@RequestParam String id) {
        if(D) log.debug("Called: dropElement(["+id+"])");
        try {
            getDAO().dropByID(id);
            return JSONManager.fromSimpleResultOK();
        } catch (Exception e) {
            return JSONManager.fromError(e.getLocalizedMessage());
        }
    }

    /**
     * Returns the DAO associated with the entity controlled.
     * @return a DAO that extends BaseDAO
     */
    protected abstract BaseDAO<T> getDAO();

    /**
     * Returns the MockEntity used to add a new one.
     * @return a Mock Entity
     */
    protected abstract T getMock();

    /**
     * Returns the view name
     * @return View name
     */
    protected abstract String getViewName();
}
