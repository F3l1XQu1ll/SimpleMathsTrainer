package code;

/**
 * a simple testing application for new XMLLogger
 * 
 * @author felixq
 *
 */
public class XMLLoggerTest {
    public static void main(String[] args) {
	/**
	 * on my device this calculation needs 2 minutes this was good to get some fake
	 * data for testing
	 */
	// XMLLogger logger = new XMLLogger();
	// logger.read();
	// logger.newTraining();
	// logger.newTry(true, 1, "2-7=-5");
	// // double test = Math.pow(2, 35);
	// // for (double i = 0; i < test; i++) {
	// // }
	// // System.out.println(":)");
	// logger.endTry();
	// logger.newTry(false, 3, "6/3=2");
	// logger.endTry();
	// logger.write();

	XMLLogEvaluter xmlLogEvaluter = new XMLLogEvaluter();
	// xmlLogEvaluter.getTrainings().forEach(e -> {
	// System.out.println(e.getAttribute("time"));
	// });
	xmlLogEvaluter.dumpTrainingsWithTimes();
	xmlLogEvaluter.dumpTimeBetweenTrainings();

	xmlLogEvaluter.dumpTimeForCalc(0);
	xmlLogEvaluter.dumpTimeForCalc(1);
	xmlLogEvaluter.dumpTimeForCalc(2);
	xmlLogEvaluter.dumpTimeForCalc(3);

	xmlLogEvaluter.dumpErrorRate(0);
	xmlLogEvaluter.dumpErrorRate(1);
	xmlLogEvaluter.dumpErrorRate(2);
	xmlLogEvaluter.dumpErrorRate(3);
	System.exit(0);
    }
}
