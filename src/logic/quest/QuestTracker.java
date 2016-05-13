/*QuestTracker
 *Tracks the quest that continues through the dungeon
 *Quest is to gather 12 literal easter eggs and bring them back to the quest giver.
 */
 
 
	public class QuestTracker
	{
		int gotEggs = 0;
		
		
		private boolean sideQuest = false;
		
		
		//Just prints out things for now rather than putting up a textbox
	
		
		public void giveSidequest()
		{
			System.out.println("Come here for a quest!");
			System.out.println("You are to collect a dozen eggs if you want to escape.");
			System.out.println("It is dangerous to go alone, take this.");
			//Equipment.switchWeapon(sword);
		}
		
		//accessors
		public int checkQuest()
		{
			return gotEggs;
		}
		
		
		
		//mutators
		public void getEgg()
		{
			gotEggs++;
		}
		
		
		
		public void completeSidequest()
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
		
		
		
		public void openTheDoor()
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
