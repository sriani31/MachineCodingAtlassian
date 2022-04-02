import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ElectionCampaignTest {

    @Test
    void testGetWinner() {
        List<String> voter1 = List.of("C","B","A");
        List<String> voter2 = List.of("B","C");
        List<String> voter3 = List.of("B","C");

        ElectionCampaign electionCampaign = new ElectionCampaign(new CandidateVotesComparator());
        Candidate winner = electionCampaign.getWinner(List.of(voter1,voter2,voter3));
        System.out.println("Candidate " + winner.getCandidate() + " Votes :" + winner.getScore());

        Assertions.assertEquals("B" , winner.getCandidate());
        Assertions.assertEquals(8,winner.getScore());
       // Assertions.assertEquals(List.of("A" ,"B") , electionCampaign.getRankedCandidates(List.of(voter1,voter2,voter3)));

        List<Candidate> candidatesList = electionCampaign.getRankedCandidates(List.of(voter1,voter2,voter3));

        Assertions.assertEquals("B", candidatesList.get(0).getCandidate());
        Assertions.assertEquals("C", candidatesList.get(1).getCandidate());
        Assertions.assertEquals("A", candidatesList.get(2).getCandidate());
    }

    @Test
    void testTieBreaker()
    {
        List<String> voter1 = List.of("A");
        List<String> voter2 = List.of("B", "C");
        List<String> voter3 = List.of("B", "C");
        List<String> voter4 = List.of("A");

        ElectionCampaign electionCampaign = new ElectionCampaign(new CandidateVotesComparator());
        Candidate winner = electionCampaign.getWinner(List.of(voter1,voter2,voter3,voter4));

        Assertions.assertEquals("B" ,winner.getCandidate());

        List<Candidate> candidateList = electionCampaign.getRankedCandidates(List.of(voter1,voter2,voter3,voter4));
        for (Candidate c: candidateList ) {
            System.out.println(c.getCandidate() + " : " + c.getScore() + " : " + c.getLastVoter());
        }

    }
}