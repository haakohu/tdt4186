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

	public CustomerQueue(int queueLength, Gui gui) {
    	this.queueLength = queueLength;
    	this.gui = gui;
	}

	public void add(Customer customer) {
		if(queue.size() >= queueLength){
			throw new IllegalStateException("The queue is full");
		}
		gui.fillLoungeChair(queue.size()-1, customer);
		queue.add(customer);
	}

	public Customer next() {
		if(queue.isEmpty()){
			throw new IllegalStateException("The queue is empty");
		}
		gui.emptyLoungeChair(queue.size()-1);
		return queue.remove();
	}

	public boolean hasCustomers(){
	    return queue.size() > 0;
    }
    public boolean isEmpty(){
	    return queue.isEmpty();
    }

	// Add more methods as needed
}
