package ru.otus.Cases;

import ru.otus.Banknotes.Banknote;
import ru.otus.Banknotes.NominalRuble;

import java.util.*;

public class ServiceWithdrawalMoneyImpl implements ServiceWithdrawalMoney {

    private final Map<NominalRuble, Case> listCases = new EnumMap<>(NominalRuble.class);

    public ServiceWithdrawalMoneyImpl(){
        listCases.put(NominalRuble.FIFTY, new CaseImpl(NominalRuble.FIFTY));
        listCases.put(NominalRuble.ONE_HUNDRED, new CaseImpl(NominalRuble.ONE_HUNDRED));
        listCases.put(NominalRuble.TWO_HUNDRED, new CaseImpl(NominalRuble.TWO_HUNDRED));
        listCases.put(NominalRuble.FIVE_HUNDRED, new CaseImpl(NominalRuble.FIVE_HUNDRED));
        listCases.put(NominalRuble.ONE_THOUSAND, new CaseImpl(NominalRuble.ONE_THOUSAND));
        listCases.put(NominalRuble.TWO_THOUSAND, new CaseImpl(NominalRuble.TWO_THOUSAND));
        listCases.put(NominalRuble.FIVE_THOUSAND, new CaseImpl(NominalRuble.FIVE_THOUSAND));
    }

    @Override
    public void loadCases(Banknote banknote) {
        listCases.get(banknote).addBanknoteInCase();
    }

    @Override
    public boolean cashOut(int amount) {
        List<Banknote> listNominal = new ArrayList<>(listCases.keySet());
        listNominal.sort(Comparator.comparing(Banknote::returnNominal).reversed());

        if (!isCanGetMoney(amount, listNominal)) {
            return false;
        } else {
            for (Banknote nominal : listNominal) {
                if (amount == 0) {
                    break;
                } else {
                    if (amount >= nominal.returnNominal()) {
                        amount = makeBanknoteCounter(amount, nominal);
                    }
                }
            }
        }
        return true;
    }

    public int makeBanknoteCounter(int amount, Banknote nominal) {
        int countBanknotes = amount / nominal.returnNominal();
        int result = listCases.get(nominal).takeMoneyOutCase(countBanknotes);
        if (result == 0) {
            return amount - nominal.returnNominal() * countBanknotes;
        } else return amount - nominal.returnNominal() * (countBanknotes - result);
    }

    public boolean isCanGetMoney(int amount, List<Banknote> list) {
        for (Banknote currentNominal : list) {
            if (amount >= currentNominal.returnNominal()) {
                int temp = amount / currentNominal.returnNominal();
                int result = isHaveQuantityBanknoteInCase(listCases.get(currentNominal), temp);
                if (result == 0) {
                    amount = amount - currentNominal.returnNominal() * temp;
                } else {
                    amount = amount - currentNominal.returnNominal() * (temp - result);
                }
            }
        }
        return amount == 0;
    }

    public int isHaveQuantityBanknoteInCase(Case currentCase, int count) {
        if (count <= currentCase.getQuantityBanknote()) {
            return 0;
        } else {
            return count - currentCase.getQuantityBanknote();
        }
    }

    @Override
    public int issueRemainingMoney() {
        int remainingMoney = 0;
        List<Case> cases = new ArrayList<>(listCases.values());
        for (Case currentCase : cases) {
            remainingMoney = remainingMoney + currentCase.calculateBalanceOfCase();
        }
        System.out.println("Остаток денег в банкомате: " + remainingMoney);
        return remainingMoney;
    }

    public void toStringBalanceATM() {
        System.out.println("Выписка о количестве купюр по номиналам:");
        List<Case> cases = new ArrayList<>(listCases.values());
        for (Case currentCase : cases) {
            currentCase.toStringBalanceOfCase();
        }
    }
}
