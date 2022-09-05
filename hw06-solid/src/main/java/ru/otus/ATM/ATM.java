package ru.otus.ATM;

import ru.otus.banknotes.BanknoteToNominalRuble;
import ru.otus.cases.*;

public class ATM implements StorageMoney {

    private final ServiceWithdrawalMoney withdrawal = new ServiceWithdrawalMoneyImpl();

    public void loadATM (BanknoteToNominalRuble banknote) {
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
