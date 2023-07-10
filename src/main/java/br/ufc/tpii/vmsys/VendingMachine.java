package br.ufc.tpii.vmsys;

import br.ufc.tpii.vmsys.exceptions.InsufficientFunds;
import br.ufc.tpii.vmsys.exceptions.InvalidSelection;
import br.ufc.tpii.vmsys.exceptions.OutOfStock;
import br.ufc.tpii.vmsys.inventory.Inventory;
import br.ufc.tpii.vmsys.inventory.Item;
import br.ufc.tpii.vmsys.inventory.exceptions.ItemNotFound;

public class VendingMachine {

	private double coinsDeposited = 0.0;

	public double getCoinsDeposited() {
		return coinsDeposited;
	}

	private Inventory inventory;

	public VendingMachine(Inventory inventory) {
		this.inventory = inventory;
	}

	public void addCoins(double coins) {
		this.coinsDeposited += coins;
	}
	
	public double withdrawRemainingCoins() {
		double remainingCoins = this.coinsDeposited;
		this.coinsDeposited = 0.0;
		return remainingCoins;
	}

	public double howManyCoinsLeft() {
		return this.coinsDeposited;
	}

	public void vend(String itemName, double coinsDeposited) throws InvalidSelection, OutOfStock, InsufficientFunds {
		Item item = null;

		try {
			item = inventory.getItem(itemName);
			
		} catch (ItemNotFound inf) {
			throw new InvalidSelection("Invalid item selection: " + itemName);
		}

		if (item.getCount() == 0) {
			throw new OutOfStock("Item out of stock: " + itemName);
		}

		if (item.getPrice() > coinsDeposited) {
			throw new InsufficientFunds("Insufficient coins to by " + itemName + ": " + this.coinsDeposited);
		}

		this.coinsDeposited += item.getPrice();

		item.decCount();
	}
}