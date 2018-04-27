package thai.bcnf_decomposition;

public class RF_pair {
	Relation r;
	FdList fds;
	
	public boolean isBCNF() {
		return this.fds.BCNFViolations(this.r).isNull();
	}
	
	public RF_pair_decompTree BCNF_decomp() {
		RF_pair_decompTree result = new RF_pair_decompTree(this.r, this.fds);
		return result.BCNF_decomp();
	}
	
	public String toString() {
		return "(" + this.r.toString() + ": " + this.fds.toString() + ")\n";
	}
	
	public RF_pair (Relation r, FdList fds) {
		this.r = r;
		this.fds = fds;
	}
}
