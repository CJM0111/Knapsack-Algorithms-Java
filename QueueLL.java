// Queue Class
// @author Chris McDonald
class QueueLL implements Queue
{
        private class node
        {
          public Object item;
      	  public node next;
        }
        
        node front, back;
        int count;
        
        public QueueLL()
        {
          front=back=null; count=0;
        }
        public int size() { return count; }
        public boolean isEmpty()
          { return count==0; }
        public boolean isFull()
          { return false; }
        public void makeEmpty()
          { front=back=null; count=0; }
        public Object getFront()
        {
          return isEmpty()? null : front.item;  
        }
        public Object dequeue()
        {
            if(isEmpty()) return null;
      	    Object saveit=getFront();
      	    count--;
      	    front=front.next;
      	    if(front==null) back=null;
      	    return saveit;
        }
        public void enqueue(Object x)
        {
            node newnode=new node();
      	    newnode.next=null;
      	    newnode.item=x;
      	    if(!isEmpty())
      	        back.next=newnode;
      	    else
      	        front=newnode;
      	    back=newnode;
      	    count++;
        }
}
