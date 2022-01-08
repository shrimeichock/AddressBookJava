# AddressBookJava

This application simulates a simple address book that allows you to add a series of 'buddies' and takes down each buddy's name, address and phone number. 
The list of buddies and their info is displayed and the user has the option to save their book as an xml so they can come back to it later. 

## Installation
1. Download source files and add them to a new java project
2. Include SampleAddress.xml in the same folder as the project
3. Run the program from the main method in the AddressBookFrame class

## Usage
- Add a new buddy using the `Add Buddy` button under the `Buddy Options` header. The buddy info will appear as a colon separated list
- Select a buddy and click `Remove Selected Buddy` to delete them from the list. An error message will pop up if a buddy is not selected
- Click `Create New Book` under the `Address Book Options` tab to clear the existing book.
- Save or import a book by clicking the appropriate menu buttons and entering the name of the XML file to save it to. SampleAddress.xml is given as an example of how the data will be saved. Multiple books can be saved as long as they have unique file names.
- `Create Sample Book` will provide an example of how a completed book should look

## Known issues/Improvements
- The buddy information is currently being stored in the tags of the `BuddyInfo` xml object which is not ideal. Creating a sepaarate default handler class that makes proper use of the start and endElement methods would be a better solution
- A cleaner, more attractive UI is on the to do list. Specifically the colon separated valaues
- Use pattern matching or something similar to check that user input is valid

## Skills I learned
- How to use XML to save and import simple data. Specifically using a BufferedWritter for exporting and a DefaultHandler to parse the data when importing.
- How to use serlialization in Java and saving/reading whole objects instead of their properties. This section of code is commented out in the `AddressBook` class
- How to split a string on a certain value 
- How to create a JMenuBar
