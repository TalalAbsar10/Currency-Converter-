# Currency-Converter-App

## Functionality
1- App fethces the data on the first screen and saves the data in the database.<br />
2- The second sreen shows the conversion of the amount as per the currencies selected from the previous screen.<br />
  -There is a timer of 30 seconds on the second screen, and if the timer finishes it navigate back to the first screen.<br />
  -If the convert button is pressd, the dialog button is shown, which asks to approve or decline the converion.<br />
  -Once approve is clicked it navigates to the last screen.<br />
  -Last screen shows the approved conversion and the exact rate of amount 1 from Currency to to Currency e-g:(1 USD to 3.67 AED).<br /> 
3- If the the device is not connected to the internet, the data is fetched from the database.

**Tech Stack**
- MVVMM
- Clean Architecture
- Retrofit
- Coroutines
- Room
- Hilt-Dagger
- Flow
- Navigation
- Data Binding
- view Binding
- Unit tests
- Integration tests
