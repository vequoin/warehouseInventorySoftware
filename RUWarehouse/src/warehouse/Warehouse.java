package warehouse;

/*
 *
 * This class implements a warehouse on a Hash Table like structure, 
 * where each entry of the table stores a priority queue. 
 * Due to your limited space, you are unable to simply rehash to get more space. 
 * However, you can use your priority queue structure to delete less popular items 
 * and keep the space constant.
 * 
 * @author Ishaan Ivaturi
 */ 
public class Warehouse {
    private Sector[] sectors;
    
    // Initializes every sector to an empty sector
    public Warehouse() {
        sectors = new Sector[10];

        for (int i = 0; i < 10; i++) {
            sectors[i] = new Sector();
        }
    }
    
    /**
     * Provided method, code the parts to add their behavior
     * @param id The id of the item to add
     * @param name The name of the item to add
     * @param stock The stock of the item to add
     * @param day The day of the item to add
     * @param demand Initial demand of the item to add
     */
    public void addProduct(int id, String name, int stock, int day, int demand) {
        evictIfNeeded(id);
        addToEnd(id, name, stock, day, demand);
        fixHeap(id);
    }

    /**
     * Add a new product to the end of the correct sector
     * Requires proper use of the .add() method in the Sector class
     * @param id The id of the item to add
     * @param name The name of the item to add
     * @param stock The stock of the item to add
     * @param day The day of the item to add
     * @param demand Initial demand of the item to add
     */
    private void addToEnd(int id, String name, int stock, int day, int demand) {
        // IMPLEMENT THIS METHOD
        int idToEnter = id%10;
        Product myProduct = new Product(id, name, stock, day, demand);
        sectors[idToEnter].add(myProduct);

    }

    /**
     * Fix the heap structure of the sector, assuming the item was already added
     * Requires proper use of the .swim() and .getSize() methods in the Sector class
     * @param id The id of the item which was added
     */
    private void fixHeap(int id) {
        // IMPLEMENT THIS METHOD
        int sectorNum = id%10;
        int index = sectors[sectorNum].getSize();
        sectors[sectorNum].swim(index);
        
    }

    /**
     * Delete the least popular item in the correct sector, only if its size is 5 while maintaining heap
     * Requires proper use of the .swap(), .deleteLast(), and .sink() methods in the Sector class
     * @param id The id of the item which is about to be added
     */
    private void evictIfNeeded(int id) {
       // IMPLEMENT THIS METHOD
       int sectorNum = id%10;
       int sectorSize = sectors[sectorNum].getSize();
       if(sectorSize == 5){
        sectors[sectorNum].swap(1, sectorSize);
        sectors[sectorNum].deleteLast();
        sectors[sectorNum].sink(1);
       }

    }

    /**
     * Update the stock of some item by some amount
     * Requires proper use of the .getSize() and .get() methods in the Sector class
     * Requires proper use of the .updateStock() method in the Product class
     * @param id The id of the item to restock
     * @param amount The amount by which to update the stock
     */
    public void restockProduct(int id, int amount) {
        // IMPLEMENT THIS METHOD
        int sectorNum = id%10;
        int sectorSize = sectors[sectorNum].getSize();
        int i = 1;
        while(i <= sectorSize){
            if(sectors[sectorNum].get(i).getId() == id){
                sectors[sectorNum].get(i).updateStock(amount);
            }
            i++;
        }
    }
    
    /**
     * Delete some arbitrary product while maintaining the heap structure in O(logn)
     * Requires proper use of the .getSize(), .get(), .swap(), .deleteLast(), .sink() and/or .swim() methods
     * Requires proper use of the .getId() method from the Product class
     * @param id The id of the product to delete
     */
    public void deleteProduct(int id) {
        // IMPLEMENT THIS METHOD
        int sectorNum = id%10;
        int sectorSize = sectors[sectorNum].getSize();
        for(int i = 1; i <= sectorSize;i++){
            System.out.println(sectors[sectorNum]);
            if(sectors[sectorNum].get(i).getId() == id){
               int last = sectors[sectorNum].getSize();
               sectors[sectorNum].swap(i, last);
               sectors[sectorNum].deleteLast();
               sectors[sectorNum].sink(i);
               break;
            }
        }
    }
    
    /**
     * Simulate a purchase order for some product
     * Requires proper use of the getSize(), sink(), get() methods in the Sector class
     * Requires proper use of the getId(), getStock(), setLastPurchaseDay(), updateStock(), updateDemand() methods
     * @param id The id of the purchased product
     * @param day The current day
     * @param amount The amount purchased
     */
    public void purchaseProduct(int id, int day, int amount) {
        // IMPLEMENT THIS METHOD
        int sectorNum = id%10;
        int sectorSize = sectors[sectorNum].getSize();
        for(int i = 1; i <= sectorSize;i++){
            if(sectors[sectorNum].get(i).getId() == id){
                int currStock = sectors[sectorNum].get(i).getStock();
                if(currStock >= amount){
                    sectors[sectorNum].get(i).setStock(currStock-amount);
                    sectors[sectorNum].get(i).setLastPurchaseDay(day);
                    sectors[sectorNum].get(i).updateDemand(amount);
                    sectors[sectorNum].sink(i);
                }
            }
        }

    }
    
    /**
     * Construct a better scheme to add a product, where empty spaces are always filled
     * @param id The id of the item to add
     * @param name The name of the item to add
     * @param stock The stock of the item to add
     * @param day The day of the item to add
     * @param demand Initial demand of the item to add
     */
    public void betterAddProduct(int id, String name, int stock, int day, int demand) {
        // IMPLEMENT THIS METHOD
        boolean wasAdded = false;
        int sectorNum = id%10;
        if(sectors[sectorNum].getSize() < 5){
            addProduct(id, name, stock, day, demand);
            wasAdded = true;
            fixHeap(id);
        }
        else{
            int i = (sectorNum + 1)%10;
            int valueToFetch = sectorNum - 1;
            if(valueToFetch == -1){
                valueToFetch = 9;
            }
            while(i != valueToFetch){
                if(sectors[i].getSize() < 5){
                    Product myProduct = new Product(id, name, stock, day, demand);
                    sectors[i].add(myProduct);
                    wasAdded = true;
                    int toFix = (id - id%10) + i;
                    fixHeap(toFix);
                    break;
                }
                i++;
                if(i == sectors.length){
                    i = 0;
                }
            }
        }
        if(wasAdded == false){
            evictIfNeeded(id);
            betterAddProduct(id, name, stock, day, demand);
        }
    }

    /*
     * Returns the string representation of the warehouse
     */
    public String toString() {
        String warehouseString = "[\n";

        for (int i = 0; i < 10; i++) {
            warehouseString += "\t" + sectors[i].toString() + "\n";
        }
        
        return warehouseString + "]";
    }

    /*
     * Do not remove this method, it is used by Autolab
     */ 
    public Sector[] getSectors () {
        return sectors;
    }
}
