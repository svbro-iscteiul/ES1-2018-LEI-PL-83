package pt.iscte.esi.projeto.utils;

/**
 * XMLFildeEditor class, used only for testing
 *
 */
public class XMLTester {

	
	/**
	 * Main
	 * @param args input
	 */
	public static void main(String[] args) {
		XMLFileEditor x = new XMLFileEditor();
		//x.AddAcountsForTwitter("as111111", "assdfsdfd", "asdasdadasdaa", "adssdfsdsdfsfsd");
		//x.AddAcountsForEmail("happyc0d3rtwo@gmail.com", "happy.two");
		System.out.println(x.getEmailTokens());
	}

}
