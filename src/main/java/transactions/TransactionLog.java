package transactions;

import java.util.ArrayList;
import java.util.HashMap;

public class TransactionLog {

	private ArrayList<Transaction> log;
	private HashMap<String, Integer> gamesPlayed;

	/**
	 * 
	 */
	public TransactionLog() {
		this.log = new ArrayList<Transaction>();
		this.gamesPlayed = new HashMap<String, Integer>();
	}

	public void updateTransaction(Transaction t) {

		log.add(t);

		if (gamesPlayed.containsKey(t.getGame())) {
			gamesPlayed.replace(t.getGame(), gamesPlayed.get(t.getGame()) + 1);
		} else {
			gamesPlayed.put(t.getGame(), 1);
		}

	}

	/**
	 * @return the log
	 */
	public ArrayList<Transaction> getLog() {
		return log;
	}

	/**
	 * @return the gamesPlayed
	 */
	public HashMap<String, Integer> getGamesPlayed() {
		return gamesPlayed;
	}

	/**
	 * return the last n transactions as a string
	 * 
	 * @param numberOfRecords
	 * @return
	 */
	public String printTransactionLog(int numberOfRecords) {
		
		StringBuilder returnBuilder = new StringBuilder("\n --- Transaction log for : " + log.get(0).getPlayer().getName() + " --- \n");
		
		returnBuilder.append("\n --- Games played: ---\n");

		for (String i : gamesPlayed.keySet()) {
			returnBuilder.append("\nGame: " + i + " played " + gamesPlayed.get(i) + " times.");
		}
		
		returnBuilder.append("\n\n --- Log of Transactions --- \n");

		for (int i = log.size() - 1; i >= log.size() - numberOfRecords; i--) {
			if (i == -1)
				break;
			returnBuilder.append("\n" + log.get(i).toString());
		}

		return returnBuilder.toString();
	}

	public void printTransactionLogToFile(String fileName) {

		String toStore = printTransactionLog(log.size());
		
		//TODO log to a file

	}

}
