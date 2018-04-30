package projectkonsep;

/**
 *  The Item class is designed to be used by Swing components that use a
 *  renderer to display an Object. Swing renderers rely on the toString()
 *  implementation to display a description of an Object. Separating
 *  the value and description properties gives increased flexibilty.
 *
 *  The "description" property is used by the renderer. The "value"
 *  property is used for processing by the application.
 */
public class Item<V> implements Comparable<Item>{
    private V value;
    private int id_tipe;
    private int id_merk;
    private int id_jenis;
    private String description;
    
    /**
    *  Create an Item object
    *
    *  @param value an Object containing data used by the application
     * @param id_tipe
    *  @param description the text to be displayed by a renderer
    */
    public Item(V value, int id_tipe, String description){
        this.value = value;
        this.id_tipe = id_tipe;
        this.description = description;
    }
    
    public Item(V value, int id_tipe, int id_merk, String description){
        this.value = value;
        this.id_tipe = id_tipe;
        this.id_merk = id_merk;
        this.description = description;
    }
    
    public Item(V value, int id_tipe, int id_merk, int id_jenis, String description){
        this.value = value;
        this.id_tipe = id_tipe;
        this.id_merk = id_merk;
        this.id_jenis = id_jenis;
        this.description = description;
    }
    
    

    
    
    /**
    *  Get the Object containing application data
    *
    *  @returns the application data
    */
    public V getValue(){
        return value;
    }
    
    /**
    *  Get the description of the value data
    *
     * @return 
    *  @returns the description to be displayed by a renderer
    */

    public String getDescription(){
        return description;
    }
   
    /**
    *  Implement the natural order for this class using the
    *  Description property
    *
    *  @param item the other Item object to be used in the comparison
    */
    @Override
    public int compareTo(Item item){
        return getDescription().compareTo(item.getDescription());
    }
    
    /**
    *  The Value property will be used to check for equality
     * @param object
    */

//    @Override
//    public boolean equals(Object object){
//        Item item = (Item)object;
//        return (item == null) ? false : value.equals(item.getValue());
//    }

    /**
    *  The Value property will be used to determine the hashCode
    */
    
    @Override
    public int hashCode()	{
        return value.hashCode();
    }
    
    /**
    *	The Description property will double as the toString representation.
    *
    *  @return the description
    */
    @Override
    public String toString(){
        return description;
    }

    public int getId_tipe() {
        return id_tipe;
    }

    public int getId_merk() {
        return id_merk;
    }

    public int getId_jenis() {
        return id_jenis;
    }

    
}