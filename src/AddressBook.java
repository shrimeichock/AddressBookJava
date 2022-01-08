import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;

public class AddressBook extends DefaultHandler implements Serializable{

    private DefaultListModel<BuddyInfo> myBuddies; //model for JList

    public AddressBook(){
        myBuddies = new DefaultListModel<>();
    }

    public void addBuddy(BuddyInfo newBuddy){
        this.myBuddies.addElement(newBuddy);
    }

    public void removeBuddy(BuddyInfo removeBuddy){
        if (removeBuddy != null) {
            this.myBuddies.removeElement(removeBuddy);
        }
    }

    public DefaultListModel<BuddyInfo> getBuddies(){
        return this.myBuddies;
    }

    public void reset() {
        this.myBuddies.removeAllElements();
    }

    /**
     * Return the address book in XML format
     * @return XML format of the book
     */
    public String toXML(){
        String XMLText;
        XMLText = "<?xml version='1.0' encoding='UTF-8'?>";
        XMLText += "<AddressBook>";

        for(Object buddy: myBuddies.toArray()){
            BuddyInfo bud = (BuddyInfo) buddy;
            XMLText += String.format("<BuddyInfo name=\"%s\" address=\"%s\" phoneNumber=\"%s\"></BuddyInfo>",bud.getName(),bud.getAddress(),bud.getPhoneNumber());
        }

        XMLText += "</AddressBook>";
        return XMLText;
    }

    /* //FIXME Old save method
    public void save(String filename) throws IOException { //saves actual object, not text (buffers)
        FileWriter fw = new FileWriter(filename);
        BufferedWriter bw = new BufferedWriter(fw);
        for(Object buddy: myBuddies.toArray()){
            bw.write(buddy.toString());
            bw.newLine();
        }
        bw.close();
    }*/

    /* //FIXME Old import method
    public void importAddressBook(String filename) throws IOException {
        this.reset();
        FileReader fw = new FileReader(filename);
        BufferedReader br = new BufferedReader(fw);

        //call importBuddyInfo on each line, pass String
        for(String line; (line = br.readLine()) != null; ) {
            addBuddy(BuddyInfo.importBuddyInfo(line));
        }
    }*/

    /* //FIXME sterilization export part 1
    public void exportAddressBookSerial(String filename) throws IOException {
        FileOutputStream oStream = new FileOutputStream(filename);
        ObjectOutputStream p = new ObjectOutputStream(oStream);

        for(Object buddy: myBuddies.toArray()){
            p.writeObject(buddy);
        }

        p.close();
        oStream.close();
    }*/

    /* //FIXME sterilization import part 1
    public void importAddressBookSerial(String filename) throws IOException, ClassNotFoundException {
        this.reset();
        FileInputStream iStream = new FileInputStream(filename);
        ObjectInputStream q = new ObjectInputStream(iStream);

        BuddyInfo line;
        try{
            while((line = (BuddyInfo) q.readObject()) != null) {
                addBuddy(line);
            }
        } catch(EOFException ex){
            //ex.printStackTrace();
        }
    }*/

    public void exportToXMLFile(String filename) throws IOException {
        FileWriter fw = new FileWriter(filename);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(toXML());
        bw.close();
    }

    public void importFromXMLFile(String filename) throws ParserConfigurationException, SAXException, IOException {
        File file = new File(filename);
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser s = spf.newSAXParser();
        this.reset();

        DefaultHandler dh = new DefaultHandler(){
            AddressBook addressBook; //FIXME is there a way to do it without creating new book?
            public void startDocument(){
                addressBook = new AddressBook();
            }

            public void startElement(String uri, String localName, String qName, Attributes a){
                //do something at start
                //System.out.println("<start element>" + qName ); <element name=bob address=121> </element>

                if(qName.equals("BuddyInfo")){
                    System.out.println(a.getValue(0)+ "\n");
                    System.out.println(a.getValue(1)+ "\n");
                    System.out.println(a.getValue(2)+ "\n");
                    BuddyInfo buddy = new BuddyInfo(a.getValue(0), a.getValue(1), a.getValue(2));
                    addressBook.addBuddy(buddy);
                }
            }

            public void endElement(String uri, String localName, String qName){
                //do something at end of doc
                //System.out.println("<end element>");
            }

            public void characters(char[] ch, int start, int length){ //Todo prints contents within tags?
                //System.out.println(new String(ch, start, length));
            }
        };
        s.parse(file, dh);
    }

    public static void main(String[] args) throws IOException {
        AddressBook book = new AddressBook();
        BuddyInfo buddy1 = new BuddyInfo("Bob", "123 Pizza Drive", "613-123-4567");
        BuddyInfo buddy2 = new BuddyInfo("Sarah", "666 Winding Way", "613-222-8888");
        book.addBuddy(buddy1);
        book.addBuddy(buddy2);
        book.toXML();
    }
}
