package ru.otus.ATM;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import ru.otus.banknotes.BanknoteToNominalRuble;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ATMTest {

    private StorageMoney testATM = new ATM();
    private final BanknoteToNominalRuble [] initialListBanknote = {
            BanknoteToNominalRuble.FIFTY,
            BanknoteToNominalRuble.ONE_HUNDRED,
            BanknoteToNominalRuble.TWO_HUNDRED,
            BanknoteToNominalRuble.FIVE_HUNDRED,
            BanknoteToNominalRuble.ONE_THOUSAND,
            BanknoteToNominalRuble.TWO_THOUSAND,
            BanknoteToNominalRuble.FIVE_THOUSAND
    };

    @Before
    public void setUp() {
        testATM = new ATM();
    }

    @Test
    @DisplayName("загружает купюру в банкомат")
    public void loadATM() {
        testATM.loadATM(initialListBanknote[0]);
        assertThat(initialListBanknote[0].returnNominal()).isEqualTo(testATM.isBalance());
    }

    @Test
    @DisplayName("выдает деньги")
    public void getMoney() {
        assertThat(0).isEqualTo(testATM.isBalance());
        int balance = 0;
        for (BanknoteToNominalRuble currentBanknote : initialListBanknote){
            testATM.loadATM(currentBanknote);
            balance = balance + currentBanknote.returnNominal();
        }
        assertThat(balance).isEqualTo(testATM.isBalance());
        testATM.getMoney(50);
        assertThat(balance - 50).isEqualTo(testATM.isBalance());

    }

    @Test
    @DisplayName("показывает баланс")
    public void isBalance() {
        assertThat(0).isEqualTo(testATM.isBalance());
        testATM.loadATM(initialListBanknote[2]);
        assertThat(initialListBanknote[2].returnNominal()).isEqualTo(testATM.isBalance());
    }

    @After
    public void tearDown() {

    }
}