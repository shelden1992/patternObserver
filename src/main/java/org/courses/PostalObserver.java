package org.courses;

import org.apache.log4j.Logger;

import java.util.StringJoiner;

public class PostalObserver implements Observer {
    private static final Logger LOG = Logger.getLogger(PostalObserver.class);
    private String name;

    public PostalObserver(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    private Subject subject;

    @Override
    public void update() {
        String update = subject.getUpdate(this);
        if (update != null) {
            LOG.info("Have new Magazine = " + update);
        } else {
            NullPointerException nullPointerException = new NullPointerException();
            LOG.error("Not have new Magazine", nullPointerException);
            throw nullPointerException;
        }
    }

    @Override
    public void setSubject(Subject subject) {
        LOG.info("Have new subscription to " + subject);
        this.subject = subject;

    }
}
