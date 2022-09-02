package ru.otus.ATM;

import ru.otus.Banknotes.Banknote;
import ru.otus.Cases.*;

public class ATM implements StorageMoney {

    private final ServiceWithdrawalMoney withdrawal = new ServiceWithdrawalMoneyImpl();

    public void loadATM (Banknote banknote) {
        withdrawal.loadCases(banknote);
    }

    @Override
    public void getMoney(int amount) {
        if (withdrawal.cashOut(amount)){
            System.out.println("Заберите деньги");
        }else System.out.println("Нет купюр в банкомате, чтобы снять сумму");
    }

    @Override
    public int isBalance() {
        return withdrawal.issueRemainingMoney();
    }

    @Override
    public void sheetBalanceOfATM (){
        withdrawal.toStringBalanceATM();
    }
}
