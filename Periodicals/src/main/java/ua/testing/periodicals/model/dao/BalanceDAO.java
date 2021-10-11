package ua.testing.periodicals.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import ua.testing.periodicals.model.entity.BalanceTransaction;
import ua.testing.periodicals.model.dao.BalanceTransactionException;
import ua.testing.periodicals.model.dao.BalanceTransactionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.testing.periodicals.model.entity.User;
import ua.testing.periodicals.service.UsersService;

@Repository
public class BalanceDAO {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UsersService usersService;

    public BalanceTransaction findTransactionById(Long id) {
        return this.entityManager.find(BalanceTransaction.class, id);
    }

    public User findUserById(Long id) {
        return usersService.get(id);
    }

    public List<BalanceTransactionInfo> listBalanceTransactionInfo() {
        String sql = "Select new " + BalanceTransactionInfo.class.getName() //
                + "(e.id,e.periodical_id,e.amount) " //
                + " from " + BalanceTransaction.class.getName() + " e ";
        Query query = entityManager.createQuery(sql, BalanceTransactionInfo.class);
        return query.getResultList();
    }

    // MANDATORY: Transaction must be created before.
    @Transactional(propagation = Propagation.MANDATORY )
    public void addAmount(Long id, double amount) throws BalanceTransactionException {
        User user = this.findUserById(id);
        if (user == null) {
            throw new BalanceTransactionException("Account not found " + id);
        }
        double newBalance = user.getBalance() + amount;
        if (user.getBalance() + amount < 0) {
            throw new BalanceTransactionException(
                    "The money in the account '" + id + "' is not enough (" + user.getBalance() + ")");
        }
        user.setBalance(newBalance);
    }

    public void addBalanceTransaction (Long fromUserId, Long forPeriodicalId, double amount) {
    }

    // Do not catch BalanceTransactionException in this method.
    @Transactional(propagation = Propagation.REQUIRES_NEW,
            rollbackFor = BalanceTransactionException.class)
    public void sendMoney(Long fromUserId, Long forPeriodicalId, double amount) throws BalanceTransactionException {
        addBalanceTransaction(fromUserId, forPeriodicalId, amount);
        addAmount(fromUserId, -amount);
    }

}