package de.tu_berlin.kbs.mwk.test;

public class Algebra implements java.io.Serializable {
	
	public float a;
	public float res;
	
	public Algebra(float a) {
		this.a = a;
	}

	public float divide(float b) {
		res = a / b;
		return res;
	}
	
	public float subtract(float b) {
		res = a - b;
		return res;
	}
	
	public float add(float b) {
		res = a + b;
		return res;
	}
	
	public float multiply(float b) {
		res = a * b;
		return res;
	}
}
