package ua.training.model.dao;

import ua.training.model.dao.impl.JDBCDaoFactory;

/**
 * The generic DAO factory.
 */
public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    /**
     * Create user user DAO.
     *
     * @return the user DAO
     */
    public abstract UserDao createUserDao();

    /**
     * Create periodical periodical DAO.
     *
     * @return the periodical DAO
     */
    public abstract PeriodicalDao createPeriodicalDao();

    /**
     * Create category categories DAO.
     *
     * @return the categories DAO
     */
    public abstract CategoriesDao createCategoryDao();

    /**
     * Create subscription subscription DAO.
     *
     * @return the subscription DAO
     */
    public abstract SubscriptionDao createSubscriptionDao();

    /**
     * Update periodical periodical DAO.
     *
     * @return the periodical DAO
     */
    public abstract PeriodicalDao updatePeriodicalDao();

    /**
     * Create role role DAO.
     *
     * @return the role DAO
     */
    public abstract RoleDao createRoleDao();

    /**
     * Get DAO factory instance.
     *
     * @return the DAO factory
     */
    public static DaoFactory getInstance(){
        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory==null){
                    DaoFactory temp = new JDBCDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }
}
