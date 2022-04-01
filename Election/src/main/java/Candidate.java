public class Candidate {

    private final String candidateName;
    private Integer votes;
    private Integer score;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Candidate(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getCandidateName() {
        return candidateName;
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

    public void incrementScore(Integer scoring)
    {
        this.score += scoring;
    }
}
