package Com.Testcases.RestaurantDetail;

import java.util.HashMap;

public class TestProgram {

	public static void main(String[] args) throws CloneNotSupportedException 
    {
        HashMap<Integer, String> map1 = new HashMap<>();
 
        map1.put(1, "A");
        map1.put(2, "B");
        map1.put(3, "C");
         
        //Same keys as map1 
        HashMap<Integer, String> map2 = new HashMap<>();
 
        map2.put(4, "C");
        map2.put(1, "A");
        map2.put(5, "B");
         
        //Different keys than map1
        HashMap<Integer, String> map3 = new HashMap<>();
 
        map3.put(1, "A");
        map3.put(2, "B");
        map3.put(3, "C");
        map3.put(3, "D");
         
        System.out.println( map1.keySet().equals( map2.keySet() ));  //true
        System.out.println( map1.keySet().equals( map3.keySet() ));  //false
        
        System.out.println( map2.containsKey( map1 ));  //true
        System.out.println( map1.equals( map3 ));  //false
    }

}
