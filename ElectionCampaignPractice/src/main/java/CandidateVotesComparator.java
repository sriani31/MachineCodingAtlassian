import java.util.Comparator;

public class CandidateVotesComparator implements Comparator<Candidate> {

    @Override
    public int compare(Candidate o1, Candidate o2) {
//        if(o1.getScore() != o2.getScore())
//            return  o1.getScore() - o2.getScore();
//        return o2.getScore() - o2.getScore();

        if(o1.getScore() != o2.getScore())
            return o1.getScore() - o2.getScore();
        else
            return o2.getLastVoter() - o1.getLastVoter();
    }
}
