public class Candidate {

    private final String candidate;
    private Integer votes;
    private Integer score;
    private Integer lastVoter;


    public Candidate(String candidate) {
        this.candidate = candidate;
        this.votes = 0;
        this.score = 0;
    }

    public String getCandidate() {
        return candidate;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }


    public void incrementVotes()
    {
        this.votes += 1;
    }

    public void incrementScore(int score)
    {
        this.score += score;
    }

    public Integer getScore() {
        return score;
    }

    public Integer getLastVoter() {
        return lastVoter;
    }

    public void updateLastVoter(Integer lastVoter) {
        this.lastVoter = lastVoter;
    }
}
