
import java.util.ArrayList;

public class DataFetch {

    // Constants
    /** The maximum number of cow images; Last number in the directory **/
    public static final int DATASAMPLE_COW_MAX_SIZE = 5;

    /** The first index of the cow images **/
    public static final int DATASAMPLE_COW_FIRST_INDEX = 1;

    /** The directory location for cow images **/
    public static final String DATASAMPLE_COW_DIRECTORY = "/Users/DanielLongo 1/Desktop/SuprevisedML/Data/Logistic_Regression_Data/cows/";

    /** The cow image file prefix **/
    public static final String DATASAMPLE_COW_NAME_PREFIX = "cow";

    /** The cow image file suffix **/
    public static final String DATASAMPLE_COW_NAME_SUFFIX = ".jpg";


    /** The maximum number of cow images; Last number in the directory **/
    public static final int DATASAMPLE_NOT_COW_MAX_SIZE = 5;

    /** The first index of the cow images **/
    public static final int DATASAMPLE_NOT_COW_FIRST_INDEX = 1;

    /** The directory location for cow images **/
    public static final String DATASAMPLE_NOT_COW_DIRECTORY = "/Users/DanielLongo 1/Desktop/SuprevisedML/Data/Logistic_Regression_Data/cows/";

    /** The cow image file prefix **/
    public static final String DATASAMPLE_NOT_COW_NAME_PREFIX = "cow";

    /** The cow image file suffix **/
    public static final String DATASAMPLE_NOT_COW_NAME_SUFFIX = ".jpg";


    /** The array of images **/
    public ArrayList<BetterArray> images = new ArrayList<>();

    /** The array of labels **/
    public ArrayList<Boolean> labels = new ArrayList<>();
    /**
     * Fetch data and label
     * @param numCows
     * @param numNonCow
     */
    public DataFetch(int numCows, int numNonCow){
        for(int i = 0; i < numCows; i ++){

            int randomIndex = (int)((Math.random()*(DATASAMPLE_COW_MAX_SIZE-DATASAMPLE_COW_FIRST_INDEX))+DATASAMPLE_COW_FIRST_INDEX);

            images.add(utils.loadPicture(DATASAMPLE_COW_DIRECTORY + DATASAMPLE_COW_NAME_PREFIX + randomIndex + DATASAMPLE_COW_NAME_SUFFIX));

            labels.add(Boolean.TRUE);


        }

        for (int i = 0; i < numNonCow; i++) {
            int randomIndex = (int)((Math.random()*(DATASAMPLE_NOT_COW_MAX_SIZE-DATASAMPLE_NOT_COW_FIRST_INDEX))+DATASAMPLE_NOT_COW_FIRST_INDEX);

            images.add(utils.loadPicture(DATASAMPLE_NOT_COW_DIRECTORY + DATASAMPLE_NOT_COW_NAME_PREFIX + randomIndex + DATASAMPLE_NOT_COW_NAME_SUFFIX));

            labels.add(Boolean.FALSE);
        }
    }
}
