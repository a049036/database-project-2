package thai.bcnf_decomposition;

public class RelList {
	private Relation r;
	private RelList next;
	private boolean isNull() {
		return (this.next == null);
	}
	
	public RelList() {
		this.r = null;
		this.next = null;
	}
	
	public String toString() {
		String result = "";
		RelList tmp = this;
		while (!tmp.next.isNull()) {
			tmp = tmp.next;
			result = tmp.r.toString() + ", ";
		}
		return result;
	}
	
	public void insert (Relation r) {
		RelList tmp = this.next;
		this.next = new RelList();
		this.next.r = r;
		this.next.next = tmp;
	}
	
	public Relation getFirst() {
		return this.next.r;
	}
	
	public Relation getNext() {
		return this.next.next.r;
	}
}
