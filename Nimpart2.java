import support.cse131.ArgsProcessor;

public class Nimpart2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArgsProcessor ap = new ArgsProcessor(args);
		int start = ap.nextInt("How many sticks do you start with?");
		int left = start;
		int round = 0;

		while (left >0) {
			int take = ap.nextInt("How many sticks do you take (only 1 or 2)?");
			while (take > 2  || take >start) {
				take = ap.nextInt("How many sticks do you take (only 1 or 2)?");
			}
			while (take <= 0) {
				take = ap.nextInt("How many sticks do you take (only 1 or 2)?");
			}
			left = left - take;
			System.out.println("Round " +round+ ": Starting with "+ start+ ", you take "+take+". "+left+ " remain");
			if (left == 0) {
				System.out.println("Congratulations: you win! :) ");
			}
			else {


				start= left;
				round = round +1;
				double ran = Math.random();
				if (ran >.5) {
					take = 1;
				}
				else {
					if (left==1) {
						take =1;
					}
					else {
						take = 2;
					}

				}
				left = left - take;
				System.out.println("Round " +round+ ": Starting with "+ start+ ", Computer takes "+take+". "+left+ " remain");
				round =round +1;
				start = left;
				if (left ==0) {
					System.out.println("Game over, the Computer won. :(");
				}
			}

		}

	}

}


