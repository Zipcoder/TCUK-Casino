package transactions;

import org.junit.Assert;
import org.junit.Test;
import io.zipcoder.Player;

public class TransactionLogTest {

	@Test
	public void testUpdateTransactionAndPrintLog() {

		// :Given
		Player p = new Player("bob", 10000);
		p.setCurrentGame("blackjack");
		Transaction t = new Transaction(p, 10);

		TransactionLog transactionLog = new TransactionLog();

		String expected = "\n --- Transaction log for : bob --- \n" + "\n --- Games played: ---\n"
				+ "\nGame: blackjack played 1 times.\n" + "\n --- Log of Transactions --- \n"
				+ "\nTransaction [Player : bob; betAmount: 10.0; game: blackjack; amount remaining: 10000.0]";

		// :When
		transactionLog.updateTransaction(t);

		// :then
		Assert.assertEquals("testUpdateTransaction - toStrings matched", expected,
				transactionLog.printTransactionLog(2));

		// TEST 2 -- increment games played:

		// :Given & When (2)
		transactionLog.updateTransaction(new Transaction(p, -20));

		String expected2 = "\n --- Transaction log for : bob --- \n" + "\n --- Games played: ---\n"
				+ "\nGame: blackjack played 2 times.\n" + "\n --- Log of Transactions --- \n"
				+ "\nTransaction [Player : bob; betAmount: -20.0; game: blackjack; amount remaining: 10000.0]"
				+ "\nTransaction [Player : bob; betAmount: 10.0; game: blackjack; amount remaining: 10000.0]";

	
		// :Then (2)
		
		Assert.assertEquals("testUpdateTransaction - toStrings matched", expected2,
				transactionLog.printTransactionLog(2));
	
	}

}
