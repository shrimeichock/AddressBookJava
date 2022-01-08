import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.*;

public class AddressBookFrame extends JFrame {

    private AddressBook addressBook;
    private JList<BuddyInfo> buddiesList; // JList corresponding to AddressBook

    public AddressBookFrame(){
        super("Address Book");

        this.addressBook = new AddressBook();
        this.setLayout(new BorderLayout());
        JMenuBar menuBar = new JMenuBar();

        // display the content of the AddressBook as a list. Updates itself as needed
        this.buddiesList = new JList<>(this.addressBook.getBuddies());
        this.add(this.buddiesList);
        this.buddiesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.add(new JScrollPane(this.buddiesList));

        //Clear book
        JMenuItem createBook = new JMenuItem("Create New Book");
        createBook.addActionListener(e -> this.addressBook.reset());

        //Save book
        JMenuItem saveBook = new JMenuItem("Save Book");
        saveBook.addActionListener(e -> {
            String fileName = JOptionPane.showInputDialog(null, "Filename:");
            if(fileName.length() != 0) {
                try {
                    //this.addressBook.save(fileName); //FIXME from Lab 7
                    //this.addressBook.exportAddressBookSerial(fileName); //from part 1
                    this.addressBook.exportToXMLFile(fileName);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        //import book
        JMenuItem importBook = new JMenuItem("Import Book");
        importBook.addActionListener(e -> {
            String fileName = JOptionPane.showInputDialog(null, "Filename:");
            try {
                //this.addressBook.importAddressBook(fileName); //FIXME from Lab 7
                //this.addressBook.importAddressBookSerial(fileName); //from part 1
                this.addressBook.importFromXMLFile(fileName);
            } catch (IOException | ParserConfigurationException | SAXException ex) {
                ex.printStackTrace();
            }
        });

        //Handle add buddy
        JMenuItem addBuddy = new JMenuItem("Add Buddy");
        addBuddy.addActionListener(e->{
            String name = JOptionPane.showInputDialog(null, "Name:");
            String address = JOptionPane.showInputDialog(null, "Address:");
            String phoneNumber = JOptionPane.showInputDialog(null, "Phone Number:");
            this.addressBook.addBuddy(new BuddyInfo(name, address, phoneNumber));
        });

        //Handle remove buddy
        JMenuItem removeBuddy = new JMenuItem("Remove Selected Buddy");
        removeBuddy.addActionListener(e -> {
            BuddyInfo buddyToRemove = buddiesList.getSelectedValue();
            if (buddyToRemove != null){
                addressBook.removeBuddy(buddyToRemove);
            }else{
                JOptionPane.showMessageDialog(this, "Select a buddy to delete", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JMenuItem createSampleList = new JMenuItem("Create Sample Book");
        createSampleList.addActionListener(e-> this.setSampleList());

        JMenu buddyOptions = new JMenu("Buddy Options");
        JMenu addressBookOptions = new JMenu("Address Book Options");

        menuBar.add(buddyOptions);
        menuBar.add(addressBookOptions);

        buddyOptions.add(addBuddy);
        buddyOptions.add(removeBuddy);

        addressBookOptions.add(createBook);
        addressBookOptions.add(saveBook);
        addressBookOptions.add(importBook);
        addressBookOptions.add(createSampleList);

        this.add(menuBar, BorderLayout.NORTH);

        this.setSize(350, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void setSampleList() {

        this.addressBook.reset();

        BuddyInfo buddy1 = new BuddyInfo("Bob", "123 Pizza Drive", "613-123-4567");
        BuddyInfo buddy2 = new BuddyInfo("Sarah", "666 Winding Way", "613-222-8888");
        BuddyInfo buddy3 = new BuddyInfo("Sally", "78 Tapioce Drive", "613-555-7777");
        BuddyInfo buddy4 = new BuddyInfo("Bill", "910 Bridal Path", "613-333-4444");

        this.addressBook.addBuddy(buddy1);
        this.addressBook.addBuddy(buddy2);
        this.addressBook.addBuddy(buddy3);
        this.addressBook.addBuddy(buddy4);
    }

    public static void main(String[] args) {
        new AddressBookFrame();
    }
}
