package thai.bcnf_decomposition;

public class FdList {
	private Fd f;
	private FdList next;
	public boolean isNull() {
		return (this.next == null);
	}
	
	private void normalize() {
		FdList tmp = this;
		while(!tmp.isNull()) {
			tmp = tmp.next;
			tmp.f.normalize();
		}
	}
	
	public FdList projectOn (Relation r) {
		FdList result = new FdList();
		FdList current = result;
		FdList tmp = this;
		while (!tmp.isNull()) {
			tmp = tmp.next;
			if (tmp.f.getLHS().subset(r)) {
				if (!tmp.f.getRHS().intersect(r).isEmpty()) {
					current.next = new FdList();
					current = current.next;
					current.f = new Fd (tmp.f.getLHS(),tmp.f.getRHS().intersect(r));
				}
			}
		}
		return result;
	}
	
	public String toString() {
		String result = "";
		FdList tmp = this;
		while (!tmp.isNull()) {
			tmp = tmp.next;
			result += tmp.f.toString() + ",";
		}
		if (result.length() != 0)
			result = result.substring(0, result.length() - 1);
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
	
	public FdList BCNFViolations (Relation r) {
		FdList result = new FdList();
		FdList tmp = this;
		while(!tmp.isNull()) {
			tmp = tmp.next;
			if (tmp.f.BCNFViolation(r)) {
				result.insert(tmp.f);
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
		FdList tmp2;
		Fd current;
		
		String [] fd = s.split(","); // Split the FDs into an array.
		
		// Add FDs into the FdList.
		for (int i=0;i<fd.length;i++) {
			if (!fd[i].matches("\\s*")) {
				boolean flag = true;
				tmp2 = this;
				current = new Fd(fd[i]);
				while (!tmp2.isNull()) {
					tmp2 = tmp2.next;
					if (current.LHS.subset(tmp2.f.LHS) || current.LHS.subset(tmp2.f.RHS)) {
						tmp2.f.RHS = tmp2.f.RHS.union(current.RHS);
					}
				}
				
				// cannot add to existing FDs. Create new one.
				if (flag) {
					tmp.next = new FdList();
					tmp = tmp.next;
					tmp.f = current;
				}
			}
		}
		// Normalize the FDs.
		this.normalize();
	}
	
	
	/*public FdList(String s) {
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
	}*/
}
