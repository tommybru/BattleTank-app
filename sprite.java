import java.util.Scanner;


public class sprite {
	int power;
	int armor;
	int resilience;
	int health;
	int staff;

	public	sprite()	{
		power = 10;
		resilience = 10;
		armor = 20;
		health = 300;
		staff = 30;
	}

	public sprite(int somePower, int someResilience, int someArmor,	int someHealth, int someStaff) {
		power = somePower;
		resilience = someResilience;
		armor = someArmor;
		health = someHealth;
		staff = someStaff;
	}

	public void printAllInfo() {
		System.out.println("Power: "+power);
		System.out.println("Resilience: "+resilience);
		System.out.println("Armor: "+armor);
		System.out.println("Health: "+health);
		System.out.println("Staff: "+staff);
	}

	private int rollOneDie(int range, int low)	{
		int result = (int)(Math.random()*range)+low;
		return result;
	}

	public int[] rollDice(int howMany, int numSides) {
		int[]	results;
		results = new	int[howMany];
		for(int i=0; i<howMany; i++) {
			results[i] = rollOneDie(numSides, 1);
		}
 		return results;
	}

	public boolean	 isDead() {
		if(health<=0) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean canAttack(sprite other) {
		if(health>other.health) {
			return true;
		}
		else {
			return false;
		}
	}
}
