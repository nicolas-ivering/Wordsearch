package se.powerballad.wordsearch;

public class NumberFinder {
	
	public static void main(String[] args) {
		int number = 15;
	
		System.out.println("answer = " + findStartNum(number));
		System.out.println("answer = " + findStartNum2(number));
	}

	private static int findStartNum(int number) {
		for(int n = 0; n < Integer.MAX_VALUE; n++) {
			int sum = n*5 + 10;
			System.out.println(sum);
			if(sum%number == 0) {
				return n;
			}
		}
		
		return -1;
	}
	
	private static int findStartNum2(int number) {
		int sum;
		if(number % 5 != 0) {
			sum = number * 5;
		} else {
			sum = number;
		}
		int answer = (sum - 10) / 5;
		
		return answer;
	}
}
