import java.util.*;
import java.util.stream.Collectors;

public class ElectionCampaign {

    private final Map<String,Candidate> candidates;
    private final List<Candidate> rankedCandidates;
    private final Comparator<Candidate> candidateComparator;

    public ElectionCampaign(Comparator candidateComparator) {
        this.candidates = new HashMap<>();
        this.rankedCandidates = new ArrayList<>();
        this.candidateComparator = candidateComparator;
    }

    public Candidate getWinner( List<List<String>> votes)
    {
        //Candidate candidate = new Candidate("")
        if(rankedCandidates.size() == 0)
        {
            calculateRanking(votes);
        }

        return rankedCandidates.stream().limit(1).findFirst().orElseThrow(() -> new RuntimeException("No Votes Cast"));
    }

    public List<Candidate> getRankedCandidates(List<List<String>> votes)
    {
        if(rankedCandidates.size() == 0)
        {
            calculateRanking(votes);
        }
        return rankedCandidates;
    }
    public void calculateRanking(List<List<String>> votes)
    {

        for(int i = 0;i< votes.size();i++)
        {
           for(int candidatePref = 0; candidatePref < votes.get(i).size();candidatePref++)
           {
               String candidateName = votes.get(i).get(candidatePref);

               Candidate candidate = candidates.getOrDefault(candidateName,new Candidate(candidateName));
               candidate.incrementVotes();

               candidates.put(candidateName,candidate);

           }

        }
        rankedCandidates.addAll(candidates.values().stream().sorted(candidateComparator.reversed()).collect(Collectors.toList()));
    }

}
