package warehouse;

/*
 * Use this class to test the betterAddProduct method.
 */ 
public class BetterAddProduct {
    public static void main(String[] args) {
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);
        
        // Use this file to test betterAddProduct
        Warehouse mWarehouse = new Warehouse();
        int n = StdIn.readInt();
	// Use this file to test addProduct
        for(int i = 0; i < n;i++){
            int day = StdIn.readInt();
            int id = StdIn.readInt();
            String name = StdIn.readString();
            int stock = StdIn.readInt();
            int demand = StdIn.readInt();
            mWarehouse.betterAddProduct(id, name, stock, day, demand);
        }
        System.out.println(mWarehouse);
    }
}
