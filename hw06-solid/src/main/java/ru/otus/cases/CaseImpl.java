package ru.otus.cases;

import ru.otus.banknotes.BanknoteToNominalRuble;

public class CaseImpl implements Case {

//    private static final int CAPACITY_CASE = 10;
    private int quantityBanknote;
    private final BanknoteToNominalRuble banknote;

    public CaseImpl(BanknoteToNominalRuble banknote){
        this.banknote = banknote;
//        this.quantityBanknote = CAPACITY_CASE;
    }

    public int getQuantityBanknote() {
        return quantityBanknote;
    }

    public void addBanknoteInCase(){
        quantityBanknote++;
    }

    public int takeMoneyOutCase (int countBanknotes){

        if (countBanknotes > quantityBanknote){
            int temp = countBanknotes - quantityBanknote;
            quantityBanknote = 0;
            return temp;
        } else {
            quantityBanknote = quantityBanknote - countBanknotes;
            return 0;
        }
    }

    public int calculateBalanceOfCase (){
        return quantityBanknote * banknote.returnNominal();
    }

    @Override
    public void toStringBalanceOfCase() {
        System.out.println("Банкнот номиналом " + banknote.returnNominal() + ": " + quantityBanknote + " шт.");
    }
}
