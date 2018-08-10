package org.wyy.domain;

import java.util.Scanner;



public class Main {



	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		Fraction a = new Fraction(in.nextInt(), in.nextInt());

		Fraction b = new Fraction(in.nextInt(),in.nextInt());

		a.print();

		b.print();

		a.plus(b).print();

		a.multiply(b).plus(new Fraction(5,6)).print();

		a.print();

		b.print();

		in.close();

	}
}

class Fraction {

	private int molecule;
	private int denominator;

	public Fraction(int molecule, int denominator) {
		if(denominator == 0) {
			throw new IllegalArgumentException("分母不能为 0");
		}
		int gcd = getGCD(molecule, denominator);
		this.molecule = molecule/gcd;
		this.denominator = denominator/gcd;

	}
	public double toDouble() {
		return 1.0*molecule/denominator;
	}
	public Fraction multiply(Fraction b) {
		int newUp = this.getMolecule() * b.getMolecule();
		int newDown = this.denominator * b.getDenominator();

		return new Fraction(newUp,newDown);
	}

	public Fraction plus(Fraction b) {
		int newUp = this.molecule*b.getDenominator() + this.denominator*b.getMolecule();
		int newDown = this.denominator * b.getDenominator();

		return new Fraction(newUp,newDown);
	}

	private int getGCD(int newUp, int newDown) {
		int gcd = 1;
		int min = Integer.min(newUp, newDown);
		int max = Integer.max(newUp, newDown);

		for(int i=min;i>1;i--) {
			if(max%i == 0 && min%i == 0) {
				gcd = i;
				break;
			}
		}
		return gcd;
	}

	public void print() {
		if(molecule%denominator == 0) {
			System.out.println(molecule/denominator);
		}else {
			System.out.println(molecule+"/"+denominator);
		}
	}

	public int getMolecule() {
		return molecule;
	}

	public void setMolecule(int molecule) {
		this.molecule = molecule;
	}

	public int getDenominator() {
		return denominator;
	}

	public void setDenominator(int denominator) {
		this.denominator = denominator;
	}
}