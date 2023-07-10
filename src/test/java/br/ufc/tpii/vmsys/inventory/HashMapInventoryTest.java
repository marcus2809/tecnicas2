package br.ufc.tpii.vmsys.inventory;

import br.ufc.tpii.vmsys.inventory.exceptions.ItemAlreadyAdded;
import br.ufc.tpii.vmsys.inventory.exceptions.ItemNotFound;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashMapInventoryTest {

    @Test
    void addItem() {
        HashMapInventory hashMapInventory = new HashMapInventory();
        Item item = new Item("teste", 10.0, 2);
        try {
            hashMapInventory.addItem(item);
        } catch (ItemAlreadyAdded e) {
            throw new RuntimeException(e);
        }
        try {
            assertEquals(item, hashMapInventory.getItem("teste"));
        } catch (ItemNotFound e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void removeItem() {
        HashMapInventory hashMapInventory = new HashMapInventory();
        Item item = new Item("teste", 10.0, 2);
        try {
            hashMapInventory.addItem(item);
        } catch (ItemAlreadyAdded e) {
            throw new RuntimeException(e);
        }
        try {
            hashMapInventory.removeItem("teste");
        } catch (ItemNotFound e) {
            throw new RuntimeException(e);
        }
        assertThrowsExactly(ItemNotFound.class, () -> hashMapInventory.getItem("teste"));
    }

    @Test
    void removeNonExistentItem() {
        HashMapInventory hashMapInventory = new HashMapInventory();
        assertThrowsExactly(ItemNotFound.class, () -> hashMapInventory.removeItem("teste"));
    }

    @Test
    void removeNullItem() {
        HashMapInventory hashMapInventory = new HashMapInventory();
        String nullString = null;
        assertThrowsExactly(ItemNotFound.class, () -> hashMapInventory.removeItem(nullString));
    }

    @Test
    void getItem() {
        HashMapInventory hashMapInventory = new HashMapInventory();
        Item item1 = new Item("teste", 10.0, 2);
        Item item2 = new Item("teste2", 10.0, 2);
        try {
            hashMapInventory.addItem(item1);
            hashMapInventory.addItem(item2);
        } catch (ItemAlreadyAdded e) {
            throw new RuntimeException(e);
        }
        try {
            assertEquals(item2, hashMapInventory.getItem("teste2"));
        } catch (ItemNotFound e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getNoItem() {
        HashMapInventory hashMapInventory = new HashMapInventory();
        assertThrowsExactly(ItemNotFound.class, () -> hashMapInventory.getItem("teste"));
    }

    @Test
    void getNullItem() {
        HashMapInventory hashMapInventory = new HashMapInventory();
        String nullString = null;
        assertThrowsExactly(ItemNotFound.class, () -> hashMapInventory.getItem(nullString));
    }

    @Test
    void numberOfItems() {
        HashMapInventory hashMapInventory = new HashMapInventory();
        Item item1 = new Item("teste", 10.0, 2);
        Item item2 = new Item("teste2", 10.0, 2);
        try {
            hashMapInventory.addItem(item1);
            hashMapInventory.addItem(item2);
        } catch (ItemAlreadyAdded e) {
            throw new RuntimeException(e);
        }
        assertEquals(2, hashMapInventory.numberOfItems());

    }
}