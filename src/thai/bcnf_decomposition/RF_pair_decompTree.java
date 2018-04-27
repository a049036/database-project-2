package thai.bcnf_decomposition;

public class RF_pair_decompTree {
	RF_pair root;
	RF_pair_decompTree left;
	RF_pair_decompTree right;
	
	public RF_pair_decompTree BCNF_decomp() {
		if (this.root.isBCNF()) return this;
		else {
			Fd violation = this.root.fds.BCNFViolations(this.root.r).getFirst();
			Relation r1 = violation.getRHS();
			Relation r2 = r1.complementIn(this.root.r);
			r1 = r1.union(violation.getLHS());
			FdList fd1 = this.root.fds.projectOn(r1);
			FdList fd2 = this.root.fds.projectOn(r2);
			
			this.left = new RF_pair_decompTree(r1, fd1);
			this.right = new RF_pair_decompTree(r2, fd2);
			
			this.left.BCNF_decomp();
			this.right.BCNF_decomp();
			
			return this;
		}
	}
	
	public void printLeaves() {
		if (this.left == null && this.right == null) {
			System.out.println(this.root.toString());
		}
		else {
			if (this.left != null) this.left.printLeaves();
			if (this.right != null) this.right.printLeaves();
		}
	}
	
	public RF_pair_decompTree (Relation r, FdList fds) {
		this.root = new RF_pair (r, fds);
		this.left = null;
		this.right = null;
	}
}
