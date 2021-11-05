package ua.training.model.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.RoleDao;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * The Role service.
 */
public class RoleService {
    /**
     * The Dao factory.
     */
    DaoFactory daoFactory = DaoFactory.getInstance();

    private final Logger logger = LogManager.getLogger(RoleService.class.getName());

    /**
     * Login.
     *
     * @param name the name
     * @return the optional
     */
    public Optional<Role> login(String name) {
        Optional<Role> result;
        try (RoleDao roleDao = daoFactory.createRoleDao()) {
            result = roleDao.findByName(name);
        }
        return result;
    }

    /**
     * Get all roles list.
     *
     * @return the list
     */
    public List<Role> getAllRoles(){
        try (RoleDao dao = daoFactory.createRoleDao()) {
            return dao.findAll();
        }
    }

    /**
     * Create role.
     *
     * @param role the role
     */
    public void createRole(Role role) {
        try (RoleDao roleDao = daoFactory.createRoleDao()) {
            roleDao.create(role);
            logger.info("Attempted to create Role with params (name) :" + "(" + role.getName() + ")");
        }
    }

    /**
     * Get role by name.
     *
     * @param name the name
     * @return the by name
     */
    public Optional<Role> getByName(String name) {
        try (RoleDao dao = daoFactory.createRoleDao()) {
            return dao.findByName(name);
        }
    }

}
