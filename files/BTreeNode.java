@SuppressWarnings("unchecked")
class BTreeNode<T extends Comparable<T>> {
	boolean leaf;
	int keyTally;
	Comparable<T> keys[];
	BTreeNode<T> references[];
	int m;
	static int level = 0;

	// Constructor for BTreeNode class
	public BTreeNode(int order, boolean leaf1)
	{
    		// Copy the given order and leaf property
		m = order;
    		leaf = leaf1;
 
    		// Allocate memory for maximum number of possible keys
    		// and child pointers
    		keys =  new Comparable[2*m-1];
    		references = new BTreeNode[2*m];
 
    		// Initialize the number of keys as 0
    		keyTally = 0;
	}

	// Function to print all nodes in a subtree rooted with this node
	public void print()
	{
		level++;
		if (this != null) {
			System.out.print("Level " + level + " ");
			this.printKeys();
			System.out.println();

			// If this node is not a leaf, then 
        		// print all the subtrees rooted with this node.
        		if (!this.leaf)
			{	
				for (int j = 0; j < 2*m; j++)
    				{
        				if (this.references[j] != null)
						this.references[j].print();
    				}
			}
		}
		level--;
	}

	// A utility function to print all the keys in this node
	private void printKeys()
	{
		System.out.print("[");
    		for (int i = 0; i < this.keyTally; i++)
    		{
        		System.out.print(" " + this.keys[i]);
    		}
 		System.out.print("]");
	}

	// A utility function to give a string representation of this node
	public String toString() {
		String out = "[";
		for (int i = 1; i <= (this.keyTally-1); i++)
			out += keys[i-1] + ",";
		out += keys[keyTally-1] + "] ";
		return out;
	}

	////// You may not change any code above this line //////

	////// Implement the functions below this line //////

	// Function to insert the given key in tree rooted with this node
	public BTreeNode<T> insert(T key)
	{
		if(this.keyTally == 2*this.m-1) {// if the root is full we need to create a new root;
			BTreeNode<T> new_root = new BTreeNode<T>(this.m , false);
			BTreeNode<T> root = this;
			new_root.references[0] = root;


			new_root.splitReference(0, new_root);
			int count = new_root.references[0].keyTally - 1;
			if (new_root.references[0].keys[count].compareTo(key) < 0)
				new_root.references[1].insertNonRoot(key);
			else new_root.references[0].insertNonRoot(key);

			return  new_root;




		}else{

			// Your code goes here
			insertNonRoot(key);
		  return this;

		}

	}

	public void insertNonRoot(T key){


			if (this.leaf) {// reached a leaf node;

				int i = this.keyTally;
				while (i > 0 && this.keys[i - 1].compareTo(key) > 0) {
					this.keys[i] = this.keys[--i];
				}

				this.keys[i] = key;
				this.keyTally++;


			} else { //  not a leaf node


				int i = 0;
				while (i < this.keyTally && this.keys[i].compareTo(key) < 0) {// looking for position of node that we want to reference to insert
					i++;
				}

				if (this.references[i].keyTally == (2 * this.m - 1)) {// if the child is full

					this.splitReference(i, this);

					int count = this.references[i].keyTally - 1;
					if (this.references[i].keys[count].compareTo(key) < 0)
						i++;

					this.references[i].insertNonRoot(key);


				} else this.references[i].insertNonRoot(key);


			}



	}

	public void splitReference(int index , BTreeNode<T> node){


		int i = this.keyTally;

		int mid = node.references[index].keyTally/2;


		while(i > index) {
			this.keys[i] = this.keys[i-1];
			i--;

		}
		int oldChildCount = this.references[index].keyTally;
		this.keys[i] = this.references[index].keys[mid];
		this.references[index].keyTally--;
		node.keyTally++;
		BTreeNode<T> temp = new BTreeNode<T>( this.m , true);
		i=0;

		while(mid < oldChildCount-1){
			temp.references[i] = this.references[index].references[++mid];
			temp.keys[i++] = this.references[index].keys[mid];
			this.references[index].references[mid] = null;
			this.references[index].keyTally--;
		}

		temp.references[i] = this.references[index].references[++mid];
		this.references[index].references[mid] = null;
		temp.keyTally = i;
		node.references[index+1] = temp;



		if(this.references[index].leaf){
			temp.leaf = true;
		}else temp.leaf = false;








	}





	// Function to search a key in a subtree rooted with this node
    	public BTreeNode<T> search(T key)
    	{  
		// Your code goes here
			int i = 0;
			for(i = 0 ; i < this.keyTally; i++){
				if(this.keys[i].compareTo(key)>=0)
					break;
			}
			if(i < this.keyTally && this.keys[i]==key)
				return this;
			if(this.leaf)
				return null;

			return this.references[i].search(key);


	}

}