
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


    /** The arrayList of images **/
    public ArrayList<BetterArray> imagesAsArrayList = new ArrayList<BetterArray>();

    /** The flattened images in BetterArray Format **/
    public  BetterArray images;

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

            imagesAsArrayList.add(utils.loadPicture(DATASAMPLE_COW_DIRECTORY + DATASAMPLE_COW_NAME_PREFIX + randomIndex + DATASAMPLE_COW_NAME_SUFFIX));

            labelsAsClass.add(Boolean.TRUE);


        }

        for (int i = 0; i < numNonCow; i++) {
            int randomIndex = (int)((Math.random()*(DATASAMPLE_NOT_COW_MAX_SIZE-DATASAMPLE_NOT_COW_FIRST_INDEX))+DATASAMPLE_NOT_COW_FIRST_INDEX);

            imagesAsArrayList.add(utils.loadPicture(DATASAMPLE_NOT_COW_DIRECTORY + DATASAMPLE_NOT_COW_NAME_PREFIX + randomIndex + DATASAMPLE_NOT_COW_NAME_SUFFIX));

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

        float[][][] imageData = new float[imagesAsArrayList.size()][imagesAsArrayList.get(0).w*imagesAsArrayList.get(0).h*imagesAsArrayList.get(0).c][1];


            float tiledMImages[][][] = new float[imagesAsArrayList.size()][100*100*3][1];

            float[][] flattenedImages = new float[imagesAsArrayList.size()][100*100*3];
            for (int i = 0; i < imagesAsArrayList.size(); i++) {
                flattenedImages[i] = utils.flatten (imagesAsArrayList.get(i));
            }



            for (int i = 0; i < tiledMImages.length; i++) {
                for (int j = 0; j < tiledMImages[0].length; j++) {
                    for (int k = 0; k < tiledMImages[0][0].length; k++) {
                        tiledMImages[i][j][k] = flattenedImages[i][j];

                    }

                }

            }
        images= new BetterArray (tiledMImages);





    }
}
