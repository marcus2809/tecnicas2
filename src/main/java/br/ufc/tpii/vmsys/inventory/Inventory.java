package br.ufc.tpii.vmsys.inventory;

import br.ufc.tpii.vmsys.inventory.exceptions.ItemAlreadyAdded;
import br.ufc.tpii.vmsys.inventory.exceptions.ItemNotFound;

public interface Inventory {

	public void addItem(Item item) throws ItemAlreadyAdded;

	public void removeItem(String itemName) throws ItemNotFound;

	public Item getItem(String itemName) throws ItemNotFound;

	public int numberOfItems();
}
