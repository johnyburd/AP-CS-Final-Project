/*QuestTracker
 *Tracks the quest that continues through the dungeon
 *Quest is to gather 12 literal easter eggs and bring them back to the quest giver.
 */
 package src.logic.quest;
 
 import src.logic.inventory.*;
 import src.logic.armor.*;
 import src.logic.weapon.*;
 import src.logic.hero.*;
 
	public class QuestTracker
	{
		static int gotEggs = 0;
		
		
		private static boolean sideQuest = false;
		
		
		//Just prints out things for now rather than putting up a textbox
	
		
		public static void giveSidequest(Hero p)
		{
			System.out.println("Come here for a quest!");
			System.out.println("You are to collect a dozen eggs if you want to escape.");
			System.out.println("It is dangerous to go alone, take this.");
			p.changeEquippedWeapon(Weapon.weaponArray[1]);
		}
		
		//accessors
		public static int checkQuest()
		{
			return gotEggs;
		}
		
		
		
		//mutators
		public static void getEgg()
		{
			gotEggs++;
		}
		
		
		
		public static void completeSidequest()
		{
			if(gotEggs == 12)
			{
				System.out.println("Congratulations! You collected all the eggs and now can escape the dungeon!");
				sideQuest = true;
			}
			else
			{
				System.out.println("You have not collected all the eggs!");
				System.out.println("I cannot make my omele- I mean you cannot escape yet!");
			}
		}
		
		
		
		public static void openTheDoor()
		{
			if(sideQuest == true)
			{
				//Method for traveling through to final level
			}
			else
			{
				System.out.println("You have not completed the side quest yet");
				
			}
		}	
	}
