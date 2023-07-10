package br.ufc.tpii.vmsys.exceptions;

public class OutOfStock extends Exception {

	private static final long serialVersionUID = 1L;

	public OutOfStock() {
		super();
	}

	public OutOfStock(String message) {
		super(message);
	}
	

}
