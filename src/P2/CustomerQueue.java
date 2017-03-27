package P2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * This class implements a queue of customers as a circular buffer.
 */
public class CustomerQueue {
	/**
	 * Creates a new customer queue. Make sure to save these variables in the class.
	 * @param queueLength	The maximum length of the queue.
	 * @param gui			A reference to the GUI interface.
	 */
	private int queueLength;
	private Gui gui;
	private Queue<Customer> queue = new LinkedList<>();
	private int totalCount = 0;

	public CustomerQueue(int queueLength, Gui gui) {
    	this.queueLength = queueLength;
    	this.gui = gui;
	}

	public synchronized void add(Customer customer) {

		if(queue.size() >= queueLength){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
		gui.fillLoungeChair(totalCount % 18, customer);
		totalCount++;
		queue.add(customer);
		notifyAll();
        System.out.println("Notified of a new customer!");
    }

	public synchronized Customer next() {
		if(queue.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
		Customer customer  = queue.remove();
        gui.emptyLoungeChair(totalCount%18 -1);
		//notifyAll();
		return customer;
	}

	public boolean hasCustomers(){
	    return queue.size() > 0;
    }
    public boolean isFull(){
	    return queue.size() >= queueLength;
    }


	// Add more methods as needed
}
