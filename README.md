# Gas Station Software Application
A Java based Gas Station Software System with an integrated POS system

## Application Outline
#### Dashboard window:
This is the entrypoint to the application. It will have a login window to start and once the user has been authenticated, tabs will appear to the following functionalities:
  * **Pump management tab** – management only*
     * This should allow setting the price for all gas types – only managers will have access to change this. Any changes must update to the database*
  * **Global tank tab** – all employee*
     * This should allow the user to see how much gas has been sold and how much is available from each gas type (Regular, plus and supreme). Set it to 1000 liters to start and remove the amount of gas from each transaction. This must be read from the database
  * **Launch Pump** – all employee*
     * This will launch the pumpSystem GUI in a new window and allow theuser to pre-set an amount. There should be a START, STOP and COMPLETE SALE button. The START button starts the sale and will update the numbers on the pumpSystem gradually. The STOP will stop the transaction. The transaction should stoponce the desired amount (previously set) is reached. 
     
     
#### We're using
  * Java
  * Swing/AWT
  * Brains lol ❤
