package bots.utilities;

public class DynArray <T> {

    private T[] Array;
    private int length;

    public void length(){
        System.out.println(this.length);
    }

    public DynArray(T[] OldArray){
        this.Array = OldArray;
        this.length = OldArray.length;
    }

    public DynArray(){
        this.Array = new T[1];
        this.length = 0;
    }

    public void add(T element){
        T[] ResArray = new T[this.length+1];
        for (int i = 0; i < this.Array.length; i++){
            ResArray[i] = this.Array[i];
        }
        this.length++;
        ResArray[this.length] = element;
        this.Array = ResArray;



    }


}
