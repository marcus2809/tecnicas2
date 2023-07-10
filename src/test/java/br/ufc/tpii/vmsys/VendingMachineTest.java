package br.ufc.tpii.vmsys;

import br.ufc.tpii.vmsys.exceptions.InsufficientFunds;
import br.ufc.tpii.vmsys.exceptions.InvalidSelection;
import br.ufc.tpii.vmsys.exceptions.OutOfStock;
import br.ufc.tpii.vmsys.inventory.HashMapInventory;
import br.ufc.tpii.vmsys.inventory.Inventory;
import br.ufc.tpii.vmsys.inventory.Item;
import br.ufc.tpii.vmsys.inventory.exceptions.ItemAlreadyAdded;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineTest {

    @Test
    void addCoins() {
        Inventory inv = new HashMapInventory();
        VendingMachine vm = new VendingMachine(inv);

        vm.addCoins(10.0);

        assertEquals(10.0, vm.getCoinsDeposited());
    }
    @Test
    void addNegativeCoins(){
        Inventory inv = new HashMapInventory();
        VendingMachine vm = new VendingMachine(inv);

        vm.addCoins(-10.0);

        assertEquals(-10.0, vm.getCoinsDeposited());
    }

    @Test
    void withdrawRemainingCoins() {
        Inventory inv = new HashMapInventory();
        VendingMachine vm = new VendingMachine(inv);

        vm.addCoins(10.0);

        vm.withdrawRemainingCoins();

        assertTrue(vm.getCoinsDeposited() == 0.0);
    }

    @Test
    void withdrawRemainingCoinsEquals0() {
        Inventory inv = new HashMapInventory();
        VendingMachine vm = new VendingMachine(inv);

        vm.withdrawRemainingCoins();

        assertTrue(vm.getCoinsDeposited() == 0.0);
    }

    @Test
    void withdrawRemainingNegativeCoins() {
        Inventory inv = new HashMapInventory();
        VendingMachine vm = new VendingMachine(inv);

        vm.addCoins(-10.0);

        vm.withdrawRemainingCoins();

        assertTrue(vm.getCoinsDeposited() == 0.0);
    }

    @Test
    void howManyCoinsLeft() {
        Inventory inv = new HashMapInventory();
        VendingMachine vm = new VendingMachine(inv);

        vm.addCoins(10.0);

        assertEquals(vm.getCoinsDeposited(), vm.howManyCoinsLeft());
    }

    @Test
    void vend() {
        Item item = new Item("Lata", 5.0, 2);
        Inventory inv = new HashMapInventory();
        int numberItens = item.getCount();
        try {
            inv.addItem(item);
        } catch (ItemAlreadyAdded e) {
            throw new RuntimeException(e);
        }

        VendingMachine vm = new VendingMachine(inv);

        try {
            vm.vend("Lata", 6.0);
        } catch (InvalidSelection | InsufficientFunds | OutOfStock e) {
            throw new RuntimeException(e);
        }
        assertEquals(numberItens - 1, item.getCount());
    }

    @Test
    void vendWithNoItens() {
        Item item = new Item("Lata", 5.0, 0);
        Inventory inv = new HashMapInventory();
        int numberItens = item.getCount();
        try {
            inv.addItem(item);
        } catch (ItemAlreadyAdded e) {
            throw new RuntimeException(e);
        }

        VendingMachine vm = new VendingMachine(inv);
        vm.addCoins(7.0);

        assertThrowsExactly(OutOfStock.class, () -> vm.vend("Lata", 6.0));
    }

    @Test
    void vendWithNoCoins() {
        Item item = new Item("Lata", 5.0, 2);
        Inventory inv = new HashMapInventory();
        int numberItens = item.getCount();
        try {
            inv.addItem(item);
        } catch (ItemAlreadyAdded e) {
            throw new RuntimeException(e);
        }

        VendingMachine vm = new VendingMachine(inv);

        assertThrowsExactly(InsufficientFunds.class, () -> vm.vend("Lata", 0.0));
    }

    @Test
    void vendWithNegativeCoins() {
        Item item = new Item("Lata", 5.0, 2);
        Inventory inv = new HashMapInventory();
        int numberItens = item.getCount();
        try {
            inv.addItem(item);
        } catch (ItemAlreadyAdded e) {
            throw new RuntimeException(e);
        }

        VendingMachine vm = new VendingMachine(inv);

        assertThrowsExactly(InsufficientFunds.class, () -> vm.vend("Lata", -2.0));
    }
}