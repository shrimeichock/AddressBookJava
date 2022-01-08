import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Serializable;

public class BuddyInfo implements Serializable {

    private String name;
    private String address;
    private String phoneNumber;

    public BuddyInfo(String name, String address, String phoneNumber){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public BuddyInfo(){
        this("", "", "");
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }


    @Override
    public String toString() {
        return name +
                " : " + address +
                " : " + phoneNumber;
    }

    //FIXME Old import method
    public static BuddyInfo importBuddyInfo(String buddyInfo){ //Factory method
        String [] st = buddyInfo.split(" : ");
        BuddyInfo buddy = new BuddyInfo(st[0], st[1], st[2]);
        return buddy;
    }
}
