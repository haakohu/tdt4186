
/**
 * This class implements the doorman's part of the
 * Barbershop thread synchronization example.
 * One doorman instance corresponds to one producer in
 * the producer/consumer problem.
 */
public class Doorman implements Runnable {
	/**
	 * Creates a new doorman. Make sure to save these variables in the class.
	 * @param customerQueue		The customer customerQueue.
	 * @param gui		A reference to the GUI interface.
	 */
	private Gui gui;
	private CustomerQueue customerQueue;
    private Thread thread;

    public Doorman(CustomerQueue customerQueue, Gui gui) {
        this.gui = gui;
        this.customerQueue = customerQueue;
    }

    /**
	 * This is the code that will run when a new thread is
	 * created for this instance.
	 */
	@Override
	public void run(){
		for(int i=0;i<500;i++){
		    synchronized (this) {
                if (!customerQueue.isFull()) {
                    Customer customer = new Customer();
                    customerQueue.add(customer);
                    gui.println("Doorman filled a new chair");
                } else {
                    gui.println("All chairs are full.");
                }
                takeSleep();
            }
        }
	}

    private void takeSleep() {
	    int sleepTime = Globals.doormanSleep + (int) (Math.random() * (Constants.MAX_DOORMAN_SLEEP - Constants.MIN_DOORMAN_SLEEP +1));
        try {
            thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    /**
	 * Starts the doorman running as a separate thread. Make
	 * sure to create the thread and start it.
	 */
	public void startThread() {
		// Incomplete
        thread = new Thread(this);
        thread.start();
	}

	/**
	 * Stops the doorman thread. Use Thread.join() for stopping
	 * a thread.
	 */
	public void stopThread() {
		// Incomplete
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

	// Add more methods as needed
}
