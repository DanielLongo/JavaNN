
import java.util.ArrayList;

public class DataFetch {

    // Constants
    /** The maximum number of cow images; Last number in the directory **/
    public static final int DATASAMPLE_COW_MAX_SIZE = 499;

    /** The first index of the cow images **/
    public static final int DATASAMPLE_COW_FIRST_INDEX = 0;

    /** The directory location for cow images **/
    public static final String DATASAMPLE_COW_DIRECTORY = "/Users/DanielLongo 1/Desktop/SuprevisedML/Data/Logistic_Regression_Data/cows/";

    /** The cow image file prefix **/
    public static final String DATASAMPLE_COW_NAME_PREFIX = "cow";

    /** The cow image file suffix **/
    public static final String DATASAMPLE_COW_NAME_SUFFIX = ".jpg";


    /** The maximum number of cow images; Last number in the directory **/
    public static final int DATASAMPLE_NOT_COW_MAX_SIZE = 499;

    /** The first index of the cow images **/
    public static final int DATASAMPLE_NOT_COW_FIRST_INDEX = 0;

    /** The directory location for cow images **/
    public static final String DATASAMPLE_NOT_COW_DIRECTORY = "/Users/DanielLongo 1/Desktop/SuprevisedML/Data/Logistic_Regression_Data/notcows/";

    /** The cow image file prefix **/
    public static final String DATASAMPLE_NOT_COW_NAME_PREFIX = "notcow";

    /** The cow image file suffix **/
    public static final String DATASAMPLE_NOT_COW_NAME_SUFFIX = ".jpg";


    /** The array of images **/
    public ArrayList<BetterArray> images = new ArrayList<BetterArray>();

    /** The arrayList of labels **/
    public ArrayList<Boolean> labelsAsClass = new ArrayList<Boolean>();

    /** The array of labels **/
    public boolean[] labels;
    /**
     * Fetch data and label
     * @param numCows The number of cows to train
     * @param numNonCow The number of non-cows to train
     */
    public DataFetch(int numCows, int numNonCow){
        for(int i = 0; i < numCows; i ++){


            int randomIndex = (int)((Math.random()*(DATASAMPLE_COW_MAX_SIZE-DATASAMPLE_COW_FIRST_INDEX))+DATASAMPLE_COW_FIRST_INDEX);

            images.add(utils.loadPicture(DATASAMPLE_COW_DIRECTORY + DATASAMPLE_COW_NAME_PREFIX + randomIndex + DATASAMPLE_COW_NAME_SUFFIX));

            labelsAsClass.add(Boolean.TRUE);


        }

        for (int i = 0; i < numNonCow; i++) {
            int randomIndex = (int)((Math.random()*(DATASAMPLE_NOT_COW_MAX_SIZE-DATASAMPLE_NOT_COW_FIRST_INDEX))+DATASAMPLE_NOT_COW_FIRST_INDEX);

            images.add(utils.loadPicture(DATASAMPLE_NOT_COW_DIRECTORY + DATASAMPLE_NOT_COW_NAME_PREFIX + randomIndex + DATASAMPLE_NOT_COW_NAME_SUFFIX));

            labelsAsClass.add(Boolean.FALSE);
        }

        labels = new boolean[labelsAsClass.size ()];
        for (int i = 0; i < labels.length; i++) {
            if(labelsAsClass.get (i) == Boolean.TRUE){
                labels[i]=true;
            }
            else{
                labels[i]=false;
            }

        }
    }
}
