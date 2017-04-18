package com.medavox.catboat;

import java.io.InputStreamReader;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import rx.Observable;
import rx.Observer;
import rx.observables.StringObservable;

public class Catboat implements Observer<String> {
    //private Scanner scanner;
	protected Queue<Message> messageQueue = new ConcurrentLinkedQueue<Message>();

    private boolean threadsEnabled = false;

    ChatPartner brian = new ChatPartner("brian");

    public Catboat() {
        threadsEnabled = true;

        Observable<String> userInput =
                StringObservable.from(new InputStreamReader(System.in));
        Observable<String> lined = StringObservable.byLine(userInput);

        lined.subscribe(this);
    }
    public static void main(String[] args) {
        Catboat catboat = new Catboat();
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(String inputLine) {
         //System.out.println(s);

    }

    private Runnable messageQueueHandler = new Runnable() {
        @Override
        public void run() {
/*
            while(threadsEnabled) {//spins until there is something in the queue
                if (scanner.hasNext()) {
                    messageQueue.add(new Message(scanner.next(), brian));
                }
*/
/*
                if() {//wait before checking again, so as not to waste CPU cycles
                    try {
                        sleep(500);
                    }
                    catch(InterruptedException ie) {
                        System.err.println("Can't a thread catch a few ms sleep around here?!");
                        ie.printStackTrace();
                    }
                }
            */
  //          }
        }
    };
}
