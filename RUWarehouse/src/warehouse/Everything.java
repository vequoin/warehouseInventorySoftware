package warehouse;

/*
 * Use this class to put it all together.
 */ 
public class Everything {
    public static void main(String[] args) {
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);

	// Use this file to test all methods
    Warehouse mWarehouse = new Warehouse();
    int n = StdIn.readInt();
    for(int i = 0; i < n;i++){
        String toDo = StdIn.readString();
        if(toDo.equals("add")){
            System.out.println("Current line is " + i + " product added");
            int day = StdIn.readInt();
            int id = StdIn.readInt();
            String name = StdIn.readString();
            int stock = StdIn.readInt();
            int demand = StdIn.readInt();
            mWarehouse.addProduct(id, name, stock, day, demand);
            System.out.println(mWarehouse);
        }
        else if(toDo.equals("purchase")){
            System.out.println("Current Line is " + i + " product purchased");
            int day = StdIn.readInt();
            int id = StdIn.readInt();
            int purchased = StdIn.readInt();
            mWarehouse.purchaseProduct(id, day, purchased);
            System.out.println(mWarehouse);
        }
        else if(toDo.equals("restock")){
            System.out.println("Current line is " + i + " product restocked");
            int id = StdIn.readInt();
            int stock = StdIn.readInt();
            mWarehouse.restockProduct(id, stock);
            System.out.println(mWarehouse);
        }
        else if(toDo.equals("delete")){
            System.out.println("Current line is " + i);
            int id = StdIn.readInt();
            mWarehouse.deleteProduct(id);
            System.out.println(mWarehouse);
        }
    }
    System.out.println(mWarehouse);
    }
}
