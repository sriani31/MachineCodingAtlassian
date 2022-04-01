import java.util.Comparator;

public class CandidateComparator implements Comparator<Candidate> {

    @Override
    public int compare(Candidate c1, Candidate c2) {
        if(c1.getScore() != c2.getScore())
                    return  c1.getScore() - c2.getScore();
        return c2.getScore() - c1.getScore();

    }
}
