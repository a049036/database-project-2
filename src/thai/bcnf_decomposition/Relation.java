package thai.bcnf_decomposition;

public class Relation {
	boolean [] A;
	
	public Relation () {
		this.A = new boolean [26];
		for (int i=0;i<26;i++) {
			this.A[i] = false;
		}
	}
	
	public Relation (String s) {
		this.A = new boolean [26];
		for (int i=0;i<26;i++) {
			this.A[i] = false;
		}
		
		String [] r = s.split("\\s+");
		for (int i=0; i<r.length; i++) {
			if (r[i]=="") continue;
			for (int j=0; j<r[i].length();j++) {
				int index = (int) r[i].charAt(j) - (int) 'A';
				this.A[index] = true;
			}
		}
	}
	
	public String toString() {
		String s ="";
		for (int i=0; i<this.A.length; i++) {
			if (this.A[i] == true) s = s + " " + (char) (i + (int) 'A');
		}
		return s;
	}
	
	public boolean equals (Relation r2) {
		for (int i=0;i<26;i++) {
			if (this.A[i] != r2.A[i]) return false;
		}
		return true;
	}
	
	public boolean contains (char c) {
		return this.A[(int) c - (int) 'A'];
	}
	
	public boolean subset (Relation r2) {
		boolean flag = true;
		for (int i=0; i<this.A.length && flag; i++) {
			if (this.A[i]==true && r2.A[i]==false) flag = false;
		}
		return flag;
	}
	
	public Relation union (Relation r2) {
		String s = "";
		for (int i=0; i<this.A.length; i++) {
			if (this.A[i]==true || r2.A[i]==true) s = s + (char) (i + (int) 'A') + " ";
		}
		Relation r = new Relation(s);
		return r;
	}
	
	public Relation intersect (Relation r2) {
		String s = "";
		for (int i=0; i<this.A.length; i++) {
			if (this.A[i]==true && r2.A[i]==true) s = s + (char) (i + (int) 'A') + " ";
		}
		Relation result = new Relation(s);
		return result;
	}
	
	public Relation complementIn (Relation r2) {
		String s = "";
		for (int i=0; i<this.A.length; i++) {
			if (r2.A[i] == true && this.A[i] == false) s = s + (char) (i + (int) 'A') + " ";
		}
		Relation result = new Relation(s);
		return result;
	}
	
	public boolean isEmpty() {
		for (int i=0;i<this.A.length;i++) {
			if (A[i]==true) return false;
		}
		return true;
	}
	
}
