The project structure follows the Model-View-Controller
architecture.

Each folder in the src subfolder will lead to where
the different parts of the project were designed.

Under the Controller subsection is the interface that
interacts between the the other sections.

Under the Model subsection is all of the code that
dictates how the game is played.  The main code
here is the board class.  This details most of the
functionality of the game.  The rest of the classes
detail the other aspects of the game.

Under the View subsection is where the model section
is displayed in a GUI.  The main window will hold
all of the other displays found in the View section

The code starts in the main class which simply runs the
mainwindow.  From there the New Game Screen starts and then
the game starts from the input given from there.