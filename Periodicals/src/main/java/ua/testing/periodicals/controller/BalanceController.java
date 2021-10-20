package ua.testing.periodicals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.testing.periodicals.form.SubscribeForm;
import ua.testing.periodicals.model.dao.BalanceDAO;
import ua.testing.periodicals.model.dao.BalanceTransactionException;
import ua.testing.periodicals.model.dao.BalanceTransactionInfo;

import java.util.List;

/**
 * The Balance controller.
 */
public class BalanceController {
    @Autowired
    private BalanceDAO balanceDAO;

    /**
     * Show bank accounts form.
     *
     * @param model the model
     * @return transactions info template name
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showBankAccounts(Model model) {
        List<BalanceTransactionInfo> list = balanceDAO.listBalanceTransactionInfo();

        model.addAttribute("accountInfos", list);

        return "transactions_info.html";
    }

    /**
     * Subscribe page string.
     *
     * @param model the model
     * @return subscribe template name
     */
    @RequestMapping(value = "/sendMoney", method = RequestMethod.GET)
    public String subscribePage(Model model) {

        SubscribeForm form = new SubscribeForm(1L, 2L, 700d);

        model.addAttribute("subscribeForm", form);

        return "subscribe.html";
    }

    /**
     * Process the send money form.
     *
     * @param model         the model
     * @param subscribeForm the subscribe form
     * @return the redirection endpoint
     */
    @RequestMapping(value = "/sendMoney", method = RequestMethod.POST)
    public String processSendMoney(Model model, SubscribeForm subscribeForm) {
        System.out.println("Send Money: " + subscribeForm.getAmount());
        try {
            balanceDAO.sendMoney(subscribeForm.getFromUserId(), //
                    subscribeForm.getforPeriodicalId(), //
                    subscribeForm.getAmount());
        } catch (BalanceTransactionException e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "/sendMoneyPage";
        }
        return "redirect:/";
    }
}
