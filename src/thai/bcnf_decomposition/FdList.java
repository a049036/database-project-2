package thai.bcnf_decomposition;

public class FdList {
	private Fd f;
	private FdList next;
	private boolean isNull() {
		return (this.next == null);
	}
	
	public String toString() {
		String result = "";
		FdList tmp = this;
		while (!tmp.isNull()) {
			tmp = tmp.next;
			result += tmp.f.toString() + "\n";
		}
		return result;
	}
	
	public void insert (Fd f) {
		FdList tmp = this.next;
		this.next = new FdList();
		this.next.f = f;
		this.next.next = tmp;
	}
	
	public Fd getFirst() {
		return this.next.f;
	}
	
	public Fd getNext() {
		return this.next.next.f;
	}
	
	public Relation closure (Relation r) {
		Relation result = new Relation();
		FdList tmp = this;
		while (!tmp.next.isNull()) {
			tmp = tmp.next;
			if (tmp.f.LHS.subset(r)) {
				result = result.union(tmp.f.RHS);
			}
		}
		return result;
	}
	
	public FdList() {
		this.f = null;
		this.next = null;
	}
	
	public FdList(String s) {
		//this.f = null;
		//this.next = null;
		FdList tmp = this;
		
		String [] fd = s.split(","); // Split the FDs into an array.
		
		// Add FDs into the FdList.
		for (int i=0;i<fd.length;i++) {
			if (!fd[i].matches("\\s*")) {
				tmp.next = new FdList();
				tmp = tmp.next;
				tmp.f = new Fd(fd[i]);
				//tmp = tmp.next;
			}
		}
	}
}
