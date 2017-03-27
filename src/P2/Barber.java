package P2;

/**
 * This class implements the barber's part of the
 * Barbershop thread synchronization example.
 * One barber instance corresponds to one consumer in
 * the producer/consumer problem.
 */
public class Barber implements Runnable {
	/**
	 * Creates a new barber. Make sure to save these variables in the class.
	 * @param customerQueue		The customer customerQueue.
	 * @param gui		The GUI.
	 * @param pos		The position of this barber's chair
	 */

	private CustomerQueue customerQueue;
	private Gui gui;
	private int pos;
	private Thread thread;

	public Barber(CustomerQueue customerQueue, Gui gui, int pos) {
		this.customerQueue = customerQueue;
		this.gui = gui;
		this.pos = pos;
	}

	/**
	 * This is the code that will run when a new thread is
	 * created for this instance.
	 */
	@Override
 	public synchronized void run(){
            Customer customer = customerQueue.next();
            gui.fillBarberChair(pos, customer);
            gui.barberIsAwake(pos);
            gui.println("P2.Barber on position " + String.valueOf(pos) + " got a customer.");
            int sleepTime = Globals.barberWork + (int) (Math.random() * (Constants.MAX_BARBER_WORK - Constants.MIN_BARBER_WORK + 1));
            getSleep(sleepTime);
            gui.emptyBarberChair(pos);
            gui.println("P2.Barber #" + String.valueOf(pos) + " is waiting.");
            gui.barberIsAwake(pos);
	}

    private void getSleep(int sleepTime){
 	    try {
            thread.sleep(sleepTime);
        }catch (InterruptedException e){
 	        System.err.println("P2.Barber sleep interrupted");
        }
    }

    /**
	 * Starts the barber running as a separate thread.
	 */
	public void startThread() {
	    thread = new Thread(this);
	    thread.start();

	}

	/**
	 * Stops the barber thread.
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

