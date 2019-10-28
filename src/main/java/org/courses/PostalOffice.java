package org.courses;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PostalOffice implements Subject {
    private static final Logger LOG = Logger.getLogger(PostalOffice.class);
    private static PostalOffice INSTANCE;
    private final Object MUTEX = new Object();
    private List<Observer> list;
    private boolean haveUpdate;
    private String nameMagazine;

    private PostalOffice() {
        list = new ArrayList<>();
    }

    public static synchronized PostalOffice getPostalOffice() {
        if (INSTANCE == null) {
            INSTANCE = new PostalOffice();
        }
        return INSTANCE;
    }

    public void register(Observer... obj) {
        if (obj == null) {
            NullPointerException nullPointerException = new NullPointerException();
            LOG.error("Observer cannot be null ", nullPointerException);
            throw nullPointerException;
        }
        synchronized (MUTEX) {
            LOG.info("Register observer " + Arrays.toString(obj));
            list.addAll(Arrays.asList(obj));
        }
    }

    public void unregister(Observer obj) {
        if (!list.contains(obj)) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
            LOG.error("Not contains observer " + obj, illegalArgumentException);
            throw illegalArgumentException;
        }
        synchronized (MUTEX) {
            LOG.info("Unregister observer " + obj);
            list.remove(obj);
        }

    }

    public void notificationObserver() {
        List<Observer> localList;
        synchronized (MUTEX) {
            if (!haveUpdate) {
                return;
            }

            localList = new ArrayList<>(this.list);
            haveUpdate = false;
        }
        localList.forEach(Observer::update);

    }

    public String getUpdate(Observer obj) {

        return this.nameMagazine;
    }

    public void postNewMagazine(String nameMagazine) {
        LOG.info("Created new Magazine =" + nameMagazine);
        this.nameMagazine = nameMagazine;
        haveUpdate = true;
        notificationObserver();

    }
}
