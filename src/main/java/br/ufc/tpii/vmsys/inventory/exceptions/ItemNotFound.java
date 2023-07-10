package br.ufc.tpii.vmsys.inventory.exceptions;

public class ItemNotFound extends Exception {

	private static final long serialVersionUID = 1L;

	public ItemNotFound() {
		super();
	}

	public ItemNotFound(String message) {
		super(message);
	}
}
