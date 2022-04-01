import java.util.*;
import java.util.stream.Collectors;

public class ElectionCampaign {
    private static final Integer MAX_CANDIDATES = 3;

    private final Map<String, Candidate> candidates;
    private final Comparator<Candidate> candidateComparator;
    private final List<Candidate> rankedCandidates;

    public ElectionCampaign(Comparator<Candidate> candidateComparator) {
        this.candidates = new HashMap<>();
        this.candidateComparator = candidateComparator;
        this.rankedCandidates = new ArrayList<>();
    }

    public Candidate getWinner(List<List<String>> votes) {
        if (rankedCandidates.size() == 0) {
            calculateRanking(votes);
        }
        return rankedCandidates.stream().limit(1).findFirst().orElseThrow(() -> new RuntimeException("No Votes Cast"));
    }

    public List<Candidate> findRanking(List<List<String>> votes) {
        if (rankedCandidates.size() == 0) {
            calculateRanking(votes);
        }
        return rankedCandidates;
    }

    private void calculateRanking(List<List<String>> votes) {
        for (int voter = 0; voter < votes.size(); voter++) {
            for (int candidatePosition = 0; candidatePosition < votes.get(voter).size(); candidatePosition++) {
                String candidateName = votes.get(voter).get(candidatePosition);
                Candidate thisCandidate = candidates.getOrDefault(candidateName, new Candidate(candidateName));
                thisCandidate.incrementScore(MAX_CANDIDATES - candidatePosition);
                //1 thing - position of candidate
                //thisCandidate.updateVoter(voter);
                candidates.put(candidateName, thisCandidate);
            }
        }
        rankedCandidates.addAll(candidates.values().stream().sorted(candidateComparator.reversed()).collect(Collectors.toList()));
    }
}