package warehouse;

/*
 * Use this class to test the deleteProduct method.
 */ 
public class DeleteProduct {
    public static void main(String[] args) {
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);
        

	// Use this file to test deleteProduct
    Warehouse mWarehouse = new Warehouse();
    int n = StdIn.readInt();
    for(int i = 0; i < n;i++){
        String toDo = StdIn.readString();
        if(toDo.equals("add")){
            int day = StdIn.readInt();
            int id = StdIn.readInt();
            String name = StdIn.readString();
            int stock = StdIn.readInt();
            int demand = StdIn.readInt();
            mWarehouse.addProduct(id, name, stock, day, demand);
        }
        else if(toDo.equals("delete")){
            int id = StdIn.readInt();
            mWarehouse.deleteProduct(id);
        }
    }
    System.out.println(mWarehouse);
    }
}
