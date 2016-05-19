/*QuestTracker
 *Tracks the quest that continues through the dungeon
 *Quest is to gather 12 literal easter eggs and bring them back to the quest giver.
 */
 package src.logic.quest;
 
 import src.logic.inventory.*;
 import src.logic.armor.*;
 import src.logic.weapon.*;
 import src.logic.hero.*;
 
 import src.hud.TextBox;
 
	public class QuestTracker
	{
		static int gotEggs = 0;
		
		
		private static boolean sideQuest = false;
		
		
		//Just prints out things for now rather than putting up a textbox
	
		
		public static void giveSidequest(Hero p, TextBox textbox)
		{
			textbox.addMessage("You are to collect a dozen eggs if you want to escape.");
			textbox.addMessage("It is dangerous to go alone, take this.");
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
		
		
		
		public static void completeSidequest(TextBox textbox)
		{
			if(gotEggs == 12)
			{
				textbox.addMessage("You collected all the eggs and now can escape the dungeon!");
				sideQuest = true;
			}
			else
			{
				textbox.addMessage("You have not collected all the eggs!");
			}
		}
		
		
		
		public static void openTheDoor(TextBox textbox)
		{
			if(sideQuest == true)
			{
				//Method for traveling through to final level
			}
			else
			{
				textbox.addMessage("You have not completed the quest yet");
				
			}
		}	
	}
