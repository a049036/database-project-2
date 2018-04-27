package thai.bcnf_decomposition;

public class Fd {
	Relation LHS;
	Relation RHS;
	
	void normalize() {
		for (int i=0;i<this.LHS.A.length;i++) {
			if (this.LHS.A[i] == true) this.RHS.A[i] = false;
		}
	}
	
	public String toString() {
		return (this.LHS.toString() + "->" + this.RHS.toString());
	}
	
	public boolean BCNFViolation (Relation r) {
		return !r.subset(this.LHS.union(this.RHS)) && this.LHS.subset(r) && !this.RHS.intersect(r).isEmpty();
	}
	
	public Relation getLHS () {
		return this.LHS;
	}
	
	public Relation getRHS () {
		return this.RHS;
	}
	
	public Fd() {
		this.LHS = null;
		this.RHS = null;
	}
	
	public Fd (Relation l, Relation r) {
		this.LHS = l;
		this.RHS = r;
	}
	
	public Fd (String s) {
		//s = s.replaceAll("\\s+", "");
		String [] fd = s.split("->");
		this.LHS = new Relation(fd[0]);
		this.RHS = new Relation(fd[1]);
		this.normalize();
	}
}
