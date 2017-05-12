# AndoidApps
You've been hired by Memory Matcher to write an Android application to promote the user’s (brain) memory.  The application has a grid of twelve cells hiding six images.  The application randomizes where the images are hidden.  The user picks one cell to reveal its image and then picks another cell to reveal its image.  If the two images match, the two cells are marked as matched.  If the two images don’t match, the two cells continue to be marked as unmatched.  The application has a single screen containing the following elements:
	Inputs
	● A group of three radio buttons to set the color of unmatched cells.
	● A number picker for sizing the width and height of the cells.  The control should a range of 20dp to 120dp.  Since the units are in DP, they have to translated to pixels using:
● A button for resetting the board.  This should be enabled at all times.
	● A button for attempting the next match.  This should only be enabled after the user has revealed two images.
	● Twelve cells (image views or image buttons) in a grid of four columns across and three rows down.
	Outputs
	● A label and a text box showing the number of match tries.
	● A label and a text box showing the number of matches.

The application uses seven images: one for the icon and six to be hidden and revealed in the cells.  It also incorporates named colors into the application by replacing file app/res/values/colors.xml
