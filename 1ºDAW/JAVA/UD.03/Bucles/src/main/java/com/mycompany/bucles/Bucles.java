package com.mycompany.bucles;

public class Bucles {

	public static void main(String[] args) {
		int i;
		for (i = 1; i <= 100; i++) {
			System.out.println(i);
		}

		System.out.println("-----------------------------------------------------------------");

		i = 1;
		while (i <= 100) {
			System.out.println(i);
			i++;
		}

		System.out.println("-----------------------------------------------------------------");

		i = 1;
		do {
			System.out.println(i);
			i++;
		} while (i <= 100);

		System.out.println("-----------------------------------------------------------------");

		for (i = 100; i >= 1; i--) {
			System.out.println(i);
		}
		System.out.println("-----------------------------------------------------------------");
		i = 100;
		while (i >= 1) {
			System.out.println(i);
			i--;
		}

		System.out.println("-----------------------------------------------------------------");

		i = 100;
		do {
			System.out.println(i);
			i--;
		} while (i >= 1);

	}
}
