package ru.otus.cases;

import ru.otus.banknotes.BanknoteToNominalRuble;

import java.util.*;

public class ServiceWithdrawalMoneyImpl implements ServiceWithdrawalMoney {

    private final Map<BanknoteToNominalRuble, Case> listCases = new EnumMap<>(BanknoteToNominalRuble.class);

    public ServiceWithdrawalMoneyImpl(){
        listCases.put(BanknoteToNominalRuble.FIFTY, new CaseImpl(BanknoteToNominalRuble.FIFTY));
        listCases.put(BanknoteToNominalRuble.ONE_HUNDRED, new CaseImpl(BanknoteToNominalRuble.ONE_HUNDRED));
        listCases.put(BanknoteToNominalRuble.TWO_HUNDRED, new CaseImpl(BanknoteToNominalRuble.TWO_HUNDRED));
        listCases.put(BanknoteToNominalRuble.FIVE_HUNDRED, new CaseImpl(BanknoteToNominalRuble.FIVE_HUNDRED));
        listCases.put(BanknoteToNominalRuble.ONE_THOUSAND, new CaseImpl(BanknoteToNominalRuble.ONE_THOUSAND));
        listCases.put(BanknoteToNominalRuble.TWO_THOUSAND, new CaseImpl(BanknoteToNominalRuble.TWO_THOUSAND));
        listCases.put(BanknoteToNominalRuble.FIVE_THOUSAND, new CaseImpl(BanknoteToNominalRuble.FIVE_THOUSAND));
    }

    @Override
    public void loadCases(BanknoteToNominalRuble banknote) {
        listCases.get(banknote).addBanknoteInCase();
    }

    @Override
    public boolean cashOut(int amount) {
        List<BanknoteToNominalRuble> listNominal = new ArrayList<>(listCases.keySet());
        listNominal.sort(Comparator.comparing(BanknoteToNominalRuble::returnNominal).reversed());

        if (!isCanGetMoney(amount, listNominal)) {
            return false;
        } else {
            for (BanknoteToNominalRuble nominal : listNominal) {
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

    public int makeBanknoteCounter(int amount, BanknoteToNominalRuble nominal) {
        int countBanknotes = amount / nominal.returnNominal();
        int result = listCases.get(nominal).takeMoneyOutCase(countBanknotes);
        if (result == 0) {
            return amount - nominal.returnNominal() * countBanknotes;
        } else return amount - nominal.returnNominal() * (countBanknotes - result);
    }

    public boolean isCanGetMoney(int amount, List<BanknoteToNominalRuble> list) {
        for (BanknoteToNominalRuble currentNominal : list) {
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
