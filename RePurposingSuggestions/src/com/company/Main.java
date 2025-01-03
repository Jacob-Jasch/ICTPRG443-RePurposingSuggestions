// the package which the class is located inside of
package com.company;

//All of the imports used by the rest of the code
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;


//----------------------------------------------------------------------------------------------------------------------
// Here defines the names of all the different fields in the UI


//the start of the main class, it implements two other class's, action listener and window listener which are both used
//to detect different types of events that may happen
public class Main extends Frame implements WindowListener, ActionListener
{
    //A list of the name's for all of the labels which declares them
    Label lblIdeaTitle, lblImageFile, lblWebLink, lblPrimaryMaterials, lblConstructionProcess, lblFind, lblWorkOrNot;
    //A list of the name's for all of the TextFields which declares them
    TextField txtIdeaTitle, txtImageFile, txtWebLink, txtPrimaryMaterials, txtFind, txtSearch;
    //A list of the name's for all of the Buttons which declares them
    Button btnNew, btnSave, btnDel, btnFind, btnExit, btnFirst, btnPrev, btnNext, btnLast, btnSortName, btnBinary, btnFilter;
    //A list of the name's for all of the TextAreas which declares them
    TextArea txtDetails, txtConstructionProcess;

    //The array which will hold all of the data that has been entered
    Suggestion[] RecyclersList = new Suggestion[100];

    // displayRecord is a int which will be used to change which line of the txt file will be currently being displayed
    int displayRecord = 0;
    // this int will keep track of the number of lines in a txt file
    int NumberOfEntries = 0;

    SpringLayout myLayout;

//----------------------------------------------------------------------------------------------------------------------
// This sets up the main window


    public static void main(String[] args)
    {
        //declares a new frame
        Frame myFrame = new Main();
        //set's the size of the frame
        myFrame.setSize(650,500);
        //set's the location of the frame on the screen
        myFrame.setLocation(400, 200);
        //sets whether or not you can change the size of the frame via clicking the edge and pulling to expand the frame
        //once the program is running
        myFrame.setResizable(false);
        //set's it so you can see the frame, which render's the frame
        myFrame.setVisible(true);
    }


//----------------------------------------------------------------------------------------------------------------------
// Here all the different types of fields get added

    // this is what runs when you click start
    public Main()
    {
        //set's the name of the app
        setTitle("RePurposing Suggestions");
        //set's the background colour of the main window
        setBackground(Color.white);

        //creates a new spring layout object
        myLayout = new SpringLayout();
        setLayout(myLayout);

        //Call's the methods below to instantiate and place the various screen components
        LocateLabels(myLayout);
        LocateTextFields(myLayout);
        LocateButtons(myLayout);
        LocateATextArea(myLayout);

        //adds a windowListener to this class
        this.addWindowListener(this);

        //call's the ReadFile method which will access all data saved in a txt file and save it to a array
        ReadFile();
        //displays some of the data from that array into a text field
        DisplayTXTDetails();
        //calls the Display method which will display the first line of data in the txt file
        Display(0);
    }


//----------------------------------------------------------------------------------------------------------------------
/**
 * Method that manages the adding of multiple Labels to the screen.
 * it defines each label, set's where it is on the screen and set's the contents of the label
**/

    public void LocateLabels(SpringLayout myLabelLayout)
    {
        lblIdeaTitle = LocateALabel(myLabelLayout, lblIdeaTitle, "Idea title:                     ", 30, 25);
        ColorLabels(lblIdeaTitle);
        lblImageFile = LocateALabel(myLabelLayout, lblImageFile, "Image File:                 ", 30, 50);
        ColorLabels(lblImageFile);
        lblWebLink = LocateALabel(myLabelLayout, lblWebLink, "Web link:                     ", 30, 75);
        ColorLabels(lblWebLink);
        lblPrimaryMaterials = LocateALabel(myLabelLayout, lblPrimaryMaterials, "Primary Materials:     ", 30, 100);
        ColorLabels(lblPrimaryMaterials);
        lblConstructionProcess = LocateALabel(myLabelLayout, lblConstructionProcess, "Construction Hints:   ", 30, 125);
        ColorLabels(lblConstructionProcess);
        lblFind = LocateALabel(myLabelLayout, lblFind, "Find:", 430, 0);
        ColorLabels(lblFind);
        lblWorkOrNot = LocateALabel(myLabelLayout, lblWorkOrNot, "                ", 30, 150);
    }


//----------------------------------------------------------------------------------------------------------------------
/**
 * this sets up the background colour called cornflower blue for use wherever wanted
 * **/

//Create the method "ColorLabels"
    public void ColorLabels(Label myLabel)
    {
        //when this method is called, set's the colour of whatever calls it to cornflower blue
        Color lblColor = new Color(100, 149, 237);
        myLabel.setBackground(lblColor);
        //Set's the background of the app to white
        myLabel.setForeground(Color.white);
    }


//----------------------------------------------------------------------------------------------------------------------
/**
 * This locates where all Labels are on the screen
**/
    //Creates the method called "LocateALabel" which is used in "LocateLabels" to help set up the labels
    public Label LocateALabel(SpringLayout myLabelLayout, Label myLabel, String  LabelCaption, int x, int y)
    {
        myLabel = new Label(LabelCaption);
        add(myLabel);
        myLabelLayout.putConstraint(SpringLayout.WEST, myLabel, x, SpringLayout.WEST, this);
        myLabelLayout.putConstraint(SpringLayout.NORTH, myLabel, y, SpringLayout.NORTH, this);
        return myLabel;
    }


//----------------------------------------------------------------------------------------------------------------------
/**
 * Method that manages the adding of multiple TextFields to the screen.
 * it defines each text field, and set's where it is on the screen
**/

    public void LocateTextFields(SpringLayout myTextFieldLayout)
    {
        txtIdeaTitle = LocateATextField(myTextFieldLayout, txtIdeaTitle, 30, 170, 25);
        txtImageFile = LocateATextField(myTextFieldLayout, txtImageFile, 30, 170, 50);
        txtWebLink = LocateATextField(myTextFieldLayout, txtWebLink, 30, 170, 75);
        txtPrimaryMaterials = LocateATextField(myTextFieldLayout, txtPrimaryMaterials, 30, 170, 97);
        txtFind = LocateATextField(myTextFieldLayout, txtFind, 8, 470, 0);
        txtSearch = LocateATextField(myTextFieldLayout, txtSearch, 18, 100, 245);
    }


//----------------------------------------------------------------------------------------------------------------------
/**
 * This locates where all Text fields are on the screen
**/

    //Creates the method called "LocateATextField" which is used in "LocateTextFields" to help set up the Text Field
    public TextField LocateATextField(SpringLayout myTextFieldLayout, TextField myTextField, int width, int x, int y)
    {
        myTextField = new TextField(width);
        add(myTextField);
        myTextFieldLayout.putConstraint(SpringLayout.WEST, myTextField, x, SpringLayout.WEST, this);
        myTextFieldLayout.putConstraint(SpringLayout.NORTH, myTextField, y, SpringLayout.NORTH, this);
        return myTextField;
    }


//----------------------------------------------------------------------------------------------------------------------
/**
 * Method that manages the adding of multiple TextArea to the screen.
 * it defines each text area, and set's where it is on the screen
**/

    public void LocateATextArea(SpringLayout myTestAreaLayout)
    {
        txtDetails = LocateATextArea(myTestAreaLayout, txtDetails, 63, 7, 100, 300);
        txtConstructionProcess = LocateATextArea(myTestAreaLayout, txtConstructionProcess, 30, 2, 170, 120);
        txtDetails.setEditable(false);
    }


//----------------------------------------------------------------------------------------------------------------------
/**
 * This locates where all Text Area's are on the screen
**/

    //Creates the method called "LocateATextArea" which is used in "LocateTextArea" to help set up the Text Area
    public TextArea LocateATextArea(SpringLayout myTestAreaLayout, TextArea myTestArea, int width, int height, int x, int y)
    {
        myTestArea = new TextArea(height, width);
        add(myTestArea);
        myTestAreaLayout.putConstraint(SpringLayout.WEST, myTestArea, x, SpringLayout.WEST, this);
        myTestAreaLayout.putConstraint(SpringLayout.NORTH, myTestArea, y, SpringLayout.NORTH, this);
        return myTestArea;
    }


//----------------------------------------------------------------------------------------------------------------------
/**
 * Method that manages the adding of multiple Buttons to the screen.
 * it defines each button, set's where it is on the screen and set's the contents of the button
**/

    public void LocateButtons(SpringLayout myButtonLayout)
    {
        btnNew = LocateAButton(myButtonLayout, btnNew, "New", 470, 125, 80, 25, this);
        btnSave = LocateAButton(myButtonLayout, btnSave, "Save", 470, 50, 80, 25,this);
        btnDel = LocateAButton(myButtonLayout, btnDel, "Delete", 470, 75, 80, 25, this);
        btnFind = LocateAButton(myButtonLayout, btnFind, "Find", 470, 25, 80, 25, this);
        btnExit = LocateAButton(myButtonLayout, btnExit, "Exit", 470, 100, 80, 25, this);
        btnFirst = LocateAButton(myButtonLayout, btnFirst, "|<", 180, 170, 30, 25,this);
        btnPrev = LocateAButton(myButtonLayout, btnPrev, "<", 220, 170, 30, 25, this);
        btnNext = LocateAButton(myButtonLayout, btnNext, ">", 260, 170, 30, 25, this);
        btnLast = LocateAButton(myButtonLayout, btnLast, ">|", 300, 170, 30, 25, this);
        btnSortName = LocateAButton(myButtonLayout, btnSortName, "Short By Idea Title",255, 270, 150, 25, this);
        btnBinary = LocateAButton(myButtonLayout, btnBinary, "Binary Search by Idea Title",100, 270, 150, 25, this);
        btnFilter = LocateAButton(myButtonLayout, btnFilter, "Filter by: ", 410, 270, 150, 25, this);
    }


//----------------------------------------------------------------------------------------------------------------------
/**
 * This locates where all the buttons are on the screen
**/

    //Creates the method called "LocateAButton" which is used in "LocateButton" to help set up the buttons
    public Button LocateAButton(SpringLayout myButtonLayout, Button myButton, String  ButtonCaption, int x, int y, int w, int h, ActionListener myListener)
    {
        myButton = new Button(ButtonCaption);
        add(myButton);
        myButtonLayout.putConstraint(SpringLayout.WEST, myButton, x, SpringLayout.WEST, this);
        myButtonLayout.putConstraint(SpringLayout.NORTH, myButton, y, SpringLayout.NORTH, this);
        myButton.setPreferredSize(new Dimension(w,h));
        myButton.addActionListener(myListener);
        return myButton;
    }


//----------------------------------------------------------------------------------------------------------------------
    /**
     * Creates the method called Display which take's as a CurrentRecord int which will be the currently displayed
     * record once the app is running
     **/


    public void Display(int CurrentRecord)
    {
        //Set's txt(InsertNameHere) to the currently displayed record from the main array, RecyclersList
        txtIdeaTitle.setText(RecyclersList[CurrentRecord].getIdeaTitle());
        txtImageFile.setText(RecyclersList[CurrentRecord].getImageFile());
        txtWebLink.setText(RecyclersList[CurrentRecord].getWebLink());
        txtPrimaryMaterials.setText(RecyclersList[CurrentRecord].getPrimaryMaterials());
        txtConstructionProcess.setText(RecyclersList[CurrentRecord].getConstructionProcess());
    }


//----------------------------------------------------------------------------------------------------------------------
    /**
     * write data to "RePurposingSuggestions.txt"
     **/


    public void WriteToFile()
    {
        //Try's to write to file
        try
        {
            //Use's the PrinterWriter class to open and write to "RePurposingSuggestions.txt"
            PrintWriter writer = new PrintWriter(new FileWriter("RePurposingSuggestions.txt"));
            //Creates a for loop which will Cycle through RecyclersList and add write it's contents to the file
            for (int i = 0; i < RecyclersList.length; i++)
            {
                var item = RecyclersList[i];
                if (RecyclersList[i] != null)
                {
                    writer.println(item.getIdeaTitle() + ";" + item.getImageFile() + ";" + item.getWebLink() + ";" +
                            item.getPrimaryMaterials() + ";" + item.getConstructionProcess() + ";");
                }
            }
            //Close's the PrintWriter to avoid having to many open files lowering performance
            writer.close();
        }
        //If writing to file fails, writes the error to the console
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


//----------------------------------------------------------------------------------------------------------------------
/**
 * Reads "RePurposingSuggestions.txt"
**/

    public void ReadFile()
    {
        //Try's to read to file
        try
        {
            //Set's the file which will be read from
            File txtFile = new File("RePurposingSuggestions.txt");
            //Creates a new scanner object which will read the file
            Scanner fileReader = new Scanner(txtFile);
            //Creates a loop which will go until the file no longer has a next line
            while (fileReader.hasNextLine())
            {
                //Creates the string fileData which will contain the next line of the file
                String fileData = fileReader.nextLine();
                //Creates a temp array called TempArrOfStr which contains the next line of data from the file and splits
                //it up into the multiple strings by removing the ';'
                String[] tempArrOfStr = fileData.split(";");

                //adds the items from tempArrOfStr to RecyclerList, this is the array which will be used to store all
                //of the data well the program is running
                RecyclersList[NumberOfEntries] = new Suggestion(tempArrOfStr[0], tempArrOfStr[1], tempArrOfStr[2],
                        tempArrOfStr[3], tempArrOfStr[4]);
                //Keep's track of the number of entries inside of RecyclerList by increasing the number of
                //NumberOfEntries
                NumberOfEntries++;
            }
            //Close's the FileReader to avoid having to many open files lowering performance
            fileReader.close();
        }
        //If an error occurs, writes the error down and let's you know
        catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

//----------------------------------------------------------------------------------------------------------------------
/**
 * refreshes the data grids so that all displayed info is up to date/correct
**/

    public void RefreshData()
    {
        //Creates a new arrayList called SaveList
        ArrayList<String> SaveList = new ArrayList<String>();
        //Retrieves all data that is currently entered by the user and stores it in SaveList
        SaveList.add(GetData());

        //A for loop which goes as many times as there are entry's in SaveList
        for(int i=0;i<SaveList.size();i++)
        {
            //Appends all of the data from SaveList to txtDetails
            txtDetails.append(SaveList.get(i));
        }
        //Makes a new line in txtDetails
        txtDetails.append("\n");
    }


//----------------------------------------------------------------------------------------------------------------------


    public void RefreshDataGrid()
    {
        //Clears the text in txtDetails
        txtDetails.setText("");
        //Adds all of the data from RecyclerList to txtDetails
        DisplayTXTDetails();
        //Set's what is being displayed to displayRecord
        Display(displayRecord);
    }


//----------------------------------------------------------------------------------------------------------------------
    /**
     * Clears all of the data entered by the user
     **/


    public void ClearEntrance()
    {
        txtIdeaTitle.setText("");
        txtImageFile.setText("");
        txtWebLink.setText("");
        txtPrimaryMaterials.setText("");
        txtConstructionProcess.setText("");
    }


//----------------------------------------------------------------------------------------------------------------------


    public void DisplayTXTDetails()
    {
        //A for loop which will run as many times as there are entries in RecyclerList
        for (int i = 0; i < NumberOfEntries; i++)
        {
            //Add all of the data from RecyclersList into txtDetails
            txtDetails.append(RecyclersList[i].getIdeaTitle() + "  ");
            txtDetails.append(RecyclersList[i].getImageFile() + "  ");
            txtDetails.append(RecyclersList[i].getWebLink() + "  ");
            txtDetails.append(RecyclersList[i].getPrimaryMaterials() + "  ");
            txtDetails.append(RecyclersList[i].getConstructionProcess() + "  ");
            //Adds a new line to txtDetails
            txtDetails.append("\n");
        }
    }


//----------------------------------------------------------------------------------------------------------------------
/**
 * deletes data from records.txt
**/

    public void DelData()
    {
        //Clears all of the user entered data
        ClearEntrance();

        for (int i = displayRecord; i < RecyclersList.length - 1; i++)
        {
            //if RecyclersList is empty, return
            if(RecyclersList[i] == null)
            {
                return;
            }
            //if i has the same value as NumberOfEntries, decrees DisplayRecord by 1 as one data has been removed
            if (i == NumberOfEntries -1)
            {
                displayRecord--;
                //set's Display to be showing displayRecord, which is one less then it used to be because there is now
                //one less data then there used to be
                Display(displayRecord);
            }
            //overwrites the data that has been removed
            RecyclersList[i] = RecyclersList[i + 1];
        }
        //due to data having had been removed, decreases the NumberOfEntries
        NumberOfEntries--;
    }


//----------------------------------------------------------------------------------------------------------------------


    private void SortNameInAlphabeticalOrder()
    {
        //Make's a new String array with a size the same as NumberOfEntries called NameSort
        String[] NameSort = new String[NumberOfEntries];
        //adds all of the Idea Titles from RecyclersList into NameSort
        for (int i=0; i < NumberOfEntries; i++)
        {
            NameSort[i] = RecyclersList[i].IdeaTitle;
        }
        //Sort's NameSort in alphabetical order
        Arrays.sort(NameSort);

        //adds all of of the IdeaTitles to txtDetails once it has been sorted
        for (int j=0; j < NumberOfEntries; j++)
        {
            txtDetails.append(NameSort[j]);
            txtDetails.append("\n");
        }
    }


//----------------------------------------------------------------------------------------------------------------------


    private void BinarySearch()
    {
        //Make's a new String array with a size the same as NumberOfEntries called NameSort
        String[] NameSort = new String[NumberOfEntries];
        //adds all of the Idea Titles from RecyclersList into NameSort
        for (int i=0; i < NumberOfEntries; i++)
        {
            NameSort[i] = RecyclersList[i].IdeaTitle.toLowerCase();
        }
        //Sort's NameSort in alphabetical order
        Arrays.sort(NameSort);

        //search's through NameSort to find data searched by the user and returns the location the data was found at
        int position = Arrays.binarySearch(NameSort, txtSearch.getText().toLowerCase());
        //to avoid returning null results, this checks to make sure position is greater then or equal 0
        if (position >= 0)
        {
            //adds some text followed by the index the searched data was found at
            txtDetails.append(txtSearch.getText() + " is at a index of: " + (position + 1));
            //adds all of of the IdeaTitles to txtDetails once it has been sorted
            for (int i=0; i < NumberOfEntries; i++)
            {
                txtDetails.append("\n");
                txtDetails.append(NameSort[i]);
            }
        }
        //if the search fails to find a positive result, aka not null. a error message is provided in txtDetails
        else
        {
            txtDetails.append("Nothing match's that search!");
        }
    }


//----------------------------------------------------------------------------------------------------------------------


    private void Find()
    {
        //Make's a new String array with a size the same as NumberOfEntries called NameSort
        String[] NameSort = new String[NumberOfEntries];
        //adds all of the Idea Titles from RecyclersList into NameSort
        for (int i=0; i < NumberOfEntries; i++)
        {
            NameSort[i] = RecyclersList[i].IdeaTitle;
        }
        //Sort's NameSort in alphabetical order
        Arrays.sort(NameSort);

        //search's through NameSort to find data searched by the user and returns the location the data was found at
        int position = Arrays.binarySearch(NameSort, txtFind.getText());
        //to avoid returning null results, this checks to make sure position is greater then or equal 0
        if (position >= 0)
        {
            //set's the display to the correct index of the search, meaning the user will automatically see the
            //searched data
            Display(position);
        }
        //if the search fails to find a positive result, aka not null. a error message is provided in txtDetails
        else
        {
            txtDetails.append("Nothing match's that search!");
        }
    }


//----------------------------------------------------------------------------------------------------------------------


    private void FilterBy()
    {
        //Makes a string called FindContent which is = to the text inside of txtSearch
        String FindContent = txtSearch.getText();

        //loops through a if statement as many times as there are entries in RecyclersList
        for (int k=0; k < NumberOfEntries ; k++)
        {
            //search's through PrimaryMaterials in RecyclersList for everything which contains the searched data
            if (RecyclersList[k].PrimaryMaterials.toLowerCase().contains(FindContent.toLowerCase()))
            {
                //adds all the data from which PrimaryMaterials contained a searched term
                txtDetails.append(RecyclersList[k].IdeaTitle + " ");
                txtDetails.append(RecyclersList[k].ImageFile + " ");
                txtDetails.append(RecyclersList[k].WebLink + " ");
                txtDetails.append(RecyclersList[k].PrimaryMaterials + " ");
                //adds a new line to avoid all data being on the same line
                txtDetails.append(RecyclersList[k].ConstructionProcess + "\n");
            }
        }
    }


//----------------------------------------------------------------------------------------------------------------------
/**
 * This see's if a button is clicked and acts accordingly
**/

    //runs the code for whenever a button is hit
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == btnFind)
        {
            //clears txtDetails
            txtDetails.setText("");
            //runs the find method
            Find();
            //clears txtFind
            txtFind.setText("");
        }
        if(e.getSource() == btnSave)
        {
            //gets all the currently entered data and adds it to RecyclersList
            RecyclersList[displayRecord] = new Suggestion(txtIdeaTitle.getText(), txtImageFile.getText(),
                    txtWebLink.getText(), txtPrimaryMaterials.getText(), txtConstructionProcess.getText());
            //runs the RefreshData method
            RefreshData();
            //runs the WriteToFile method
            WriteToFile();
            NumberOfEntries++;
        }
        if(e.getSource() == btnDel)
        {
            //runs the DelDate method
            DelData();
            //runs the RefreshDataGrid method
            RefreshDataGrid();
        }
        if(e.getSource() == btnExit)
        {
            //runs the WriteToFile method
            WriteToFile();
            //Close's the program with the crash message of closed on command
            System.exit(0);
        }
        if(e.getSource() == btnNew)
        {
            //runs the ClearEntrance method
            ClearEntrance();
            //Set's NumberOfEntries to the same as displayRecord
            displayRecord = NumberOfEntries;
            //Increases the number of entries by 1 as a new data has been added
        }
        if(e.getSource() == btnFirst)
        {
            //Clears lblWorkOrNot
            lblWorkOrNot.setText("");
            //Set's DisplayRecord back to 0, the starting record
            displayRecord = 0;
            //runs the display method to display record 0
            Display(displayRecord);
        }
        if(e.getSource() == btnPrev)
        {
            //Checks to see if there are any are previous entry's to display
            if (displayRecord == 0)
            {
                //if there was none, displays some text as a error message
                lblWorkOrNot.setText("Sorry!");
            }
            else
            {
                //Change's the currently displayed data to the previous one
                displayRecord--;
                lblWorkOrNot.setText("");
                Display(displayRecord);
            }
        }
        if(e.getSource() == btnNext)
        {
            //Checks to see if there is a next entry to display
            if (displayRecord > NumberOfEntries -2)
            {
                //if there was none, displays some text as a error message
                lblWorkOrNot.setText("Sorry!");
            }
            else
            {
                //Change's the currently displayed data to the next one
                displayRecord++;
                lblWorkOrNot.setText("");
                Display(displayRecord);
            }
        }
        if(e.getSource() == btnLast)
        {
            //Clears lblWorkOrNot
            lblWorkOrNot.setText("");
            //Set's DisplayRecord to the number of entries - 1
            displayRecord = NumberOfEntries -1;
            //runs the display method to display the last record
            Display(displayRecord);
        }
        if(e.getSource() == btnBinary)
        {
            //clears txtDetails
            txtDetails.setText("");
            //runs the BinarySearch method
            BinarySearch();
            //clear txtSearch
            txtSearch.setText("");
        }
        if(e.getSource() == btnSortName)
        {
            //clear txtDetails
            txtDetails.setText("");
            //runs the SortNameInAlphabeticalOrder method
            SortNameInAlphabeticalOrder();
        }
        if (e.getSource() == btnFilter)
        {
            //clears txtDetails
            txtDetails.setText("");
            //runs the FilterBy method
            FilterBy();
            //clears txtSearch
            txtSearch.setText("");
        }
    }



//----------------------------------------------------------------------------------------------------------------------
/**
 * gets the data that is currently entered in all of the txt fields
**/

    //Gets all of the data a user has entered and save's it to a string of the same name
    public String GetData()
    {
        String BusinessName = txtIdeaTitle.getText();

        String Address = txtImageFile.getText();

        String PhoneNo = txtWebLink.getText();

        String WebSite = txtPrimaryMaterials.getText();

        String Recyclers = txtConstructionProcess.getText();
        //returns all of the data but divided up by ;
        return BusinessName + ";" + Address + ";" + PhoneNo + ";" + WebSite + ";" + Recyclers;

    }


//----------------------------------------------------------------------------------------------------------------------
/**
 * Manage responses to the various Window events
**/

    //Runs when the x buttons in the top hight hand corner of a window is hit
    public void windowClosing(WindowEvent we)
    {
        //runs the WriteToFile method
        WriteToFile();
        //Close's the program with the crash message of closed on command
        System.exit(0);
    }

    public void windowIconified(WindowEvent we)
    {
    }

    public void windowOpened(WindowEvent we)
    {
    }

    public void windowClosed(WindowEvent we)
    {
    }

    public void windowDeiconified(WindowEvent we)
    {
    }

    public void windowActivated(WindowEvent we)
    {
    }

    public void windowDeactivated(WindowEvent we)
    {
    }

}
