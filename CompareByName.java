package prj5;

import java.util.Comparator;

// -------------------------------------------------------------------------
/**
 * sorts influencers lexicographically.
 * 
 * @author jdevi
 * @version Nov 16, 2023
 */
public class CompareByName implements Comparator<Influencer> {
    // ~Public Methods ........................................................
    /**
     * Orders influencers lexicographically.
     * 
     * @param inf1
     *            Influencer object compared with inf2
     * @param inf2
     *            Influencer object compared with inf1
     * @return positive integer
     */
    @Override
    public int compare(Influencer inf1, Influencer inf2) {
        if (inf1.getChannelName().compareToIgnoreCase(inf2
            .getChannelName()) < 0) {
            return -1;
        }
        else if (inf1.getChannelName().compareToIgnoreCase(inf2
            .getChannelName()) > 0) {
            return 1;
        }
        else {
            return 0;
        }
        // return
        // inf1.getChannelName().compareToIgnoreCase(inf2.getChannelName());
    }
}
