package thai.bcnf_decomposition;

public class Fd {
	Relation LHS;
	Relation RHS;
	
	public String toString() {
		return (this.LHS.toString() + "->" + this.RHS.toString());
	}
	
	public boolean BCNFviolation (Relation r) {
		return !r.equals(this.LHS.union(this.RHS));
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
	
	public Fd (String s) {
		//s = s.replaceAll("\\s+", "");
		String [] fd = s.split("->");
		this.LHS = new Relation(fd[0]);
		this.RHS = new Relation(fd[1]);
	}
}
