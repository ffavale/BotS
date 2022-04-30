# Plan for the BotS project

## Software Structure
- Input:
    - Initial population
        - Member count
        - Ratios of types
    - Cost coefficients
- Setup (t)
    - generate all objects
- WHILE LOOP:
    - for every individual:
        - select event to attend
    - For every event: (t)
            - For every individual in it: (t)
                - Rel chance (given among other things by horoscope)
                - procreation decision (decided by FPCS | if yes, break)
                - edit life expectancy
                - is alive?
    - Replenish Extroversion points (with energy bleed?)
    - Log stuff
        <!-- GENERATION: The average interval of time between the birth of parents and the birth of their offspring. -->
    - Check stability

## Additional Features
- Horoscope
- Logging (Might be slow)
- Random meetups
- Event based meetups
- Introversion/Extroversion

## Directory Structre
- src - contains all the source files
    - every subdirectory is a package

- test - contains the code to test the src code
    - mirror image of src

- documentation
    - instructions for use
    - latex paper directory
    - notes and stuff

- script
    - contains the scripts for the building and testing of the project
