# Settings_manager.py
Python script to easily generate settings files.
Using a script allows the creating of a large number of tests quickly.

# Main class

## main function
Program starts here.

Given:
- a settings file target
- a logging directory target
- a results directory target

Do:
- Read the simulation settings out of the settings file for each simulation
- Create the desired simulations with the required settings
- For each simulation:
    - Write the final state into a results directory
