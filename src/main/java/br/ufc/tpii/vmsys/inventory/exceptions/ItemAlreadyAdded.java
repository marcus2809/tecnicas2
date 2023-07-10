package br.ufc.tpii.vmsys.inventory.exceptions;

public class ItemAlreadyAdded extends Exception {

	private static final long serialVersionUID = 1L;

	public ItemAlreadyAdded() {
		super();
	}

	public ItemAlreadyAdded(String message) {
		super(message);
	}
}
