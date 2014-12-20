package sports_simulation;

	// The Math library is needed for its random() function
	import java.lang.Math;

	/**
	 * This class simulates a volleyball or racquetball game
	 * between players/teams A and B, based on the probability
	 * that A will win a volley.  1000 games are simulated,
	 * with the probability of A winning a volley being 
	 * 0.40, 0.41, ..., 0.59, 0.60 .
	 */
	public class Game3Recur {
	    // Edit these lines to indicate the game to be simulated
	    // and the number of games to simulate for each server probability
	    public static String game = "racquetball"; // "racquetball" or "volleyball"
	    public static int numberOfGames = 1000;
	    public static boolean winByTwo;

	    // use the following field to enable tracing of results of each game
	    public static boolean printGame = true;
	    public static boolean printPoint = true;

	    /**
	     * Format a probability (a number between 0.0 and 1.0)
	     * as a 2-character integer percentage, followed by a
	     * "%" character.
	     */
	    public static String formatPercent (double value)
	    {
		String str = "" + Math.round(value * 100.0);
		while (str.length() < 3)
		    str = " " + str;
		return str + "%";
	    }
		
	    /**
	     * Play one game of racquetball or volleyball to conclusion
	     * @parms  server and receiver indicate the team "A" or "B"
	     *         probWinVolley specifies the likelihood the server wins a volley
	     *         serverScore, recScore contain current score of
	     *             server and receiver
	     * @returns winner of game: either "A" or "B"
	     */
	    public static String playUntilWin (String server, String receiver,
					       double probWinVolley, 
					       int serverScore, int recScore) 
	     {  // serve
		if (Math.random() < probWinVolley)
		    { // score point
			serverScore++;
			if (printPoint)
			{
				System.out.println ("server won volley" + " current score~ :"+ serverScore + " - "+ recScore);
			}
		      // if win, return winner
			if ((serverScore >= 15)
			    && ((! winByTwo)
				 || (serverScore >= recScore + 2)))
			    { if (printGame)
				    System.out.println (server + " - Scores:  " 
							+ serverScore 
							+ " / " + recScore);
			      return server;
			    }
			
		      // if not win, serve again
			{return playUntilWin (server, receiver, probWinVolley, 
					      serverScore, recScore);
			}

		    }
		else
		    {
			if (printPoint)
			{
				System.out.println ("receiver won volley" + " current score~ :"+ serverScore + " - "+ recScore);
			}
			
			// other side wins; other player serves 
			return playUntilWin (receiver, server, 1.0-probWinVolley,
					     recScore, serverScore);
		    }
	    }

	    /**
	     * Run simulation of 1000 games for probability of "A" winning
	     *   a volley covering the range 0.40, 0.41, ..., 0.59, 0.60.
	     * For each probability of "A" winning,
	     *   simulate games with Player/Team A always serving first
	     *   print one line with the percentage of volleys won by A and B 
	     *     and percentage of games won by A and B
	     */
	    public static void main (String [] args)
	    {   // determine if server must win by 2 (e.g., in volleyball)
	        winByTwo = game.equals("volleyball");

		// print headings
		System.out.println ("\nSimulation of " + game
				    + " based on " + numberOfGames + " games");
		System.out.println ("Must win by 2:  " + winByTwo);
		System.out.println ();
		System.out.println ("    Probabilities         Percentage");
		System.out.println (" for winning volley        of Wins");
		System.out.println ("     A       B            A        B");
	        System.out.println ();
		
		// Simulate games for 40% to 60% probabilities for A
		for (int prob40To60 = 40; prob40To60 <= 60; prob40To60++)
		    {
		      double probWinVolley = prob40To60 / 100.0;
	 
		      // Simulate games for a given probability
		      int AWins = 0;  // at first neither A nor B has won any games
		      int BWins = 0;
		      for (int i = 0; i < numberOfGames; i++)
			  { // tally winner of game
			    if (playUntilWin ("A", "B", probWinVolley, 0, 0).equals("A"))
				AWins++;
			    else
				BWins++;
			  }
		      System.out.println ("   "
				  + formatPercent(probWinVolley)   + "    " 
				  + formatPercent(1-probWinVolley) + "         "
				  + formatPercent(((double) AWins) / numberOfGames)
				  + "     "
				  + formatPercent(((double) BWins) / numberOfGames));
		    }
		System.out.println ("\nEnd of Simulation\n");
	    }
	}

	

