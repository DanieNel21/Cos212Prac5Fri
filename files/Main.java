import java.util.Random;

public class Main {

    public static void main(String[] args) 
    {
	BTree<Integer> t = new BTree<Integer>(2); // A B-Tree with order 4 (2*m)
   
	t.insert(20);
	t.insert(10);
	t.insert(30);
	t.insert(50);
	t.insert(40);
	t.insert(60);
	t.insert(90);
	t.insert(70);
	 t.insert(80);
	/*Random rand = new Random();
	for(int i = 0 ; i < 15; i++){
		int n = rand.nextInt(50);
		t.insert(n);
	}*/

	/*System.out.println("Search the constucted tree for 80: ");
	BTreeNode result = t.search(80);
	if (result != null)
		System.out.println("Found in node " + result);
	else
		System.out.println("Not found!");
	System.out.println("Search the constucted tree for 100: ");
    	result = t.search(100);
	if (result != null)
		System.out.println("Found in node " + result);
	else
		System.out.println("Not found!");*/
	System.out.println("Structure of the constucted tree is : ");
	t.print();

	/* Expected Output:
	Search the constucted tree for 80:
	Found in node [70,80,90]
	Search the constucted tree for 100:
	Not found!
	Structure of the constucted tree is :
	Level 1 [ 40]
	Level 2 [ 20]
	Level 3 [ 10]
	Level 3 [ 30]
	Level 2 [ 60]
	Level 3 [ 50]
	Level 3 [ 70 80 90]
	*/
    }


    
}