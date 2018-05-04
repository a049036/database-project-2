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
		this.rmDuplicate();
	}
	
	private void rmDuplicate () {
		FdList p1, p2;
		p1 = this;
		while(!p1.isNull()) {
			p2 = p1.next;
			while (!p2.isNull()) {
				p2 = p2.next;
				if (p1.next.f.equals(p2.f)) {
					p1.next = p1.next.next;
				}
			}
			p1 = p1.next;
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
		this.f = null;
		this.next = null;
		FdList tmp = this;
		FdList tmp2;
		Fd current;
		
		String [] fd = s.split(","); // Split the FDs into an array.
		
		// Add FDs into the FdList.
		for (int i=0;i<fd.length;i++) {
			if (!fd[i].matches("\\s*")) {
				boolean flag = true;
				current = new Fd(fd[i]);
				while (flag) { // Try to merge new FD into existing ones.
					tmp2 = this;
					flag = false;
					while (!tmp2.isNull()) {
						tmp2 = tmp2.next;
						if (current.LHS.subset(tmp2.f.LHS) || current.LHS.subset(tmp2.f.RHS)) {
							Relation tmp3 = tmp2.f.RHS.union(current.RHS);
							if (!tmp2.f.RHS.equals(tmp3) && tmp2.f.RHS.subset(tmp3)) {
								tmp2.f.RHS = tmp2.f.RHS.union(current.RHS);
								flag = true;
							}
						}
						if (tmp2.f.LHS.subset(current.LHS) || tmp2.f.LHS.subset(current.RHS)) {
							Relation tmp3 = current.RHS.union(tmp2.f.RHS);
							if (!current.RHS.equals(tmp3) && current.RHS.subset(tmp3)) {
								current.RHS = tmp3;
								flag = true;
							}
						}
					}
				}
				
				// cannot add to existing FDs. Create new one.
					tmp.next = new FdList();
					tmp = tmp.next;
					tmp.f = current;
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
