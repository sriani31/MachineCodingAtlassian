import java.util.Comparator;

public class CandidateComparator implements Comparator<Candidate> {

    @Override
    public int compare(Candidate o1, Candidate o2) {
        if(o1.getVotes() != o2.getVotes())
            return  o1.getVotes() - o2.getVotes();
        return o2.getVotes() - o2.getVotes();
    }
}
