//==============================================================
//
// Title:       Memory Matcher
// Course:      CSC 5991 Android Mobile Application Development
// Application: 2
// Author:      Shailja Sharma
// Date:        6-10-2016
// Description:
//  Memory Matcher to write an Android application to promote the user (brain) memory.
// The application has a grid of twelve cells hiding six images.
// The application randomizes where the images are hidden.
// The user picks one cell to reveal its image and then picks another cell to reveal its image.
// If the two images match, the two cells are marked as matched.
// If the two images doesnot match, the two cells continue to be marked as unmatched
// 
//
//==============================================================


package memorymatcher.csc5991.wsu.memorymatcher;

/*------------------------------------
 Import Android packages
 */


import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;



/*--------------------------------------------------------------
  Class ActMain
--------------------------------------------------------------
*/

public class ActMain extends AppCompatActivity {

    /*--------------------------------------------------------------
        Declare Variables
    -------------------------------------------------------------
    */

    private ImageView[] imageArray = new ImageView[12];
    private int imageCellPointer[] = new int[12];
    private Integer imageNumber[] = new Integer[6];
    private NumberPicker np1;
    private Button resetBoard;
    private Button nextTry;
    private RadioGroup radioGroupColorvalue;
    private RadioButton rbRed;
    private RadioButton rbGreen;
    private RadioButton rbBlue;
    private EditText noOfTries;
    private EditText noOfGames;
    int nooftries;
    int noofmatchespalyed;
    boolean unmatched;
    boolean matched;
    private int imagePtr = 0;
    int macthedpointerfirst = 23;
    int matchedpointersecond = 43;
    int ptr = 0;
    String APP_NAME;
    private int count;
    Set<Integer> pointerSet = new TreeSet<Integer>();
    List<Integer> colorArrray = new ArrayList<Integer>(6);
    Integer[] pointerarray;


    /*--------------------------------------------------------------
           onCreate
     --------------------------------------------------------------
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.laymain);

        // Taking images from resource file
        imageNumber[0] = R.mipmap.emu;
        imageNumber[1] = R.mipmap.msu;
        imageNumber[2] = R.mipmap.ou;
        imageNumber[3] = R.mipmap.um;
        imageNumber[4] = R.mipmap.wayne;
        imageNumber[5] = R.mipmap.wmu;


        colorArrray.addAll(Arrays.asList(imageNumber));

        Random random = new Random();

        // Inserting images value in image pointer array


        for (int i = 0; i <= 11; i++) {
            if (i > 5) {

                colorArrray = new ArrayList<Integer>(6);
                colorArrray.addAll(Arrays.asList(imageNumber));
            }
            int num = random.nextInt(colorArrray.size());
            imageCellPointer[i] = colorArrray.get(num);
            colorArrray.remove(colorArrray.get(num));

        }

        System.out.println("imageCellPointer array : " + imageCellPointer);

        imagePtr = 0;

        imageArray[0] = (ImageView) (findViewById(R.id.ib1));
        imageArray[0].setImageResource(R.color.green);
        imageArray[1] = (ImageView) (findViewById(R.id.ib2));
        imageArray[1].setImageResource(R.color.green);
        imageArray[2] = (ImageView) (findViewById(R.id.ib3));
        imageArray[2].setImageResource(R.color.green);
        imageArray[3] = (ImageView) (findViewById(R.id.ib4));
        imageArray[3].setImageResource(R.color.green);
        imageArray[4] = (ImageView) (findViewById(R.id.ib5));
        imageArray[4].setImageResource(R.color.green);
        imageArray[5] = (ImageView) (findViewById(R.id.ib6));
        imageArray[5].setImageResource(R.color.green);
        imageArray[6] = (ImageView) (findViewById(R.id.ib7));
        imageArray[6].setImageResource(R.color.green);
        imageArray[7] = (ImageView) (findViewById(R.id.ib8));
        imageArray[7].setImageResource(R.color.green);
        imageArray[8] = (ImageView) (findViewById(R.id.ib9));
        imageArray[8].setImageResource(R.color.green);
        imageArray[9] = (ImageView) (findViewById(R.id.ib10));
        imageArray[9].setImageResource(R.color.green);
        imageArray[10] = (ImageView) (findViewById(R.id.ib11));
        imageArray[10].setImageResource(R.color.green);
        imageArray[11] = (ImageView) (findViewById(R.id.ib12));
        imageArray[11].setImageResource(R.color.green);

        APP_NAME = getString(R.string.app_name);


        // Connect to the components from laymain.xml

        np1 = (NumberPicker) findViewById(R.id.np1);

        resetBoard = (Button) findViewById(R.id.resetBoard);

        nextTry = (Button) findViewById(R.id.nextTry);

        radioGroupColorvalue = (RadioGroup) findViewById(R.id.colorvalue);

        rbRed = (RadioButton) findViewById(R.id.rbRed);

        rbGreen = (RadioButton) findViewById(R.id.rbGreen);

        rbBlue = (RadioButton) findViewById(R.id.rbBlue);

        noOfTries = (EditText) findViewById(R.id.tries);

        noOfGames = (EditText) findViewById(R.id.matches);


        //minimum and maximum value for numberpicker
        np1.setMinValue(20);
        np1.setMaxValue(120);

        final double DPtoPXconversionFactor =
                getApplicationContext().getResources().getDisplayMetrics().densityDpi / 160.;

        //changeListener for numberpicker
        np1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {


                LinearLayout.LayoutParams parms = (LinearLayout.LayoutParams) imageArray[1].getLayoutParams(); 

               parms.width = (int) (newVal * DPtoPXconversionFactor);
                parms.height = (int) (newVal * DPtoPXconversionFactor);
                                for (int i = 0; i <= 11; i++) {
                    //set size to the imageview
                    imageArray[i].setLayoutParams(parms);

                }


            }

        });

    }

    /*--------------------------------------------------------------
           changeColor
     --------------------------------------------------------------
    */
    public void changeColor(View view) {


        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Setting the color of each cell to the selected unmatched color
        switch (view.getId()) {
            case R.id.rbRed:


                for (int i = 0; i <= 11; i++) {

                    imageArray[i].setImageResource(R.color.red);
                }


                break;
            case R.id.rbGreen:


                for (int i = 0; i <= 11; i++) {

                    imageArray[i].setImageResource(R.color.green);


                }
                break;

            case R.id.rbBlue:


                for (int i = 0; i <= 11; i++) {

                    imageArray[i].setImageResource(R.color.blue);
                }
                break;

        }


    }

    /*--------------------------------------------------------------
              resetBoard
        --------------------------------------------------------------
       */
    public void resetBoard(View view) {

        //Applying the selected color to each cell

        for (int i = 0; i <= 11; i++) {

            int id = radioGroupColorvalue.getCheckedRadioButtonId();
            if (id == rbRed.getId())
                imageArray[i].setImageResource(R.color.red);
            if (id == rbGreen.getId())
                imageArray[i].setImageResource(R.color.green);
            if (id == rbBlue.getId())
                imageArray[i].setImageResource(R.color.blue);


        }
        //) Randomizing the image pointer where each image of the six images appears twice in the twelve-cell board

        Random random = new Random();
        for (int i = 0; i <= 11; i++) {
            if (i > 5) {

                colorArrray = new ArrayList<Integer>(6);
                colorArrray.addAll(Arrays.asList(imageNumber));
            }
            int num = random.nextInt(colorArrray.size());

            imageCellPointer[i] = colorArrray.get(num);

            colorArrray.remove(colorArrray.get(num));

        }

        //) Resetting the tries and matches counts.

        noOfTries.setText(nooftries);

        noofmatchespalyed++;

        noOfGames.setText(noofmatchespalyed);


        // Show toast message
        Toast.makeText(getApplicationContext(),
                " Board has been reset",
                Toast.LENGTH_SHORT).show();


    }

    /*--------------------------------------------------------------
                  nextTry
            --------------------------------------------------------------
           */
    public void nextTry(View view)

    {

        //) Setting two matched cells to  selected match color  and disabling the cells

        if (ptr == 2 && (imageCellPointer[macthedpointerfirst] == imageCellPointer[matchedpointersecond])) {
            imageArray[macthedpointerfirst].setImageResource(R.color.yellow);
            imageArray[matchedpointersecond].setImageResource(R.color.yellow);
            imageArray[macthedpointerfirst].setEnabled(false);
            imageArray[matchedpointersecond].setEnabled(false);
            ptr=0;

        } 
else if ((ptr == 2) && (imageCellPointer[macthedpointerfirst] != imageCellPointer[matchedpointersecond])) 

{


            //resetting the unmatched cells back to the selected unmatched color.
            int id = radioGroupColorvalue.getCheckedRadioButtonId();


            if (id == rbRed.getId())
                imageArray[macthedpointerfirst].setImageResource(R.color.red);
            if (id == rbGreen.getId())
                imageArray[macthedpointerfirst].setImageResource(R.color.green);
            if (id == rbBlue.getId())
                imageArray[macthedpointerfirst].setImageResource(R.color.blue);

            if (id == rbRed.getId())
                imageArray[matchedpointersecond].setImageResource(R.color.red);
            if (id == rbGreen.getId())
                imageArray[matchedpointersecond].setImageResource(R.color.green);
            if (id == rbBlue.getId())
                imageArray[matchedpointersecond].setImageResource(R.color.blue);
            ptr=0;


        }


        nooftries = nooftries + 1;

    }

    /*--------------------------------------------------------------
                     matchImage
               --------------------------------------------------------------
              */
    public void matchImage(View v) {

        //Testing how many cells are currently selected
        int i = 0;

        count = count + 1;
        int imagePointer = 0;
        int id = v.getId();

        //Testing for single cell and updating the tracking number
        for (i = 0; i <= 11; i++) {
            if (id == imageArray[i].getId()) {
                System.out.println("Id is present");
                count = count + 1;
                break;
            }
        }


        imageArray[i].setImageResource(imageCellPointer[i]);
        ptr = ptr + 1;

        

        if (ptr == 1)
            macthedpointerfirst = i;

        ////Testing for two cell
        if (ptr == 2)
            matchedpointersecond = i;

        //Checking if the two revealed images are the same

        if (ptr == 2 && (imageCellPointer[macthedpointerfirst] == imageCellPointer[matchedpointersecond])) {
            
            matched = true;


            //enabling nextTry
            nextTry.setEnabled(true);

            // dialog box to inform the user  there was a match
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle(APP_NAME + " Matched");
            builder.setMessage("The  images  matched");
            builder.setPositiveButton("OK", null);
            builder.show();

            //update and showing the tries and matches counts
            nooftries = nooftries + 1;
            noOfTries.setText(nooftries);
            noOfGames.setText(noofmatchespalyed);

            ////Checking if the two revealed images are not same
        } else if ((ptr == 2) && (imageCellPointer[macthedpointerfirst] != imageCellPointer[i])) {
            System.out.println(" Not matched pointer second");

            //matchedpointersecond = i;
            matched = false;
            ptr = 0;
            System.out.println("matched pointer second" + matchedpointersecond);
            nextTry.setEnabled(true);


            // dialog box to inform the user  there is  no match
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle(APP_NAME + " Not Matched");
            builder.setMessage("The  images did not matched");
            builder.setPositiveButton("OK", null);
            builder.show();


            //update and showing the tries and matches counts
            nooftries = nooftries + 1;
            noOfTries.setText(nooftries);
            noOfGames.setText(noofmatchespalyed);


        }


    }


}


