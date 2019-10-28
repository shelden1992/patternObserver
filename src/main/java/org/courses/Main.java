package org.courses;

public class Main {
    public static void main(String[] args) {
        PostalOffice postalOffice = PostalOffice.getPostalOffice();
        PostalObserver postalObserver1 = new PostalObserver("Vasia");
        PostalObserver postalObserver2 = new PostalObserver("Petia");
        postalObserver1.setSubject(postalOffice);
        postalObserver2.setSubject(postalOffice);
        postalOffice.register(postalObserver1);
        postalOffice.register(postalObserver2);
        for (int i = 0; i < 10; i++) {
            try {

                Thread.sleep(10000);
                postalOffice.postNewMagazine("NewMag");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }


        postalOffice.register();
    }
}
