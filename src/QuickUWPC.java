
public class QuickUWPC implements IUnionFind{
	private int[] myID;
	private int myComponents;
	private int[] mySize;

	public QuickUWPC() {
		this(10);
	}
	
	public QuickUWPC(int n) {
		initialize(n);
	}
	
	
	public void initialize(int n) {
		// TODO Auto-generated method stub
		myComponents = n;
		myID = new int[n];
		mySize = new int[n];
		for(int i=0; i<n; i++) {
			myID[i]=i;
			mySize[i]=1;
		}
	}

	
	public int components() {
		// TODO Auto-generated method stub
		return myComponents;
	}

	public int find(int x) {
		if (x<0 || x>=myID.length) {
			throw new IllegalArgumentException("ILLEGAL ARGUMENT");
		}
		int root = x;
		while (root != myID[root])
			root = myID[root];
		while (x != root) {
			int temp = myID[x];
			myID[x] = root;
			x = temp;
		}
		return root;
	}

	public boolean connected(int p, int q) {
		// TODO Auto-generated method stub
		return find(p) == find(q);
	}


	public void union(int p, int q) {
		// TODO Auto-generated method stub
		int pRoot = find(p);
		int 	qRoot = find(q);
		
		if (pRoot == qRoot) return;
		
		if (pRoot<qRoot) {
			myID[pRoot] = qRoot;
			mySize[qRoot] += mySize[pRoot];
		}
		
		else {
			myID[qRoot] = pRoot;
			mySize[pRoot] += mySize[qRoot];
		}
	}

}
