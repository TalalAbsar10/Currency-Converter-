# Currency-Converter-App

## Functionality
1- The App fetches the data on the first screen and saves the data in the database.<br />
2- The second screen shows the conversion of the amount as per the currencies selected from the previous screen.<br />
  -There is a timer of 30 seconds on the second screen, and if the timer finishes it navigates back to the first screen.<br />
  -If the convert button is pressed, the dialog button is shown, which asks to approve or decline the conversion.<br />
  -Once approve is clicked it navigates to the last screen.<br />
  -The Last screen shows the approved conversion and the exact rate of amount 1 from Currency to Currency e-g:(1 USD is 3.67 AED).<br /> 
3- If the device is not connected to the internet, the data is fetched from the database.

**Tech Stack**
- MVVM
- Clean Architecture
- Retrofit
- Coroutines
- Room
- Hilt-Dagger
- Flow
- Navigation
- Data Binding
- View Binding
- Unit tests
- Integration tests
