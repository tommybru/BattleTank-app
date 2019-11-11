// BattleTank
// game.java
//
// Tommy Bruzzese
//
// BattleTank is an attacking/defending tank game where each player uses their
// unique fleet of tanks to try and defeat their opponent. With the different
// tank types, each having a different Armor, Resilience, Health, Staff, and Power,
// BattleTank is as much a game of strategy as it is a game of luck.


import java.util.Scanner;

public class game {

	static boolean gameover = false;
	static String p1name;
	static String p2name;
	static tank[] p1tanks = new tank[3];
	static tank[] p2tanks = new tank[3];
	static boolean p1turn = false;
	static String answer;
	static int answerInt;
	static int diceRolled;
	static int randomNum;
	static tank attackingTank;
	static int attackTotal;
	static tank defendingTank;
	static int healTotal;
	static int time;
	static boolean attacked = false;



	public static void main(String[] args) {
		welcome();
		picking();
		while(!gameover) {
			System.err.flush();
			System.out.flush();
			attack();
			if(attacked && defendingTank.health > 0 && defendingTank.staff > 0) {
				sleep(time = 3000);
				System.err.flush();
				System.out.flush();
				defend();
			}
			else {
				sleep(time = 1000);
				System.out.println(" ");
				System.out.println("There was no attack or the Defending Tank's health or staff is 0 or below. There will be no defense.");
				sleep(time = 100);
			}
			if(p1turn) {
				p1turn = false;
			}
			else {
				p1turn = true;
			}
			checkP1();
			checkP2();
			if(checkP1() || checkP2()) {
				gameover = true;
			}
			sleep(time = 5000);
		}

		System.out.println(" ");
		sleep(time = 700);
		System.err.print("WINNER");
		sleep(time = 1);

		for(int i =0;i<6;i++) {
			sleep(time = 250);
			System.out.print(".");

		}
		sleep(time = 200);
		if(checkP1()){
			System.err.print(p2name);
		}
		else {
			System.err.print(p1name);
		}
		sleep(time = 1);
		System.out.print(" has ANNIHILATED ");
		if(checkP1()){
			System.err.print(p1name);
		}
		else {
			System.err.print(p2name);
		}
		sleep(time = 4);
		System.out.println(" ");
		System.out.println("CONGRATS! GAME OVER!");


	}

  /* welcome introduces players to the game by having them input their player names
  and learn about the tank types they can choose from
  */
	public static void welcome() {
		sleep(time = 200);
		Scanner theInput = new Scanner(System.in);
		System.err.print("WELCOME TO RISK");
		for(int i =0;i<6;i++) {
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.err.print(".");

		}
		System.out.println(" ");
		System.out.println(" ");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Player 1, what is your name?");
		p1name = theInput.nextLine();
		System.out.println("Player 2, what is your name?");
		p2name = theInput.nextLine();

		System.out.println(" ");
		System.out.println("Here are your tank options:");
		tank exattacker = new tank();
		exattacker.attacker = true;
		tank exhealer = new tank();
		exhealer.healer = true;
		tank exarmor = new tank();
		exarmor.wellarmor = true;
		tank[] exampleTanks = {exattacker, exhealer, exarmor};


		for(int i=0;i<3;i++) {
			System.out.println(" ");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			exampleTanks[i].setup();
			System.err.println(exampleTanks[i].type);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			exampleTanks[i].printAllInfo();

		}
	}

  /* from the tank types displayed in welcome(), picking
  alternates between the 2 players until they have each picked
  3 different tanks to joint their fleet
  */
	public static void picking() {
		Scanner theInput = new Scanner(System.in);
		System.out.println(" ");
		System.out.println(" ");
		sleep(time = 1000);
		System.err.println("DRAFTING");
		sleep(time = 500);
		System.out.println("Type 1 is Attacker");
		System.out.println("Type 2 is Healer");
		System.out.println("Type 3 is Well-Armored");
		System.out.println(" ");
		sleep(time = 1000);
		for(int i=0;i<3;i++) {
			System.err.print(p1name);
			System.out.print(" pick the type of your Tank #" + (i+1));
			System.out.println(" ");
			answerInt = Integer.parseInt(theInput.nextLine());
			while((answerInt != 1) && (answerInt != 2) && (answerInt !=3)) {
				System.out.println("Please Pick: 1, 2, or 3");
				System.err.print("");
				System.out.println("1 for Attacker, 2 for Healer, or 3 for Well-Armored");
				answerInt = Integer.parseInt(theInput.nextLine());
			}
			p1tanks[i] = new tank();
			if(answerInt == 1) {
				p1tanks[i].attacker = true;
			}
			if(answerInt == 2) {
				p1tanks[i].healer = true;
			}
			if(answerInt == 3) {
				p1tanks[i].wellarmor = true;
			}
			p1tanks[i].setup();


			System.err.print(p2name);
			System.out.print(" pick the type of your Tank #" + (i+1));
			System.out.println(" ");
			answerInt = Integer.parseInt(theInput.nextLine());
			while((answerInt != 1) && (answerInt != 2) && (answerInt !=3)) {
				System.out.println("Please Pick: 1, 2, or 3");
				System.err.print("");
				System.out.println("1 for Attacker, 2 for Healer, or 3 for Well-Armored");
				answerInt = Integer.parseInt(theInput.nextLine());
			}
			p2tanks[i] = new tank();
			if(answerInt == 1) {
				p2tanks[i].attacker = true;
			}
			if(answerInt == 2) {
				p2tanks[i].healer = true;
			}
			if(answerInt == 3) {
				p2tanks[i].wellarmor = true;
			}
			p2tanks[i].setup();


			randomNum = (int)(Math.random()*2);
			if(randomNum == 0) {
				p1turn = true;
			}
			else {
				p1turn = false;
			}
		}
	}

  /* runs through a full attacking round (i.e., both players attack).
  Asks each player if they want to attack, using which tank, and then attacks
  a random alive tank from the opponent accordingly. Updates all the necessary
  attributes of each tank involved in the fight.
  */
	public static void attack() {
		Scanner theInput = new Scanner(System.in);
		System.out.println(" ");
		sleep(time = 700);
		System.err.print("ATTACKING");
		sleep(time = 1);

		for(int i =0;i<6;i++) {
			sleep(time = 250);
			System.out.print(".");

		}
		System.out.println(" ");
		if(p1turn) {
			System.err.println("It is " + p1name +"'s turn");
			System.out.println(" ");
			sleep(time = 1);
			System.out.println(p1name +", would you like to attack? Yes or No");
			answer = theInput.nextLine();
			if(answer.equals("Yes") || answer.equals("yes") || answer.equals("y")) {
				attacked = true;
				System.out.println("Which tank would you like to use to attack?");
				for(int i=0;i<3;i++) {
					System.out.println(" ");
					sleep(time = 700);
					System.err.println("Tank #"+(i+1));
					if(!p1tanks[i].isDead()) {
						sleep(time=5);
						System.err.println(p1tanks[i].type);
						sleep(time=100);
						p1tanks[i].printAllInfo();
					}
					else {
						sleep(time=5);
						System.out.println("DEAD");
					}

				}
				answerInt = Integer.parseInt(theInput.nextLine());
				while(((answerInt != 1) && (answerInt != 2) && (answerInt !=3)) || p1tanks[answerInt-1].health<=0  || p1tanks[answerInt-1].staff<=0) {
					System.out.println("Please Pick: 1, 2, or 3");
					System.err.print("");
					System.out.println("1 for Tank #1, 2 for Tank #2, or 3 for Tank #3");
					System.out.println("Also make sure you are selecting a tank that has health and staff left.");
					answerInt = Integer.parseInt(theInput.nextLine());
				}
				sleep(time = 40);
				System.err.print(p1name);
				System.out.print(" is now attacking with tank #" + answerInt);
				System.out.println(" ");
				attackingTank = p1tanks[answerInt-1];
				sleep(time = 400);
				randomNum = (int)(Math.random()*3);
				while(p2tanks[randomNum].health <= 0) {
					randomNum = (int)(Math.random()*3);
				}
				sleep(time = 1000);
				System.err.print(p2name);
				System.out.println(" will be defending with randomly selected tank #" + (randomNum+1));
				System.out.println(" ");
				sleep(time = 2);
				defendingTank = p2tanks[randomNum];
				System.err.println("Tank #"+(randomNum + 1));
				sleep(time = 500);
				System.err.println(p2tanks[randomNum].type);
				sleep(time = 100);
				p2tanks[randomNum].printAllInfo();
				sleep(time = 300);
				System.out.println(" ");
				System.out.println(" ");
				sleep(time = 2);
				System.err.print(p1name);
				System.out.println(", how many dice will you roll? (Maximum of 6) (Staff of tank #"+answerInt+" will be reduced by the amount of dice you roll)");
				diceRolled = Integer.parseInt(theInput.nextLine());
				while( ( (diceRolled != 1) && (diceRolled != 2) && (diceRolled !=3)  && (diceRolled !=4)  && (diceRolled !=5)  && (diceRolled !=6) ) || diceRolled>attackingTank.staff) {
					System.out.println("Please Pick: 1, 2, 3, 4, 5, or 6 dice to roll");
					System.out.println("Make sure you have enough staff for the amount of dice as well");
					diceRolled = Integer.parseInt(theInput.nextLine());
				}
				int[] diceRolls;
				attackTotal = attackingTank.printAndAddDice(diceRolls = attackingTank.rollDice(diceRolled, 6));
				System.out.println(" ");
				sleep(time = 1);
				if(!attackingTank.actuallyAttack(attackTotal = attackTotal, attackingTank = attackingTank, defendingTank = defendingTank, p1turn = p1turn, p1name = p1name, p2name = p2name)) {
					attacked = false;
				}
				attackingTank.redoAttackingStaff(diceRolled = diceRolled, attackingTank = attackingTank, p1turn = p1turn, p1name = p1name, p2name = p2name);

				for(int i=0;i<3;i++) {
					System.out.println(" ");
					sleep(time = 700);
					System.err.println("Tank #"+(i+1));
					sleep(time=5);
					if(!p1tanks[i].isDead()) {
						System.err.println(p1tanks[i].type);
						sleep(time=100);
						if(i!=(answerInt-1)) {
							p1tanks[i].staff = p1tanks[i].staff + 1;
							p1tanks[i].health = p1tanks[i].health + 5;
						}
						p1tanks[i].printAllInfo();
					}
					else {
						System.out.println("DEAD");
					}

				}
			}
			else {
				attacked = false;
				System.out.println("Okay, all alive tanks get +2 staff and +10 health");
				sleep(time = 1000);
				System.err.print(p1name);
				System.out.println(", here are your tanks current attributes:");
				sleep(time = 1);
				for(int i=0;i<3;i++) {
					System.out.println(" ");
					sleep(time = 700);
					System.err.println("Tank #"+(i+1));
					sleep(time=5);
					if(!p1tanks[i].isDead()) {
						System.err.println(p1tanks[i].type);
						sleep(time=100);
						p1tanks[i].staff = p1tanks[i].staff + 2;
						p1tanks[i].health = p1tanks[i].health + 10;
						p1tanks[i].printAllInfo();
					}
					else {
						System.out.println("DEAD");
					}

				}
			}
		}
		else {
			System.err.println("It is " + p2name +"'s turn");
			System.out.println(" ");
			sleep(time = 1);
			System.out.println(p2name +", would you like to attack? Yes or No");
			answer = theInput.nextLine();
			if(answer.equals("Yes") || answer.equals("yes") || answer.equals("y")) {
				attacked = true;
				System.out.println("Which tank would you like to use to attack?");
				for(int i=0;i<3;i++) {
					System.out.println(" ");
					sleep(time = 700);
					System.err.println("Tank #"+(i+1));
					sleep(time=5);
					if(!p2tanks[i].isDead()) {
						System.err.println(p2tanks[i].type);
						sleep(time=100);
						p2tanks[i].printAllInfo();
					}
					else {
						System.out.println("DEAD");
					}

				}
				answerInt = Integer.parseInt(theInput.nextLine());
				while(((answerInt != 1) && (answerInt != 2) && (answerInt !=3)) || p2tanks[answerInt-1].health<=0 || p2tanks[answerInt-1].staff<=0) {
					System.out.println("Please Pick: 1, 2, or 3");
					System.err.print("");
					System.out.println("1 for Tank #1, 2 for Tank #2, or 3 for Tank #3");
					System.out.println("Also make sure you are selecting a tank that has health and staff left.");
					answerInt = Integer.parseInt(theInput.nextLine());
				}
				sleep(time = 4);
				System.err.print(p2name);
				System.out.print(" is now attacking with tank #" + answerInt);
				System.out.println(" ");
				attackingTank = p2tanks[answerInt-1];
				sleep(time = 400);
				randomNum = (int)(Math.random()*3);
				while(p1tanks[randomNum].health <= 0) {
					randomNum = (int)(Math.random()*3);
				}
				sleep(time = 1000);
				System.err.print(p1name);
				System.out.println(" will be defending with randomly selected tank #" + (randomNum+1));
				System.out.println(" ");
				sleep(time = 2);
				defendingTank = p1tanks[randomNum];
				System.err.println("Tank #"+(randomNum + 1));
				sleep(time = 500);
				System.err.println(p1tanks[randomNum].type);
				sleep(time = 100);
				p1tanks[randomNum].printAllInfo();
				sleep(time = 300);
				System.out.println(" ");
				System.out.println(" ");
				sleep(time = 1);
				System.err.print(p2name);
				System.out.println(", how many dice will you roll? (Maximum of 6) (Staff of tank #"+answerInt+" will be reduced by the amount of dice you roll)");
				diceRolled = Integer.parseInt(theInput.nextLine());
				while(((diceRolled != 1) && (diceRolled != 2) && (diceRolled !=3)  && (diceRolled !=4)  && (diceRolled !=5) && (diceRolled !=6)) || diceRolled>p2tanks[answerInt-1].staff) {
					System.out.println("Please Pick: 1, 2, 3, 4, 5, or 6 dice to roll");
					System.out.println("Make sure you have enough staff for that dice roll as well");
					diceRolled = Integer.parseInt(theInput.nextLine());
				}
				int[] diceRolls;
				attackTotal = attackingTank.printAndAddDice(diceRolls = attackingTank.rollDice(diceRolled, 6));
				System.out.println(" ");
				sleep(time = 1);
				if(!attackingTank.actuallyAttack(attackTotal = attackTotal, attackingTank = attackingTank, defendingTank = defendingTank, p1turn = p1turn, p1name = p1name, p2name = p2name)) {
					attacked = false;
				}
				attackingTank.redoAttackingStaff(diceRolled = diceRolled, attackingTank = attackingTank, p1turn = p1turn, p1name = p1name, p2name = p2name);

				for(int i=0;i<3;i++) {
					System.out.println(" ");
					sleep(time = 700);
					System.err.println("Tank #"+(i+1));
					sleep(time=5);
					if(!p2tanks[i].isDead()) {
						System.err.println(p2tanks[i].type);
						sleep(time=100);
						if(i!=(answerInt-1)) {
							p2tanks[i].staff = p2tanks[i].staff + 1;
							p2tanks[i].health = p2tanks[i].health + 5;
						}
						p2tanks[i].printAllInfo();
					}
					else {
						System.out.println("DEAD");
					}

				}
			}
			else {
				attacked = false;
				System.out.println("Okay, all alive tanks get +2 staff and +10 health");
				sleep(time = 1000);
				System.err.print(p2name);
				System.out.println(", here are your tanks current attributes:");
				sleep(time = 1);
				for(int i=0;i<3;i++) {
					System.out.println(" ");
					sleep(time = 700);
					System.err.println("Tank #"+(i+1));
					sleep(time=5);
					if(!p2tanks[i].isDead()) {
						System.err.println(p2tanks[i].type);
						sleep(time=100);
						p2tanks[i].staff = p2tanks[i].staff + 2;
						p2tanks[i].health = p2tanks[i].health + 10;
						p2tanks[i].printAllInfo();
					}
					else {
						System.out.println("DEAD");
					}

				}
			}
		}

	}

  /* runs through the setup for a defense from the tank that was just damaged.
  The amount of Health that a player can give back to their damaged tank is
  dependent on that tank's Resilience and remaining Staff.
  */
	public static void defend() {
		Scanner theInput = new Scanner(System.in);
		System.out.println(" ");
		sleep(time = 700);
		System.err.print("DEFENDING");
		sleep(time = 1);

		for(int i =0;i<6;i++) {
			sleep(time = 250);
			System.out.print(".");

		}
		System.out.print(" ");
		sleep(time = 25);
		if(p1turn) {
			System.err.print(p2name);
		}
		else {
			System.err.print(p1name);
		}
		sleep(time = 1);
		System.out.println(", you can now recover. Here are the attributes of your tank that was just attacked: ");

		System.out.println(" ");
		sleep(time = 700);
		System.err.println("Tank #"+(randomNum+1));
		sleep(time=5);
		System.err.println(defendingTank.type);
		sleep(time=100);
		defendingTank.printAllInfo();

		System.out.println("Your tank has a resilience of " + defendingTank.resilience + ", would you like to recover it? Yes or No.");
		answer = theInput.nextLine();
		if(answer.equals("Yes") || answer.equals("yes") || answer.equals("y")) {
			System.out.println("How many dice would you like to roll? (Maximum is " + defendingTank.resilience + ") (The staff of the tank will be reduced by how many dice you roll)");
			diceRolled = Integer.parseInt(theInput.nextLine());
			while( (diceRolled <= 0) || (diceRolled > defendingTank.resilience) || diceRolled>defendingTank.staff) {
				System.out.println("Please pick a number of dice between 1 and " + defendingTank.resilience);
				System.out.println("Make sure you have enough staff for that dice roll as well");
				diceRolled = Integer.parseInt(theInput.nextLine());
			}
			int[] diceRolls;
			healTotal = defendingTank.printAndAddDice(diceRolls = defendingTank.rollDice(diceRolled, 6));
			defendingTank.actuallyDefend(defendingTank = defendingTank, healTotal = healTotal);
			System.out.println(" ");
			defendingTank.redoDefendingStaff(diceRolled = diceRolled, defendingTank = defendingTank, p1turn = p1turn, p1name = p1name, p2name = p2name);
			sleep(time = 1);
			System.err.println("Tank #"+(randomNum + 1));
			sleep(time = 500);
			System.err.println(defendingTank.type);
			sleep(time = 100);
			defendingTank.printAllInfo();
			sleep(time = 300);
			System.out.println(" ");
			System.out.println(" ");


		}
		else {
			System.out.println("Okay.");
			sleep(time = 400);
		}
	}

  /*Checking to see if either player 1 or player 2 has lost */

	public static boolean checkP1() {
		if( p1tanks[0].isDead() && p1tanks[1].isDead() && p1tanks[2].isDead() ) {
			return true;
		}
		else {
			return false;
		}
	}

	public static boolean checkP2() {
		if( p2tanks[0].isDead() && p2tanks[1].isDead() && p2tanks[2].isDead() ) {
			return true;
		}
		else {
			return false;
		}
	}

	public static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.err.flush();
		System.out.flush();
	}


}
