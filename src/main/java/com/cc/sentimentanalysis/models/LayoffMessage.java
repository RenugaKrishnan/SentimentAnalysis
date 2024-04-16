package com.cc.sentimentanalysis.models;

import com.opencsv.bean.CsvBindByName;

public class LayoffMessage {
    @CsvBindByName(column = "text")
    private String message;
    @CsvBindByName(column = "role")
    private String jobTitle;
    @CsvBindByName(column = "num_reactions")
    private String likes;
    @CsvBindByName(column = "num_comments")
    private String comments;
    @CsvBindByName(column = "num_reposts")
    private String reposts;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getReposts() {
        return reposts;
    }

    public void setReposts(String reposts) {
        this.reposts = reposts;
    }
}
