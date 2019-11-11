public class tank extends sprite {
	boolean attacker;
	boolean healer;
	boolean wellarmor;
	String type;
	static int time;

	public void setup() {
		if(attacker) {
			type = "Attacker";
			power = 12;
			resilience = 3;
			armor = 7;
			health = 125;
			staff = 14;
		}
		else if(healer) {
			type = "Healer";
			power = 7;
			resilience = 4;
			armor = 6;
			health = 150;
			staff = 14;
		}
		else if(wellarmor) {
			type = "Well-Armored";
			power = 6;
			resilience = 2;
			armor = 13;
			health = 100;
			staff = 12;
		}
	}


	static int printAndAddDice(int[] diceRolls) {
		int sum = 0;
		sleep(time = 500);
		System.out.print("Your dice rolls are: ");
		for(int i=0;i<diceRolls.length;i++) {
			sleep(time = 500);
			System.out.print(diceRolls[i] + ", ");
			sum+=diceRolls[i];
		}
		sleep(time = 500);
		System.out.print("totaling " + sum);
		return sum;

	}

	static boolean actuallyAttack(int attackTotal, tank attackingTank, tank defendingTank, boolean p1turn, String p1name, String p2name) {
		sleep(time = 1500);
		if(p1turn) {
			System.err.print(p2name);
		}
		else {
			System.err.print(p1name);
		}
		sleep(time = 1);
		System.out.print("'s defending tank has armor of " + defendingTank.armor);
		System.out.println(" ");
		System.out.println(attackTotal + " - " + defendingTank.armor + " = " + (attackTotal - defendingTank.armor));
		if((attackTotal - defendingTank.armor)<=0) {
			sleep(time = 2000);
			System.out.println("Because there is no damage done, attack is now void.");
			return false;
		}
		else {
			sleep(time = 2000);
			if(p1turn) {
				System.err.print(p1name);
			}
			else {
				System.err.print(p2name);
			}
			sleep(time = 1);
			System.out.print("'s attacking tank has power of " + attackingTank.power);
			System.out.println(" ");
			System.out.println( (attackTotal - defendingTank.armor)  + " x " + attackingTank.power + " = " + ( (attackTotal - defendingTank.armor)  * attackingTank.power));
		}
		attackTotal = (attackTotal - defendingTank.armor) * attackingTank.power;
		if(attackTotal>0) {
			sleep(time = 2000);
			if(p1turn) {
				System.err.print(p2name);
			}
			else {
				System.err.print(p1name);
			}
			sleep(time = 1);
			System.out.print("'s defending tank started with health of " + defendingTank.health);
			System.out.println(" ");
			System.out.println(defendingTank.health + " - " + (attackTotal) + " = " + (defendingTank.health - attackTotal));
			defendingTank.health = defendingTank.health - attackTotal;
		}
		return true;
	}

	public void actuallyDefend(tank defendingTank, int healTotal) {
		sleep(time = 1000);
		System.out.println(" ");
		if(defendingTank.staff > 1) {
			System.out.print("Staff/2 is " + (defendingTank.staff/2) + ". ");
			sleep(time = 1000);
			System.out.print(healTotal + " x " +  (defendingTank.staff/2) + " = " + (healTotal * (defendingTank.staff/2)) + ".");
			sleep(time = 1000);
			System.out.println(" ");
			System.out.println("Health is now: " + defendingTank.health + " + " + (healTotal * (defendingTank.staff/2)));
			defendingTank.health = defendingTank.health + (healTotal * (defendingTank.staff/2));
		}
		else {
			sleep(time = 1000);
			System.out.print(healTotal + " x 1 = " + (healTotal) + ".");
			sleep(time = 1000);
			System.out.println(" ");
			System.out.println("Health is now: " + defendingTank.health + " + " + healTotal);
			defendingTank.health = defendingTank.health + (healTotal);
		}
	}

	public void redoAttackingStaff(int diceRolled, tank attackingTank, boolean p1turn, String p1name, String p2name) {
		sleep(time = 2500);
		if(p1turn) {
			System.err.print(p1name);
		}
		else {
			System.err.print(p2name);
		}
		sleep(time = 1);
		System.out.print(", your attacking tank started with staff of " + attackingTank.staff + ". ");
		if(diceRolled != 1)	{
			System.out.print("You rolled " + diceRolled + " dice. ");
		}
		else {
			System.out.print("You rolled " + diceRolled + " die. ");
		};
		System.out.println(attackingTank.staff + " - " + (diceRolled) + " = " + (attackingTank.staff - diceRolled));
		attackingTank.staff = attackingTank.staff - diceRolled;
		sleep(time = 2000);
		if(p1turn) {
			System.err.print(p1name);
		}
		else {
			System.err.print(p2name);
		}
		sleep(time = 1);
		System.out.print(", your remaining alive tanks received +1 staff and +5 health");
		System.out.println(" ");
		sleep(time = 5000);
		System.out.println("Here are your tanks' current attributes: ");

	}

	public void redoDefendingStaff(int diceRolled, tank defendingTank, boolean p1turn, String p1name, String p2name) {
		sleep(time = 2500);
		if(p1turn) {
			System.err.print(p2name);
		}
		else {
			System.err.print(p1name);
		}
		sleep(time = 1);
		System.out.print(", your defending tank started with staff of " + defendingTank.staff + ". ");
		if(diceRolled != 1)	{
			System.out.print("You rolled " + diceRolled + " dice. ");
		}
		else {
			System.out.print("You rolled " + diceRolled + " die. ");
		}
		System.out.println(defendingTank.staff + " - " + diceRolled + " = " + (defendingTank.staff - diceRolled));
		defendingTank.staff = defendingTank.staff - diceRolled;
		sleep(time = 6500);
		if(p1turn) {
			System.err.print(p2name);
		}
		else {
			System.err.print(p1name);
		}
		sleep(time = 1);
		System.out.print(", here is your defending tank's attributes after healing: ");
		sleep(time = 1);
		System.out.println(" ");
		System.out.println(" ");

	}

	private static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.err.flush();
		System.out.flush();
	}
}
