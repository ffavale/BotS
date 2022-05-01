# Simulation class

## Input:
- Member count
- Ratio men/women
- Ratio faithful/philanderers
- Ratio coy/fast
- Cost/benefit coefficients
The coefficients will be used to calculate the number of cycles individuals are unable to have children and spread their genetic traits.
    - Cost of courtship
    - Cost of childrearing
    - Benefits of procreation

## Fields
- Simulation Id
- Base extroversion amount
- Starting Extroversion Points
- Extroversion recycle rate

## Setup:
- Generate individuals into dynArray

## Loop
- Check stability (Exit conditions)
- Generate loop Event
- Add participants to event
- Event takes place (see event info)
- Procreation happens (conditional FPCS)
- Generate new individuals
- Cost/Benefits applied to individuals (according to FPCS)
- Check which individuals live next loop
