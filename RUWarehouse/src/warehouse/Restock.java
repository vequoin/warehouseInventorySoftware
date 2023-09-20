package warehouse;

public class Restock {
    public static void main(String[] args) {
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);
       Warehouse  mWarehouse = new Warehouse();

	// Uset his file to test restock
    int n = StdIn.readInt();
    for(int i =0; i < n;i++){
        String toDo = StdIn.readString();
        if(toDo.equals("add")){
            int day = StdIn.readInt();
            int id = StdIn.readInt();
            String name = StdIn.readString();
            int stock = StdIn.readInt();
            int demand = StdIn.readInt();
            mWarehouse.addProduct(id, name, stock, day, demand);
            System.out.println("product add at sector: " + id%10);
        }
        else if(toDo.equals("restock")){
            int id = StdIn.readInt();
            int stock = StdIn.readInt();
            System.out.println("Looking for product at id " + id%10);
            mWarehouse.restockProduct(id, stock);
        }
    }

    System.out.println(mWarehouse);
        
    }
}
