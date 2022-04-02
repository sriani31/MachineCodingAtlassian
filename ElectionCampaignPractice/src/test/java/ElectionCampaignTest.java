import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ElectionCampaignTest {

    @Test
    void testGetWinner() {
        List<String> voter1 = List.of("A");
        List<String> voter2 = List.of("B");
        List<String> voter3 = List.of("A");


        ElectionCampaign electionCampaign = new ElectionCampaign(new CandidateComparator());
        Candidate winner = electionCampaign.getWinner(List.of(voter1,voter2,voter3));
        System.out.println("Candidate " + winner.getCandidate() + " Votes :" + winner.getVotes());

        Assertions.assertEquals("A" , winner.getCandidate());
        Assertions.assertEquals(2,winner.getVotes());
       // Assertions.assertEquals(List.of("A" ,"B") , electionCampaign.getRankedCandidates(List.of(voter1,voter2,voter3)));


    }
}