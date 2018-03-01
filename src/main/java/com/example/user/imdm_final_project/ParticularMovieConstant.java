package com.example.user.imdm_final_project;

//ParticularMovieConstant class to store all the detailed information of a particular movie
public class ParticularMovieConstant
{

    public String posterURL;
    public String mName;
    public Float popularity;
    public String Description;
    public String ReleaseDate;
    public String Budget;
    public String Revenue;
    public String Status;
    public String AverageVote;
    public String VoteCount;
    public String Overview;

    public String getOverview() {
        return Overview;
    }

    public void setOverview(String overview) {
        Overview = overview;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public Float getPopularity() {
        return popularity;
    }

    public void setPopularity(Float popularity) {
        this.popularity = popularity;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        ReleaseDate = releaseDate;
    }

    public String getBudget() {
        return Budget;
    }

    public void setBudget(String budget) {
        Budget = budget;
    }

    public String getRevenue() {
        return Revenue;
    }

    public void setRevenue(String revenue) {
        Revenue = revenue;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getAverageVote() {
        return AverageVote;
    }

    public void setAverageVote(String averageVote) {
        AverageVote = averageVote;
    }

    public String getVoteCount() {
        return VoteCount;
    }

    public void setVoteCount(String voteCount) {
        VoteCount = voteCount;
    }
}