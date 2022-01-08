import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.Assert.*;

public class AddressBookFrameTest {

    private AddressBook addressBook;
    private BuddyInfo buddy1;
    private BuddyInfo buddy2;

    @Before
    public void setUp() throws Exception {
        this.addressBook = new AddressBook();
        this.buddy1 = new BuddyInfo("Bob", "123 Pizza Drive", "613-123-4567");
        this.buddy2 = new BuddyInfo("Sarah", "666 Winding Way", "613-222-8888");
        this.addressBook.addBuddy(buddy1);
        this.addressBook.addBuddy(buddy2);
    }

    /* FIXME test for old save/export
    @Test
    public void checkImportExportBookBuffer() throws IOException {
        addressBook.save("testBasic.txt");
        DefaultListModel<BuddyInfo> myBuddiesExport = addressBook.getBuddies();

        //addressBook = new AddressBook();
        addressBook.importAddressBook("testBasic.txt");
        DefaultListModel<BuddyInfo> myBuddiesImport = addressBook.getBuddies();

        assertArrayEquals(myBuddiesExport.toArray(), myBuddiesImport.toArray());
    }*/

    /*
    @Test
    public void checkImportExportBookSerial() throws IOException, ClassNotFoundException {
        addressBook.exportAddressBookSerial("testSerial.txt");
        DefaultListModel<BuddyInfo> myBuddiesExport = addressBook.getBuddies();

        addressBook.importAddressBookSerial("testSerial.txt");
        DefaultListModel<BuddyInfo> myBuddiesImport = addressBook.getBuddies();

        assertArrayEquals(myBuddiesExport.toArray(), myBuddiesImport.toArray());
    }*/

    @Test
    public void checkImportExportXML() throws IOException, ParserConfigurationException, SAXException {
        addressBook.exportToXMLFile("sampleAddress.xml");
        DefaultListModel<BuddyInfo> myBuddiesExport = addressBook.getBuddies();
        Object[] before = addressBook.getBuddies().toArray();

        addressBook.importFromXMLFile("sampleAddress.xml");
        //BuddyInfo buddy3 = new BuddyInfo("Bob", "123 Pizza Drive", "613-123-4567");
        //addressBook.addBuddy(buddy3);

        DefaultListModel<BuddyInfo> myBuddiesImport = addressBook.getBuddies();

        assertArrayEquals(myBuddiesExport.toArray(), myBuddiesImport.toArray());
    }
}